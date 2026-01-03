package AOC2015;
import java.io.*;
import java.util.*;
public class Day23 {
    public static void main(String[] args){
        System.out.println(operate("inputs/input.txt",1));
        System.out.println(operate("inputs/input.txt",2));
    }

    public static int operate(String fileName, int part){
        ArrayList<String[]> inputs = new ArrayList<>();
        try{
            Scanner s = new Scanner(new File(fileName));
            while(s.hasNextLine()) inputs.add(s.nextLine().split("\\s"));
            s.close();
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        return getShiftRegister(inputs, part);
    }

    public static int getShiftRegister(ArrayList<String[]> inputs, int part){
        int a = 0; int b = 0;
        if(part == 2) a++;
        for(int i = 0; i < inputs.size(); i++){
            String[] input = inputs.get(i);
            switch (input[0]) {
                case "hlf" -> {
                    if(input[1].equals("a")) a/=2;
                    else b/=2;
                }
                case "tpl" -> {
                    if(input[1].equals("a")) a*=3;
                    else b*=3;
                }
                case "inc" -> {
                    if(input[1].equals("a")) a++;
                    else b++;
                }
                case "jmp" -> {
                    String operation = input[1].substring(0, 1);;
                    int value = Integer.parseInt(input[1].substring(1));
                    if (operation.equals("-")) i -= (value+1);
                    else i += (value-1);
                }
                case "jie" -> {
                    String currentRegister = input[1].substring(0, 1);
                    int registerVal = (currentRegister.equals("a")) ? a : b;
                    if (registerVal % 2 == 0) {
                        String operation = input[2].substring(0, 1);
                        int value = Integer.parseInt(input[2].substring(1));
                        if (operation.equals("-")) i -= (value+1);
                        else i += (value-1);
                    }
                }
                case "jio" -> {
                    String currentRegister = input[1].substring(0, 1);
                    int registerVal = (currentRegister.equals("a")) ? a : b;
                    if (registerVal == 1) {
                        String operation = input[2].substring(0, 1);
                        int value = Integer.parseInt(input[2].substring(1));
                        if (operation.equals("-")) i -= (value+1);
                        else i += (value-1);
                    }
                }
            }
        }
        return b;
    }
}
