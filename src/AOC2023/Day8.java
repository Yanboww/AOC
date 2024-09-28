package AOC2023;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.text.DecimalFormat;
public class Day8 {
    public static void main(String[] args) {
        File f;
        try{
            f = new File("InputFile");
            Scanner s = new Scanner (f);
            List <Integer> direction = new ArrayList<>();
            List<String> option = new ArrayList<>();
            List<Integer> stepList= new ArrayList<>();
            List<String> start = new ArrayList<>();
            List<String> last = new ArrayList<>();
            int steps=0;
            String rl = s.nextLine();
            for(int a = 0;a<rl.length();a++)
            {
                String letter = rl.substring(a,a+1);
                if(letter.equals("R"))
                {
                    direction.add(2);
                }
                else{
                    direction.add(1);
                }
            }
            while(s.hasNextLine())
            {
                option.add(s.nextLine());
            }
            String next ="";
            //Code for part 1
            /*int current = findRoute(option,"AAA");
            while(!next.equals("ZZZ"))
            {
                int direct = direction.get(0);
                direction.remove(0);
                direction.add(direct);
                next = nextLocation(option, current,direct);
                current = findRoute(option,next);
                steps+=1;
            }
            System.out.println(steps);**/

            //part2
            findLastChar(start,"A",option);
            findLastChar(last,"Z",option);
            for(String item: start)
            {
                int step2 =0;
                int current = option.indexOf(item);
                while(!last.contains(option.get(findRoute(option,next))))
                {
                    int direct = direction.get(0);
                    direction.remove(0);
                    direction.add(direct);
                    next = nextLocation(option, current,direct);
                    current = findRoute(option,next);
                    step2+=1;
                }
                stepList.add(step2);
                next="";
            }
            //System.out.println(stepList);
            int n = stepList.size();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = stepList.get(i);
            }
            DecimalFormat df = new DecimalFormat("#");
            df.setMaximumFractionDigits(40);
            System.out.println(df.format(findLCM(arr)));

        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found");
        }
    }
    public static String nextLocation(List<String> option, int current, int direction)
    {
        String next;
        String choice = option.get(current);
        if(direction == 2)
        {
            int index = choice.indexOf(",")+2;
            next = choice.substring(index,choice.indexOf(")"));
        }
        else{
            int index = choice.indexOf("(")+1;
            next = choice.substring(index, choice.indexOf(","));
        }
        return next;
    }

    public static int findRoute(List<String> option, String word)
    {
        for (String item : option) {
            if (item.contains(word+" =") ){
                return option.indexOf(item);
            }
        }
        return -1;

    }

    public static void findLastChar(List<String> nodes,String word, List<String> options)
    {
        for(String item : options)
        {
            String letter = item.substring(2,3);
            if(letter.equals(word))
            {
                nodes.add(item);
            }
        }
    }

    public static double findLCM(int[] arr) {
        double lcm = arr[0];
        for (int i = 1; i < arr.length; i++) {
            double currentNumber = arr[i];
            lcm = (lcm * currentNumber) / gcd(lcm, currentNumber);
        }
        return lcm;
    }
    public static double gcd(double a, double b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }




}
