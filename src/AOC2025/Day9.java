package AOC2025;
import java.awt.*;
import java.awt.geom.Area;
import java.io.*;
import java.util.*;

public class Day9 {
    public static void main(String[] args){
        long start = System.nanoTime();
        System.out.println(theater("inputs/input.txt",1));
        System.out.println(theater("inputs/input.txt",2));
        long end = System.nanoTime();
        System.out.println((double)(end-start)/1000000000);
    }

    public static long theater (String fileName, int part){
        ArrayList<int[]> inputs = new ArrayList<>();
        int[] first = null;
        try{
            Scanner s = new Scanner(new File(fileName));
            while(s.hasNextLine()){
                int[] line = Arrays.stream(s.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
                inputs.add(line);
                if(first == null) first = line;
            }
            inputs.add(first);
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        Polygon shape = constructPolygon(inputs);
        Area shapeArea = new Area(shape);
        return biggestRectangle(inputs, shapeArea, part);
    }

    public static long biggestRectangle(ArrayList<int[]> inputs, Area shape, int part){
        PriorityQueue<Long> rectangles = new PriorityQueue<>(Comparator.reverseOrder());
        HashSet<String> used = new HashSet<>();
        for(int i = 0; i < inputs.size(); i++){
            for(int j = 0; j < inputs.size(); j++){
                if(i == j) continue;
                if(!used.contains(i+","+j) && !used.contains(j+","+i)){
                    used.add(i+","+j);
                    int[] red1 = inputs.get(i);
                    int[] red2 = inputs.get(j);
                    long result = (long)(Math.abs(red1[0]-red2[0])+1) * (Math.abs(red1[1]-red2[1])+1);
                    if(!rectangles.isEmpty() && result < rectangles.peek()) continue;
                    if(part == 1) rectangles.offer(result);
                    else{
                        if(isValidRectangle(shape,red1,red2)){
                            rectangles.offer(result);
                        }
                    }
                }
            }
        }
        if(rectangles.isEmpty()){
            return 0;
        }
        return rectangles.poll();
    }

    public static boolean isValidRectangle(Area shape, int[] red1, int[] red2){
        int x = Math.min(red1[0],red2[0]);
        int y = Math.min(red1[1],red2[1]);
        int width = (Math.abs(red1[0]-red2[0]));
        int height = (Math.abs(red1[1]-red2[1]));
        Rectangle rect = new Rectangle(x,y,width,height);
        return shape.contains(rect);
    }

    public static Polygon constructPolygon(ArrayList<int[]> coordinates) {
        Polygon shape = new Polygon();
        shape.reset();
        int[] prev = null;
        for (int[] val : coordinates) {
            if (prev == null){
                shape.addPoint(val[0], val[1]);
            }
            else {
                if (prev[0] < val[0]) {
                    for (int i = prev[0] + 1; i <= val[0]; i++) {
                        shape.addPoint(i, val[1]);
                    }
                } else if (prev[0] > val[0]) {
                    for (int i = prev[0] - 1; i >= val[0]; i--) {
                        shape.addPoint(i, val[1]);
                    }
                } else if (prev[1] < val[1]) {
                    for (int i = prev[1] + 1; i <= val[1]; i++) {
                        shape.addPoint(val[0], i);
                    }
                } else if (prev[1] > val[1]) {
                    for (int i = prev[1] - 1; i >= val[1]; i--) {
                        shape.addPoint(val[0], i);
                    }
                }
            }
            prev = val;
        }
        return shape;
    }
}
