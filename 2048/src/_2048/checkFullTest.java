package _2048;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class checkFullTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCheckFull() {
		
		View test = new View();
		
		ArrayList<Integer> waarden = new ArrayList<Integer>();
		waarden.add(4);
		waarden.add(2);
		waarden.add(4);
		
		waarden.add(16);
		waarden.add(32);
		waarden.add(2);
		
		waarden.add(64);
		waarden.add(2);
		waarden.add(0);
		test.waarden=waarden;
				
		assertFalse( test.checkFull());
	}

}
