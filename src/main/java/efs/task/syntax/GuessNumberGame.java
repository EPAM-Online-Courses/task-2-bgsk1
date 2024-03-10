package efs.task.syntax;

import java.util.Scanner;
import java.util.Random;
import java.lang.Math;

public class GuessNumberGame {
    private final int M;

    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) {
        try {
            M = Integer.parseInt(argument); // conversion string to int
        } catch (NumberFormatException e) {
            System.out.println("'" + argument + "'" + " to " + UsefulConstants.WRONG_ARGUMENT + " - to nie liczba");
            throw new IllegalArgumentException(e);
        }
        if (M > UsefulConstants.MAX_UPPER_BOUND || M < 1) {
            System.out.println(M + " to " + UsefulConstants.WRONG_ARGUMENT + " - jest spoza zakresu <1, " + UsefulConstants.MAX_UPPER_BOUND + ">");
            throw new IllegalArgumentException();
        }
    }
    public void play() {
        System.out.println("Zagrajmy. Zgadnij liczbę z zakresu <1," + M + ">"); // range of numbers

        Random random = new Random();
        int randomNumber = random.nextInt(M) + 1; // generate random number <1,M>
        int L = (int) Math.abs((Math.log10(M)/Math.log10(2)) + 1); // calculate formula


        boolean guessedNumber = false;
        int counter = 0;
        do {
            int pickedNumber;
            progressBar(counter, L);

            System.out.println(UsefulConstants.GIVE_ME + " liczbę :");
            Scanner scanner = new Scanner(System.in);

            String userNumber = scanner.next();
            try {
                pickedNumber = Integer.parseInt(userNumber);
            } catch (NumberFormatException e) {
                ++counter;
                System.out.println(UsefulConstants.NOT_A_NUMBER);
                continue;
            }
            if (pickedNumber > randomNumber) {
                System.out.println("To " + UsefulConstants.TO_MUCH);
                ++counter;
            } else if (pickedNumber < randomNumber) {
                System.out.println("To " + UsefulConstants.TO_LESS);
                ++counter;
            } else {
                ++counter;
                System.out.println(UsefulConstants.YES);
                System.out.println(UsefulConstants.CONGRATULATIONS + ", " + counter + " - tyle prób zajęło Ci odgadnięcie liczby " + randomNumber);
                guessedNumber = true;
                break;
            }
            } while (counter + 1 < L + 1);

            if(!guessedNumber)
            {
                System.out.println(UsefulConstants.UNFORTUNATELY + ", wyczerpałeś limit prób (" + L + ") do odgadnięcia liczby " + randomNumber);
            }
    }
    public void progressBar(int failedTrials, int maxNumberOfTrials) {
        System.out.print("Twoje próby: [");
        for(int i = 0; i < failedTrials + 1; i++) {
            System.out.print("*");
        }
        for (int i = 0; i < maxNumberOfTrials - failedTrials - 1; i++) {
            System.out.print(".");
        }
        System.out.println("]");
    }


}
