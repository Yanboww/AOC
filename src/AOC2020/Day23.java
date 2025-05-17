package AOC2020;
import java.io.*;
import java.util.*;

public class Day23 {
    public static void main(String[] args){
        System.out.println(cupSimulator("inputs/input.txt",1));
    }

    public static String cupSimulator(String file, int part){
        File f = new File(file);
        String[] cups = new String[0];
        try{
            Scanner s = new Scanner(f);
            cups = s.nextLine().split("(?!^)");
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) simulateMovement(cups);
        StringBuilder result = new StringBuilder();
        for(String val : cups) result.append(val);
        return result.substring(1);
    }

    public static void simulateMovement(String[] cups){
        int index = 0;
        for(int i = 0; i < 100; i++){
            moveCup(cups,index);
            index++;
            if(index > cups.length-1) index = 0;
        }
        shiftCups(cups,0,"1");
    }

    public static void moveCup(String[] cups, int index){
        int currentVal = Integer.parseInt(cups[index]);
        int index1 = index+1;
        if(index1 > cups.length-1) index1 = 0;
        int index2 = index1+1;
        if(index2 > cups.length-1) index2 = 0;
        int index3 = index2+1;
        if(index3 > cups.length-1) index3 = 0;
        String[] removed = new String[]{cups[index1], cups[index2], cups[index3]};
        String[] workingArr = new String[cups.length-3];
        int lowest = Integer.parseInt(cups[0]);
        int highest = Integer.parseInt(cups[0]);
        int workingIndex = 0;
        for(int i = 0; i < cups.length; i++){
            String val = cups[i];
            if(i != index1 && i != index2 && i != index3){
                workingArr[workingIndex] = val;
                workingIndex++;
                if(Integer.parseInt(val) < lowest) lowest = Integer.parseInt(val);
                else if(Integer.parseInt(val) > highest) highest = Integer.parseInt(val);
            }
        }
        int subtract = 1;
        int moveIndex = contains(currentVal-subtract,workingArr);
        while(moveIndex == -1){
            subtract++;
            if(currentVal-subtract < lowest) moveIndex = contains(highest,workingArr);
            else moveIndex = contains(currentVal-subtract,workingArr);
        }
        int move1 = moveIndex+1;
        if(move1 > cups.length-1) move1 = 0;
        int move2 = move1+1;
        if(move2 > cups.length-1) move2 = 0;
        int move3 = move2+1;
        if(move3 > cups.length-1) move3 = 0;
        workingIndex = 0;
        for(int i = 0; i < cups.length; i++) {
            if (i == move1) cups[i] = removed[0];
            else if (i == move2) cups[i] = removed[1];
            else if (i == move3) cups[i] = removed[2];
            else {
                cups[i] = workingArr[workingIndex];
                workingIndex++;
            }

        }
        shiftCups(cups,index,Integer.toString(currentVal));
    }

    public static int contains(int val, String[] arr){
        String valStr = Integer.toString(val);
        for(int i = 0; i < arr.length; i++){
            String num = arr[i];
            if(num.equals(valStr)) return i;
        }
        return -1;
    }

    public static void shiftCups(String[] cups, int index, String val){
        while(!cups[index].equals(val)){
            String start = cups[0];
            for(int i = 1; i < cups.length; i++){
                cups[i-1] = cups[i];
            }
            cups[cups.length-1] = start;
        }
    }
}
