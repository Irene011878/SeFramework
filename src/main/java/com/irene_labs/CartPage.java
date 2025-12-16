package com.irene_labs;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends AbstractComponent {

    //WebDriver driver;

    By checkoutBtn = By.cssSelector(".totalRow button");
    By cartProducts = By.cssSelector(".cartSection h3");


    public CartPage(WebDriver driver){

        super(driver);
        //this.driver = driver;
    }

    public Boolean VerifyProductDisplay(String productName){
        List<WebElement> titles = driver.findElements(cartProducts);
        return titles.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
    }

    public CheckoutPage  goToCheckout(){

        driver.findElement(checkoutBtn).click();
        return new CheckoutPage(driver);

    }


}
