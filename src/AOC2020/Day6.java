package AOC2020;
import java.io.*;
import java.util.*;
public class Day6 {
    public static void main(String[] args){
        System.out.println(findTotalYes("inputs/input.txt",1));
        System.out.println(findTotalYes("inputs/input.txt",2));
    }

    public static int findTotalYes(String fileName, int part)
    {
        File f = new File(fileName);
        ArrayList<String> groups = new ArrayList<>();
        ArrayList<Integer> people = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            StringBuilder currentGroup = new StringBuilder();
            int count = 0;
            while(s.hasNextLine()){
                String line = s.nextLine();
                if(line.isEmpty()){
                    groups.add(currentGroup.toString());
                    people.add(count);
                    currentGroup.setLength(0);
                    count = 0;
                }
                else{
                    currentGroup.append(line);
                    count++;
                }
            }
            groups.add(currentGroup.toString());
            people.add(count);
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        int sum = 0;
        int i = 0;
        for(String group : groups){
            if(part == 1) sum+=calcYes(group);
            else sum+=calcConsensus(group,people.get(i));
            i++;
        }
        return  sum;
    }

    public static int calcYes(String group){
        HashSet<Character> storage = new HashSet<>();
        char[] arr = group.toCharArray();
        for(char question : arr) storage.add(question);
        return storage.size();
    }

    public static int calcConsensus(String group, int people){
        HashSet<Character> storage = new HashSet<>();
        char[] arr = group.toCharArray();
        int count = 0;
        for(char question : arr){
            if(countOccurrence(arr,question) == people && !storage.contains(question)) count++;
            storage.add(question);
        }
        return  count;
    }

    public static int countOccurrence(char[] line, char letter){
        int count = 0;
        for(char l : line) if(l == letter) count++;
        return count;
    }
}
