package Utils;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class Tools extends BasicStaticDriver{

    public static WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));

    public  void clickFunction(WebElement element)
    {
        elementToBeClickable(element);
        element.click();
    }

    public void sendKeysFunction(WebElement element , String text)
    {
        elementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    public void verifyContainsText(WebElement element, String text)
    {
        elementToBeVisible(element);
        Assert.assertTrue(element.getText().toLowerCase().contains(text.toLowerCase()));
    }


    public void selectFunctionByText(WebElement element , String text)
    {
        elementToBeVisible(element);
        Select select=new Select(element);
        select.selectByVisibleText(text);
    }

    public void clickAfterMouseOver(WebElement mouseOver,WebElement element)
    {
        elementToBeClickable(mouseOver);
        Actions actios = new Actions(driver);
        Action action = actios.moveToElement(mouseOver).build();
        action.perform();

        clickFunction(element);
    }


    public void scrollToElement(WebElement element)
    {
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }


    public void elementToBeClickable(WebElement element)
    {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    public void elementToBeVisible(WebElement element)
    {
        wait.until(ExpectedConditions.visibilityOf(element));
    }




}
