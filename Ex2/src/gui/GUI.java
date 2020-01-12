package gui;


import algorithm.*;
import dataStructure.*;
import utils.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import utils.StdDraw;

public class GUI
{
	private graph g;
	//private HashMap<Integer, node_data> nodes = new HashMap<Integer, node_data>();
	private HashMap<Integer, Collection<edge_data> > edges = new HashMap<Integer, Collection<edge_data>>();
	private Collection<node_data> nodes = g.getV();
	
	
	
	private void edge_hash ()
	{
		Collection<edge_data> edgess;
		
		for (node_data node : nodes) 
		{
			edgess = this.g.getE(node.getKey());
			edges.put(node.getKey(), edgess);
		}
	}
	
	private void drawColorLine(double x0, double x1, double y0, double y1, Color color)
	{
		StdDraw.setPenColor(color);
		StdDraw.line(x0, y0, x1, y1);
	}
	
	
	
	
	
	
	public void drawNode(/*int width, int height, Range rx, Range ry, int resolution*/) 
	{
		
		/*double x0 = rx.get_min();
		double x1 = rx.get_max();
		double y0 = ry.get_min();
		double y1 = ry.get_max();*/
		
		//double res = (x1 - x0) / resolution;
		
		StdDraw.setCanvasSize(1000,1000);
		StdDraw.setXscale(-500, 500);
		StdDraw.setYscale(-500, 500);
		
				
		/*for (double d = 0; d < x1; d++)
		{
			drawColorLine(d, y0, d, y1, StdDraw.LIGHT_GRAY);
			drawColorLine(d, -0.5, d, 0.5, StdDraw.BLACK);
		}
		for (double d = 0; d > x0; d--)
		{
			drawColorLine(d, y0, d, y1, StdDraw.LIGHT_GRAY);
			drawColorLine(d, -0.5, d, 0.5, StdDraw.BLACK);
		}
		
		for (double d = 0; d < y1; d++)
		{
			drawColorLine(x0, d, x1, d, StdDraw.LIGHT_GRAY);
			drawColorLine(-0.5, d, 0.5, d, StdDraw.BLACK);
		}
		for (double d = 0; d > y0; d--)
		{
			drawColorLine(x0, d, x1, d, StdDraw.LIGHT_GRAY);
			drawColorLine(-0.5, d, 0.5, d, StdDraw.BLACK);
		}*/
		
		for (node_data node : nodes) 
		{
			Point3D t = node.getLocation();
			StdDraw.circle(t.x(), t.y(), 0.2);
		}
		
		for (node_data node_data : nodes) {
			
		}
		drawColorLine(0,2, 11, 122, StdDraw.BLACK);
		drawColorLine(0, y0, 0, y1, StdDraw.BLACK);
		StdDraw.draw
		
		
		int colorIndex = 0;
		
		for (function f : fColl)
		{
			Color clr = colorArray[colorIndex++];
			if (colorIndex == colorArray.length)
				colorIndex = 0;
			
			double preX = x0;
			double preY = f.f(x0);
			
			for (double d = x0; d < x1; d+=res)
			{
				double newY = f.f(d);
				drawColorLine(preX, preY, d, newY, clr);
				preX = d;
				preY = newY;		
				
			}
		}
		
	}
	
	/**
	 * Draws all the functions in the collection in a GUI window using the given JSON file
	 * @param json_file - the file with all the parameters for the GUI window. 
	 * Note: is the file id not readable or in wrong format should use default values. 
	 */
	
	public void drawFunctions(String json_file) 
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
	}
}
