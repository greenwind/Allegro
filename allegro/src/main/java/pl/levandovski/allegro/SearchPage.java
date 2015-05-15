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
	
	public final static Logger LOGGER = LoggerFactory.getLogger(SearchPage.class);
	
	private WebDriver driver;
	
	public SearchPage(WebDriver driver) {
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
		return this;
	}
	
	/**
     * Get items count
     * @return the resulting long 
     */
	public Long getSearchItemsCount() {
		WebElement searchItemCountElement = driver.findElement(By.id("main-breadcrumb-search-hits"));
		String itemsCountString = searchItemCountElement.getText().replaceAll( "[^\\d]", "");
		Long itemsCount = Long.parseLong(itemsCountString);
		LOGGER.info("All items: {}", itemsCount);
		return itemsCount;
	}
	
	/**
     * Get new items count
     * @return the resulting long 
     */
	public Long getSearchNewItemsCount() {
		WebElement newItemCountElement = driver.findElement(By.xpath(".//*[@id='display-form']/fieldset[1]/ul/li[1]/label/a/span/span"));
		String newItemsCountString = newItemCountElement.getText().replaceAll( "[^\\d]", "");
		Long newItemsCount = Long.parseLong(newItemsCountString);
		LOGGER.info("New items: {}", newItemsCount);
		return newItemsCount;
	}
	
	/**
     * Get used items count
     * @return the resulting long 
     */
	public Long getSearchUsedItemsCount() {
		WebElement usedItemCountElement = driver.findElement(By.xpath(".//*[@id='display-form']/fieldset[1]/ul/li[2]/label/a/span/span"));
		String usedItemCountString = usedItemCountElement.getText().replaceAll( "[^\\d]", "");
		Long usedItemCount = Long.parseLong(usedItemCountString);
		LOGGER.info("Used items: {}", usedItemCount);
		return usedItemCount;
	}
}
