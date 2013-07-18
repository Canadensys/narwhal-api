package net.canadensys.api.narwhal.service;

import java.util.List;

import net.canadensys.api.narwhal.model.CoordinateAPIResponse;
import net.canadensys.api.narwhal.model.DateAPIResponse;

/**
 * narwhal-api service layer interface
 * @author canadensys
 *
 */
public interface APIService {
	
	/**
	 * Process the given data as a CoordinateAPIResponse
	 * @param rawCoordinates extracted rawCoordinates
	 * @param id extracted (optional) id
	 * @param fallbackList raw line in case the id/data extraction was misinterpreted due to shared separator and optional id.
	 * @return
	 */
	public CoordinateAPIResponse processCoordinates(List<String> rawCoordinates, List<String> id, List<String> fallbackList);
	
	/**
	 * Process the given data as a CoordinateAPIResponse when at least one id is provided
	 * @param rawCoordinates extracted rawCoordinates
	 * @param id extracted id
	 * @return
	 */
	public CoordinateAPIResponse processCoordinates(List<String> rawCoordinates, List<String> id);
	
	/**
	 * Process the given data as a CoordinateAPIResponse when NO id are provided
	 * @param rawCoordinates extracted rawCoordinates
	 * @return
	 */
	public CoordinateAPIResponse processCoordinates(List<String> rawCoordinates);
	
	public DateAPIResponse processDates(List<String> rawDates, List<String> id);
}
