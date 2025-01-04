package AOC2015;
import java.util.*;
import java.io.*;
public class Day1 {
    public static void main(String[] args)
    {
        System.out.println(findFloor("inputs/input.txt",1));
        System.out.println(findFloor("inputs/input.txt",2));
    }

    public static int findFloor(String fileName, int part)
    {
        File f = new File(fileName);
        int floor = 0;
        try{
            Scanner s = new Scanner(f);
            String line = s.nextLine();
            for(int i = 0; i < line.length(); i++){
                String character = line.substring(i,i+1);
                if(character.equals("(")) floor++;
                else floor--;
                if(floor == -1 && part == 2) return i+1;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        return floor;
    }

}
