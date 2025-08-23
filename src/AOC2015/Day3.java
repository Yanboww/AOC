package AOC2015;
import java.io.*;
import java.util.*;
public class Day3 {
    public static void main(String[] args){
        System.out.println(delivery("inputs/input.txt",1));
        System.out.println(delivery("inputs/input.txt",2));
    }

     public static int delivery(String fileName, int part){
        File f = new File(fileName);
        String[] directions = new String[0];
        try {
            Scanner s = new Scanner(f);
            directions = s.nextLine().split("(?!^)");
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) return countHouses(directions);
        return roboSanta(directions);
     }

     public static int countHouses(String[] directions){
        HashSet<String> houses = new HashSet<>();
        houses.add("0,0");
        int x = 0; int y = 0;
        for(String direction : directions){
            switch (direction){
                case ">": x++; break;
                case "<": x--; break;
                case "^": y++; break;
                case "v": y--; break;
            }
            houses.add(x+","+y);
        }
        return houses.size();
     }

     public static int roboSanta(String[] directions){
        HashSet<String> houses = new HashSet<>();
         houses.add("0,0");
         int x1 = 0; int y1 = 0; int x2 = 0; int y2 = 0;
         boolean santa = true;
         for(String direction : directions){
             switch (direction){
                 case ">": if(santa) x1++; else x2++; break;
                 case "<": if(santa) x1--; else x2--; break;
                 case "^": if(santa) y1++; else y2++; break;
                 case "v": if(santa) y1--; else y2--; break;
             }
             if(santa) houses.add(x1+","+y1);
             else houses.add(x2+","+y2);
             santa = !santa;
         }
         return houses.size();
     }



}
