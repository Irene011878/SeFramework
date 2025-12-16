package com.irene_labs;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends AbstractComponent {

    //WebDriver driver;

    By countryInput = By.cssSelector("[placeholder='Select Country']");
    By results = By.cssSelector(".ta-results");
    By selectCountryOption = By.xpath("(//button[contains(@class,'ta-item')])[2]");
    By submitBtn = By.cssSelector(".action__submit");

    public CheckoutPage (WebDriver driver){

        super(driver);
        //this.driver = driver;

    }


     public void selectCountry(String countryName){

         Actions a = new Actions(driver);
         a.sendKeys(driver.findElement(countryInput), countryName).build().perform();
         waitForElementToAppear(results);
         driver.findElement(selectCountryOption).click();

     }

     public ConfirmationPage submitOrder(){

         driver.findElement(submitBtn).click();
         return new ConfirmationPage(driver);

     }
}
