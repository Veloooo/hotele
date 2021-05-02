package com.kodilla.testing2.ebay;

import com.kodilla.testing2.config.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EBayTestingApp {
    public static final String SEARCHFIELDNAME = "_nkw" ;
    public static final String SEARCHFIELDID = "gh-ac";

    public static void main(String[] args) {
        WebDriver chromeDriver = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
        chromeDriver.get("https://www.ebay.com");

        WebElement chromeSearchField = chromeDriver.findElement(By.name(SEARCHFIELDNAME));
        chromeSearchField.sendKeys("Laptop");
        chromeSearchField.submit();

        WebDriver firefoxDriver = WebDriverConfig.getDriver(WebDriverConfig.FIREFOX);
        firefoxDriver.get("https://www.ebay.com");

        WebElement firefoxSearchField = firefoxDriver.findElement(By.id(SEARCHFIELDID));
        firefoxSearchField.sendKeys("Laptop");
        firefoxSearchField.submit();
    }
}
