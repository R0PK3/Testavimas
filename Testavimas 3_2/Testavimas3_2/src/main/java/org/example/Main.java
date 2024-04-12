package org.example;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.util.concurrent.TimeUnit;


import java.time.Duration;
import java.util.List;
import java.util.Objects;

import static java.lang.Thread.sleep;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("geckodriver", "C:\\Users\\Ropke\\Desktop\\Testavimas\\draiveris\\geckodriver.exe");

        FirefoxDriver driver = new FirefoxDriver();
        Duration timeout = Duration.ofSeconds(20);
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        driver.manage().window().maximize();

        //1 Atsidaryti tinklalapi
        driver.get("https://demoqa.com/");

        //implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //2 Uzdaryti cookies sutikimo langa
        WebElement cookies = driver.findElement(By.xpath("//p[@class = 'fc-button-label']"));
        cookies.click();

        //3 Pasirinkti elements kortele
        WebElement elements = driver.findElement(By.xpath("//div[@class ='card-body']//h5[(text() = 'Elements')]"));
        elements.click();

        //4 Pasirinkti meniu punkta "web tables" (javascript scroll)

        WebElement webtables = driver.findElement(By.xpath("//span[text() = 'Web Tables']"));
        webtables.click();

        //5 prideti pakankamai elementu, kad atsirastu antras puslapis puslapiavime

        WebElement addButton = driver.findElement(By.id("addNewRecordButton"));
        WebElement totalPages = driver.findElement(By.xpath("//span[@class = '-totalPages']"));
        while(Objects.equals(totalPages.getText(), "1")){
            addButton.click();
            driver.findElement(By.id("firstName")).sendKeys("A");
            driver.findElement(By.id("lastName")).sendKeys("B");
            driver.findElement(By.id("userEmail")).sendKeys("a@b.com");
            driver.findElement(By.id("age")).sendKeys("40");
            driver.findElement(By.id("salary")).sendKeys("1700");
            driver.findElement(By.id("department")).sendKeys("C");
            driver.findElement(By.id("submit")).click();
        }

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)", "");
        //6 Pasirinkti antra puslapi paspaudus Next

        driver.findElement(By.xpath("//div[@class = '-next']/button")).click();

        //7 Istrinti elementa antrajame puslapyje

        driver.findElement(By.id("delete-record-11")).click();

        //8 Isitikinti, kad automatiskai puslapiavimas perkeliamas i pirmaji puslapi ir kad psl skaicius sumazejo iki 1

        assert(Objects.equals(totalPages.getText(), "1"));
       // assert(Objects.equals(driver.findElement(By.xpath("//input[contains(@aria-label, 'jump to page')]")).getAttribute("value"), "2"));
        Assert.assertEquals("1", driver.findElement(By.xpath("//input[contains(@aria-label, 'jump to page')]")).getAttribute("value"));
        driver.quit();
    }
}