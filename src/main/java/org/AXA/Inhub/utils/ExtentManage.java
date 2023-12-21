package org.AXA.Inhub.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentManage {

    private static ExtentReports extent;

    public static ExtentReports getInstance(String paths){
        File path = new File(paths);
        if(!path.exists()){
            path.mkdirs();
        }
        if(extent == null) {
            extent = new ExtentReports();
            ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(path+"/reporte.html");
            extent.attachReporter(extentSparkReporter);
        }
        return extent;
    }

}
