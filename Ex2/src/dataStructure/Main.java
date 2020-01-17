package dataStructure;

import java.awt.Color;
import java.awt.List;
import java.util.*;


import algorithm.*;
import gui.GUI;
import utils.StdDraw;

public class Main
{
	
	public static void main(String args[]) 
	{ 	
		
		// Create graphs given in the above diagrams 
        graph g1 = new DGraph(); 
        Collection< node_data> nodes = new ArrayList<node_data>();
        for (int i = 0; i < 7; i++) 
        {
        	node_data t = new Node (i);
        	g1.addNode(t);
        	nodes.add(t);
        	
        }
        
   
        Thread gui = new GUI(g1);
        gui.start();
        
        //gui.drawNode(nodes);
    	//gui.MainDraw(nodes);
        
        g1.addNode(new Node(9));
 
        
      /*  g1.connect(0, 9, 10); 
        g1.connect(1, 0, 10); 
        g1.connect(1, 2, 100); 
        g1.connect(2, 1, 10); 
        g1.connect(2, 9, 10); 
        g1.connect(3, 1, 10); 
        g1.connect(3, 2, 10);*/ 
        g1.connect(3, 9, 11.8); 
        g1.connect(9, 3, 10);
        
        for (int i = 0; i < 10; i++) 
        {
        	int t1 = (int)(Math.random()*7);
        	int t2 = (int)(Math.random()*7);

        	if (t1 != t2)
        		g1.connect(t1, t2, 100*Math.random()); 
        	
        	else if (t2 != 6)
        		g1.connect(t1, t2+1, 100*Math.random()); 
        	
        	else 
        		g1.connect(t1, t2-1, 100*Math.random()); 
        }
        
        
        Graph_Algo ga = new Graph_Algo();
        ga.init(g1);
        double d = ga.shortestPathDist(1, 2);
        
       
        //gui.draw(g1);
        
        for (int i = 7; i < 20; i++) 
        {
        	try 
        	{
        		Thread.sleep(100);
        		g1.addNode(new Node (i));       	
        	}
        	catch (Exception e) {
			}
        }
        
        g1.addNode(new Node (158));
        g1.addNode(new Node (12));
        g1.addNode(new Node (7));
        g1.connect(12, 3, 0.8);
        g1.connect(158, 2, 98);
        ga.shortestPath(1, 5);
        
        System.out.println("shortestPathDist=" + d);
        
       
        //check if graph is not strongly connected or not
		if (ga.isConnected())
			System.out.println("Graph is Strongly Connected");
		else
			System.out.println("Graph is not Strongly Connected");
		
        
        
        //Collection <node_data> nodes = g1.getV();
        
        
        g1.getNode(0);
        for (node_data ver : nodes)
        {
        	Collection<edge_data> edges = g1.getE(ver.getKey());
        	if (edges != null)
				for (edge_data edge: edges)
				{
					System.out.println(edge);
				}
		}
        
  /*      g1.connect(0, 1, 0.5);
        System.out.println(g1.getEdge(0, 1));
        g1.connect(0, 1, 0.6);
        System.out.println(g1.getEdge(0, 1));*/
        
        g1.addNode(new Node(4));
        g1.connect(1, 2, 8); 
        g1.connect(2, 3, 9); 
        g1.connect(3, 0, 3.6); 
        g1.connect(2, 4, 7); 
        g1.connect(4, 3, 44.5); 
        System.out.println(g1.edgeSize());	    
	 }
}
