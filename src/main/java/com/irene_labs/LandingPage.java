package com.irene_labs;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*public class LandingPage extends AbstractComponent {

    //WebDriver driver;

    By userEmail = By.id("userEmail");
    By userPassword = By.id("userPassword");
    By submitBtn = By.id("login");
    By errorMessage = By.cssSelector("[class*='flyInOut']");

    //consturctor
    public LandingPage(WebDriver driver)
    {
        super(driver);
        //this.driver = driver;
    }

    //ACTIONS METHODS

    public ProductCatalogue loginAplication(String email, String password)
    {
        driver.findElement(userEmail).sendKeys(email);
        driver.findElement(userPassword).sendKeys(password);
        driver.findElement(submitBtn)   .click();
        driver.findElement(errorMessage);


        //como sabes que despues del click te llevara a ProductCatalogue
        return  new ProductCatalogue(driver);
    }

    public String getErrorMessage()
    {
        waitForElementToAppear(errorMessage);
        return driver.findElement(errorMessage).getText();
    }



    public void goTo()
    {
        driver.get("https://rahulshettyacademy.com/client");
    }
}*/
public class LandingPage extends AbstractComponent {

    By userEmail = By.id("userEmail");
    By userPassword = By.id("userPassword");
    By submitBtn = By.id("login");
    By errorMessage = By.cssSelector("[class*='flyInOut']");

    // Constructor
    public LandingPage(WebDriver driver) {
        super(driver);
    }

    // 🔹 Login action
    public ProductCatalogue loginApplication(String email, String password) {

        driver.findElement(userEmail).clear();
        driver.findElement(userEmail).sendKeys(email);

        driver.findElement(userPassword).clear();
        driver.findElement(userPassword).sendKeys(password);

        driver.findElement(submitBtn).click();

        // ⚠️ NO validamos error aquí
        // El test decide si espera ProductCatalogue o error

        return new ProductCatalogue(driver);
    }

    // 🔹 Error message (solo cuando el login falla)
    public String getErrorMessage() {
        waitForElementToAppear(errorMessage);
        return driver.findElement(errorMessage).getText().trim();
    }

    // 🔹 Open app
    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client");
    }
}
