package _2048;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestFailure {

	@Test
	public void test() {
	    // sortRight is tested
	    View tester = new View();
	    
	    //testArrays
	    ArrayList<Integer> array1 = new ArrayList<Integer>();
	    array1.add(32);
	    array1.add(16);
	    array1.add(4);
	    array1.add(4);
	    
	    //expected result
	    ArrayList<Integer> array2 = new ArrayList<Integer>();
	    array2.add(0);
	    array2.add(32);
	    array2.add(16);
	    array2.add(8);

	    System.out.println("array1: " + array1);
	    System.out.println("array2: " + array2);
	    System.out.println("sortRight(array1): " + tester.sortRight(array1));
	    
	    // assert statements
	    assertEquals("2 2 4 0 --> 0 0 4 4", array2, tester.sortRight(array1));
	    
	    // checkFull is tested
	    ArrayList<Integer> values = new ArrayList<Integer>();
	    values.add(2);
	    values.add(16);
	    values.add(2);
	    values.add(4);
	    values.add(64);
	    values.add(4);
	    values.add(16);
	    values.add(2);
	    values.add(2);
	    values.add(16);
	    values.add(2);
	    values.add(8);
	    values.add(32);
	    values.add(8);
	    values.add(64);
	    values.add(4);
	    values.add(2);
	    values.add(256);
	    values.add(8);
	    values.add(2);
	    
	    tester.values = values;
	    
	    assertTrue(tester.checkFull());
	    
	}

}
