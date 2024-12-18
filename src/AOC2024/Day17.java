package AOC2024;
import java.io.*;
import java.util.*;
public class Day17 {

    private static final HashMap<Integer,String> combo = new HashMap<>();
    private static final ArrayList<Integer> commands = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println(findTotalOutPut("inputs/input.txt"));
        ArrayList<String> arr = new ArrayList<>();
        //System.out.println(reverseRegister());
        System.out.println(test(164516454365621L));

        /*for(long i = 0; i < 5; i++)
        {
            String test = test(i);
            if(test.matches("2(.+)?")) {
                System.out.println(i+"--------------");
                System.out.println(test);
            }

        }**/
        //System.out.println(findTotalOutPut("inputs/trial", 2));
    }

    public static String findTotalOutPut(String fileName)
    {
        File f = new File(fileName);
        HashMap<String,Integer> register = new HashMap<>();
        initializedCombo(combo, register);
        try{
            Scanner s = new Scanner(f);
            boolean command = false;
            while(s.hasNextLine()){
                String line = s.nextLine();
                if(command){
                    line = line.substring(line.indexOf(" ")+1);
                    String[] arr = line.split(",");
                    for(int i = 0; i < arr.length;i++) commands.add(Integer.parseInt(arr[i]));
                }
                else if(line.isEmpty()) command = true;
                else{
                    register.put(line.substring(line.indexOf(" ")+1,line.indexOf(":")),Integer.parseInt(line.substring(line.lastIndexOf(" ")+1)));
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found;");
        }
        ArrayList<String> saveAll = new ArrayList<>();
        String result ="";
        for(int i = 0; i < commands.size();i+=2)
        {
            int commandRun = commands.get(i);
            saveAll.add(commandRun + " "  + commands.get(i+1));
            if( commandRun == 3 && register.get("A") != 0) i = commands.get(i+1)-2;
            else {
                String add = execute(commandRun,commands.get(i+1),register,combo);
                if(!add.isEmpty())result+=add+",";
            }
        }
        //answer = 9 digits and saveAll.size = 72 which is 9*8
        System.out.println(saveAll);
        return result.substring(0,result.length()-1);
    }

    public static String execute(int command, int input, HashMap<String,Integer> register, HashMap<Integer,String> combo)
    {
        String result = "";
        if(command == 0){
            int A = register.get("A");
            A = (int)(A/(Math.pow(2,register.get(combo.get(input)))));
            register.put("A",A);
        }
        else if(command == 1)
        {
            int B = register.get("B");
            B = B^input;
            register.put("B",B);
        }
        else if(command == 2)
        {
            int B = (register.get(combo.get(input)))%8;
            register.put("B",B);
        }
        else if(command == 4)
        {
            int B = register.get("B");
            int C = register.get("C");
            B = B^C;
            register.put("B",B);
        }
        else if(command == 5)
        {
            int calc = register.get(combo.get(input))%8;
            result+=calc;
        }
        else if(command == 6)
        {
            int A = register.get("A");
            A = (int)(A/(Math.pow(2,register.get(combo.get(input)))));
            register.put("B",A);
        }
        else if(command == 7)
        {
            int A = register.get("A");
            A = (int)(A/(Math.pow(2,register.get(combo.get(input)))));
            register.put("C",A);
        }
        return result;
    }

   public static void reverseRegister(){
        ArrayList<Long> possible = new ArrayList<>();

    }

    public static String totalExecute(int a, int b, int c)
    {
        HashMap<String,Integer> register = new HashMap<>();
        register.put("A",a);
        register.put("B",b);
        register.put("C",c);
        register.put("0",0);
        register.put("1",1);
        register.put("2",2);
        register.put("3",3);
        String result ="";
        for(int i = 0; i < commands.size();i+=2)
        {
            int commandRun = commands.get(i);
            if( commandRun == 3 && register.get("A") != 0) i = commands.get(i+1)-2;
            else {
                String add = execute(commandRun,commands.get(i+1),register,combo);
                if(!add.isEmpty())result+=add+",";
            }
        }
        return result;
    }

    public static void initializedCombo(HashMap<Integer,String> combo, HashMap<String,Integer> register)
    {
        combo.put(0,"0");
        combo.put(1,"1");
        combo.put(2,"2");
        combo.put(3,"3");
        combo.put(4,"A");
        combo.put(5,"B");
        combo.put(6,"C");
        combo.put(7,"nothing");
        register.put("0",0);
        register.put("1",1);
        register.put("2",2);
        register.put("3",3);
    }


    public static String test(long A)
    {
        HashMap<String,Long> register = new HashMap<>();
        register.put("A",A);
        register.put("B",0L);
        register.put("C",0L);
        register.put("0",0L);
        register.put("1",1L);
        register.put("2",2L);
        register.put("3",3L);
        String result ="";
        for(int i = 0; i < commands.size();i+=2)
        {
            int commandRun = commands.get(i);
            if( commandRun == 3 && register.get("A") != 0) i = commands.get(i+1)-2;
            else {
                String add = execute2(commandRun,commands.get(i+1),register,combo);
                if(!add.isEmpty())result+=add+",";
            }
        }
        //answer = 9 digits and saveAll.size = 72 which is 9*8
        return result.substring(0,result.length()-1);
    }

    public static String execute2(int command, int input, HashMap<String,Long> register, HashMap<Integer,String> combo)
    {
        String result = "";
        if(command == 0){
            long A = register.get("A");
            A = (long)(A/(Math.pow(2,register.get(combo.get(input)))));
            register.put("A",A);
        }
        else if(command == 1)
        {
            long B = register.get("B");
            B = B^input;
            register.put("B",B);
        }
        else if(command == 2)
        {
            long B = (register.get(combo.get(input)))%8;
            register.put("B",B);
        }
        else if(command == 4)
        {
            long B = register.get("B");
            long C = register.get("C");
            B = B^C;
            register.put("B",B);
        }
        else if(command == 5)
        {
            long calc = register.get(combo.get(input))%8;
            result+=calc;
        }
        else if(command == 6)
        {
            long A = register.get("A");
            A = (long)(A/(Math.pow(2,register.get(combo.get(input)))));
            register.put("B",A);
        }
        else if(command == 7)
        {
            long A = register.get("A");
            A = (long)(A/(Math.pow(2,register.get(combo.get(input)))));
            register.put("C",A);
        }
        return result;
    }

}
