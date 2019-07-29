package com.company.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Scanner;

public class FileContentExample {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException, IOException, NoSuchMethodException {

        Class c;
        Field f;

        Method m; //method()
        Parameter p;
        Constructor ctor;

        System.out.println("--- Class ---");
        System.out.println(FileContent.class);
        System.out.println();
        System.out.println("--- Fields ---");
        System.out.println();

        Field[] fields = FileContent.class.getFields();
        Arrays.stream(fields).forEach(System.out::println);

        System.out.println();
        System.out.println("--- Declared fields ---");

        fields = FileContent.class.getDeclaredFields();
        Arrays.stream(fields).forEach(System.out::println);


        //Class -> Fields[] | Methods[] | Constructors[]
        //Method -> Parameters[]

        System.out.println();
        System.out.println("--- Methods ---");
        Method [] methods = FileContent.class.getMethods();
        Arrays.stream(methods).forEach(System.out::println);

        System.out.println();
        System.out.println("--- Declared Methods ---");
        methods = FileContent.class.getDeclaredMethods();
        Arrays.stream(methods).forEach(System.out::println);

        System.out.println();

        FileContent.class.getSuperclass(); //FileContentParent -> declared ..


        methods = FileContent.class.getSuperclass().getSuperclass().getDeclaredMethods(); //Object
        System.out.println("--- Object's Declared methods ---");
        Arrays.stream(methods).forEach(System.out::println);

        System.out.println();

        //1. Create FileContent instance
        //2. find field and set value
        //3. find method and invoke (call) it
        //4. get return value and print it

        //IllegalAccessException -> private constructor, for example
        //InstantiationException -> new int, new void, new interface, new array

        String classToWorkWith = "com.company.example.FileContent";

        //FileContent || FileContent.class
        Class fileContentClass = Class.forName(classToWorkWith);

        //FileContent fileContentInstance = new FileContent()
        Object fileContentInstance = fileContentClass.newInstance();

        //fileContentInstance.fileContent
        Field field = fileContentClass.getField("fileContent");

        System.out.println("Enter filename: ");
        String filename = new Scanner(System.in).nextLine();
        String content = getFileContent(filename);

        //fileContentInstance.fileContent = content;
        field.set(fileContentInstance, content);

        Method method = fileContentClass.getMethod("getFileContent", String.class);

        //InvocationTargetException -> what if exception was thrown from inside of the method

        //fileContentInstance.getFileContent(filename)
        try {

            //String getFileContent() return null;
            //void getFileContent()
            Object returnValue = method.invoke(fileContentInstance, filename);
            System.out.printf("Method: [%s] returned value: [%s]\n",
                    method.getName(),
                    returnValue);

        } catch (InvocationTargetException e) {
            System.out.println("Exception thrown from method " + method.getName());
            System.out.println(e.getCause().getMessage());
        }
    }

    private static String getFileContent(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return reader.readLine();
        }
    }
}
class FileContentParent extends Object {

    private String publicParentField;


    public void parentMethod() {}
    private void privateParentMethod() {}
}

class FileContent extends FileContentParent {

    public String publicField;
    protected String protectedField;
    String packageDefaultField;
    private String privateField;

    public String fileContent;

    //file.content.txt: This is my customer test for reflection
    public String getFileContent(String filename) {
        if (filename == null || filename.isEmpty()) {
            throw new RuntimeException("Filename cannot be null");
        }
        return filename + " -> " + fileContent;
    }


    public void publicMethod() {}
    protected void protectedMethod() {}
    private void privateMethod() {}
    void packageDefaultMethod() {}
}
