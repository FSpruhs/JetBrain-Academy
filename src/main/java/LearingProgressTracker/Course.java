package main.java.LearingProgressTracker;

import java.util.*;

public class Course {

    final int maxPoints;
    String courseName;
    LinkedHashMap<Integer, Integer> studentList = new LinkedHashMap<Integer, Integer>();
    ArrayList<Integer> notified = new ArrayList<>();
    int submissions = 0;

    public void printStatistic() {
        sortStudentList();
        System.out.println("id    points    completed");
        for (Map.Entry<Integer, Integer> entry : studentList.entrySet()) {
            System.out.printf("%d %d %.1f", entry.getKey(), entry.getValue(),
                    ((float) entry.getValue()) / maxPoints * 100);
            System.out.println("%");
        }
    }

    public ArrayList<Integer> studentsAccomplished() {
        ArrayList<Integer> students = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : studentList.entrySet()) {
            if (entry.getValue() >= maxPoints && !notified.contains(entry.getKey())) {
                notified.add(entry.getKey());
                students.add(entry.getKey());
            }
        }
        return students;
    }

    private void sortStudentList() {
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<Map.Entry<Integer, Integer>>(studentList.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<Integer, Integer>>(){
            public int compare(Map.Entry<Integer, Integer> entry1, Map.Entry<Integer, Integer> entry2) {
                if (entry2.getValue() - entry1.getValue() == 0) {
                    return entry1.getKey() - entry2.getKey();
                }
                return entry2.getValue() - entry1.getValue();
            }
        });
        studentList.clear();
        for(Map.Entry<Integer, Integer> entry : entries){
            studentList.put(entry.getKey(), entry.getValue());
        }
    }

    public Course(String courseName, int maxPoint) {
        this.courseName = courseName;
        this.maxPoints = maxPoint;
    }

    public void addPoints(int id, int points) {
        if (points > 0) {
            submissions++;
            if (studentList.containsKey(id)) {
                studentList.replace(id, studentList.get(id) + points);
            } else {
                studentList.put(id, points);
            }
        }
    }

    public int getStudentsPoints(int studentID) {
        return studentList.getOrDefault(studentID, 0);

    }

    public int getStudentsNumber() {
        return studentList.size();
    }

    public int getSubmissions() {
        return submissions;
    }

    public int getAverageScore() {
        if (submissions == 0) {
            return 0;
        }
        int totalScore = 0;
        for (Map.Entry<Integer, Integer> entry : studentList.entrySet()) {
            totalScore += entry.getValue();
        }
        return totalScore / submissions;
    }
}
