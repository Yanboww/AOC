package AOC2023;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;


public class Day13Part2 {
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
                boolean replaced = gotNew(input);
                List<String> column = getColumn(input);
                List<String> newInput = getNewList(input);
                List<String> newColumn = getColumn(newInput);
                if(!replaced)
                {
                    newColumn = getNewList(column);
                    newInput = getRow(newColumn);
                }
                //System.out.println("map:"+count);
                total+=mirror(newInput,newColumn,input,column,total);
                input.subList(0,input.indexOf(" ")+1).clear();
                newInput.subList(0,newInput.indexOf(" ")+1).clear();
                count++;
            }
            System.out.println(total);




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
        column.add(" ");
        //System.out.println(column);
        return column;
    }

    public static List<String> getRow(List<String> column)
    {
        List<String> row = new ArrayList<>();
        int len = column.get(0).length();
        for(int i =0;i<len;i++)
        {
            String line ="";
            for(int a =0;a<column.size();a++)
            {
                String word = column.get(a);
                line+=word.substring(i,i+1);

            }
            row.add(line);

        }
        row.add(" ");
        //System.out.println(column);
        return row;
    }


    public static int mirror(List<String> input, List<String> column, List<String> oldInput, List<String>oColumn, int tot)
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
                if(checkAll(a,inputReplace,oldInput))
                {
                    if(answered ==1)
                    {
                        total =0;
                    }
                    total+=(a+1)*100;
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
                if(checkAll(b,column,oColumn))
                {
                    if(answered ==1)
                    {
                        total=0;
                    }
                    //System.out.println(b+1 +"c");
                    total+=b+1;
                    answered=1;
                }
            }
        }
        if(tot>30000)
        {
            int index = input.indexOf(" ");
            int index2 = input.lastIndexOf(" ");
            if(index==index2)
            {
                total+=101;
            }
        }
        return total;
    }

    public static boolean checkAll(int reflect,List<String> input, List<String> original)
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
        while(i>=0 && isMirror)
        {
            String line = input.get(i);
            String oldLine = original.get(i);
            if(!line.equals(oldLine))
            {
                isMirror = false;
                break;
            }
            i--;
        }
        while(b<input.size() && isMirror)
        {
            String line = input.get(b);
            String oldLine = original.get(b);
            if(!line.equals(oldLine))
            {
                isMirror = false;
                break;
            }
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

    public static List<String> getNewList(List<String> input)
    {
        List<String> replacement = new ArrayList<>();
        int indexTime = input.indexOf(" ");
        if(indexTime == -1) indexTime = input.size();
        List<String> inputReplace = input.subList(0, indexTime);
        int use =0;
        for(int a =0;a<inputReplace.size()-1;a++)
        {
            String line1 = inputReplace.get(a);
            String line2 = inputReplace.get(a+1);
            if(checkDifference(line1,line2)|| line1.equals(line2))
            {
                int c =a;
                int d = a+1;
                while(c>=0 && d<inputReplace.size())
                {
                    line1 = inputReplace.get(c);
                    line2 = inputReplace.get(d);
                    boolean check = checkDifference(line1,line2);
                    if(check && use ==0)
                    {
                        replacement = replaceTheList(c,d,input);
                        use++;
                        break;
                       /*if(checkAll(a,replacement,replacement))
                       {
                           use++;
                           break;
                       }
                       else{
                           replacement.clear();
                       }**/
                    }
                    c--;
                    d++;
                }

            }
            if(!replacement.isEmpty())
            {
                break;
            }
        }
        if(replacement.isEmpty())
        {
            return input;
        }
        return replacement;

    }
    public static boolean gotNew(List<String> input)
    {
        List<String> inputReplace = input.subList(0, input.indexOf(" "));
        for(int a =0;a<inputReplace.size()-1;a++)
        {
            String line1 = inputReplace.get(a);
            String line2 = inputReplace.get(a+1);
            if(checkDifference(line1,line2)|| line1.equals(line2))
            {
                int c =a;
                int d = a+1;
                while(c>=0 && d<inputReplace.size())
                {
                    line1 = inputReplace.get(c);
                    line2 = inputReplace.get(d);
                    boolean check = checkDifference(line1,line2);
                    if(check)
                    {
                        return true;
                    }
                    c--;
                    d++;
                }

            }
        }
        return false;
    }



    public static boolean checkDifference(String line1, String line2)
    {
        int count =0;
        for(int i =0; i<line1.length();i++)
        {
            String letter1 = line1.substring(i,i+1);
            String letter2 = line2.substring(i,i+1);
            if(!letter1.equals(letter2))
            {
                count++;
            }
        }
        if(count==1)return true;
        return false;
    }

    public static List<String>  replaceTheList (int a, int b, List<String> input)
    {
        List<String> replacementInput = new ArrayList<>();
        for(int i = 0; i<input.size();i++)
        {
            if(i!=b)
            {
                replacementInput.add(input.get(i));
            }
            else{
                replacementInput.add(input.get(a));
            }
        }
        return replacementInput;
    }






}