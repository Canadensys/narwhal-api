package net.canadensys.api.narwhal;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Integration tests of the rendered pages.
 * @author canadensys
 *
 */
public class APIWebPageIntegrationTest {
	//TODO : move to a test config file
	private static final String TESTING_SERVER_URL = "http://localhost:9966/tools/";
	
	private WebDriver browser;
	 
	@Before
	public void setup() {
		browser = new FirefoxDriver();
	}
	 
	@Test
	public void testCoordinatesPage() {
		//Coordinates is the landing page
		browser.get(TESTING_SERVER_URL);
		
		browser.findElement(By.name("data")).sendKeys("2 | 40°26′47″N,74° 0' 21.5022\"W");
		browser.findElement(By.tagName("input")).click();
		
		WebElement resultTable = browser.findElement(By.className("narwhal-results"));
		
		assertEquals("40.4463889", resultTable.findElement(By.cssSelector("tbody td:nth-child(3)")).getText());
		assertEquals("-74.0059728", resultTable.findElement(By.cssSelector("tbody td:nth-child(4)")).getText());
	}
	
	@Test
	public void testDatesPage() {
		browser.get(TESTING_SERVER_URL + "dates");
		
		browser.findElement(By.name("data")).sendKeys("2 VII 1986");
		browser.findElement(By.tagName("input")).click();
		
		WebElement resultTable = browser.findElement(By.className("narwhal-results"));
		
		assertEquals("1986", resultTable.findElement(By.cssSelector("tbody td:nth-child(2)")).getText());
		assertEquals("7", resultTable.findElement(By.cssSelector("tbody td:nth-child(3)")).getText());
		assertEquals("2", resultTable.findElement(By.cssSelector("tbody td:nth-child(4)")).getText());
		assertEquals("1986-07-02", resultTable.findElement(By.cssSelector("tbody td:nth-child(5)")).getText());
	}
	 
	@After
	public void tearDown() {
		browser.close();
	}

}
