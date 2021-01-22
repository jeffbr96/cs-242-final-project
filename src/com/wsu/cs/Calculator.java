package com.wsu.cs;

import java.io.*;
import java.util.*;

/**
 Author Jefferson Bourguignon Coutinho
 Date 12/15/2020
 */
public class Calculator extends CreateFile {

    Stack<Double> stack = new Stack<>();

    private File file;
    Scanner in;
    final String fileName = "wishLog.txt";

    /**
     * Description:  constructor initializes the class
     * @param // File file
     * @return  n/a
     * @see //    n/a
     * @since           1.0
     */
    public Calculator(File file) throws FileNotFoundException {
        this.file = file;
        this.in = CreateFile.readFromFile(file);
    }

    /**
     * Description:  overloaded constructor
     * @param // void
     * @return n/a
     * @see //    n/a
     * @since           1.0
     */
    public Calculator(){}

    /**
     * Description:  write to files no duplicates
     * @param // String item, double value
     * @return void
     * @see //    CreateFile,
     * @since           1.0
     */
    public static void individualSum(String item, double value) throws IOException {
        String set;
        final String fileName = "uniqueValues.txt";
        HashMap<String, Double> expense = new HashMap<>();

        File file = CreateFile.fileMaker(fileName);

        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        FileReader fileReader = new FileReader(file.getAbsoluteFile());
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Scanner in = new Scanner(bufferedReader);
        if(in.hasNext())while(in.hasNext())expense.put(in.next(), in.nextDouble());

        //Erasing the file after loading
        bufferedWriter = CreateFile.writeToFile(file.getAbsolutePath(), false);
        bufferedWriter.write("");
        bufferedWriter.close();

        bufferedWriter = CreateFile.writeToFile(file.getAbsolutePath(), true);

        if(expense.containsKey(item)){
            value += expense.get(item);
            expense.replace(item, value);

            fileWriter = new FileWriter(file.getAbsoluteFile());
            bufferedWriter = new BufferedWriter(fileWriter);
            Set<String> expenseSet = expense.keySet();
            for(String key: expenseSet){
                value = expense.get(key);
                set = key + " " + value;

                bufferedWriter.write(set);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            return;
        }

        expense.put(item, value);
        Set<String> expenseSet = expense.keySet();
        for(String key: expenseSet){
            value = expense.get(key);
            set = key + " " + value;

            bufferedWriter.write(set);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }

    /**
     * Description:  adds all expenses
     * @param // void
     * @return float total
     * @see //    Expenses
     * @since           1.0
     */
    public static float totalExpenses() throws IOException {
        float total = 0;
        Expenses expenses = new Expenses();
        Scanner in = new Scanner(expenses.getFile());

        while(in.hasNext()){
            in.next();
            total += in.nextDouble();
        }

        return total;
    }

    /**
     * Description:  prints from the file that holds only unique item
     * @param // void
     * @return void
     * @see //    CreateFile
     * @since           1.0
     */
    public static void printIndividualSum() throws IOException {
        File file = CreateFile.fileMaker("uniqueValues.txt");
        Scanner in = CreateFile.readFromFile(file);
        while(in.hasNextLine()){
            System.out.println(in.nextLine());
        }
    }

    /**
     * Description:  reads from wish files sort it and rewrites to file in order
     * @param // void
     * @return double
     * @see //    CreateFile, sortByValue()
     * @since           1.0
     */
    public static double sortWishes() throws IOException {
        Scanner in;
        HashMap<String, Double> hashMap = new HashMap<>();
        Deque<Float> deque = new ArrayDeque<>();
        String items = "";
        Double exit = 0.0;

        File file = CreateFile.fileMaker("wishLog.txt");

        FileReader fileReader = new FileReader(file.getAbsoluteFile());
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        in = new Scanner(bufferedReader);
        if(in.hasNext())while(in.hasNext())hashMap.put(in.next(), in.nextDouble());
        else return exit;

        FileWriter fileWriter1 = new FileWriter(file.getAbsoluteFile(), false);
        BufferedWriter bufferedWriter1 = new BufferedWriter(fileWriter1);
        bufferedWriter1.write("");
        bufferedWriter1.close();

        File file1 = CreateFile.fileMaker("wishLog.txt");


        FileWriter fileWriter = new FileWriter(file1.getAbsoluteFile(), true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        Map<String, Double> hm1 = sortByValue(hashMap);
        String key;
        float value = 0;
        for(Map.Entry<String, Double> entry: hm1.entrySet() ){
            key = entry.getKey();
            value = entry.getValue().floatValue();
            deque.add(value);
            bufferedWriter.write(key + " " + value);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();

        return deque.getFirst();
    }

    /**
     * Description:  sorts a HashMap by value
     * @param //  HashMap<String, Double> hm
     * @return HashMap<String, Double>
     * @see //
     * @since           1.0
     */
    public static HashMap<String, Double> sortByValue(HashMap<String, Double> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Double> > list =
                new LinkedList<Map.Entry<String, Double> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Double> >() {
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });


        // put data from sorted list to hashmap
        HashMap<String, Double> temp = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }

        return temp;
    }

    /**
     * Description:  it adjusts list to reflect new user actions
     * @param // Scanner in
     * @return void
     * @see //    CreateFile,
     * @since           1.0
     */
    public static void adjustWishes(Scanner in) throws IOException {
        File file = CreateFile.fileMaker("wishLog.txt");
        Stack<String> stack = CreateFile.generateStackFromFile(file);
        BufferedWriter bufferedWriter = writeToFile(file.getAbsolutePath(), false);
        bufferedWriter.write("");
        bufferedWriter.close();

        file = CreateFile.fileMaker("wishLog.txt");
        bufferedWriter = writeToFile(file.getAbsolutePath());

        Collections.reverse(stack);
        if (stack.empty())return;

        stack.pop();
        while(!stack.empty()){
            bufferedWriter.write(stack.pop());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }

    /**
     * Description:  test value is a number until it is a number (recursive)
     * @param // String test
     * @return float
     * @see //
     * @since           1.0
     */
    public static float testForNumber(String test){
        float  price = 0;
        Scanner scanner = new Scanner(test);

        if(scanner.hasNextFloat()){return scanner.nextFloat();}

        System.out.print("Please enter a number: ");
        scanner = new Scanner(System.in);
        test = scanner.next();
        scanner = new Scanner(test);
        if(scanner.hasNextFloat()){
            price = scanner.nextFloat();
            return price;
        }
        else{
            testForNumber(test);
        }
        return -1;
    }
}
