package net.canadensys.api.narwhal.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * API Response object for coordinates processing.
 * @author canadensys
 */
public class CoordinateAPIResponse {
	
	private List<CoordinateAPIResponseElement> processedCoordinateList;
	
	private boolean idProvided = false;
	
	public CoordinateAPIResponse(){
		processedCoordinateList = new ArrayList<CoordinateAPIResponseElement>();
	}
	
	/**
	 * Add a new processed coordinate to the result object
	 * @param id
	 * @param originalValue
	 * @param decimalLatitude
	 * @param decimalLongitude
	 * @param error
	 */
	public void addProcessedCoordinate(String id, String originalValue, Double decimalLatitude, Double decimalLongitude, String error){
		CoordinateAPIResponseElement dateResponseElement = new CoordinateAPIResponseElement();
		dateResponseElement.setId(id);
		dateResponseElement.setOriginalValue(originalValue);
		dateResponseElement.setDecimalLatitude(decimalLatitude);
		dateResponseElement.setDecimalLongitude(decimalLongitude);

		if(!StringUtils.isBlank(error)){
			dateResponseElement.setError(error);
		}
		processedCoordinateList.add(dateResponseElement);
	}
	
	public List<CoordinateAPIResponseElement> getProcessedCoordinateList(){
		return processedCoordinateList;
	}
	
	public boolean isIdProvided() {
		return idProvided;
	}
	public void setIdProvided(boolean idProvided) {
		this.idProvided = idProvided;
	}
	
	public static class CoordinateAPIResponseElement{
		private String originalValue;
		
		private String id;
		
		private Double decimalLatitude;
		private Double decimalLongitude;
		
		private String error;


		public Double getDecimalLatitude() {
			return decimalLatitude;
		}
		public void setDecimalLatitude(Double decimalLatitude) {
			this.decimalLatitude = decimalLatitude;
		}

		public Double getDecimalLongitude() {
			return decimalLongitude;
		}
		public void setDecimalLongitude(Double decimalLongitude) {
			this.decimalLongitude = decimalLongitude;
		}

		public String getOriginalValue() {
			return originalValue;
		}
		public void setOriginalValue(String originalValue) {
			this.originalValue = originalValue;
		}

		public String getError() {
			return error;
		}
		public void setError(String error) {
			this.error = error;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
	}
}
