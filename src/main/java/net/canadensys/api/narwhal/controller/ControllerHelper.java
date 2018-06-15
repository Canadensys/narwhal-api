package net.canadensys.api.narwhal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import net.canadensys.api.narwhal.config.NarwhalConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

/**
 * Candidate for future canadensys-web-core library
 * Helper class used to for different API controllers.
 * @author canadensys
 *
 */
class ControllerHelper {
	
	static final Pattern JSONP_ACCEPTED_CHAR_PATTERN = Pattern.compile("[\\w\\.]+");
	static final String JSONP_CONTENT_TYPE = "application/x-javascript";
	
	//separator used for lines
	static final String LINE_SEPARATOR = "[\r\n]+";
	//optional separator used between id and data
	static final String DATA_SEPARATOR = "[\t|]+";

	/**
	 * No constructor, utility class
	 */
	private ControllerHelper() {}

	static Map<String,Object> createPageModel(HttpServletRequest request) {
		Map<String,Object> pageModel = new HashMap<>();
		addLanguagesUrl(request, pageModel);
		return pageModel;
	}

	/**
	 * Add to the provided model a map including the URL to the provided resource in the other
	 * available language(s).
	 *
	 * @param request
	 * @param model
	 */
	static void addLanguagesUrl(HttpServletRequest request, Map<String,Object> model){
		Locale locale = RequestContextUtils.getLocale(request);
		String currLanguage = locale.getLanguage();
		// add current language
		model.put("language", currLanguage);
		model.put("languageUrl", buildLanguageUri(request, currLanguage));

		// add other language(s)
		Map<String,String> languagePathMap = new HashMap<>();
		for(String currSupportedLang : NarwhalConfiguration.SUPPORTED_LANGUAGES){
			if(!currSupportedLang.equalsIgnoreCase(currLanguage)){
				languagePathMap.put(currSupportedLang, buildLanguageUri(request, currSupportedLang));
			}
		}
		model.put("otherLanguage", languagePathMap);
	}

	private static String buildLanguageUri(HttpServletRequest request, String lang){
		UriComponentsBuilder bldr = ServletUriComponentsBuilder.fromRequest(request);
		bldr.replaceQueryParam(NarwhalConfiguration.LANG_PARAM, lang);
		return bldr.build().toUriString();
	}
	
	/**
	 * splitIdAndData without fallback list if the provided data can NOT use a DATA_SEPARATOR inside the data part or a id column id used. 
	 * @param data
	 * @param dataList
	 * @param idList
	 */
	public static void splitIdAndData(String data, List<String> dataList, List<String> idList){
		splitIdAndData(data,dataList,idList,null);
	}
	
	/**
	 * splitData when NO id column is used
	 * @param data
	 * @param dataList
	 */
	public static void splitData(String data, List<String> dataList){
		String[] lines = data.split(ControllerHelper.LINE_SEPARATOR);
		for(String currLine : lines){
			dataList.add(currLine);
		}
	}
	
	/**
	 * This function splits the parts of the query data parameter.
	 * Id is an optional part and the DATA_SEPARATOR might be used in the data part itself.
	 * @param lines
	 * @param dataList
	 * @param idList can contain null item id no id is provided
	 * @param fallbackList can contain null item if no DATA_SEPARATOR is found. The purpose of the fallbackList is to provide the raw line in case the DATA_SEPARATOR is an accepted value for the data part and
	 * no id was provided. In that case, a part of the data could have been mistakenly split as an id. It is the responsibility of the caller to determine what to do with the split data.
	 */
	public static void splitIdAndData(String data, List<String> dataList, List<String> idList, List<String> fallbackList){
		String[] lines = data.split(ControllerHelper.LINE_SEPARATOR);
		
		for(String currLine : lines){
			String[] dataParts = currLine.split(DATA_SEPARATOR,2);
			//if we only find one element
			if(dataParts.length == 1){
				idList.add(null);
				if(fallbackList!=null){
					//add null since the dataList already contains the entire string
					fallbackList.add(null);
				}
				dataList.add(dataParts[0].trim());
			}//if we find more than one, we use the first as ID, second as data. If there is more, it will be ignored.
			else if(dataParts.length > 1){
				idList.add(dataParts[0].trim());
				dataList.add(dataParts[1].trim());
				if(fallbackList!=null){
					fallbackList.add(currLine);
				}
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
			if(StringUtils.isNotBlank(str)){
				return true;
			}
		}
		return false;
	}
}
