public class Main {

    public static void main(String[] args) {
        SpellingBee bee = new SpellingBee();

        bee.promptForPuzzle();
        System.out.println(bee);

        bee.searchForAnswers();
        bee.displayAnswers();
    }
}
