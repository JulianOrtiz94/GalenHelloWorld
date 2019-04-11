package com.lambdatest.qa.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.galenframework.api.Galen;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;

public class GalenExampleTest {
	private static final String lambdaTestpage = "src\\test\\resources\\specs\\Homepage.gspec";
	private static final String baseURL = "https://www.lambdatest.com/";
	private static WebDriver driver;
	private LayoutReport layoutReport;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\SELENIUM\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(baseURL);
		driver.manage().window().maximize();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() {
		try {
			layoutReport = Galen.checkLayout(driver, lambdaTestpage, Arrays.asList("desktop"));
            List<GalenTestInfo> tests = new LinkedList<>();
            GalenTestInfo test = GalenTestInfo.fromString("Test Automation Using Galen Framework");
            test.getReport().layout(layoutReport, "Verify logo present and log image comparison");
            tests.add(test);
            new HtmlReportBuilder().build(tests, "target/galen-html-reports");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
