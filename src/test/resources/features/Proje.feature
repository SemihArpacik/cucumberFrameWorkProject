Feature: Beymen

  @Beymen
  Scenario: Tc01

    Given Kullanıcı Beymen anasayfasına gider
    When Ana sayfanın açıldığı kontrol edilir.
    When Arama kutucuğuna “şort” kelimesi girilir.
    When - Arama kutucuğuna girilen “şort” kelimesi silinir.
    When - Arama kutucuğuna “gömlek” kelimesi girilir.
    When - Klavye üzerinden “enter” tuşuna bastırılır
    When - Sonuca göre sergilenen ürünlerden rastgele bir ürün seçilir.
    When - Seçilen ürünün ürün bilgisi ve tutar bilgisi txt dosyasına yazılır.
    When - Seçilen ürün sepete eklenir.
    When - Ürün sayfasındaki fiyat ile sepette yer alan ürün fiyatının doğruluğu karşılaştırılır.
    When - Adet arttırılarak ürün adedinin iki olduğu doğrulanır.
    When - Ürün sepetten silinerek sepetin boş olduğu kontrol edilir.

