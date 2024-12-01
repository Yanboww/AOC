package AOC2024;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class Day1 {
    public static void main(String[] args)
    {

        System.out.println(findTotalDistance("inputs/AOC2024inputs/day1"));
        System.out.println(findTotalSimilarity("inputs/AOC2024inputs/day1"));
    }

    public static int findTotalDistance(String fileName)
    {
        File f = new File(fileName);
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                String[] lineArr = line.split("\\s+");
                list1.add(Integer.parseInt(lineArr[0]));
                list2.add(Integer.parseInt(lineArr[1]));
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        sortArr(list1);
        sortArr(list2);
        int sum =0;
        for(int i = 0; i < list1.size(); i++)
        {
            sum+= Math.abs(list1.get(i) - list2.get(i));
        }
        return sum;
    }
    public static int findTotalSimilarity(String fileName)
    {
        File f = new File(fileName);
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                String[] lineArr = line.split("\\s+");
                list1.add(Integer.parseInt(lineArr[0]));
                list2.add(Integer.parseInt(lineArr[1]));
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        sortArr(list1);
        sortArr(list2);
        int sum =0;
        for(int i = 0; i < list1.size(); i++)
        {
            int val = list1.get(i);
            sum+=(val * findSim(val, list2));
        }
        return sum;
    }

    public static int findSim(int val, ArrayList<Integer> list2)
    {
        int count = 0;
        for(int value : list2)
        {
            if(value>val) break;
            if(val == value) count++;
        }
        return count;
    }

    public static void sortArr(ArrayList<Integer> list)
    {
        int[] temp = new int[list.size()];
        split(0,list.size()-1,list,temp);
    }

    public static void split(int start, int end, ArrayList<Integer> list, int[] arr)
    {
        if(start < end)
        {
            int m = (end+start)/2;
            split(start, m, list, arr);
            split(m+1, end, list, arr);
            merge(start, m, end, list, arr);
        }
    }

    public static void merge(int start, int mid, int end, ArrayList<Integer> list, int[] temp)
    {
        int s = start;
        int index = start;
        int m = mid+1;
        while(s <= mid && m <= end)
        {
            int v1 = list.get(s);
            int v2 = list.get(m);
            if(v1 > v2)
            {
                temp[index] = v2;
                m++;
            }
            else{
                temp[index] = v1;
                s++;
            }
            index++;
        }
        while(s <= mid)
        {
            temp[index] = list.get(s);
            s++;
            index++;
        }
        while(m <= end)
        {
            temp[index] = list.get(m);
            m++;
            index++;
        }
        for(int i = start; i <= end; i++)
        {
            list.set(i,temp[i]);
        }
    }
}
