package AOC2020;
import java.io.*;
import java.util.*;
public class Day5 {
    public static void main(String[] args) {
        System.out.println(findSeatValue("inputs/input.txt", 1));
        System.out.println(findSeatValue("inputs/input.txt", 2));
    }

    public static int findSeatValue(String fileName, int part){
        File f = new File(fileName);
        ArrayList<char[]> seats = new ArrayList<>();
        try{
            Scanner  s = new Scanner(f);
            while(s.hasNextLine()) seats.add(s.nextLine().toCharArray());

        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        ArrayList<Integer> seatIds = new ArrayList<>();
        int highest = 0;
        for(char[] seat : seats){
            int seatId = findId(seat);
            if(highest < seatId) highest = seatId;
            seatIds.add(seatId);
        }
        Collections.sort(seatIds);
        if(part == 2) return findMissing(seatIds);
        return  highest;
    }

    public static int findId(char[] seat){
        int row = seatInfoHelper(0,7,0,127,'F','B',seat);
        int column = seatInfoHelper(7,10,0,7,'L','R',seat);
        return row*8+column;
    }

    public static int seatInfoHelper(int start, int end, int startVal, int endVal, char front, char back, char[] seat){
        for(int i = start; i < end; i++){
            if(seat[i] == front) endVal = (endVal+startVal)/2;
            else if(seat[i] == back){
                int temp = (endVal+startVal)/2;
                if((double)(endVal+startVal)/2 > (double) temp) startVal = temp+1;
                else startVal = temp;
            }
        }
        return endVal;
    }

    public static int findMissing(ArrayList<Integer> seatIds){
        for(int i = 0; i < seatIds.size(); i++){
            int num = seatIds.get(i);
            int num2 = seatIds.get(i+1);
            if(num2-num > 1) return num+1;
        }
        return -1;
    }
}
