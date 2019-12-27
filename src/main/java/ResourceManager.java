import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ResourceManager {
    JavascriptExecutor js;
    String website = "http://automationpractice.com/index.php";
    String email = "a1b2" + RandomStringUtils.randomAlphanumeric(8) + "@gmail.com";
    String firstName = "myFirstName";
    String lastName = "myLastName";
    String password = "mypass12";
    String address1 = "100 Sheppard ave";
    String city = "myCity";
    String state = "Alaska";
    String zip = "23145";
    String mobile = "4161234567";
    private String baseUrl;
    private WebDriver driver;
    private WebDriverWait wait;


    ResourceManager(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, 30);
    }

    public static void Hover(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    public static void HoverAndClick(WebDriver driver, WebElement elementToHover, WebElement elementToClick) {
        Actions action = new Actions(driver);
        action.moveToElement(elementToHover).click(elementToClick).build().perform();
    }

    public void selectWomen() throws InterruptedException {

        WebElement women = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[title=\"Women\"]")));
        Hover(driver, women);

        WebElement dress = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[title=\"Summer Dresses\"]")));
        HoverAndClick(driver, women, dress);

    }

    public void findDress() {
        WebElement chiffon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div/div[3]/div[2]/ul/li[3]/div/div[2]/h5/a")));
        js.executeScript("arguments[0].scrollIntoView();", chiffon);
    }

    public void selectChiffon() {
        WebElement chiffon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[title=\"Printed Chiffon Dress\"]")));
        HoverAndClick(driver, chiffon, chiffon);

    }

    public void selectSize() {
        new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("group_1")))).selectByVisibleText("M");
    }

    public void clickAddToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add_to_cart"))).click();
    }

    public void clickProcessToCheckout() {
        WebElement process = driver.findElement(By.xpath("//a[@title='Proceed to checkout']"));
        wait.until(ExpectedConditions.elementToBeClickable(process)).click();

    }

    public void scrollBottom() {
        WebElement total = driver.findElement(By.className("total_price_container"));
        wait.until(ExpectedConditions.elementToBeClickable(total));
        js.executeScript("arguments[0].scrollIntoView();", total);
    }

    public void openSite() {
        driver.get(website);
    }

    public void clickProcess() {
        WebElement process = driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]/span"));
        wait.until(ExpectedConditions.elementToBeClickable(process)).click();
    }

    public void createAccountEmail() {
        WebElement emailCreate = driver.findElement(By.id("email_create"));
        wait.until(ExpectedConditions.elementToBeClickable(emailCreate));
        emailCreate.clear();
        System.out.println("Email address is " + email);
        emailCreate.sendKeys(email);
    }

    public void clickSubmitCreate() {
        WebElement submit = driver.findElement(By.id("email_create"));
        js.executeScript("arguments[0].scrollIntoView();", submit);
        driver.findElement(By.id("SubmitCreate")).click();
    }

    public void sendCustomerFirstName() {
        WebElement first = driver.findElement(By.id("customer_firstname"));
        wait.until(ExpectedConditions.elementToBeClickable(first));
        first.clear();
        first.sendKeys(firstName);
    }

    public void sendCustomerLastName() {
        driver.findElement(By.id("customer_lastname")).clear();
        driver.findElement(By.id("customer_lastname")).sendKeys(lastName);
    }

    public void sendEmail() {
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);
    }

    public void sendPassword() {
        driver.findElement(By.id("passwd")).clear();
        driver.findElement(By.id("passwd")).sendKeys("aaaaaaa");

    }

    public void sendFirstName() {
        driver.findElement(By.id("firstname")).clear();
        driver.findElement(By.id("firstname")).sendKeys(firstName);
    }

    public void sendLastName() {
        driver.findElement(By.id("lastname")).clear();
        driver.findElement(By.id("lastname")).sendKeys(lastName);
    }

    public void sendAddress() {
        driver.findElement(By.id("address1")).clear();
        driver.findElement(By.id("address1")).sendKeys(address1);
    }

    public void selectCity() {
        driver.findElement(By.id("city")).clear();
        driver.findElement(By.id("city")).sendKeys(city);
    }

    public void selectState() {
        new Select(driver.findElement(By.id("id_state"))).selectByVisibleText(state);
    }

    public void selectZip() {
        driver.findElement(By.id("postcode")).clear();
        driver.findElement(By.id("postcode")).sendKeys(zip);
    }

    public void country() {
        new Select(driver.findElement(By.id("id_country"))).selectByValue("21");
    }

    public void sendMobile() {
        driver.findElement(By.id("phone_mobile")).clear();
        driver.findElement(By.id("phone_mobile")).sendKeys(mobile);
    }

    public void clickSubmitAccount() {
        WebElement phone = driver.findElement(By.id("phone_mobile"));
        js.executeScript("arguments[0].scrollIntoView();", phone);
        driver.findElement(By.id("submitAccount")).click();
    }

    public void clickProcessAddress() {
        WebElement message = driver.findElement(By.name("message"));
        js.executeScript("arguments[0].scrollIntoView();", message);
        driver.findElement(By.name("processAddress")).click();
    }

    public void acceptTerms() {
        WebElement checkbox = driver.findElement(By.id("cgv"));
        checkbox.click();
    }

    public void clickProcessCarrier() {
        driver.findElement(By.name("processCarrier")).click();
    }

    public boolean isPaymentMethodPresent() {
        try {
            WebElement payment = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("cart_ref")));
            if (payment.getText().contains("demo_7")) {
                return true;
            }
            return false;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
