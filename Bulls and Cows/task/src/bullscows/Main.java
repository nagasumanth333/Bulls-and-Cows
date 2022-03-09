package bullscows;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

class Game{

    public static int bulls = 0;
    public static int cows = 0;

    public static void checkBulls(String input, String code){
        for(int i=0;i<input.length();i++){
            if((input.charAt(i)==code.charAt(i)))
                bulls++;
        }
    }

    public static void checkCows(String input, String secret){

        for(int i=0;i<input.length();i++){
            for(int j=0;j<secret.length();j++){
                if((input.charAt(i)==secret.charAt(j)) && (i!=j))
                    cows++;
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {

        ArrayList<Integer> secret = new ArrayList<>();
        Random random = new Random();
        boolean flag = true;
        boolean flag2 = true;
        boolean flag5=true;
        Scanner sc = new Scanner(System.in);
        int count=0;
        int totalSize=0;
        String code="";

        while(flag2) {
            System.out.println("Input the length of the secret code:");
            String temp4 = sc.next();
            int count1=0;
            try{
                count1=Integer.parseInt(temp4);
            } catch (Exception e){
                System.out.println("Error: "+temp4+" isn't a valid number.");
                flag5 =false;
                break;
            }

            if(count1==0) {
                flag5 = false;
                System.out.println("Error:");
                break;
            }
            System.out.println("Input the number of possible symbols in the code:");
            int totalLength=0;

            String temp2 = sc.next();

            try {
                totalLength=Integer.parseInt(temp2);

            } catch(Exception e){
                System.out.println("Error: "+temp2+" isn't a valid number.");
                flag5 =false;
                break;
            }

            if(count1 > totalLength) {
                System.out.println("Error: it's not possible to generate a code with a length of " + totalLength + " with " + count1 + " unique symbols.");
                flag5 =false;
                break;
            } else if (totalLength > 36) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                flag5 =false;
                break;
            }
            else {
                flag2 = false;
                count=count1;
                totalSize=totalLength;
            }
        }

        if(flag5==true) {

            if (totalSize > 10) {
                while (count > 1) {
                    int a = random.nextInt(9);
                    String temp = Integer.toString(a);
                    if ((a > 0 && a < 10) && (!code.contains(temp))) {
                        code += temp;
                        count--;
                    }
                }
            } else {
                while (count > 0) {
                    int a = random.nextInt(9);
                    String temp = Integer.toString(a);
                    if ((a > 0 && a < 10) && (!code.contains(temp))) {
                        code += temp;
                        count--;
                    }
                }
            }


            if (totalSize > 10) {
                int randomNo = random.nextInt(totalSize - 10);
                ArrayList<Character> ch = new ArrayList<>();
                for (char cha = 'a'; cha <= 'z'; cha++) {
                    ch.add(cha);
                }
                char answer = ch.get(randomNo);
                code += answer;
            }
            int temp2 = 0;
            char res = 'a';

            System.out.print("The secret is prepared: ");
            for (int i = 0; i < code.length(); i++) {
                System.out.print("*");
            }
            if (totalSize > 10) {
                char ch2 = 'a';
                for (int i = 2; i < 37; i++) {
                    ch2++;
                    if (totalSize - 10 == i)
                        res = ch2;
                }
            }
            if (totalSize < 10)
                temp2 = totalSize - 1;
            else
                temp2 = 9;

            if (totalSize < 11)
                System.out.println(" (0-" + temp2 + ")");
            else if (totalSize == 11)
                System.out.println(" (0-" + temp2 + ",a).");
            else
                System.out.println(" (0-" + temp2 + ",a-" + res + ").");

            System.out.println("Okay, let's start a game!");
        }


        boolean flag3=true;
        int counter=1;

        while(flag3==true && flag5==true) {

            System.out.println("Turn "+counter+":" );
            String input = sc.next();
            Game.checkBulls(input,code);
            Game.checkCows(input,code);
            counter++;


            if (Game.bulls == 0 && Game.cows != 0)
                System.out.println("Grade: " + Game.cows + " cow(s).");
            else if (Game.bulls != 0 && Game.cows == 0)
                System.out.println("Grade: " + Game.bulls + " bull(s).");
            else if (Game.bulls == 0 && Game.cows == 0)
                System.out.println("Grade: None.");
            else
                System.out.println("Grade: " + Game.bulls + " bull and " + Game.cows + " cow.");

            if(Game.bulls==input.length()) {
                flag3 = false;
                System.out.println("Congratulations! You guessed the secret code.");
            }
            Game.bulls=0;
            Game.cows=0;
        }
    }
}


