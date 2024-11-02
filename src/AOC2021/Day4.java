package AOC2021;
import java.io.File;
import java.io.FileNotFoundException;
//import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
public class Day4 {
    public static void main(String[] args){
        System.out.println(findBingoScore("inputs/AOC2021Inputs/input4"));
        System.out.println(findLastToWinScore("inputs/AOC2021Inputs/input4"));
    }
    public static int findBingoScore(String fileName){
        File f = new File(fileName);
        String[] randomNumbers = new String[1];
        ArrayList<String[][]> boards = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            randomNumbers = s.nextLine().split(",");
            String[][] board = new String[5][];
            int index = 0;
            s.nextLine();
            while(s.hasNextLine())
            {
                String line = s.nextLine().trim();
                if(line.isEmpty()){
                    boards.add(board);
                    board = new String[5][];
                    index = 0;
                }
                else{
                    board[index] = line.split("\\s+");
                    index++;
                }
            }
            boards.add(board);
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        /*for(String[][] board : boards){
            for(String[] line : board)
            {
                System.out.println(Arrays.toString(line));
            }
            System.out.println();
        }**/
        int score = 0;
        int winIndex = -1;
        for(String num : randomNumbers) {
            score = Integer.parseInt(num);
            for(String[][] board : boards){
                markBoard(num,board);
            }
            winIndex = checkWin(boards);
            if(winIndex != -1) break;
        }
        //System.out.println(score +" " + findSum(boards.get(winIndex)));
        return score * findSum(boards.get(winIndex));
    }
    public static int findLastToWinScore(String fileName){
        File f = new File(fileName);
        String[] randomNumbers = new String[1];
        ArrayList<String[][]> boards = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            randomNumbers = s.nextLine().split(",");
            String[][] board = new String[5][];
            int index = 0;
            s.nextLine();
            while(s.hasNextLine())
            {
                String line = s.nextLine().trim();
                if(line.isEmpty()){
                    boards.add(board);
                    board = new String[5][];
                    index = 0;
                }
                else{
                    board[index] = line.split("\\s+");
                    index++;
                }
            }
            boards.add(board);
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        int score = 0;
        for(String num : randomNumbers) {
            score = Integer.parseInt(num);
            for(String[][] board : boards){
                markBoard(num,board);
            }
            removeWin(boards,score);
            if(boards.size() == 1 && checkWin(boards) != -1) break;
        }
        return score * findSum(boards.get(0));
    }

    private static void removeWin(ArrayList<String[][]> boards, int score){
        for(int i = 0; i < boards.size(); i++){
            String[][] board = boards.get(i);
            if(checkBoardColumn(board) || checkBoardRow(board)){
               if(boards.size() > 1){
                   //System.out.println(score * findSum(boards.get(i)));
                   boards.remove(i);
                   i--;
               }
            }
        }
    }



    private static void markBoard(String num, String[][] board)
    {
        for(int r = 0; r < board.length; r++)
        {
            for(int c = 0; c < board[r].length; c++){
                if(board[r][c].equals(num)) board[r][c] = ".";
            }
        }
    }
    private static int checkWin(ArrayList<String[][]> boards){
        for(int i = 0; i < boards.size(); i++){
            String[][] board = boards.get(i);
            if(checkBoardColumn(board) || checkBoardRow(board)) return i;
        }
        return -1;
    }
    private static boolean checkBoardRow(String[][] board){
        for(String[] row : board)
        {
            boolean rowMarked = true;
            for(String val : row){
                if(!val.equals(".")){
                    rowMarked = false;
                    break;
                }
            }
            if(rowMarked){
                return true;
            }
        }
        return false;
    }
    private static boolean checkBoardColumn(String[][] board)
    {
        for(int c = 0; c < board[0].length; c++)
        {
            boolean colMarked = true;
            for(String[] row : board){
                if(!row[c].equals(".")){
                    colMarked = false;
                    break;
                }
            }
            if(colMarked){
                return true;
            }
        }
        return false;
    }

    private static int findSum(String[][] board)
    {
        int sum = 0;
        for(String[] row : board)
        {
            for(String val : row){
                if(!val.equals(".")) sum+=Integer.parseInt(val);
            }
        }
        return sum;
    }

}
