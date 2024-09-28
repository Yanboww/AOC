package AOC2023;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.List;

public class Day3Part2 {
    public static void main(String[] args) {
        File f;
        try{
            f = new File("InputFile");
            Scanner s = new Scanner(f);
            String numList = "1234567890";
            List<String> input = new ArrayList<>();
            List<String> lineInt = new ArrayList<>();
            int total = 0;
            while(s.hasNextLine())
            {
                input.add(s.nextLine());

            }
            for(int i = 0;i<input.size();i++)
            {
                String line = input.get(i);
                String line2 = line+".";
                String num = "";
                for(int b = 0; b<line2.length();b++)
                {
                    String letter = line2.substring(b,b+1);
                    if(letter.equals("*"))
                    {
                        lineInt.add(letter);
                    }
                }

            }
            int i=0;
            while (!lineInt.isEmpty() && i<input.size())
            {
                List<String> aroundSy = new ArrayList<>();
                List<Integer> lineAroundSy = new ArrayList<>();
                String value = "*";
                String line = input.get(i);
                int len = 1;
                int index = line.indexOf(value);
                while(index!=-1)
                {
                    lineInt.remove(0);
                    if (index == 0)
                    {
                        line = "." + line.substring(1);
                    }
                    else {
                        line = line.substring(0,index) + "." + line.substring(index+1);
                    }
                    String lineKeep=line;
                    if(i==0)
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
                        if(isInt(sub) || isInt(sub1) || isInt(sub5)||isInt(sub6) || isInt(sub7))
                        {
                            if(index ==1)
                            {
                                line = line.substring(0,index+4)+".";
                                ALine = ALine.substring(0,index+4)+".";
                            }
                            else if(index == line.length()-1)
                            {
                                line = line.substring(index-3)+".";
                                ALine = ALine.substring(index-3)+".";
                            }
                            else{
                                line = line.substring(index-3,index+4)+".";
                                ALine = ALine.substring(index-3,index+4)+".";
                            }
                            String num="";
                            String num2="";
                            for(int b = 0; b<line.length();b++)
                            {
                                String letter = line.substring(b,b+1);
                                if (numList.contains(letter))
                                {
                                    num+=letter;
                                }
                                else if(!numList.contains(letter) && !num.isEmpty())
                                {
                                    aroundSy.add(num);
                                    num="";
                                    lineAroundSy.add(i);
                                }

                            }
                            for(int b = 0; b<ALine.length();b++)
                            {
                                String letter = ALine.substring(b,b+1);
                                if (numList.contains(letter))
                                {
                                    num2+=letter;
                                }
                                else if(!numList.contains(letter) && !num2.isEmpty())
                                {
                                    aroundSy.add(num2);
                                    num2="";
                                    lineAroundSy.add(i+1);
                                }
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
                        if(isInt(sub) || isInt(sub1) || isInt(sub2)||isInt(sub3) || isInt(sub4))
                        {
                            if(index ==1)
                            {
                                line = line.substring(0,index+4)+".";
                                pLine = pLine.substring(0,index+4)+".";
                            }
                            else if(index == line.length()-1)
                            {
                                line = line.substring(index-3)+".";
                                pLine = pLine.substring(index-3)+".";
                            }
                            else{
                                line = line.substring(index-3,index+4)+".";
                                pLine = pLine.substring(index-3,index+4)+".";
                            }
                            String num="";
                            String num2="";
                            for(int b = 0; b<line.length();b++)
                            {
                                String letter = line.substring(b,b+1);
                                if (numList.contains(letter))
                                {
                                    num+=letter;
                                }
                                else if(!numList.contains(letter) && !num.isEmpty())
                                {
                                    aroundSy.add(num);
                                    num="";
                                    lineAroundSy.add(i);
                                }
                            }
                            for(int b = 0; b<pLine.length();b++)
                            {
                                String letter = pLine.substring(b,b+1);
                                if (numList.contains(letter))
                                {
                                    num2+=letter;
                                }
                                else if(!numList.contains(letter) && !num2.isEmpty())
                                {
                                    aroundSy.add(num2);
                                    num2="";
                                    lineAroundSy.add(i-1);
                                }
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
                        if(isInt(sub) || isInt(sub1) || isInt(sub2)||isInt(sub3) || isInt(sub4)|| isInt(sub5)||isInt(sub6)||isInt(sub7))
                        {
                            if(index ==1)
                            {
                                line = line.substring(0,index+4)+".";
                                pLine = pLine.substring(0,index+4)+".";
                                ALine = ALine.substring(0,index+4)+".";
                            }
                            else if(index == line.length()-1)
                            {
                                line = line.substring(index-3)+".";
                                pLine = pLine.substring(index-3)+".";
                                ALine = ALine.substring(index-3)+".";
                            }
                            else{
                                line = line.substring(index-3,index+4)+".";
                                pLine = pLine.substring(index-3,index+4)+".";
                                ALine = ALine.substring(index-3,index+4)+".";
                            }
                            //System.out.println(pLine);
                            //System.out.println(line);
                            //System.out.println(ALine);
                            //System.out.println("aehdkjawbfabjwfbjwb");
                            String num="";
                            String num2="";
                            String num3="";
                            for(int b = 0; b<line.length();b++)
                            {
                                String letter = line.substring(b,b+1);
                                if (numList.contains(letter))
                                {
                                    num+=letter;
                                }
                                else if(!numList.contains(letter) && !num.isEmpty())
                                {
                                    aroundSy.add(num);
                                    num="";
                                    lineAroundSy.add(i);
                                }
                            }
                            for(int b = 0; b<ALine.length();b++)
                            {
                                String letter = ALine.substring(b,b+1);
                                if (numList.contains(letter))
                                {
                                    num2+=letter;
                                }
                                else if(!numList.contains(letter) && !num2.isEmpty())
                                {
                                    aroundSy.add(num2);
                                    num2="";
                                    lineAroundSy.add(i+1);
                                }
                            }
                            for(int b = 0; b<pLine.length();b++)
                            {

                                String letter = pLine.substring(b,b+1);
                                if (numList.contains(letter))
                                {
                                    num3+=letter;
                                    //System.out.println(num3);

                                }
                                else if(!numList.contains(letter) && !num3.isEmpty())
                                {
                                    aroundSy.add(num3);
                                    num3="";
                                    lineAroundSy.add(i-1);
                                }
                            }

                        }
                    }
                    if(aroundSy.size()>=2)
                    {
                        int one =0;
                        int two = 0;
                        //System.out.println(aroundSy);
                        for (int b = 0; b< aroundSy.size();b++)
                        {
                            int val = Integer.parseInt(aroundSy.get(b));
                            int lineOfVal = lineAroundSy.get(b);
                            if (isAroundSy(val,lineOfVal,input,index))
                            {
                                int symbolVal = indexOfSymbol(val,lineOfVal,input,index);
                                //System.out.println(val);
                                //System.out.println(symbolVal);
                                //System.out.println(index);
                                if(symbolVal==index) {
                                    if (one != 0) {
                                        two = val;
                                    } else {
                                        one = val;
                                    }
                                }

                            }

                        }
                        //System.out.println(one);
                        //System.out.println(two);
                        total+= one*two;
                        //System.out.println(one + " " + two);
                    }
                    line = lineKeep;
                    index = line.indexOf("*");
                    aroundSy.clear();
                    lineAroundSy.clear();
                }
                i++;

            }
            System.out.println(total);

        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Does not Exist");
        }

    }
    public static boolean isInt(String str) {
        try {
            if(str.length()==1)
            {
                Integer.parseInt(str);
            }
            else{
                for(int l =0;l<str.length();l++)
                {
                    String letter = str.substring(l,l+1);
                    Integer.parseInt(letter);
                }
            }
            return true;
        } catch(NumberFormatException a){
            return false;
        }
    }

    public static int isExactNum(int nums, int i, List<String> input, int expect)
    {
        String numList ="1234567890";
        String number = Integer.toString(nums);
        String line = input.get(i);
        int index =-1;
        for (int p = 0; p < line.length();p++)
        {
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
                    if(num.equals(number))
                    {
                        int len = num.length();
                        index = b-len;
                        if(expect-index < 10&& expect-index>-10)
                        {
                            return index;
                        }

                    }
                    num="";
                }

            }

        }
        if(index==-1)
        {
            index = line.indexOf(number);
        }
        return index;
    }


    public static boolean isAroundSy(int num, int i,List<String> input, int expect)
    {
        String number = Integer.toString(num);
        String line = input.get(i);
        int len = number.length();
        int index = isExactNum(num,i,input, expect);
        //System.out.println(line);
        //System.out.println(index);
        //System.out.println(num);
        //System.out.println("------");
        String symbol = "*";
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
                return true;
            }
            else if(symbol.contains(sub1))
            {
                return true;
            }
            else if(symbol.contains(sub5))
            {
                return true;
            }
            else if(symbol.contains(sub6))
            {
                return true;
            }
            for (int d = 0; d<sub7.length();d++)
            {
                String two = sub7.substring(d,d+1);
                if (symbol.contains(two))
                {
                    return true;
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
                return true;
            }
            else if(symbol.contains(sub1))
            {
                return true;
            }
            else if(symbol.contains(sub2))
            {
                return true;
            }
            else if(symbol.contains(sub3))
            {
                return true;
            }
            for (int d = 0; d<sub4.length();d++)
            {
                String one = sub4.substring(d,d+1);
                if (symbol.contains(one))
                {
                    return true;
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
                return true;
            }
            else if(symbol.contains(sub1))
            {
                return true;
            }
            else if(symbol.contains(sub2))
            {
                return true;
            }
            else if(symbol.contains(sub3))
            {
                return true;
            }
            else if(symbol.contains(sub5))
            {
                return true;
            }
            else if(symbol.contains(sub6))
            {
                return true;
            }
            for (int d = 0; d<sub4.length();d++)
            {
                String one = sub4.substring(d,d+1);
                String two = sub7.substring(d,d+1);

                if (symbol.contains(one) || symbol.contains(two))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public static int indexOfSymbol(int num, int i,List<String> input, int expect)
    {
        String number = Integer.toString(num);
        String line = input.get(i);
        int len = number.length();
        int index = isExactNum(num,i,input,expect);
        int returnValue = -1;
        String symbol = "*";
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
                returnValue = index-1;
            }
            else if(symbol.contains(sub1))
            {
                returnValue = index+len;
            }
            else if(symbol.contains(sub5))
            {
                returnValue = index-1;
            }
            else if(symbol.contains(sub6))
            {
                returnValue = index+len;
            }
            for (int d = 0; d<sub7.length();d++)
            {
                String two = sub7.substring(d,d+1);
                if (symbol.contains(two))
                {
                    returnValue = index+d;
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
                returnValue = index-1;
            }
            else if(symbol.contains(sub1))
            {
                returnValue = index+len;
            }
            else if(symbol.contains(sub2))
            {
                returnValue = index-1;
            }
            else if(symbol.contains(sub3))
            {
                returnValue = index+len;
            }
            for (int d = 0; d<sub4.length();d++)
            {
                String one = sub4.substring(d,d+1);
                if (symbol.contains(one))
                {
                    returnValue = index+d;
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
                returnValue = index-1;
            }
            else if(symbol.contains(sub1))
            {
                returnValue = index+len;
            }
            else if(symbol.contains(sub2))
            {
                returnValue = index-1;
            }
            else if(symbol.contains(sub3))
            {
                returnValue = index+len;
            }
            else if(symbol.contains(sub5))
            {
                returnValue = index-1;
            }
            else if(symbol.contains(sub6))
            {
                returnValue = index+len;
            }
            for (int d = 0; d<sub4.length();d++)
            {
                String one = sub4.substring(d,d+1);
                String two = sub7.substring(d,d+1);

                if (symbol.contains(one) || symbol.contains(two))
                {
                    returnValue = index+d;
                }
            }
        }
        return returnValue;
    }
}
