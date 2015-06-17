package _2048;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class swipeTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSwipe() {

		    View tester = new View();
		    
		    //testen swipe(3)
		    
		    // invoer genereren
		    ArrayList<Integer> waarden = new ArrayList<Integer>();
		    waarden.add(4);
		    waarden.add(32);
		    waarden.add(16);
		    waarden.add(2);
		    waarden.add(2);
		    waarden.add(16);
		    waarden.add(2);
		    waarden.add(16);
		    waarden.add(32);
		    waarden.add(4);
		    waarden.add(8);
		    waarden.add(2);
		    waarden.add(4);
		    waarden.add(4);
		    waarden.add(2);
		    waarden.add(0);
		    
		    System.out.println(waarden);
		    tester.settings.waarden = waarden;
		    
		    
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
		    
		    System.out.println("Testerwaarden: "+tester.settings.waarden);
		    System.out.println("eindwaarden: "+eindwaarden);
		    assertEquals("result: ", tester.settings.waarden, eindwaarden );
	}

}
