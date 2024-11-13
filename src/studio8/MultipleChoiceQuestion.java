package studio8;

public class MultipleChoiceQuestion extends Question {

    private String[] choices; // Array to hold the multiple-choice options

    // Constructor for MultipleChoiceQuestion
    public MultipleChoiceQuestion(String prompt, String answer, int points, String[] choices) {
        super(prompt, answer, points); // Call the superclass constructor
        this.choices = choices; // Initialize the specific field for choices
    }

    @Override
    public void displayPrompt() {
        super.displayPrompt(); // Call the base class's displayPrompt method to show the prompt
        // Display the multiple-choice options
        for (int i = 0; i < choices.length; i++) {
            System.out.println((i + 1) + ". " + choices[i]);
        }
    }

    public static void main(String[] args) {
        // Create choices for the multiple-choice question
        String[] choices = { "seven", "nine", "eight", "six" };
        // Create a MultipleChoiceQuestion object
        Question multipleChoice = new MultipleChoiceQuestion("What studio is this?", "3", 1, choices);
        
        // Test the displayPrompt and checkAnswer methods
        multipleChoice.displayPrompt(); // Display the prompt and choices
        System.out.println(multipleChoice.checkAnswer("hi")); // Incorrect answer, should return 0
        System.out.println(multipleChoice.checkAnswer("1")); // Incorrect answer, should return 0
        System.out.println(multipleChoice.checkAnswer("3")); // Correct answer, should return 1
    }
}
