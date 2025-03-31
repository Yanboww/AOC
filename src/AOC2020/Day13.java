package AOC2020;
import java.io.*;
import java.util.*;
public class Day13 {
    public static void main(String[] args){
        //System.out.println(findEarliest("inputs/input.txt",1));
        System.out.println(findEarliest("inputs/input.txt",2));

    }

    public static long findEarliest(String fileName, int part)
    {
        File f = new File(fileName);
        int start = 0;
        String[] busIds = new String[1];
        try{
            Scanner s = new Scanner(f);
            start = Integer.parseInt(s.nextLine());
            String ids = s.nextLine();
            busIds = ids.split(",");
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        System.out.println(Arrays.toString(busIds));
        if(part == 1) return earliestId(start,busIds);
        return timeStamp(busIds);
    }

    public static int earliestId(int start, String[] busIds)
    {
        int earliestBudId = 0;
        int timeDifference = start;
        for(String id : busIds)
        {
            if(id.equals("x")) continue;
            int idInt = Integer.parseInt(id);
            int timeArrival = start;
            if(start%idInt>0){
                timeArrival = idInt * (start/idInt + 1);
            }
            int tempTimeDifference = timeArrival - start;
            if(timeDifference > tempTimeDifference){
                timeDifference = tempTimeDifference;
                earliestBudId = idInt;
            }
        }
        return timeDifference * earliestBudId;
    }

    public static long timeStamp(String[] busIds)
    {
        long timeStamp = 0;
        long count = 1;
        for(int i = 0; i < busIds.length; i++)
        {
            if(busIds[i].equals("x")) continue;
           while(true)
           {
               int id = Integer.parseInt(busIds[i]);
               timeStamp += count;
               if((timeStamp + i) % id == 0){
                   count *= id;
                   break;
               }
           }
        }
        return timeStamp;
    }


}
