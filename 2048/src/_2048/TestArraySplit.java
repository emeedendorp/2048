package _2048;


import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestArraySplit {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testArraySplit() {

		    
		    //test arraySplit
		    
		    // generate input
		    ArrayList<Integer> values = new ArrayList<Integer>();
		    values.add(4);
		    values.add(32);
		    values.add(16);
		    values.add(2);
		    values.add(2);
		    values.add(16);
		    values.add(2);
		    values.add(16);
		    values.add(32);
		    values.add(4);
		    values.add(8);
		    values.add(2);
		    values.add(4);
		    values.add(4);
		    values.add(2);
		    values.add(0);
		    
		    Console.printValues("TestArraySplit Before test",values, 4, 4);		    

		    
		    //uitvoeren test
		    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		    result = Util.splitArray(values,4);

		    System.out.println(result);

		    

	}

}
