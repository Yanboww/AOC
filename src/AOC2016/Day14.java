package AOC2016;
import java.math.BigInteger;
import java.util.*;
import java.security.*;
import java.util.regex.*;
public class Day14 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(findLastKey("ihaygndm",1));
        System.out.println(findLastKey("ihaygndm",2));
    }

    public static int findLastKey(String input, int part) throws NoSuchAlgorithmException{
        HashMap<String,String> dict = new HashMap<>();
        int index = -1;
        int keys = 0;
        while(keys < 64){
            index++;
            String currentInput = input+index;
            String hash;
            if(dict.containsKey(currentInput)) hash = dict.get(currentInput);
            else{
               hash = MD5(currentInput,part);
            }
            if(isKey(hash, dict, input, index, part)) keys++;
        }
        return index;
    }

    public static boolean isKey(String hash, HashMap<String,String> dict, String input, int index, int part) throws NoSuchAlgorithmException {
        if(hash.matches("^.*(.)(\\1){2}.*+$")){
            Pattern p = Pattern.compile("(.)(\\1){2}");
            Matcher match = p.matcher(hash);
            String result = "";
            if(match.find()) result = match.group().substring(0,1);
            for(int i = 0; i < 1000; i++){
                String current = input+(index+i+1);
                if(dict.containsKey(current)) hash = dict.get(current);
                else{
                    hash = MD5(current,part);
                    dict.put(current,hash);
                }
                if(hash.matches(".*("+ Pattern.quote(result)+"){5}.*")) return true;
            }
        }
        return false;
    }

    public static String MD5(String input, int part) throws NoSuchAlgorithmException{
        int additional = (part == 1) ? 0 : 2016;
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(input.getBytes());
        BigInteger converter = new BigInteger(1,m.digest());
        StringBuilder hash = new StringBuilder(converter.toString(16).toLowerCase());
        for(int i = 0; i < additional; i++){
            while(hash.length() < 32) hash.insert(0, "0");
            m.update(hash.toString().getBytes());
            converter = new BigInteger(1,m.digest());
            hash = new StringBuilder(converter.toString(16).toLowerCase());
        }
        return hash.toString();
    }
}
