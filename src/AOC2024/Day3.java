package AOC2024;
import java.io.*;
import java.util.*;
import java.util.regex.*;
public class Day3 {
    public static boolean  instruction = true;
    public static void main(String[] args) {
        System.out.println(findTotalValid("inputs/AOC2024Inputs/day3", 1));
        System.out.println(findTotalValid("inputs/AOC2024Inputs/day3", 2));
    }

    public static int findTotalValid(String fileName, int part)
    {
        File f = new File(fileName);
        String regex = "mul\\(\\d+,\\d+\\)";
        if (part == 2) regex = "(" + regex + "|do\\(\\)|don't\\(\\))";
        Pattern p = Pattern.compile(regex);
        ArrayList<String> matches = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            while(s.hasNextLine())
            {
                Matcher match = p.matcher(s.nextLine());
                while(match.find()) matches.add(match.group());
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        int total = 0;
        for(String match : matches) total+=findProduct(match);
        return  total;
    }

    public static int findProduct(String match)
    {
        if(!instruction && !match.equals("do()")) return 0;
        boolean ran = false;
        int product = 1;
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(match);
       while(m.find())
       {
           product*=Integer.parseInt(m.group());
           ran = true;
       }
       if(!ran){
           if(match.equals("do()")) instruction = true;
           else instruction = false;
           return 0;
       }
       return product;
    }
}
