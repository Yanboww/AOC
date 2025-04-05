package AOC2020.Day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Day14 {
    public static void main(String[] args)
    {
        System.out.println(docking("inputs/input.txt",1));
        System.out.println(docking("inputs/input.txt",2));
    }
    public static long docking(String fileName, int part)
    {
        File f = new File(fileName);
        ArrayList<Masks> masks = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            Masks current = null;
            while(s.hasNextLine()){
                String line = s.nextLine();
                if(line.contains("mask")){
                    if(current != null) masks.add(current);
                    current = new Masks(line.substring(line.lastIndexOf(" ")));
                }
                else{
                    if(current!=null)current.updateMaskMemory(line);
                }
            }
            masks.add(current);
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part ==1 )return calculateSumMem(masks);
        return calculateSumMemX(masks);
    }

    public static long calculateSumMem(ArrayList<Masks> masks){
        HashMap<Integer,Long> dict = new HashMap<>();
        ArrayList<Integer> keys = new ArrayList<>();
        for(Masks mask : masks){
            ArrayList<Integer> memories = mask.getMemory();
            ArrayList<Integer> calculations = mask.getCalculations();
            String[] maskArr = mask.getMaskArr();
            for(int i = 0; i < memories.size(); i++){
                int memory = memories.get(i);
                dict.put(memory,calcVal(maskArr,calculations.get(i)));
                if(!keys.contains(memory)) keys.add(memory);
            }
        }
        long sum = 0;
        for(int key : keys) sum+=dict.get(key);
        return sum;
    }

    public static long calculateSumMemX(ArrayList<Masks> masks){
        HashMap<Long,Integer> dict = new HashMap<>();
        ArrayList<Long> keys = new ArrayList<>();
        for(Masks mask : masks){
            ArrayList<Integer> memories = mask.getMemory();
            ArrayList<Integer> calculations = mask.getCalculations();
            String[] maskArr = mask.getMaskArr();
            for(int i = 0; i < memories.size(); i++){
                int memory = memories.get(i);
                int calculation = calculations.get(i);
                ArrayList<Long> maskedMemories = new ArrayList<>();
                calcTotalPossible(calcValNew(maskArr,memory),maskedMemories);
                for(Long maskedMemory : maskedMemories){
                    dict.put(maskedMemory,calculation);
                    if(!keys.contains(maskedMemory)) keys.add(maskedMemory);
                }
            }
        }
        long sum = 0;
        for(Long key : keys){
            sum += dict.get(key);
        }
        return sum;
    }

    public static void calcTotalPossible(String value, ArrayList<Long> memories){
        if(!value.contains("X")){
            memories.add(Long.parseLong(value,2));
            return;
        }
        int i = value.indexOf("X");
        calcTotalPossible(value.substring(0, i) +"0"+ value.substring(i + 1),memories);
        calcTotalPossible(value.substring(0, i) +"1"+ value.substring(i + 1),memories);
    }

    public static String calcValNew(String[] maskArr, int val){
        String[] valArr = binaryConvert(val);
        for(int i = 0; i < valArr.length; i++){
            String current = maskArr[i];
            if(!current.equals("0")) valArr[i] = current;
        }
        StringBuilder newVal = new StringBuilder();
        for(String character: valArr) newVal.append(character);
        String newValStr = newVal.toString();
        int first = newValStr.indexOf("1");
        int second = newValStr.indexOf("X");
        if(second < first) first = second;
        newValStr = newValStr.substring(first);
        return newValStr;
    }

    public static long calcVal(String[] maskArr, int val){
        String[] valArr = binaryConvert(val);
        for(int i = 0; i < valArr.length; i++){
            String current = maskArr[i];
            if(!current.equals("X")) valArr[i] = current;
        }
        StringBuilder newVal = new StringBuilder();
        for(String character: valArr) newVal.append(character);
        String newValStr = newVal.toString();
        newValStr = newValStr.substring(newValStr.indexOf("1"));
        return Long.parseLong(newValStr,2);
    }

    public static String[] binaryConvert(int number)
    {
        String[] binary = new String[36];
        Arrays.fill(binary,"0");
        int counter = 35;
        while(number!=0)
        {
            binary[counter] = Integer.toString(number%2);
            number /= 2;
            counter--;
        }
        return binary;
    }
}
