package AOC2023;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;
public class Day6 {
    public static void main(String[] args) {
        File f;
        try{
            f = new File("Input");
            Scanner s = new Scanner(f);
            //List<Integer> time = new ArrayList<>();
            //List<Integer> distance = new ArrayList<>();
            List<Double> time = new ArrayList<>();
            List<Double> distance = new ArrayList<>();
            String timeLine = s.nextLine() +".";
            String distanceLine = s.nextLine()+".";
            int total =1;
            //getAllIndividualNum(time,timeLine);
            //getAllIndividualNum(distance, distanceLine);
            getAllNum(time,timeLine);
            getAllNum(distance, distanceLine);
            //List<Integer> totalList = new ArrayList<>();
            List<Integer> totalList = new ArrayList<>();
            for(int i = 0;i<time.size();i++)
            {
                int count =0;
                double timeLim = time.get(i);
                double dist = distance.get(i);
                for( int c =1; c<timeLim;c++)
                {
                    double score =(timeLim-c)*c;
                    if (score>dist)
                    {
                        count++;
                    }
                }
                if(count>0)
                {
                    totalList.add(count);
                }
            }
            for(int d = 0; d<totalList.size();d++)
            {
                int multiple = totalList.get(d);
                total*=multiple;
            }
            System.out.println(total);

        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }

    }

    public static void getAllIndividualNum(List<Integer> list, String line)
    {
        String numList = "1234567890";
        String num="";
        for(int b = 0; b<line.length();b++)
        {
            String letter = line.substring(b,b+1);
            if (numList.contains(letter))
            {
                num+=letter;
            }
            else if(!numList.contains(letter) && !num.isEmpty())
            {
                int value = Integer.parseInt(num);
                list.add(value);
                num="";
            }
        }
    }
    public static void getAllNum(List<Double> list, String line)
    {
        String numList = "1234567890";
        String num="";
        for(int b = 0; b<line.length();b++)
        {
            String letter = line.substring(b,b+1);
            if (numList.contains(letter))
            {
                num+=letter;
            }
        }
        if(!num.isEmpty())
        {
            double value = Double.parseDouble(num);
            list.add(value);
        }
    }


}
