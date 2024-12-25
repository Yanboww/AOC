package AOC2024;
import java.io.*;
import java.util.*;
public class Day25 {
    public static void main(String[] args)
    {
        System.out.println(findKeyLockCombo("inputs/input.txt"));
    }

    public static int findKeyLockCombo(String fileName)
    {
        File f  = new File(fileName);
        ArrayList<String> keys = new ArrayList<>();
        ArrayList<String> locks = new ArrayList<>();
        int length = 0;
        try{
            Scanner s = new Scanner(f);
            String[][] temp = new String[7][5];
            int index = 0;
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                if(!line.isEmpty()){
                    for(int i = 0; i < line.length(); i++) temp[index][i] = line.substring(i,i+1);
                    index++;
                }
                else{
                    if(Arrays.asList(temp[0]).contains("#")) locks.add(findLockPins(temp));
                    else keys.add(findKeyPins(temp));
                    temp = new String[7][5];
                    index = 0;
                }
            }
            if(Arrays.asList(temp[0]).contains("#")) locks.add(findLockPins(temp));
            else keys.add(findKeyPins(temp));
            length = temp.length;
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        return uniqueCombo(keys,locks, length);
    }

    public static String findLockPins(String[][] map)
    {
        StringBuilder pins = new StringBuilder();
        for(int c = 0; c < map[0].length; c++)
        {
            for(int r = 0; r < map.length; r++)
            {
                if(map[r][c].equals(".")){
                    pins.append((r - 1)).append(",");
                    break;
                }
            }
        }
        return pins.substring(0,pins.length()-1);
    }

    public static String findKeyPins(String[][] map)
    {
        StringBuilder pins = new StringBuilder();
        for(int c = 0; c < map[0].length; c++)
        {
            for(int r = map.length-1; r >= 0; r--)
            {
                if(map[r][c].equals(".")){
                    pins.append((map.length - r - 2)).append(",");
                    break;
                }
            }
        }
        return pins.substring(0,pins.length()-1);
    }

    public static int uniqueCombo(ArrayList<String> keys, ArrayList<String> locks, int length)
    {
        int count = 0;
        for(String lock : locks)
        {
            for(String key : keys)
            {
                if(!compare(lock,key, length)) count++;
            }
        }
        return  count;
    }

    public static boolean compare(String lock, String key, int length)
    {
        String[] keyArr = key.split(",");
        String[] lockArr = lock.split(",");
        for(int i = 0; i < keyArr.length; i++){
            if(Integer.parseInt(keyArr[i])+1 + Integer.parseInt(lockArr[i])+1 > length){
                return true;
            }
        }
        return false;
    }
}
