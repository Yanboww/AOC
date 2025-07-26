package AOC2021;
import java.io.*;
import java.util.*;
import java.util.regex.*;
public class Day18 {
    public static void main(String[] args){
        System.out.println(snailFish("inputs/input.txt",1));
        System.out.println(snailFish("inputs/input.txt",2));
    }

    public static long snailFish(String fileName, int part){
        File f = new File(fileName);
        ArrayList<String> snailNumbers = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            while(s.hasNextLine())  snailNumbers.add(s.nextLine());
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) return addAll(snailNumbers);
        return addTwo(snailNumbers);
    }

    public static long addAll(ArrayList<String> snailNumbers){
        StringBuilder current = new StringBuilder(snailNumbers.get(0));
        for(int i = 1; i < snailNumbers.size(); i++){
            String temp = current.toString();
            current.delete(0,current.length());
            current.append("[").append(temp).append(",").append(snailNumbers.get(i)).append("]");
            boolean reduce = true;
            while(reduce){
                reduce = explode(current);
                if(reduce){
                    continue;
                }
                reduce = split(current);
            }
        }
        return magnitude(current);
    }

    public static long addTwo(ArrayList<String> snailNumbers){
        long max = 0;
        for(String snailNumber1 : snailNumbers){
            for(String snailNumber2 : snailNumbers){
                if(snailNumber1.equals(snailNumber2)) continue;
                ArrayList<String> temp = new ArrayList<>();
                temp.add(snailNumber1);temp.add(snailNumber2);
                long magnitude = addAll(temp);
                if(magnitude > max) max = magnitude;
            }
        }
        return max;
    }

    public static boolean explode (StringBuilder string){
        int depth = 0;
        int index = 0;
        for(int i = 0; i < string.length(); i++){
            if(string.charAt(i) == '[') depth++;
            else if(string.charAt(i) == ']') depth--;
            if(depth == 5){
                index = i;
                break;
            }
        }
        if(depth != 5) return false;
        String left = reverse(string.substring(0,index));
        String right = string.substring(index);
        int stopIndex = right.indexOf("]");
        String[] pair = right.substring(1,stopIndex).split(",");
        right = right.substring(stopIndex+1);
        Pattern p = Pattern.compile("\\d+");
        Matcher matchLeft = p.matcher(left);
        if(matchLeft.find()){
            int num  = Integer.parseInt(reverse(matchLeft.group())) + Integer.parseInt(pair[0]);
            String reversedNum = reverse(Integer.toString(num));
            left = left.substring(0,matchLeft.start())+reversedNum+left.substring(matchLeft.end());
        }
        Matcher matchRight = p.matcher(right);
        if(matchRight.find()){
            int num = Integer.parseInt(matchRight.group()) + Integer.parseInt(pair[1]);
            right = right.substring(0,matchRight.start())+num+right.substring(matchRight.end());
        }
        string.delete(0,string.length());
        string.append(reverse(left)).append(0).append(right);
        return true;
    }

    public static boolean split(StringBuilder string){
        Pattern p = Pattern.compile("\\d+");
        Matcher matcher = p.matcher(string);
        int start = 0;
        int end = 0;
        int num = -1;
        while(matcher.find()){
            int current = Integer.parseInt(matcher.group());
            if(current >= 10){
                start = matcher.start();
                end = matcher.end();
                num = current;
                break;
            }
        }
        if(num == -1) return false;
        int left = num/2;
        int right = num-left;
        String pair = "["+left+","+right+"]";
        String leftString = string.substring(0,start);
        String rightString = string.substring(end);
        string.delete(0,string.length());
        string.append(leftString).append(pair).append(rightString);
        return true;
    }

    public static long magnitude(StringBuilder string){
        while(!isInt(string.toString())){
            calculatePair(string);
        }
        return Integer.parseInt(string.toString());
    }

    public static boolean isInt(String string){
        try{
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    public static void calculatePair(StringBuilder string){
        Pattern p = Pattern.compile("\\[\\d+,\\d+]");
        Matcher matcher = p.matcher(string);
        if(matcher.find()){
            String[] pair = matcher.group().split(",");
            long left = Integer.parseInt(pair[0].substring(1));
            long right = Integer.parseInt(pair[1].substring(0,pair[1].length()-1));
            long total = 3*left + 2*right;
            String leftString = string.substring(0,matcher.start());
            String rigthString = string.substring(matcher.end());
            string.delete(0,string.length());
            string.append(leftString).append(total).append(rigthString);
        }
    }

    public static String reverse(String line){
        StringBuilder newLine = new StringBuilder();
        for(int i = line.length()-1; i >= 0; i--) newLine.append(line.charAt(i));
        return newLine.toString();
    }
}

