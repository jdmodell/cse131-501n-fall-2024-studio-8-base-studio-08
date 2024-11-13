package studio8;

import java.util.Scanner;

public class Quiz {

	private Question[] questions;
	
	public Quiz(Question[] questions) {
		this.questions = questions;
	}
	
	private String getUserAnswer() {
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter your answer: ");
		String out = in.next();
		return out;
	}
	
	public int getTotalPoints() {
		int sum = 0;
		for(Question q : questions) {
			sum += q.getPoints();
		}
		return sum;
	}
	
	public void takeQuiz() {
		Scanner in = new Scanner(System.in);
		int totalEarnedPoints = 0;

        for (Question question : questions) {
            question.displayPrompt(); // Display the question prompt
            String userAnswer = getUserAnswer(); // Get user answer
            int pointsEarned = question.checkAnswer(userAnswer); // Check answer and get points
            totalEarnedPoints += pointsEarned; // Add points earned for this question
            System.out.println("Points earned for this question: " + pointsEarned);
        }

        System.out.println("Quiz completed! Total points earned: " + totalEarnedPoints + 
                           " out of " + getTotalPoints());

        in.close(); // Close Scanner resource
	}
	
	public static void main(String[] args) {
		Question q = new Question("What number studio is this?", "8", 2);

		String[] choices = {"seven", "nine", "eight", "six"};
		Question multipleChoice = new MultipleChoiceQuestion("What studio is this?", "3", 1, choices);

		choices = new String[] {"instance variables", "git", "methods", "eclipse"};
		Question selectAll = new SelectAllQuestion("Select all of the following that can be found within a class:", "13", choices);

		Question[] questions = {q, multipleChoice, selectAll}; //create and add more questions!
		
		Quiz studio8quiz = new Quiz(questions);
		studio8quiz.takeQuiz();
	}
}
