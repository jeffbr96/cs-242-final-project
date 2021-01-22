package com.wsu.cs;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class CreateFile {

    public static File fileMaker(String fileName) throws IOException {
        String workingDirectory = System.getProperty("user.dir");
        String absolutePath = workingDirectory + File.separator + fileName;
        File file = new File(absolutePath);
        if(!file.exists()){
            file.createNewFile();
        }

        return file;
    }

    public static void writeToFile(String info, String absolutePath) throws IOException {
        FileWriter fileWriter = new FileWriter(absolutePath, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write(info);
        bufferedWriter.newLine();

        bufferedWriter.close();
    }

    public static BufferedWriter writeToFile(String absolutePath) throws IOException {
        FileWriter fileWriter = new FileWriter(absolutePath, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        return bufferedWriter;
    }

    public static BufferedWriter writeToFile(String absolutePath, boolean concat) throws IOException {
        FileWriter fileWriter = new FileWriter(absolutePath, concat);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        return bufferedWriter;
    }

    public static Scanner readFromFile(File file) throws FileNotFoundException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Scanner in = new Scanner(bufferedReader);
        return in;
    }

    public static Stack<String> generateStackFromFile(File file) throws FileNotFoundException {
        Stack<String> stack = new Stack<>();

        Scanner in = readFromFile(file);

        while(in.hasNextLine()) stack.push(in.nextLine());

        return stack;
    }

    public static HashMap<String, Double> generateHashMapFromFile(File file) throws FileNotFoundException {
        HashMap<String, Double> hashMap = new HashMap<>();
        Scanner in = readFromFile(file);
        if(in.hasNext())while(in.hasNext())System.out.println(hashMap.put(in.next(), in.nextDouble()));
        return hashMap;
    }
}