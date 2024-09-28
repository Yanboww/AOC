import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
public class Day12 {
    static HashMap<String,Integer> key = new HashMap<>();
   static  ArrayList<Integer> steps = new ArrayList<>();
    public static void main(String[] args) {
        String letters = "abcdefghijklmnopqrstuvwxyz";
        for(int i = 0; i < letters.length(); i++)
        {
            key.put(letters.substring(i,i+1),i);
        }
        key.put(".",1000);
        key.put("S",-1);
        key.put("E",1000);
        System.out.println(findPath("inputs/input12"));
    }
    public static int findPath(String fileName)
    {
        File f = new File(fileName);
        String[][] map = new String[41][77];
        String[][] answerMap = new String[41][77];
        for(String[] row : answerMap)
        {
            Arrays.fill(row,".");
        }
        int startRow = 0;
        int startCol = 0;
        try{
            int row = 0;
            Scanner s = new Scanner(f);
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                for(int i = 0; i < line.length(); i++)
                {
                    String character = line.substring(i,i+1);
                    map[row][i] = character;
                    if(character.equals("S")) {
                        startRow = row;
                        startCol = i;
                    }
                }
                row++;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        //System.out.println(startRow+","+startCol);
        /*for(String[] row : map)
        {
            System.out.println(Arrays.toString(row));
        }**/
        getPath(answerMap,map,startRow,startCol);
        //System.out.println(steps);
        return findLowest()+1;
    }

    public static int findLowest()
    {
        int lowest = steps.get(0);
        for(int value : steps)
        {
            if(value<lowest) lowest = value;
        }
        return lowest;
    }

    public static void getPath(String[][] answer,String[][] map, int row, int column)
    {
        //System.out.println(steps);
        System.out.println("___________________________________________________");
        for(String[] ansRow : answer)
        {
            System.out.println(Arrays.toString(ansRow));
        }
        if(!win(map,row,column))
        {
            if(down(map,row,column,answer))
            {
                answer[row][column] ="#";
                getPath(answer,map,row+1,column);
                answer[row][column] = ".";
            }
        }
        if(!win(map,row,column))
        {
            if(right(map,row,column,answer))
            {
                answer[row][column] ="#";
                getPath(answer,map,row,column+1);
                answer[row][column] = ".";
            }
        }
        if(!win(map,row,column))
        {
            if(left(map,row,column,answer))
            {
                answer[row][column] ="#";
                getPath(answer,map,row,column-1);
                answer[row][column] = ".";
            }
        }
        if(!win(map,row,column))
        {
            if(up(map,row,column,answer))
            {
                answer[row][column] ="#";
                getPath(answer,map,row-1,column);
                answer[row][column] = ".";
            }
        }
        if(win(map,row,column)) steps.add(countPath(answer));
    }
    public static boolean right(String[][] map, int row, int column, String[][] answer)
    {
        boolean possible = false;
        if(column!=map[0].length-1){
            int value = key.get(map[row][column]) - key.get(map[row][column+1]);
            if((value == -1 || value == 0) && !answer[row][column+1].equals("#")) possible = true;
        }
        return possible;
    }
    public static boolean left(String[][] map, int row, int column, String[][] answer)
    {
        boolean possible = false;
        if(column!= 0){
            int value = key.get(map[row][column]) - key.get(map[row][column-1]);
            if( (value == -1 || value == 0) && !answer[row][column-1].equals("#")) possible = true;
        }
        return possible;
    }
    public static boolean up(String[][] map, int row, int column, String[][] answer)
    {
        boolean possible = false;
        if(row!= 0){
            int value = key.get(map[row][column]) - key.get(map[row-1][column]);
            if((value == -1 || value == 0 ) && !answer[row-1][column].equals("#")) possible = true;
        }
        return possible;
    }
    public static boolean down(String[][] map, int row, int column, String[][] answer)
    {
        boolean possible = false;
        if(row!= map.length-1){
            int value = key.get(map[row][column]) - key.get(map[row+1][column]);
            if( (value == -1 || value == 0) && !answer[row+1][column].equals("#")) possible = true;
        }
        return possible;
    }

    public static boolean win(String[][] map, int row, int column)
    {
        boolean won = false;
        if(map[row][column].equals("z"))
        {
            if(row != 0 && map[row-1][column].equals("E")) won = true;
            else if(row!= map.length-1 && map[row+1][column].equals("E")) won = true;
            else if(column!=0 && map[row][column-1].equals("E")) won = true;
            else if(column!=map[0].length-1 && map[row][column+1].equals("E")) won = true;
        }
        return won;
    }


    public static int countPath(String[][] map)
    {
        int count = 0;
        for(int r = 0; r < map.length; r++)
        {
            for(int c = 0; c < map[r].length; c++)
            {
                if(map[r][c].equals("#")) count++;
            }
        }
        return count;
    }
}
