import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Day9 {
    public static void main(String[] args) {
        System.out.println(tailVisited("inputs/input9"));
        System.out.println(tailVisitedPart2("inputs/input9"));
    }

    public static int tailVisited(String fileName)
    {
        String[][] plane = new String[1000][1000];
       for(String[] array : plane)
        {
            Arrays.fill(array,".");
        }
        int rH = 490;
        int cH = 490;
        int rT = 490;
        int cT = 490;
        try{
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            int counter = 1;
            while(s.hasNextLine())
            {
                String[] directions = s.nextLine().split(" ");
                String direction = directions[0];
                int count = Integer.parseInt(directions[1]);
                counter++;
                if(direction.equals("D")){
                    int timer = rH+count;
                    while(rH < timer)
                    {
                        plane[rT][cT] = "#";
                        rH++;
                        int[] coordinates = findNewT(rT, cT, rH, cH);
                        cT = coordinates[0];
                        rT = coordinates[1];
                    }
                    plane[rT][cT] = "#";
                }
                else if(direction.equals("U")){
                    int timer = rH-count;
                    while(rH > timer)
                    {
                        plane[rT][cT] = "#";
                        rH--;
                        int[] coordinates = findNewT(rT, cT, rH, cH);
                        cT = coordinates[0];
                        rT = coordinates[1];
                    }
                    plane[rT][cT] = "#";
                }
                else if(direction.equals("L")){
                    int timer = cH-count;
                    while(cH > timer)
                    {
                        plane[rT][cT] = "#";
                        cH--;
                        int[] coordinates = findNewT(rT, cT, rH, cH);
                        cT = coordinates[0];
                        rT = coordinates[1];
                    }
                    plane[rT][cT] = "#";
                }
                else if(direction.equals("R")){
                    int timer = cH+count;
                    while(cH < timer)
                    {
                        plane[rT][cT] = "#";
                        cH++;
                        int[] coordinates = findNewT(rT, cT, rH, cH);
                        cT = coordinates[0];
                        rT = coordinates[1];
                    }
                    plane[rT][cT] = "#";
                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        return countSymbol(plane);
    }

    public static int tailVisitedPart2(String fileName)
    {
        String[][] plane = new String[1000][1000];
        for(String[] array : plane)
        {
            Arrays.fill(array,".");
        }
        int rH = 490;
        int cH = 490;
        int r1 = 490;
        int c1 = 490;
        int r2 = 490;
        int c2 = 490;
        int r3 = 490;
        int c3 = 490;
        int r4 = 490;
        int c4 = 490;
        int r5 = 490;
        int c5 = 490;
        int r6 = 490;
        int c6 = 490;
        int r7 = 490;
        int c7 = 490;
        int r8 = 490;
        int c8 = 490;
        int rT = 490;
        int cT = 490;
        try{
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while(s.hasNextLine())
            {
                String[] directions = s.nextLine().split(" ");
                String direction = directions[0];
                int count = Integer.parseInt(directions[1]);
                if(direction.equals("D")){
                    int timer = rH+count;
                    while(rH < timer)
                    {
                        plane[rT][cT] = "#";
                        rH++;
                        int[] coordinates = findNewT(r1, c1, rH, cH);
                        c1 = coordinates[0];
                        r1 = coordinates[1];
                        coordinates = findNewT(r2, c2, r1, c1);
                        c2 = coordinates[0];
                        r2 = coordinates[1];
                        coordinates = findNewT(r3, c3, r2, c2);
                        c3 = coordinates[0];
                        r3 = coordinates[1];
                        coordinates = findNewT(r4, c4, r3, c3);
                        c4 = coordinates[0];
                        r4 = coordinates[1];
                        coordinates = findNewT(r5, c5, r4, c4);
                        c5 = coordinates[0];
                        r5 = coordinates[1];
                        coordinates = findNewT(r6, c6, r5, c5);
                        c6 = coordinates[0];
                        r6 = coordinates[1];
                        coordinates = findNewT(r7, c7, r6, c6);
                        c7 = coordinates[0];
                        r7 = coordinates[1];
                        coordinates = findNewT(r8, c8, r7, c7);
                        c8 = coordinates[0];
                        r8 = coordinates[1];
                        coordinates = findNewT(rT, cT, r8, c8);
                        cT = coordinates[0];
                        rT = coordinates[1];
                    }
                    plane[rT][cT] = "#";
                }
                else if(direction.equals("U")){
                    int timer = rH-count;
                    while(rH > timer)
                    {
                        plane[rT][cT] = "#";
                        rH--;
                        int[] coordinates = findNewT(r1, c1, rH, cH);
                        c1 = coordinates[0];
                        r1 = coordinates[1];
                        coordinates = findNewT(r2, c2, r1, c1);
                        c2 = coordinates[0];
                        r2 = coordinates[1];
                        coordinates = findNewT(r3, c3, r2, c2);
                        c3 = coordinates[0];
                        r3 = coordinates[1];
                        coordinates = findNewT(r4, c4, r3, c3);
                        c4 = coordinates[0];
                        r4 = coordinates[1];
                        coordinates = findNewT(r5, c5, r4, c4);
                        c5 = coordinates[0];
                        r5 = coordinates[1];
                        coordinates = findNewT(r6, c6, r5, c5);
                        c6 = coordinates[0];
                        r6 = coordinates[1];
                        coordinates = findNewT(r7, c7, r6, c6);
                        c7 = coordinates[0];
                        r7 = coordinates[1];
                        coordinates = findNewT(r8, c8, r7, c7);
                        c8 = coordinates[0];
                        r8 = coordinates[1];
                        coordinates = findNewT(rT, cT, r8, c8);
                        cT = coordinates[0];
                        rT = coordinates[1];
                    }
                    plane[rT][cT] = "#";
                }
                else if(direction.equals("L")){
                    int timer = cH-count;
                    while(cH > timer)
                    {
                        plane[rT][cT] = "#";
                        cH--;
                        int[] coordinates = findNewT(r1, c1, rH, cH);
                        c1 = coordinates[0];
                        r1 = coordinates[1];
                        coordinates = findNewT(r2, c2, r1, c1);
                        c2 = coordinates[0];
                        r2 = coordinates[1];
                        coordinates = findNewT(r3, c3, r2, c2);
                        c3 = coordinates[0];
                        r3 = coordinates[1];
                        coordinates = findNewT(r4, c4, r3, c3);
                        c4 = coordinates[0];
                        r4 = coordinates[1];
                        coordinates = findNewT(r5, c5, r4, c4);
                        c5 = coordinates[0];
                        r5 = coordinates[1];
                        coordinates = findNewT(r6, c6, r5, c5);
                        c6 = coordinates[0];
                        r6 = coordinates[1];
                        coordinates = findNewT(r7, c7, r6, c6);
                        c7 = coordinates[0];
                        r7 = coordinates[1];
                        coordinates = findNewT(r8, c8, r7, c7);
                        c8 = coordinates[0];
                        r8 = coordinates[1];
                        coordinates = findNewT(rT, cT, r8, c8);
                        cT = coordinates[0];
                        rT = coordinates[1];
                    }
                    plane[rT][cT] = "#";
                }
                else if(direction.equals("R")){
                    int timer = cH+count;
                    while(cH < timer)
                    {
                        plane[rT][cT] = "#";
                        cH++;
                        int[] coordinates = findNewT(r1, c1, rH, cH);
                        c1 = coordinates[0];
                        r1 = coordinates[1];
                        coordinates = findNewT(r2, c2, r1, c1);
                        c2 = coordinates[0];
                        r2 = coordinates[1];
                        coordinates = findNewT(r3, c3, r2, c2);
                        c3 = coordinates[0];
                        r3 = coordinates[1];
                        coordinates = findNewT(r4, c4, r3, c3);
                        c4 = coordinates[0];
                        r4 = coordinates[1];
                        coordinates = findNewT(r5, c5, r4, c4);
                        c5 = coordinates[0];
                        r5 = coordinates[1];
                        coordinates = findNewT(r6, c6, r5, c5);
                        c6 = coordinates[0];
                        r6 = coordinates[1];
                        coordinates = findNewT(r7, c7, r6, c6);
                        c7 = coordinates[0];
                        r7 = coordinates[1];
                        coordinates = findNewT(r8, c8, r7, c7);
                        c8 = coordinates[0];
                        r8 = coordinates[1];
                        coordinates = findNewT(rT, cT, r8, c8);
                        cT = coordinates[0];
                        rT = coordinates[1];
                    }
                    plane[rT][cT] = "#";
                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        return countSymbol(plane);
    }

    public static int[] findNewT(int rT, int cT, int rH, int cH){
        int[] coordinates = new int[2];
        if(isTouching(rT,cT,rH,cH)) {
            coordinates[0] = cT;
            coordinates[1] = rT;
        }
        else if(cH != cT && rH != rT)
        {
            if(cH == cT+1 && rH > rT)
            {
                coordinates[0] = cT+1;
                coordinates[1] = rT+1;
            }
            else if (cH == cT+1 && rH < rT) {
                coordinates[0] = cT+1;
                coordinates[1] = rT-1;
            }
            else if(cH == cT-1 && rH > rT)
            {
                coordinates[0] = cT-1;
                coordinates[1] = rT+1;
            }
            else if (cH == cT-1 && rH < rT) {
                coordinates[0] = cT-1;
                coordinates[1] = rT-1;
            }
            else if(cH == cT-2 && rH > rT)
            {
                coordinates[0] = cT-1;
                coordinates[1] = rT+1;
            }
            else if(cH == cT - 2 && rH < rT)
            {
                coordinates[0] = cT -1;
                coordinates[1] = rT -1;
            }
            else if(cH == cT +2 && rH > rT)
            {
                coordinates[0] = cT+1;
                coordinates[1] = rT+1;
            }
            else if (cH == cT+2 && rH < rT)
            {
                coordinates[0] = cT +1;
                coordinates[1] = rT -1;
            }
        }
        else if(rH == 2+rT)
        {
            coordinates[0] = cT;
            coordinates[1] = rT+1;
        }
        else if(rH == rT-2)
        {
            coordinates[0] = cT;
            coordinates[1] = rT-1;
        }
        else if(cH == 2+cT)
        {
            coordinates[0] = cT+1;
            coordinates[1] = rT;
        }
        else if(cH == cT-2)
        {
            coordinates[0] = cT-1;
            coordinates[1] = rT;
        }
        else {
            coordinates[0] = cT;
            coordinates[1] = rT;

        }
        return coordinates;
    }

    public static int countSymbol (String[][] plane)
    {
        int count = 0;
        for(int r = 0; r < plane.length; r++)
        {
            for(int c = 0; c < plane[r].length; c++)
            {
                if(plane[r][c].equals("#")) count++;
            }
        }
        return count;
    }

    public static boolean isTouching(int rT, int cT, int rH, int cH)
    {
        if(Math.abs(rT-rH) < 2 && Math.abs(cT-cH) <2) return true;
        return false;
    }



}
