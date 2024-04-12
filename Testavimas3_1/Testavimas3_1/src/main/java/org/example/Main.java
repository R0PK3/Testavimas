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

        //3 Pasirinkti widgets kortele
        WebElement widgets = driver.findElement(By.xpath("//div[@class ='card mt-4 top-card']//h5[(text() = 'Widgets')]"));
        widgets.click();

        //4 Pasirinkti meniu punkta "Progress Bar" (javascript scroll)
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,350)", "");

        WebElement progressBar = driver.findElement(By.xpath("//span[text() = 'Progress Bar']"));
        progressBar.click();

        //5 Spausti mygtuka Start

        WebElement start = driver.findElement(By.id("startStopButton"));
        start.click();

        //6 sulaukti kol bus 100% ir spausti reset

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'progress-bar') and . = '100%']")));
        sleep(50);
        WebElement reset = driver.findElement(By.id("resetButton"));
        reset.click();

        //7 Isitikinti kad progreso eilute tuscia

        WebElement progress_bar = driver.findElement(By.className("progress-bar"));
        assert(Objects.equals(progress_bar.getAttribute("aria-valuenow"), "0"));

        driver.quit();
    }
}