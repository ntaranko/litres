package ru.litres.singleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Singleton {
    private static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(Singleton.class);

    private Singleton() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();

            WebDriver.Options options = driver.manage();
            WebDriver.Timeouts timeouts = options.timeouts();
            timeouts.implicitlyWait(Duration.ofSeconds(10));
            driver
                    .manage()
                    .window()
                    .maximize();
        }
        return driver;
    }

    public static boolean ifElementExists(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            logger.error(String.format("%s element doesn't exists", locator));
            return false;
        }
    }

    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
