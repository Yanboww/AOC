package AOC2024;
import java.util.*;
import java.io.*;
public class Day4 {
    public static void main(String[] args) {
        System.out.println(countXmas("inputs/AOC2024Inputs/day4", 1));
        System.out.println(countXmas("inputs/AOC2024Inputs/day4", 2));
        //System.out.println(countXmas("inputs/trial", 2));
    }
    public static int countXmas(String fileName, int part)
    {
        File f = new File(fileName);
        int length = 140;
        if(fileName.contains("trial")) length = 10;
        String[][] input = new String[length][];
        try{
            Scanner s = new Scanner(f);
            int index = 0;
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                String[] temp = new String[line.length()];
                for(int i = 0; i < line.length(); i++)
                {
                    temp[i] = line.substring(i,i+1);
                }
                input[index] = temp;
                index++;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        if(part == 1) return xMasCount(input);
        return masButXCount(input);
    }

    public static int xMasCount(String[][] input)
    {
        int count = 0;
        for(int r = 0; r < input.length; r++)
        {
            for(int c = 0; c < input[r].length; c++)
            {
                if(input[r][c].equals("X")) count+= checkXmas(input, r, c);
            }
        }
        return count;
    }

    public static int masButXCount(String[][] input)
    {
        int count = 0;
        for(int r = 0; r < input.length; r++)
        {
            for(int c = 0; c < input[r].length; c++)
            {
                if(input[r][c].equals("A")) count+= checkMas(input, r, c);
            }
        }
        return count;
    }

    public static int checkMas(String[][] input, int r, int c)
    {
        String key ="MAS SAM";
        if(r-1 >= 0 && r+1 < input.length && c+1 < input[r].length && c-1 >=0)
        {
            String temp1 = "";
            String temp2 = "";
            int cTemp = c-1;
            for(int i = r-1; i <= r+1; i++)
            {
                temp1+=input[i][cTemp];
                cTemp++;
            }
            cTemp = c+1;
            for(int i = r-1; i <= r+1; i++)
            {
                temp2+=input[i][cTemp];
                cTemp--;
            }
            if(key.contains(temp1) && key.contains(temp2)) return 1;
        }
        return 0;
    }

    public static int checkXmas(String[][] input, int r, int c)
    {
        int count = 0;
        //left
        if(c-3 >= 0){
            if(checkLeft(input, r, c)) count++;
            if(checkLeftBottom(input,r,c)) count++;
            if(checkLeftUp(input,r,c)) count++;
        }
        //right
        if(c+3 < input[r].length)
        {
           if(checkRight(input,r,c)) count++;
           if(checkRightUp(input,r,c)) count++;
           if(checkRightDown(input,r,c)) count++;
        }
        //up/down
        if(checkUp(input, r, c)) count++;
        if(checkDown(input,r,c)) count++;
        return count;
    }

    public static boolean checkLeft(String[][] input, int r, int c)
    {
        String temp ="";
        for(int i = c; i >=c-3; i--)
        {
            temp+=input[r][i];
        }
        return temp.equals("XMAS");
    }

    public static boolean checkLeftBottom(String[][] input, int r, int c)
    {
       if(r+3 < input.length)
       {
           String temp ="";
           for(int i = c; i >=c-3; i--)
           {
               temp+=input[r][i];
               r++;
           }
           return temp.equals("XMAS");
       }
       return false;
    }

    public static boolean checkLeftUp(String[][] input, int r, int c)
    {
        if(r-3 >=0)
        {
            String temp ="";
            for(int i = c; i >=c-3; i--)
            {
                temp+=input[r][i];
                r--;
            }
            return temp.equals("XMAS");
        }
        return false;
    }

    public static boolean checkRight(String[][] input, int r, int c)
    {
        String temp = "";
        for(int i = c; i <= c+3; i++)
        {
            temp+=input[r][i];
        }
        return temp.equals("XMAS");
    }

    public static boolean checkRightUp(String[][] input, int r, int c)
    {
       if(r-3 >= 0)
       {
           String temp = "";
           for(int i = c; i <= c+3; i++)
           {
               temp+=input[r][i];
               r--;
           }
           return temp.equals("XMAS");
       }
       return false;
    }

    public static boolean checkRightDown(String[][] input, int r, int c)
    {
        if(r+3 < input.length)
        {
            String temp = "";
            for(int i = c; i <= c+3; i++)
            {
                temp+=input[r][i];
                r++;
            }
            return temp.equals("XMAS");
        }
        return false;
    }

    public static boolean checkUp(String[][] input, int r, int c)
    {
        if(r+3 < input.length)
        {
            String temp = "";
            for(int i = r; i <= r+3; i++)
            {
                temp+=input[i][c];
            }
            return temp.equals("XMAS");
        }
        return false;
    }

    public static boolean checkDown(String[][] input, int r, int c)
    {
        if(r-3 >= 0)
        {
            String temp = "";
            for(int i = r; i >= r-3; i--)
            {
                temp+=input[i][c];
            }
            return temp.equals("XMAS");
        }
        return false;
    }

}
