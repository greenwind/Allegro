package pl.levandovski.allegro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class represents page with search results
 * 
 * @author Marek Lewandowski
 *
 */
public class SearchPage {
	private WebDriver driver;
	public SearchPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public final static Logger LOGGER = LoggerFactory.getLogger(SearchPage.class);
	
	public SearchPage searchByName(String searchString) {
		WebElement searchField = driver.findElement(By.id("main-search-text"));
		WebElement submitButton = driver.findElement(By.className("sprite search-btn"));
		searchField.sendKeys(searchString);
		submitButton.click();
		return this;
	}
}
