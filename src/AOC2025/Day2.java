package AOC2025;
import java.io.*;
import java.util.*;
public class Day2 {
    public static void main(String[] args){
        System.out.println(invalidID("inputs/input.txt",1));
        System.out.println(invalidID("inputs/input.txt",2));
        //System.out.println(invalidID("inputs/trial", 1));
    }

    public static long invalidID(String fileName, int part){
        String[] idRanges;
        try {
            Scanner s = new Scanner(new File(fileName));
            idRanges = s.nextLine().split(",");
            s.close();
        } catch (FileNotFoundException e){
            System.out.println("File not found");
            return -1;
        }
        return sumInvalids(idRanges, part);
    }

    public static long sumInvalids(String[] idRanges, int part){
        long total = 0;
        for(String range : idRanges){
            long[] rangeArr = Arrays.stream(range.split("-")).mapToLong(Long::parseLong).toArray();
            for(long i = rangeArr[0]; i <= rangeArr[1]; i++){
                String curretnString = Long.toString(i);
                if(part == 1 && curretnString.matches("^(\\d+)\\1$")) total+=i;
                else if(part == 2 && curretnString.matches("^(\\d+)\\1+$")) total+=i;
            }
        }
        return total;
    }
}
