import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class Purchase {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private ResourceManager resource;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/resources/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        resource = new ResourceManager(driver);

    }

    @Test
    public void summerDresses() throws Exception {

        //open website and select summer dress
        resource.openSite();
        resource.selectWomen();

        //Select Chiffon dress
        resource.findDress();
        resource.selectChiffon();

        //Select the size and add to cart
        resource.selectSize();
        resource.clickAddToCart();
        resource.clickProcessToCheckout();

        //Process your order
        resource.scrollBottom();
        resource.clickProcess();

        //create an account and fill mandatory information
        resource.createAccountEmail();
        resource.clickSubmitCreate();
        resource.sendCustomerFirstName();
        resource.sendCustomerLastName();

        resource.sendEmail();
        resource.sendPassword();
        resource.sendFirstName();
        resource.sendLastName();
        resource.sendAddress();

        resource.selectCity();
        resource.selectState();
        resource.selectZip();
        resource.country();

        resource.sendMobile();
        resource.clickSubmitAccount();

        //Accept the T&C and complete purchase
        resource.clickProcessAddress();
        resource.acceptTerms();
        resource.clickProcessCarrier();

        //verify correct order
        assertTrue(resource.isPaymentMethodPresent());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}


