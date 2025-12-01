package AOC2025;
import java.io.*;
import java.util.*;
public class Day1 {
    public static void main(String[] args){
        //System.out.println(decode("inputs/input.txt",1));
        System.out.println(decode("inputs/input.txt",2));
        //System.out.println(decode("inputs/trial",2));
    }

    public static int decode(String fileName, int part){
        ArrayList<String> directions = new ArrayList<>();
        try{
            Scanner s = new Scanner(new File(fileName));
            while(s.hasNextLine()) directions.add(s.nextLine());
            s.close();
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) return decodePart1(directions);
        return decodePart2(directions);
    }

    public static int decodePart1(ArrayList<String> directions){
        int count = 0;
        int currentPos = 50;
        for(String direction : directions){
            String dir = direction.substring(0,1);
            int val = Integer.parseInt(direction.substring(1));
            if(dir.equals("R")) currentPos+=val;
            else currentPos-=val;
            currentPos%=100;
            if(currentPos<0) currentPos += 100;
            if(currentPos == 0) count++;
        }
        return count;
    }

    public static int decodePart2(ArrayList<String> directions){
        int count = 0;
        int currentPos = 50;
        for(String direction : directions){
            int prev = currentPos;
            String dir = direction.substring(0,1);
            int fullVal = Integer.parseInt(direction.substring(1));
            count+=fullVal/100;
            int val= fullVal - fullVal/100*100;
            if(dir.equals("R")) currentPos+=val;
            else currentPos-=val;
            int temp = currentPos;
            currentPos%=100;
            if(currentPos<temp && currentPos!=0) count++;
            if(currentPos<0){
                currentPos += 100;
                if(prev != 0) count++;
            }
            if(currentPos == 0) count++;
        }
        return count;
    }
}
