package AbstractComponents;

import com.irene_labs.CartPage;
import com.irene_labs.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/*public class AbstractComponent {
    //todos los metodos que creas que seran reusables ponlos aqui
    //this will be the parent class of the Pages classes

    protected WebDriver driver;
    protected WebDriverWait wait;

    By cartHeader = By.cssSelector("[routerlink*='cart']"); //btn del carrito
    By orderHeader = By.cssSelector("[routerlink*='myorders']");  //btn order


    public AbstractComponent(WebDriver driver)
    {
        this.driver = driver;    //this is how we are connecting the 2 pieces parent with child
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }


    public void waitForElementToAppear(By locator)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }



    public  CartPage goToCartPage()
    {
        driver.findElement(cartHeader).click();
        return new CartPage(driver);
    }

    public OrderPage goToOrdersPage()
    {
        driver.findElement(orderHeader).click();
        return new OrderPage(driver);
    }

    public void waitForElementToDissapear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public  void waitForWebElementToDissapear(WebElement ele) throws InterruptedException{

        Thread.sleep(1000);
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //wait.until(ExpectedConditions.invisibilityOf(ele));

    }
}*/
//16 dic try 1
/*public class AbstractComponent {

    protected WebDriver driver;
    protected WebDriverWait wait;

    By cartHeader = By.cssSelector("[routerlink*='cart']");
    By orderHeader = By.cssSelector("[routerlink*='myorders']");

    // 🔹 Constructor
    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 🔹 Espera por locator (By)
    public void waitForElementToAppear(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // 🔹 Espera por WebElement
    public void waitForWebElementToAppear(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // 🔹 Espera a que desaparezca un elemento (By)
    public void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // 🔹 Espera a que desaparezca un WebElement
    public void waitForWebElementToDisappear(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    // 🔹 Navegación común
    public CartPage goToCartPage() {
        driver.findElement(cartHeader).click();
        return new CartPage(driver);
    }

    public OrderPage goToOrdersPage() {
        driver.findElement(orderHeader).click();
        return new OrderPage(driver);
    }
}*/
public class AbstractComponent {

    protected WebDriver driver;
    protected WebDriverWait wait;

    // 🔹 Headers
    By cartHeader = By.cssSelector("[routerlink*='cart']");
    By orderHeader = By.cssSelector("[routerlink*='myorders']");

    // 🔹 Spinner / Loader (CLAVE para CI)
    By spinner = By.cssSelector(".ngx-spinner-overlay");

    // 🔹 Constructor
    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 🔹 Espera por locator (By)
    public void waitForElementToAppear(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // 🔹 Espera por WebElement
    public void waitForWebElementToAppear(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // 🔹 Espera a que desaparezca un elemento (By)
    public void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // 🔹 Espera a que desaparezca un WebElement
    public void waitForWebElementToDisappear(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    // 🔹 Navegación común (CORREGIDA PARA CI)
    public CartPage goToCartPage() {

        // 🔴 PASO CRÍTICO: esperar a que el spinner desaparezca
        waitForElementToDisappear(spinner);

        // 🔴 asegurar que el botón sea visible
        waitForElementToAppear(cartHeader);

        driver.findElement(cartHeader).click();
        return new CartPage(driver);
    }

    public OrderPage goToOrdersPage() {

        waitForElementToDisappear(spinner);
        waitForElementToAppear(orderHeader);

        driver.findElement(orderHeader).click();
        return new OrderPage(driver);
    }
}

