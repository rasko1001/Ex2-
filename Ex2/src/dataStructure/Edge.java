package dataStructure;

public class Edge implements edge_data
{
	
	private int _src = 0;
	private int _dest = 0;
	private double _weigth = 0;
	private String _info ="";
	private int _tag = 0;
	
	/*public Edge ()
	{
		
	}*/
	
	public Edge (int src, int dest, double weigth)
	{
		this._src = src;
		this._dest = dest;
		if (weigth > 0.0)
			this._weigth = weigth;
		else
			this._weigth = 0.0;
	}
	
	public Edge (int src, int dest, double weigth,String info, int tag)
	{
		this._src = src;
		this._dest = dest;
		if (weigth > 0.0)
			this._weigth = weigth;
		else
			this._weigth = 0.0;
		this._info = info;
		this._tag = tag;
	}
	
	public Edge (Edge edge)
	{
		this._src = edge.getSrc();
		this._dest = edge.getDest();
		this._weigth = edge.getWeight();
		this._info = edge.getInfo();
		this._tag = edge.getTag();
	}

	@Override
	public int getSrc() 
	{
		return this._src;
	}

	@Override
	public int getDest() 
	{
		return this._dest;
	}

	@Override
	public double getWeight() 
	{
		return this._weigth;
	}

	@Override
	public String getInfo() 
	{
		return this._info;
	}

	@Override
	public void setInfo(String s) 
	{
		this._info = s;
	}

	@Override
	public int getTag() 
	{		
		return this._tag;
	}

	@Override
	public void setTag(int t)
	{
		this._tag = t;
	}

	/*
	public int getHashCode (int src, int dest)
	{
		int hashCode = 0;
		hashCode = src*src*dest+src+src/2+dest/3-dest;
		return hashCode;
	}
	*/
	
	@Override
	public String toString()
	{
		return "the src of the edge is in: " + this._src + " and the dest is in: " + this._dest + " and the wiegth between them is: " + this._weigth;
	}
	
}
