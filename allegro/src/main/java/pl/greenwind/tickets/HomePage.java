package pl.greenwind.tickets;

import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class represents tickets.pl home page
 * 
 * @author Piotr Janik
 *
 */
public class HomePage {
	
	public final static Logger LOGGER = LoggerFactory.getLogger(HomePage.class);

	public final static String HOME = "https://tickets.pl/";

	private WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
     * Search for item
     * @param req - request
     * @return SearchPage
     */
	public SearchPage search(HomeSearchRequest req) {
		WebElement from = driver.findElement(By.id("from_name"));
		WebElement to = driver.findElement(By.id("to_name"));
		WebElement dateFrom = driver.findElement(By.id("departure_date"));
		WebElement dateTo = driver.findElement(By.id("departure_date_1"));

		FirefoxDriver js = (FirefoxDriver) driver;


		js.executeScript("$('#from_name').val('" + req.getFrom() + "')");
		js.executeScript("$('#to_name').val('" + req.getTo() + "')");
		js.executeScript("$('#from_code').val('" + req.getFromCode() + "')");
		js.executeScript("$('#to_code').val('" + req.getToCode() + "')");

		selectDate(dateFrom, req.getDateFrom());
		selectDate(dateTo, req.getDateTo());

		js.executeScript("$('.search_button')[0].click()");

		new WebDriverWait(driver, 60*2, 500).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				LOGGER.info("Waiting for search result page...");
				return driver.findElement(By.className("change_search")) != null;
			}
		});
		
		return new SearchPage(driver);
	}

	private void selectDate(WebElement dateElement, LocalDate date) {
		WebElement element = driver.findElement(By.id("ui-datepicker-div"));
		if (!element.isDisplayed()) {
			dateElement.click();
		}
		WebElement first = element.findElement(By.className("ui-datepicker-group-first"));
		WebElement second = element.findElement(By.className("ui-datepicker-group-last"));
		List<WebElement> firstTds = first.findElements(By.tagName("td"))
				.stream().filter(e -> !e.getAttribute("class").contains("ui-datepicker-unselectable")).collect(
						Collectors.toList());
		Integer year = Integer.valueOf(firstTds.iterator().next().getAttribute("data-year"));
		Integer month = (Integer.valueOf(firstTds.iterator().next().getAttribute("data-month")) + 1);

		if (month == date.getMonthValue()) {
			for (WebElement firstTd : firstTds) {
				WebElement a = firstTd.findElement(By.tagName("a"));
				String text = a.getText();
				if (!NumberUtils.isNumber(text)) {
					continue;
				}
				if (Integer.valueOf(text) == date.getDayOfMonth()) {
					a.click();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return;
				}
			}
		}

		if (date.getMonthValue() > month) {
			second.findElement(By.className("ui-datepicker-next")).click();
			selectDate(dateElement, date);
			return;
		}

		// TODO sprawdzac rok i mniejsza date

	}
}