package AOC2020;
import java.io.*;
import java.util.*;
import java.util.regex.*;
public class Day18 {
    public static void main(String[] args) {
        System.out.println(evaluateHomeWorK("inputs/input.txt",1));
        System.out.println(evaluateHomeWorK("inputs/input.txt",2));
    }

    public static long evaluateHomeWorK(String fileName, int part){
        File f = new File(fileName);
        ArrayList<String> expressions = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            while(s.hasNextLine()) expressions.add(s.nextLine());
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        long sum = 0;
        for (String expression : expressions) sum += evaluateExpression(expression, part);
        return sum;
    }

   public static long evaluateExpression(String expression, int part){
        while(expression.contains("(")) expression = simplifiedExpression(expression, part);
        return solve(expression, part);
   }

   public static String simplifiedExpression(String expression, int part){
        Pattern p = Pattern.compile("(?<=\\()[^()]*(?=\\))");
        Matcher match = p.matcher(expression);
        while(match.find()){
            String group = match.group();
            String solution = Long.toString(solve(group,part));
            expression = expression.replace("("+group+")",solution);
        }
        return expression;
   }

   public static long solve(String expression, int part){
       String[] expressionArr = expression.split(" ");
        if(part == 1){
            long start = Long.parseLong(expressionArr[0]);
            for(int i = 1; i < expressionArr.length; i+=2){
                String op = expressionArr[i];
                long val = Long.parseLong(expressionArr[i+1]);
                if(op.equals("+")) start+=val;
                else start*=val;
            }
            return start;
        }
        return solveOrdered(expression);
   }

   public static long solveOrdered(String expression){
        Pattern p = Pattern.compile("\\d+ \\+ \\d+");
        while(expression.contains("+")){
            Matcher match = p.matcher(expression);
            if(match.find()){
                String group = match.group();
                String solve = Long.toString(solve(group,1));
                StringBuilder temp = new StringBuilder(expression);
                temp.replace(match.start(),match.end(),solve);
                expression = temp.toString();
            }
        }
        return solve(expression,1);
   }

}
