import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Day7 {
    public static void main(String[] args) {
        System.out.println(findSumSmallestDirectories("inputs/input7"));
        System.out.println(findSmallestDeleted("inputs/input7"));
    }
    public static int findSumSmallestDirectories(String fileName)
    {
        ArrayList<String> cmd = new ArrayList<>();
        try{
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            s.nextLine();
            s.nextLine();
            while(s.hasNextLine())
            {
                cmd.add(s.nextLine());
            }
            cmd.add("$ cd");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        ArrayList<String> dirVal = new ArrayList<>();
        String currDir = "/ = ";
        int val = 0;
        for(String cmdLine : cmd)
        {
            if(cmdLine.contains("$ cd") && !cmdLine.equals("$ cd .."))
            {
                dirVal.add(currDir + val);
                currDir = cmdLine.substring(cmdLine.lastIndexOf(" ")+1) +" = ";
                val = 0;
            }
            else if(!cmdLine.contains("$ ls") && !cmdLine.contains("$"))
            {
                if(cmdLine.contains("dir")) currDir = currDir + cmdLine.substring(cmdLine.indexOf(" ")+1) +" + ";
                else val += Integer.parseInt(cmdLine.substring(0,cmdLine.indexOf(" ")));
            }
        }
        return calcLowestSum(dirVal);
    }

    public static int findSmallestDeleted(String fileName)
    {
        ArrayList<String> cmd = new ArrayList<>();
        try{
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            s.nextLine();
            s.nextLine();
            while(s.hasNextLine())
            {
                cmd.add(s.nextLine());
            }
            cmd.add("$ cd");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        ArrayList<String> dirVal = new ArrayList<>();
        String currDir = "/ = ";
        int val = 0;
        for(String cmdLine : cmd)
        {
            if(cmdLine.contains("$ cd"))
            {
                dirVal.add(currDir + val);
                currDir = cmdLine.substring(cmdLine.lastIndexOf(" ")+1) +" = ";
                val = 0;
            }
            else if(!cmdLine.contains("$ ls") && !cmdLine.contains("$"))
            {
                if(cmdLine.contains("dir")) currDir = currDir + cmdLine.substring(cmdLine.indexOf(" ")+1) +" + ";
                else val += Integer.parseInt(cmdLine.substring(0,cmdLine.indexOf(" ")));
            }
        }
        return calcLowestDelete(dirVal);
    }

    public static int calcLowestSum(ArrayList<String> sizes)
    {
        int sum = 0;
        int index = 0;
        for(String fileSize: sizes)
        {
            int totalSize = 0;
            String size = fileSize.substring(fileSize.indexOf("=")+2);
            if(size.contains("+"))
            {
                String[] sizeArray = size.split("\\+");
                for(String dir : sizeArray)
                {
                    dir = dir.trim();
                    totalSize += findDirectorySize(sizes, dir, index);
                }
            }
            else totalSize = Integer.parseInt(size);
            if(totalSize <=  100000){
                sum+=totalSize;
            }
            index++;
        }
        return sum;
    }

    public static int calcLowestDelete(ArrayList<String> sizes)
    {
        int lowest = 0;
        int delete = 0;
        int index = 0;
        for(String fileSize: sizes)
        {
            int totalSize = 0;
            String size = fileSize.substring(fileSize.indexOf("=")+2);
            if(size.contains("+"))
            {
                String[] sizeArray = size.split("\\+");
                for(String dir : sizeArray)
                {
                    dir = dir.trim();
                    totalSize += findDirectorySize(sizes, dir, index);
                }
            }
            else totalSize = Integer.parseInt(size);
            if (index == 0)
            {
                int total = findTotalDirectorySize(sizes);
                delete = 30000000-(70000000-total);
                lowest = total;
            }
            else {
                if(totalSize>=delete)
                {
                    lowest = Math.min(lowest,totalSize);
                }
            }
            index++;
        }
        return lowest;
    }

    public static int findDirectorySize(ArrayList<String> sizes, String dir, int currIndex)
    {
        int test = isInt(dir);
        if(test > -1) return test;
        int index = getIndex(sizes,dir+" = ",currIndex);
        String line = sizes.get(index);
        int totalSize = 0;
        line = line.substring(line.indexOf("=")+2);
        String[] values = line.split("\\+");
        for(String value : values)
        {
            value = value.trim();
            totalSize+=findDirectorySize(sizes,value,index);
        }
        return totalSize;
    }

    public static int findTotalDirectorySize(ArrayList<String>sizes)
    {
        int total = 0;
        for(int i = 0; i< sizes.size(); i++)
        {
            String line = sizes.get(i);
            line = line.substring(line.indexOf("=")+2);
            if(line.contains("+"))
            {
                String[] dirDir = line.split("\\+");
                for(String size : dirDir)
                {
                    size = size.trim();
                    int test = isInt(size);
                    if(test>-1) total+=test;
                }
            }
            else total += Integer.parseInt(line);
        }
        System.out.println(total);
        return total;
    }


    public static int getIndex(ArrayList<String> list , String val, int currIndex)
    {
        for(int i = 0; i < list.size(); i++)
        {
            if(list.get(i).contains(val) && i > currIndex) return i;
        }
        return -1;
    }


    public static int isInt(String val)
    {
        try{
            return Integer.parseInt(val);
        }
        catch (NumberFormatException e)
        {
            return -1;
        }
    }


}
