package AOC2024;
import java.io.*;
import java.util.*;
public class Day8 {
    public static void main(String[] args)
    {
        System.out.println(findAntiNode("inputs/input.txt",1));
        System.out.println(findAntiNode("inputs/input.txt",2));
    }

    public static int findAntiNode  (String fileName, int part)
    {
        File f = new File(fileName);
        int length = 50;
        if(fileName.contains("trial")) length = 12;
        String[][] map = new String[length][];
        try{
            Scanner s = new Scanner(f);
            int index = 0;
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                String[] temp = new String[line.length()];
                for(int i = 0; i < line.length(); i++) temp[i] = line.substring(i,i+1);
                map[index]=temp;
                index++;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        return countAntiNodes(map, part);
    }

    public static int countAntiNodes(String[][] map, int part)
    {
        String[][] temp = new String[map.length][map[0].length];
        for(String[] arr : temp)
        {
            Arrays.fill(arr,".");
        }
        for(int r = 0; r < map.length; r++)
        {
            for(int c = 0; c < map[r].length; c++)
            {
                String val = map[r][c];
                if(val.matches("\\w")){
                    if(part ==1)checkIsAntiNode(map,temp,r,c);
                    else checkIsAntiNodeHarmonics(map,temp,r,c);
                }
            }
        }
        return countTags(temp);
    }

    public static void checkIsAntiNode(String[][] map, String[][] temp, int r, int c)
    {
        String node = map[r][c];
        for(int j = 0; j < map.length;j++)
        {
            for(int k = 0; k < map[j].length; k++)
            {
                if(j == r && k == c) continue;
                String tempVal = map[j][k];
                if(tempVal.equals(node))
                {
                    int diffX = c-k;
                    int diffY = r-j;
                    int antiNodeX1 = c+diffX;
                    int antiNodeY1 = r+diffY;
                    if(antiNodeX1 < map[j].length && antiNodeX1 >= 0 && antiNodeY1 < map.length && antiNodeY1 >= 0 ) temp[antiNodeY1][antiNodeX1]= "#";
                    int antiNodeX2 = k-diffX;
                    int antiNodeY2 = j-diffY;
                    if(antiNodeX2 < map[j].length && antiNodeX2 >= 0 && antiNodeY2 < map.length && antiNodeY2 >= 0 ) temp[antiNodeY2][antiNodeX2]= "#";
                }
            }
        }
    }

    public static void checkIsAntiNodeHarmonics(String[][] map, String[][] temp, int r, int c)
    {
        String node = map[r][c];
        temp[r][c] = "#";
        for(int j = 0; j < map.length;j++)
        {
            for(int k = 0; k < map[j].length; k++)
            {
                if(j == r && k == c) continue;
                String tempVal = map[j][k];
                if(tempVal.equals(node))
                {
                    temp[j][k]="#";
                    int diffX = c-k;
                    int diffY = r-j;
                    int antiNodeX1 = c+diffX;
                    int antiNodeY1 = r+diffY;
                    while(antiNodeX1 < map[j].length && antiNodeX1 >= 0 && antiNodeY1 < map.length && antiNodeY1 >= 0)
                    {
                        temp[antiNodeY1][antiNodeX1]= "#";
                        antiNodeX1 +=diffX;
                        antiNodeY1 +=diffY;
                    }
                    int antiNodeX2 = k-diffX;
                    int antiNodeY2 = j-diffY;
                    while(antiNodeX2 < map[j].length && antiNodeX2 >= 0 && antiNodeY2 < map.length && antiNodeY2 >= 0 ) {
                        temp[antiNodeY2][antiNodeX2]= "#";
                        antiNodeX2 -=diffX;
                        antiNodeY2-=diffY;
                    }
                }
            }
        }
    }

    public static int countTags(String[][] temp)
    {
        int count = 0;
        for(String[] arr : temp )
        {
            for(String val : arr)
            {
                if(val != null && val.equals("#")) count++;
            }
        }
        return count;
    }

}
