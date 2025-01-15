package pl.buarzej.configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebDriverConfiguration {

    private final ChromeOptions options = new ChromeOptions()
            .addArguments("--headless", "--disable-gpu", "--no-sandbox");

    @Bean(destroyMethod = "quit")
    public WebDriver webDriver() {
        System.setProperty("webdriver.chrome.driver", "E:\\Projects\\Webdrivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        return new ChromeDriver(options);
    }
}
