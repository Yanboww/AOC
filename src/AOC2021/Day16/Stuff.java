package AOC2021.Day16;

import java.util.ArrayList;

public class Stuff {
    private final int id;
    private final long val;
    private final int bitLength;
    private final String lengthType;
    private final ArrayList<Stuff> array;


    private int arrayLength;
    public Stuff(int id, long val, String lengthType, int bitLength){
        this.id = id;
        this.val = val;
        this.lengthType = lengthType;
        this.bitLength = bitLength;
        array = new ArrayList<>();
        arrayLength = 0;
    }

    public int getId() {
        return id;
    }

    public long getVal() {
        return val;
    }

    public String getLengthType() {
        return lengthType;
    }


    public ArrayList<Stuff> getArray() {
        return array;
    }

    public void setArray(Stuff file) {
        array.add(file);
        arrayLength += file.bitLength;
        if(file.getArrayLength() > 0) arrayLength+= file.getArrayLength();
    }

    public int getArrayLength() {
        return arrayLength;
    }

    public int getBitLength(){
        return bitLength;
    }

}
