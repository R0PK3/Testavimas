package org.example;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("geckodriver", "C:\\Users\\Ropke\\Desktop\\Testavimas\\draiveris\\geckodriver.exe");
        FirefoxDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();

       // try {

            //1 Atsidaryti tinklalapi
            driver.get("https://demowebshop.tricentis.com/");
            Thread.sleep(1000);

            //2 Spausti Gift Cards kairiajame meniu
            WebElement giftCards = driver.findElement(By.xpath("//ul[@class = 'list']//a[@href='/gift-cards']"));
            giftCards.click();
            Thread.sleep(1000);

            //3 Pasirinkti preke kurios kaina > 99
            WebElement elementPrice = driver.findElement(By.xpath("//span[. > 99]/../../div[@class='buttons']/input"));
            elementPrice.click();
            Thread.sleep(1000);

            //4 Supildyti laukus
            WebElement fillRecipient = driver.findElement(By.id("giftcard_4_RecipientName"));
            fillRecipient.sendKeys("Test1");
            Thread.sleep(1000);

            WebElement fillName = driver.findElement(By.id("giftcard_4_SenderName"));
            fillName.sendKeys("Test2");
            Thread.sleep(1000);

            //5 Ivesti qty 5000
            WebElement fillQuantity = driver.findElement(By.id("addtocart_4_EnteredQuantity"));
            fillQuantity.clear();
            fillQuantity.sendKeys("5000");
            Thread.sleep(1000);

            //6 Add to cart mygtukas
            WebElement addToCard = driver.findElement(By.xpath("//input[contains(@class,'add-to-cart-button')]"));
            addToCard.click();
            Thread.sleep(1000);

            //7 Add to wishlist
            WebElement addToWishList = driver.findElement(By.xpath("//input[contains(@class,'add-to-wishlist-button')]"));
            addToWishList.click();
            Thread.sleep(1000);

            //8 Spausti jewlry kairiam meniu
            WebElement Jewel = driver.findElement(By.xpath("//ul[@class='list']//a[@href='/jewelry']"));
            Jewel.click();
            Thread.sleep(1000);

            //9 Create your own jewelry nuoroda
            WebElement clickOnLink = driver.findElement(By.xpath("//a[@href='/create-it-yourself-jewelry']"));
            clickOnLink.click();
            Thread.sleep(1000);

            //10.1 Select material
            WebElement dropdown = driver.findElement(By.xpath("//select"));
            Select dropdownSelect = new Select(dropdown);
            dropdownSelect.selectByIndex(2);
            Thread.sleep(1000);

            //10.2 Set length
            WebElement lengthInCm = driver.findElement(By.xpath("//input[@class ='textbox']"));
            lengthInCm.sendKeys("80");
            Thread.sleep(1000);

            //10.3 Set Star
            WebElement pendant = driver.findElement(By.xpath("//label[contains(text(),'Star ')]/../input"));
            pendant.click();
            Thread.sleep(1000);

            //11 Qty 11
            WebElement fillQuantity2 = driver.findElement(By.xpath("//input[@class ='qty-input']"));
            fillQuantity2.clear();
            fillQuantity2.sendKeys("26");

            //12 AddToCart
            WebElement addToCard2 = driver.findElement(By.xpath("//input[contains(@class,'add-to-cart-button')]"));
            addToCard2.click();
            Thread.sleep(1000);

            //13 AddToWishlist
            WebElement addToWishList2 = driver.findElement(By.xpath("//input[contains(@class,'add-to-wishlist-button')]"));
            addToWishList2.click();
            Thread.sleep(1000);

            //14 Wishlist Nuoroda
            WebElement clickOnLink2 = driver.findElement(By.xpath("//a[@href='/wishlist']"));
            clickOnLink2.click();
            Thread.sleep(1000);

            //15 Varneles
            List<WebElement> listOfItems = driver.findElements(By.xpath("//td[@class='add-to-cart']/input"));
            for (WebElement checkbox : listOfItems) {
                checkbox.click();
            }
            Thread.sleep(1000);

            //16 AddToCart mygtukas
            WebElement addToCard3 = driver.findElement(By.xpath("//input[contains(@class,'wishlist-add-to-cart-button')]"));
            addToCard3.click();
            Thread.sleep(1000);

            //17 Patvirtinti kad reiksme tinkama
            String checkPrice = driver.findElement(By.xpath("//span[@class='product-price']")).getText();
            Assert.assertEquals(checkPrice, "1002600.00");
            Thread.sleep(1000);

            driver.quit();

      //  } catch (Exception e) {
      //      e.printStackTrace();
      //  }
    }
}

