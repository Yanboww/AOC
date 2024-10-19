package AOC2021;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Day2 {
    public static void main(String[] args)
    {
        System.out.println(calcPos("inputs/AOC2021Inputs/input2"));
        System.out.println(calcPos2("inputs/AOC2021Inputs/input2"));
    }
    public static int calcPos(String fileName)
    {
        File f = new File(fileName);
        int depth = 0;
        int horizontal = 0;
        try{
            Scanner s = new Scanner(f);
            while(s.hasNextLine())
            {
                String[] command = s.nextLine().split(" ");
                int delta = Integer.parseInt(command[1]);
                if(command[0].equals("up")) depth-=delta;
                else if(command[0].equals("down")) depth+=delta;
                else horizontal+=delta;
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        return depth*horizontal;
    }
    public static int calcPos2(String fileName)
    {
        File f = new File(fileName);
        int depth = 0;
        int horizontal = 0;
        int aim = 0;
        try{
            Scanner s = new Scanner(f);
            while(s.hasNextLine())
            {
                String[] command = s.nextLine().split(" ");
                int delta = Integer.parseInt(command[1]);
                if(command[0].equals("up")) aim-=delta;
                else if(command[0].equals("down")) aim+=delta;
                else{
                    horizontal+=delta;
                    depth+=aim*delta;
                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        return depth*horizontal;
    }

}
