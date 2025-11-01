package AOC2015.Day14;

public class Reindeer {
    private final int speed; private final int burstTime;
    private final int restTime; private boolean isResting;
    private int rested; private int currentDistance;

    public Reindeer(int speed, int burstTime, int restTime){
        this.speed = speed;
        this.burstTime = burstTime;
        this.restTime = restTime;
    }

    public int incrementSecond(){
        if(isResting){
            rested++;
            if(rested == restTime){
                isResting = false;
                rested = 0;
            }
        }
        else{
            currentDistance+=speed;
            rested ++;
            if(rested == burstTime){
                isResting = true;
                rested = 0;
            }
        }
        return currentDistance;
    }

    public  int getSpeed(){return speed;}
    public int getBurstTime(){return burstTime;}
    public int getRestTime(){return restTime;}
}
