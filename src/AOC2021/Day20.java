package AOC2021;
import java.io.*;
import java.util.*;
public class Day20 {
    private static String everything = ".";
    private static final HashMap<String, String> binary = new HashMap<>();
    public static void main(String[] args){
        binary.put("#","1");
        binary.put(".","0");
        System.out.println(enhance("inputs/input.txt",1));
        System.out.println(enhance("inputs/input.txt",2));
    }

    public static int enhance(String fileName, int part){
        File f = new File(fileName);
        int length = 300;
        if(fileName.contains("trial")) length = 165;
        String[] algorithm = new String[0];
        String[][] image = new String[length][];
        try {
            Scanner s = new Scanner(f);
            algorithm = s.nextLine().split("(?!^)");
            s.nextLine();
            int index = 0;
            while (index < length/3){
                String[] temp = new String[length];
                Arrays.fill(temp,".");
                image[index] = temp;
                index++;
            }
            while (s.hasNextLine()){
                String[] temp = new String[length];
                Arrays.fill(temp,".");
                String[] temp2 = s.nextLine().split("(?!^)");
                System.arraycopy(temp2,0,temp,length/3,temp2.length);
                image[index] = temp;
                index++;
            }
            while(index < image.length){
                String[] temp = new String[length];
                Arrays.fill(temp,".");
                image[index] = temp;
                index++;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        if(part == 1) return litPixels(algorithm,image);
        return lit50(algorithm,image);
    }

    public static int litPixels(String[] algorithm, String[][] image){
        String[][] output = enhancement(algorithm,image);
        String[][] twiceOutput = enhancement(algorithm, output);
        return countLit(twiceOutput);
    }

    public static int lit50(String[] algorithm, String[][] image){
        for(int i = 0; i < 50; i++){
            image = enhancement(algorithm,image);
        }
        return countLit(image);
    }


    public static String[][] enhancement (String[] algorithm, String[][] input){
        String[][] output = new String[input.length][input[0].length];
        for(int r = 0; r < input.length; r++){
            for(int c = 0; c < input[r].length; c++) {
                int index = calculateIndex(input, r, c);
                output[r][c] = algorithm[index];
            }
        }
        if(everything.equals("#")) everything = algorithm[Integer.parseInt("111111111",2)];
        else everything = algorithm[Integer.parseInt("000000000",2)];
        return output;
    }

    public static int calculateIndex(String[][] input, int r, int c){
        StringBuilder index = new StringBuilder();
        for(int i = -1; i < 2; i++){
            for(int k = -1; k < 2; k++){
                try{
                    index.append(binary.get(input[r + i][c + k]));
                }
                catch (IndexOutOfBoundsException e){
                    index.append(binary.get(everything));
                }
            }
        }
        return Integer.parseInt(index.toString(),2);
    }

    public static int countLit(String[][] image){
        int count = 0;
        for (String[] strings : image) {
            for (String string : strings) {
                if (string.equals("#")) count++;
            }
        }
        return count;
    }
}
