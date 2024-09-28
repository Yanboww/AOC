package AOC2023;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
public class Day21Part1 {
    public static void main(String[] args) {
        File f;
        try{
            f = new File("Trial");
            Scanner s = new Scanner(f);
            List<String> inputs = new ArrayList<>();
            List<String> queue = new ArrayList<>();
            int startRow = 0;
            int startIndex = 0;
            boolean found = false;
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                inputs.add(line);
                if(line.contains("S"))
                {
                    found = true;
                    startIndex = line.indexOf("S");
                }
                if(!found) startRow++;
            }
            queue.add(Integer.toString(startRow) + " " + Integer.toString(startIndex));
            System.out.println(move(inputs,queue,64));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
    }

    public static long move(List<String> inputs, List<String> queue, int repeat)
    {
        List<String> possible = new ArrayList<>();
        for(int i = 0;i<repeat;i++)
        {
            for(String items : queue)
            {
                int row = Integer.parseInt(items.substring(0,items.indexOf(" ")));
                //System.out.println(row);
                int index = Integer.parseInt(items.substring(items.indexOf(" ")+1));
                int row1 = 69420;
                int index1 = 69420;
                int row2 = 69420;
                int index2 = 69420;
                int row3 = 69420;
                int index3 = 69420;
                int row4 = 69420;
                int index4 = 69420;
                if(row>0)
                {
                    row1 = row-1;
                    index1 = index;
                }
                if(row<inputs.size()-1)
                {
                    row2 = row+1;
                    index2 = index;
                }
                if(index>0)
                {
                    row3 = row;
                    index3 = index-1;
                }
                if(index<inputs.get(0).length()-1)
                {
                    row4 = row;
                    index4 = index+1;
                }
                if(test(row1,index1,inputs))
                {
                    String addition = Integer.toString(row1) + " " + Integer.toString(index1);
                    if(!possible.contains(addition))
                    {
                        possible.add(addition);
                    }
                }
                if(test(row2,index2,inputs))
                {
                    String addition = Integer.toString(row2) + " " + Integer.toString(index2);
                    if(!possible.contains(addition))
                    {
                        possible.add(addition);
                    }
                }
                if(test(row3,index3,inputs))
                {
                    String addition = Integer.toString(row3) + " " + Integer.toString(index3);
                    if(!possible.contains(addition))
                    {
                        possible.add(addition);
                    }
                }
                if(test(row4,index4,inputs))
                {
                    String addition = Integer.toString(row4) + " " + Integer.toString(index4);
                    if(!possible.contains(addition))
                    {
                        possible.add(addition);
                    }
                }

            }
            queue.clear();
            changeList(queue,possible);
            if(i!=repeat-1)
            {
               possible.clear();
            }

        }
        return possible.size();
    }

    public static boolean test(int row, int index, List<String> inputs)
    {
        //System.out.println(row);
        //System.out.println(index);
        if(row!=69420 && index!=69420)
        {
            String letter = inputs.get(row).substring(index,index+1);
            if(!letter.equals("#")) return true;
        }
        return false;
    }

    public static void changeList (List<String> queue, List<String> possible)
    {
        for(String items:possible)
        {
            queue.add(items);
        }
    }

}
