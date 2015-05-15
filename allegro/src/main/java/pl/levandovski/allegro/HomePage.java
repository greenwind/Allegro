package pl.levandovski.allegro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class represents Allegro home page
 * 
 * @author Marek Lewandowski
 *
 */
public class HomePage {
	
	public final static Logger LOGGER = LoggerFactory.getLogger(HomePage.class);
	
	private WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
     * Search for item
     * @param searchString - name of the item
     * @return SearchPage
     */
	public SearchPage searchByName(String searchString) {
		WebElement searchField = driver.findElement(By.id("main-search-text"));
		WebElement submitButton = driver.findElement(By.className("sprite search-btn"));
		searchField.sendKeys(searchString);
		submitButton.click();
		return new SearchPage(driver);
	}
}