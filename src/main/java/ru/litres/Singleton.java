package ru.litres;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Singleton {
    private static WebDriver driver;

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

    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
