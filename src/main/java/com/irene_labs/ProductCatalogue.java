package com.irene_labs;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {

    //WebDriver driver;

    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");
    By spinner = By.cssSelector(".ngx-spinner-overlay");
    //By spinner = By.cssSelector("..ng-animating");

    //consturctor
    public ProductCatalogue(WebDriver driver)
    {
        //inicialization
        super(driver);  //este paso se tiene que hacer en cada clase hija para conectrar con la clase padreAbtractComponent
        //this.driver = driver;
    }

    //ACTIONS METHODS
    public List<WebElement>getProductList(){

        waitForElementToAppear(productsBy);
        return driver.findElements(productsBy);
    }

    public WebElement getProductByName(String productName){

        return getProductList().stream().filter(product->
                        product.findElement(By.cssSelector("b")).getText().equals(productName))
                .findFirst().orElse(null);

    }

    public void addProductToCart(String productName)throws InterruptedException{

        WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDissapear(spinner);

    }

}
