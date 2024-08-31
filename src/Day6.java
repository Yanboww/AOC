import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Day6 {
    public static void main(String[] args)
    {
        System.out.println(findProcessBeforePacket("inputs/input6"));
        System.out.println(findProcessBeforeMessage("inputs/input6"));
    }

    public static int findProcessBeforePacket(String fileName)
    {
        int count = 0;
        try{
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            String packet = s.nextLine();
            for(int i = 0; i < packet.length()-3; i++)
            {
                count++;
                String code =packet.substring(i,i+1);
                String second=packet.substring(i+1,i+2);
                if(!code.contains(second))
                {
                    code+=second;
                    String third =packet.substring(i+2,i+3);
                    if(!code.contains(third))
                    {
                        code+=third;
                        String fourth = packet.substring(i+3,i+4);
                        if(!code.contains(fourth)) break;
                    }

                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        return count+3;
    }
    public static int findProcessBeforeMessage(String fileName)
    {
        int count = 0;
        try{
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            String packet = s.nextLine();
            for(int i = 0; i < packet.length()-3; i++)
            {
                count++;
                String code = packet.substring(i,i+14);
                if(checkRepeat(code)) break;

            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        return count+13;
    }

    public static boolean checkRepeat(String code)
    {
        String tempCode = "";
        for(int i = 0; i < code.length(); i++)
        {
            String letter = code.substring(i,i+1);
            if(!tempCode.contains(letter)) tempCode+=letter;
            else return false;
        }
        return true;
    }



}
