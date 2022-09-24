package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class BeymenPages {

    public BeymenPages() {

        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy (xpath = "//input[@class='default-input o-header__search--input']")
    public WebElement aramaAlani;

    @FindBy (xpath = "(//*[@href='/p_wabi-sabi-tyratop-bej-dikis-detayli-keten-bluz_997356'])[1]")
    public WebElement KadınGömleği;

    @FindBy (xpath = "(//span[text()='Tyratop Bej Dikiş Detaylı Keten Bluz'])[2]")
    public WebElement KadınGömlek;


    @FindBy (xpath = "(//span[@class='m-variation__item'])[2]")
    public WebElement SecilenGömlekBeden;

    @FindBy (xpath = "//button[@class='m-addBasketFavorite__basket btn']")
    public WebElement SepeteEkle;

    @FindBy (xpath = "//a[text()='Wabi Sabi']")
    public WebElement Gömlekmarka;

    @FindBy (xpath = "//*[@id='priceNew']")
    public WebElement GömlekFiyatı;

    @FindBy (xpath = "//span[@class='m-productPrice__salePrice']")
    public WebElement SepettenöncekiGömlekFiyatı;

    @FindBy (xpath = "(//span[@class='o-header__userInfo--text'])[3]")
    public WebElement Sepetim;

    @FindBy (xpath = "(//span[@class='m-orderSummary__value'])[3]")
    public WebElement SepettenSonrakiGömlekFiyatı;

    @FindBy (xpath = "//select[@id='quantitySelect0-key-0']")
    public WebElement AdetButonu;

    @FindBy (xpath = "//option[@value='2']")
    public WebElement AdetArtırma;

    @FindBy (xpath = "//select[@id='quantitySelect0-key-0']")
    public WebElement SepettekiMiktar;

    @FindBy (xpath = "//button[@id='removeCartItemBtn0-key-0']")
    public WebElement SilButonu;

    @FindBy (xpath = "(//strong[@class='m-empty__messageTitle'])[1]")
    public WebElement SepetteUrunYok;




}