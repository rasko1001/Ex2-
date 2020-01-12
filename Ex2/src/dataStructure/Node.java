package dataStructure;

import utils.Point3D;

public class Node implements node_data 
{
	private int key = 0;
	private Point3D point = null;
	private double weigth = 0;
	private int tag = 0;
	private String info = null;
	private boolean visited = false;
	/*public node ()
	{
	}*/
	
	public Node (int key) 
	{
		double t1 = Math.random()*100;
		double t2 = Math.random()*100;
		double x = t1*2+t2+13.3;
		double y = t2*1.3+t1+20;
		Point3D tp = new Point3D(x,y);
		this.point =tp;
		this.key = key;
	}
	
	public Node (int key, Point3D p , double weigth) 
	{
		this.key = key;
		this.point = new Point3D (p);
		this.weigth = weigth;
	}
	
	public Node (int key, Point3D p , double weigth, int tag, String info) 
	{
		this.key = key;
		this.point = new Point3D (p);
		this.weigth = weigth;
		this.tag = tag;
		this.info = info;
	}
	
	public Node (Node n) 
	{
		this.key    = n.getKey();
		this.point  = n.getLocation();
		this.weigth = n.getWeight();
		this.info   = n.getInfo();
		this.tag    = n.getTag();
	}
	
	@Override
	/**
	 * Return the key (id) associated with this node.
	 * @return
	 */
	public int getKey()
	{
		return this.key;
	}
		
		
	/** Return the location (of applicable) of this node, if
	 * none return null.
	 * 
	 * @return
	 */
	@Override
	public Point3D getLocation()
	{
		return this.point;
	}
		
		
	/** Allows changing this node's location.
	 * 
	 * @param p - new new location  (position) of this node.
	 */
	@Override
	public void setLocation(Point3D p)
	{
		this.point = new Point3D (p);
	}
	
		
		
	/**
	 * Return the weight associated with this node.
	 * @return
	 */
	@Override
	public double getWeight()
	{
		return this.weigth;
	}
		
		
	/**
	* Allows changing this node's weight.
	* @param w - the new weight
	 */
	@Override
	public void setWeight(double w)
	{
		this.weigth = w;
	}
		
		
	/**
	 * return the remark (meta data) associated with this node.
	 * @return
	 */
	@Override
	public String getInfo()
	{
		return this.info;
	}
	
	/**
	 * Allows changing the remark (meta data) associated with this node.
	 * @param s
	 */
	@Override
	public void setInfo(String s)
	{
		this.info = s;
	}
	
	/**
	 * Temporal data (aka color: e,g, white, gray, black) 
	 * which can be used be algorithms 
	 * @return
	 */
	@Override
	public int getTag()
	{
		return this.tag;
	}
	
	/** 
	 * Allow setting the "tag" value for temporal marking an node - common 
	 * practice for marking by algorithms.
	 * @param t - the new value of the tag
	 */
	@Override
	public void setTag(int t)
	{
		this.tag = t;
	}

	@Override
	public boolean equals(Object n)
	{
		if (n instanceof node_data)
		{
			if (this.key == ((node_data) n).getKey())
				return true;
		}
		return false;
	}
	
	@Override
	public String toString()
	{
		return "the node's key id: " + this.key + " and is location is in point: " + this.point.toString();
	}

	public boolean getVisited ()
	{
		return this.visited;
	}
	
	public void setVisited (boolean bool)
	{
		this.visited = bool;
	}
			
}
