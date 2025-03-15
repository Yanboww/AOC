package AOC2020;
import java.io.*;
import java.util.*;
public class Day11 {
    public static void main(String[] args){
        //System.out.println(findSeating("inputs/input.txt",1));
        System.out.println(findSeating("inputs/input.txt",2));
        //System.out.println(findSeating("inputs/trial",2));
    }

    public static int findSeating(String fileName,int part){
        File f = new File(fileName);
        int rows = 97;
        if(fileName.contains("trial")) rows = 10;
        String[][] map = new String[rows][];
        try{
            Scanner s = new Scanner(f);
            int index = 0;
            while(s.hasNextLine()) {
                map[index] = s.nextLine().split("(?!^)");
                index++;
            }

        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        return numberOccupied(map,part);
    }

    public static int numberOccupied(String[][] map, int part){
        String[][] copy = new String[map.length][map[0].length];
        while(true){
            boolean variable = !arrayCopy(map,copy);
            if(!variable) break;
            if(part == 1) simulate(map,copy);
            else simulate2(map,copy);
        }
        return countSeated(map);
    }

    public static boolean arrayCopy(String[][] original, String[][] newArr){
        boolean isEqual = true;
        for(int i = 0; i < original.length; i++){
            if(!Arrays.equals(original[i],newArr[i])) isEqual = false;
            System.arraycopy(original[i],0,newArr[i],0,original[i].length);
        }
        return isEqual;
    }

    public static void simulate(String[][] map, String[][] copy){
        for(int r = 0; r < copy.length; r++) {
            for(int c = 0; c < copy[r].length; c++){
               if(!copy[r][c].equals(".")){
                   int occupancy = 0;
                   if(r != 0 ){
                       if(copy[r-1][c].equals("#")) occupancy++;
                       if(c != 0) if(copy[r-1][c-1].equals("#")) occupancy++;
                       if(c != copy[0].length-1) if(copy[r-1][c+1].equals("#")) occupancy++;
                   }
                   if(r != copy.length-1) {
                       if(copy[r+1][c].equals("#")) occupancy++;
                       if(c != 0) if(copy[r+1][c-1].equals("#")) occupancy++;
                       if(c != copy[0].length-1) if(copy[r+1][c+1].equals("#")) occupancy++;
                   }
                   if(c != 0) if(copy[r][c-1].equals("#")) occupancy++;
                   if(c != copy[0].length-1) if(copy[r][c+1].equals("#")) occupancy++;
                   if(occupancy >= 4) map[r][c] = "L";
                   else if(occupancy == 0) map[r][c] = "#";
               }
            }
        }
    }

    public static void simulate2(String[][] map, String[][] copy){
        for(int r = 0; r < copy.length; r++){
            for(int c = 0; c < copy[0].length; c++) {
                if(!copy[r][c].equals(".")){
                    int occupancy = 0;
                    int tempR = r;
                    while(tempR > 0){
                        tempR--;
                        String temp = copy[tempR][c];
                        if(!temp.equals(".")){
                            if(temp.equals("#")) occupancy++;
                            break;
                        }
                    }
                    tempR = r;
                    while(tempR < copy.length-1){
                        tempR++;
                        String temp = copy[tempR][c];
                        if(!temp.equals(".")){
                            if(temp.equals("#")) occupancy++;
                            break;
                        }
                    }
                    int tempC = c;
                    while(tempC > 0){
                        tempC--;
                        String temp = copy[r][tempC];
                        if(!temp.equals(".")){
                            if(temp.equals("#")) occupancy++;
                            break;
                        }
                    }
                    tempC = c;
                    while(tempC < copy[0].length-1){
                        tempC++;
                        String temp = copy[r][tempC];
                        if(!temp.equals(".")){
                            if(temp.equals("#")) occupancy++;
                            break;
                        }
                    }
                    tempR = r;
                    tempC = c;
                    while(tempC > 0 && tempR > 0){
                        tempC--;
                        tempR--;
                        String temp = copy[tempR][tempC];
                        if(!temp.equals(".")){
                            if(temp.equals("#")) occupancy++;
                            break;
                        }
                    }
                    tempR = r;
                    tempC = c;
                    while(tempC < copy[0].length-1 && tempR > 0){
                        tempC++;
                        tempR--;
                        String temp = copy[tempR][tempC];
                        if(!temp.equals(".")){
                            if(temp.equals("#")) occupancy++;
                            break;
                        }
                    }
                    tempR = r;
                    tempC = c;
                    while(tempC > 0 && tempR < copy.length-1){
                        tempC--;
                        tempR++;
                        String temp = copy[tempR][tempC];
                        if(!temp.equals(".")){
                            if(temp.equals("#")) occupancy++;
                            break;
                        }
                    }
                    tempR = r;
                    tempC = c;
                    while(tempC < copy[0].length-1 && tempR < copy.length-1){
                        tempC++;
                        tempR++;
                        String temp = copy[tempR][tempC];
                        if(!temp.equals(".")){
                            if(temp.equals("#")) occupancy++;
                            break;
                        }
                    }
                    if(occupancy >= 5) map[r][c] = "L";
                    else if(occupancy == 0) map[r][c] = "#";
                }
            }
        }
    }

    public static int countSeated(String[][] map){
        int count = 0;
        for(String[] row : map){
            for(String seat : row){
                if(seat.equals("#")) count++;
            }
        }
        return count;
    }
}
