package AOC2023;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
public class Day18 {
    public static void main(String[] args) {
        File f;
        try {
            f = new File("InputFile");
            Scanner s = new Scanner(f);
            List<String> commands = new ArrayList<>();
            List<String> coord = new ArrayList<>();
            List<String> calcArea = new ArrayList<>();
            while (s.hasNextLine()) {
                commands.add(s.nextLine());
            }
            coord.add("0");
            coord.add("0");
            coord.add("F");
            long surrounding =0;
            for(String items: commands)
            {
                String hex = items.substring(items.indexOf("#")+1);
                //String direction = items.substring(0,1);
                //int repeat = Integer.parseInt(items.substring(2,items.lastIndexOf(" ")));
                String direction = direction(hex);
                long repeat = convertHex(hex);
                surrounding+=repeat;
                move(direction,repeat,coord,calcArea);
            }
            calcArea.add("0,0");
            System.out.println(countTotal(calcArea,surrounding));
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
    }
    public static void move(String direction, long repeat, List<String> coord, List<String> calcArea)
    {
        //System.out.println(coord);
        long currentIndex = Long.parseLong(coord.get(0));
        long currentRow = Long.parseLong(coord.get(1));
        String prev  = coord.get(2);
        coord.clear();
        if(direction.equals("U"))
        {
            coord.add(Long.toString(currentIndex));
            coord.add(Long.toString(currentRow+repeat));
            coord.add(direction);

        }
        else if(direction.equals("D"))
        {
            coord.add(Long.toString(currentIndex));
            coord.add(Long.toString(currentRow-repeat));
            coord.add(direction);
        }
        else if(direction.equals("R"))
        {
            coord.add(Long.toString(currentIndex+repeat));
            coord.add(Long.toString(currentRow));
            coord.add(direction);
        }
        else{
            coord.add(Long.toString(currentIndex-repeat));
            coord.add(Long.toString(currentRow));
            coord.add(direction);
        }
        if(!prev.equals(direction)){
            calcArea.add(currentIndex +","+currentRow);
        }
    }

    public static long countTotal(List<String> calcArea,long outside)
    {
        long total;
        long total1 =0;
        long total2=0;
        for(int i =0;i<calcArea.size()-1;i++)
        {
            String line = calcArea.get(i);
            String line2 = calcArea.get(i+1);
            long x1 =  Integer.parseInt(line.substring(0, line.indexOf(",")));
            long y1 = Integer.parseInt(line.substring(line.indexOf(",")+1));
            long x2 = Integer.parseInt(line2.substring(0,line2.indexOf(",")));
            long y2 = Integer.parseInt(line2.substring(line2.indexOf(",")+1));
            total1 += x1*y2;
            total2 += y1*x2;
        }
        total = (Math.abs(total1-total2))/2;
        total = total - ((long)outside/2)+1 + outside;
        return total;

    }

    public static int convertHex(String line)
    {
        line = line.substring(0,line.length()-2);
        return Integer.parseInt(line,16);
    }

    public static String direction(String line)
    {
        String direction ="";
        String num = line.substring(line.indexOf(")")-1,line.indexOf(")"));
        if(num.equals("0"))
        {
            direction = "R";
        }
        else if(num.equals("1"))
        {
            direction ="D";
        }
        else if(num.equals("2"))
        {
            direction = "L";
        }
        else if(num.equals("3"))
        {
            direction ="U";
        }
        return direction;
    }



}
