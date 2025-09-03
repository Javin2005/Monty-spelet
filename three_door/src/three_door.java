import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class three_door{

    public static void main(String[] args){
        
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);


    while (true) {
        System.out.println("vill du se statistik eller spela själv?: stats/spela");

        String ans = scanner.nextLine();

        if (ans.equals("stats")) {
            System.out.println("Hur många test?:");
            int nr = scanner.nextInt();
            stats(rand, nr);
            break;

        } else if (ans.equals("spela")) {
            play_game(scanner, rand);
            break;

        } else {
            System.out.println("Svara stats/spela");
        }
    }

    scanner.close();

    }

    public static ArrayList<Boolean> doors_gen(ArrayList<Boolean> a, Random rand) {

        int r = rand.nextInt(3);
        
        for(int i = 0; i < 3; i++){
            if(i == r){
                a.add(true);
            }
            else{a.add(false);} 
        }


        return a;
    }


    public static void play_game(Scanner scanner, Random rand){
        ArrayList<Boolean> doors = new ArrayList<>();
        doors_gen(doors, rand);
        System.out.println("Gissa dörr: ");

        int g = scanner.nextInt();
        Boolean guess = doors.get(g);
        
        doors.remove(g);

        
        int b = doors.indexOf(false);
        doors.remove(b);
        
        System.out.println("Vill du byta dörr? 0: nej, 1: ja");
        int a = scanner.nextInt();
        if (a == 0) {
            if (guess) {
                System.out.println("Grattis, du vann bilen!");
            } else {
                System.out.println("Tyvärr, du fick en get.");
            }
        } else {
            guess = doors.get(0);
            if (guess) {
                System.out.println("Grattis, du vann bilen!");
            } else {
                System.out.println("Tyvärr, du fick en get.");
            }
        }
    }

    public static void stats(Random rand, int nr){
       
        int[] wins = {0,0};
        int[] loss = {0,0};
        
        
        for(int i=0; i<nr;i++){
            ArrayList<Boolean> doors = new ArrayList<>();
            doors_gen(doors,rand);

            int g = rand.nextInt(3);
            Boolean guess = doors.get(g);
            doors.remove(g);
            
            // Ingen ändring
            if(guess){
                wins[0] = wins[0] + 1;
            }
            else{
                loss[0] = loss[0] + 1;
            }


            //ändring
            int b = doors.indexOf(false);
            doors.remove(b);

            guess = doors.get(0);

            if(guess){
                wins[1] = wins[1] + 1;
            }

            else{
                loss[1] = loss[1] + 1;
            }


        }


        double stayWinRate = (100.0 * wins[0]) / (wins[0] + loss[0]);
        double switchWinRate = (100.0 * wins[1]) / (wins[1] + loss[1]);

        System.out.printf("Win rate (stay): %.2f%%\n", stayWinRate);
        System.out.printf("Win rate (switch): %.2f%%\n", switchWinRate);

    }
}





