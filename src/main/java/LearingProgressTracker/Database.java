package main.java.LearingProgressTracker;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Database {

    Map<Integer, Student> studentRegister = new HashMap<>();
    Map<String, Course> course = new HashMap<>();
    String[] courseNames = {"Java", "DSA", "Databases", "Spring"};
    int[] maxPoints = {600, 400, 480, 550};

    Database() {
        for (int i = 0; i < courseNames.length; i++) {
            course.put(courseNames[i], new Course(courseNames[i], maxPoints[i]));
        }
    }

    public void findStudent(String input) {
        int studentId;
        try {
            studentId = Integer.parseInt(input);
            if (!studentRegister.containsKey(studentId)) {
                throw new Exception();
            } else {
                System.out.println(studentRegister.get(studentId).toString());
            }
        } catch (Exception e) {
            System.out.println("No student is found for id=" + input + ".");
        }
    }

    public void addPoints(String input) {
        int[] inputInt = new int[5];
        String[] inputSplit = input.split(" ");
        try {
            if (!studentRegister.containsKey(Integer.parseInt(inputSplit[0]))) {
                throw new NumberFormatException();
            }
            if (!input.matches("(\\d+\\s)(\\d+\\s)(\\d+\\s)(\\d+\\s)(\\d+)")) {
                throw new Exception();
            }
            for (int i = 0; i < 5; i++) {
                inputInt[i] = Integer.parseInt(inputSplit[i]);
            }
        } catch (NumberFormatException e) {
            System.out.println("No student is found for id=" + inputSplit[0] + ".");
            return;
        } catch (Exception e) {
            System.out.println("Incorrect points format.");
            return;
        }
        for (int i = 0; i < courseNames.length; i++) {
            addStudentPoints(courseNames[i], inputInt[0], inputInt[i + 1]);
        }
        System.out.println("Points updated.");
    }

    private void addStudentPoints(String courseName, int id, int points) {
        Course toAdd = course.get(courseName);
        toAdd.addPoints(id, points);
    }


    public void showList() {
        if (studentRegister.isEmpty()) {
            System.out.println("No students found");
        } else {
            System.out.println("Students:");
            for (int id : studentRegister.keySet()) {
                System.out.println(id);
            }
        }
    }

    public int addStudent(String input) {
        boolean correctInput;
        try {
            String firstName = input.substring(0, input.indexOf(" "));
            String lastName = input.substring(input.indexOf(" "), input.lastIndexOf(" "));
            String mailAddress = input.substring(input.lastIndexOf(" "));
            if (lastName.equals("")) {
                throw new Exception();
            }
            correctInput = checkFirstName(firstName.strip());
            if (!correctInput) {
                return 0;
            }
            correctInput = checkLastName(lastName.strip());
            if (!correctInput) {
                return 0;
            }
            correctInput = checkMailAddressName(mailAddress.strip());
            if (!correctInput) {
                return 0;
            }
            correctInput = checkMailAddressExist(mailAddress);
            if (correctInput) {
                int id = createNewId();
                studentRegister.put(id, new Student(id, mailAddress, lastName, firstName, this));
                System.out.println("The student has been added.");
                return 1;
            }
        } catch (Exception e) {
            System.out.println("Incorrect credentials.");
        }
        return 0;
    }

    private int createNewId() {
        Random rnd = new Random();
        int newId;
        int low = 10000;
        int high = 99999;
        do {
            newId = rnd.nextInt(high - low) + low;
        } while(studentRegister.containsKey(newId));
        return newId;
    }

    private boolean checkMailAddressExist(String mailAddress) {
        for (Student student : studentRegister.values()) {
            if (student.getMailAddress().equals(mailAddress)) {
                System.out.println("This email is already taken.");
                return false;
            }
        }
        return true;
    }

    public boolean checkMailAddressName(String mailAddress) {
        if (mailAddress.matches("[a-zA-Z0-9\\.]+@[a-zA-Z0-9]+\\..+")) {
            return true;
        } else {
            System.out.println("Incorrect email.");
            return false;
        }
    }

    public boolean checkLastName(String lastName) {
        if (lastName.matches("^[A-Za-z]+((\\s)?(['-]?([A-Za-z])+))+?$")) {
            return true;
        } else {
            System.out.println("Incorrect last name.");
            return false;
        }
    }

    private boolean checkFirstName(String firstName) {
        if (firstName.matches("^[A-Za-z]+((\\s)?(['-]?([A-Za-z])+))+?$")) {
            return true;
        } else {
            System.out.println("Incorrect first name.");
            return false;
        }
    }

    public int getStudentsPoints(String courseName, int studentID) {
        Course c = course.get(courseName);
        return c.getStudentsPoints(studentID);
    }

    public void mostPopular() {
        String name = "n/a";
        int mostPopular = 0;
        for (Map.Entry<String, Course> entry : course.entrySet()) {
            int studentsNumber = entry.getValue().getStudentsNumber();
            if (studentsNumber > mostPopular) {
                mostPopular = studentsNumber;
                name = entry.getKey();
            } else if (studentsNumber == mostPopular && studentsNumber != 0) {
                name = name + ", " + entry.getKey();
            }
        }
        System.out.printf("Most popular: %s\n", name);
    }

    public void leastPopular() {
        String name = "n/a";
        int counter = 1;
        int leastPopular = 0;
        for (Map.Entry<String, Course> entry : course.entrySet()) {
            int studentsNumber = entry.getValue().getStudentsNumber();
            if (studentsNumber < leastPopular) {
                leastPopular = studentsNumber;
                name = entry.getKey();
            } else if (studentsNumber == leastPopular && studentsNumber != 0) {
                name = name + ", " + entry.getKey();
                counter++;
            } else if (studentsNumber > 0 && leastPopular == 0) {
                leastPopular = studentsNumber;
                name = entry.getKey();
            }
        }
        if ( counter == 4) {
            name = "n/a";
        }
        System.out.printf("Least popular: %s\n", name);
    }

    public void highestActivity() {
        String name = "n/a";
        int highestActivity = 0;
        for (Map.Entry<String, Course> entry : course.entrySet()) {
            int submissions = entry.getValue().getSubmissions();
            if (submissions > highestActivity) {
                highestActivity = submissions;
                name = entry.getKey();
            } else if (submissions == highestActivity && submissions != 0) {
                name = name + ", " + entry.getKey();
            }
        }
        System.out.printf("Highest activity: %s\n", name);
    }

    public void lowestActivity() {
        String name = "n/a";
        int counter = 0;
        int lowestActivity = 0;
        for (Map.Entry<String, Course> entry : course.entrySet()) {
            int submissions = entry.getValue().getSubmissions();
            if (submissions < lowestActivity) {
                lowestActivity = submissions;
                name = entry.getKey();
            } else if (submissions == lowestActivity && submissions != 0) {
                name = name + ", " + entry.getKey();
                counter++;
            } else if (submissions > 0 && lowestActivity == 0) {
                lowestActivity = submissions;
                name = entry.getKey();
            }
        }
        if (counter == 3) {
            name = "n/a";
        }
        System.out.printf("Lowest activity: %s\n", name);
    }

    public void easiestCourse() {
        String name = "n/a";
        int easiestCourse = 0;
        for (Map.Entry<String, Course> entry : course.entrySet()) {
            int averageScore = entry.getValue().getAverageScore();
            if (averageScore > easiestCourse) {
                easiestCourse = averageScore;
                name = entry.getKey();
            } else if (averageScore == easiestCourse && averageScore != 0) {
                name = name + ", " + entry.getKey();
            }
        }
        System.out.printf("Easiest course: %s\n", name);
    }

    public void hardestCourse() {
        String name = "n/a";
        int hardestCourse = 0;
        for (Map.Entry<String, Course> entry : course.entrySet()) {
            int averageScore = entry.getValue().getAverageScore();
            if (averageScore < hardestCourse) {
                hardestCourse = averageScore;
                name = entry.getKey();
            } else if (averageScore == hardestCourse && averageScore != 0) {
                name = name + ", " + entry.getKey();
            } else if (averageScore > 0 && hardestCourse == 0) {
                hardestCourse = averageScore;
                name = entry.getKey();
            }
        }
        System.out.printf("Hardest course: %s\n", name);
    }

    public Student getStudents(int studentID) {
        return studentRegister.get(studentID);
    }
}
