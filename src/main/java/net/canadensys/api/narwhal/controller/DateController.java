package net.canadensys.api.narwhal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import net.canadensys.api.narwhal.model.DateAPIResponse;
import net.canadensys.api.narwhal.service.APIService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Controller handling dates processing requests.
 * @author canadensys
 *
 */
@Controller
public class DateController {
	
	public static final ObjectMapper JACKSON_MAPPER = new ObjectMapper();
	private static final Pattern LINE_ID_VALUE = Pattern.compile(".+[\t|].+");
		
	@Autowired
	private APIService apiService;
	
	/**
	 * Handle dates parsing for JSONP
	 * @param data
	 * @param callback
	 * @param response
	 * @return JSONP response
	 */
	@RequestMapping(value={"/dates.json"}, method=RequestMethod.GET, params="callback")
	public ResponseEntity<String> handleJSONP(@RequestParam String data, @RequestParam String callback,
			HttpServletResponse response){
		
		response.setContentType(APIControllerHelper.JSONP_CONTENT_TYPE);
		
		if(APIControllerHelper.JSONP_ACCEPTED_CHAR_PATTERN.matcher(callback).matches()){
			//JSONP handling
			String json = "";
			DateAPIResponse apiResponse = null;
			List<String> dataList = new ArrayList<String>();
			List<String> idList = new ArrayList<String>();

			APIControllerHelper.splitIdAndData(data, dataList, idList);
			apiResponse = apiService.processDates(dataList, idList);
			for(String str : idList){
				if(!StringUtils.isBlank(str)){
					apiResponse.setIdProvided(true);
				}
			}
			
			try {
				json = JACKSON_MAPPER.writeValueAsString(apiResponse);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return new ResponseEntity<String>(callback +"("+json+");", HttpStatus.OK);
		}
		return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Handle dates parsing for all requests (except JSONP)
	 * @param data
	 * @param callback
	 * @param model
	 * @param response
	 * @return view name
	 */
	@RequestMapping(value={"/dates"}, method={RequestMethod.GET,RequestMethod.POST}, params="!callback")
	public String handleDatesParsing(@RequestParam(required=false) String data,
			@RequestParam(required=false)String callback,
			ModelMap model, HttpServletResponse response){
		
		if(StringUtils.isBlank(data)){
			return "dates";
		}
		
		DateAPIResponse apiResponse = null;
		//make sure the answer is set as UTF-8
		//response.setCharacterEncoding("UTF-8");

		List<String> dataList = new ArrayList<String>();
		List<String> idList = new ArrayList<String>();
		
		APIControllerHelper.splitIdAndData(data, dataList, idList);
		
		apiResponse = apiService.processDates(dataList, idList);
		//check if at least one line is providing an id
		for(String str : idList){
			if(!StringUtils.isBlank(str)){
				apiResponse.setIdProvided(true);
			}
		}
		model.addAttribute("data", apiResponse);
		return "dates-results";
	}
}
