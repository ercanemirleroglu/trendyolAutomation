package base;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class BaseTest {

    private WebDriverWait webDriverWait;
    private String browser;
    private String driverPath;
    private String key;
    public final String chrome = "Chrome";
    public final String firefox = "Firefox";
    public final String chromeDriver = "\\chromedriver.exe";
    public final String firefoxDriver = "\\geckodriver.exe";
    public final String chromeKey = "webdriver.chrome.driver";
    public final String firefoxKey = "webdriver.gecko.driver";
    public String url = "https://www.trendyol.com/";

    protected static final Logger log = Logger.getLogger("Log: ");


    public void chooseBrowser(String _browser){
        this.browser=_browser;
        if (this.browser.equalsIgnoreCase(chrome)){
            this.driverPath = chromeDriver;
            this.key = chromeKey;
            this.browser = chrome;

        }else if(this.browser.equalsIgnoreCase(firefox)){
            this.driverPath = firefoxDriver;
            this.key = firefoxKey;
            this.browser = firefox;
        }
    }

    public String sendDriverPath(){
        this.driverPath = System.getProperty("user.dir")+"\\drivers"+this.driverPath;
        return this.driverPath;
    }

    public void getDriver(String _browser){
        chooseBrowser(_browser);
        String path = sendDriverPath();
        System.setProperty(this.key, path);
        System.setProperty("selenide.browser", this.browser);
        Selenide.open(url);
        this.waitUntilUrlContains("https://www.trendyol.com/");
    }

    public void switchToFrame() throws InterruptedException {

        WebDriver popup = null;
        Set<String> handleSet = WebDriverRunner.getWebDriver().getWindowHandles();
        Iterator<String> windowIterator = handleSet.iterator();

        for (String s : handleSet) {

            String windowHandle = s;
            popup = WebDriverRunner.getWebDriver().switchTo().window(windowHandle);

        }
        WebDriverRunner.getWebDriver().switchTo().activeElement();
        sleep(1000);
    }

    protected boolean waitUntilUrlContains(String expectedValue) {
        try {
            Wait<WebDriver> wait = (new FluentWait(WebDriverRunner.getWebDriver())).withTimeout(Duration.ofSeconds(30L)).pollingEvery(Duration.ofMillis(100L)).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
            boolean urlExists = (Boolean)wait.until(ExpectedConditions.urlContains(expectedValue));
            if (urlExists) {
                log.info(expectedValue + " bulundu");
            }
            return true;
        } catch (Exception var4) {
            log.warning(expectedValue+ " bulunamadı");
            return false;
        }
    }

    public void imageControl() throws InterruptedException {
        sleep(5000);
        List<WebElement> images = WebDriverRunner.getWebDriver().findElements(By.tagName("img"));
        WebElement image;

        sleep(8000);
        for (int i = 0; i < images.size(); i++) {
            image = images.get(i);
            String src;
            try{
                src = image.getAttribute("src");
            }catch(org.openqa.selenium.StaleElementReferenceException ex){
                log.warning((i + 1) + "Image yuklenemedi");
            }
           try{
               if (image.isEnabled()) {
                   //src = image.getAttribute("src");
                   Object result = ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript(
                           "return arguments[0].complete && " +
                                   "typeof arguments[0].naturalWidth != \"undefined\" && " +
                                   "arguments[0].naturalWidth > 0", image);
                   src = image.getAttribute("src");
                   if (result instanceof Boolean) {
                       log.info((i + 1) + ". Image: "+src+" yuklendi");
                   } else {
                       log.warning((i + 1) + "Image "+src+" yuklenemedi");
                   }}
            }catch(org.openqa.selenium.StaleElementReferenceException ex){
               log.warning((i + 1) + ".Image yuklenemedi");
           }

        }
    }

    public int randomNumber(int size){
        Random rnd = new Random();
        return rnd.nextInt(size-1);
    }


}
