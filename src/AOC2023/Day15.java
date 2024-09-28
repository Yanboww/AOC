package AOC2023;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
public class Day15 {
    public static void main(String[] args) {
        File f;
        try{
            f = new File("InputFile");
            Scanner s = new Scanner(f);
            List<String> input = new ArrayList<>();
            List<String> boxes = new ArrayList<>();
            String inputLine = s.nextLine() +",";
            String newInput ="";
            for(int i =0;i<inputLine.length();i++)
            {
                String letter = inputLine.substring(i,i+1);
                if(!letter.equals(","))
                {
                    newInput+=letter;
                }
                else{
                    input.add(newInput);
                    newInput="";
                }
            }
            System.out.println(findAllASCII(input));
            for(int i =0;i<256;i++)
            {
                boxes.add(i+":");
            }
            for(String items: input)
            {
                findBox(items,boxes);
            }
            //System.out.println(boxes);
            System.out.println(addBox(boxes));


        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found");
        }
    }
    public static int findAllASCII (List<String> input)
    {
        int total = 0;
        for(String item: input)
        {
            int value =0;
            for(int i =0;i<item.length();i++)
            {
                char there = item.charAt(i);
                value+=(int)there;
                value*=17;
                value%=256;

            }
            total+=value;
        }
        return total;
    }

    public static int findASCII (String input)
    {
        int total = 0;
        int value =0;
        for(int i =0;i<input.length();i++)
        {
            char there = input.charAt(i);
            value+=(int)there;
            value*=17;
            value%=256;
        }
        total+=value;
        return total;
    }

    public static List<String> findBox(String line,List<String> boxes)
    {
        int stopIndex = line.indexOf("=");
        String type = "add";
        if(stopIndex==-1) {stopIndex = line.indexOf("-"); type = "remove";}
        String boxCode = line.substring(0,stopIndex);
        int boxNum = findASCII(boxCode);
        if(type.equals("remove"))
        {
            String box = boxes.get(boxNum);
            int indexExactIndex = exactMatch(box,boxCode);
            if(exactMatch(box,boxCode)!=-1)
            {
                String newBox = box.substring(0,indexExactIndex);
                boolean isOver= false;
                for(int i =indexExactIndex;i<box.length();i++ )
                {
                    String letter = box.substring(i,i+1);
                    if(isOver)
                    {
                        newBox+=letter;
                    }
                    if(letter.equals(","))isOver = true;
                }
                //System.out.println(newBox);
                boxes.set(boxNum,newBox);
            }

        }
        else{
            String box = boxes.get(boxNum);
            int len = boxCode.length();
            int exactIndex = exactMatch(box,boxCode);
            if(exactIndex!=-1)
            {
                int end = -1;
                for(int i = exactIndex; i<box.length();i++)
                {
                    String letter = box.substring(i,i+1);
                    if(letter.equals(","))
                    {
                        end = i;
                        break;
                    }
                }
                box = box.substring(0,exactIndex+len) + line.substring(stopIndex+1) + box.substring(end);
            }
            else{
                box+=boxCode+line.substring(stopIndex+1)+",";
            }
            //System.out.println(box);
            if(box.length()>1)
            {
                boxes.set(boxNum, box);
            }
        }
        return boxes;
    }

    public static boolean isInt (String letter)
    {
        try{
            int num = Integer.parseInt(letter);
            return true;
        }
        catch(NumberFormatException a)
        {
            return false;
        }
    }

   public static int addBox(List<String> box)
    {
        int total =0;
        int count1 =1;
       for(String item : box)
       {
           item = item.substring(item.indexOf(":")+1);
           int count = 1;
           while(item.contains(","))
           {
               int index = item.indexOf(",");
               String num ="";
               for(int i =0;i<index;i++)
               {
                   String letter = item.substring(i,i+1);
                   if(isInt(letter))
                   {
                       num+=letter;
                   }
               }
               //System.out.println(num +" "+ count);
               total+= count*Integer.parseInt(num)*count1;
               count++;
               item = item.substring(index+1);
           }
          count1++;
       }
       return total;
    }

    public static int exactMatch(String line, String want)
    {
        int index =-1;
        int len = want.length();
        boolean status = false;
        while(!status)
        {
            int currentIndex = line.indexOf(want);
            if(currentIndex!=-1)
            {
                String test = line.substring(currentIndex, currentIndex+len);
                if(test.equals(want))
                {
                    status = true;
                    index = currentIndex;
                }
                else{
                    line = line.substring(0,currentIndex) +"." + line.substring(currentIndex+1);
                }

            }
            else{
                break;
            }
        }
        return index;
    }







}
