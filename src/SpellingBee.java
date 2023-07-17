import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SpellingBee {

    private String puzzle = "";
    private ArrayList<String> answers = new ArrayList<>();



    public void promptForPuzzle() {
        Scanner sc = new Scanner(System.in);

        while (!isValidPuzzle(puzzle)) {
            System.out.print("puzzle? ");
            puzzle = sc.nextLine();
        }
    }


// Makes sure entered puzzle is valid
    private boolean isValidPuzzle(String possiblePuzzle) {

        if (possiblePuzzle.length() != 7) {
            return false;
        }

        for (int i = 0; i < possiblePuzzle.length(); i++) {
            if (possiblePuzzle.charAt(i) < 'a' || possiblePuzzle.charAt(i) > 'z') {
                return false;

            }
        }

        for (int i = 0; i < possiblePuzzle.length(); i++) {
            for (int j = i + 1; j < possiblePuzzle.length(); j++) {
                if (possiblePuzzle.charAt(i) == possiblePuzzle.charAt(j)) {
                    return false;
                }

            }

        }

        return true;
    }


// Searches for possible answers to puzzle
    public void searchForAnswers() {
        File file = new File("EnglishWords.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Invalid file name");
            System.exit(0);
        }

        while (sc.hasNext()) {
            String word = sc.nextLine();
            boolean isValid = true;
            if (word.length() <= 3) {
                isValid = false;
            }
            if (!word.contains("" + puzzle.charAt(0))) {
                isValid = false;
            }
            for (int i = 0; i < word.length(); i++) {
                String letter = "" + word.charAt(i);
                if (!puzzle.contains(letter)) {
                    isValid = false;

                }
            }

            if (isValid == true) {
                answers.add(word);
            }

        }
    }



    public void displayAnswers() {
        int number = answers.size();
        System.out.println(number + " words:");
        for (int i = 0; i < answers.size(); i++) {
            int Points = 0;

            if(answers.get(i).length() == 4) {
                Points = 1;
            }
            else{
                Points = Points + answers.get(i).length();
            }

            if(isPangram(answers.get(i))) {
                System.out.println(Points + " - " + answers.get(i) + " *");
            }
            else{
                System.out.println(Points + " - " + answers.get(i));
            }
        }
    }


// This is for puzzle printing beehive shape
    public String toString() {

        System.out.println("   " + puzzle.charAt(2) + "   ");
        System.out.println(puzzle.charAt(3) + "     " + puzzle.charAt(1));
        System.out.println("   " + puzzle.charAt(0) + "   ");
        System.out.println(puzzle.charAt(4) + "     " + puzzle.charAt(6));
        System.out.println("   " + puzzle.charAt(5) + "   ");

        return puzzle;
    }

    //Decides whether word is pangram
    private boolean isPangram(String word) {
        for (int i = 0; i < puzzle.length(); i++) {
            char Char = puzzle.charAt(i);

            if (!word.contains("" + Char)) {
                return false;
            }

        }
        return true;
    }
}