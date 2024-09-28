package AOC2023;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;
public class Day9 {
    public static void main(String[] args) {
        File f;
        try{
            f = new File("InputFile");
            Scanner s = new Scanner(f);
            List<String> inputs = new ArrayList<>();
            List <String> prediction;
            List<Integer> addLines = new ArrayList<>();
            int totals =0;
            while(s.hasNextLine())
            {
                inputs.add(s.nextLine());
            }
            for(int i = 0; i<inputs.size();i++)
            {
                prediction = predictPattern(inputs,i);
                //addLines.add(getNextInt(prediction,inputs,i));
                addLines.add(getNextInt2(prediction,inputs,i));

            }
            for(int items: addLines)
            {
                totals+=items;
            }
            System.out.println(totals);

        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not Found!");
        }

    }
    public static List<String> predictPattern(List<String> inputs, int index)
    {
        List<String> diff = new ArrayList<>();
        List<Integer> line = new ArrayList<>();
        String inputLine = inputs.get(index)+" ";
        String num = "";
        int next =0;
        for(int a = 0; a<inputLine.length();a++)
        {
            String letter = inputLine.substring(a,a+1);
            if (!letter.equals(" "))
            {
                num+=letter;
            }
            else
            {
                line.add(Integer.parseInt(num));
                num="";
            }
        }
        for(int b = 1;b<line.size();b++)
        {
            int one = line.get(b-1);
            int two = line.get(b);
            String three = Integer.toString(two-one);
            diff.add(three);
        }
        next = Integer.parseInt(diff.get(diff.size()-1));
        int start =1;
        while(next!=0)
        {
            diff.add(" ");
            for(int c = start;c<diff.lastIndexOf(" ");c++)
            {
                int one = Integer.parseInt(diff.get(c-1));
                int two = Integer.parseInt(diff.get(c));
                String three = Integer.toString(two-one);
                diff.add(three);
            }
            next = Integer.parseInt(diff.get(diff.size()-1));
            start = diff.lastIndexOf(" ")+2;
        }
        return diff;

    }
    //part 1 method
    public static int getNextInt(List<String> diff,List<String> inputs, int index)
    {
        String line = inputs.get(index);
        int beginning =Integer.parseInt(line.substring(line.lastIndexOf(" ")+1));
        List<Integer> add = new ArrayList<>();
        int total =0;
        for(int d = 0;d<diff.size()-1;d++)
        {
            String one = diff.get(d);
            String two = diff.get(d+1);
            if(two.equals(" "))
            {
                add.add(Integer.parseInt(one));
            }
        }
        for(int item: add)
        {
            total+=item;
        }
        total+=beginning;
        return total;
    }

    //part 2 method
    public static int getNextInt2(List<String> diff,List<String> inputs, int index)
    {
        String line = inputs.get(index);
        int beginning =Integer.parseInt(line.substring(0,line.indexOf(" ")));
        List<Integer> add = new ArrayList<>();
        int total =0;
        add.add(Integer.parseInt(diff.get(0)));
        for(int d = 0;d<diff.size()-1;d++)
        {
            String one = diff.get(d);
            String two = diff.get(d+1);
            if(one.equals(" "))
            {
                add.add(Integer.parseInt(two));
            }
        }

        for(int z = add.size()-1;z>=0;z--)
        {
            total = -1*total + add.get(z);
        }
        //System.out.println(add);
        total = beginning - total;
        //System.out.println(total);
        return total;
    }




}
