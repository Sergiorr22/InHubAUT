package org.AXA.Inhub.steps;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.AXA.Inhub.pages.AdditionalDataPage;
import org.AXA.Inhub.pages.BasicDataPage;
import org.AXA.Inhub.pages.HomePage;
import org.AXA.Inhub.pages.OfferPage;
import org.AXA.Inhub.utils.WebServices;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.AXA.Inhub.utils.ExtentManage;
import org.AXA.Inhub.utils.ScreenshotUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Steps {

    private static WebDriver driver;
    private static HomePage homePage;
    private static BasicDataPage basicDataPages;
    private static AdditionalDataPage additionalDataPage;
    private static OfferPage offerPage;
    private static ExtentReports extent;
    public static ExtentTest test;
    public static ScreenshotUtils screenshotUtils;
    public static String pathReport;


    public static Properties properties = new Properties();
    @BeforeAll
    public static void beforeAll(){
        properties.load(new FileInputStream(new File("src/main/resources/configuracion.properties")))
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());
        pathReport = "output/"+timestamp;
        extent = ExtentManage.getInstance(pathReport);
    }

    @Before
    public static void setup(Scenario scenario){
        test = extent.createTest(scenario.getName());
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        screenshotUtils = new ScreenshotUtils(driver, pathReport);
        homePage = new HomePage(driver);
        basicDataPages = new BasicDataPage(driver);
        additionalDataPage = new AdditionalDataPage(driver);
        offerPage = new OfferPage(driver);
    }

    @After
    public static void tearDown(){
        driver.close();
        driver.quit();
        extent.flush();
    }

    @AfterClass
    public static void afterClass(){
        System.out.println("Mensaje despues de todas las pruebas");
    }


    @Given("que estoy en el portal Inhub {string} y perfil {string} con {string}")
    public void que_estoy_en_el_portal_Inhub(String producto, String clave, String agente) throws IOException {
        if(clave.equals("")){
            driver.get("https://axa-auto-uat.inhub.cloud/");
            test.pass("Se ingreso de manera exitosa a Inhub");
            homePage.seleccionarProducto(producto);
            homePage.seleccionarAgente(agente);
        }
        else {
            String url = WebServices.traerUrl(clave);
            driver.get(url);
            test.pass("Se ingreso de manera exitosa a Inhub");
        }
    }

    @When("Diligencio la informacion del asesor {string}")
    public void info_Asesor(String clave)  {
        test.pass("Se diligencio correctamente la información del asesor");
    }

    @And("ingreso los datos del formulario datos basicos {string}, {string}, {string}, {string}, {string}")
    public void ingreso_datosb(String tipoDoc, String numDoc, String fasecolda, String servicio, String zona)  {
        basicDataPages.datosPersonales(tipoDoc, numDoc);
        test.pass("La información de la persona fue diligenciada");
        basicDataPages.datosAuto(fasecolda, servicio, zona);
        test.pass("Se encuentra vehiculo y diligencia información");
    }

    @And("Ingreso los datos del formulario datos adicionales")
    public void ingreso_datosAdic () {
        additionalDataPage.datosAdicionales();
        test.pass("Se diligencio de manera exitosa la información adicional");
    }

    @Then("Se selecciona oferta y se guarda cotizacion {string}, {string}, {string}, {string}, {string}")
    public void guardar_cotizacion(String asistencia, String email, String name, String lastName, String telefono) {
        offerPage.oferta(asistencia);
        //offerPage.email(email, name, lastName, telefono);
    }

    @And("ingreso los datos del formulario datos basicos {string}, {string}, {string}, {string}, {string}, {string}")
    public void ingreso_datosPlaca(String tipoDoc, String numDoc, String fasecolda, String servicio, String zona, String placa)  {
        basicDataPages.datosPersonales(tipoDoc, numDoc);
        test.pass("La información de la persona fue diligenciada");
        if(fasecolda.equals("")){
            basicDataPages.datosAutoPlaca(servicio, zona, placa);
            test.pass("Se encuentra vehiculo y diligencia información");
        }
        else {
            basicDataPages.datosAuto(fasecolda, servicio, zona);
            test.pass("Se encuentra vehiculo y diligencia información");
        }
    }

    private static WebDriver configDriverChrome(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximied");
        //options.addArguments("--window-size=900,720");
        //options.addArguments("incognito");
        options.addArguments("headless");
        options.addArguments("disable-extensions");
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", "C:\\TestDownloads");
        options.setExperimentalOption("prefs", prefs);
        //options.addExtensions();
        return new ChromeDriver(options);
    }
}
