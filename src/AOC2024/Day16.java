package AOC2024;
import java.io.*;
import java.util.*;
public class Day16 {
    public static void main(String[] args)
    {
        //System.out.println(lowestScore("inputs/input.txt",1));
        //System.out.println(lowestScore("inputs/input.txt",1));
        System.out.println(lowestScore("inputs/trial",1));
    }

    public static int lowestScore (String fileName, int part)
    {
        File f = new File(fileName);
        int length = 141;
        if(fileName.contains("trial")) length = 7;
        String[][] map = new String[length][];
        int[] start = new int[2];
        int[] end = new int[2];
        ArrayList<String> nodes = new ArrayList<>();
        HashMap<String,String> dict = new HashMap<>();
        try{
            Scanner s = new Scanner(f);
            int index = 0;
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                String[] temp = new String[line.length()];
                for(int i = 0; i < line.length();i++){
                    temp[i] = line.substring(i,i+1);
                    switch (temp[i]) {
                        case "S" -> start = new int[]{index, i};
                        case "." -> nodes.add(index + "," + i);
                        case "E" -> end = new int[]{index, i};
                    }
                }
                map[index] = temp;
                index++;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        System.out.println(nodes);
        return lowestScoreCalc(map,start,end,dict,nodes);
    }

    public static int lowestScoreCalc(String[][] map, int[] start, int[] end, HashMap<String,String> dict, ArrayList<String> nodes)
    {
        ArrayList<int[]> queue = new ArrayList<>();
        queue.add(start);
        while (!queue.isEmpty()){
            String direction = "r";
            int score = 0;
            int[] cord = queue.get(0);
            String compare = cord[0]+","+cord[1];
            queue.remove(0);
            int index = nodes.indexOf(compare);
            if(index != -1) nodes.remove(index);
            if(dict.containsKey(compare)){
                String result = dict.get(compare);
                score = Integer.parseInt(result.substring(0,result.indexOf(" ")));
                direction = result.substring(result.lastIndexOf(" ")+1);
            };
            if(direction.equals("l") && cord[1]>0)
            {
                if(!map[cord[0]][cord[1]-1].equals("#")  ){
                    if(nodes.contains(cord[0]+","+(cord[1]-1))){
                        queue.add(new int[]{cord[0],cord[1]-1});
                    }
                    String temp = cord[0]+","+(cord[1]-1);
                    if(!dict.containsKey(temp) || Integer.parseInt(dict.get(temp).substring(0,dict.get(temp).indexOf(" ")) )> score+1) {
                        dict.put(cord[0]+","+(cord[1]-1), score+1 + " " + cord[0]+","+cord[1] + " " + direction);
                        //System.out.println(direction);
                    }
                }
            }
            else if(direction.equals("r") && cord[1] < map[0].length-1)
            {
                if(!map[cord[0]][cord[1]+1].equals("#"))
                {
                    if(nodes.contains(cord[0]+","+(cord[1]+1))){
                        queue.add(new int[]{cord[0],cord[1]+1});
                    }
                    String temp = cord[0]+","+(cord[1]+1);
                    if(!dict.containsKey(temp) || Integer.parseInt(dict.get(temp).substring(0,dict.get(temp).indexOf(" ")) )> score+1){
                        dict.put(cord[0]+","+(cord[1]+1), score+1 + " " + cord[0]+","+cord[1] + " " + direction);
                       // System.out.println(direction);
                    }
                }
            }
            else if(direction.equals("d") && cord[0] < map.length-1)
            {
                if(!map[cord[0]+1][cord[1]].equals("#"))
                {
                    if(nodes.contains(cord[0]+1+","+(cord[1]))){
                        queue.add(new int[]{cord[0]+1,cord[1]});
                    }
                    String temp = cord[0]+1+","+(cord[1]);
                    if(!dict.containsKey(temp) || Integer.parseInt(dict.get(temp).substring(0,dict.get(temp).indexOf(" ")) )> score+1) {
                        dict.put(cord[0]+1+","+cord[1], score+1 + " " + cord[0]+","+cord[1] + " " + direction);
                        //System.out.println(direction);
                    }
                }
            }
            else if(direction.equals("u") && cord[0] > 0)
            {
                if(!map[cord[0]-1][cord[1]].equals("#"))
                {
                    if(nodes.contains(cord[0]-1+","+(cord[1]))){
                        queue.add(new int[]{cord[0]-1,cord[1]});
                    }
                    String temp = cord[0]-1+","+(cord[1]);
                    if(!dict.containsKey(temp) || Integer.parseInt(dict.get(temp).substring(0,dict.get(temp).indexOf(" ")) )> score+1){
                        //System.out.println(direction);
                        dict.put(cord[0]-1+","+cord[1], score+1 + " " + cord[0]+","+cord[1] + " " + direction);
                    }
                }
            }
            if(direction.equals("r") || direction.equals("l"))
            {
                if(cord[0] > 0 && !map[cord[0]-1][cord[1]].equals("#"))
                {
                    if(nodes.contains(cord[0]-1+","+(cord[1]))){
                        queue.add(new int[]{cord[0]-1,cord[1]});
                    }
                    String temp = cord[0]-1+","+(cord[1]);
                    if(!dict.containsKey(temp) || Integer.parseInt(dict.get(temp).substring(0,dict.get(temp).indexOf(" ")) )> score+1001){
                        dict.put(cord[0]-1+","+cord[1], score+1001 + " " + cord[0]+","+cord[1] + " u");
                        //System.out.println(direction);
                    }
                }
                if(cord[0] < map.length-1 && !map[cord[0]+1][cord[1]].equals("#"))
                {
                    if(nodes.contains(cord[0]+1+","+(cord[1]))){
                        queue.add(new int[]{cord[0]+1,cord[1]});
                    }
                    String temp = cord[0]+1+","+(cord[1]);
                    if(!dict.containsKey(temp) || Integer.parseInt(dict.get(temp).substring(0,dict.get(temp).indexOf(" ")) )> score+1001){
                        dict.put(cord[0]+1+","+cord[1], score+1001 + " " + cord[0]+","+cord[1] + " d");
                        //System.out.println(direction);
                    }
                }
            }
            else
            {
                if(cord[1] > 0 && !map[cord[0]][cord[1]-1].equals("#"))
                {
                    if(nodes.contains(cord[0]+","+(cord[1]-1))){
                        queue.add(new int[]{cord[0],cord[1]-1});
                    }
                    String temp = cord[0]+","+(cord[1]-1);
                    if(!dict.containsKey(temp) || Integer.parseInt(dict.get(temp).substring(0,dict.get(temp).indexOf(" ")) )> score+1001){
                        dict.put(cord[0]+","+(cord[1]-1), score+1001 + " " + cord[0]+","+cord[1] + " l");
                        System.out.println(direction);
                    }
                }
                if(cord[1] < map[0].length-1 && !map[cord[0]][cord[1]+1].equals("#"))
                {
                    if(nodes.contains(cord[0]+","+(cord[1]+1))){
                        queue.add(new int[]{cord[0],cord[1]+1});
                    }
                    String temp = cord[0]+","+(cord[1]+1);
                    if(!dict.containsKey(temp) || Integer.parseInt(dict.get(temp).substring(0,dict.get(temp).indexOf(" ")) )> score+1001){
                        dict.put(cord[0]+","+(cord[1]+1), score+1001 + " " + cord[0]+","+cord[1] + " r");
                        System.out.println(direction);
                    }
                }
            }
            sortQueue(queue,dict);
            //System.out.println(Arrays.toString(cord));
            String result = dict.get(end[0]+","+end[1]);
            if(result!=null) System.out.println(Integer.parseInt(result.substring(0,result.indexOf(" "))));
        }
        String result = dict.get(end[0]+","+end[1]);
        return Integer.parseInt(result.substring(0,result.indexOf(" ")));
    }

    public static void sortQueue(ArrayList<int[]> list, HashMap<String,String> dict)
    {
        int[][] temp = new int[list.size()][];
        split(0,list.size()-1,list,temp,dict);
    }

    public static void split(int start, int end, ArrayList<int[]> list, int[][] arr, HashMap<String,String> dict)
    {
        if(start < end)
        {
            int m = (end+start)/2;
            split(start, m, list, arr,dict);
            split(m+1, end, list, arr,dict);
            merge(start, m, end, list, arr,dict);
        }
    }

    public static void merge(int start, int mid, int end, ArrayList<int[]> list, int[][] temp, HashMap<String,String> dict)
    {
        int s = start;
        int index = start;
        int m = mid+1;
        while(s <= mid && m <= end)
        {
            int[] cord1 = list.get(s);
            int[] cord2 = list.get(m);
            String val1 = dict.get(cord1[0]+","+cord1[1]);
            String val2 = dict.get(cord2[0]+","+cord2[1]);
            int v1 = Integer.parseInt(val1.substring(0,val1.indexOf(" ")));
            int v2 = Integer.parseInt(val2.substring(0,val2.indexOf(" ")));
            if(v1 > v2)
            {
                temp[index] = cord2;
                m++;
            }
            else{
                temp[index] = cord1;
                s++;
            }
            index++;
        }
        while(s <= mid)
        {
            temp[index] = list.get(s);
            s++;
            index++;
        }
        while(m <= end)
        {
            temp[index] = list.get(m);
            m++;
            index++;
        }
        for(int i = start; i <= end; i++)
        {
            list.set(i,temp[i]);
        }
    }


}
