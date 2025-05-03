package AOC2020;
import java.io.*;
import java.util.*;
public class Day19 {
    public static void main(String[] args){
        System.out.println(checkMessages("inputs/input.txt",1));
    }

    public static int checkMessages(String fileName, int part)
    {
        File f = new File(fileName);
        int length = 133;
        if(fileName.contains("trial")) length=43;
        String[] rules = new String[length];
        ArrayList<String> messages = new ArrayList<>();
        try{
            boolean receivedMessage = false;
            Scanner s = new Scanner(f);
            while (s.hasNextLine()){
                String line = s.nextLine();
                if(line.isEmpty()) receivedMessage = true;
                else if(receivedMessage) messages.add(line);
                else{
                    int point = line.indexOf(":");
                    int index = Integer.parseInt(line.substring(0,point));
                    line = line.substring(point+2);
                    rules[index] = line;
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) return findValid(rules,messages);
        return 0;
    }

    public static int findValid(String[] rules, ArrayList<String> messages){
        int count = 0;
        ArrayList<String> validMessages = generateValidMessages(rules,0);
        for(String message : messages){
            if(validMessages.contains(message)) count++;
        }
        return count;
    }

    public static ArrayList<String> generateValidMessages(String[] rules, int rule){
        ArrayList<String> result = new ArrayList<>();
        String currentRule = rules[rule];
        if(currentRule.contains("\"")){
            result.add(currentRule.substring(1,currentRule.lastIndexOf("\"")));
            return result;
        }
        String[] possibilities = currentRule.split(" \\| ");
        for(String nextRules : possibilities){
            String[] nextRulesArr = nextRules.trim().split("\s");
            ArrayList<ArrayList<String>> tempStorage = new ArrayList<>();
            for(String nextRule : nextRulesArr){
                tempStorage.add(generateValidMessages(rules, Integer.parseInt(nextRule)));
            }
            addToResult(result,tempStorage, "",0);
        }
        return result;
    }

    public static void addToResult(ArrayList<String> result, ArrayList<ArrayList<String>> tempStorage, String message, int index){
        if(index == tempStorage.size()) result.add(message);
        else {
            ArrayList<String> currents = tempStorage.get(index);
            for(String current : currents){
                addToResult(result,tempStorage,message+current,index+1);
            }
        }
    }
}
