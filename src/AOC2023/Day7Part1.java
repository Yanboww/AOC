package AOC2023;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;
public class Day7Part1 {
    public static void main(String[] args) {
        File f;
        try{
            f = new File("InputFile");
            Scanner s = new Scanner(f);
            List<String> hand = new ArrayList<>();
            List<Integer> bid = new ArrayList<>();
            List<Integer> type = new ArrayList<>();
            List<Integer> rank = new ArrayList<>();
            int total = 0;
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                String value ="";
                for(int a=0;a<line.indexOf(" ");a++)
                {
                    String letters = line.substring(a,a+1);
                    value+=letters;
                }
                hand.add(value);
                value="";
                for(int b = line.indexOf(" ")+1;b<line.length();b++)
                {
                    String letters = line.substring(b,b+1);
                    value+=letters;
                }
                int value2 = Integer.parseInt(value);
                bid.add(value2);
            }
            for(int c = 0;c<hand.size();c++)
            {
                String cards = hand.get(c);
                String nonRep ="";
                for(int e = 0; e< cards.length();e++)
                {
                    String letter = cards.substring(e,e+1);
                    if(countRep(letter,nonRep) ==0 && countRep(letter,cards)>1)
                    {
                        nonRep+=letter;
                    }
                }
                //System.out.println(cards);
                int c1 = countRep(cards.substring(0,1),cards);
                int c2 = countRep(cards.substring(1,2),cards);
                int c3 = countRep(cards.substring(2,3),cards);
                int c4 = countRep(cards.substring(3,4),cards);
                int c5 = countRep(cards.substring(4,5),cards);
                int count = determineType(c1,c2,c3,c4,c5);
                if(count ==0)
                {
                    int len = nonRep.length();
                    if(len == 1)
                    {
                        count=2;
                    }
                    else if(len==2)
                    {
                        count =3;
                    }
                }
                type.add(count);
            }
            for(int h =0;h<type.size();h++)
            {
                int numGet = type.get(h);
                int rankingAdd = findRank(hand, type,numGet,h);
                rank.add(rankingAdd);
            }
            for(int j = 0; j<rank.size();j++)
            {
                int ranking = rank.get(j);
                int bidNum = bid.get(j);

                total+=ranking*bidNum;
            }
            System.out.println(total);



        }
        catch(FileNotFoundException e)
        {
            System.out.println("File does not exist");
        }

    }
    public static int countRep(String l, String cards)
    {
        int count=0;
        for(int d=0;d<cards.length();d++)
        {
            String nl = cards.substring(d,d+1);
            if(nl.equals(l))
            {
                count++;
            }
        }
        return count;
    }

    public static int determineType(int c1,int c2, int c3, int c4,int c5)
    {
        if(c1 ==5 || c2 == 5 || c3 ==5 || c4==5 || c5==5)
        {
            return 7;
        }
        else if(c1 ==4 || c2 == 4 || c3 ==4 || c4==4 || c5==4)
        {
            return 6;
        }
        else if(c1 ==3 || c2 == 3 || c3 ==3 || c4==3 || c5==3)
        {
            if(c1 ==2 || c2 == 2 || c3 ==2 || c4==2 || c5==2)
            {
                return 5;
            }
            else{
                return 4;
            }
        }
        else if(c1 ==1 && c2 == 1 && c3 ==1 && c4==1 && c5==1)
        {
            return 1;
        }
        return 0;
    }

    public static int findRank(List<String> hand, List<Integer> type, int num, int i)
    {
        String power = "23456789TJQKA";
        int rank = hand.size();
        for(int f =0;f<type.size();f++)
        {
            if(f!=i)
            {
                int compare = type.get(f);
                if(num<compare)
                {
                    rank--;
                }
                else if(num==compare)
                {
                    String numCard = hand.get(i);
                    String compareCard = hand.get(f);
                    for(int g =0;g<numCard.length();g++)
                    {
                        String l1 = numCard.substring(g,g+1);
                        String l2 = compareCard.substring(g,g+1);
                        int index1 = power.indexOf(l1);
                        int index2 = power.indexOf(l2);
                        if(index1>index2)
                        {
                            break;
                        }
                        else if(index1<index2)
                        {
                            rank--;
                            break;
                        }
                    }

                }
            }

        }
        return rank;
    }




}