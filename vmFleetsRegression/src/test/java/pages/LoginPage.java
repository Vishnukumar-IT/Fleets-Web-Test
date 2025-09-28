package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	 WebDriver driver;
	    WebDriverWait wait;

	    public LoginPage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    }

	    By loginNav = By.id("login-nav");
	    By emailInput = By.id("emailInput");
	    By passwordInput = By.id("password");
	    By loginButton = By.id("login");

	    public void login(String email, String password) {
	        wait.until(ExpectedConditions.elementToBeClickable(loginNav)).click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
	        driver.findElement(passwordInput).sendKeys(password);
	        driver.findElement(loginButton).click();
	    }

}
