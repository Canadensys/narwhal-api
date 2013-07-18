package net.canadensys.api.narwhal.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.canadensys.api.narwhal.geotools.GMLWriter;
import net.canadensys.api.narwhal.geotools.GeoToolsModelBuilder;
import net.canadensys.api.narwhal.model.CoordinateAPIResponse;
import net.canadensys.api.narwhal.service.APIService;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.geojson.feature.FeatureJSON;
import org.geotools.geojson.geom.GeometryJSON;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

/**
 * Coordinates processing controller.
 * 
 * @author canadensys
 *
 */
@Controller
public class CoordinatesController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoordinatesController.class);
	private static final int JSON_NUMBER_DECIMALS = 7;
	
	private static final SimpleFeatureType FEATURE_TYPE = GeoToolsModelBuilder.createFeatureType();
	private static final GeometryFactory GEOMETRY_FACTORY = JTSFactoryFinder.getGeometryFactory(null);
	
	private final GMLWriter gmlWriter = new GMLWriter();
	
	@Autowired
	private APIService apiService;
	
	@RequestMapping(value="/")
	public String handleLandingPage(){
		return "redirect:/coordinates";
	}
	
	/**
	 * Handles HTML representation of a coordinates processing.
	 * @param data
	 * @param idprovided
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/coordinates"}, method={RequestMethod.GET,RequestMethod.POST})
	public String handleCoordinatesHtml(@RequestParam(required=false) String data, @RequestParam(required=false) Boolean idprovided, ModelMap model,
			HttpServletRequest request){
		
		if(StringUtils.isBlank(data)){
			return "coordinates";
		}
				
		CoordinateAPIResponse apiResponse = generateCoordinateAPIResponse(data,idprovided);
		LOGGER.info("Coordinate|{}|{}|{}", request.getMethod(), request.getRequestURI(), request.getRemoteAddr());
		
		model.addAttribute("data", apiResponse);
		return "coordinates-results";
	}
	
	/**
	 * Handle GML(xml) representation of a coordinates processing.
	 * @param data query string parameters
	 * @param idprovided
	 * @param request
	 * @param response 
	 * @return GML3 xml string
	 */
	@RequestMapping(value="/coordinates.xml", method={RequestMethod.GET,RequestMethod.POST},produces = "application/xml;charset=UTF-8")
	@ResponseBody
	public String handleCoordinatesXml(@RequestParam String data, @RequestParam(required=false) Boolean idprovided, HttpServletRequest request, HttpServletResponse response){
		String returnString;
		//make sure the answer is set as UTF-8
		response.setCharacterEncoding("UTF-8");

		CoordinateAPIResponse apiResponse = generateCoordinateAPIResponse(data,idprovided);
		returnString = gmlWriter.toGML(buildSimpleFeatureCollection(apiResponse));
		
		LOGGER.info("Coordinate|{}|{}|{}", request.getMethod(), request.getRequestURI(), request.getRemoteAddr());
		
		return returnString;
	}
	
	/**
	 * Handle JSONP representation of a coordinates processing.
	 * We are not returning any Object to avoid confusion with any MessageConverter.
	 * MappingJackson2HttpMessageConverter is not supporting JSONP and custom implementation
	 * seems to conflict with ContentNegotiatingViewResolver (we want application/x-javascript and ask it with .json).
	 * @param data
	 * @param idprovided
	 * @param callback
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/coordinates.json", method={RequestMethod.GET},params="callback")
	public void handleJSONP(@RequestParam String data, @RequestParam(required=false) Boolean idprovided, @RequestParam String callback,
			HttpServletRequest request, HttpServletResponse response){
		
		//make sure the answer is set as UTF-8
		response.setCharacterEncoding("UTF-8");
		response.setContentType(APIControllerHelper.JSONP_CONTENT_TYPE);
				
		if(APIControllerHelper.JSONP_ACCEPTED_CHAR_PATTERN.matcher(callback).matches()){

			CoordinateAPIResponse apiResponse = generateCoordinateAPIResponse(data,idprovided);
			StringWriter sw = new StringWriter();
			try {
				GeometryJSON gjson = new GeometryJSON(7);
				org.geotools.geojson.feature.FeatureJSON a = new FeatureJSON(gjson);
				a.writeFeatureCollection(buildSimpleFeatureCollection(apiResponse), sw);

				String responseTxt = callback + "("+sw.toString()+");";
				response.getWriter().print(responseTxt);
				response.setContentLength(responseTxt.length());
				response.getWriter().close();
				
				LOGGER.info("Coordinate(jsonp)|{}|{}|{}", request.getMethod(), request.getRequestURI(), request.getRemoteAddr());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
	
	/**
	 * Handle GeoJSON representation of a coordinates processing.
	 * @param data
	 * @param idprovided
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/coordinates.json", method={RequestMethod.GET,RequestMethod.POST},params="!callback")
	@ResponseBody
	public String handleCoordinatesJson(@RequestParam String data, @RequestParam(required=false) Boolean idprovided, HttpServletRequest request, HttpServletResponse response){
		CoordinateAPIResponse apiResponse;
		String returnString;
		//make sure the answer is set as UTF-8
		response.setCharacterEncoding("UTF-8");
		
		apiResponse = generateCoordinateAPIResponse(data,idprovided);
		
		StringWriter sw = new StringWriter();
		try {
			GeometryJSON gjson = new GeometryJSON(JSON_NUMBER_DECIMALS);
			org.geotools.geojson.feature.FeatureJSON fj = new FeatureJSON(gjson);
			fj.writeFeatureCollection(buildSimpleFeatureCollection(apiResponse), sw);
			
			LOGGER.info("Coordinate|{}|{}|{}", request.getMethod(), request.getRequestURI(), request.getRemoteAddr());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		returnString= sw.toString();
		return returnString;
	}
	
	/**
	 * Generate the appropriate CoordinateAPIResponse according to the provided data and "hint"
	 * @param data
	 * @param idprovided Boolean.TRUE, Boolean.FALSE or null if no specified
	 * @return
	 */
	private CoordinateAPIResponse generateCoordinateAPIResponse(String data, Boolean idprovided){
		List<String> dataList = new ArrayList<String>();
		List<String> idList = new ArrayList<String>();
		
		//check if we have a hint about the id column
		if(BooleanUtils.isTrue(idprovided)){
			APIControllerHelper.splitIdAndData(data, dataList, idList);
			return apiService.processCoordinates(dataList, idList);
		}
		else if(BooleanUtils.isFalse(idprovided)){
			APIControllerHelper.splitData(data, dataList);
			return apiService.processCoordinates(dataList);
		}
		else{ //idprovided == null, we need to guess
			List<String> fallbackList = new ArrayList<String>();
			APIControllerHelper.splitIdAndData(data, dataList, idList,fallbackList);
			return apiService.processCoordinates(dataList, idList,fallbackList);
		}
	}
	
	/**
	 * Creates a geotools SimpleFeatureCollection
	 * @param repList APICoordinateResponse list
	 * @return
	 */
	private SimpleFeatureCollection buildSimpleFeatureCollection(CoordinateAPIResponse repList){
		DefaultFeatureCollection collection = new DefaultFeatureCollection();
		SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(FEATURE_TYPE);
		
		for(CoordinateAPIResponse.CoordinateAPIResponseElement currRep : repList.getProcessedCoordinateList()){
			if(currRep != null && currRep.getDecimalLatitude() != null && currRep.getDecimalLongitude() != null){
				/* Longitude (= x coord) first ! */
		        Point point = GEOMETRY_FACTORY.createPoint(new Coordinate(currRep.getDecimalLongitude(), currRep.getDecimalLatitude()));
		        featureBuilder.add(point);
		        featureBuilder.add(currRep.getOriginalValue());
		        SimpleFeature feature = featureBuilder.buildFeature(currRep.getId());
		        collection.add(feature);
			}
			else{
		        featureBuilder.add(null);
		        featureBuilder.add(currRep.getOriginalValue());
		        featureBuilder.add(currRep.getError());
		        SimpleFeature feature = featureBuilder.buildFeature(currRep.getId());
		        collection.add(feature);
			}
		}
        return collection;     
	}
}
