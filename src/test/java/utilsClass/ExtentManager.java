package utilsClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	
	 private static ExtentReports extent;

	    public static ExtentReports getInstance() {
	        if (extent == null) {
	            String path = System.getProperty("user.dir") + "/target/extent-reports/ExtentReport.html";
	            ExtentSparkReporter spark = new ExtentSparkReporter(path);
	            spark.config().setReportName("Automation Test Report");
	            spark.config().setDocumentTitle("Test Results");
	            spark.config().setTheme(Theme.STANDARD);

	            extent = new ExtentReports();
	            extent.attachReporter(spark);
	            extent.setSystemInfo("OS", System.getProperty("os.name"));
	            extent.setSystemInfo("Java", System.getProperty("java.version"));
	        }
	        return extent;
	    }
}
