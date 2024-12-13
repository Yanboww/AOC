package AOC2024;
import java.io.*;
import java.util.*;
public class Day12 {
    public static void main(String[] args) {
        System.out.println(findFencePrice("inputs/input.txt",1));
        System.out.println(findFencePrice("inputs/input.txt",2));
    }

    public static int findFencePrice(String fileName, int part)
    {
        File f = new File(fileName);
        int length = 140;
        if(fileName.contains("trial")) length = 10;
        String[][] map = new String[length][];
        try{
            Scanner s = new Scanner(f);
            int index = 0;
            while(s.hasNextLine())
            {
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
        Boolean[][] mapTest = new Boolean[map.length][map[0].length];
        for(Boolean[] bool : mapTest)
        {
            Arrays.fill(bool,false);
        }
        return findTotal(map, mapTest, part);
    }

    public static int findTotal(String[][]map, Boolean[][] mapTest, int part)
    {
        int total = 0;
        for(int r = 0; r < map.length; r++)
        {
            for(int c = 0; c < map[r].length; c++)
            {
                if(!mapTest[r][c]) total += findCost(r,c,map, mapTest, part);
            }
        }
        return total;
    }

    public static int findCost(int r, int c, String[][] map, Boolean[][] mapTest, int part)
    {
        HashSet<String> area = new HashSet<>();
        ArrayList<String> edges = new ArrayList<>();
        String val = map[r][c];
        up(r,c,val,map,mapTest,area,edges);
        down(r,c,val,map,mapTest,area,edges);
        right(r,c,val,map,mapTest,area,edges);
        left(r,c,val,map,mapTest,area,edges);
        Collections.sort(edges);
        if(part == 1) return area.size()*edges.size();
        int sides = calcSides(edges);
        return area.size() * sides;
    }



    public static int calcSides(ArrayList<String> edges)
    {
        int count = 1;
        String[] coordinates = edges.get(0).split("\\W+");
        String current = coordinates[0];
        int prevR = Integer.parseInt(coordinates[1]);
        int prevC = Integer.parseInt(coordinates[2]);
        for(int i = 1; i < edges.size(); i++)
        {
            String side = edges.get(i);
            coordinates = side.split(",");
            if(!current.equals(coordinates[0])){
                current = coordinates[0];
                if(current.equals("U") || current.equals("D")) {
                    prevR = Integer.parseInt(coordinates[1]);
                    prevC = Integer.parseInt(coordinates[2]);
                }
                else{
                    prevR =Integer.parseInt(coordinates[2]);
                    prevC =Integer.parseInt(coordinates[1]);
                }
                count++;
            }
            else if (current.equals("U") || current.equals("D"))
            {
                int r = Integer.parseInt(coordinates[1]);
                int c = Integer.parseInt(coordinates[2]);
                if(prevR != r || prevC+1 != c){
                    count++;
                }
                prevR = r;
                prevC = c;

            }
            else if(current.equals("R") || current.equals("L"))
            {
                int r = Integer.parseInt(coordinates[2]);
                int c = Integer.parseInt(coordinates[1]);
                if(c != prevC || prevR+1 != r){
                    count++;
                }
                prevR = r;
                prevC = c;
            }
        }
        return count;
    }

    public static void up(int r, int c, String val, String[][] map, Boolean[][] mapTest, HashSet<String> area, ArrayList<String> edges)
    {
        if(r >= 0 && map[r][c].equals(val))
        {
            if(!mapTest[r][c])
            {
                area.add(r+","+c);
                mapTest[r][c] = true;
                up(r-1,c,val,map,mapTest,area,edges);
                down(r+1,c,val,map,mapTest,area,edges);
                right(r,c+1,val,map,mapTest,area,edges);
                left(r,c-1,val,map,mapTest,area,edges);
            }
        }
        else{
            String cString = Integer.toString(c);
            if(cString.length() == 2 && !cString.contains("-")) cString ="0" +cString;
            else if(cString.length() == 1 && !cString.contains("-")) cString="00"+cString;
            String rString = Integer.toString(r);
            if(rString.length()==2 && !rString.contains("-")) rString="0"+rString;
            else if(rString.length()==1 & !rString.contains("-")) rString="00"+rString;
            edges.add("U,"+rString+","+cString);
        }

    }
    public static void down(int r, int c, String val, String[][] map, Boolean[][] mapTest, HashSet<String> area, ArrayList<String> edges)
    {
        if(r < map.length && map[r][c].equals(val))
        {
            if(!mapTest[r][c])
            {
                area.add(r+","+c);
                mapTest[r][c] = true;
                up(r-1,c,val,map,mapTest,area,edges);
                down(r+1,c,val,map,mapTest,area,edges);
                right(r,c+1,val,map,mapTest,area,edges);
                left(r,c-1,val,map,mapTest,area,edges);
            }
        }
        else{
            String cString = Integer.toString(c);
            if(cString.length() == 2 && !cString.contains("-")) cString ="0" +cString;
            else if(cString.length() == 1 && !cString.contains("-")) cString="00"+cString;
            String rString = Integer.toString(r);
            if(rString.length()==2 && !rString.contains("-")) rString="0"+rString;
            else if(rString.length()==1 & !rString.contains("-")) rString="00"+rString;
            edges.add("D,"+rString+","+cString);
        }

    }
    public static void right(int r, int c, String val, String[][] map, Boolean[][] mapTest, HashSet<String> area, ArrayList<String> edges)
    {
        if(c < map[r].length && map[r][c].equals(val))
        {
            if(!mapTest[r][c])
            {
                area.add(r+","+c);
                mapTest[r][c] = true;
                up(r-1,c,val,map,mapTest,area,edges);
                down(r+1,c,val,map,mapTest,area,edges);
                right(r,c+1,val,map,mapTest,area,edges);
                left(r,c-1,val,map,mapTest,area,edges);
            }
        }
        else{
            String cString = Integer.toString(c);
            if(cString.length() == 2 && !cString.contains("-")) cString ="0" +cString;
            else if(cString.length() == 1 && !cString.contains("-")) cString="00"+cString;
            String rString = Integer.toString(r);
            if(rString.length()==2 && !rString.contains("-")) rString="0"+rString;
            else if(rString.length()==1 & !rString.contains("-")) rString="00"+rString;
            edges.add("R,"+cString+","+rString);
        }
    }
    public static void left(int r, int c, String val, String[][] map, Boolean[][] mapTest, HashSet<String> area, ArrayList<String> edges)
    {
        if(c >= 0 && map[r][c].equals(val))
        {
            if(!mapTest[r][c])
            {
                area.add(r+","+c);
                mapTest[r][c] = true;
                up(r-1,c,val,map,mapTest,area,edges);
                down(r+1,c,val,map,mapTest,area,edges);
                right(r,c+1,val,map,mapTest,area,edges);
                left(r,c-1,val,map,mapTest,area,edges);
            }
        }
        else{
            String cString = Integer.toString(c);
            if(cString.length() == 2 && !cString.contains("-")) cString ="0" +cString;
            else if(cString.length() == 1 && !cString.contains("-")) cString="00"+cString;
            String rString = Integer.toString(r);
            if(rString.length()==2 && !rString.contains("-")) rString="0"+rString;
            else if(rString.length()==1 & !rString.contains("-")) rString="00"+rString;
            edges.add("L,"+cString+","+rString);
        }
    }
}
