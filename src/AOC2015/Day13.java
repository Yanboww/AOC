package AOC2015;
import java.io.*;
import java.util.*;
public class Day13 {
    public static void main(String[] args){
        System.out.println(optimal("inputs/input.txt",1));
        System.out.println(optimal("inputs/input.txt",2));
    }

    public static int optimal(String fileName, int part){
        HashMap<String, Integer> moodChange = new HashMap<>();
        ArrayList<String> attendees = new ArrayList<>();
        HashSet<String> invited = new HashSet<>();
        try{
            Scanner s = new Scanner(new File(fileName));
            while(s.hasNextLine()){
                String[] input = s.nextLine().split(" ");
                if(!attendees.contains(input[0])) attendees.add(input[0]);
                int mood = Integer.parseInt(input[3]);
                String gainOrLose = input[2];
                if(gainOrLose.equals("lose")) mood*=-1;
                moodChange.put(input[0]+" "+input[10],mood);
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(part == 2)  attendees.add("Me");
        return optimalMood(moodChange,attendees, new String[attendees.size()], invited,0);
    }

    public static int optimalMood(HashMap<String,Integer> dict, ArrayList<String> attendees, String[] layout, HashSet<String> invited, int depth){
        if(depth == layout.length){
            int totalMood = 0;
            for(int i = 0; i < layout.length; i++){
                String current = layout[i];
                String next; String prev;
                if(i == 0) prev = layout[layout.length-1];
                else prev = layout[i-1];
                if(i == layout.length-1) next = layout[0];
                else next = layout[i+1];
                totalMood += dict.getOrDefault(current+" "+next+".",0);
                totalMood += dict.getOrDefault(current+" "+prev+".",0);
            }
            return totalMood;
        }
        int maxMood = 0;
        for(int i = 0; i < layout.length; i++){
            String attendee = attendees.get(i);
            if(invited.contains(attendee)) continue;
            invited.add(attendee);
            layout[depth] = attendee;
            int mood = optimalMood(dict,attendees,layout,invited,depth+1);
            if(maxMood < mood) maxMood = mood;
            invited.remove(attendee);
        }
        return maxMood;
    }

}
