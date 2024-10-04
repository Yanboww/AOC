package AOC2022;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Day13 {
    public static void main(String[] args)
    {
        System.out.println(findSim("inputs/AOC2022Inputs/input13"));
        System.out.println(findSimPart2("inputs/AOC2022Inputs/input13"));
    }

    public static int findSim(String fileName)
    {
        File f = new File(fileName);
        int resultIndex = 0;
        try{
            Scanner s = new Scanner(f);
            int index = 1;
            while(s.hasNextLine())
            {
                String lineOne = s.nextLine();
                String lineTwo = s.nextLine();
                if(s.hasNextLine()) s.nextLine();
                String[] lineOneArray = lineOne.split(",");
                String[] lineTwoArray = lineTwo.split(",");
                if(correctOrder(lineOneArray,lineTwoArray)) resultIndex+=index;
                index++;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not Found");
        }
        return  resultIndex;
    }

    public static int findSimPart2(String fileName)
    {
        File f = new File(fileName);
        ArrayList<String[]> allLists = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            while(s.hasNextLine())
            {
                allLists.add(s.nextLine().split(","));
                allLists.add(s.nextLine().split(","));
                if(s.hasNextLine()) s.nextLine();
            }
            String pack1 = "[[2]]";
            allLists.add(pack1.split(","));
            String pack2 = "[[6]]";
            allLists.add(pack2.split(","));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        String[] pack1 = allLists.get(allLists.size()-2);
        String[] pack2 = allLists.get(allLists.size()-1);
        sortArrays(allLists);
       /* for(String[] stuff : allLists)
        {
            System.out.println(Arrays.toString(stuff));
        }**/
        return (allLists.indexOf(pack1)+1) * (allLists.indexOf(pack2)+1);
    }


    public static int toInt(String string)
    {
        int openIndex = string.lastIndexOf("[");
        if(openIndex != -1) string = string.substring(openIndex+1);
        int closeIndex = string.indexOf("]");
        if(closeIndex != -1) string = string.substring(0,closeIndex);
        if(string.isEmpty()) return -100000;
        return Integer.parseInt(string);
    }

    public static int countOpen(String string)
    {
        int count = 0;
        for(int i = 0; i < string.length(); i++)
        {
            if(string.charAt(i) == '[') count++;
        }
        return count;
    }

    public static boolean correctOrder(String[] lineOneArray, String[] lineTwoArray)
    {

        for(int i = 0; i < lineOneArray.length; i++)
        {
            if(i >= lineTwoArray.length){
                break;
            }
            int numOne = toInt(lineOneArray[i].trim());
            int numTwo = toInt(lineTwoArray[i].trim());
            if(numOne > numTwo) break;
            else if(numOne == numTwo)
            {
                int openOne = countOpen(lineOneArray[i]);
                int openTwo = countOpen(lineTwoArray[i]);
                if(openOne > openTwo) break;
                else if(openTwo > openOne)
                {
                    return true;
                    //System.out.println(index);
                }
            }
            else
            {
                return true;
                //System.out.println(index);
            }
            if(i == lineOneArray.length-1 && lineOneArray.length < lineTwoArray.length){
                return true;
                //System.out.println(index);
            }
        }
        return false;
    }

    public static void sortArrays(ArrayList<String[]> list)
    {
        for(int i = 1; i < list.size(); i++)
        {
            String[] two = list.get(i);
            int newIndex = i;
            while(newIndex > 0)
            {
                String[] one = list.get(newIndex-1);
                if(!correctOrder(one,two))
                {
                    list.set(newIndex,one);
                    newIndex--;
                }
                else break;
            }
            list.set(newIndex,two);
        }
    }

}
