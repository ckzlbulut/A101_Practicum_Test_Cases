package Task;

import Utils.BasicStaticDriver;
import Utils.Tools;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io
        .FileUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

public class Task extends BasicStaticDriver {
    public static void main(String[] args) throws IOException, AWTException {

        Tools tools = new Tools();
        driver.get("https://www.a101.com.tr/");

        Robot rbt=new Robot();
        rbt.keyPress(KeyEvent.VK_META);
        rbt.keyPress(KeyEvent.VK_TAB);
        rbt.keyRelease(KeyEvent.VK_TAB);
        stay(1);
        rbt.keyRelease(KeyEvent.VK_META);  /** (25-29) satırlar macOS'ta robotun chrome penceresinde çalışması için eklendi*/

        rbt.keyPress(KeyEvent.VK_ESCAPE);
        rbt.keyRelease(KeyEvent.VK_ESCAPE);

        WebElement acceptCookies = driver.findElement(By.xpath("//*[text()='Kabul Et']"));
        tools.clickFunction(acceptCookies);

        WebElement giyimAksesuar = driver.findElement(By.cssSelector("[href='/giyim-aksesuar/']"));
        WebElement dizaltiCorap = driver.findElement(By.cssSelector("[href='/giyim-aksesuar/dizalti-corap/']"));
        tools.clickAfterMouseOver(giyimAksesuar, dizaltiCorap);

        List<WebElement> dizaltiCoraplar = driver.findElements(By.cssSelector("[class='name']"));
        for (WebElement dC : dizaltiCoraplar)
        {
            if (dC.getText().toLowerCase().contains("siyah"))
            {
                dC.click();
            }
            break;
        }

        WebElement sepeteEkle = driver.findElement(By.cssSelector("[class='icon-sepetekle']"));
        tools.clickFunction(sepeteEkle);

        WebElement sepet = driver.findElement(By.xpath("(//*[@title='Sepeti Görüntüle'])[2]"));
        tools.clickFunction(sepet);

        WebElement sepetiOnayla = driver.findElement(By.xpath("(//a[@title='Sepeti Onayla'])[2]"));
        tools.clickFunction(sepetiOnayla);

        WebElement uyeliksizDevam = driver.findElement(By.cssSelector("[title='ÜYE OLMADAN DEVAM ET']"));
        tools.clickFunction(uyeliksizDevam);

        WebElement email = driver.findElement(By.cssSelector("[name='user_email']"));
        tools.sendKeysFunction(email, "ckzlbulut@gmail.com");

        WebElement devamEt = driver.findElement(By.cssSelector("[class='button block green']"));
        tools.clickFunction(devamEt);

        WebElement plusicon = driver.findElement(By.cssSelector("[class='new-address js-new-address']"));
        tools.clickFunction(plusicon);

        WebElement adresBaslik = driver.findElement(By.cssSelector("[placeholder='Ev adresim, iş adresim vb.']"));
        tools.sendKeysFunction(adresBaslik, "EV");

        WebElement isim = driver.findElement(By.cssSelector("[name='first_name']"));
        tools.sendKeysFunction(isim, "Caglar");

        WebElement soyisim = driver.findElement(By.cssSelector("[name='last_name']"));
        tools.sendKeysFunction(soyisim, "KIZILBULUT");

        WebElement telefon = driver.findElement(By.cssSelector("[name='phone_number']"));
        tools.sendKeysFunction(telefon, "5451252525");

        WebElement city = driver.findElement(By.className("js-cities"));
        tools.selectFunctionByText(city, "BATMAN");

        WebElement ilce = driver.findElement(By.className("js-township"));
        tools.selectFunctionByText(ilce, "MERKEZ");

        WebElement mahalleler = driver.findElement(By.xpath("//*[contains(@name,'district')]"));
        wait.until(ExpectedConditions.elementToBeClickable(mahalleler));
        Select dd3 = new Select(mahalleler);
        List<WebElement> elements = dd3.getOptions();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//*[contains(@name,'district')]//option"), 100));
        dd3.selectByVisibleText("SİTE MAH");

        WebElement adres = driver.findElement(By.className("js-address-textarea"));
        int randomNum = (int) (Math.random() * 1000);
        String strRandomNum = String.valueOf(randomNum); /**Otomasyon birebir aynı adresle defalarca çalıştırıldığında tahminimce DB kaynaklı bir sorun içerdiği için eklendi */
        tools.sendKeysFunction(adres, "Site mahallesi blok" + strRandomNum + " daire 12");

        WebElement adresKayit = driver.findElement(By.xpath("//button[@class='button green js-set-country js-prevent-emoji']"));
        tools.clickFunction(adresKayit);

        driver.navigate().refresh();  /** StaleElement hatası için eklendi */
        WebElement kargo = driver.findElement(By.xpath("(//*[@class='radio'])[3]"));
        tools.clickFunction(kargo);

        WebElement kayitDevam = driver.findElement(By.cssSelector("[class='button block green js-proceed-button']"));
        tools.clickFunction(kayitDevam);

        WebElement assertPoint = driver.findElement(By.cssSelector("[class='card-detail']"));
        tools.verifyContainsText(assertPoint, "başka bir kart ile ödemek istiyorum");

        TakesScreenshot ss=(TakesScreenshot) driver;
        File hafizadaliHali=ss.getScreenshotAs(OutputType.FILE);
        LocalTime dr=LocalTime.now();
        String zmn=dr.toString();
        FileUtils.copyFile(hafizadaliHali,new File("ekranGörüntüleri/"+zmn+"ÖdemeBilgisi.png"));

        stayQuit();
    }
}
