package AOC2024;
import java.io.*;
import java.util.*;
public class Day7 {
    public static void main(String[] args) {
        System.out.println(findSumTrue("inputs/input.txt", 1));
        System.out.println(findSumTrue("inputs/input.txt", 2));
    }

    public static Long findSumTrue (String fileName, int part)
    {
        File f = new File(fileName);
        long total = 0L;
        try{
            Scanner s = new Scanner(f);
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] temp = line.split("\\W+");
                total+=isPossible(temp, part);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        return total;
    }

    public static Long isPossible(String[] temp, int part)
    {
        long val = Long.parseLong(temp[0]);
        ArrayList<String> possibleVariations = new ArrayList<>();
        makeVariations(possibleVariations, temp, 1, "", part);
        for(String var : possibleVariations)
        {
            if(computeVar(var) == val) {
                return val;
            }

        }
        return (long)0;
    }

    public static void makeVariations(ArrayList<String> variations, String[] temp, int index, String current, int part)
    {
        if(index == temp.length-1){
            variations.add(current+temp[index]);
            return;
        }
        makeVariations(variations,temp,index+1,current+temp[index]+"*", part);
        makeVariations(variations,temp,index+1,current+temp[index]+"+", part);
        if(part == 2) makeVariations(variations,temp,index+1,current+temp[index]+"|", part);

    }

    public static long computeVar(String var)
    {
        String[] temp = var.split("\\W+");
        String[] temp2 = var.split("\\w+");
        long total = Long.parseLong(temp[0]);
        int index = 1;
        for(int i = 1; i < temp.length; i++)
        {
            if(temp2[index].equals("*")){
                total*=Long.parseLong(temp[i]);
            }
            else if(temp2[index].equals("|"))
            {
                String tempString = total + temp[i];
                total =Long.parseLong(tempString);
            }
            else{
                total+=Long.parseLong(temp[i]);
            }
            index++;
        }
        return total;
    }

}
