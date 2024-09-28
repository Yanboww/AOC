package AOC2023;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;
public class Day11 {
    public static void main(String[] args) {
        File f;
        try{
            f = new File("InputFile");
            Scanner s = new Scanner(f);
            List<String> galaxy = new ArrayList<>();
            List<Integer> indexRow = new ArrayList<>();
            List<Integer> columNum = new ArrayList<>();
            long total =0;
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                galaxy.add(line);
            }
            List<Integer> empty =findEmptyColumn(galaxy);
            galaxy = expandGalaxy(galaxy,empty);
            //System.out.println((galaxy.get(0)).length());
            //System.out.println((galaxy.get(1)).length());
            int size = 0;
            for(int i =0; i<galaxy.size();i++)
            {
                String line = galaxy.get(i);
                if(line.equals("$"))
                {
                    size+=999998;
                }
                StringBuilder builder = new StringBuilder(line);
                int index = builder.indexOf("#");
                while(index!=-1)
                {
                    indexRow.add(index);
                    int actualSize = i+size;
                    columNum.add(actualSize);
                    builder.setCharAt(index,'&');
                    index = builder.indexOf("#");
                }
            }
            galaxy.clear();
            List<Integer> distances = findDistance(indexRow,columNum);
            for(int item: distances)
            {
                total+=item;
            }
            System.out.println(total);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found");
        }

    }
    public static List<Integer> findEmptyColumn(List<String> galaxy)
    {
        List<String> columns = new ArrayList<>();
        List<Integer> emptyColumns = new ArrayList<>();
        int len = (galaxy.get(0)).length();
        for(int i = 0;i<len;i++)
        {
            String column="";
            for(int a =0; a<galaxy.size();a++)
            {
                String line = galaxy.get(a);
                column+=line.substring(i,i+1);
            }
            columns.add(column);
        }
        for(int i =0; i< columns.size();i++)
        {
            String line = columns.get(i);
            if(!line.contains("#"))
            {
                emptyColumns.add(i);
            }
        }
        return emptyColumns;
    }

    public static List<String> expandGalaxy(List<String>galaxy, List<Integer> column)
    {
        List<String> expandedGalaxy = new ArrayList<>();
        String inputString ="";
        for(int z =0;z<999999;z++)
        {
            inputString+=".";
            System.out.println(inputString.length());
        }
        for(int i =0; i<galaxy.size();i++)
        {
            String line = galaxy.get(i);
            int count=999999;
            for(int a =0;a<column.size();a++)
            {
                for(int b =0; b<1;b++)
                {
                    int indexChange = column.get(a);
                    if(a!=0)
                    {
                        int prev = column.get(a-1);
                        if(indexChange>prev){
                            indexChange+=count;
                            count+=999999;
                        }
                        else if(count>999999)
                        {
                            indexChange+=count;
                        }
                    }
                    line = line.substring(0,indexChange) + inputString + line.substring(indexChange);

                }
            }
            expandedGalaxy.add(line);
            if(!line.contains("#"))
            {
                for(int c =0; c<1;c++)
                {
                    expandedGalaxy.add("$");
                }
            }
        }
        return expandedGalaxy;
    }

    public static List<Integer> findDistance(List<Integer> row, List<Integer> column)
    {
        List<Integer> distance = new ArrayList<>();
        for(int i =0; i<row.size();i++)
        {
            int x1 = row.get(i);
            int y1 = column.get(i);
            for(int a =i+1;a<row.size();a++)
            {
                int x2 = row.get(a);
                int y2 = column.get(a);
                distance.add(Math.abs(x1-x2) + Math.abs(y1-y2));
            }
        }
        return distance;
    }


}
