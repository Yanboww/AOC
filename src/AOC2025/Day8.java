package AOC2025;
import java.io.*;
import java.util.*;
public class Day8 {
    public static void main(String[] args) {
        System.out.println(junction("inputs/input.txt",1));
        System.out.println(junction("inputs/input.txt",2));
    }

    public static long junction(String fileName, int part){
        ArrayList<long[]> boxes = new ArrayList<>();
        try{
            Scanner s = new Scanner(new File(fileName));
            while (s.hasNextLine()) boxes.add(Arrays.stream(s.nextLine().split(",")).mapToLong(Long::parseLong).toArray());
            s.close();
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        return junctionConnections(boxes, part);
    }

    public static long junctionConnections(ArrayList<long[]> boxes, int part){
        ArrayList<String> pairs = new ArrayList<>();
        PriorityQueue<String> queue = new PriorityQueue<>();
        HashSet<String> usedSet = new HashSet<>();
        for(int i = 0; i < boxes.size(); i++){
            for(int j = 0; j < boxes.size(); j++){
                if(i == j) continue;
                long[] pair1 = boxes.get(i);
                long[] pair2 = boxes.get(j);
                String pair1Str = pair1[0]+","+pair1[1]+","+pair1[2];
                String pair2Str = pair2[0]+","+pair2[1]+","+pair2[2];
                if(!usedSet.contains(pair2Str+" "+pair1Str) && !usedSet.contains(pair1Str+" "+pair2Str)){
                    double dist = calcDistance(pair1,pair2);
                    StringBuilder distStr = new StringBuilder(Double.toString(dist));
                    int topLen = distStr.substring(0,distStr.indexOf(".")).length();
                    while(topLen < 10){
                        distStr.insert(0, "0");
                        topLen++;
                    }
                    queue.offer(distStr+" "+pair1Str+" "+pair2Str);
                    usedSet.add(pair1Str+" "+pair2Str);
                }
            }
        }
        int count = 0;
        String lastPair = "";
        while(true){
            if(!queue.isEmpty()){
                String[] currentPair = queue.poll().split(" ");
                String circuitStr = currentPair[1]+" "+currentPair[2];
                pairs.add(circuitStr);
                merge(pairs);
                lastPair = circuitStr;
                count++;
                if(part == 1 && count == 1000) break;
                else if(part == 2 && pairs.get(0).split(" ").length==boxes.size()) break;
            }
            else break;
        }
        if(part == 1) return calcResult(pairs);
        else{
            String[] lastPairs = lastPair.split(" ");
            String[] pair1 = lastPairs[0].split(",");
            String[] pair2 = lastPairs[1].split(",");
            return Long.parseLong(pair1[0])*Long.parseLong(pair2[0]);
        }
    }

    public static double calcDistance(long[] point1, long[] point2){
        return Math.sqrt(Math.pow((point1[0]-point2[0]),2)+Math.pow((point1[1]-point2[1]),2)+Math.pow((point1[2]-point2[2]),2));
    }

    public static int calcResult(ArrayList<String> results){
        PriorityQueue<Integer> sizes = new PriorityQueue<>(Comparator.reverseOrder());
        for(String result : results){
            sizes.offer(result.split(" ").length);
        }
        int size = 1;
        for(int i = 0; i < 3; i++){
            if(!sizes.isEmpty()) {
                int val = sizes.poll();
                size*=val;
            }
        }
        return size;
    }

    public static void merge(ArrayList<String> pairs){
        for(int i = 0; i < pairs.size(); i++){
            for(int r = 0; r < pairs.size(); r++){
                if(i == r) continue;
                String pair1 = pairs.get(i);
                String pair2 = pairs.get(r);
                if(isOverlap(pair1,pair2)){
                    String result = mergePair(pair1,pair2);
                    pairs.remove(pair1); pairs.remove(pair2);
                    i=0;
                    r=0;
                    pairs.add(result);
                }
            }
        }
    }

    public static boolean isOverlap(String pair1, String pair2){
        if(pair1.length() > pair2.length()){
            String[] pair1Arr = pair1.split(" ");
            for(String temp : pair1Arr){
                if(pair2.contains(temp)) return true;
            }
        }
        else{
            String[] pair2Arr = pair2.split(" ");
            for(String temp : pair2Arr){
                if(pair1.contains(temp)) return true;
            }
        }
        return false;
    }

    public static String mergePair(String pair1, String pair2){
        HashSet<String> used = new HashSet<>();
        StringBuilder result = new StringBuilder();
        String[] pair1Arr = pair1.split(" "); String[] pair2Arr = pair2.split(" ");
        for(String temp : pair1Arr){
            if(!used.contains(temp)){
                result.append(" ").append(temp);
                used.add(temp);
            }
        }
        for(String temp : pair2Arr){
            if(!used.contains(temp)){
                result.append(" ").append(temp);
                used.add(temp);
            }
        }
        return result.toString().trim();
    }
}
