package AOC2025;
import java.io.*;
import java.util.*;
public class Day11 {
    public static void main(String[] args){
        System.out.println(paths("inputs/input.txt",1));
        System.out.println(paths("inputs/input.txt",2));
    }

    public static long paths(String fileName, int part){
        HashMap<String, String[]> path = new HashMap<>();
        try{
            Scanner s = new Scanner(new File(fileName));
            while(s.hasNextLine()){
                String line = s.nextLine();
                String source = line.substring(0,line.indexOf(":"));
                String[] output = line.substring(line.indexOf(" ")+1).split(" ");
                path.put(source,output);
            }
            s.close();
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 1) return totalPossiblePaths(path,"you","you", "out", new HashSet<>());
        HashMap<String, Long> cache = new HashMap<>();
        return part2(cache, path, "svr", new ArrayList<>(List.of("dac","fft")));
    }

    public static long totalPossiblePaths(HashMap<String,String[]> outputs, String current, String path, String end,HashSet<String> allPaths){
        if(current.equals(end)) allPaths.add(path);
        else{
            String[] options = outputs.get(current);
            if(options != null){
                for(String output : options){
                    if(!path.contains(output)){
                        totalPossiblePaths(outputs,output,path+" "+output,end,allPaths);
                    }
                }
            }
        }
        return allPaths.size();
    }

    public static long part2(HashMap<String,Long> cache, HashMap<String,String[]> path, String current, ArrayList<String> restrictions){
        StringBuilder cacheKeySb= new StringBuilder(current);
        for(String restriction : restrictions){
            cacheKeySb.append(restriction);
        }
        String cacheKey = cacheKeySb.toString();
        if(cache.containsKey(cacheKey)) return cache.get(cacheKey);
        else if(current.equals("out")){
            if (restrictions.isEmpty()) return 1;
            else return 0;
        }
        boolean removed = false;
        if(restrictions.contains(current)){
            removed = true;
            restrictions.remove(current);
        }
        long paths = 0;
        for(String output : path.get(current)) paths += part2(cache,path,output, restrictions);
        cache.put(cacheKey,paths);
        if(removed) restrictions.add(current);
        return paths;
    }
}
