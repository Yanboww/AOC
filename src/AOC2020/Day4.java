package AOC2020;
import java.io.*;
import java.util.*;
public class Day4 {
    public static void main(String[] args){
        System.out.println(findValidPassport("inputs/input.txt", 1));
        System.out.println(findValidPassport("inputs/input.txt", 2));
    }

    public static int findValidPassport(String fileName, int part){
        File f = new File(fileName);
        ArrayList<String> passports = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            StringBuilder passport = new StringBuilder();
            while(s.hasNextLine()){
                String temp = s.nextLine();
                if(temp.isEmpty()){
                    passports.add(passport.toString());
                    passport = new StringBuilder();
                }
                else{
                    passport.append(" ").append(temp);
                }
            }
            passports.add(passport.toString());
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) return checkPass(passports);
        return checkPassStrict(passports);
    }

    public static int checkPass(ArrayList<String> passports){
        int count = 0;
        for(String passport: passports){
            if(!test("byr", passport)) continue;
            if(!test("iyr", passport)) continue;
            if(!test("eyr", passport)) continue;
            if(!test("hgt", passport)) continue;
            if(!test("hcl", passport)) continue;
            if(!test("ecl", passport)) continue;
            if(!test("pid", passport)) continue;
            count++;
        }
        return count;
    }

    public static int checkPassStrict(ArrayList<String> passports){
        int count = 0;
        for(String passport: passports){
            String[] pass = passport.split(" ");
            if(!test("byr", pass, new int[]{1920,2002})) continue;
            if(!test("iyr", pass, new int[]{2010,2020})) continue;
            if(!test("eyr", pass, new int[]{2020,2030})) continue;
            if(!test("pid", pass, "s")) continue;
            if(!test("hcl", pass, "s")) continue;
            if(!test("ecl", pass, "s")) continue;
            if(!test("hgt", pass, "s")) continue;
            count++;
        }
        return count;
    }
    public static boolean test(String regex, String word){
        return word.contains(regex);
    }

    public static boolean test(String regex, String[] word, int[] range){
        for (String s : word) {
            if (s.contains(regex)) {
                int value = Integer.parseInt(s.substring(s.indexOf(":") + 1));
                return value >= range[0] && value <= range[1];
            }
        }
        return false;
    }

    public static boolean test(String regex, String[] word, String wtv){
        String lookRegex = "";
        int val = findIndex(regex,word);
        if(val != -1){
            if(regex.equals("pid")) lookRegex = "\\d{9}";
            else if(regex.equals("ecl")) lookRegex = "(amb|blu|brn|gry|grn|hzl|oth)";
            else if(regex.equals("hcl")) lookRegex ="#([a-f0-9]){6}";
            else if(regex.equals("hgt")){
                String next = word[val];
                next = next.substring(next.indexOf(":")+1);
                if(next.contains("cm")){
                    int height = Integer.parseInt(next.substring(0,next.indexOf("cm")));
                    return height >= 150 && height <= 193;
                }
                else if(next.contains("in")){
                    int height = Integer.parseInt(next.substring(0,next.indexOf("in")));
                    return height >= 59 && height <= 76;
                }
            }
            return word[val].substring(word[val].indexOf(":")+1).matches(lookRegex);
        }
        return false;
    }

    public static int findIndex(String search, String[] arr){
        for(int i = 0; i < arr.length; i++){
            if(arr[i].contains(search)) return i;
        }
        return -1;
    }
}
