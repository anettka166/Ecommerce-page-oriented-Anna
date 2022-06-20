package com.academy.techcenture.ecommerce.base;

import com.academy.techcenture.ecommerce.config.ConfigReader;
import com.academy.techcenture.ecommerce.config.Driver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class BaseTest {

    protected WebDriver driver;
    protected SoftAssert softAssert;
    protected String screenshotFolderName;


    @BeforeMethod
    public void setUp() {
        driver = Driver.getDriver();
        driver.get(ConfigReader.getProperty("URL"));
        softAssert = new SoftAssert();

    }

    @AfterMethod
    public void cleanUp(ITestResult result){

        if (result.getStatus() == ITestResult.FAILURE){
            captureScreenshot(result.getTestContext().getName() + "_" + result.getMethod().getMethodName() + ".jpg");
        }

        if (driver != null){
            driver.quit();
        }

    }

    public void captureScreenshot(String fileName){
        if (screenshotFolderName == null){
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
            screenshotFolderName = dateTime.format(formatter);
        }

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        File destFile = new File("./Screenshots/"+screenshotFolderName + "/" + fileName);

        try {
            FileUtils.copyFile(sourceFile, destFile);
        }catch (Exception e){

        }
        System.out.println("Screenshot saved successfully");

    }

}