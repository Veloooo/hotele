package com.kodilla.testing2.facebook;

import com.kodilla.testing2.config.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FacebookTestingApp {
    public static final String ACCEPT_COOKIES = "//button[contains(@title, \"Accept All\")]";
    public static final String CREATE_ACCOUNT = "//form/div/a[contains(@href, \"#\")]";
    public static final String SET_YEAR = "//select[contains(@name, \"birthday_year\")]";
    public static final String SET_MONTH = "//select[contains(@name, \"birthday_month\")]";
    public static final String SET_DAY = "//select[contains(@name, \"birthday_day\")]";

    public static void main(String[] args) {
        WebDriver driver = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
        driver.get("https://Facebook.com");
        WebDriverWait wait = new WebDriverWait (driver, 10);

        WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ACCEPT_COOKIES)));
        cookieButton.click();

        WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CREATE_ACCOUNT)));
        createButton.click();

        WebElement birthdayYear = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SET_YEAR)));
        WebElement birthdayMonth = driver.findElement(By.xpath(SET_MONTH));
        WebElement birthdayDay = driver.findElement(By.xpath(SET_DAY));
        Select selectYear = new Select(birthdayYear);
        Select selectMonth = new Select(birthdayMonth);
        Select selectDay = new Select(birthdayDay);
        selectYear.selectByValue("1985");
        selectMonth.selectByVisibleText("Nov");
        selectDay.selectByValue("5");

    }
}
