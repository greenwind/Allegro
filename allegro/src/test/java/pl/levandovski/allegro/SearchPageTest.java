package pl.levandovski.allegro;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pl.levandovski.allegro.SearchPageTest;
import pl.levandovski.allegro.HomePage;
import pl.levandovski.allegro.SearchPage;

public class SearchPageTest {
	public WebDriver driver;
	
	public final static Logger LOGGER = LoggerFactory.getLogger(SearchPageTest.class);
	
	@BeforeMethod
	public void beforeMethod() {
		driver = new FirefoxDriver();
		driver.get("http://allegro.pl");
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
	
	@Test
	public void getSearchItemsCountTest() {
		SearchPage searchPage = new HomePage(driver).searchByName("wrotki");
		Long searchItemsCount = searchPage.getSearchItemsCount();
		Assert.assertNotNull(searchItemsCount);
		// how to check it?
		// Assert.assertEquals(searchItemsCount.longValue(), 14L);
	}
	
	@Test
	public void getSearchItemsCountNegativeTest() {
		SearchPage searchPage = new HomePage(driver).searchByName("not_existing_item");
		Long searchItemsCount = searchPage.getSearchItemsCount();
		Assert.assertNotNull(searchItemsCount);
		Assert.assertEquals(searchItemsCount.longValue(), 0L);
	}
}
