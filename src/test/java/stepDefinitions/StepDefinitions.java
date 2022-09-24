package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import pages.BeymenPages;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReasuableMethods;

import java.io.*;


public class StepDefinitions {


    BeymenPages beymenPages = new BeymenPages();


    @Given("Kullanıcı Beymen anasayfasına gider")
    public void kullanıcı_beymen_anasayfasına_gider() throws InterruptedException {
        Driver.getDriver().get(ConfigReader.getProperty("beymenUrl"));


    }

    @When("Ana sayfanın açıldığı kontrol edilir.")
    public void ana_sayfanın_açıldığı_kontrol_edilir() {
        String sayfaUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(sayfaUrl.equals(ConfigReader.getProperty("beymenUrl")));
    }


    @When("Arama kutucuğuna “şort” kelimesi girilir.")
    public void arama_kutucuğuna_şort_kelimesi_girilir() throws IOException, InterruptedException {

        String dosyaYolu = "src/test/resources/beymen.xlsx";
        FileInputStream fis = new FileInputStream(dosyaYolu);

        Workbook workbook = WorkbookFactory.create(fis);
        String actualData = workbook
                .getSheet("Sayfa1")
                .getRow(0)
                .getCell(0)
                .toString();
        System.out.println(actualData);

        beymenPages.aramaAlani.sendKeys(actualData);
        ReasuableMethods.waitFor(1);


    }

    @When("- Arama kutucuğuna girilen “şort” kelimesi silinir.")
    public void arama_kutucuğuna_girilen_şort_kelimesi_silinir() throws InterruptedException {

        beymenPages.aramaAlani.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        ReasuableMethods.waitFor(1);


    }

    @When("- Arama kutucuğuna “gömlek” kelimesi girilir.")
    public void arama_kutucuğuna_gömlek_kelimesi_girilir() throws IOException, InterruptedException {
        String dosyaYolu = "src/test/resources/beymen.xlsx";
        FileInputStream fis = new FileInputStream(dosyaYolu);

        Workbook workbook = WorkbookFactory.create(fis);
        String actualData = workbook
                .getSheet("Sayfa1")
                .getRow(0)
                .getCell(1)
                .toString();
        System.out.println(actualData);

        beymenPages.aramaAlani.sendKeys(actualData);
        ReasuableMethods.waitFor(1);


    }

    @When("- Klavye üzerinden “enter” tuşuna bastırılır")
    public void klavye_üzerinden_enter_tuşuna_bastırılır() throws InterruptedException {

        beymenPages.aramaAlani.sendKeys(Keys.ENTER);
        ReasuableMethods.waitFor(1);
        ReasuableMethods.JSEScrollBy();
        ReasuableMethods.waitFor(1);

    }

    @When("- Sonuca göre sergilenen ürünlerden rastgele bir ürün seçilir.")
    public void sonuca_göre_sergilenen_ürünlerden_rastgele_bir_ürün_seçilir() {

        beymenPages.KadınGömleği.click();
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollBy(0,350)");


    }

    @When("- Seçilen ürünün ürün bilgisi ve tutar bilgisi txt dosyasına yazılır.")
    public void seçilen_ürünün_ürün_bilgisi_ve_tutar_bilgisi_txt_dosyasına_yazılır() throws FileNotFoundException {

        String txtDosya = "src/test/resources/Beymen.txt";

        try {

            FileWriter fileWriter = new FileWriter(txtDosya, true);

            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.append(beymenPages.Gömlekmarka.getText() + "\n");

           //String model = "Tyratop Bej Dikiş Detaylı Keten Bluz";

            writer.append(beymenPages.KadınGömlek.getText() + "\n");


            // String Beden = "M/L";

            writer.append(beymenPages.SecilenGömlekBeden.getText() + "\n");

            //String fiyat = "850 TL";

            writer.append(beymenPages.GömlekFiyatı.getText() + "\n");

            writer.close();

        } catch (Exception e) {
            e.printStackTrace();


        }
    }
    @When("- Seçilen ürün sepete eklenir.")
    public void seçilen_ürün_sepete_eklenir() throws InterruptedException {

        beymenPages.SecilenGömlekBeden.click();
        ReasuableMethods.waitFor(1);

        beymenPages.SepeteEkle.click();
        ReasuableMethods.waitFor(1);

        beymenPages.Sepetim.click();


    }

    @When("- Ürün sayfasındaki fiyat ile sepette yer alan ürün fiyatının doğruluğu karşılaştırılır.")
    public void ürün_sayfasındaki_fiyat_ile_sepette_yer_alan_ürün_fiyatının_doğruluğu_karşılaştırılır() {


        Assert.assertEquals("Urun fiyatlari esit degil",beymenPages.SepettenöncekiGömlekFiyatı.getText(), beymenPages.SepettenSonrakiGömlekFiyatı.getText());
    }

    @When("- Adet arttırılarak ürün adedinin iki olduğu doğrulanır.")
    public void adet_arttırılarak_ürün_adedinin_olduğu_doğrulanır() {

        beymenPages.AdetButonu.click();
        beymenPages.AdetArtırma.click();
        Assert.assertTrue(beymenPages.SepettekiMiktar.isDisplayed());
        ReasuableMethods.waitFor(5);


    }

    @When("- Ürün sepetten silinerek sepetin boş olduğu kontrol edilir.")
    public void ürün_sepetten_silinerek_sepetin_boş_olduğu_kontrol_edilir() {
        beymenPages.SilButonu.click();
        Assert.assertTrue(beymenPages.SepetteUrunYok.isDisplayed());
        ReasuableMethods.waitFor(5);

        //Driver.CloseDriver();


    }
}
