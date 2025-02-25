package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class Authentication {
    private WebDriver driver;

    public Authentication(WebDriver driver) {
        this.driver = driver;
    }

    public void login() {
        String path = "cookie.txt";
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            String value = lines.findFirst().orElse("File rỗng");
            Cookie cookie = new Cookie(".AspNet.Cookies", value);
            driver.manage().addCookie(cookie);
            driver.navigate().refresh();
        } catch (IOException e) {
            System.err.println("Lỗi đọc file: " + e.getMessage());
        }
    }
}
