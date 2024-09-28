package AOC2023;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.io.FileNotFoundException;
public class Day19Part1 {
    public static void main(String[] args) {
        File f;
        try{
            f = new File("InputFile");
            Scanner s = new Scanner(f);
            List<String> workFlow = new ArrayList<>();
            List<String> parts = new ArrayList<>();
            List<Integer> accepted = new ArrayList<>();
            int start = 0;
            boolean reached = false;
            while(s.hasNextLine())
            {
                String next = s.nextLine();
                if(next.isEmpty())break;
                workFlow.add(next);
                if(!reached && !next.contains("in"))
                {
                    start++;
                }
                else {
                    reached= true;
                }

            }
            while(s.hasNextLine())
            {
                parts.add(s.nextLine());
            }
            s.close();
            for(String item: parts)
            {
                check(start,workFlow,item,accepted);
            }
            System.out.println(addThem(accepted));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
    }
    public static boolean check(int start, List<String> workFlow, String parts, List<Integer> add)
    {
        int indexX = parts.indexOf("x");
        int indexM = parts.indexOf("m");
        int indexA = parts.indexOf("a");
        int indexS = parts.indexOf("trial");
        String x = parts.substring(indexX+2,indexM-1);
        String m = parts.substring(indexM+2,indexA-1);
        String a = parts.substring(indexA+2,indexS-1);
        String s = parts.substring(indexS+2,parts.indexOf("}"));
        String line = nextWork(workFlow,"in");
        String next = "amongus";
        while (!next.equals("A") && !next.equals("R"))
        {
            List<String> conditions = new ArrayList<>();
            String part ="";
            int lastIndex = line.lastIndexOf(",");
            line+=",";
            for(int i =0;i<line.length();i++)
            {
                String letter = line.substring(i,i+1);
                if(!letter.equals(",") || lastIndex ==i)
                {
                    part+=letter;
                }
                else{
                    conditions.add(part);
                    part ="";
                }
            }
            int count =0;
            //System.out.println(conditions.size());
            //System.out.print(next);
            //System.out.println(conditions);
            for(String items: conditions)
            {
                String letter = items.substring(0,1);
                String conditionType = items.substring(1,2);
                int num = Integer.parseInt(items.substring(2,items.indexOf(":")));
                int lastItem = conditions.size()-1;
                boolean status;
                if(letter.equals("x"))
                {
                    if(conditionType.equals(">"))
                    {
                        status = Integer.parseInt(x)> num;
                    }
                    else{
                        status = Integer.parseInt(x)<num;
                    }
                }
                else if(letter.equals("m"))
                {
                    if(conditionType.equals(">"))
                    {
                        status = Integer.parseInt(m)> num;
                    }
                    else{
                        status = Integer.parseInt(m)<num;
                    }
                }
                else if(letter.equals("a"))
                {
                    if(conditionType.equals(">"))
                    {
                        status = Integer.parseInt(a)> num;
                    }
                    else{
                        status = Integer.parseInt(a)<num;
                    }
                }
                else
                {
                    if(conditionType.equals(">"))
                    {
                        status = Integer.parseInt(s)> num;
                    }
                    else{
                        status = Integer.parseInt(s)<num;
                    }
                }
                if (count == lastItem)
                {
                    if(status)
                    {
                        next = items.substring(items.indexOf(":")+1,items.lastIndexOf(","));
                        line = nextWork(workFlow,next);
                        break;
                    }
                    else{
                        next = items.substring(items.lastIndexOf(",")+1);
                        line = nextWork(workFlow,next);
                        break;
                    }
                }
                else{
                    if(status){
                        next = items.substring(items.indexOf(":")+1);
                        line = nextWork(workFlow,next);
                        break;
                    }
                }
               count++;
                //System.out.println(line);
            }
        }
        if(next.equals("A"))
        {
            add.add(Integer.parseInt(x));
            add.add(Integer.parseInt(a));
            add.add(Integer.parseInt(m));
            add.add(Integer.parseInt(s));
            return true;
        }
        return false;
    }

    public static String nextWork(List<String> workFlow, String next)
    {
        String line ="";
        for(int i =0;i<workFlow.size();i++)
        {
            line = workFlow.get(i);
            if(line.contains(next+"{") && line.indexOf(next)==0) break;
        }
        //System.out.println(line.substring(line.indexOf("{")+1, line.indexOf("}")));
        return line.substring(line.indexOf("{")+1, line.indexOf("}"));
    }

    public static long addThem(List<Integer> list)
    {
        //System.out.println(list);
        long total = 0;
        for(int item : list)
        {
            total+=item;
        }
        return total;
    }


}
