package Siparis;

import base.BaseTest;
import base.LoginPage;
import base.MainPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import users.User;

public class Siparis_Olusturma extends BaseTest {
    User user = new User();
    @BeforeMethod
    public void BeforeTest(){
        String browser="Chrome";
        getDriver(browser);
    }

    @Test(enabled = true, description= "Butiklerin gelip gelmediginin kontrolu")
    public void TS0001_Butik_Kontrol() throws InterruptedException {
        LoginPage loginPage = new LoginPage();
        MainPage mainPage = new MainPage();
        loginPage.login(user.ercanUser, user.ercanPass);
        switchToFrame();
        mainPage.chooseCategoryHeader("KADIN")
                .chooseCategoryHeader("ERKEK")
                .chooseCategoryHeader("ÇOCUK")
                .chooseCategoryHeader("AYAKKABI & ÇANTA")
                .chooseCategoryHeader("SAAT & AKSESUAR")
                .chooseCategoryHeader("KOZMETİK")
                .chooseCategoryHeader("EV & YAŞAM")
                .chooseCategoryHeader("ELEKTRONİK")
                .chooseCategoryHeader("SÜPERMARKET");
    }

    @Test(enabled = true, description= "Bir urunun sepete eklenmesi")
    public void TS0002_Sepete_Ekleme() throws InterruptedException {

        LoginPage loginPage = new LoginPage();
        MainPage mainPage = new MainPage();
        loginPage.login(user.ercanUser, user.ercanPass);
        switchToFrame();
        mainPage.chooseCategoryHeader("ELEKTRONİK")
                .chooseRandomBoutique()
                .chooseRandomProduct()
                .clickAddToCartButton();
    }


}
