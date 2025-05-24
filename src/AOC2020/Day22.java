package AOC2020;
import java.io.*;
import java.util.*;
public class Day22 {
    public static void main(String[] args){
        System.out.println(crabGame("inputs/input.txt",1));
        System.out.println(crabGame("inputs/input.txt",2));
    }

    public static long crabGame(String fileName, int part){
        File f = new File(fileName);
        ArrayList<Integer> player1 = new ArrayList<>();
        ArrayList<Integer> player2 = new ArrayList<>();
        try{
            boolean isPlayer1 = true;
            Scanner s = new Scanner(f);
            while(s.hasNextLine()){
                String line = s.nextLine();
                if(line.isEmpty()) isPlayer1 = false;
                else if(!line.contains("Player")){
                    int card = Integer.parseInt(line);
                    if(isPlayer1) player1.add(card);
                    else player2.add(card);
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) return simulateGame(player1,player2);
        return recursiveGame(player1,player2,1);
    }

    public static long simulateGame(ArrayList<Integer> player1, ArrayList<Integer> player2)
    {
        while(!player1.isEmpty() && !player2.isEmpty()){
            int player1Card = player1.remove(0);
            int player2Card = player2.remove(0);
            if(player1Card > player2Card){
                player1.add(player1Card);
                player1.add(player2Card);
            }
            else{
                player2.add(player2Card);
                player2.add(player1Card);
            }
        }
        if(player1.isEmpty()) return calculate(player2);
        return calculate(player1);
    }

    public static long recursiveGame(List<Integer> player1, List<Integer> player2, int game){
        HashSet<String> pastRounds = new HashSet<>();
        while(!player1.isEmpty() && !player2.isEmpty()){
            if(checkRound(pastRounds,player1,player2)){
                player2.clear();
                break;
            }
            pastRounds.add(player1 +"|"+player2);
            int player1Card = player1.remove(0);
            int player2Card = player2.remove(0);
            boolean isPlayer1Winner = player1Card > player2Card;
            if(player1.size() >= player1Card && player2.size() >= player2Card){
                int winner = (int)recursiveGame(new ArrayList<>(player1.subList(0,player1Card)), new ArrayList<>(player2.subList(0,player2Card)),game+1);
                isPlayer1Winner = winner >= 0;
            }
            if(isPlayer1Winner){
                player1.add(player1Card);
                player1.add(player2Card);
            }
            else{
                player2.add(player2Card);
                player2.add(player1Card);
            }
        }
        if(game == 1){
            if(player1.isEmpty()) return calculate(player2);
            else return calculate(player1);
        }
        else{
            if(player1.isEmpty()) return -1;
            else return 1;
        }
    }

    public static long calculate(List<Integer> player){
        long sum = 0;
        int factor = 1;
        for(int i = player.size()-1; i>=0; i--){
            sum += ((long) factor *player.get(i));
            factor++;
        }
        return sum;
    }

    public static boolean checkRound(HashSet<String> past, List<Integer> player1, List<Integer> player2){
        return past.contains(player1+"|"+player2);
    }


}
