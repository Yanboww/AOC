package AOC2016;
import java.io.*;
import java.util.*;
public class Day15 {
    public static void main(String[] args){
        System.out.println(timingCapsule("inputs/input.txt",1));
        System.out.println(timingCapsule("inputs/input.txt",2));
    }

    public static int timingCapsule(String fileName, int part){
        ArrayList<int[]> input = new ArrayList<>();
        try{
            Scanner s = new Scanner(new File(fileName));
            while(s.hasNextLine()){
                String lineStr = s.nextLine();
                String[] lineArr = lineStr.substring(0,lineStr.length()-1).split(" ");
                int[] lineInt = new int[]{Integer.parseInt(lineArr[3]),Integer.parseInt(lineArr[lineArr.length-1])};
                input.add(lineInt);
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 2) input.add(new int[]{11,0});
        return earliest(input);
    }

    public static int earliest(ArrayList<int[]> input){
        int startTime = 0;
        while(true){
            int currentStart = startTime;
            int count = 0;
            for(int[] arr : input){
                currentStart++;
                int position = (arr[1]+currentStart)%arr[0];
                if(position != 0) break;
                count++;
            }
            if(count == input.size()) break;
            startTime++;
        }
        return startTime;
    }
}
