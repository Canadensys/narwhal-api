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
	
	public CoordinateAPIResponse processCoordinates(List<String> rawCoordinates, List<String> id);
	public DateAPIResponse processDates(List<String> rawDates, List<String> id);
}
