package AOC2024;
import java.io.*;
import java.util.*;
public class Day23 {
    public static void main(String[] args)
    {
        System.out.println(interconnected3("inputs/input.txt",1));
        System.out.println(interconnected3("inputs/input.txt",2));
    }

    public static String interconnected3(String fileName, int part)
    {
        File f = new File(fileName);
        ArrayList<String> connections = new ArrayList<>();
        ArrayList<String> interconnected = new ArrayList<>();
        try{
            Scanner s = new Scanner(f);
            while(s.hasNextLine()) connections.add(s.nextLine());
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        for(String connection : connections)
        {
            if(part == 1) findConnections(connection,connections,interconnected);
            else findBigConnections(connection,connections,interconnected);
        }
        if(part == 1) return Integer.toString(countT(interconnected));
        else return passwordFinder(interconnected);
    }

    public static void findConnections(String connection, ArrayList<String> connections, ArrayList<String> interconnected)
    {
        String pc1 = connection.substring(0,connection.indexOf("-"));
        String pc2 = connection.substring(connection.indexOf("-")+1);
        ArrayList<String> pc1s = new ArrayList<>();
        ArrayList<String> pc2s = new ArrayList<>();
        for(String connectionTest : connections)
        {
            if(connectionTest.contains(pc1)) pc1s.add(connectionTest);
            else if(connectionTest.contains(pc2)) pc2s.add(connectionTest);
        }
        for(String pc3s : pc1s)
        {
            String[] pc3Comps = pc3s.split("-");
            String pc3 = pc3Comps[0];
            if(pc3.equals(pc1)) pc3 = pc3Comps[1];
            if(pc2s.contains(pc2+"-"+pc3) || pc2.contains(pc3+"-"+pc2))
            {
                if(!interconnected.contains(pc1+","+pc2+","+pc3) && !interconnected.contains(pc1+","+pc3+","+pc2) && !interconnected.contains(pc2+","+pc1+","+pc3) && !interconnected.contains(pc2+","+pc3+","+pc1) && !interconnected.contains(pc3+","+pc2+","+pc1) && !interconnected.contains(pc3+","+pc1+","+pc2)){
                    interconnected.add(pc1+","+pc2+","+pc3);
                }
            }
        }
    }

    public static void findBigConnections(String connection, ArrayList<String> connections, ArrayList<String> interconnected)
    {
        String pc1 = connection.substring(0,connection.indexOf("-"));
        String pc2 = connection.substring(connection.indexOf("-")+1);
        ArrayList<String> pc2s = new ArrayList<>();
        for(String connectionTest : connections)
        {
            if(connectionTest.contains(pc2)) pc2s.add(connectionTest);
        }
        String temp = pc2+","+pc1;
        for(String pc3s : pc2s)
        {
            String[] pc3Comps = pc3s.split("-");
            String pc3 = pc3Comps[0];
            if(pc3.equals(pc2)) pc3 = pc3Comps[1];
            String[] tempArr = temp.split(",");
            boolean can = true;
            for(String last : tempArr)
            {
                if(!connections.contains(last+"-"+pc3) && !connections.contains(pc3+"-"+last)){
                    can = false;
                    break;
                }
            }
            if(can) temp+=","+pc3;
        }
        if(temp.length()> 3) interconnected.add(temp);
    }

    public static int countT(ArrayList<String> arr)
    {
       // System.out.println(arr);
        //System.out.println(arr.size());
        int count = 0;
        for(String val : arr)
        {
            String[] temp = val.split(",");
            for(String val2 : temp)
            {
                if(val2.startsWith("t")){
                    //System.out.println(val);
                    count++;
                    break;
                }
            }
        }
        return  count;
    }

    public static String passwordFinder(ArrayList<String> connections)
    {
        int length = connections.get(0).length();
        String pass = connections.get(0);
        for(int i = 1; i < connections.size(); i++)
        {
            String val = connections.get(i);
            if(length < val.length()){
                pass = val;
                length = val.length();
            }
        }
        String[] passTemp = pass.split(",");
        List<String> tempPass = Arrays.asList(passTemp);
        Collections.sort(tempPass);
        pass = "";
        for(String val : tempPass) pass+=val+",";
        return pass.substring(0,pass.length()-1);
    }

}
