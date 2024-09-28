package AOC2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
public class Day2 {
    public static HashMap<String,Integer> moveValue = new HashMap<>();
    public static HashMap<String,Integer> win = new HashMap<>();
    public static void main(String[] args)
    {
        setMoveValue();
        setWin();
        System.out.println(calcScore("inputs/AOC2022Inputs/input2"));
        System.out.println(calcScoreRevised("inputs/AOC2022Inputs/input2"));
    }

    public static int calcScore(String fileName)
    {
        String[] opponent = new String[2500];
        String[] outcome = new String[2500];
        int score = 0;
        try{
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            int i = 0;
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                opponent[i] =line.substring(0,1);
                outcome[i] = line.substring(2,3);
                i++;
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        for(int i = 0; i < opponent.length; i++)
        {
            String response = outcome[i];
            int roundVal = win.get(opponent[i]+response);
            int responseVal = moveValue.get(response);
            score+=roundVal+responseVal;
        }
        return score;
    }
    public static int calcScoreRevised(String fileName)
    {
        String[] opponent = new String[2500];
        String[] outcome = new String[2500];
        int score = 0;
        try{
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            int i = 0;
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                opponent[i] =line.substring(0,1);
                outcome[i] = line.substring(2,3);
                i++;
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        for(int i = 0; i < opponent.length; i++)
        {
            String response = outcome[i];
            String move = opponent[i];
            if(response.equals("Z"))
            {
                score+=6 + calcWin(move);
            }
            else if(response.equals("Y"))
            {
                score+=3 + calcDraw(move);
            }
            else{
                score += calLose(move);
            }
        }
        return score;
    }

    public static void setMoveValue()
    {
        moveValue.put("A",1);
        moveValue.put("B",2);
        moveValue.put("C",3);
        moveValue.put("X",1);
        moveValue.put("Y",2);
        moveValue.put("Z",3);
    }
    public static void setWin()
    {
        win.put("AX",3);
        win.put("AY", 6);
        win.put("AZ", 0);
        win.put("BX", 0);
        win.put("BY", 3);
        win.put("BZ", 6);
        win.put("CX", 6);
        win.put("CY", 0);
        win.put("CZ", 3);
    }

    public static int calcWin(String move)
    {
        if(move.equals("A")) return 2;
        else if(move.equals("B")) return 3;
        return 1;
    }
    public static int calcDraw(String move)
    {
        return moveValue.get(move);
    }
    public static int calLose(String move)
    {
        if(move.equals("A")) return 3;
        else if(move.equals("B")) return 1;
        return 2;
    }




}
