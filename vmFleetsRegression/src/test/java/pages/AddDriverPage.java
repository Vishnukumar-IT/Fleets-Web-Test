package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.WaitUtil;

public class AddDriverPage {
	WebDriver driver;
    WebDriverWait wait;
    By loader = By.className("loader-full");

    public AddDriverPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(70));
    }

    By driverDropdown = By.xpath("/html/body/div[2]/section[1]/div[3]/div/div[3]/div[1]/div[1]/span");
    By activeDrivers = By.id("menu-activeDrivermanagement");
    By addDriverMenu = By.id("menu-addNewDriver");
    By licenseInput = By.name("dl_no");
    By dobInput = By.id("dob_driver");
    By verifyButton = By.xpath("/html/body/div[2]/section[2]/div[2]/form/div[2]/div[2]/div[1]/div[1]/div[3]/button");
    By phoneInput = By.id("phoneno");
    By nextButton = By.id("personal-nextbtn");
    By submitButton = By.id("personal-submitbutton");

    public void openAddDriverForm() {
    	WaitUtil.waitForLoaderAndClick(driver, loader, driverDropdown, 70);
        WaitUtil.waitForLoaderAndClick(driver, loader, activeDrivers, 70);
    }

    public void fillDriverDetails(String license, String dob, String phone) {
    	WaitUtil.waitForLoaderAndClick(driver, loader, addDriverMenu, 70);
        driver.findElement(licenseInput).sendKeys(license);
        driver.findElement(dobInput).sendKeys(dob);
        WaitUtil.waitForLoaderAndClick(driver, loader, verifyButton, 70);
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void completeForm() throws InterruptedException {
    	WaitUtil.waitForLoaderAndClick(driver, loader, nextButton, 70);
        WaitUtil.waitForLoaderAndClick(driver, loader, nextButton, 70);
        WaitUtil.waitForLoaderAndClick(driver, loader, submitButton, 70);
    }

}
