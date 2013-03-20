package net.canadensys.api.narwhal.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Tests related to the APIControllerHelper
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

		APIControllerHelper.splitIdAndData(data, dataList, idList);
		assertEquals(dataList.size(), idList.size());
		assertEquals("2010-08-02",dataList.get(1));
		assertNull(idList.get(1));
	}
	
	/**
	 * Test lines with ID
	 */
	@Test
	public void testSplitWithId(){
		String data = "1|2012-10-05\n2\t2010-08-02";
		
		List<String> dataList = new ArrayList<String>();
		List<String> idList = new ArrayList<String>();

		APIControllerHelper.splitIdAndData(data, dataList, idList);
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

		APIControllerHelper.splitIdAndData(data, dataList, idList);
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

		APIControllerHelper.splitIdAndData(data, dataList, idList);
		assertEquals(dataList.size(), idList.size());
		assertEquals(1,dataList.size());
	}
}
