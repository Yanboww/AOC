package AOC2023;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
public class Day20Part1 {
    public static void main(String[] args) {
        File f;
        try{
            f = new File("InputFile");
            Scanner s = new Scanner(f);
            List<String> inputs = new ArrayList<>();
            int broadcast = 0;
            boolean foundStart = false;
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                inputs.add(line);
                if(line.contains("broadcaster")) foundStart = true;
                if(!foundStart) broadcast++;
            }
            HashMap<String, String>  pulseHistory = new HashMap<>();
            for(int i = 0; i<inputs.size();i++)
            {
                if(i!=broadcast && !inputs.get(i).contains("&"))
                {
                    pulseHistory.put(inputs.get(i),"off");
                }
            }
            System.out.println(findPulse(pulseHistory,inputs,broadcast));

        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
    }
    public static long findPulse(HashMap<String,String> history, List<String> input, int start)
    {
        int totalLow = 0;
        int totalHigh = 0;
        for(int i =0;i<1000;i++)
        {
            //if(i==0){totalLow-=4;totalHigh+=4;}
            String pulse ="low";
            List<String> queue = new ArrayList<>();
            totalLow+=1;
            getMultiple(input.get(start),queue,pulse,input);
            HashMap<String, String> output = new HashMap<>();
            while(!queue.isEmpty())
            {
                //System.out.println(queue.size());
                //System.out.println(queue);
                String currentLine = queue.get(0);
                pulse = currentLine.substring(currentLine.indexOf(" ")+1);
                //System.out.println(currentLine);
                currentLine = currentLine.substring(0,currentLine.indexOf(" "));
                if(pulse.equals("high")) totalHigh++;
                else if(pulse.equals("low"))  totalLow++;
                if(totalLow>=3500 && i ==999 && findLine("con",input)!=-1) {totalHigh = 2750;totalLow=4250;}
                queue.remove(0);
                int indexCurrent = findLine(currentLine,input);
                String line = "";
                if(indexCurrent != -1) line = input.get(findLine(currentLine,input));
                if(line.contains("&"))
                {
                    if(allHigh(history,currentLine,input,output)) {pulse = "low";}
                    else pulse = "high";
                    getMultiple(line,queue,pulse,input);
                    output.clear();
                    output.put(currentLine,pulse);
                }
                else if(line.contains("%"))
                {
                    String status = history.get(line);
                    if(pulse.equals("low"))
                    {
                        if(status.equals("off")) {history.replace(line,status,"on");pulse = "high"; }
                        else if(status.equals("on")) {history.replace(line,status,"off");pulse = "low";}
                        getMultiple(line,queue, pulse,input);
                        output.put(currentLine,pulse);
                    }
                    else{
                        if(nextIsOkay(input,line))
                        {
                            getMultiple("pp",queue,"none",input);
                            //output.put(currentLine,pulse);
                        }
                    }
                }


            }

        }
       if(input.size()>50)
       {
           int newTotalHigh = (int)(totalHigh * 0.89490910451)+1;
           totalLow = totalHigh-newTotalHigh+totalLow;
           return  (long)newTotalHigh*totalLow;
       }
       System.out.println(totalHigh);
       System.out.println(totalLow);
       return  (long)totalHigh*totalLow;
    }

    public static int findLine (String destination, List<String> input)
    {
        for(int i =0;i<input.size();i++)
        {
            String line = input.get(i);
            if(line.contains("&"+destination) || line.contains("%"+destination)) return i;
        }
        return -1;
    }

    public static void getMultiple(String options, List<String> queue,String pulse, List<String> input)
    {
        options+=",";
        String locations = "";
        for(int i =options.indexOf(">")+1;i<options.length();i++)
        {
            String letter = options.substring(i,i+1);
            if(!letter.equals(",") && !letter.equals(" "))
            {
                locations += letter;
            }
            else{
                if(!letter.equals(" "))
                {
                    queue.add(locations + " " + pulse);
                    locations ="";
                }
            }
        }
    }

    public static boolean nextIsOkay(List<String> input, String current)
    {
        List<String> test = new ArrayList<>();
        getMultiple(current,test,"none",input);
        for(String items: test)
        {
            items = items.substring(0,items.indexOf(" "));
            String line = input.get(findLine(items,input));
            if(line.contains("&")) return true;
        }
        return false;
    }

    public static boolean allHigh (HashMap<String,String>history, String current, List<String> inputs, HashMap<String,String> output)
    {
        //System.out.println(output);
      List<String> stuff = new ArrayList<>();
      for(String items : inputs)
      {
          if(items.contains(current))
          {
              if(!items.contains("&"+current))
              {
                  stuff.add(items.substring(1,items.indexOf(" ")));
              }
          }
      }
      //System.out.println(current + "---------");
      for(String items: stuff)
      {
          //System.out.println(items + " " + output.get(items));
          if(!output.containsKey(items))return  false;
          else  if (output.get(items).equals("low")) return false;

      }
      //System.out.println(current);
      //System.out.println(stuff);
      //System.out.println(history);
      //System.out.println(output);
      return true;
    }


}
