package dataStructure;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.event.RowSorterEvent.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * This class represents a directional weighted graph.
 * The class has a road-system or communication network in mind - and should support a large number of nodes (over 100,000).
 * The class base on an efficient compact representation.
 */

public class DGraph implements graph ,Serializable 
{
	private HashMap<Integer, node_data> nodes = new HashMap<Integer, node_data>();
	//HashMap<Integer, edge_data> edges = new HashMap<Integer, edge_data>();
	private HashMap<Integer, HashMap<Integer, edge_data>> edges = new HashMap<Integer, HashMap<Integer, edge_data>>();
	//HashMap<Integer, edge_data> dests = new HashMap<Integer, edge_data>();    
	private int countNodes = 0;
	private int countEdges = 0;
	

	public  DGraph()
	{

	}
	
	/*
	 * public  DGraph(DGraph g)
	{
		
		this.edges = g.getCloneEdges();
		this.nodes = g.getCloneNodes();
		
	}
	*/
	
	/**
	 * add a new node to the graph with the given node_data.
	 * if the node  already exists will not add the node 
	 * Note: this method should run in O(1) time.
	 * @param n
	 */
	@Override
	public void addNode(node_data n) 
	{
		if (!this.nodes.containsKey(n.getKey()))
		{
			node_data tmp = new Node(n.getKey(), n.getLocation(), n.getWeight(), n.getTag(), n.getInfo());
			this.nodes.put(tmp.getKey(), tmp);   
			countNodes++;
		}
	}
	
	/**
	 * Connect an edge with weight w between node src to node dest.
	 * * Note: this method should run in O(1) time.
	 * @param src - the source of the edge.
	 * @param dest - the destination of the edge.
	 * @param w - positive weight representing the cost (aka time, price, etc) between src-->dest.
	 */
	@Override
	public void connect(int src, int dest, double w)
	{	
		if (src == dest)
			throw new RuntimeException ("can't connect vertex to himself");
		if (!nodes.containsKey(src) || !nodes.containsKey(dest))
			throw new RuntimeException ("at least one of the vertex dosent't exists in the graph");
		if (this.edges.containsKey(src))
		{
			if (this.edges.get(src).containsKey(dest))
			{
				this.removeEdge(src, dest);
				countEdges--;
			}
		}
		
		Edge edge = new Edge (src, dest, w);
		if (this.edges.containsKey(src))
		{
			this.edges.get(src).put(dest, edge);
			countEdges++;
		}
		else
		{
			this.edges.put(src, new HashMap<Integer, edge_data>());
			this.edges.get(src).put(dest, edge);
			countEdges++;
		}
	}
	
	
	/**
	 * return the node_data by the node_id,
	 * @param key - the node_id
	 * @return the node_data by the node_id, null if none.
	 */
	@Override
	public node_data getNode(int key) 
	{
		if (this.nodes.containsKey(key))
			return this.nodes.get(key);
		return null;
	}
 
	/**
	 * return the data of the edge (src,dest), null if none.
	 * Note: this method should run in O(1) time.
	 * @param src
	 * @param dest
	 * @return
	 */
	@Override
	public edge_data getEdge(int src, int dest)
	{
		if(this.edges.containsKey(src))
			if(this.edges.get(src).containsKey(dest))
				return this.edges.get(src).get(dest);
		return null;
	} 
		

	/**
	 * This method return a pointer (shallow copy) for the
	 * collection representing all the nodes in the graph. 
	 * Note: this method should run in O(1) time.
	 * @return Collection<node_data>
	 */
	@Override
	public Collection<node_data> getV()
	{
		Collection<node_data> vertecies = nodes.values();

		return vertecies;  		
	}

	/**
	 * This method return a pointer (shallow copy) for the
	 * collection representing all the edges getting out of 
	 * the given node (all the edges starting (source) at the given node). 
	 * Note: this method should run in O(1) time.
	 * @return Collection<edge_data>
	 */
	@Override
	public Collection<edge_data> getE(int node_id)
	{
		if (this.edges.containsKey(node_id))
			 return edges.get(node_id).values();
			
		return null;
	}
	
	/**
	 * Delete the node (with the given ID) from the graph -
	 * and removes all edges which starts or ends at this node.
	 * This method should run in O(n), |V|=n, as all the edges should be removed.
	 * @return the data of the removed node (null if none). 
	 * @param key
	 */
	@Override
	public node_data removeNode(int key)
	{
		if (this.nodes.containsKey(key))
		{
			nodes.remove(key);
			countNodes--;
			return this.nodes.get(key);
		}
		return null;
	}

	/**
	 * Delete the edge from the graph, 
	 * Note: this method should run in O(1) time.
	 * @param src
	 * @param dest
	 * @return the data of the removed edge (null if none).
	 */
	@Override
	public edge_data removeEdge(int src, int dest)
	{
		if(this.edges.containsKey(src))
			if(this.edges.get(src).containsKey(dest))
			{
				edges.get(src).remove(dest);
				countEdges--;
				if (this.edges.get(src).size() == 0)
					edges.remove(src);
				return this.edges.get(src).get(dest);
			}			
		return null;
	}

	/** return the number of vertices (nodes) in the graph.
	 * Note: this method should run in O(1) time. 
	 * @return
	 */
	@Override
	public int nodeSize()
	{
		return nodes.size();
	}

	/** 
	 * return the number of edges (assume directional graph).
	 * Note: this method should run in O(1) time.
	 * @return
	 */
	@Override
	public int edgeSize()
	{
		int count = 0;
		// Iterating over keys only
		for (Integer key : this.edges.keySet()) 
		{
			count = count + this.edges.get(key).size();
		}
		
		return count;
	}
	
	/**
	 * return the Mode Count - for testing changes in the graph.
	 * @return
	 */
	@Override
	public int getMC()
	{
		return (countNodes+countEdges);
	}
		
	
	public LinkedList<Integer> [] listOfEdges()
	{
		LinkedList<Integer> adj[];	
		adj = new LinkedList[this.nodeSize()];
		Collection<node_data> Nodes = this.getV();
    	for (node_data node : Nodes)
    	{
    		adj[node.getKey()] = new LinkedList<Integer>();
    		Collection<edge_data> Edges = this.getE(node.getKey());
    		for (edge_data edge : Edges)
    		{
				adj[edge.getSrc()].add(edge.getDest());
			} 
		}	
		return adj;		
	}
	
	public int sizeOfNodeEdges()
	{
		return this.edges.size();
	}

	
	/*
	public HashMap<Integer, HashMap<Integer, edge_data>> getCloneEdges()	    
		{	
		String json = new Gson().toJson(this.edges);
		HashMap<Integer, HashMap<Integer, edge_data>> mapCopy= new Gson().fromJson( json, new TypeToken <HashMap<Integer, HashMap<Integer, edge_data>>>() {}.getType());
		   
		return mapCopy;
		}
	

	public HashMap<Integer, node_data> getCloneNodes()	    
		{	
		String json = new Gson().toJson(this.edges);
		HashMap<Integer, node_data> mapCopy= new Gson().fromJson( json, new TypeToken <HashMap<Integer, node_data>>() {}.getType());
		   
		return mapCopy;
		}
		*/
}
