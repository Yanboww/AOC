package AOC2021;
import java.io.*;
import java.util.*;
public class Day9 {
    public static void main(String[] args)
    {
        System.out.println(findRisk("inputs/input.txt",1));
        System.out.println(findRisk("inputs/input.txt",2));
    }

    public static int findRisk(String fileName, int part)
    {
        File f = new File(fileName);
        int length = 100;
        if(fileName.contains("trial")) length = 5;
        String[][] map = new String[length][];
        try{
            Scanner s = new Scanner(f);
            int index = 0;
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] temp = new String[line.length()];
                for(int i = 0; i < line.length(); i++) temp[i] = line.substring(i,i+1);
                map[index] = temp;
                index++;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        if(part == 1) return lowestRiskLevels(map);
        return findBasins(map);
    }

    public static int lowestRiskLevels(String[][] map)
    {
        int total = 0;
        for(int r = 0; r < map.length; r++)
        {
            for(int c = 0; c < map[r].length; c++)
            {
                String currentVal = map[r][c];
                boolean isLowest = true;
                if(r > 0) if(map[r-1][c].compareTo(currentVal) <= 0) isLowest = false;
                if(isLowest && r < map.length-1) if(map[r+1][c].compareTo(currentVal) <= 0) isLowest = false;
                if(isLowest && c > 0) if(map[r][c-1].compareTo(currentVal) <= 0) isLowest = false;
                if(isLowest && c < map[r].length-1) if(map[r][c+1].compareTo(currentVal) <= 0) isLowest = false;
                if(isLowest){
                    //System.out.println(currentVal +": " + r + ","+c);
                    total+= Integer.parseInt(currentVal)+1;
                }
            }
        }
        return  total;
    }

    public static int findBasins(String[][] map)
    {
        ArrayList<Integer> sizes = new ArrayList<>();
        HashSet<String> traveled = new HashSet<>();
        for(int r = 0; r < map.length; r++)
        {
            for(int c = 0; c < map[r].length; c++)
            {
                if(!map[r][c].equals("9") && !traveled.contains(r+","+c)) sizes.add(getBasinSize(r,c,map,traveled));
            }
        }
        Collections.sort(sizes);
        return sizes.get(sizes.size()-1) * sizes.get(sizes.size()-2) * sizes.get(sizes.size()-3);
    }

    public static int getBasinSize(int r, int c, String[][] map, HashSet<String> traveled)
    {
        HashSet<String> area = new HashSet<>();
        area.add(r+","+c);
        traveled.add(r+","+c);
        up(r-1,c,map,area,traveled);
        down(r+1,c,map,area,traveled);
        right(r,c+1,map,area,traveled);
        left(r,c-1,map,area,traveled);
        return area.size();
    }
    public static void up(int r, int c, String[][] map, HashSet<String> area, HashSet<String> traveled)
    {
        if(r >= 0 && !map[r][c].equals("9"))
        {
            if(!traveled.contains(r+","+c))
            {
                area.add(r+","+c);
                traveled.add(r+","+c);
                up(r-1,c,map,area,traveled);
                down(r+1,c,map,area,traveled);
                right(r,c+1,map,area,traveled);
                left(r,c-1,map,area,traveled);
            }
        }
    }
    public static void down(int r, int c, String[][] map, HashSet<String> area, HashSet<String> traveled)
    {
        if(r < map.length && !map[r][c].equals("9"))
        {
            if(!traveled.contains(r+","+c))
            {
                area.add(r+","+c);
                traveled.add(r+","+c);
                up(r-1,c,map,area,traveled);
                down(r+1,c,map,area,traveled);
                right(r,c+1,map,area,traveled);
                left(r,c-1,map,area,traveled);
            }
        }

    }
    public static void right(int r, int c, String[][] map, HashSet<String> area, HashSet<String> traveled)
    {
        if(c < map[r].length && !map[r][c].equals("9"))
        {
            if(!traveled.contains(r+","+c))
            {
                area.add(r+","+c);
                traveled.add(r+","+c);
                up(r-1,c,map,area,traveled);
                down(r+1,c,map,area,traveled);
                right(r,c+1,map,area,traveled);
                left(r,c-1,map,area,traveled);
            }
        }
    }
    public static void left(int r, int c, String[][] map, HashSet<String> area, HashSet<String> traveled)
    {
        if(c >= 0 && !map[r][c].equals("9"))
        {
            if(!traveled.contains(r+","+c))
            {
                area.add(r+","+c);
                traveled.add(r+","+c);
                up(r-1,c,map,area,traveled);
                down(r+1,c,map,area,traveled);
                right(r,c+1,map,area,traveled);
                left(r,c-1,map,area,traveled);
            }
        }
    }
}
