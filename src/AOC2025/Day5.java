package AOC2025;
import java.io.*;
import java.util.*;
public class Day5 {
    public static void main(String[] args){
        System.out.println(findFresh("inputs/input.txt",1));
        System.out.println(findFresh("inputs/input.txt",2));
    }

    public static long findFresh(String fileName, int part){
        ArrayList<String> ranges = new ArrayList<>();
        ArrayList<Long> ingredientID = new ArrayList<>();
        try {
            Scanner s = new Scanner(new File(fileName));
            boolean finishedRanges = false;
            while(s.hasNextLine()){
                String line = s.nextLine();
                if(line.isEmpty()) finishedRanges = true;
                else if(!finishedRanges) ranges.add(line);
                else ingredientID.add(Long.parseLong(line));
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) return countFresh(ranges,ingredientID);
        Collections.sort(ranges);
        return totalPossible(ranges);
    }

    public static int countFresh(ArrayList<String> ranges, ArrayList<Long> ingredients){
        int count = 0;
        for(Long ingredient : ingredients){
            for(String range : ranges){
                long[] rangeArr = Arrays.stream(range.split("-")).mapToLong(Long::parseLong).toArray();
                if(ingredient >= rangeArr[0] && ingredient <= rangeArr[1]){
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public static long totalPossible(ArrayList<String> ranges){
        long count = 0;
        ArrayList<long[]> triedRanges = new ArrayList<>();
        for(String range : ranges){
            long[] rangeArr = Arrays.stream(range.split("-")).mapToLong(Long::parseLong).toArray();
            long lo = rangeArr[0]; long hi = rangeArr[1];
            long covered = hi-lo+1;
            for(long[] tried : triedRanges){
                if(tried[0]<=lo && tried[1]>=lo){
                    covered-=(tried[1]-lo+1);
                    if(covered <= 0) break;
                    lo = tried[1]+1;
                }
                if(tried[0]<=hi && tried[1]>=hi){
                    covered-=(hi-tried[0]+1);
                    if(covered <= 0) break;
                    hi = tried[0]-1;
                }
            }
            if(covered>0){
                count+=covered;
                triedRanges.add(rangeArr);
            }
        }
        return count;
    }
}
