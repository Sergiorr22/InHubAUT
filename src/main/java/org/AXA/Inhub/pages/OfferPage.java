package org.AXA.Inhub.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.WatchEvent;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import static org.AXA.Inhub.steps.Steps.screenshotUtils;
import static org.AXA.Inhub.steps.Steps.test;

public class OfferPage {

    @FindBy(xpath = "//button[.='GUARDAR COTIZACIÓN']")
    private WebElement btnSave;

    @FindBy(xpath = "//button[.='Aceptar']")
    private WebElement btnAccept;

    @FindBy(xpath = "//button[.='ENVIAR POR CORREO']")
    private WebElement btnEmail;

    @FindBy(xpath = "//td[.='CLIENTE']//preceding::input[1]")
    private WebElement checkbox;

    @FindBy(xpath = "//td[.='CLIENTE']//preceding::button[1]")
    private WebElement btnEdit;

    @FindBy(xpath = "//input[@placeholder='Dirección de Email']")
    private WebElement campoEmail;

    @FindBy(id = "//input[@placeholder='Nombre(s)']")
    private WebElement campoName;

    @FindBy(id = "//input[@placeholder='Apellido(s)']")
    private WebElement campoLastname;

    @FindBy(id = " //input[@placeholder='Teléfono']")
    private WebElement campoTelefono;

    @FindBy(xpath = "//td[.='CLIENTE']//preceding::span[2]")
    private WebElement btnSaveEmail;


    private WebDriver driver;
    private WebDriverWait wait;
    public OfferPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(60));
    }

    public void oferta(String asistencia){

        WebElement valueAssitance = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/form/div[3]/div/div/div[4]/div/div[2]/p[2]")));
        wait.until(driver -> {
            String textoParrafo = valueAssitance.getText();
            return textoParrafo != null && !textoParrafo.isEmpty();
        });
        WebElement btnValor = driver.findElement(By.xpath("//p[.='"+asistencia+"']//following::button[1]"));
        btnValor.click();
        takeScreenshot();
        btnSave.click();
        wait.until(ExpectedConditions.elementToBeClickable(btnAccept));
        takeScreenshot();
        btnAccept.click();
    }

    public void email(String email, String name, String lastName, String telefono){

        btnEmail.click();
        checkbox.click();
        wait.until(ExpectedConditions.elementToBeClickable(btnEdit));
        btnEdit.click();
        campoEmail.sendKeys(email, Keys.TAB);
        campoName.sendKeys(name, Keys.TAB);
        campoLastname.sendKeys(lastName, Keys.TAB);
        campoTelefono.sendKeys(telefono);
        btnSaveEmail.click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
