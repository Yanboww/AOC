package AOC2015;
import java.io.*;
import java.util.*;
public class Day17 {
    public static void main(String[] args){
        System.out.println(combination("inputs/input.txt",1));
        System.out.println(combination("inputs/input.txt",2));
    }

    public static int combination(String fileName, int part) {
        ArrayList<Integer> containers = new ArrayList<>();
        try{
            Scanner s = new Scanner(new File(fileName));
            while(s.hasNextLine()) containers.add(s.nextInt());
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) return findExactCombo(containers, 0, 0);
        return minContainerCombo(containers);
    }

    public static int findExactCombo(ArrayList<Integer> containers, int index, int sum){
        if(sum == 150) return 1;
        else if(containers.size() == index) return 0;
        int totalPossible = findExactCombo(containers,index+1,sum+containers.get(index));
        totalPossible+= findExactCombo(containers, index+1, sum);
        return totalPossible;
    }

    public static int findExactCombo(ArrayList<Integer> containers, int index, int sum, int counters, HashMap<Integer, Integer> dict){
        if(sum == 150){
            dict.put(counters,dict.getOrDefault(counters,0)+1);
            return 1;
        }
        else if(containers.size() == index) return 0;
        int totalPossible = findExactCombo(containers,index+1,sum+containers.get(index),counters+1, dict);
        totalPossible+= findExactCombo(containers, index+1, sum, counters, dict);
        return totalPossible;
    }

    public static int minContainerCombo(ArrayList<Integer> containers){
        HashMap<Integer,Integer> dict = new HashMap<>();
        int val = findExactCombo(containers,0,0,0,dict);
        int min = -1;
        for(int key : dict.keySet()) {
            if (min == -1) min = key;
            else if (min > key) min = key;
        }
        return dict.get(min);
    }


}
