package AOC2020;
import java.io.*;
import java.util.*;
public class Day8 {
    public static void main(String[] args){
        //System.out.println(findAccumulator("inputs/input.txt",1));
        System.out.println(findAccumulator("inputs/input.txt",2));
        //System.out.println(findAccumulator("inputs/trial",2));
    }

    public static int findAccumulator(String fileName, int part)
    {
        File f = new File(fileName);
        ArrayList<String> operations = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            while(s.hasNextLine()) operations.add(s.nextLine());
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) return findLoopAccumulator(operations);
        return uncorruptedAccumulator(operations);
    }

    public static int findLoopAccumulator(ArrayList<String> operations){
        HashSet<Integer> visited = new HashSet<>();
        int accumulator = 0;
        for(int i = 0; i < operations.size(); i ++){
            if(visited.contains(i)) break;
            String operation = operations.get(i);
            if(!operation.contains("nop")){
                int change = Integer.parseInt(operation.substring(operation.indexOf(" ")+1));
                if(operation.contains("acc")) accumulator += change;
                else if(operation.contains("jmp")) i += change-1;
            }
            visited.add(i);
        }
        return accumulator;
    }

    public static int uncorruptedAccumulator(ArrayList<String> operations){
        String[] result = new String[2];
        for(int i = 0; i < operations.size(); i++){
            String original = operations.get(i);
            if(original.contains("jmp")) operations.set(i,original.replace("jmp","nop"));
            else if(original.contains("nop")) operations.set(i,original.replace("nop","jmp"));
            result = test(operations);
            if(result[0].equals("T")) break;
            operations.set(i,original);
        }
        return Integer.parseInt(result[1]);
    }

    public static String[] test(ArrayList<String> operations){
        HashSet<Integer> visited = new HashSet<>();
        int accumulator = 0;
        String status = "T";
        for(int i = 0; i < operations.size(); i ++){
            if(visited.contains(i)){
                status = "F";
                break;
            }
            visited.add(i);
            String operation = operations.get(i);
            if(!operation.contains("nop")){
                int change = Integer.parseInt(operation.substring(operation.indexOf(" ")+1));
                if(operation.contains("acc")) accumulator += change;
                else if(operation.contains("jmp")) i += change-1;
            }
        }
        return new String[]{status,Integer.toString(accumulator)};
    }
}
