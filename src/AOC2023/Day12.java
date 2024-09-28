//still not very fast but gets the job done
package AOC2023;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.util.HashMap;
public class Day12 {
    public static void main(String[] args) {
        File f;
        try{
            f = new File("InputFile");
            Scanner s = new Scanner(f);
            List<String> inputs = new ArrayList<>();
            HashMap<String,Integer> memo = new HashMap<>();
            while(s.hasNextLine())
            {
                inputs.add(s.nextLine());
            }
            int sum =0;
            int sumP2 =0;
            for(String items: inputs)
            {
                String desc = makeDesc(items)+",";
                String drawing = makeDrawing(items);
                sum+=findPerm(drawing,desc,memo);
                items = makeNewLine(items);
                desc = makeDesc(items);
                drawing = makeDrawing(items);
                sum+=findPerm(drawing,desc,memo);
            }
            System.out.println(sum);
            System.out.println(sumP2);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found");
        }
    }

    public static String makeDesc(String line)
    {
        int index = line.indexOf(" ")+1;
        return line.substring(index);
    }
    public static String makeDrawing(String line)
    {
        int index = line.indexOf(" ");
        return line.substring(0,index);
    }

    public static int countChar(String line, String specific)
    {
        int count = 0;
        for(int i = 0;i<line.length();i++)
        {
            String letter = line.substring(i,i+1);
            if(letter.equals(specific))
            {
                count++;
            }
        }
        return count;
    }
    public static String makeDesc2(String drawing)
    {
        drawing = drawing+".";
        String returnDesc ="";
        int count =0;
        for(int i =0;i<drawing.length();i++)
        {
            String letter = drawing.substring(i,i+1);
            if(letter.equals("#"))
            {
                count++;
            }
            else if(letter.equals(".") && count!=0)
            {
                returnDesc+= count+",";
                count =0;
            }
        }
        //System.out.println(returnDesc);
        return returnDesc;
    }


    public static int findPerm(String drawing, String desc, HashMap<String, Integer> key)
    {
        //System.out.println(drawing);
        int unknownCount  = countChar(drawing,"?");
        if(unknownCount == 0)
        {
            if(makeDesc2(drawing).equals(desc))return 1;
            return 0;
        }
        if(key.containsKey(drawing+desc)) return key.get(drawing+desc);
        int sum = 0;
        sum+=findPerm(replaceOnce(drawing,"?","."),desc, key);
        sum+= findPerm(replaceOnce(drawing,"?","#"),desc,key);
        key.put(drawing+desc,sum);
        return sum;
    }
    public static String replaceOnce(String drawing, String target, String replacement)
    {
        int index = drawing.indexOf(target);
        if(index == 0)
        {
            return replacement+drawing.substring(1);
        }
        else{
            return drawing.substring(0,index) + replacement + drawing.substring(index+1);
        }
    }

    public static String makeNewLine(String line)
    {
        String drawing = makeDrawing(line);
        String desc = makeDesc(line);
        String returnNewLine ="";
        for(int i = 0;i<5;i++)
        {
            returnNewLine +=drawing;
            if(i!=4)
            {
                returnNewLine+="?";
            }
        }
        returnNewLine+= " ";
        for(int i = 0;i<5;i++)
        {
            returnNewLine+=desc+",";
        }
        return returnNewLine;
    }





}
