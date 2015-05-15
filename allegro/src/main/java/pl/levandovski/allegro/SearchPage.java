package pl.levandovski.allegro;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
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
		WebElement submitButton = driver.findElement(By.xpath(".//*[@id='main-search']/input"));
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
	
	/**
     * Get items count from first page
     * @return the resulting int 
     */
	public int getSearchItemsFirstPageCount() {
		WebElement featuredOffers = null;
		try {
			featuredOffers = driver.findElement(By.id("featured-offers"));
		} catch (NoSuchElementException e) {
			LOGGER.warn("Element featuredOffers not found!");
		}
		
		if(featuredOffers == null) {
			return 0;
		}
		List<WebElement> searchItemsFirstPageCountElement = featuredOffers.findElements(By.className("excerpt"));
		LOGGER.info("Items on first page: {}", searchItemsFirstPageCountElement.size());
		return searchItemsFirstPageCountElement.size();
	}
	
	/**
     * Filter items by province
     * @param provinceEnum - province
     * @return SearchPage 
     */
	public SearchPage filterByProvince(ProvinceEnum provinceEnum) {
		Select dropdown = new Select(driver.findElement(By.id("state")));
		dropdown.selectByVisibleText(provinceEnum.getValue());
		LOGGER.info("Selected province: {}", provinceEnum);
		return this;
	}
	
	/**
     * Get item URLs
     * @return List of URL strings 
     */
	public List<String> getSearchItemsUrls() {
		List<String> itemsUrls = new ArrayList<String>();
		WebElement featuredOffers = null;
		try {
			featuredOffers = driver.findElement(By.id("featured-offers"));
		} catch (NoSuchElementException e) {
			LOGGER.warn("Element featuredOffers not found!");
		}
		
		if(featuredOffers == null) {
			return new ArrayList<String>(0);
		}
		
		List<WebElement> searchItemsElements = featuredOffers.findElements(By.className("excerpt"));
		for (WebElement webElement : searchItemsElements) {
			itemsUrls.add(webElement.findElement(By.tagName("a")).getAttribute("href"));
		}
		
		return itemsUrls;
	}
}
