package org.AXA.Inhub.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import static org.AXA.Inhub.steps.Steps.screenshotUtils;
import static org.AXA.Inhub.steps.Steps.test;

public class AdditionalDataPage {

    @FindBy(xpath = "//p[.='¿Es de importación directa?']//following::button[2]")
    private WebElement btnImport;

    @FindBy(xpath = "//p[.='¿Tiene accesorios?']//following::button[2]")
    private WebElement btnAccessories;

    @FindBy(xpath = "//div[@id='hasArmor']//following::button[1]")
    private WebElement btnArmor;

    @FindBy(xpath = "//p[.='¿Vehículo en Leasing?']//following::button[2]")
    private WebElement btnLeasing;

    @FindBy(xpath = "//p[.='¿Tiene conductor habitual?']//following::button[2]")
    private WebElement btndriver;

    @FindBy(xpath = "//button[.='OFERTA']")
    private WebElement btnOffer;

    private WebDriver driver;
    private WebDriverWait wait;

    public AdditionalDataPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(60));
    }

    public void datosAdicionales(){
//        wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("policyValidityDays"), "366"));
        wait.until(driver -> {
            WebElement element = driver.findElement(By.id("policyValidityDays"));
            String textoCampo = element.getAttribute("value");
            return textoCampo != null && !textoCampo.isEmpty();
        });
        btnImport.click();
        btnAccessories.click();
        btnArmor.click();
        btnLeasing.click();
        btndriver.click();
        wait.until(ExpectedConditions.elementToBeClickable(btnOffer));
        btnOffer.click();
        takeScreenshot();
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
