package gui;


import algorithm.*;
import dataStructure.*;
import utils.*;
import java.awt.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import utils.StdDraw;

public class GUI extends Thread
{
	private graph g;
	//private HashMap<Integer, node_data> nodes = new HashMap<Integer, node_data>();
	private HashMap<Integer, Collection<edge_data> > edges = new HashMap<Integer, Collection<edge_data>>();
	//private Collection<node_data> nodes = g.getV();
	
	
	public GUI ()
	{super();}
	
	public GUI (graph g)
	{
		this.g = g;
	}
	
	public void init(graph g) 
	{
		this.g = g;	
	}
	
	/*private void drawLine(double x0, double x1, double y0, double y1, double weight)
	{
		double m = 0;
		if (x0 != x1)
			 m = (y0-y1)/(x0-x1);
		
		double tx = 5*(x0+x1)/6 ;
		double ty = m*(tx-x0)+y0;
		
		StdDraw.setPenColor(Color.blue);
		StdDraw.line(x0, y0, tx, ty);
		
		
		StdDraw.setPenColor(StdDraw.BOOK_RED);
		StdDraw.textLeft(tx, ty, "         "+ weight);
		
		StdDraw.setPenColor(Color.red);
		StdDraw.line(tx, ty, x1, y1);	
	}*/

	@Override
	public void run(/*graph g*/) 
	{
		
		double minX = Double.MAX_VALUE;
		double maxX = Double.MIN_VALUE;
		double minY = Double.MAX_VALUE;
		double maxY = Double.MIN_VALUE;
		
		Collection<node_data> nodes = g.getV();
		if (nodes != null)
		{
			for (node_data node : nodes)
			{
				double x = node.getLocation().x();
				double y = node.getLocation().y();
				
				if (x < minX)
					minX = x;
				
				 if (x > maxX)
					maxX = x;
				
				if (y < minY)
					minY = y;
				
				 if (y > maxY)
					maxY = y;
			}
			
			double xScale = (maxX - minX)*0.2;
			double yScale = (maxY - minY)*0.2;
			double rightScaleX = xScale*0.2;
	        double rightScaleY =  yScale*0.2;
	        
			StdDraw.setCanvasSize(1300, 700);
			StdDraw.setXscale(minX-xScale, maxX*1.2);
			StdDraw.setYscale(minY-yScale, maxY*1.2);
			
			StdDraw.setPenColor(StdDraw.BOOK_RED);
			StdDraw.textLeft(rightScaleX+4000, rightScaleY-2000, "  "+ "in red the dest's of the edge");	
			
			StdDraw.setPenColor(Color.GREEN);
			StdDraw.textLeft(rightScaleX+4000, rightScaleY-4000, "  "+ "in green the src's of the edge");	
			
			for (node_data node : nodes)
			{
				int key = node.getKey();
				
				double x0 = g.getNode(key).getLocation().x();
				double y0 = g.getNode(key).getLocation().y();
				
				StdDraw.setPenColor(Color.black);
				StdDraw.filledCircle((x0), (y0),(rightScaleY+rightScaleX)*0.2);
				
				StdDraw.setPenColor(Color.white);	
				StdDraw.text(x0, y0, " "+ node.getKey());
				
			}
			
			this.drawEdges(g);
		}
		return;
	}
	
	public void drawEdges(graph g) 
	{	
		Collection<node_data> nodes = g.getV();
		Collection<edge_data> edges;
		DecimalFormat df = new DecimalFormat("#.##");
		
		if (nodes != null)
		{
			for (node_data node : nodes)
			{
				int key = node.getKey();
				double x0 = g.getNode(key).getLocation().x();
				double y0 = g.getNode(key).getLocation().y();
				edges = g.getE(key);
				
				if (edges != null)
				{
					for (edge_data edge : edges)
					{
						double x1 = g.getNode(edge.getDest()).getLocation().x();
						double y1 = g.getNode(edge.getDest()).getLocation().y();
						double tx = 0;
						double ty = 0;
						
						double m = 0;
						if (x0 != x1)
							 m = (y0-y1)/(x0-x1);
						
						if(Math.abs(x0-x1) >= Math.abs(y0-y1))
						{
							tx = (x0+x1)*0.2;
							ty = m*(tx-x0)+y0;
						}
						
						else 
						{
							ty = (y0+y1)*0.2;
							tx = (ty-y0)/m+x0;
						}
						
						if (g.getEdge(edge.getDest(), key) == null)
							StdDraw.setPenColor(Color.GREEN);
						
						else 
							StdDraw.setPenColor(StdDraw.BOOK_RED);
	
						StdDraw.line(x0, y0, tx, ty);				
						
						StdDraw.setPenColor(StdDraw.BOOK_RED);	
						StdDraw.textLeft(tx, ty, "  "+ df.format(edge.getWeight()));				
						
						StdDraw.setPenColor(Color.red);
						StdDraw.line(tx, ty, x1, y1);				
					}
				}
			}
		}	
	}
   
        
		
	
	
	/**
	 * Draws all the functions in the collection in a GUI window using the given JSON file
	 * @param json_file - the file with all the parameters for the GUI window. 
	 * Note: is the file id not readable or in wrong format should use default values. 
	 */
	
	/*public void drawFunctions(String json_file) 
	{
		
		JSONParser parser = new JSONParser();

		try 
		{   
			int i = 0;
			Object obj = parser.parse(new FileReader(json_file));

			JSONObject jsonObject =  (JSONObject) obj;

			int width = ((Long)jsonObject.get("Width")).intValue();
			int height = ((Long)jsonObject.get("Height")).intValue();
			int resolution = ((Long)jsonObject.get("Resolution")).intValue();		
			JSONArray Range_X = (JSONArray)jsonObject.get("Range_X");
			JSONArray Range_Y = (JSONArray)jsonObject.get("Range_Y");
			double x0 = 0,x1 =0 ,y0 = 0 ,y1 = 0;
			
			
	        
	         //Iterating the contents of the array
	         Iterator<Long> itX = Range_X.iterator();
	         while(itX.hasNext()) 
	         {
	        	  x0 = itX.next().doubleValue();
	        	  x1 = itX.next().doubleValue();
	         }
	         
	         Iterator<Long> itY = Range_Y.iterator();
	         while(itY.hasNext()) 
	         {
	        	  y0 = itY.next().doubleValue();
	        	  y1 = itY.next().doubleValue();
	         }
	         
	         
	         Range RangeX = new Range (x0,x1);
	         Range RangeY = new Range (y0,y1);
	         
	         drawFunctions(width, height, RangeX, RangeY, resolution);
          
			
			
		}
		
		catch (FileNotFoundException e) 
		{			
			drawFunctions(500, 500, new Range(-20,20), new Range(-20,20), 1000);        
		}
		
		catch  (IOException e)
		{		
			drawFunctions(500, 500, new Range(-20,20), new Range(-20,20), 1000);        
		}
		
		catch (ParseException e) 
		{	
			drawFunctions(500, 500, new Range(-20,20), new Range(-20,20), 1000);        
		}
	}*/
}
