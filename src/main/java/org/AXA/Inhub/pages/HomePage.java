package org.AXA.Inhub.pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.AXA.Inhub.steps.Steps.test;
import static org.AXA.Inhub.steps.Steps.screenshotUtils;

public class HomePage {
    @FindBy(id = "adviser")
    private WebElement asesor;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnAsesor;

    @FindBy(xpath = "//*[.='Cargando...']")
    private WebElement cargando;

    private WebDriver driver;
    private WebDriverWait wait;
    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(60));
    }

    public void seleccionarProducto(String producto){
        WebElement btnCotizar = driver.findElement(By.xpath("//p[.='"+producto+"']//following::button[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(btnCotizar));
        btnCotizar.click();
        takeScreenshot();
    }
    public void seleccionarAgente(String agente){
        asesor.sendKeys(agente, Keys.ENTER);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        asesor.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(btnAsesor));
        takeScreenshot();
        btnAsesor.click();
    }

    private void takeScreenshot(WebElement element){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.backgroundColor = \"red\";", element);
        test.addScreenCaptureFromPath(screenshotUtils.captureScreenshot(timestamp));
        js.executeScript("arguments[0].style.backgroundColor = \"red\";", element);
    }

    private void takeScreenshot(){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());

        test.addScreenCaptureFromPath(screenshotUtils.captureScreenshot(timestamp));
    }
}