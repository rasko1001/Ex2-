package Test;
import dataStructure.*;
import utils.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class NodeTest
{
	@Test
	void testSetLocation() 
	{
		Point3D p = new Point3D(1, 1, 1);
		node_data t = new dataStructure.Node(5, p, 0);
		p.add(1, 1, 1);
		t.setLocation(p);
		assertEquals(p.toString(), t.getLocation().toString());
	}


	@Test
	void testSetWeight()
	{
		Point3D p = new Point3D(1, 1, 1);
		node_data t = new dataStructure.Node(5, p, 0);
		double w = 1;
		t.setWeight(w);
		assertEquals(w, t.getWeight());
	}


	@Test
	void testSetInfo() 
	{
		Point3D p = new Point3D(1, 1, 1);
		node_data t = new dataStructure.Node(5, p, 0);
		String info = "new info";
		t.setInfo(info);;
		assertEquals(info, t.getInfo());
	}


	@Test
	void testSetTag() 
	{
		Point3D p = new Point3D(1, 1, 1);
		node_data t = new dataStructure.Node(5, p, 0);
		int tag = 56;
		t.setTag(tag);
		assertEquals(tag, t.getTag());
	}

	@Test
	void testEqualsObject() 
	{
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

	@Test
	void testGetVisited() {
		fail("Not yet implemented");
	}

	@Test
	void testSetVisited() {
		fail("Not yet implemented");
	}

}
