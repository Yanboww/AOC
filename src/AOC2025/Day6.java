package AOC2025;
import java.io.*;
import java.util.*;
public class Day6 {
    public static void main(String[] args){
        System.out.println(solveMathHW("inputs/input.txt",1));
        System.out.println(solveMathHW("inputs/input.txt",2));
    }

    public static long solveMathHW(String fileName, int part){
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<String[]> numbers = new ArrayList<>();
        String[] operators = new String[0];
        try {
            Scanner s = new Scanner(new File(fileName));
            while(s.hasNextLine()){
                String lineUnTrim = s.nextLine();
                String line = lineUnTrim.trim();
                if(line.contains("*") || line.contains("+")) operators = line.split("\\s+");
                else{
                    numbers.add(line.split("\\s+"));
                }
                lines.add(lineUnTrim);
            }
        } catch (FileNotFoundException e){
            System.out.println("File not fund");
        }
        if(part == 1) return solvePart1(numbers,operators);
        return solvePart2(lines,operators);
    }

    public static long solvePart1(ArrayList<String[]> numbers, String[] operators){
        long total = 0;
        for(int i = 0; i < operators.length; i++){
            long currentTotal = 0;
            String operator = operators[i];
            if(operator.equals("*")){
                currentTotal = 1;
                for(String[] arr : numbers){
                    if(i < arr.length){
                        currentTotal*=Long.parseLong(arr[i]);
                    }
                }
            }
            else{
                for(String[] arr : numbers){
                    if(i < arr.length){
                        currentTotal+=Long.parseLong(arr[i]);
                    }
                }
            }
            total+=currentTotal;
        }
        return total;
    }

    public static long solvePart2(ArrayList<String> line, String[] operators){
        long total = 0;
        for (String s : operators) {
            long currentTotal = 0;
            long[] numbers = computeNumbers(line);
            if (s.equals("*")) {
                currentTotal = 1;
                for (long val : numbers) {
                    currentTotal *= val;
                }
            }
            else {
                for (long val : numbers) {
                    currentTotal += val;
                }
            }
            total += currentTotal;
        }
        return total;
    }

    public static long[] computeNumbers(ArrayList<String> line){
        String[] values = new String[line.size()-1];
        String operatorLine = line.get(line.size()-1);
        int index = -1;
        for(int i = 1; i < operatorLine.length(); i++){
            String currentChar = operatorLine.substring(i,i+1);
            if(currentChar.equals("+") || currentChar.equals("*")){
                line.remove(line.size()-1);
                line.add(operatorLine.substring(i));
                index = i-1;
                break;
            }
        }
        if(index == -1){
            int maxLen = 0;
            for(String temp : line) if(temp.length() > maxLen) maxLen = temp.length();
            index = maxLen;
        }
        for(int i = 0; i < values.length; i++){
            String currentLine = line.remove(i);
            values[i] = currentLine.substring(0,index);
            if(index < operatorLine.length()){
                line.add(i,currentLine.substring(index+1));
            }
            else line.add(i,currentLine);
        }
        long[] result = new long[index];
        int resultIndex = 0;
        for(int i = 0; i < index; i++){
            StringBuilder currentVal = new StringBuilder();
            for(String value : values){
                String currentChar = value.substring(i,i+1);
                if(!currentChar.equals(" ")) currentVal.append(currentChar);
            }
            result[resultIndex] = Long.parseLong(currentVal.toString());
            resultIndex++;
        }
        return result;
    }
}
