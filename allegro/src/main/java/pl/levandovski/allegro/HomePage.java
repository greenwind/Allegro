package pl.levandovski.allegro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
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
		WebElement submitButton = driver.findElement(By.xpath(".//*[@id='main-search']/input"));
		searchField.sendKeys(searchString);
		submitButton.click();
		
		new WebDriverWait(driver, 10, 500).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				LOGGER.info("Waiting for search result page...");
				return driver.findElement(By.id("sidebar-params")) != null;
			}
		});
		
		return new SearchPage(driver);
	}
}