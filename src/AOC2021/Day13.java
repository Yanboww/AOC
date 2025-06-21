package AOC2021;
import java.io.*;
import java.util.*;
public class Day13 {
    public static void main(String[] args){
        System.out.println(fold("inputs/input.txt",1));
        fold("inputs/input.txt",2);
    }

    public static int fold(String fileName, int part){
        File f = new File(fileName);
        ArrayList<String> coordinates = new ArrayList<>();
        ArrayList<String> folds = new ArrayList<>();
        int maxR = 0;
        int maxC = 0;
        try {
            Scanner s = new Scanner(f);
            boolean isCoordinate = true;
            while(s.hasNextLine()){
                String line = s.nextLine();
                if(line.isEmpty()){
                    isCoordinate = false;
                    continue;
                }
                if(isCoordinate){
                    coordinates.add(line);
                    String[] coordinatePoint = line.split(",");
                    int currentC = Integer.parseInt(coordinatePoint[0]);
                    int currentR = Integer.parseInt(coordinatePoint[1]);
                    if(currentC > maxC) maxC = currentC;
                    if(currentR > maxR) maxR = currentR;
                }
                else folds.add(line);

            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        String[][] instruction = new String[maxR+1][maxC+1];
        for(String[] row : instruction){
            Arrays.fill(row,".");
        }
        if(part == 1) return oneFold(instruction,coordinates,folds.get(0));
        String[][] result = allFold(instruction,coordinates,folds);
        for(String[] row : result) System.out.println(Arrays.toString(row));
        return 0;
    }

    public static int oneFold(String[][] instruction, ArrayList<String> coordinates, String fold) {
        fillInstruction(instruction,coordinates);
        String[][] newArr = simulateFold(instruction,fold);
        return count(newArr);
    }

    public static String[][] allFold(String[][] instruction, ArrayList<String> coordinates, ArrayList<String> fold){
        fillInstruction(instruction,coordinates);
        String[][] newArr = simulateFold(instruction,fold.get(0));
        for(int i = 1; i < fold.size(); i++){
            newArr = simulateFold(newArr, fold.get(i));
        }
        return newArr;
    }



    public static void fillInstruction(String[][] instruction, ArrayList<String> coordinates){
        for(String coordinate : coordinates){
            String[] coordinateArr = coordinate.split(",");
            instruction[Integer.parseInt(coordinateArr[1])][Integer.parseInt(coordinateArr[0])] = "#";
        }
    }

    public static String[][] simulateFold(String[][] arr, String fold){
        String[] foldArr = fold.split(" ")[2].split("=");
        String[][] newArr;
        int foldPosition = Integer.parseInt(foldArr[1]);

        if(foldArr[0].equals("x")){
            int half = arr[0].length/2;
            int length = foldPosition;
            int end = arr[0].length-1;
            int start = foldPosition - (end-foldPosition);
            if(foldPosition < half){
                length = half*2-foldPosition-1;
                start = 0;
                end = foldPosition*2;
            }
            newArr = new String[arr.length][length];
            for (int r = 0; r < newArr.length; r++){
                int tempStart = start;
                int tempEnd = end;
                int tempEnd2 = end+1;
                int tempStart2 = 0;
                for(int c = 0; c < newArr[0].length; c++){
                    if(foldPosition < half){
                        if(tempStart < foldPosition){
                            if(arr[r][tempStart].equals("#") || arr[r][tempEnd].equals("#")) newArr[r][c] = "#";
                            else newArr[r][c] = ".";
                            tempEnd--;
                            tempStart++;
                        }
                        else{
                            newArr[r][c] = arr[r][tempEnd2];
                            tempEnd++;
                        }

                    }
                    else {
                        if(tempStart2 < start){
                            newArr[r][c] = arr[r][tempStart2];
                            tempStart2++;
                        }else{
                            if(arr[r][tempStart].equals("#") || arr[r][tempEnd].equals("#")) newArr[r][c] = "#";
                            else newArr[r][c] = ".";
                            tempEnd--;
                            tempStart++;
                        }
                    }
                }
            }

        }
        else {
            int half = arr.length/2;
            int length = foldPosition;
            int end = arr.length-1;
            int start = foldPosition - (end-foldPosition);
            if(foldPosition < half){
                length = half*2-foldPosition-1;
                start = 0;
                end = foldPosition*2;
            }
            newArr = new String[length][arr[0].length];
            for (int c = 0; c < newArr[0].length; c++){
                int tempStart = start;
                int tempEnd = end;
                int tempEnd2 = end+1;
                int tempStart2 = 0;
                for(int r = 0; r < newArr.length; r++){
                    if(foldPosition < half){
                        if(tempStart < foldPosition){
                            if(arr[tempStart][c].equals("#") || arr[tempEnd][c].equals("#")) newArr[r][c] = "#";
                            else newArr[r][c] = ".";
                            tempEnd--;
                            tempStart++;
                        } else{
                            newArr[r][c] = arr[tempEnd2][c] ;
                            tempEnd2++;
                        }

                    }
                    else {
                        if(tempStart2 < start){
                            newArr[r][c] = arr[tempStart2][c];
                            tempStart2++;
                        }else{
                            if(arr[tempStart][c].equals("#") || arr[tempEnd][c].equals("#")) newArr[r][c] = "#";
                            else newArr[r][c] = ".";
                            tempEnd--;
                            tempStart++;
                        }
                    }
                }
            }
        }
        return newArr;
    }
    public static int count(String[][] arr){
        int count = 0;
        for(String[] row : arr){
            for(String point : row){
                if(point.equals("#")) count++;
            }
        }
        return count;
    }

}
