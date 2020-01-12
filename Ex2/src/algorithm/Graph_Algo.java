package algorithm;

import java.io.*;
import java.util.*;
import dataStructure.*;

/**
 * This class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms
{
	
	private graph g;
	
	public Graph_Algo () 
	{
		
	}
	
	/**
	 * Init this set of algorithms on the parameter - graph.
	 * @param g
	 */
	@Override
	public void init(graph g) 
	{
		this.g = g;
		
	}

	/**
	 * Init a graph from file
	 * @param file_name
	 * @throws ClassNotFoundException 
	 */
	@Override
	public void init(String file_name) throws IOException
	{
		// read the content from file
		try
		{
			ObjectInputStream inpotObj = new ObjectInputStream(new FileInputStream(file_name));
		    this.init((graph)inpotObj.readObject());
		    inpotObj.close();   
		} 
		catch (FileNotFoundException e)
		{
			throw new IOException ("There was something wrong in the file"); 
		} catch (ClassNotFoundException e) {
			throw new IOException ("There was something wrong in the file"); 
		}		
	}

	/** Saves the graph to a file.
	 * 
	 * @param file_name
	 */
	@Override
	public void save(String file_name) throws IOException 
	{
		// write the content in file 
		try 
		{
			ObjectOutputStream outpotObj = new ObjectOutputStream(new FileOutputStream(file_name));
		    //String fileContent = "This is a sample text.";
			outpotObj.writeObject(this.g);
			outpotObj.close();
		} 
		catch (IOException e) 
		{
			throw new IOException ("There was something wrong in the file"); 
		}
	}

	
	private void DFS(graph g1, node_data v)
	{
		// mark current node as visited
		((Node)v).setVisited(true);

		Collection<edge_data> edges = g1.getE(v.getKey());	
		if (edges == null)
			return;
		
		for (edge_data edge : edges) 
		{
			Node node = (Node)g1.getNode(edge.getDest());
			if (!node.getVisited())
				DFS(g1, node);
		}
	}
	
	
	/**
	 * Returns true if and only if (iff) there is a valid path from EVREY node to each
	 * other node. NOTE: assume directional graph - a valid path (a-->b) does NOT imply a valid path (b-->a).
	 * @return
	 */
	@Override
	public boolean isConnected() 
	{
		// do for every vertex
		Collection<node_data> nodes = this.g.getV();
	
		for (node_data node : nodes) 
		
		{
			// stores vertex is visited or not

			// start DFS from first vertex
			DFS(this.g, node);

			// If DFS traversal doesn’t visit all vertices,
			// then graph is not strongly connected
			Collection<node_data> nodess = g.getV();
			for (node_data nod : nodess)
			{
				if (!((Node)nod).getVisited())
					return false;
				
				((Node)nod).setVisited(false);
			}
		}
		return true;		
	}

	/**
	 * returns the length of the shortest path between src to dest
	 * @param src - start node
	 * @param dest - end (target) node
	 * @return
	 */
	@Override
	public double shortestPathDist(int src, int dest)
	{
		int max = -1;

		// Adjacency list representation of the 
		// connected edges 
		List<List<Node1> > adj = new ArrayList<List<Node1> >(); 

		
		
		Collection<node_data> nodes = this.g.getV();
		
		for (node_data node : nodes)
		{
			if (max < node.getKey())
				max = node.getKey()+1;
		}
		
		// Initialize list for every node 
		for (int i = 0; i < max; i++)
		{ 
			List<Node1> item = new ArrayList<Node1>(); 
			adj.add(item); 
		} 
		
		
		for (node_data node : nodes)
		{
			Collection<edge_data> edges = this.g.getE(node.getKey());
			if (edges != null)
			{
				for (edge_data edge : edges) 
				{
					adj.get(node.getKey()).add(new Node1(edge.getDest(), edge.getWeight()));
				}
			}
		}	
		
		DPQ d = new DPQ(max);
		d.dijkstra(adj, src);
		return d.dist[dest];
	}

	/**
	 * returns the the shortest path between src to dest - as an ordered List of nodes:
	 * src--> n1-->n2-->...dest
	 * see: https://en.wikipedia.org/wiki/Shortest_path_problem
	 * @param src - start node
	 * @param dest - end (target) node
	 * @return
	 */
	@Override
	public List<node_data> shortestPath(int src, int dest)
	{

		// Adjacency list representation of the 
		// connected edges 
		List<List<Node1> > adj = new ArrayList<List<Node1> >(); 

		int max = -1;

		
		
		Collection<node_data> nodes = this.g.getV();
		
		for (node_data node : nodes)
		{
			if (max < node.getKey())
				max = node.getKey()+1;
		}
		
		// Initialize list for every node 
		for (int i = 0; i < max; i++)
		{ 
			List<Node1> item = new ArrayList<Node1>(); 
			adj.add(item); 
		} 
		
		// Initialize list for every node 
		for (int i = 0; i < max; i++) 
		{ 
			List<Node1> item = new ArrayList<Node1>(); 
			adj.add(item); 
		} 
		
		for (node_data node : nodes)
		{
			Collection<edge_data> edges = this.g.getE(node.getKey());
			if (edges != null)
			{
				for (edge_data edge : edges) 
				{
					adj.get(node.getKey()).add(new Node1(edge.getDest(), edge.getWeight()));
				}
			}
		}	
		
		DPQ d = new DPQ(max);
		d.dijkstra(adj, src);
		
		List<node_data> ret = new ArrayList<node_data>();
		for (Integer integer : d.path.get(dest)) 
		{
			ret.add(this.g.getNode(integer));			
		}
		
		return (ret);
	}

	/**
	 * computes a relatively short path which visit each node in the targets List.
	 * Note: this is NOT the classical traveling salesman problem, 
	 * as you can visit a node more than once, and there is no need to return to source node - 
	 * just a simple path going over all nodes in the list. 
	 * @param targets
	 * @return
	 */
	@Override
	public List<node_data> TSP(List<Integer> targets) 
	{
		graph tempG = new DGraph();
		Collection<edge_data> tempE;
		for (Integer integer : targets)
		{
			Node temp1 =(Node)this.g.getNode(integer);
			Node temp2 = new Node (temp1);
			tempG.addNode(temp2);
		}
		
		for (Integer integer : targets)
		{
			if (this.g.getE(integer) != null)
			{
				tempE = this.g.getE(integer);
				
				for (edge_data edge : tempE)
				{
					if (targets.contains(edge.getDest()))
						tempG.connect(edge.getSrc(), edge.getDest(), edge.getWeight());
				}
			}
		}
		Graph_Algo tempGA = new Graph_Algo();
		tempGA.init(tempG);
		if (!tempGA.isConnected())
			return null;
		
		return null;
	}
	
	/** 
	 * Compute a deep copy of this graph.
	 * @return
	 */
	@Override
	public graph copy() 
	{
		graph newG = new DGraph();
		Collection<node_data> nodes = this.g.getV();
		for (node_data node : nodes)
		{
			newG.addNode(node);
			Collection<edge_data> edges = this.g.getE(node.getKey());
			for (edge_data edge : edges)
			{
				newG.connect(edge.getSrc(), edge.getDest(), edge.getWeight());
			}
		}
 		return newG;
	}
	
	// Class to represent a node in the graph 
	class Node1 implements Comparator<Node1>
	{ 
		public int node; 
		public double cost; 

		public Node1() 
		{ 
		} 

		public Node1(int node, double cost) 
		{ 
			this.node = node; 
			this.cost = cost; 
		} 

		@Override
		public int compare(Node1 node1, Node1 node2) 
		{ 
			if (node1.cost < node2.cost) 
				return -1; 
			if (node1.cost > node2.cost) 
				return 1; 
			return 0; 
		} 
	} 
	
	public class DPQ
	 { 
		private double dist[]; 
		private List<List<Integer>> path = new ArrayList<List<Integer>>(); 
		private Set<Integer> settled; 
		private PriorityQueue<Node1> pq; 
		private int V; // Number of vertices 
		List<List<Node1> > adj; 

		public DPQ(int V) 
		{ 
			this.V = V; 
			for (int i = 0; i < V; i++) 
			{ 
				List<Integer> item = new ArrayList<Integer>(); 
				path.add(item); 
			} 
			dist = new double[V]; 
			settled = new HashSet<Integer>(); 
			pq = new PriorityQueue<Node1>(V, new Node1()); 
		} 

		// Function for Dijkstra's Algorithm 
		public void dijkstra(List<List<Node1> > adj, int src) 
		{ 
			this.adj = adj; 

			for (int i = 0; i < V; i++) 
				dist[i] = Integer.MAX_VALUE; 

			// Add source node to the priority queue 
			pq.add(new Node1(src, 0)); 

			// Distance to the source is 0 
			dist[src] = 0; 
			path.get(src).add(src);
			while ((settled.size() != V) && (pq.size() > 0))
			{ 
				// remove the minimum distance node 
				// from the priority queue
				int u = pq.remove().node; 

				// adding the node whose distance is 
				// finalized 
				settled.add(u); 

				e_Neighbours(u); 
			} 
			
			
		} 

		// Function to process all the neighbours 
		// of the passed node 
		private void e_Neighbours(int u) 
		{ 
			double edgeDistance = -1; 
			double newDistance = -1; 

			// All the neighbors of v 
			for (int i = 0; i < adj.get(u).size(); i++) 
			{ 
				Node1 v = adj.get(u).get(i); 

				// If current node hasn't already been processed 
				if (!settled.contains(v.node)) 
				{ 
					edgeDistance = v.cost; 
					newDistance = dist[u] + edgeDistance; 

					// If new distance is cheaper in cost 
					if (newDistance < dist[v.node]) 
					{
						dist[v.node] = newDistance;
						
						List<Integer> tmp = new ArrayList<Integer>();
						for (Integer integer : path.get(u)) 
						{
							tmp.add(integer);
						} 							
						tmp.add(v.node);
						path.set(v.node, tmp);
					}

					// Add the current node to the queue 
					pq.add(new Node1(v.node, dist[v.node])); 
				} 
			} 
		} 
	 }

}
