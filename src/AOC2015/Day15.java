package AOC2015;
import java.util.*;
import java.io.*;
public class Day15 {
    public static void main(String[] args) {
        System.out.println(findHighestScore("inputs/input.txt", 1));
        System.out.println(findHighestScore("inputs/input.txt", 2));
    }

    public static long findHighestScore(String fileName, int part){
        ArrayList<int[]> ingredients = new ArrayList<>();
        try{
            Scanner s = new Scanner(new File(fileName));
            while(s.hasNextLine()){
                String[] line = s.nextLine().replace(",","").split(" ");
                int[] ingredient = new int[]{Integer.parseInt(line[2]),
                Integer.parseInt(line[4]), Integer.parseInt(line[6]), Integer.parseInt(line[8]),
                Integer.parseInt(line[10])};
                ingredients.add(ingredient);
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        return scoreCalc(ingredients, part);
    }

    public static long scoreCalc(ArrayList<int[]> ingredients, int part){
        long maxScore = 0;
        for(int s = 0; s <= 100; s++){
            for(int b = 0; b <= 100-s; b++){
                for(int choco = 0; choco <= 100-s-b; choco++){
                    int candy = 100-s-b-choco;
                    long score = calc(ingredients, new int[]{s,b,choco,candy}, part);
                    if(score > maxScore) maxScore = score;
                }
            }
        }
        return maxScore;
    }

    public static long calc(ArrayList<int[]> ingredients, int[] amounts, int part){
        int capa = 0; int dura = 0; int flavor = 0; int tex = 0; int calo = 0;
        for(int i = 0; i < ingredients.size(); i++){
            int[] ingredient = ingredients.get(i);
            int amount = amounts[i];
            capa += amount * ingredient[0];
            dura += amount * ingredient[1];
            flavor += amount * ingredient[2];
            tex += amount * ingredient[3];
            calo += amount * ingredient[4];
        }
        if(capa < 0) capa = 0; if(dura < 0) dura = 0;
        if (flavor < 0) flavor = 0; if (tex < 0) tex = 0;
        long score = (long)capa * dura * flavor * tex;
        if(part == 2 && calo != 500) return 0;
        return score;
    }

}
