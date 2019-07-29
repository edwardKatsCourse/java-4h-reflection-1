package com.company.reports;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class reportClass = null;
        try {
            reportClass = Class.forName(args[0]); //com.company.YearlyReport
        } catch (ClassNotFoundException e) {
            System.out.printf("Report with such name [%s] does not exist\n", args[0]);
        }

        if (!Report.class.isAssignableFrom(reportClass)) {
            System.out.println("This class is not a Report!");
            return;
        }

//        Arrays.stream(reportClass.getMethods()).forEach(x -> System.out.println(x));

        System.out.println("Enter report data: ");
        String reportData = new Scanner(System.in).nextLine();

        //Class -> newInstance()                                        | default constructor
        //Class -> Constructor(String.class) -> newInstance("value")    | with String parameter


        Constructor constructor = reportClass.getConstructor(String.class);

        //YearlyReport yearlyReport = new YearlyReport(reportData)
        Object reportInstance = constructor.newInstance(reportData);
        Method execute = reportClass.getMethod("execute");

        //yearlyReport.execute();
        execute.invoke(reportInstance);
    }
}
