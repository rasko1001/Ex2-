package dataStructure;

import java.util.*;


import algorithm.*;

public class Main
{
	
	public static void main(String args[]) 
	{ 	
		
		// Create graphs given in the above diagrams 
        DGraph g1 = new DGraph(); 
        for (int i = 0; i < 4; i++)   
        	g1.addNode(new Node(i));
        
    	g1.addNode(new Node(9));
 
        
        g1.connect(0, 9, 10); 
        g1.connect(1, 0, 10); 
        g1.connect(1, 2, 100); 
        g1.connect(2, 1, 10); 
        g1.connect(2, 9, 10); 
        g1.connect(3, 1, 10); 
        g1.connect(3, 2, 10); 
        g1.connect(9, 3, 10); 
        
        Graph_Algo ga = new Graph_Algo();
        ga.init(g1);
        double d = ga.shortestPathDist(1, 2);
        
        ga.shortestPath(1, 2);
        
        System.out.println("shortestPathDist=" + d);
        
       
        //check if graph is not strongly connected or not
		if (ga.isConnected())
			System.out.println("Graph is Strongly Connected");
		else
			System.out.println("Graph is not Strongly Connected");
		
        
        
        Collection <node_data> nodes = g1.getV();
        
        
        g1.getNode(0);
        for (node_data ver : nodes)
        {
			System.out.println(ver.toString());
		}
        
        g1.connect(0, 1, 0.5);
        System.out.println(g1.getEdge(0, 1));
        g1.connect(0, 1, 0.6);
        System.out.println(g1.getEdge(0, 1));
        
        g1.addNode(new Node(4));
        g1.connect(1, 2, 8); 
        g1.connect(2, 3, 9); 
        g1.connect(3, 0, 3.6); 
        g1.connect(2, 4, 7); 
        g1.connect(4, 3, 44.5); 
        System.out.println(g1.edgeSize());
        
       
       
        /*DGraph g2 = new DGraph(); 
        g2.connect(0, dest, w);(0, 1); 
        g2.addEdge(1, 2); 
        g2.addEdge(2, 3); 
        if (g2.isSC()) 
            System.out.println("Yes"); 
        else
            System.out.println("No"); */
		    
	 }

}
