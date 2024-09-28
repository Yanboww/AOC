package AOC2022;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Arrays;
public class Day5 {
    public static void main(String[] args) {
        System.out.println(findTop9000("inputs/AOC2022Inputs/input5"));
        System.out.println(findTop9001("inputs/AOC2022Inputs/input5"));
    }

    public static String findTop9000(String fileName)
    {
        String[][] crates  = new String[64][9];
        String[] crateColumn = new String[8];
        int index = 0;
        try{
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            boolean start = false;
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                if(!line.contains("[") && !start) {
                    s.nextLine();
                    start = true;
                    index = 0;
                    for(int i = crateColumn.length-1; i>=0; i--)
                    {
                        String[] column = crateColumn[index].split(" ");
                        index++;
                        for(int c = 0; c < column.length; c++)
                        {
                            String value = column[c].substring(1,2);
                            if(!value.equals("0"))
                            {
                                crates[i][c] = value;
                            }
                        }

                    }
                }
                else if(!start)
                {
                    crateColumn[index] = line;
                    index++;
                }
                else{
                    int numMoved = Integer.parseInt(line.substring(line.indexOf("move")+5,line.indexOf("from")-1));
                    int from = Integer.parseInt(line.substring(line.indexOf("from")+5,line.indexOf("to")-1))-1;
                    int to = Integer.parseInt(line.substring(line.indexOf("to")+3))-1;
                    int topCrateIndex = findTopCrate(from,crates);
                    int topAvailable = topRowAvailable(to, crates);
                    for(int i = topCrateIndex; i > topCrateIndex-numMoved; i--)
                    {
                        crates[topAvailable][to] = crates[i][from];
                        crates[i][from]= null;
                        topAvailable++;
                    }
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        return findTopHelper(crates);
    }

    public static String findTop9001(String fileName)
    {
        String[][] crates  = new String[64][9];
        String[] crateColumn = new String[8];
        int index = 0;
        try{
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            boolean start = false;
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                if(!line.contains("[") && !start) {
                    s.nextLine();
                    start = true;
                    index = 0;
                    for(int i = crateColumn.length-1; i>=0; i--)
                    {
                        String[] column = crateColumn[index].split(" ");
                        index++;
                        for(int c = 0; c < column.length; c++)
                        {
                            String value = column[c].substring(1,2);
                            if(!value.equals("0"))
                            {
                                crates[i][c] = value;
                            }
                        }

                    }
                }
                else if(!start)
                {
                    crateColumn[index] = line;
                    index++;
                }
                else{
                    int numMoved = Integer.parseInt(line.substring(line.indexOf("move")+5,line.indexOf("from")-1));
                    int from = Integer.parseInt(line.substring(line.indexOf("from")+5,line.indexOf("to")-1))-1;
                    int to = Integer.parseInt(line.substring(line.indexOf("to")+3))-1;
                    int topCrateIndex2 = findTopCrate2(from,crates,numMoved);
                    int topCrateIndex = findTopCrate(from,crates);
                    int topAvailable = topRowAvailable(to, crates);
                    for(int i = topCrateIndex2; i <=topCrateIndex; i++)
                    {
                        crates[topAvailable][to] = crates[i][from];
                        crates[i][from]= null;
                        topAvailable++;
                    }
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        return findTopHelper(crates);
    }

    private static String findTopHelper(String[][] crates)
    {
        String top = "";
        for(int c = 0; c < crates[0].length; c++)
        {
            for(int r = crates.length-1; r >=0; r-- )
            {
                String crate = crates[r][c];
                if(crate!=null){
                    top+=crate;
                    break;
                }
            }
        }
        return top;
    }

    private static int topRowAvailable(int column, String[][] crates)
    {
        for(int r = 0; r < crates.length; r++)
        {
            if(crates[r][column] == null) return r;
        }
        return -1;
    }

    private static int findTopCrate(int column, String[][] crates)
    {
        for(int r = crates.length-1; r>=0; r--)
        {
            if(crates[r][column]!=null) return r;
        }
        return -1;
    }

    private static int findTopCrate2(int column, String[][] crates, int numMoved)
    {
        return findTopCrate(column,crates) - numMoved+1;
    }





}
