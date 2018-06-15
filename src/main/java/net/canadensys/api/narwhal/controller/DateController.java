package net.canadensys.api.narwhal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.canadensys.api.narwhal.config.NarwhalConfiguration;
import net.canadensys.api.narwhal.model.DateAPIResponse;
import net.canadensys.api.narwhal.service.APIService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DateController.class);
	
	//Own Jackson Object Mapper to add JSONP support
	public static final ObjectMapper JACKSON_MAPPER = new ObjectMapper();
		
	@Autowired
	private APIService apiService;
	
	/**
	 * Response to HEAD request with a HTTP_OK and no content, as defined by the standard.
	 * @param response
	 */
    @RequestMapping(value="/dates", method=RequestMethod.HEAD)
    public void healthCheckHead(HttpServletResponse response) {
        response.setContentLength(0);
        response.setStatus(HttpServletResponse.SC_OK);
    }
	
	/**
	 * Handle dates parsing for JSONP
	 * @param data
	 * @param callback
	 * @param response
	 * @return JSONP response
	 */
	@RequestMapping(value={"/dates.json"}, method=RequestMethod.GET, params="callback")
	public void handleJSONP(@RequestParam String data, @RequestParam String callback,
			 HttpServletRequest request, HttpServletResponse response){
		
		//make sure the answer is set as UTF-8
		response.setCharacterEncoding("UTF-8");
		response.setContentType(ControllerHelper.JSONP_CONTENT_TYPE);
		
		if(ControllerHelper.JSONP_ACCEPTED_CHAR_PATTERN.matcher(callback).matches()){
			//JSONP handling
			String json = "";
			DateAPIResponse apiResponse = null;
			List<String> dataList = new ArrayList<String>();
			List<String> idList = new ArrayList<String>();

			ControllerHelper.splitIdAndData(data, dataList, idList);
			apiResponse = apiService.processDates(dataList, idList);
			
			try {
				json = JACKSON_MAPPER.writeValueAsString(apiResponse);
				
				String responseTxt = callback + "("+json+");";
				response.getWriter().print(responseTxt);
				response.setContentLength(responseTxt.length());
				response.getWriter().close();
				
				LOGGER.info("Date(jsonp)|{}|{}|{}", request.getMethod(), request.getRequestURI(), request.getRemoteAddr());
				
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
	 * @param model
	 * @param request
	 * @param response
	 * @return view name
	 */
	@RequestMapping(value={"/dates"}, method={RequestMethod.GET,RequestMethod.POST}, params="!callback")
	public String handleDatesParsing(@RequestParam(required=false) String data,
			ModelMap model, HttpServletRequest request, HttpServletResponse response){

		// only include the page model for freemarker
		if(!StringUtils.endsWith(request.getRequestURI(), ".json") &&
				!StringUtils.endsWith(request.getRequestURI(), ".xml")) {
			model.addAttribute(NarwhalConfiguration.PAGE_ROOT_MODEL_KEY, ControllerHelper.createPageModel(request));
		}

		if(StringUtils.isBlank(data)){
			return "dates";
		}
		
		DateAPIResponse apiResponse = null;
		
		response.setCharacterEncoding("UTF-8");

		List<String> dataList = new ArrayList<>();
		List<String> idList = new ArrayList<>();
		
		ControllerHelper.splitIdAndData(data, dataList, idList);
		
		apiResponse = apiService.processDates(dataList, idList);
		apiResponse.setIdProvided(ControllerHelper.containsAtLeastOneNonBlank(idList));
		
		LOGGER.info("Date|{}|{}|{}", request.getMethod(), request.getRequestURI(), request.getRemoteAddr());
		model.addAttribute("data", apiResponse);
		return "dates-results";
	}
}
