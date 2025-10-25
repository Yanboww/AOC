package AOC2015;
import java.util.*;
import java.util.regex.*;
import java.io.*;
import org.json.*;
public class Day12 {
    public static void main(String[] args) {
        System.out.println(parse("inputs/input.txt",1));
        System.out.println(parse("inputs/input.txt",2));
    }

    public static int parse(String fileName, int part) {
        String input = "";
        try {
            Scanner s = new Scanner(new File(fileName));
            input = s.nextLine();
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        if (part == 1) return sumNum(input);
        return removeRed(input);
    }

    public static int sumNum(String input) {
        Pattern p = Pattern.compile("-*\\d+");
        Matcher m = p.matcher(input);
        int sum = 0;
        while (m.find()) {
            sum += Integer.parseInt(m.group());
        }
        return sum;
    }

    public static int removeRed(String input){
        try {
            if(input.startsWith("{")){
                JSONObject obj = new JSONObject(input);
                return sumNum(obj);
            }
            else{
                JSONArray obj = new JSONArray(input);
                return sumNum(obj);
            }
        } catch (JSONException e) {
            System.out.println("failed");
        }
        return 0;
    }

    public static int sumNum(Object obj){
        if (obj instanceof Integer) return (int) obj;
        else if (obj instanceof  String) return 0;
        int sum = 0;
        if(obj instanceof JSONArray){
            for(int i = 0; i < ((JSONArray) obj).length(); i++){
                sum+=sumNum(((JSONArray) obj).get(i));
            }
        }
        else if(obj instanceof JSONObject){
            JSONArray names = ((JSONObject) obj).names();
            for(int i = 0; i < names.length(); i++){
                String  name = (String) names.get(i);
                if(((JSONObject) obj).get(name).equals("red")) return 0;
                sum+=sumNum(((JSONObject) obj).get(name));
            }
        }
        return sum;
    }


}
