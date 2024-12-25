package AOC2024;
import java.io.*;
import java.util.*;
public class Day24 {
    public static void main(String[] args) {
        System.out.println(calcBitNum("inputs/input.txt"));
        //System.out.println(calcBitNum("inputs/input.txt"));
        //System.out.println(calcBitNumP2("inputs/trial"));
    }

    public static Long calcBitNum(String fileName)
    {
        File f = new File(fileName);
        HashMap<String,Boolean> vars = new HashMap<>();
        ArrayList<String> operations = new ArrayList<>();
        int latest = 0;
        try{
            Scanner s = new Scanner(f);
            boolean operation = false;
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                if(line.contains("z"))
                {
                    int tempZ = Integer.parseInt(line.substring(line.indexOf("z")+1));
                    if(tempZ>latest) latest = tempZ;
                }
                if(line.isEmpty()) operation = true;
                else if(!operation){
                    String[] temp = line.split("\\W+");
                    boolean isTrue = temp[1].equals("1");
                    vars.put(temp[0],isTrue);
                }
                else{
                    operations.add(line);
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        doOperations(operations,vars);
        return bitVal(vars, latest);
    }

    public static String calcBitNumP2(String fileName)
    {
        File f = new File(fileName);
        HashMap<String,Boolean> vars = new HashMap<>();
        ArrayList<String> operations = new ArrayList<>();
        int latest = 0;
        String x = "";
        String y = "";
        try{
            Scanner s = new Scanner(f);
            boolean operation = false;
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                if(line.contains("z"))
                {
                    int tempZ = Integer.parseInt(line.substring(line.indexOf("z")+1));
                    if(tempZ>latest) latest = tempZ;
                }
                if(line.isEmpty()) operation = true;
                else if(!operation){
                    String[] temp = line.split("\\W+");
                    boolean isTrue = temp[1].equals("1");
                    vars.put(temp[0],isTrue);
                    if(line.contains("x")) x = temp[1]+x;
                    else y = temp[1]+y;
                }
                else{
                    operations.add(line);
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        long sum = Long.parseLong(y,2) & Long.parseLong(x,2);
        System.out.println(sum);
        System.out.println(Long.toBinaryString(sum));
        doOperations(operations,vars);
        bitVal(vars, latest);
        return "nope";
    }

    public static void doOperations(ArrayList<String> operations, HashMap<String,Boolean> vars)
    {
        while(!operations.isEmpty())
        {
            String operation = operations.get(0);
            operations.remove(0);
            String[] temp = operation.split("\\W+");
            if(vars.containsKey(temp[0]) && vars.containsKey(temp[2]))
            {
                if(temp[1].equals("AND")) vars.put(temp[3],vars.get(temp[0]) && vars.get(temp[2]));
                else if(temp[1].equals("OR")) vars.put(temp[3], vars.get(temp[0]) || vars.get(temp[2]));
                else vars.put(temp[3],vars.get(temp[0]) != vars.get(temp[2]));
            }
            else operations.add(operation);
        }
    }

    public static Long bitVal(HashMap<String,Boolean> vars, int latestZ)
    {
        String bits = "";
        for(int i =latestZ; i >=0; i--)
        {
            String temp = Integer.toString(i);
            if(temp.length() == 1) temp = "0"+temp;
            if(vars.containsKey("z"+temp)){
                if(vars.get("z"+temp)) bits+="1";
                else bits+="0";
            }
        }
        //System.out.println(bits);
        return Long.parseLong(bits,2);
    }
}
