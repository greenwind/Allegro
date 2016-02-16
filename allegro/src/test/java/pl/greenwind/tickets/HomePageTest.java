package pl.greenwind.tickets;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePageTest {

	public WebDriver driver;

	public final static Logger LOGGER = LoggerFactory.getLogger(HomePageTest.class);

	@BeforeMethod
	public void beforeMethod() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(HomePage.HOME);
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

	@Test
	public void getSearchItemsCountTest() {
		LocalDate from = LocalDate.of(2016, 10, 1);
		LocalDate to = LocalDate.of(2016, 10, 10);

		for (int i = 0; i < Period.between(from, to).getDays(); ++i) {
			HomeSearchRequest request = HomeSearchRequest.builder()
					.from("Berlin, Niemcy")
					.fromCode("BER")
					.to("Tokyo, Japan")
					.toCode("TYO")
					.dateFrom(from.plusDays(i))
					.dateTo(from.plusDays(i + 1))
					.build();
			SearchPage searchPage = new HomePage(driver).search(request);
			List<Double> prices = searchPage.getPrices();
			Assert.assertNotNull(prices);
			LOGGER.info("request = {}", request);
			LOGGER.info("prices = {}", prices);
			driver.get(HomePage.HOME);
		}

		//LOGGER.info(searchPage.getPage());
	}


}
