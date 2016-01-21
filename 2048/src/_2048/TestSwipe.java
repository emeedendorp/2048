package _2048;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSwipe {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSwipe() {

		    Control tester = new Control();
		    
		    //test swipe(3)
		    
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
		    
		    System.out.println(values);
		    tester.values = values;
		    
		    
		    //verwachte uitvoer
		    ArrayList<Integer> eindwaarden = new ArrayList<Integer>();
		    eindwaarden.add(4);
		    eindwaarden.add(0);
		    eindwaarden.add(16);
		    eindwaarden.add(2);
		    eindwaarden.add(2);
		    eindwaarden.add(32);
		    eindwaarden.add(2);
		    eindwaarden.add(2);
		    eindwaarden.add(32);
		    eindwaarden.add(16);
		    eindwaarden.add(8);
		    eindwaarden.add(16);
		    eindwaarden.add(4);
		    eindwaarden.add(8);
		    eindwaarden.add(2);
		    eindwaarden.add(2);		    
		    
		    //uitvoeren test
		   tester.swipe(3);
		    
		    System.out.println("Testerwaarden: "+tester.values);
		    System.out.println("eindwaarden: "+eindwaarden);
		    assertEquals("result: ", tester.values, eindwaarden );
	}

}
