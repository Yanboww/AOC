package AOC2022;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Day18 {
    public static void main(String[] args) {
        System.out.println(findSurfaceArea("inputs/AOC2022Inputs/input18"));
        System.out.println(findSurfaceArea2("inputs/trial"));
    }
    public static int findSurfaceArea(String fileName)
    {
        File f = new File(fileName);
        ArrayList<String> cubes = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            while(s.hasNextLine())
            {
                cubes.add(s.nextLine());
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        int totalArea = cubes.size()*6;
        for(String cube : cubes)
        {
            String[] coordinates = cube.split(",");
           totalArea-= tester(cubes,coordinates);
        }
        return totalArea;
    }
    public static int findSurfaceArea2(String fileName)
    {
        File f = new File(fileName);
        String[][][] cubes = new String[4][4][7];
        for(String[][] cubess : cubes)
        {
            for(String[] cube : cubess)
            {
                Arrays.fill(cube,".");
            }
        }
        try{
            Scanner s = new Scanner(f);
            while(s.hasNextLine())
            {
                String[] coordinates = s.nextLine().split(",");
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);
                int z = Integer.parseInt(coordinates[2]);
                cubes[x][y][z] = "#";
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        for(String[][] cubess : cubes)
        {
            for(String[] cube : cubess)
            {
                System.out.print(Arrays.toString(cube));
            }
            System.out.println();
            System.out.println("---------");
        }
        return 0;
    }
    public static int tester(ArrayList<String> cubes, String[] coordinates)
    {
        int x = Integer.parseInt(coordinates[0]);
        int y = Integer.parseInt(coordinates[1]);
        int z = Integer.parseInt(coordinates[2]);
        int counter = 0;
        if(testUp(cubes,x, y,z )) counter++;
        if(testDown(cubes,x,y,z)) counter++;
        if(testLeft(cubes,x,y,z)) counter++;
        if(testRight(cubes,x,y,z)) counter++;
        if(testFront(cubes,x,y,z)) counter++;
        if(testBack(cubes,x,y,z)) counter++;
        return  counter;
    }
    public static boolean testUp(ArrayList<String> cubes, int x, int y, int z)
    {
        y++;
        String topCoord = x+","+y+","+z;
        return cubes.contains(topCoord);
    }
    public static boolean testDown(ArrayList<String> cubes, int x, int y, int z)
    {
        y--;
        String bottomCoord = x+","+y+","+z;
        return cubes.contains(bottomCoord);
    }
    public static boolean testLeft(ArrayList<String> cubes, int x, int y, int z)
    {
        x--;
        String leftCoord = x+","+y+","+z;
        return cubes.contains(leftCoord);
    }
    public static boolean testRight(ArrayList<String> cubes, int x, int y, int z)
    {
        x++;
        String rightCoord = x+","+y+","+z;
        return cubes.contains(rightCoord);
    }
    public static boolean testFront(ArrayList<String> cubes, int x, int y, int z)
    {
        z++;
        String frontCoord = x+","+y+","+z;
        return cubes.contains(frontCoord);
    }
    public static boolean testBack(ArrayList<String> cubes, int x, int y, int z)
    {
        z--;
        String backCoord = x+","+y+","+z;
        return cubes.contains(backCoord);
    }
}
