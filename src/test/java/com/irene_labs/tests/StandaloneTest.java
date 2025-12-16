package com.irene_labs.tests;

import com.irene_labs.LandingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandaloneTest {

    public static void main(String[] args) {

        String productName = "ZARA COAT 3";

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client");

        //LandingPage landingPage = new LandingPage(driver);
        //landingPage.loginAplication("iremma8@love.com", "1234/Abcd");
        driver.findElement(By.id("userEmail")).sendKeys("iremma8@love.com");
        driver.findElement(By.id("userPassword")).sendKeys("1234/Abcd");
        driver.findElement(By.id("login")).click();

        //para esperar que se carguen los productos(de manera global)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        //para grabar todos los productos en una lista
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        //interactuar con los productos con Java Streams
        WebElement prod = products.stream().filter(product->
                product.findElement(By.cssSelector("b")).getText().equals(productName))
                .findFirst().orElse(null);
        //para encontrar un elemento a partir de la interaccion anterior osea el Zara Coat
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container"))); //btn Add to cart
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))); // mensaje de producto agregado
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click(); //btn del carrito

        List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

        Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);

        driver.findElement(By.cssSelector(".totalRow button")).click();

        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));

        //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();

        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
        driver.close();


    }
}
