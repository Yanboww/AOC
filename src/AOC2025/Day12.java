package AOC2025;
import java.io.*;
import java.util.*;
public class Day12 {
    public static void main(String[] args){
        System.out.println(presents("inputs/input.txt",1));
    }

    public static int presents(String fileName, int part){
        ArrayList<String[]> questions = new ArrayList<>();
        try{
            Scanner s = new Scanner(new File(fileName));
            while(s.hasNextLine()){
                String line = s.nextLine();
                if(line.contains("x")){
                    questions.add(line.split(":"));
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) return findValid(questions);
        return 0;
    }

    public static int findValid(ArrayList<String[]> questions){
        int valid = 0;
        for(String[] question : questions){
            int[] dimensions = Arrays.stream(question[0].split("x")).mapToInt(Integer::parseInt).toArray();
            int area = dimensions[0]/3 * dimensions[1]/3;
            int[] amounts = Arrays.stream(question[1].trim().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int totalBoxes = 0;
            for (int amount : amounts) {
                totalBoxes += amount;
            }
            if(totalBoxes <= area) valid++;
        }
        return valid;
    }
}
