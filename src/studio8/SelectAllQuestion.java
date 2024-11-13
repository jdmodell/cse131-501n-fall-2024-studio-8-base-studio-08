package studio8;

public class SelectAllQuestion extends MultipleChoiceQuestion {

    public SelectAllQuestion(String prompt, String answer, String[] choices) {
        super(prompt, answer, choices.length, choices); // Call the superclass constructor
    }

    @Override
    public int checkAnswer(String givenAnswer) {
        // Calculate missing correct answers and incorrect given answers
        int missingCorrectAnswers = findMissingCorrectAnswers(givenAnswer);
        int incorrectGivenAnswers = findIncorrectGivenAnswers(givenAnswer);

        // Calculate partial credit: Total possible points minus deductions
        int partialCredit = this.getPoints() - missingCorrectAnswers - incorrectGivenAnswers;

        // Ensure the score is not negative
        return Math.max(partialCredit, 0);
    }

    private int findMissingCorrectAnswers(String givenAnswer) {
        String answer = this.getAnswer();
        return findMissingCharacters(givenAnswer, answer); // How many correct answers are missing
    }

    private int findIncorrectGivenAnswers(String givenAnswer) {
        String answer = this.getAnswer();
        return findMissingCharacters(answer, givenAnswer); // How many incorrect answers are included
    }

    /*
     * Returns the number of characters in toCheck that are missing from the
     * baseString. For example, findMissingCharacters("hi", "hoi") would return 1,
     * as 'o' is not in baseString.
     */
    private static int findMissingCharacters(String baseString, String toCheck) {
        int missingValues = 0;
        for (int i = 0; i < toCheck.length(); i++) {
            char characterToLocate = toCheck.charAt(i);
            if (baseString.indexOf(characterToLocate) == -1) { // Character not in baseString
                missingValues++;
            }
        }
        return missingValues;
    }

    public static void main(String[] args) {
        String[] choices = { "instance variables", "git", "methods", "eclipse" };
        Question selectAll = new SelectAllQuestion(
            "Select all of the following that can be found within a class:",
            "13",
            choices
        );
        selectAll.displayPrompt();
        System.out.println(selectAll.checkAnswer("hi")); // No credit (0 points)
        System.out.println(selectAll.checkAnswer("2")); // Partial credit (1 point)
        System.out.println(selectAll.checkAnswer("13")); // Full credit (4 points)
        System.out.println(selectAll.checkAnswer("31")); // Full credit (4 points)
        System.out.println(selectAll.checkAnswer("1")); // Partial credit (3 points)
        System.out.println(selectAll.checkAnswer("3")); // Partial credit (3 points)
        System.out.println(selectAll.checkAnswer("23")); // Partial credit (2 points)
        System.out.println(selectAll.checkAnswer("34")); // Partial credit (2 points)
        System.out.println(selectAll.checkAnswer("4")); // Partial credit (1 point)
        System.out.println(selectAll.checkAnswer("124")); // Partial credit (1 point)
        System.out.println(selectAll.checkAnswer("24")); // No credit (0 points)
    }
}
