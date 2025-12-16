package com.irene_labs;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConfirmationPage extends AbstractComponent {

    //WebDriver driver;

    By confirmationMessageBy = By.cssSelector(".hero-primary");

    public ConfirmationPage(WebDriver driver){

        super(driver);
        //this.driver = driver;
    }

    public String getConfirmationMessage(){

        return driver.findElement(confirmationMessageBy).getText();
    }


}
