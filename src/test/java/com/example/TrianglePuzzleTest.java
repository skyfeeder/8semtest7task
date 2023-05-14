package com.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
//import org.junit.Test;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TrianglePuzzleTest {

    @Test
    public void Test() {

        boolean isDisplayedAnswersLink = false;
        boolean isDisplayedHideAnswersButton = false;

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.get("https://playground.learnqa.ru/puzzle/triangle");

        WebElement giveUpButton = driver.findElement(By.xpath("//button[contains(text(),'Я сдаюсь')]"));
        boolean isDisplayedGiveUpButton = giveUpButton.isDisplayed();
        if (isDisplayedGiveUpButton) {
            giveUpButton.click();

            WebElement answersLink = driver.findElement(By.linkText("Ссылка на ответы"));

            isDisplayedAnswersLink = answersLink.isDisplayed();
            if (isDisplayedAnswersLink) {

                System.out.println("Ссылка на ответы найдена");

                WebElement hideAnswersButton = driver.findElement(By.xpath("//button[contains(text(),'Спрятать ответы')]"));

                isDisplayedHideAnswersButton = hideAnswersButton.isDisplayed();

            }
        }

        assertTrue(isDisplayedGiveUpButton);
        assertTrue(isDisplayedHideAnswersButton);
        driver.quit();
    }
}