package pl.greenwind.tickets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class represents page with search results
 * 
 * @author Piotr Janik
 *
 */
public class SearchPage {
	
	public final static Logger LOGGER = LoggerFactory.getLogger(SearchPage.class);
	
	private WebDriver driver;
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
	}

	public List<Double> getPrices() {
		List<WebElement> price_cell_pln = driver.findElements(By.cssSelector(".your_price .PLN"));
		return price_cell_pln.stream()
				.map(e -> Double.valueOf(e.getText().replace(" ", "").replace("PLN", ""))).collect(Collectors.toList());
	}

	public String getPage() {
		return driver.getPageSource();
	}
}
