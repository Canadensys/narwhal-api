package net.canadensys.api.narwhal.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * API Response object for Date processing.
 * @author canadensys
 */
@JsonInclude(Include.NON_NULL)
@XmlRootElement(name="results")
public class DateAPIResponse {
	
	@JsonProperty(value="results")
	@XmlElement(name="result")
	private List<DateAPIResponseElement> processedDateList;
	
	@JsonIgnore
	private boolean idProvided = false;
	
	public DateAPIResponse(){
		processedDateList = new ArrayList<DateAPIResponse.DateAPIResponseElement>();
	}
	
	/**
	 * Add a new processed date to the result object
	 * @param originalValue
	 * @param y year
	 * @param m month
	 * @param d day
	 * @param error
	 */
	public void addProcessedDate(String originalValue, Integer y, Integer m, Integer d, String error){
		addProcessedDate(null,originalValue, y, m, d, error);
	}
	
	/**
	 * Add a new processed date(with id) to the result object
	 * @param id
	 * @param originalValue
	 * @param y year
	 * @param m month
	 * @param d day
	 * @param error
	 */
	public void addProcessedDate(String id, String originalValue, Integer y, Integer m, Integer d, String error){
		DateAPIResponseElement dateResponseElement = new DateAPIResponseElement();
		dateResponseElement.setId(id);
		dateResponseElement.setOriginalValue(originalValue);
		dateResponseElement.setYear(y);
		dateResponseElement.setMonth(m);
		dateResponseElement.setDay(d);
		if(!StringUtils.isBlank(error)){
			dateResponseElement.setError(error);
		}
		dateResponseElement.setPartial((y == null || m == null || d == null));
		//Compute the ISO date (including partial date)
		if(y != null){
			//not fancy, but easy to understand
			String isoDate = y.toString();
			if(m != null){
				isoDate += "-" + m.toString();
				if(d != null){
					isoDate += "-" + d.toString();
				}
			}
			dateResponseElement.setIsoDate(isoDate);
		}
		
		processedDateList.add(dateResponseElement);
	}
	
	public List<DateAPIResponseElement> getProcessedDateList(){
		return processedDateList;
	}
	
	@XmlTransient
	public boolean isIdProvided() {
		return idProvided;
	}
	public void setIdProvided(boolean idProvided) {
		this.idProvided = idProvided;
	}
	
	/**
	 * Public inner class to ease serialization.
	 * Need to be public to allow Java Bean usage.
	 * @author canadensys
	 *
	 */
	@JsonInclude(Include.NON_NULL)
	public static class DateAPIResponseElement{
		private String id;
		private String originalValue;
		private Integer year;
		private Integer month;
		private Integer day;
		
		private boolean isPartial;
		private String isoDate;
		private String error;
		
		public Integer getYear() {
			return year;
		}

		public void setYear(Integer year) {
			this.year = year;
		}
		public Integer getMonth() {
			return month;
		}
		public void setMonth(Integer month) {
			this.month = month;
		}
		public Integer getDay() {
			return day;
		}
		public void setDay(Integer day) {
			this.day = day;
		}
		public boolean isPartial() {
			return isPartial;
		}
		public void setPartial(boolean isPartial) {
			this.isPartial = isPartial;
		}
		public String getIsoDate() {
			return isoDate;
		}
		public void setIsoDate(String isoDate) {
			this.isoDate = isoDate;
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


