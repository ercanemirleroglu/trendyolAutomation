package base;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.Thread.sleep;

public class LoginPage extends BaseTest{
    SelenideElement A_POPUPKAPAT_XPATH = $(By.xpath("//a[@title='Close']"));
    SelenideElement SPAN_GIRISYAP_XPATH = $(By.xpath("//span[text()='Giriş Yap']"));
    SelenideElement DIV_GIRISYAP_XPATH = $(By.xpath("//div[text()='Giriş Yap']"));
    SelenideElement INP_EMAIL_XPATH = $(By.xpath("//div[@tabindex='-1']//input[@name='email']"));
    SelenideElement INP_PASS_XPATH = $(By.xpath("//div[@tabindex='-1']//input[@name='password']"));
    SelenideElement A_LOGIN_XPATH = $(By.xpath("//div[@tabindex='-1']//a[@id='loginSubmit']"));

    //password
    public LoginPage login(String user, String pass) throws InterruptedException {
        popupClose();
        signIn(user, pass);
        return this;
    }

    public void popupClose() throws InterruptedException {
        sleep(1000);
        switchToFrame();
        A_POPUPKAPAT_XPATH.shouldBe(Condition.visible).click();
    }

    public void signIn(String user, String pass) throws InterruptedException {
        //sleep(1000);
        switchToFrame();
        SPAN_GIRISYAP_XPATH.hover();
        sleep(1000);
        DIV_GIRISYAP_XPATH.shouldBe(Condition.visible).click();
        switchToFrame();
        INP_EMAIL_XPATH.sendKeys(user);
        INP_PASS_XPATH.sendKeys(pass);
        A_LOGIN_XPATH.click();
    }

}
