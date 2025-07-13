package AOC2021.Day16;
import java.io.*;
import java.util.*;
public class Day16 {
    private static final HashMap<String,String> binaryHex = new HashMap<>();
    private static String binaryCode;
    public static void main(String[] args){
        setMap();
        System.out.println(parseHex("inputs/input.txt",1));
        System.out.println(parseHex("inputs/input.txt",2));
    }

    public static long parseHex(String fileName, int part){
        File f = new File(fileName);
        String code;
        try{
            Scanner s = new Scanner(f);
            code = s.nextLine();
        }
        catch (FileNotFoundException e){
            code = "N/A";
            System.out.println("File not found");
        }
        if(part == 1) return versionNum(code);
        return packetVal(code);
    }

   public static int versionNum(String code){
        String binaryCode = convertToBinary(code);
        int count = 0;
        int num = 0;
        while(!binaryCode.isEmpty()){
            try{
                num = Integer.parseInt(binaryHex.get("0"+binaryCode.substring(0,3)));
                count += num;
                int version = Integer.parseInt(binaryHex.get("0"+binaryCode.substring(3,6)));
                if(version == 4){
                    binaryCode = binaryCode.substring(6);
                    while(binaryCode.charAt(0) == '1'){
                        binaryCode = binaryCode.substring(5);
                    }
                    binaryCode = binaryCode.substring(5);
                }
                else{
                    String operator = binaryCode.substring(6,7);
                    if(operator.equals("1")) binaryCode = binaryCode.substring(18);
                    else binaryCode = binaryCode.substring(22);
                }
                num = 0;
            }
            catch (StringIndexOutOfBoundsException e){
                count -= num;
                binaryCode = "";
            }
        }
        return count;
    }
    public static long packetVal(String code){
        binaryCode = convertToBinary(code);
        Stuff file = structure(null);
        return calculate(file);
    }

    public static Stuff structure(Stuff previous){
        Stuff current = null;
        while(!binaryCode.isEmpty()){
            String binaryCode1 = binaryCode;
            int id;
            int length = 6;
            long val;
            try{
                id = Integer.parseInt(binaryCode1.substring(3,6),2);
                if(id == 4){
                    binaryCode1 = binaryCode1.substring(6);
                    StringBuilder total = new StringBuilder();
                    while(binaryCode1.charAt(0) == '1'){
                        total.append(binaryCode1, 1, 5);
                        binaryCode1 = binaryCode1.substring(5);
                        length+=5;
                    }
                    total.append(binaryCode1, 1, 5);
                    val = Long.parseLong(total.toString(),2);
                    length+=5;
                    binaryCode1 = binaryCode1.substring(5);
                    current = new Stuff(id,val,"0",length);
                    if(previous != null){
                        if(previous.getLengthType().equals("0")){
                            if(previous.getArrayLength() + current.getBitLength() <= previous.getVal()){
                                binaryCode = binaryCode1;
                                previous.setArray(current);
                            }
                            else break;
                        }
                        else {
                            if(previous.getArray().size() < previous.getVal()){
                                binaryCode = binaryCode1;
                                previous.setArray(current);
                            }
                            else break;
                        }
                    }
                    else break;
                }
                else{
                    String operator = binaryCode1.substring(6,7);
                    if(operator.equals("1")){
                        val = Long.parseLong(binaryCode1.substring(7,18),2);
                        length+=11;
                        binaryCode1 = binaryCode1.substring(18);
                    }
                    else{
                        val = Long.parseLong(binaryCode1.substring(7,22),2);
                        length+=15;
                        binaryCode1 = binaryCode1.substring(22);
                    }
                    length++;
                    current = new Stuff(id,val,operator,length);
                    if(previous != null){
                        String temp = binaryCode;
                        binaryCode = binaryCode1;
                        structure(current);
                        if(previous.getLengthType().equals("0")){
                            if(previous.getArrayLength() + current.getBitLength() +current.getArrayLength() <= previous.getVal()){
                                previous.setArray(current);
                            }
                            else{
                                binaryCode = temp;
                                break;
                            }
                        }
                        else {
                            if(previous.getArray().size() < previous.getVal()){
                                previous.setArray(current);
                            }
                            else{
                                binaryCode = temp;
                                break;
                            }
                        }
                    }
                    else{
                        binaryCode = binaryCode1;
                        structure(current);
                    }
                }
            }
            catch (StringIndexOutOfBoundsException e){
                binaryCode = "";
            }
        }
        return current;
    }

    public static void setMap(){
        binaryHex.put("0000","0");binaryHex.put("0001","1");binaryHex.put("0010","2");binaryHex.put("0011","3");
        binaryHex.put("0100","4");binaryHex.put("0101","5");binaryHex.put("0110","6");binaryHex.put("0111","7");
        binaryHex.put("1000","8");binaryHex.put("1001","9");binaryHex.put("1010","10");binaryHex.put("1011","11");
        binaryHex.put("1100","12");binaryHex.put("1101","13");binaryHex.put("1110","14");binaryHex.put("1111","15");

        binaryHex.put("0","0000");binaryHex.put("1","0001");binaryHex.put("2","0010");binaryHex.put("3","0011");
        binaryHex.put("4","0100");binaryHex.put("5","0101");binaryHex.put("6","0110");binaryHex.put("7","0111");
        binaryHex.put("8","1000");binaryHex.put("9","1001");binaryHex.put("A","1010");binaryHex.put("B","1011");
        binaryHex.put("C","1100");binaryHex.put("D","1101");binaryHex.put("E","1110");binaryHex.put("F","1111");

    }

    public static String convertToBinary(String code){
        StringBuilder newCode = new StringBuilder();
        for(int i = 0; i < code.length(); i++) newCode.append(binaryHex.get(code.substring(i,i+1)));
        return newCode.toString();
    }

    public static long calculate(Stuff file){
        if(file.getId() == 4){
            return file.getVal();
        }
        else if(file.getId() == 0){
            long total;
            total = 0;
            for(Stuff files : file.getArray()) total+=calculate(files);
            return total;
        }
        else if(file.getId() == 1){
            long total;
            total = 1;
            for(Stuff files : file.getArray()) total*=calculate(files);
            return total;
        }
        else if(file.getId() == 2){
            long smallest = calculate(file.getArray().get(0));
            for(int i = 1; i < file.getArray().size(); i++){
                long val = calculate(file.getArray().get(i));
                if(smallest > val) smallest = val;
            }
            return smallest;
        }
        else if(file.getId() == 3){
            long max = calculate(file.getArray().get(0));
            for(int i = 1; i < file.getArray().size(); i++){
                long val = calculate(file.getArray().get(i));
                if(max < val) max = val;
            }
            return max;
        }
        else if(file.getId() == 5){
            long val1 = calculate(file.getArray().get(0));
            long val2 = calculate(file.getArray().get(1));
            if(val1 > val2) return 1;
            return 0;
        }
        else if(file.getId() == 6){
            long val1 = calculate(file.getArray().get(0));
            long val2 = calculate(file.getArray().get(1));
            if(val1 < val2) return 1;
            return 0;
        }
        else{
            long val1 = calculate(file.getArray().get(0));
            long val2 = calculate(file.getArray().get(1));
            if(val1 == val2) return 1;
            return 0;
        }
    }
}
