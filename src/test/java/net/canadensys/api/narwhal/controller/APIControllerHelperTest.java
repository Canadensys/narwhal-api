package net.canadensys.api.narwhal.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Tests related to the ControllerHelper
 * @author canadensys
 *
 */
public class APIControllerHelperTest {
	
	/**
	 * Test lines without ID
	 */
	@Test
	public void testSplitWithoutId(){
		String data = "2012-10-05\n2010-08-02";
		
		List<String> dataList = new ArrayList<String>();
		List<String> idList = new ArrayList<String>();

		ControllerHelper.splitIdAndData(data, dataList, idList);
		assertEquals(dataList.size(), idList.size());
		assertEquals("2010-08-02",dataList.get(1));
		assertNull(idList.get(1));
	}
	
	/**
	 * Test line using tab(\t) in the data part
	 */
	@Test
	public void testSplitWithSharedSeparator(){
		String data = "1\t2:3:4N\t5:6:7W";
		
		List<String> dataList = new ArrayList<String>();
		List<String> idList = new ArrayList<String>();
		List<String> fallbackList = new ArrayList<String>();

		ControllerHelper.splitIdAndData(data, dataList, idList);
		assertEquals(dataList.size(), idList.size());
		assertEquals("2:3:4N\t5:6:7W",dataList.get(0));
		
		//Here 2:3:4N will be interpreted as the id.
		data = "2:3:4N\t5:6:7W";
		dataList.clear();
		idList.clear();
		ControllerHelper.splitIdAndData(data, dataList, idList, fallbackList);
		//Validate that 2:3:4N is used as an id (I know it doesn't make sense but that's the expected behavior, the caller will use the fallbackList since he is the only one that knows it doesn't make sense)
		assertEquals("2:3:4N",idList.get(0));
		assertEquals("5:6:7W",dataList.get(0));
		
		assertEquals("2:3:4N\t5:6:7W",fallbackList.get(0));
	}

	
	/**
	 * Test lines with ID
	 */
	@Test
	public void testSplitWithId(){
		String data = "1|2012-10-05\n2\t2010-08-02";
		
		List<String> dataList = new ArrayList<String>();
		List<String> idList = new ArrayList<String>();

		ControllerHelper.splitIdAndData(data, dataList, idList);
		assertEquals(dataList.size(), idList.size());
		assertEquals("2010-08-02",dataList.get(1));
		assertEquals("2",idList.get(1));
	}
	
	/**
	 * Test that we can mix lines with ID and lines without ID
	 */
	@Test
	public void testSplitWithAndWithoutId(){
		String data = "1|2012-10-05\n2010-08-02";
		
		List<String> dataList = new ArrayList<String>();
		List<String> idList = new ArrayList<String>();

		ControllerHelper.splitIdAndData(data, dataList, idList);
		assertEquals(dataList.size(), idList.size());
		assertEquals("2010-08-02",dataList.get(1));
		assertEquals("1",idList.get(0));
		assertNull(idList.get(1));
	}
	
	/**
	 * Test behavior with empty data String
	 */
	@Test
	public void testEmpty(){
		String data = "";
		
		List<String> dataList = new ArrayList<String>();
		List<String> idList = new ArrayList<String>();

		ControllerHelper.splitIdAndData(data, dataList, idList);
		assertEquals(dataList.size(), idList.size());
		assertEquals(1,dataList.size());
	}
}
