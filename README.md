# Ex2-
repository of Ex2 - assigment of algorithem on graphs and represending of graph

### class Node impelment node_data
this class represent a vertex in the graph, her pram is: @int key, @point3D ppoint, @double weihgt,
                                                         @boolean viseted, @int tag, @string info.
#public Node (int key);
  	initalize with key, and random point.
	
#public Node (int key, Point3D p);
  	initalize with key, point.
	
#public Node (int key, Point3D p, int tag, String info);
  	initalize with key, point, tag and info.

#public Node (Node n);
    intialize new key (deep copy) from another Node.
    
	@Override
	#public int getKey();
    Return the key (id) associated with this node.
		
	@Override
	#public Point3D getLocation();
      Return the location (of applicable) of this node, if none return null.	
		
	@Override
	#public void setLocation(Point3D p);
    	new location  (position) of this node.
      
	@Override
	#public double getWeight();	
		Return the weight associated with this node.
		
	@Override
	#public void setWeight(double w);
  	Allows changing this node's weight.
		
	@Override
	#public String getInfo();
	   return the remark (meta data) associated with this node.
    
	@Override
	#public void setInfo(String s);
	    Allows changing the remark (meta data) associated with this node.
	
	@Override
	public int getTag();
		return this.tag;
	
	@Override
	public void setTag(int t);
	  Allow setting the "tag" value for temporal marking an node - common practice for marking by algorithms.

	@Override
	#public boolean equals(Object n);
	    return true if the object is istance of node_data and had the same key.
      else return false.
	
	@Override
	#public String toString();
      to string of the node.

	#public boolean getVisited ();
	    return the corennt status od the node.
	
	#public void setVisited (boolean bool);
	    change the corennt status of the Node.
			
