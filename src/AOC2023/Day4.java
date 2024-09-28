//used iteration to solve so takes wayyyyy too long to run
package AOC2023;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class Day4 {
    public static void main(String[] args) {
        File f;
        try{
            f = new File("InputFile");
            Scanner s = new Scanner(f);
            int total =0;
            int current = 0;
            String numList ="1234567890";
            List<String> input = new ArrayList<>();
            List<Integer> won = new ArrayList<>();
            List<Integer> keeptrack = new ArrayList<>();
            List<Integer> cardValue = new ArrayList<>();
            while(s.hasNextLine())
            {
                input.add(s.nextLine());
            }
            while(current<input.size())
            {
                String line = input.get(current);
                line+=".";
                List<String> win = new ArrayList<>();
                List<String> have = new ArrayList<>();
                int startIndex = line.indexOf(":");
                String num="";
                String value = line.substring(startIndex+2,startIndex+3);
                while(!value.equals("|"))
                {
                    if(numList.contains(value))
                    {
                        num+=value;
                    }
                    else{
                        if(!num.isEmpty())
                        {
                            win.add(num);
                        }
                        num="";
                    }
                    startIndex++;
                    value = line.substring(startIndex+2,startIndex+3);
                }
                int index = line.indexOf("|")+2;
                for(int i = index;i<line.length();i++)
                {
                    value = line.substring(i,i+1);
                    if(numList.contains(value))
                    {
                        num+=value;
                    }
                    else{
                        if(!num.isEmpty())
                        {
                            have.add(num);
                        }
                        num="";
                    }
                }
                int score =0;
                int matches =0;
                for(int a = 0; a<have.size();a++)
                {
                    value = have.get(a);
                    if (win.contains(value))
                    {
                        matches++;
                        if(score==0)
                        {
                            score++;
                        }
                        else{
                            score*=2;
                        }
                    }
                }
                total+=score;
                int count =1;
                for(int z = 0; z<matches; z++)
                {
                    int wonCard = current+count;
                    if(wonCard<input.size())
                    {
                        won.add(wonCard);
                        keeptrack.add(wonCard);
                        //System.out.println(won);
                    }
                    count++;

                }
                //System.out.println(win);
                //System.out.println(have);
                //System.out.println(current);
                //System.out.println(matches);
                //System.out.println(won);
                //System.out.println("----");
                cardValue.add(matches);
                current++;
            }
            //System.out.println(won);
            while(!won.isEmpty())
            {
                System.out.println(won.size());
                int indexValue = won.get(0);
                won.remove(0);
                int matches = cardValue.get(indexValue);
                //System.out.println(matches);
                int count =1;
                for(int z = 0; z<matches; z++)
                {
                    int wonCard = indexValue+count;
                    //System.out.println(keeptrack);
                    if(wonCard<input.size())
                    {
                        won.add(wonCard);
                        keeptrack.add(wonCard);
                        //System.out.println(wonCard);
                    }
                    //System.out.println(wonCard);
                    count++;

                }

            }
            total = input.size() + keeptrack.size();
            System.out.println(total);

        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found!");
        }

    }
}
