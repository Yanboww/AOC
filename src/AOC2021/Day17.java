package AOC2021;
import java.io.*;
import java.util.*;
public class Day17 {
    public static void main(String[] args){
        //day17
        System.out.println(findVelocity("inputs/input.txt",1));
        System.out.println(findVelocity("inputs/input.txt",2));
    }

    public static int findVelocity(String fileName, int part){
        File f = new File(fileName);
        int minX = 0;
        int maxX = 0;
        int minY = 0;
        int maxY = 0;
        try {
            Scanner s = new Scanner(f);
            String line = s.nextLine();
            String x = line.substring(line.indexOf("=")+1,line.indexOf(","));
            String y =  line.substring(line.lastIndexOf("=")+1);
            minX = Integer.parseInt(x.substring(0,x.indexOf(".")));
            maxX = Integer.parseInt(x.substring(x.lastIndexOf(".")+1));
            minY = Integer.parseInt(y.substring(0,y.indexOf(".")));
            maxY = Integer.parseInt(y.substring(y.lastIndexOf(".")+1));
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) return findHighestY(minY);
        return uniqueInitial(minY,maxY,minX, maxX);
    }

    public static int findHighestY(int minY){
        int yPos = 0;
        for(int velocity = Math.abs(minY) - 1; velocity > 0; velocity--){
            yPos+=velocity;
        }
        return yPos;
    }

    public static int uniqueInitial(int minY, int maxY, int minX, int maxX){
        int count = 0;
        for(int y = minY; y <= Math.abs(minY)-1; y++){
            for(int x = 0; x <= maxX; x++){
                if(isValid(y,x,minY,maxY,minX,maxX)) count++;
            }
        }
        return count;
    }

    public static boolean isValid(int y, int x, int minY, int maxY, int minX, int maxX){
        int xPos = 0;
        int yPos = 0;
        while(xPos <= maxX && yPos >= minY){
            xPos+=x;
            yPos+=y;
            y--;
            if(x > 0) x--;
            else if (x < 0) x++;
            if((xPos >= minX && xPos <= maxX) && (yPos <= maxY && yPos >= minY)) return true;
        }
        return false;
    }
}
