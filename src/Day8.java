import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Day8 {
    public static void main(String[] args) {
        System.out.println(findVisible("inputs/input8"));
        System.out.println(findBestScenicScore("inputs/input8"));
    }

    public static int findVisible(String fileName)
    {
        int[][] forestMap = new int[99][99];
        int totalVisible;
        try{
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            int lineNum = 0;
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                for(int i = 0; i < line.length(); i++)
                {
                    forestMap[lineNum][i] = Integer.parseInt(line.substring(i,i+1));
                }
                lineNum++;
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found");
        }
        totalVisible = forestMap.length *2 + (forestMap[0].length - 2) * 2;
        for(int r = 1; r < forestMap.length-1; r ++)
        {
            for(int c = 1; c < forestMap[r].length-1; c++)
            {
                int currentVal = forestMap[r][c];
                boolean left = checkLeft(r, c, forestMap, currentVal);
                boolean right = checkRight(r, c, forestMap, currentVal);
                boolean up = checkUp(r,c,forestMap,currentVal);
                boolean down = checkDown(r,c,forestMap,currentVal);
                if(left || right || up || down)
                {
                    totalVisible++;
                }
            }
        }
        return totalVisible;
    }
    public static int findBestScenicScore(String fileName)
    {
        int[][] forestMap = new int[99][99];
        int bestScore = 0;
        try{
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            int lineNum = 0;
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                for(int i = 0; i < line.length(); i++)
                {
                    forestMap[lineNum][i] = Integer.parseInt(line.substring(i,i+1));
                }
                lineNum++;
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found");
        }
        for(int r = 0; r < forestMap.length; r ++)
        {
            for(int c = 0; c < forestMap[r].length; c++)
            {
                int currentVal = forestMap[r][c];
                int left = leftScore(r,c,forestMap,currentVal);
                int right = rightScore(r,c,forestMap,currentVal);
                int up = upScore(r,c,forestMap,currentVal);
                int down = downScore(r,c,forestMap,currentVal);
                int score = left*right*up*down;
                bestScore = Math.max(score,bestScore);
                //System.out.println(left + " " + right + " " + up + " "+ down + " :" + r + " " + c);
            }
        }
        return bestScore;
    }


    public static boolean checkLeft(int row, int col, int[][] map, int currentVal)
    {
        for(int c = 0; c < col; c++ )
        {
            if(map[row][c] >= currentVal) return false;
        }
        return true;
    }

    public static boolean checkRight(int row, int col, int[][] map, int currentVal)
    {
        for(int c = col+1; c < map[row].length; c++)
        {
            if(map[row][c] >= currentVal) return false;
        }
        return true;
    }

    public static boolean checkUp(int row, int col, int[][] map, int currentVal)
    {
        for(int r = 0; r < row; r ++)
        {
            if(map[r][col] >= currentVal) return false;
        }
        return true;
    }

    public static boolean checkDown(int row, int col, int[][] map, int currentVal)
    {
        for(int r = row +1; r < map.length; r++)
        {
            if(map[r][col] >= currentVal) return false;
        }
        return true;
    }

    public static int leftScore(int row, int col, int[][] map, int currentVal)
    {
        int score = 0;
        for(int c = col-1; c >=0; c-- )
        {
            score++;
            if(map[row][c] >= currentVal) break;
        }
        return score;
    }

    public static int rightScore(int row, int col, int[][] map, int currentVal)
    {
        int score = 0;
        for(int c = col+1; c < map[row].length; c++)
        {
            score++;
            if(map[row][c] >= currentVal) break;
        }
        return score;
    }

    public static int upScore(int row, int col, int[][] map, int currentVal)
    {
        int score = 0;
        for(int r = row-1; r >=0; r --)
        {
            score++;
            if(map[r][col] >= currentVal) break;
        }
        return score;
    }

    public static int downScore(int row, int col, int[][] map, int currentVal)
    {
        int score =0;
        for(int r = row +1; r < map.length; r++)
        {
            score++;
            if(map[r][col] >= currentVal) break;
        }
        return score;
    }



}