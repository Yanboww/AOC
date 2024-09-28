package AOC2022.Day11;
import java.util.ArrayList;

public class Monkey {
    final private ArrayList<Long> items;
    final private String operation;
    final private int test;
    private int timesWithItems;
    final private int trueDestination;
    final private int falseDestination;
    final private int name;

    public Monkey(String operation, int test, int trueDestination, int falseDestination, int count)
    {
        items = new ArrayList<>();
        this.operation = operation;
        this.test = test;
        timesWithItems = 0;
        this.trueDestination = trueDestination;
        this.falseDestination = falseDestination;
        name = count;
    }

    public void addItem(long value)
    {
        items.add(value);
    }

    public long[] removeItem()
    {
        timesWithItems++;
        long[] result = new long[2];
        long worry =math();
        worry/=3;
        items.remove(0);
        long destination = findDestination(worry);
        result[0] = destination;
        result[1] = worry;
        /*System.out.println("name " + name);
        System.out.println(Arrays.toString(result));
        System.out.println(items);**/
        return result;
    }


    private long math()
    {
        long currentVal = items.get(0);
        if(operation.contains("*"))
        {
            if(operation.contains("old")) return currentVal*currentVal;
            else return currentVal*Integer.parseInt(operation.substring(2));
        }
        else{
            return currentVal + Integer.parseInt(operation.substring(2));
        }

    }

    private int findDestination(long worry)
    {
        if(worry%test == 0) return trueDestination;
        return falseDestination;
    }

    public int getTimesWithItems(){
        return timesWithItems;
    }

    public ArrayList<Long> getItems()
    {
        return items;
    }

    public long[] removeItem2(int mod)
    {
        timesWithItems++;
        long[] result = new long[2];
        long worry = math();
        worry = worry%mod;
        items.remove(0);
        long destination = findDestination(worry);
        result[0] = destination;
        result[1] = worry;
        return result;
    }




}
