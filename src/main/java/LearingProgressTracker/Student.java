package main.java.LearingProgressTracker;

public class Student {
    private String mailAddress;
    private String firstName;
    private String lastName;
    private int studentID;
    private Database db;

    public Student(int id, String mailAddress, String lastName, String firstName, Database database) {
        this.studentID = id;
        this.mailAddress = mailAddress;
        this.lastName = lastName;
        this.firstName = firstName;
        this.db = database;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void sendMessage(String course) {
        String text = String.format("To: %s\n" +
                "Re: Your Learning Progress\n" +
                "Hello, %s %s! You have accomplished our %s course!", mailAddress, firstName, lastName, course);
        System.out.println(text);
    }

    @Override
    public String toString() {
        return String.format("%d points: Java=%d; DSA=%d; Databases=%d; Spring=%d",
                studentID,
                db.getStudentsPoints("Java", studentID),
                db.getStudentsPoints("DSA", studentID),
                db.getStudentsPoints("Databases", studentID),
                db.getStudentsPoints("Spring", studentID));
    }
}
