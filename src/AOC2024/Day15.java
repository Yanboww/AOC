package AOC2024;
import java.io.*;
import java.util.*;
public class Day15 {

    public static boolean can;
    public static void main(String[] args) {
       System.out.println(gps("inputs/input.txt",1));
       System.out.println(gps("inputs/input.txt",2));
    }

    public static int gps(String fileName, int part)
    {
        File f = new File(fileName);
        int length = 50;
        if(fileName.contains("trial")) length = 10;
        String[][] map = new String[length][];
        ArrayList<String> instructions = new ArrayList<>();
        int[] botPos = new int[2];
        try{
            Scanner s = new Scanner(f);
            boolean isMap = true;
            int index = 0;
            while(s.hasNextLine()){
                String line = s.nextLine();
                if(line.isEmpty()) isMap = false;
                else if(isMap)
                {
                    String[] temp = new String[line.length()];
                    for(int i = 0; i < line.length(); i++){
                        String tempBlock = line.substring(i,i+1);
                        temp[i] = tempBlock;
                        if(tempBlock.equals("@")){
                            botPos[0] = index;
                            botPos[1] = i;
                        }
                    }
                    map[index] = temp;
                    index++;
                }
                else{
                    for(int i = 0; i < line.length(); i++) instructions.add(line.substring(i,i+1));
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        if(part == 2){
            map = alterMap(map);
            botPos = findBotPos(map);
        }
        int count = 1;
        for(String instruction : instructions)
        {
            moveBot(map,botPos,instruction, part);
            //uncomment for visualization
            /*System.out.println(count + " " + instruction);
            for(String[] row : map)
            {
                System.out.println(Arrays.toString(row));
            }
            System.out.println();**/
            count++;
        }
        return calcGPS(map, part);
    }

    public static int[]findBotPos(String[][] map)
    {
        int[] botPos = new int[2];
        for(int r = 0; r < map.length; r++)
        {
            for(int c = 0; c < map[r].length; c++)
            {
                if(map[r][c].equals("@")){
                    botPos[0] = r;
                    botPos[1] = c;
                }
            }
        }
        return botPos;
    }

    public static String[][] alterMap(String[][] map)
    {
        String[][] map2 = new String[map.length][map[0].length*2];
        int index;
        for(int r = 0; r < map.length; r++)
        {
            index = 0;
            for(int c = 0; c < map[r].length; c++){
                String current = map[r][c];
                if(current.equals("O")){
                    map2[r][index] = "[";
                    index++;
                    map2[r][index]= "]";
                }
                else if (current.equals("@"))
                {
                    map2[r][index] = "@";
                    index++;
                    map2[r][index]= ".";
                }
                else {
                    map2[r][index] = current;
                    index++;
                    map2[r][index]= current;
                }
                index++;
            }
        }
        return map2;
    }

    public static void moveBot(String[][] map, int[] botPos, String instruction, int part)
    {
        if(instruction.equals(">"))
        {
            if(part== 1)moveRight(map,botPos);
            else moveRight2(map,botPos);
        }
        else if(instruction.equals("<")){
            if(part== 1)moveLeft(map,botPos);
            else moveLeft2(map,botPos);
        }
        else if(instruction.equals("^"))
        {
            if(part== 1)moveUp(map,botPos);
            else{
                can = true;
                String[][] tempMap = new String[map.length][map[0].length];
                for(int r = 0; r < map.length; r++){
                    System.arraycopy(map[r],0,tempMap[r],0,map[r].length);
                }
                moveUp2(tempMap,botPos[0],botPos[1],botPos,1);
                if(can)
                {
                    for(int r = 0; r < map.length; r++){
                        System.arraycopy(tempMap[r],0,map[r],0,map[r].length);
                    }
                }
            }
        }
        else{
            if(part== 1)moveDown(map,botPos);
            else{
                can = true;
                String[][] tempMap = new String[map.length][map[0].length];
                for(int r = 0; r < map.length; r++){
                    System.arraycopy(map[r],0,tempMap[r],0,map[r].length);
                }
                moveDown2(tempMap,botPos[0],botPos[1],botPos,1);
                if(can)
                {
                    for(int r = 0; r < map.length; r++){
                        System.arraycopy(tempMap[r],0,map[r],0,map[r].length);
                    }
                }
            }


        }
    }

    public static void moveRight(String[][] map, int[] botPos)
    {
        int currentR = botPos[0];
        int currentC = botPos[1];
        if(currentC < map[0].length-1){
            String next = map[currentR][currentC+1];
            if(next.equals("O"))
            {
                int temp = currentC+1;
                while(next.equals("O"))
                {
                    if(temp < map[0].length-1)
                    {
                        next = map[currentR][temp+1];
                        temp++;
                    }
                }
                if(map[currentR][temp].equals("."))
                {
                    for(int i = temp;i >currentC+1;i--)
                    {
                        map[currentR][i]= "O";
                    }
                    map[currentR][currentC] = ".";
                    map[currentR][currentC+1] = "@";
                    botPos[1] = currentC+1;
                }
            }
            else if(!next.equals("#")){
                map[currentR][currentC] = ".";
                map[currentR][currentC+1] = "@";
                botPos[1] = currentC+1;
            }
        }
    }

    public static void moveRight2(String[][] map, int[] botPos)
    {
        int currentR = botPos[0];
        int currentC = botPos[1];
        if(currentC < map[0].length-1){
            String next = map[currentR][currentC+1];
            if(next.equals("["))
            {
                int temp = currentC+2;
                while(next.equals("["))
                {
                    if(temp < map[0].length-1)
                    {
                        next = map[currentR][temp+1];
                        temp+=2;
                    }
                }
                if(map[currentR][temp-1].equals("."))
                {
                    for(int i = temp-1;i >currentC+1;i-=2)
                    {
                        map[currentR][i]= "]";
                        map[currentR][i-1] = "[";
                    }
                    map[currentR][currentC] = ".";
                    map[currentR][currentC+1] = "@";
                    botPos[1] = currentC+1;
                }
            }
            else if(!next.equals("#")){
                map[currentR][currentC] = ".";
                map[currentR][currentC+1] = "@";
                botPos[1] = currentC+1;
            }
        }
    }

    public static void moveLeft(String[][] map, int[] botPos)
    {
        int currentR = botPos[0];
        int currentC = botPos[1];
        if(currentC > 0){
            String next = map[currentR][currentC-1];
            if(next.equals("O"))
            {
                int temp = currentC-1;
                while(next.equals("O"))
                {
                    if(temp >0)
                    {
                        next = map[currentR][temp-1];
                        temp--;
                    }
                }
                if(map[currentR][temp].equals("."))
                {
                    for(int i = temp;i < currentC-1;i++)
                    {
                        map[currentR][i]= "O";
                    }
                    map[currentR][currentC] = ".";
                    map[currentR][currentC-1] = "@";
                    botPos[1] = currentC-1;
                }
            }
            else if(!next.equals("#")){
                map[currentR][currentC] = ".";
                map[currentR][currentC-1] = "@";
                botPos[1] = currentC-1;
            }
        }
    }
    public static void moveLeft2(String[][] map, int[] botPos)
    {
        int currentR = botPos[0];
        int currentC = botPos[1];
        if(currentC > 0){
            String next = map[currentR][currentC-1];
            if(next.equals("]"))
            {
                int temp = currentC-2;
                while(next.equals("]"))
                {
                    if(temp >0)
                    {
                        next = map[currentR][temp-1];
                        temp-=2;
                    }
                }
                if(map[currentR][temp+1].equals("."))
                {
                    for(int i = temp+1;i < currentC-1;i+=2)
                    {
                        map[currentR][i]= "[";
                        map[currentR][i+1]= "]";
                    }
                    map[currentR][currentC] = ".";
                    map[currentR][currentC-1] = "@";
                    botPos[1] = currentC-1;
                }
            }
            else if(!next.equals("#")){
                map[currentR][currentC] = ".";
                map[currentR][currentC-1] = "@";
                botPos[1] = currentC-1;
            }
        }
    }

    public static void moveUp(String[][] map, int[] botPos)
    {
        int currentR = botPos[0];
        int currentC = botPos[1];
        if(currentR > 0){
            String next = map[currentR-1][currentC];
            if(next.equals("O"))
            {
                int temp = currentR-1;
                while(next.equals("O"))
                {
                    if(temp >0)
                    {
                        next = map[temp-1][currentC];
                        temp--;
                    }
                }
                if(map[temp][currentC].equals("."))
                {
                    for(int i = temp;i < currentR-1;i++)
                    {
                        map[i][currentC]= "O";
                    }
                    map[currentR][currentC] = ".";
                    map[currentR-1][currentC] = "@";
                    botPos[0] = currentR-1;
                }
            }
            else if(!next.equals("#")){
                map[currentR][currentC] = ".";
                map[currentR-1][currentC] = "@";
                botPos[0] = currentR-1;
            }
        }
    }

    public static void moveUp2(String[][] map, int r, int c, int[] botPos, int count)
    {
        if(r > 0)
        {
            boolean recurse = true;
            String next = map[r-1][c];
            String next2;
            if(next.equals("]")) next2 = map[r-1][c-1];
            else next2 = map[r-1][c+1];
            if(next.equals(".")) recurse = false;
            else if(next.equals("#") || (next2.equals("#") && count != 1)){
                can = false;
            }
            if(recurse && can)
            {
                moveUp2(map,r-1,c,botPos,count+1);
                if(next.equals("]")) moveUp2(map,r-1,c-1,botPos,count+1);
                else moveUp2(map,r-1,c+1,botPos,count+1);
            }
           if(can){
               map[r-1][c] = map[r][c];
               map[r][c] = ".";
               if(count == 1) botPos[0]--;
           }
        }
    }

    public static void moveDown(String[][] map, int[] botPos)
    {
        int currentR = botPos[0];
        int currentC = botPos[1];
        if(currentR < map.length-1){
            String next = map[currentR+1][currentC];
            if(next.equals("O"))
            {
                int temp = currentR+1;
                while(next.equals("O"))
                {
                    if(temp < map.length-1)
                    {
                        next = map[temp+1][currentC];
                        temp++;
                    }
                }
                if(map[temp][currentC].equals("."))
                {
                    for(int i = temp;i > currentR+1 ;i--)
                    {
                        map[i][currentC]= "O";
                    }
                    map[currentR][currentC] = ".";
                    map[currentR+1][currentC] = "@";
                    botPos[0] = currentR+1;
                }
            }
            else if(!next.equals("#")){
                map[currentR][currentC] = ".";
                map[currentR+1][currentC] = "@";
                botPos[0] = currentR+1;
            }
        }
    }

    public static void moveDown2(String[][] map, int r, int c, int[] botPos, int count)
    {
        if(r < map.length-1)
        {
            boolean recurse = true;
            String next = map[r+1][c];
            String next2;
            if(next.equals("]")) next2 = map[r+1][c-1];
            else next2 = map[r+1][c+1];
            if(next.equals(".")) recurse = false;
            else if(next.equals("#") || (next2.equals("#") && count != 1)) can = false;
            if(recurse && can)
            {
                moveDown2(map,r+1,c,botPos,count+1);
                if(next.equals("]")) moveDown2(map,r+1,c-1,botPos,count+1);
                else moveDown2(map,r+1,c+1,botPos,count+1);
            }
            if(can){
                map[r+1][c] = map[r][c];
                map[r][c] = ".";
                if(count == 1) botPos[0]++;
            }
        }
    }

    public static int calcGPS(String[][] map, int part)
    {
        int gps = 0;
        String match = "O";
        if(part == 2) match ="[";
        for(int r = 0; r < map.length; r++)
        {
            for(int c = 0; c < map[r].length; c++){
                if(map[r][c].equals(match)) gps+= 100*r + c;
            }
        }
        return gps;
    }

}
