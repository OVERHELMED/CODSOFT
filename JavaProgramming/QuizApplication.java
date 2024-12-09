import java.util.Scanner;
import java.util.concurrent.*;

class QuizApplication {
    static class Question {
        String questionText;
        String[] options;
        int correctAnswer;

        public Question(String questionText, String[] options, int correctAnswer) {
            this.questionText = questionText;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Technical Java Quiz Questions
        Question[] questions = {
                new Question("What is the output of the following code? int a = 5; System.out.println(a++ + ++a);",
                        new String[] { "1. 10", "2. 11", "3. 12", "4. Compilation Error" }, 2),
                new Question("Which of the following is NOT a valid way to create a thread in Java?",
                        new String[] { "1. Extending Thread class", "2. Implementing Runnable interface",
                                "3. Using ExecutorService", "4. Using synchronized keyword" },
                        4),
                new Question("What is the time complexity of accessing an element in a HashMap?",
                        new String[] { "1. O(1)", "2. O(n)", "3. O(log n)", "4. O(n^2)" }, 1),
                new Question("Which method is used to start a thread in Java?",
                        new String[] { "1. run()", "2. start()", "3. execute()", "4. begin()" }, 2),
                new Question("In Java, what is the difference between == and .equals()?",
                        new String[] { "1. == compares references, .equals compares values",
                                "2. == compares values, .equals compares references",
                                "3. Both are used for reference comparison",
                                "4. There is no difference" },
                        1)
        };

        int score = 0;
        ExecutorService executor = Executors.newSingleThreadExecutor();

        System.out.println("Welcome to the Advanced Java Quiz!");
        System.out.println("You have 10 seconds to answer each question.\n");

        for (int i = 0; i < questions.length; i++) {
            Question q = questions[i];
            System.out.println("Question " + (i + 1) + ": " + q.questionText);

            for (String option : q.options) {
                System.out.println(option);
            }

            Future<Integer> future = executor.submit(() -> {
                System.out.print("Enter your answer (1-4): ");
                return scanner.nextInt();
            });

            try {
                int answer = future.get(10, TimeUnit.SECONDS);
                if (answer == q.correctAnswer) {
                    System.out.println("Correct!\n");
                    score++;
                } else {
                    System.out.println("Incorrect. The correct answer was: " + q.correctAnswer + "\n");
                }
            } catch (TimeoutException e) {
                System.out.println("\nTime's up! Moving to the next question.\n");
                future.cancel(true);
            } catch (Exception e) {
                System.out.println("\nInvalid input. Moving to the next question.\n");
            }
        }

        executor.shutdown();
        System.out.println("\nQuiz Over!");
        System.out.println("Your final score is: " + score + " out of " + questions.length);
        scanner.close();
    }
}
