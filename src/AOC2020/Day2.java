package AOC2020;
import java.io.*;
import java.util.*;
import java.util.regex.*;
public class Day2 {
    public static void main(String[] args)
    {
        System.out.println(findValidPass("inputs/input.txt",1));
        System.out.println(findValidPass("inputs/input.txt",2));
    }

    public static int findValidPass(String fileName, int part)
    {
        File f = new File(fileName);
        ArrayList<String[]> rules = new ArrayList<>();
        ArrayList<String> password = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                String[] temp = line.split("\\s+");
                String[] tempRule = new String[3];
                String[] range = temp[0].split("-");
                tempRule[0] = range[0];
                tempRule[1] = range[1];
                tempRule[2] = temp[1].substring(0,temp[1].indexOf(":"));
                rules.add(tempRule);
                password.add(temp[2]);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        if(part == 1) return testPass(rules,password);
        return testPassIndex(rules,password);
    }

    public static int testPass(ArrayList<String[]> rules, ArrayList<String> password)
    {
        int count = 0;
        for(int i = 0; i < rules.size(); i++)
        {
            String[] rule = rules.get(i);
            String pass = password.get(i);
            String key = rule[2];
            StringBuilder result = new StringBuilder();
            Pattern p = Pattern.compile(key);
            Matcher m = p.matcher(pass);
            while(m.find()){
                result.append(m.group());
            }
            if(result.toString().matches("^"+key+"{"+rule[0]+","+ rule[1] +"}$")) count++;

        }
        return count;
    }

    public static int testPassIndex(ArrayList<String[]> rules, ArrayList<String> passwords)
    {
        int count = 0;
        for(int i = 0; i < passwords.size(); i++)
        {
            String[] rule = rules.get(i);
            String pass = passwords.get(i);
            int index1 = Integer.parseInt(rule[0])-1;
            int index2 = Integer.parseInt(rule[1])-1;
            boolean valid = pass.charAt(index1) == rule[2].charAt(0);
            if(pass.charAt(index2) == rule[2].charAt(0)) valid = !valid;
            if(valid) count++;
        }
        return count;
    }
}
