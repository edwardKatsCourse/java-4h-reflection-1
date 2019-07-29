package com.company.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws
            IllegalAccessException,
            InstantiationException,
            NoSuchMethodException,
            InvocationTargetException {

	    //Reflection
        //1. Why???
        //2. When to use? Who uses reflection?
        //3. Get Methods/Fields, find values ...

        Object instance = Example.class.newInstance();
        Method method = Example.class.getMethod("method");
        Method method2 = Example.class.getMethod("method", String.class);
        method.invoke(instance);

        method2.invoke(instance, "My custom parameter for reflection call");

        String firstName = new Scanner(System.in).nextLine();
        //null, "", "  g  "

        //Phase 1
        //if (firstName == null || firstName.isEmpty() || firstName.trim().isEmpty())

        //Phase 2
        hasValue(firstName);
        System.out.println(firstName); //yes\no

        //tHis iS SomE teXt -> This Is Some Text (capitalized)

        //String -> Integer

        //
    }

    static boolean hasValue(String value) {
        boolean emptyValue = value == null || value.isEmpty() || value.trim().isEmpty();

        return !emptyValue;
    }

}

class Example {

    private String fileContent;

    public void method() {
        System.out.println("Example.method");
    }

    public void method(String text) {
        System.out.println("Example.method(string) -> " + text);
    }

}

class App {

    private String content;
}
