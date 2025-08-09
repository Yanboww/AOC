package AOC2021;
import java.io.*;
import java.util.*;

/*https://www.reddit.com/r/adventofcode/comments/rl6p8y/comment/hpkxh2c/?utm_source=share&utm_medium=web2x&context=3
Wasn't quite sure how I'd do part 2, so I checked on reddit and found this.*/
public class Day21 {
    public static void main(String[] args){
        System.out.println(diracDice("inputs/input.txt",1));
        System.out.println(diracDice("inputs/input.txt",2));
    }

    public static long diracDice(String fileName, int part){
        File f = new File(fileName);
        int start1 = 0;
        int start2 = 0;
        try {
            Scanner s = new Scanner(f);
            String temp = s.nextLine();
            start1 = Integer.parseInt(temp.substring(temp.indexOf(":")+2));
            temp = s.nextLine();
            start2 = Integer.parseInt(temp.substring(temp.indexOf(":")+2));
        }
        catch (FileNotFoundException e){
            System.out.println("file not found");
        }
        if (part == 1) return determined(start1, start2);
        return quantum(start1, start2);
    }

    public static long determined(int start1, int start2){
        long rolls = 0;
        int player1 = 0;
        int player2 = 0;
        int dice = 1;
        while(player2 < 1000){
            for(int i = 0; i < 3; i++){
                start1 += dice;
                dice++;
                if(dice > 100) dice = 1;
            }
            if(start1 > 10) start1 %= 10;
            if(start1 == 0) start1 = 10;
            player1 += start1;
            rolls += 3;

            if(player1 >= 1000) break;

            for(int i = 0; i < 3; i++) {
                start2 += dice;
                dice++;
                if(dice > 100) dice = 1;
            }
            if(start2 > 10) start2 %= 10;
            if(start2 == 0) start2 = 10;
            player2 += start2;
            rolls += 3;
        }
        return Math.min(player2,player1) * rolls;
    }

    public static long quantum(int start1, int start2){
        HashMap<Integer,Integer> preCompute = new HashMap<>();
        preCompute.put(3,1);preCompute.put(4,3);preCompute.put(5,6);preCompute.put(6,7);
        preCompute.put(7,6);preCompute.put(8,3);preCompute.put(9,1);
        long[] wins = calculateWins(start1-1,21,start2-1,21, preCompute);
        Arrays.sort(wins);
        return wins[1];
    }

    public static long[] calculateWins(int p1, int s1, int p2, int s2, HashMap<Integer,Integer> rf){
        if(s2 <= 0) return new long[]{0,1};
        long win1 = 0; long win2 = 0;
        for(int key : rf.keySet()){
            long[] sim = calculateWins(p2,s2,(p1+key)%10,s1-1-(p1+key)%10, rf);
            win1 += rf.get(key) * sim[1];
            win2 += rf.get(key) * sim[0];
        }
        return new long[]{win1,win2};
    }
}
