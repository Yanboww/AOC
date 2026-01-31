package AOC2016;
import java.util.*;
public class Day13 {
    public static void main(String[] args){
        System.out.println(findShortest(1364,39,31,1));
        System.out.println(findShortest(1364,39,31,2));
    }

    public static int findShortest(int favoriteNum, int endRow, int endCol, int part){
        int[][] grid = new int[100][100];
        for(int y = 0; y < grid.length; y++ ){
            for(int x = 0; x < grid.length; x++){
                String result = Integer.toBinaryString(x*x + 3*x + 2*x*y + y + y*y + favoriteNum).replace("0","");
                int total = result.length();
                if(total%2==0) grid[y][x] = 0;
                else grid[y][x] = 1;
            }
        }
        return pathFinding(grid, endRow, endCol, new HashMap<>(), new PriorityQueue<>(), part);
    }

    public static int pathFinding(int[][] grid, int endRow, int endCol, HashMap<String,Integer> dict, PriorityQueue<String> queue, int part){
        HashSet<String> locations = new HashSet<>();
        locations.add("1,1");
        queue.add("1,1");
        dict.put("1,1",0);
        while(!queue.isEmpty()){
            String current = queue.poll();
            int[] coordinate = Arrays.stream(current.split(",")).mapToInt(Integer::parseInt).toArray();
            if(coordinate[0] > 0){
                if(grid[coordinate[0]-1][coordinate[1]] == 0){
                    String up = coordinate[0]-1+","+coordinate[1];
                    int value = dict.getOrDefault(up,10000);
                    int diff = dict.get(current)+1 - value;
                    if(diff < 0){
                        dict.put(up,dict.getOrDefault(current,0)+1);
                        if(dict.get(up) <= 50) locations.add(up);
                        queue.offer(up);
                    }
                }
            }
            if(coordinate[0] < grid.length - 1){
                if(grid[coordinate[0]+1][coordinate[1]] == 0){
                    String down = coordinate[0]+1 +","+ coordinate[1];
                    int value = dict.getOrDefault(down,10000);
                    int diff = dict.get(current)+1 - value;
                    if(diff < 0){
                        dict.put(down,dict.getOrDefault(current,0)+1);
                        if(dict.get(down) <= 50) locations.add(down);
                        queue.offer(down);
                    }
                }
            }
            if(coordinate[1] > 0){
                if(grid[coordinate[0]][coordinate[1]-1] == 0){
                    String left  = coordinate[0]+","+(coordinate[1]-1);
                    int value = dict.getOrDefault(left,10000);
                    int diff = dict.get(current) + 1 - value;
                    if(diff < 0){
                        dict.put(left,dict.getOrDefault(current,0)+1);
                        if(dict.get(left) <= 50) locations.add(left);
                        queue.offer(left);
                    }
                }
            }
            if(coordinate[1] < grid[0].length-1){
                if(grid[coordinate[0]][coordinate[1]+1] == 0){
                    String right = coordinate[0]+","+(coordinate[1]+1);
                    int value = dict.getOrDefault(right,10000);
                    int diff = dict.get(current) + 1 - value;
                    if(diff < 0){
                        dict.put(right,dict.getOrDefault(current,0)+1);
                        if(dict.get(right) <= 50) locations.add(right);
                        queue.offer(right);
                    }
                }
            }
        }
        if(part == 1) return dict.get(endRow+","+endCol);
        return locations.size();
    }
}
