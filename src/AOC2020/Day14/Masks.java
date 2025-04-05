package AOC2020.Day14;

import javax.swing.*;
import java.util.ArrayList;

public class Masks {
    private String[] maskArr;
    private ArrayList<Integer> memory;
    private ArrayList<Integer> calculations;

    public Masks(String mask){
        maskArr = mask.trim().split("(?!^)");
        calculations = new ArrayList<>();
        memory = new ArrayList<>();
    }

    public void updateMaskMemory(String line){
        memory.add(Integer.parseInt(line.substring(line.indexOf("[")+1,line.indexOf("]"))));
        calculations.add(Integer.parseInt(line.substring(line.lastIndexOf(" ")+1)));
    }

    public ArrayList<Integer> getMemory(){
        return memory;
    }

    public ArrayList<Integer> getCalculations() {
        return calculations;
    }

    public String[] getMaskArr(){
        return maskArr;
    }
}
