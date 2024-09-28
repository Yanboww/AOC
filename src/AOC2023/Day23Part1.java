package AOC2023;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
public class Day23Part1 {
    public static void main(String[] args) {
        String[][] maze = getMatrix("Data/Trial");
        System.out.println(findPaths(maze));
        printMatrix(maze);
        String red = "\u001B[31m";
        String reset = "\u001B[0m";
    }

    public static String[][] getMatrix(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }

        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        int rows = fileData.size();
        int cols = fileData.get(0).length();

        String[][] matrix = new String[rows][cols];

        for (int i = 0; i < fileData.size(); i++) {
            String d = fileData.get(i);
            for (int j = 0; j < d.length(); j++) {
                String number = d.charAt(j) + "";
                matrix[i][j] = number;
            }
        }
        return matrix;
    }

    public static void printMatrix(String[][] matrix) {
        for (String[] line : matrix) {
            System.out.println(Arrays.toString(line));
        }
    }

    public static void printSolution(ArrayList<String>solution)
    {
        System.out.print(solution.get(0));
        for(int i = 1;i<solution.size();i++)
        {
            System.out.print(" ---> " + solution.get(i));
        }
        System.out.println();
    }

    public static int findPaths(String[][] maze) {
        final int[] start = findPointStart(maze[0],0,".");
        final int[] end = findPointEnd(maze[maze.length-1],maze.length-1,".");
        int max = 0;
        String red = "\u001B[31m";
        String reset = "\u001B[0m";
        maze[end[0]][end[1]] = "O";
        for(int z = 0; z < 2; z++)
        {
            ArrayList<String> steps = new ArrayList<>();
            ArrayList<String> queue = new ArrayList<>();
            queue.add("(" + start[0] + "," + start[1] +")");
            String[][] matrix = copyArray(maze);
            matrix[end[0]][end[1]] = ".";
            matrix[start[0]][start[1]] = ".";
            while(!queue.isEmpty())
            {
                String coord = queue.get(0);
                int prevRow = Integer.parseInt(coord.substring(coord.indexOf("(")+1, coord.indexOf(",")));
                int prevColumn = Integer.parseInt(coord.substring(coord.indexOf(",")+1, coord.indexOf(")")));
                steps.add(coord);
                String breakPoint = "";
                while(prevRow!=end[0] || prevColumn!=end[1])
                {
                    int storageRow = prevRow;
                    int storageColumn = prevColumn;
                    matrix[prevRow][prevColumn] = "O";
                    boolean foundAPath = false;
                    String top = null;
                    String bottom = null;
                    String left = null;
                    String right = null;
                    if (prevRow != 0) top = matrix[prevRow - 1][prevColumn];
                    if (prevRow != matrix.length - 1) bottom = matrix[prevRow + 1][prevColumn];
                    if (prevColumn != 0) left = matrix[prevRow][prevColumn - 1];
                    if (prevColumn != matrix[0].length - 1) right = matrix[prevRow][prevColumn + 1];
                    if (prevRow != 0) top = matrix[prevRow - 1][prevColumn];
                    if (prevRow != matrix.length - 1) bottom = matrix[prevRow + 1][prevColumn];
                    if (prevColumn != 0) left = matrix[prevRow][prevColumn - 1];
                    if (prevColumn != matrix[0].length - 1) right = matrix[prevRow][prevColumn + 1];
                    if (top != null && top.equals(".") || top != null && top.equals(red + "P"+reset)){
                        prevRow -= 1;
                        steps.add("(" + prevRow + "," + prevColumn +")");
                        foundAPath = true;
                    }
                    if (bottom!= null && bottom.equals(".") ||bottom!= null && bottom.equals("v") ||bottom!= null && bottom.equals(red+"P"+reset) ) {
                        if (!foundAPath){
                            prevRow += 1;
                            steps.add("(" + prevRow + "," + prevColumn +")");
                            foundAPath = true;
                        }
                        else{
                            int changeRow = storageRow+1;
                            breakPoint = steps.get(steps.size()-1);
                            if(!matrix[changeRow][storageColumn].equals(red+"P"+reset))
                            {
                                if(queue.size()>1) queue.remove(1);
                                queue.add(1,"(" + changeRow+ "," + storageColumn +")");
                            }

                        }
                    }
                    if (left!=null && left.equals(".") || left!=null && left.equals("<")|| left!=null && left.equals(red+"P"+reset)) {
                        if (!foundAPath){
                            prevColumn -= 1;
                            steps.add("(" + prevRow + "," + prevColumn +")");
                            foundAPath = true;
                        }
                        else{
                            int changeColumn = storageColumn-1;
                            breakPoint = steps.get(steps.size()-1);
                            if(!matrix[storageRow][changeColumn].equals(red+"P"+reset))
                            {
                                if(queue.size()>1) queue.remove(1);
                                queue.add(1,"(" + storageRow+ "," + changeColumn +")");
                            }
                        }
                    }
                    if (right!=null && right.equals(".") || right!=null && right.equals(">")|| right!=null && right.equals(red+"P"+reset)) {
                        if (!foundAPath){
                            prevColumn += 1;
                            steps.add("(" + prevRow + "," + prevColumn +")");
                            foundAPath = true;
                        }
                        else{
                            int changeColumn = storageColumn+1;
                            breakPoint = steps.get(steps.size()-1);
                            if(!matrix[storageRow][changeColumn].equals(red+"P"+reset))
                            {
                                if(queue.size()>1) queue.remove(1);
                                queue.add(1,"(" + storageRow+ "," + changeColumn +")");
                            }
                        }
                    }
                    if(!foundAPath){
                        steps = listSub(breakPoint,steps, maze);
                        break;
                    }
                }
                queue.remove(0);
                if(steps.contains("(" + end[0] + "," + end[1] +")")) break;
            }
            checkList(steps, maze);
           if(steps.size()-1>max) max = steps.size()-1;
           System.out.println(steps.size()-1);
           fillCoord(maze,steps);
            printMatrix(maze);
        }
        return max;
    }



    public static int[] findPointStart(String[] line, int rowNum, String key)
    {
        int[] coord = {rowNum,0};
        for(int i = 0; i<line.length;i++)
        {
            if(line[i].equals(key)){
                coord[1] = i;
                return coord;
            }
        }
        return coord;
    }

    public static int[] findPointEnd(String[] line, int rowNum, String key)
    {
        int[] coord = {rowNum,0};
        for(int i = 0; i<line.length;i++)
        {
            if(line[i].equals(key)){
                coord[1] = i;
            }
        }
        return coord;
    }

    public static ArrayList<String> listSub(String start, ArrayList<String> steps, String[][] maze)
    {
        //System.out.println(steps);
        //System.out.println(start + " --");
        int coord = steps.indexOf(start);
        ArrayList<String> list = new ArrayList<>();
        if(coord == 0 || coord == -1) return steps;
        for(int i = 0; i<coord; i++)
        {
            list.add(steps.get(i));
        }
        //System.out.println(list);
        return list;
    }

    public static void checkList(ArrayList<String> steps, String[][]maze)
    {
        for(int i = steps.size()-1;i>0;i--)
        {
            String coord = steps.get(i);
            String coord1 = steps.get(i-1);
            int row = Integer.parseInt(coord.substring(coord.indexOf("(")+1, coord.indexOf(",")));
            int column = Integer.parseInt(coord.substring(coord.indexOf(",")+1, coord.indexOf(")")));
            int prevRow = Integer.parseInt(coord1.substring(coord1.indexOf("(")+1, coord1.indexOf(",")));
            int prevColumn = Integer.parseInt(coord1.substring(coord1.indexOf(",")+1, coord1.indexOf(")")));
            if(Math.abs(row-prevRow)>1 || Math.abs(prevColumn-column)>1 || row!=prevRow && column!=prevColumn)
            {
                steps.remove(i-1);
            }
        }
    }

    public static String[][] copyArray ( String[][] orig)
    {
        String[][] temp = new String[orig.length][orig[0].length];
        for(int i = 0; i < orig.length; i++)
        {
            for(int a = 0; a < orig[i].length; a++)
            {
                temp[i][a] = orig[i][a];
            }
        }
        return temp;
    }

    public static boolean contains(String[][] array, String key)
    {
        for(String[] lines : array)
        {
            for(String points : lines)
            {

                if(points.equals(key)) return true;
            }
        }
        return false;
    }

    public static int findMax (ArrayList<Integer> values)
    {
        int max = values.get(0);
        for( int numbers : values)
        {
            if(max<numbers) max = numbers;
        }
        return max;
    }

    public static void fillCoord(String[][] matrix, ArrayList<String> steps)
    {
        String red = "\u001B[31m";
        String reset = "\u001B[0m";
        for(String coord : steps)
        {
            int row = Integer.parseInt(coord.substring(coord.indexOf("(")+1, coord.indexOf(",")));
            int column = Integer.parseInt(coord.substring(coord.indexOf(",")+1, coord.indexOf(")")));
            matrix[row][column] = red + "P" + reset;
        }
    }

    public static void printSolutionMaze(String[][] maze, ArrayList<String> steps)
    {
        String blue = "\u001B[34m";
        String reset = "\u001B[0m";
        for(String coord : steps)
        {
            int row = Integer.parseInt(coord.substring(1,coord.indexOf(",")));
            int column = Integer.parseInt(coord.substring(coord.indexOf(",")+1,coord.length()-1));
            maze[row][column] = blue + "V" + reset;

        }
        printMatrix(maze);
    }







}
