package AOC2015;
import java.io.*;
import java.util.*;
import java.security.*;
public class Day4 {
    public static void main(String[] args) {
        System.out.println(MD5("inputs/input.txt",1));
        System.out.println(MD5("inputs/input.txt",2));
    }

    public static int MD5(String fileName, int part) {
        File f = new File(fileName);
        String input = "";
        try {
            Scanner s = new Scanner(f);
            input = s.nextLine();
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) return leadingZero(input,"00000");
        return leadingZero(input,"000000");
    }

    public static int leadingZero(String input,String startsWith) {
        int i = 0;
        while(true){
            String temp = input+i;
            MessageDigest md;
            try{
                md = MessageDigest.getInstance("MD5");
            }
            catch (NoSuchAlgorithmException e){
                System.out.println("no algorithm");
                return -1;
            }
            byte[] md5 = md.digest(temp.getBytes());
            HexFormat hexFormat = HexFormat.of();
            if(hexFormat.formatHex(md5).startsWith(startsWith)) return i;
            i++;
        }
    }

}
