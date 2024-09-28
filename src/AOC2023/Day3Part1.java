package AOC2023;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.List;

public class Day3Part1 {
    public static void main(String[] args) {
        File f;
        try{
            f = new File("InputFile");
            Scanner s = new Scanner(f);
            String symbol = "!@#$%^&()_-+=|?/><~`-*";
            String numList = "1234567890";
            List<String> input = new ArrayList<>();
            int total = 0;
            while(s.hasNextLine())
            {
                input.add(s.nextLine());

            }
            for(int i = 0;i<input.size();i++)
            {
                List<String> lineInt = new ArrayList<>();
                String line = input.get(i);
                String line2 = line+".";
                String num = "";
                for(int b = 0; b<line2.length();b++)
                {
                    String letter = line2.substring(b,b+1);
                    if (numList.contains(letter))
                    {
                        num+=letter;
                    }
                    else if(!numList.contains(letter) && !num.isEmpty())
                    {
                        lineInt.add(num);
                        num="";
                    }
                }
                for (int c = 0;c<lineInt.size();c++)
                {

                    String value = lineInt.get(c);
                    int len = value.length();
                    boolean status = false;
                    int index = line.indexOf(value);
                    if (index == 0)
                    {
                        line = ".." + line.substring(2);
                    }
                    else {
                        line = line.substring(0,index) + ".." + line.substring(index+2);
                    }
                    if(i == 0)
                    {
                        String ALine = input.get(i+1);
                        String sub = ".";
                        String sub1 =".";
                        String sub5 = ".";
                        String sub6 = ".";
                        String sub7 = ALine.substring(index,index+len);
                        if(index>0)
                        {
                            sub = line.substring(index-1,index);
                            sub5 = ALine.substring(index-1,index);

                        }
                        if (index<line.length()-1)
                        {
                            if(index+len < line.length())
                            {
                                sub1 = line.substring(index+len, index+len+1);
                                sub6 = ALine.substring(index+len,index+len+1);
                            }
                            else{
                                sub1 =".";
                                sub6 = ".";
                            }
                        }
                        if(symbol.contains(sub))
                        {
                            status = true;
                        }
                        else if(symbol.contains(sub1))
                        {
                            status = true;
                        }
                        else if(symbol.contains(sub5))
                        {
                            status = true;
                        }
                        else if(symbol.contains(sub6))
                        {
                            status = true;
                        }
                        for (int d = 0; d<sub7.length();d++)
                        {
                            String two = sub7.substring(d,d+1);
                            if (symbol.contains(two))
                            {
                                status = true;
                            }
                        }
                    }
                    else if(i == input.size()-1)
                    {
                        String pLine = input.get(i-1);
                        String sub = ".";
                        String sub1 =".";
                        String sub2 = ".";
                        String sub3 = ".";
                        String sub4 = pLine.substring(index,index+len);
                        if(index>0)
                        {
                            sub = line.substring(index-1,index);
                            sub2 = pLine.substring(index-1,index);
                        }
                        if (index<line.length()-1)
                        {
                            if(index+len < line.length())
                            {
                                sub1 = line.substring(index+len, index+len+1);
                                sub3 = pLine.substring(index+len,index+len+1);
                            }
                            else{
                                sub1 =".";
                                sub3 =".";
                            }

                        }
                        if(symbol.contains(sub))
                        {
                            status = true;
                        }
                        else if(symbol.contains(sub1))
                        {
                            status = true;
                        }
                        else if(symbol.contains(sub2))
                        {
                            status = true;
                        }
                        else if(symbol.contains(sub3))
                        {
                            status = true;
                        }
                        for (int d = 0; d<sub4.length();d++)
                        {
                            String one = sub4.substring(d,d+1);
                            if (symbol.contains(one))
                            {
                                status = true;
                            }
                        }
                    }
                    else{
                        String pLine = input.get(i-1);
                        String ALine = input.get(i+1);
                        String sub = ".";
                        String sub1 =".";
                        String sub2 = ".";
                        String sub3 = ".";
                        String sub4 = pLine.substring(index,index+len);
                        String sub5 = ".";
                        String sub6 = ".";
                        String sub7 = ALine.substring(index,index+len);
                        if(index>0)
                        {
                            sub = line.substring(index-1,index);
                            sub2 = pLine.substring(index-1,index);
                            sub5 = ALine.substring(index-1,index);

                        }
                        if (index<line.length()-1)
                        {
                            if(index+len < line.length())
                            {
                                sub1 = line.substring(index+len, index+len+1);
                                sub3 = pLine.substring(index+len,index+len+1);
                                sub6 = ALine.substring(index+len,index+len+1);
                            }
                            else{
                                sub1 =".";
                                sub3 =".";
                                sub6 =".";
                            }
                        }
                        if(symbol.contains(sub))
                        {
                            status = true;
                        }
                        else if(symbol.contains(sub1))
                        {
                            status = true;
                        }
                        else if(symbol.contains(sub2))
                        {
                            status = true;
                        }
                        else if(symbol.contains(sub3))
                        {
                            status = true;
                        }
                        else if(symbol.contains(sub5))
                        {
                            status = true;
                        }
                        else if(symbol.contains(sub6))
                        {
                            status = true;
                        }
                        for (int d = 0; d<sub4.length();d++)
                        {
                            String one = sub4.substring(d,d+1);
                            String two = sub7.substring(d,d+1);

                            if (symbol.contains(one) || symbol.contains(two))
                            {
                                status = true;
                            }
                        }
                    }
                    if (status)
                    {
                        total += Integer.parseInt(value);
                        //System.out.println(value);
                    }


                }

            }
            System.out.println(total);

        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Does not Exist");
        }

    }

}