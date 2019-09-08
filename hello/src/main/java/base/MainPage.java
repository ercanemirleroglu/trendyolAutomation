package base;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage extends BaseTest{
    BaseTest baseTest = new BaseTest();

    ElementsCollection DIV_BUTIK_XPATH = $$(By.xpath("//div[@class='component-list component-big-list']/article[@class='component-item']/a"));
    ElementsCollection DIV_PRODUCT_XPATH = $$(By.xpath("//div[@id='boutiqueDetailContainer']//ul/li//a"));
    SelenideElement BTN_ADDCART_XPATH = $(By.xpath("//div[text()='Sepete Ekle']/.."));
    public MainPage chooseCategoryHeader(String category) throws InterruptedException {
        SelenideElement se = $(By.xpath("//a[text()='"+category+"']"));
        String _url = se.attr("href").toLowerCase();
        se.click();
        waitUntilUrlContains(_url);
        imageControl();
        return this;
    }

    public MainPage chooseRandomBoutique(){
        int index = randomNumber(DIV_BUTIK_XPATH.size());
        SelenideElement se = DIV_BUTIK_XPATH.get(index);
        String _url = se.attr("href").toLowerCase();
        se.scrollTo().shouldBe(Condition.visible).click();
        waitUntilUrlContains(_url);
        return this;
    }

    public MainPage chooseRandomProduct(){
        int index = randomNumber(DIV_PRODUCT_XPATH.size());
        SelenideElement se = DIV_PRODUCT_XPATH.get(index);
        String _url = se.attr("href").toLowerCase();
        se.scrollTo().shouldBe(Condition.visible).click();
        waitUntilUrlContains(_url);
        return this;
    }

    public MainPage clickAddToCartButton(){
        BTN_ADDCART_XPATH.shouldBe(Condition.visible).click();
        return this;
    }


}
