package AOC2024;
import java.util.*;
import java.io.*;
public class Day5 {
    private static HashSet<String> rules;
    public static void main(String[] args) {
        System.out.println(findSumMid("inputs/AOC2024Inputs/day5",1));
        System.out.println(findSumMid("inputs/AOC2024Inputs/day5",2));
    }

    public static int findSumMid(String fileName, int part)
    {
        File f = new File(fileName);
        ArrayList<String> inputs = new ArrayList<>();
        rules = new HashSet<>();
        try{
            Scanner s = new Scanner(f);
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                if(!line.contains(",")) rules.add(line);
                else inputs.add(line);
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        int count = 0;
        for(String update : inputs) {
            if(part == 1) count += findIfValid(update);
            else count+= findIfInvalid(update);
        }

        return count;
    }

    public static int findIfValid(String line)
    {
        String[] temp = line.split("\\W+");
       for(int i = 0; i < temp.length; i++)
       {
           String val = temp[i];
           for(int k = i+1; k < temp.length; k++)
           {
               String val2 = temp[k];
               if(rules.contains(val2+"|"+val)) return 0;
           }
       }
       int index = temp.length/2;
       return Integer.parseInt(temp[index]);
    }
    public static int findIfInvalid(String line)
    {
        if(findIfValid(line) == 0)
        {
            String[] temp = line.split("\\W+");
            for(int i = 0; i < temp.length; i++)
            {
                String val = temp[i];
                for(int k = i+1; k < temp.length; k++)
                {
                    String val2 = temp[k];
                    if(rules.contains(val2+"|"+val)){
                        temp[i] = val2;
                        temp[k] = val;
                        i = -1;
                        break;
                    }
                }
            }
            int index = temp.length/2;
            return Integer.parseInt(temp[index]);
        }
        return 0;
    }

}
