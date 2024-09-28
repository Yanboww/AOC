package AOC2023;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;


public class Day13 {
    public static List<String> file = new ArrayList<>();
    public static void main(String[] args) {
        File f;
        try{
            f = new File("InputFile");
            Scanner s = new Scanner(f);
            List<String> input = new ArrayList<>();
            int total =0;
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                if(!line.isEmpty())
                {
                    input.add(line);
                }
                else{
                    input.add(" ");
                }
            }
            input.add(" ");
            int count =1;
            while(!input.isEmpty())
            {

                List<String> column = getColumn(input);
                total+=mirror(input,column);
                input.subList(0,input.indexOf(" ")+1).clear();
                count++;
            }
            System.out.println(total);
            System.out.println(file);




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
            for(int b =0;b< input.indexOf(" ");b++)
            {
                line+=input.get(b).substring(a,a+1);;


            }
            column.add(line);
        }
        //column.add(" ");
        //System.out.println(column);
        return column;
    }

    public static int mirror(List<String> input, List<String> column)
    {
        List<String> inputReplace = input.subList(0, input.indexOf(" "));
        int total =0;
        int answered =0;
        for(int a =0;a<inputReplace.size()-1;a++)
        {
            String line1 = inputReplace.get(a);
            String line2 = inputReplace.get(a+1);
            if(line1.equals(line2))
            {
                if(checkAll(a,inputReplace))
                {
                    if(answered ==1)
                    {
                        total =0;
                        file.remove(file.size()-1);
                    }
                    total+=(a+1)*100;
                    file.add(a+1 +"r");
                    answered=1;
                    //System.out.println(a+1+"h");
                }

            }
        }
        answered =0;
        for(int b=0;b<column.size()-1;b++)
        {
            String line1 = column.get(b);
            String line2 = column.get(b+1);
            //System.out.println(column);
            if( line1.equals(line2))
            {
                if(checkAll(b,column))
                {
                    if(answered ==1)
                    {
                        total=0;
                        file.remove(file.size()-1);
                    }
                    //System.out.println(b+1 +"c");
                    total+=b+1;
                    file.add(b+1+"c");
                    answered=1;
                }
            }
        }
        file.add(" ");
        return total;
    }

    public static boolean checkAll(int reflect,List<String> input)
    {
        boolean isMirror = false;
        int i = reflect;
        int b = reflect+1;
        while(i>=0 && b<input.size())
        {
            String line = input.get(i);
            String line2 = input.get(b);
            if(line.equals(line2))
            {
                isMirror = true;
            }
            else{
                isMirror = false;
                break;
            }
            i--;
            b++;
        }
        if(reflect+1==input.size()-1)
        {
            isMirror =true;
        }
        if(reflect==0)
        {
            isMirror =true;
        }
        return isMirror;
    }


}




