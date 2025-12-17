package com.irene_labs.TestComponents;

import com.irene_labs.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;


/*public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;

    // 🔹 Inicializa el driver
    public WebDriver initializeDriver() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir")
                        + "/src/main/java/Resources/GlobalData.properties");
        prop.load(fis);

        String browserName = System.getProperty("browser") != null
                ? System.getProperty("browser")
                : prop.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();

            // 👉 CI / GitHub Actions
            if (System.getProperty("ci") != null) {
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
            }

            driver = new ChromeDriver(options);

        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        return driver;
    }

    // 🔹 Leer JSON y convertirlo en HashMap
    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

        String jsonContent = FileUtils.readFileToString(
                new File(filePath),
                StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(
                jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {
                });
    }

    // 🔹 Screenshot (para Extent Report)
    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        String folderPath = System.getProperty("user.dir") + "/reports/screenshots/";
        File destinationFile = new File(folderPath + testCaseName + ".png");

        destinationFile.getParentFile().mkdirs();
        FileUtils.copyFile(source, destinationFile);

        // Ruta relativa para Extent
        return "screenshots/" + testCaseName + ".png";
    }

    // 🔹 BeforeMethod
    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {

        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    // 🔹 AfterMethod
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                getScreenshot(result.getName(), driver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}*/
public class BaseTest {

    protected WebDriver driver;
    protected LandingPage landingPage;

    // 🔹 Inicializa el driver
    public WebDriver initializeDriver() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir")
                        + "/src/main/java/Resources/GlobalData.properties");
        prop.load(fis);

        String browserName = System.getProperty("browser") != null
                ? System.getProperty("browser")
                : prop.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();

            // ✅ Detecta CI correctamente
            boolean isCI = System.getenv("GITHUB_ACTIONS") != null;

            if (isCI) {
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
            }

            driver = new ChromeDriver(options);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    // 🔹 Leer JSON y convertirlo en HashMap
    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

        String jsonContent = FileUtils.readFileToString(
                new File(filePath),
                StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(
                jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {
                });
    }

    // 🔹 Screenshot
    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        String folderPath = System.getProperty("user.dir") + "/reports/screenshots/";
        File destinationFile = new File(folderPath + testCaseName + ".png");

        destinationFile.getParentFile().mkdirs();
        FileUtils.copyFile(source, destinationFile);

        return "screenshots/" + testCaseName + ".png";
    }

    // 🔹 BeforeMethod
    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {

        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    // 🔹 AfterMethod
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                getScreenshot(result.getName(), driver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
