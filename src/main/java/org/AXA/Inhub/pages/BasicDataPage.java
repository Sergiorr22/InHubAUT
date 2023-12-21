package org.AXA.Inhub.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import static org.AXA.Inhub.steps.Steps.test;
import static org.AXA.Inhub.steps.Steps.screenshotUtils;

public class BasicDataPage {

    @FindBy(xpath = "//button[@value='true' and @tabindex='0']")
    private WebElement dataTreatment;

    @FindBy(id = "documentType")
    private WebElement docType;

    @FindBy(id = "documentNumber")
    private WebElement docNumb;

    @FindBy(xpath = "//button[@tabindex='0' and @value='false']")
    private WebElement btnPlaca;

    @FindBy(id = "codeFasecolda")
    private WebElement btnFasecolda;

    @FindBy(xpath = "//input[@id='codeFasecolda']//following::button[1]")
    private WebElement btnSearchFasecolda;

    @FindBy(id = "typeService")
    private WebElement typeService;

    @FindBy(id = "circulationZone")
    private WebElement zone;

    @FindBy(xpath = "//button[.='CONTINUAR']")
    private WebElement btnContinuar;

    @FindBy(id = "licensePlate")
    private WebElement autPlaca;

    @FindBy(xpath = "//button[.='Buscar']")
    private WebElement btnBuscarP;

    private WebDriver driver;
    private WebDriverWait wait;
    public BasicDataPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(60));
    }
    public void datosPersonales(String tipoDoc, String numDoc) {
        wait.until(ExpectedConditions.elementToBeClickable(dataTreatment));
        dataTreatment.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        docType.click();
        WebElement dataDoc = driver.findElement(By.xpath("//li[.='" + tipoDoc + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(dataDoc));
        dataDoc.click();
        docNumb.sendKeys(numDoc, Keys.TAB);
        takeScreenshot();
    }
    public void datosAuto(String fasecolda, String servicio, String zona) {
        btnPlaca.click();
        btnFasecolda.sendKeys(fasecolda);
        btnSearchFasecolda.click();
        takeScreenshot();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        typeService.click();
        driver.findElement(By.xpath("//li[.='" + servicio + "']")).click();
        typeService.sendKeys(Keys.PAGE_DOWN);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        zone.click();
        driver.findElement(By.xpath("//li[.='" + zona + "']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(btnContinuar));
        takeScreenshot();
        btnContinuar.click();
    }

    public void datosAutoPlaca(String placa, String servicio, String zona) {
        autPlaca.sendKeys(placa);
        btnBuscarP.click();
        takeScreenshot();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        typeService.click();
        driver.findElement(By.xpath("//li[.='" + servicio + "']")).click();
        typeService.sendKeys(Keys.PAGE_DOWN);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        zone.click();
        driver.findElement(By.xpath("//li[.='" + zona + "']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(btnContinuar));
        takeScreenshot();
        btnContinuar.click();
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
