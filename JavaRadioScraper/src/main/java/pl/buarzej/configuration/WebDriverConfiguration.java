package pl.buarzej.configuration;

import jakarta.annotation.PreDestroy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebDriverConfiguration {

    private final ChromeOptions options = new ChromeOptions()
            .addArguments("--headless", "--disable-gpu", "--no-sandbox");

    private WebDriver driver;

    @Bean
    public WebDriver webDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "E:\\Projects\\Webdrivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
            driver = new ChromeDriver(options);
        }

        return driver;
    }

    @PreDestroy
    public void closeWebDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println("WebDriver closed.");
        }
    }
}
