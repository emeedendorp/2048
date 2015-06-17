package _2048;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestFailure {

	@Test
	public void test() {
	    // sorteerRechts is tested
	    View tester = new View();
	    
	    //testArrays
	    ArrayList<Integer> array1 = new ArrayList<Integer>();
	    array1.add(32);
	    array1.add(16);
	    array1.add(4);
	    array1.add(4);
	    
	    //verwacht resultaat
	    ArrayList<Integer> array2 = new ArrayList<Integer>();
	    array2.add(0);
	    array2.add(32);
	    array2.add(16);
	    array2.add(8);

	    System.out.println("array1: " + array1);
	    System.out.println("array2: " + array2);
	    System.out.println("sorteerRechts(array1): " + tester.sorteerRechts(array1));
	    
	    // assert statements
	    assertEquals("2 2 4 0 --> 0 0 4 4", array2, tester.sorteerRechts(array1));
	    
	    // checkFull is tested
	    ArrayList<Integer> waarden = new ArrayList<Integer>();
	    waarden.add(2);
	    waarden.add(16);
	    waarden.add(2);
	    waarden.add(4);
	    waarden.add(64);
	    waarden.add(4);
	    waarden.add(16);
	    waarden.add(2);
	    waarden.add(2);
	    waarden.add(16);
	    waarden.add(2);
	    waarden.add(8);
	    waarden.add(32);
	    waarden.add(8);
	    waarden.add(64);
	    waarden.add(4);
	    waarden.add(2);
	    waarden.add(256);
	    waarden.add(8);
	    waarden.add(2);
	    
	    tester.waarden = waarden;
	    
	    assertTrue(tester.checkFull());
	    
	}

}
