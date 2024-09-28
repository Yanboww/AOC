package AOC2023;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;
public class Day14{
    public static void main(String[] args) {
        File f;
        try{
            f = new File("InputFile");
            Scanner s = new Scanner(f);
            List<String> input = new ArrayList<>();
            while(s.hasNextLine())
            {
                input.add(s.nextLine());
            }
            System.out.println(findNorthWeight(input));
            List<String> north = north(input);
            List<String> west = west(north);
            List<String> south = south(west);
            List<String> east = east(south);
            List<String> start = east;
            int count =1;
            for(int i =0; i<999;i++)
            {
                //System.out.println(count);
                north = north(east);
                west = west(north);
                south = south(west);
                east = east(south);
                count++;
                if(east.equals(start))
                {
                    break;
                }
            }
            System.out.println(getWeight(east));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
    }
    public static List<String> getColumn(List<String> input)
    {
        List<String> column = new ArrayList<>();
        int len = input.get(0).length();
        for(int a =0; a<len;a++)
        {
            String line ="";
            for(int b =0;b<input.size();b++)
            {
                line+=input.get(b).substring(a,a+1);

            }
            column.add(line);
        }
        return column;
    }
    public static int findNorthWeight(List<String> input)
    {
       List<String> column = getColumn(input);
       int total =0;
       for(String item : column)
       {
           int value = 0;
           int len = item.length();
           while(item.contains("O"))
           {
               int index = item.indexOf("O");
               for(int i =index;i>=0;i--)
               {
                   String letter = item.substring(i,i+1);
                   if(letter.equals("#"))
                   {
                       item = item.substring(0,i+1) + "#" + item.substring(i+2);
                       if(i+1!=index)
                       {
                           item = item.substring(0,index) +"."+item.substring(index+1);
                       }
                       value+= len-(i+1);
                       break;
                   }
                   else if(i==0)
                   {
                       item = "#" +item.substring(1);
                       if(index !=0)
                       {
                           item = item.substring(0,index)+"."+item.substring(index+1);
                       }
                       value+=len;
                       break;
                   }
               }
           }
           total+=value;

       }
       return total;
    }

    public static List<String> north(List<String> input)
    {
        List<String> column = getColumn(input);
        List<String> north = new ArrayList<>();
        for(String item : column)
        {
            int len = item.length();
            while(item.contains("O"))
            {
                int index = item.indexOf("O");
                for(int i =index;i>=0;i--)
                {
                    String letter = item.substring(i,i+1);
                    if(letter.equals("#") || letter.equals("8"))
                    {
                        item = item.substring(0,i+1) + "8" + item.substring(i+2);
                        if(i+1!=index)
                        {
                            item = item.substring(0,index) +"."+item.substring(index+1);
                        }
                        break;
                    }
                    else if(i==0)
                    {
                        item = "8" +item.substring(1);
                        if(index !=0)
                        {
                            item = item.substring(0,index)+"."+item.substring(index+1);
                        }
                        break;
                    }
                }
            }
            north.add(item);

        }
        return getColumn(north);
    }

    public static List<String> west (List<String> input)
    {
        List<String> west = new ArrayList<>();
        for(String item : input)
        {
            int len = item.length();
            while(item.contains("8"))
            {
                int index = item.indexOf("8");
                for(int i =index;i>=0;i--)
                {
                    String letter = item.substring(i,i+1);
                    if(letter.equals("#") || letter.equals("7"))
                    {
                        item = item.substring(0,i+1) + "7" + item.substring(i+2);
                        if(i+1!=index)
                        {
                            item = item.substring(0,index) +"."+item.substring(index+1);
                        }
                        break;
                    }
                    else if(i==0)
                    {
                        item = "7" +item.substring(1);
                        if(index !=0)
                        {
                            item = item.substring(0,index)+"."+item.substring(index+1);
                        }
                        break;
                    }
                }
            }
            west.add(item);

        }
        return west;
    }

    public static List<String> south(List<String> input)
    {
        List<String> column = getColumn(input);
        List<String> south = new ArrayList<>();
        for(String item : column)
        {
            while(item.contains("7"))
            {
                int index = item.lastIndexOf("7");
                for(int i =index;i<item.length();i++)
                {
                    String letter = item.substring(i,i+1);
                    if(letter.equals("#") || letter.equals("6"))
                    {
                        item = item.substring(0,i-1) + "6" + item.substring(i);
                        if(i-1!=index)
                        {
                            item = item.substring(0,index) +"."+item.substring(index+1);
                        }
                        break;
                    }
                    else if(i==item.length()-1)
                    {
                        item = item.substring(0,item.length()-1) +"6";
                        if(index != item.length()-1)
                        {
                            item = item.substring(0,index)+"."+item.substring(index+1);
                        }
                        break;
                    }
                }
            }
            south.add(item);

        }
        return getColumn(south);
    }

    public static List<String> east(List<String> input)
    {
        List<String> east = new ArrayList<>();
        for(String item : input)
        {
            int len = item.length();
            while(item.contains("6"))
            {
                int index = item.lastIndexOf("6");
                for(int i =index;i<len;i++)
                {
                    String letter = item.substring(i,i+1);
                    if(letter.equals("#") || letter.equals("O"))
                    {
                        item = item.substring(0,i-1) + "O" + item.substring(i);
                        if(i-1!=index)
                        {
                            item = item.substring(0,index) +"."+item.substring(index+1);
                        }
                        break;
                    }
                    else if(i==len-1)
                    {
                        item = item.substring(0,len-1)+"O";
                        if(index !=len-1)
                        {
                            item = item.substring(0,index)+"."+item.substring(index+1);
                        }
                        break;
                    }
                }
            }
            east.add(item);

        }
        return east;
    }

    public static int getWeight(List<String> input)
    {
        List<String> column = getColumn(input);
        int total =0;
        for(String items: column)
        {
            int len = items.length();
           while(items.contains("O"))
           {
               int index = items.indexOf("O");
               total+=len-index;
               if(index==0)
               {
                   items = "." + items.substring(1);
               }
               else if(index == len-1)
               {
                   items = items.substring(0,index-1) + ".";
               }
               else{
                   items = items.substring(0,index) + "." + items.substring(index+1);
               }
           }

        }
        return total;
    }




}
