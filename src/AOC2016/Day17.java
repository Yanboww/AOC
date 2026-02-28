package AOC2016;
import java.math.BigInteger;
import java.security.*;
import java.util.*;
public class Day17 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(vaultPath("awrkjxxr",1));
        System.out.println(vaultPath("awrkjxxr",2));
    }

    public static String vaultPath(String password, int part) throws NoSuchAlgorithmException {
        if(part == 1) return findPath(part,password,"",0,0,0,new HashMap<>()).split(" ")[0];
        return findPath(part,password,"",0,0,0,new HashMap<>()).split(" ")[1];
    }

    public static String findPath(int part,String password, String path, int r, int c,int steps, HashMap<String,String> dict) throws NoSuchAlgorithmException {
        if(dict.containsKey(r+","+c)){
            String[] storage = dict.get(r+","+c).split(" ");
            if(Integer.parseInt(storage[1]) > steps && part == 1){
                dict.put(r+","+c,path+" "+steps);
            }
            else if(Integer.parseInt(storage[1]) < steps && part == 2){
                dict.put(r+","+c,path+" "+steps);
            }
        }
        else dict.put(r+","+c,path+" "+steps);
        if(r == 3 && c == 3) return path;
        String hash = MD5(password+path).substring(0,4);
        if(hash.substring(0,1).matches("[bcdef]") && r != 0) findPath(part,password,path+"U",r-1,c,steps+1,dict);
        if(hash.substring(1,2).matches("[bcdef]") && r != 3) findPath(part,password, path+"D",r+1,c,steps+1,dict);
        if(hash.substring(2,3).matches("[bcdef]") && c != 0) findPath(part,password, path+"L",r,c-1,steps+1,dict);
        if(hash.substring(3,4).matches("[bcdef]") && c != 3) findPath(part,password, path+"R",r,c+1,steps+1,dict);
        return dict.get("3,3");
    }

    public static String MD5(String input) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(input.getBytes());
        BigInteger converter = new BigInteger(1,m.digest());
        StringBuilder md5 = new StringBuilder(converter.toString(16).toLowerCase());
        while(md5.length() < 32){
            md5.insert(0,"0");
        }
        return md5.toString();
    }


}
