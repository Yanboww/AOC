package AOC2024;
import java.io.*;
import java.util.*;
public class Day13 {
    public static void main(String[] args) {
        System.out.println(findTokenForPrize("inputs/input.txt",1));
        System.out.println(findTokenForPrize("inputs/input.txt",2));
    }

    public static Long findTokenForPrize(String fileName, int part)
    {
        File f = new File(fileName);
        long cost = 0L;
        ArrayList<String[]> inputs = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            String[] temp = new String[3];
            int index = 0;
            while (s.hasNextLine()){
                String line = s.nextLine();
                if(line.isEmpty()){
                    inputs.add(temp);
                    temp = new String[3];
                    index = 0;
                }
                else{
                    temp[index] = line;
                    index++;
                }
            }
            inputs.add(temp);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        for(String[] input : inputs)
        {
            if(part == 1) cost+= calcTotal(input, part);
            else cost+=linearAlg(input,part);
        }
        return cost;
    }

    public static long linearAlg(String[] input, int part)
    {
        Long[] a = parse(input[0],"b",part);
        Long[] b = parse(input[1],"b",part);
        Long[] end = parse(input[2], "i", part);
        long numOfB = (a[1]*end[0]-a[0]*end[1])/(a[1]*b[0]-a[0]*b[1]);
        long numOfA = (end[0] - numOfB*b[0])/a[0];
        if(numOfA*a[0] + numOfB*b[0] == end[0] && numOfA*a[1] + numOfB*b[1] == end[1]) return 3*numOfA+numOfB;
        return 0;
    }

    public static long calcTotal(String[] input, int part)
    {
        Long[] a = parse(input[0],"b",part);
        Long[] b = parse(input[1],"b",part);
        Long[] end = parse(input[2], "i", part);
        long cost = 0;
        long x,y;
        for(long aCount = 0; aCount <=100; aCount++)
        {
            x = aCount*a[0];
            y = aCount*a[1];
            long calc = (end[0]-x)%b[0];
            if(calc == 0)
            {
                long bCount = ((end[0]-x)/b[0]);
                if(bCount*b[1]+y == end[1]) {
                    cost = (aCount*3 + bCount);
                    break;
                }
            }
        }
        return cost;
    }


    public static Long[] parse(String line, String type, int part)
    {
        long x, y;
        if(type.equals("b"))
        {
            x = Long.parseLong(line.substring(line.indexOf("+")+1,line.indexOf(",")));
            y = Long.parseLong(line.substring(line.indexOf(",")+4));
        }
        else{
            long add = 0;
            if(part == 2) add = 10000000000000L;
            x = Long.parseLong(line.substring(line.indexOf("=")+1, line.indexOf(","))) + add;
            y = Long.parseLong(line.substring(line.lastIndexOf("=")+1)) + add;
        }
        return  new Long[]{x,y};
    }
}
