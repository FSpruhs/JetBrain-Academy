package main.java.LearingProgressTracker;

import java.util.*;

public class TrackerMenu {

    private final Scanner scanner = new Scanner(System.in);
    private Database db = new Database();

    public void startTrackerMenu() {
        String input = "";
        System.out.println("Learning Progress Tracker");
        while (!input.equals("exit")) {
            input = scanner.nextLine();

            switch (input.strip()) {
                case "exit":
                    System.out.println("Bye!");
                    break;
                case "":
                    System.out.println("No input.");
                    break;
                case "add students":
                    startAddStudentMenu();
                    break;
                case "back":
                    System.out.println("Enter 'exit' to exit the program.");
                    break;
                case "list":
                    db.showList();
                    break;
                case "add points":
                    startAddPointsMenu();
                    break;
                case "find":
                    startFindMenu();
                    break;
                case "statistics":
                    startStatisticsMenu();
                    break;
                case "notify":
                    notifyStudents();
                    break;
                default:
                    System.out.println("Error: Unknown command!");
                    break;
            }
        }
    }

    private void notifyStudents() {
        ArrayList<Integer> studentsNotified = new ArrayList<>();
        int tracker = 0;
        for (Map.Entry<String, Course> entry : db.course.entrySet()) {
            ArrayList<Integer> students = entry.getValue().studentsAccomplished();
            for (int studentID: students) {
                Student student = db.getStudents(studentID);
                student.sendMessage(entry.getKey());
                if (!studentsNotified.contains(studentID)) {
                    tracker++;
                    studentsNotified.add(studentID);
                }
            }
        }
        System.out.println("Total " + tracker + " students have been notified.");
    }

    private void startStatisticsMenu() {
        String input = "";
        System.out.println("Type the name of a course to see details or 'back' to quit:");
        db.mostPopular();
        db.leastPopular();
        db.highestActivity();
        db.lowestActivity();
        db.easiestCourse();
        db.hardestCourse();
        while (!input.equals("back")) {
            input = scanner.nextLine().strip();
            if (input.equals("back")) {
                return;
            }
            if (input.equals("Java") || input.equals("DSA") || input.equals("Databases")
                    || input.equals("Spring")) {
                Course course = db.course.get(input);
                System.out.println(input);
                course.printStatistic();
            } else {
                System.out.println("Unknown course.");
            }
        }
    }

    private void startFindMenu() {
        String input = "";
        System.out.println("Enter an id or 'back' to return:");
        while (!input.equals("back")) {
            input = scanner.nextLine().strip();
            if (!input.equals("back")) {
                db.findStudent(input);
            }
        }
    }

    private void startAddPointsMenu() {
        String input = "";
        System.out.println("Enter an id and points or 'back' to return:");
        while (!input.equals("back")) {
            input = scanner.nextLine().strip();
            if (!input.equals("back")) {
                db.addPoints(input);
            }
        }
    }

    private void startAddStudentMenu() {
        String input = "";
        int counterStudents = 0;
        System.out.println("Enter student credentials or 'back' to return:");
        while (!input.equals("back")) {
            input = scanner.nextLine().strip();
            if (input.equals("back")) {
                System.out.println("Total " + counterStudents +
                        " students have been added.");
            } else {
                counterStudents += db.addStudent(input);
            }
        }
    }
}
