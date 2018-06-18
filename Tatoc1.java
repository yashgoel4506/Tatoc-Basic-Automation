package Tatoc;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Tatoc1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.get("http://10.0.1.86/tatoc");
		
		WebElement search = driver.findElement(By.linkText("Basic Course"));
		search.click();
		
		driver.findElement(By.className("greenbox")).click();
		
		driver.switchTo().frame("main");
		String box1 = new String();
		box1 = driver.findElement(By.id("answer")).getAttribute("class");
		String box2 = "";
		driver.switchTo().frame("child");
		box2 = driver.findElement(By.id("answer")).getAttribute("class");
		while(!box1.equals(box2))
		{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("main");
			driver.findElement(By.linkText("Repaint Box 2")).click();
			driver.switchTo().frame("child");
			box2 = driver.findElement(By.id("answer")).getAttribute("class");
		}
		driver.switchTo().defaultContent();
		driver.switchTo().frame("main");
		driver.findElement(By.linkText("Proceed")).click();
		
		WebElement to = driver.findElement(By.id("dropbox"));
		WebElement from = driver.findElement(By.id("dragbox"));
		Actions action = new Actions(driver);
		action.dragAndDrop(from, to).build().perform();
		driver.findElement(By.linkText("Proceed")).click();
		
		driver.findElement(By.linkText("Launch Popup Window")).click();
		String main = driver.getWindowHandle();
		Set<String> popup = driver.getWindowHandles();
		Iterator<String> i = popup.iterator();
		String child = " ";
		while(i.hasNext())
		{
			child = i.next();
		}
		driver.switchTo().window(child);
		driver.findElement(By.id("name")).sendKeys("Yash Goel");
		driver.findElement(By.id("submit")).click();
		driver.switchTo().window(main);
		driver.findElement(By.linkText("Proceed")).click();
		
		driver.findElement(By.linkText("Generate Token")).click();
		String token = driver.findElement(By.id("token")).getText();
		String tokenid = token.substring(7,token.length());
		Cookie cookie = new Cookie("Token", tokenid);
		driver.manage().addCookie(cookie);
		driver.findElement(By.linkText("Proceed")).click();

	}

}
