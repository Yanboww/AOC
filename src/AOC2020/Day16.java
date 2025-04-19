package AOC2020;
import java.io.*;
import java.util.*;
public class Day16 {
    public static void main(String[] args){
        System.out.println(decipherTicket("inputs/input.txt",1));
        System.out.println(decipherTicket("inputs/input.txt",2));
    }

    public static Long decipherTicket(String fileName, int part)
    {
        File f = new File(fileName);
        ArrayList<String> ranges = new ArrayList<>();
        String[] personalTicket = new String[0];
        ArrayList<String[]> surrounding = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            int count = 0;
            while(s.hasNextLine()){
                String line = s.nextLine();
                if(line.isEmpty()) count++;
                else if (count == 0){
                    line = line.substring(line.indexOf(":")+2);
                    String[] currentRanges = line.split(" or ");
                    Collections.addAll(ranges, currentRanges);
                }
                else if(count == 1){
                    personalTicket = s.nextLine().split(",");
                }
                else{
                    if(!line.contains(":")){
                        surrounding.add(line.split(","));
                    }
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found!");
        }
        if (part == 1) return (long)findSurrounding(ranges,surrounding);
        return findDepartureProduct(ranges,surrounding,personalTicket);
    }

    public static int findSurrounding(ArrayList<String> ranges, ArrayList<String[]> surrounding){
        int total = 0;
        for(String[] ticket : surrounding){
            for(String number : ticket){
                int numberInt = Integer.parseInt(number);
                if(!inRange(ranges,numberInt)) total+=numberInt;
            }
        }
        return total;
    }

    public static long findDepartureProduct(ArrayList<String> ranges, ArrayList<String[]> surroundings, String[] personal){
        filterSurroundings(ranges,surroundings);
        HashMap<Integer,ArrayList<Integer>> dict = new HashMap<>();
        for(int i = 0; i < personal.length; i++){
            boolean first = true;
            ArrayList<Integer> validRanges = new ArrayList<>();
            ArrayList<Integer> counter = new ArrayList<>();
            for(String[] ticket : surroundings){
                if(first){
                    for(int j = 0; j < ranges.size(); j++){
                        String range = ranges.get(j);
                        if(inRange(range,Integer.parseInt(ticket[i]))){
                            validRanges.add(j);
                        }
                    }
                    first = false;
                }
                else{
                    for (int rangeIndex : validRanges) {
                        int secondIndex = rangeIndex + 1;
                        if (rangeIndex % 2 != 0) secondIndex -= 2;
                        int ticketNum = Integer.parseInt(ticket[i]);
                        if (inRange(ranges.get(rangeIndex), ticketNum) || inRange(ranges.get(secondIndex), ticketNum)) {
                            counter.add(rangeIndex);
                        }
                    }
                }
                dict.put(i,findFieldIndex(counter));
            }
        }
        HashMap<Integer,String> ordered = calculateOrder(dict,personal);
        long product = 1;
        for(int i = 0; i < 6; i++){
            product*=Long.parseLong(ordered.get(i));
        }
        return product;
    }

    public static HashMap<Integer,String> calculateOrder(HashMap<Integer,ArrayList<Integer>> dict, String[] personal){
        ArrayList<Integer> reviewed = new ArrayList<>();
        int currentReview = isDone(dict,reviewed);
        while(currentReview>-1){
            removeVal(dict,currentReview);
            reviewed.add(currentReview);
            currentReview = isDone(dict,reviewed);
        }
        HashMap<Integer,String> result = new HashMap<>();
        for(Map.Entry<Integer,ArrayList<Integer>> entry : dict.entrySet()){
            result.put(entry.getValue().get(0),personal[entry.getKey()]);
        }
        return result;
    }

    public static Integer isDone(HashMap<Integer,ArrayList<Integer>> dict, ArrayList<Integer> reviewed){
        for(Map.Entry<Integer,ArrayList<Integer>> entry : dict.entrySet()){
             ArrayList<Integer> entryVal = entry.getValue();
             if(entryVal.size() == 1 && !reviewed.contains(entryVal.get(0))) return entryVal.get(0);
        }
        return -1;
    }

    public static void removeVal(HashMap<Integer,ArrayList<Integer>> dict,int review){
        for(Map.Entry<Integer,ArrayList<Integer>> entry : dict.entrySet()){
            ArrayList<Integer> entryVal = entry.getValue();
            if(entryVal.contains(review) && entryVal.size() > 1){
                for(int i = 0; i < entryVal.size(); i++){
                    if(entryVal.get(i) == review) {
                        entryVal.remove(i);
                        break;
                    }
                }
            }
        }
    }
    public static ArrayList<Integer> findFieldIndex(ArrayList<Integer> counter){
        HashMap<Integer,Integer> count = new HashMap<>();
        for(int element : counter){
            count.put(element,count.getOrDefault(element,0)+1);
        }
        int maxFrequency = 0;
        ArrayList<Integer> mostFrequentElements = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                maxFrequency = entry.getValue();
                mostFrequentElements.clear();
                mostFrequentElements.add(entry.getKey()/2);
            }
            else if(entry.getValue() == maxFrequency) mostFrequentElements.add(entry.getKey()/2);
        }
        return mostFrequentElements;
    }

    public static void filterSurroundings(ArrayList<String> ranges, ArrayList<String[]> surroundings){
        for(int i = 0; i < surroundings.size(); i++){
            String[] ticket = surroundings.get(i);
            for(String number: ticket){
                int numberInt = Integer.parseInt(number);
                if(!inRange(ranges,numberInt)){
                    surroundings.remove(i);
                    i--;
                    break;
                }
            }
        }
    }

    public static boolean inRange(ArrayList<String> ranges, int number){
        for(String range : ranges){
            String[] rangeVal = range.split("-");
            int low = Integer.parseInt(rangeVal[0]);
            int high = Integer.parseInt(rangeVal[1]);
            if(number >= low && number <= high) return true;
        }
        return false;
    }

    public static boolean inRange(String range, int number){
        String[] rangeVal = range.split("-");
        int low = Integer.parseInt(rangeVal[0]);
        int high = Integer.parseInt(rangeVal[1]);
        return number >= low && number <= high;
    }


}
