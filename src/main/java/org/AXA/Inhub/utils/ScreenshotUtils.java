package org.AXA.Inhub.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotUtils {

    private String pathScreenShot;

    private WebDriver driver;

    public ScreenshotUtils (WebDriver driver, String pathScreenShot){
        this.driver =  driver;
        this.pathScreenShot = pathScreenShot;

    }

    public String captureScreenshot(String screenshotName){
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] bytes = ts.getScreenshotAs(OutputType.BYTES);
            String screenshotPath = pathScreenShot + "/" +screenshotName+".png";
            Files.write(Paths.get(screenshotPath), bytes);
            return screenshotName+".png";
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

}
