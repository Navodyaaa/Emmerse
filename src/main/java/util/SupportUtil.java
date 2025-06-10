package util;

import static util.AppConstant.ExceptionMessage.UNDEFINED_BROWSER_TYPE;

import exception.BrowserException;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.Map;
import java.util.function.Supplier;
import org.openqa.selenium.WebDriver;

public class SupportUtil {

  public static WebDriver getDriver(String browser) {
    Map<String, Supplier<WebDriver>> driverMap =
        Map.of(
            "Chrome", WebDriverManager.chromedriver()::create,
            "Firefox", WebDriverManager.firefoxdriver()::create,
            "Edge", WebDriverManager.edgedriver()::create,
            "Safari", WebDriverManager.safaridriver()::create);

    return driverMap
        .getOrDefault(
            browser,
            () -> {
              throw new BrowserException(StringUtil.formatString(UNDEFINED_BROWSER_TYPE, browser));
            })
        .get();
  }
}
