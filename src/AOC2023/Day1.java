package AOC2023;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) {
        File f;
        try{
            int total = 0;
            f = new File("InputFile");
            Scanner s = new Scanner(f);
            String intList = "1234567890";
            while (s.hasNextLine())
            {
                String word = s.nextLine();
                word = word.replace("one","o1e");
                word = word.replace("two","t2o");
                word = word.replace("three","t3e");
                word = word.replace("four","f4r");
                word = word.replace("five","f5e");
                word = word.replace("six","s6x");
                word = word.replace("seven","s7n");
                word = word.replace("eight","e8t");
                word = word.replace("nine","n9e");
                String numbers="";
                int len = word.length();
                for (int i = 0; i< len; i++)
                {
                    String letter = word.substring(i,i+1);
                    if (intList.contains(letter))
                    {
                        numbers += letter+",";

                    }

                }
                String number1 = numbers.substring(0,numbers.indexOf(","));
                String number2 = numbers.substring(numbers.lastIndexOf(",")-1,numbers.lastIndexOf(","));
                number1+=number2;
                int hiddenValue = Integer.parseInt(number1);
                System.out.println(hiddenValue);
                total+=hiddenValue;
            }
            System.out.println(total);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("The file does not exist.");

        }

    }
}
