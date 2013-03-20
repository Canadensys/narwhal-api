package net.canadensys.api.narwhal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.canadensys.api.narwhal.model.DateAPIResponse;
import net.canadensys.api.narwhal.service.APIService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	//Own Jackson Object Mapper to add JSONP support
	public static final ObjectMapper JACKSON_MAPPER = new ObjectMapper();
		
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
	public void handleJSONP(@RequestParam String data, @RequestParam String callback,
			HttpServletResponse response){
		
		//make sure the answer is set as UTF-8
		response.setCharacterEncoding("UTF-8");
		response.setContentType(APIControllerHelper.JSONP_CONTENT_TYPE);
		
		if(APIControllerHelper.JSONP_ACCEPTED_CHAR_PATTERN.matcher(callback).matches()){
			//JSONP handling
			String json = "";
			DateAPIResponse apiResponse = null;
			List<String> dataList = new ArrayList<String>();
			List<String> idList = new ArrayList<String>();

			APIControllerHelper.splitIdAndData(data, dataList, idList);
			apiResponse = apiService.processDates(dataList, idList);
			
			try {
				json = JACKSON_MAPPER.writeValueAsString(apiResponse);
				
				String responseTxt = callback + "("+json+");";
				response.getWriter().print(responseTxt);
				response.setContentLength(responseTxt.length());
				response.getWriter().close();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
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

		List<String> dataList = new ArrayList<String>();
		List<String> idList = new ArrayList<String>();
		
		APIControllerHelper.splitIdAndData(data, dataList, idList);
		
		apiResponse = apiService.processDates(dataList, idList);
		apiResponse.setIdProvided(APIControllerHelper.containsAtLeastOneNonBlank(idList));
		
		model.addAttribute("data", apiResponse);
		return "dates-results";
	}
}
