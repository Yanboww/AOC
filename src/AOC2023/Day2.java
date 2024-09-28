package AOC2023;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Day2 {
    public static void main(String[] args) {
        File f;
        try{
            f = new File("InputFile");
            Scanner s = new Scanner(f);
            int total = 0;
            while (s.hasNextLine())
            {
                int prevGreen = 0;
                int prevRed = 0;
                int prevBlue = 0;
                String word = s.nextLine();
                int len1 = word.indexOf(" ");
                int len2 = word.indexOf(":");
                int gameNum = Integer.parseInt(word.substring(len1+1,len2));
                word = word.substring(len2+2) + ";";
                while(word.contains(";")) {
                    int len = word.indexOf(";");
                    String current = word.substring(0, len);
                    current += ",";
                    int greenNum = 0;
                    int redNum = 0;
                    int blueNum = 0;
                    while (current.contains(",")) {
                        String numbers = current.substring(0, current.indexOf(" "));
                        int number = Integer.parseInt(numbers);
                        if (current.indexOf(" ") + 1 == current.indexOf("green")) {
                            greenNum = number;
                        } else if (current.indexOf(" ") + 1 == current.indexOf("blue")) {
                            blueNum = number;
                        } else if (current.indexOf(" ") + 1 == current.indexOf("red")) {
                            redNum = number;
                        }
                        if (current.length() - 1 > current.indexOf(",")) {
                            current = current.substring(current.indexOf(",") + 2);
                        } else if (current.length() - 1 == current.indexOf(",")) {
                            current = current.substring(0, current.indexOf(","));
                        }

                    }
                    if (greenNum > prevGreen) {
                        prevGreen = greenNum;
                    }
                    if (redNum > prevRed) {
                        prevRed = redNum;
                    }
                    if (blueNum > prevBlue) {
                        prevBlue = blueNum;
                    }
                    if (word.length() - 1 > word.indexOf(";")) {
                        word = word.substring(len + 2);
                    } else if (word.length() - 1 == word.indexOf(";")) {
                        word = word.substring(0, word.indexOf(";"));
                    }

                }
                System.out.println(prevGreen);
                System.out.println(prevRed);
                System.out.println(prevBlue);
                System.out.println("----");
                int value = prevRed*prevBlue*prevGreen;
                total+=value;

            }
            System.out.println(total);

        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Does not Exist");
        }
    }
}