package com.qa.fb.keyword.engine;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.fb.keyword.base.BaseClass;


public class KeyWordEngine {
	public WebDriver driver;
	public Properties properties;

	public static Workbook book;
	public static Sheet sheet;

	public BaseClass base;
	public WebElement element;

	public final String SCENARIO_SHEET_PATH = "C:\\Users\\Admin\\Desktop\\KeywordDriven.xlsx";

	public void startExecution(String sheetName) throws InvalidFormatException {

		FileInputStream file = null;
		try {
			file = new FileInputStream(SCENARIO_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		int k = 0;
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			try {
				String locatorType = sheet.getRow(i + 1).getCell(k + 1).toString().trim();
				String locatorColumnValue = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
				String action = sheet.getRow(i + 1).getCell(k + 3).toString().trim();
				String value = sheet.getRow(i + 1).getCell(k + 4).toString().trim();
				switch (action) {
				case "Open browser":
					base = new BaseClass();
					properties = base.initializeProperties();
					if (value.isEmpty() || value.equals("NA")) {
						driver = base.initializeDriver(properties.getProperty("browser"));
					} else {
						driver = base.initializeDriver(value);
					}
					break;
				case "Enter url":
					if (value.isEmpty() || value.equals("NA")) {
						driver.get(properties.getProperty("url"));
					} else {
						driver.get(value);
					}
					break;
				case "quit":
					driver.quit();
					break;
				default:
					break;
				}
				switch (locatorType) {
				case "id":
					element = driver.findElement(By.id(locatorColumnValue));
					if (action.equalsIgnoreCase("sendkeys")) {
						element.clear();
						element.sendKeys(value);
					} else if (action.equalsIgnoreCase("click")) {
						element.click();
					} else if (action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
					} else if (action.equalsIgnoreCase("getText")) {
						String elementText = element.getText();
						System.out.println("text from element : " + elementText);
					}
					locatorType = null;
					break;
				case "name":
					element = driver.findElement(By.name(locatorColumnValue));
					if (action.equalsIgnoreCase("sendkeys")) {
						element.clear();
						element.sendKeys(value);
					} else if (action.equalsIgnoreCase("click")) {
						element.click();
					} else if (action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
					} else if (action.equalsIgnoreCase("getText")) {
						String elementText = element.getText();
						System.out.println("text from element : " + elementText);
					}
					locatorType = null;
					break;
				case "xpath":
					element = driver.findElement(By.xpath(locatorColumnValue));
					if (action.equalsIgnoreCase("sendkeys")) {
						element.clear();
						element.sendKeys(value);
					} else if (action.equalsIgnoreCase("click")) {
						element.click();
					} else if (action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
					} else if (action.equalsIgnoreCase("getText")) {
						String elementText = element.getText();
						System.out.println("text from element : " + elementText);
					}
					locatorType = null;
					break;
				case "className":
					element = driver.findElement(By.className(locatorColumnValue));
					if (action.equalsIgnoreCase("sendkeys")) {
						element.clear();
						element.sendKeys(value);
					} else if (action.equalsIgnoreCase("click")) {
						element.click();
					} else if (action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
					} else if (action.equalsIgnoreCase("getText")) {
						String elementText = element.getText();
						System.out.println("text from element : " + elementText);
					}
					locatorType = null;
					break;
				case "cssSelector":
					element = driver.findElement(By.cssSelector(locatorColumnValue));
					if (action.equalsIgnoreCase("sendkeys")) {
						element.clear();
						element.sendKeys(value);
					} else if (action.equalsIgnoreCase("click")) {
						element.click();
					} else if (action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
					} else if (action.equalsIgnoreCase("getText")) {
						String elementText = element.getText();
						System.out.println("text from element : " + elementText);
					}
					locatorType = null;
					break;
				case "linkText":
					element = driver.findElement(By.linkText(locatorColumnValue));
					element.click();
					locatorType = null;
					break;
				case "partiallinkText":
					element = driver.findElement(By.partialLinkText(locatorColumnValue));
					element.click();
					locatorType = null;
					break;

				default:
					break;
				}
			} catch (Exception e) {
			}
		}
	}
}

