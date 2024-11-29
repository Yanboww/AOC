package AOC2021;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
public class Day8 {
    private static HashMap<String,String> dict = new HashMap<>();
    public static void main(String[] args)
    {
        System.out.println(findUniqueSegments("inputs/AOC2021Inputs/input8"));
        System.out.println(findSumResult("inputs/AOC2021Inputs/input8"));
    }

    public static int findUniqueSegments(String file){
        File f = new File(file);
        int count = 0;
        try{
            Scanner s = new Scanner(f);
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                String[] results = line.substring(line.indexOf("|")+2).split(("\\s"));
                for(String result : results){
                    int len = result.length();
                    if(len == 2 || len == 3 || len == 4 || len ==7) count++;
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        return count;
    }

    public static int findSumResult(String file)
    {
        File f = new File(file);
        int sum = 0;
        try{
            Scanner s = new Scanner(f);
            while(s.hasNextLine())
            {
                dict = new HashMap<>();
                String line = s.nextLine();
                int index = line.indexOf("|");
                String[] pre = line.substring(0,index-1).split("\\s");
                for(String code : pre)
                {
                    if(code.length()==2) initializedDict(code,"1");
                    else if(code.length()==3) initializedDict(code,"7");
                    else if(code.length()==4) initializedDict(code, "4");
                    else if(code.length()==7) initializedDict(code, "8");
                }
                String[] results = line.substring(index+2).split(("\\s"));
                String lineVal = findVal(results);
                sum+=Integer.parseInt(lineVal);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        return sum;
    }

    public static String findVal(String[] results)
    {
        StringBuilder lineVal = new StringBuilder();
        for(String result : results)
        {
            int len = result.length();
            if(len == 2) lineVal.append("1");
            else if(len == 3) lineVal.append("7");
            else if(len == 4) lineVal.append("4");
            else if(len == 7) lineVal.append("8");
            else{
                lineVal.append(matchDict(result));
            }
        }
        return lineVal.toString();
    }

    public static void initializedDict(String key, String val)
    {
        dict.put(val,key);
    }

    public static String matchDict(String result)
    {
        if(result.length() == 6)
        {
            if(contains(result,dict.get("4"))) return "9";
            else if(contains(result, dict.get("7"))) return "0";
            else return "6";
        }
        else if(result.length() ==5)
        {
            if(findInCommon(result,dict.get("4")) == 2) return "2";
            else if(contains(result, dict.get("7"))) return "3";
            else return "5";
        }
        return "null";

    }

    public static boolean contains(String s1, String s2)
    {
        char[] s2Arr = s2.toCharArray();
        for(char s2C : s2Arr)
        {
            if(s1.indexOf(s2C) == -1) return false;
        }
        return true;
    }

    public static int findInCommon(String s1, String s2)
    {
        char[] s1Arr = s1.toCharArray();
        char[] s2Arr = s2.toCharArray();
        int count = 0;
        for(char s1C : s1Arr)
        {
            for(char s2C : s2Arr)
            {
                if(s1C == s2C) count++;
            }
        }
        return  count;
    }
}
