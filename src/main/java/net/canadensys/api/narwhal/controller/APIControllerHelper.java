package net.canadensys.api.narwhal.controller;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * Helper class used to for different API controllers.
 * @author canadensys
 *
 */
public class APIControllerHelper {
	
	public static final Pattern JSONP_ACCEPTED_CHAR_PATTERN = Pattern.compile("[\\w\\.]+");
	public static final String JSONP_CONTENT_TYPE = "application/x-javascript";
	
	public static final String LINE_SEPARATOR = "[\r\n]+";
	public static final String DATA_SEPARATOR = "[\t|]+";
	
	/**
	 * This function splits the parts of the query data parameter.
	 * @param lines
	 * @param dataList
	 * @param idList can contain null item id no id is provided
	 */
	public static void splitIdAndData(String data, List<String> dataList, List<String> idList){
		String[] lines = data.split(APIControllerHelper.LINE_SEPARATOR);
		
		for(String currLine : lines){
			String[] dataParts = currLine.split(DATA_SEPARATOR);
			if(dataParts.length == 1){
				idList.add(null);
				dataList.add(dataParts[0].trim());
			}
			else if(dataParts.length > 1){
				idList.add(dataParts[0].trim());
				dataList.add(dataParts[1].trim());
			}
			else{
				System.out.println("Incorrect dataParts size " + dataParts.length);
			}
		}
	}
	
	/**
	 * Check that a list allowing null and empty item contains at least one element that is
	 * not blank.
	 * @param list
	 * @return
	 */
	public static boolean containsAtLeastOneNonBlank(List<String> list){
		for(String str : list){
			if(!StringUtils.isBlank(str)){
				return true;
			}
		}
		return false;
	}
}
