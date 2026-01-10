package AOC2015;
import java.io.*;
import java.util.*;
public class Day24 {
    public static void main(String[] args){
        System.out.println(balance("inputs/input.txt",1));
        System.out.println(balance("inputs/input.txt",2));
    }

    public static long balance(String fileName, int part){
        ArrayList<Integer> input = new ArrayList<>();
        int weight = 0;
        try{
            Scanner s = new Scanner(new File(fileName));
            while(s.hasNextLine()){
                int line = s.nextInt();
                input.add(line);
                weight+=line;
            }
            s.close();
            input.sort(Collections.reverseOrder());
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        ArrayList<String> result = new ArrayList<>();
        if(part == 1) combination(result,input,weight/3,0, 0, "");
        else combination(result,input,weight/4,0,0,"");
        Collections.sort(result);
        return entanglement(result);
    }

    public static void combination(ArrayList<String> results, ArrayList<Integer> input, int weight, int start, int used, String current){
        if(weight == 0){
            StringBuilder usedStr = new StringBuilder(Integer.toString(used));
            while(usedStr.length() < 2) usedStr.insert(0, "0");
            results.add(usedStr+current);
        }
        for(int i = start; i < input.size(); i++){
            combination(results, input, weight-input.get(i),i+1, used+1, current+" "+input.get(i));
        }
    }

    public static long entanglement(ArrayList<String> results){
        int minUsed = -1;
        long entanglement = -1;
        for(String result : results){
            int[] arr = Arrays.stream(result.split(" ")).mapToInt(Integer::parseInt).toArray();
            if(minUsed < 0 || arr[0] <= minUsed){
                minUsed = arr[0];
                long tempEntanglement = 1L;
                for(int i = 1; i < arr.length; i++) tempEntanglement *= arr[i];
                if(entanglement < 0 || tempEntanglement < entanglement) entanglement = tempEntanglement;
            }
            else break;
        }
        return entanglement;
    }
}
