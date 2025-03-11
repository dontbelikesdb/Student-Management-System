import javax.swing.*;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        // Create the GUI window
        AppGUI window = new AppGUI();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        // Populate the linked list with student data from the database
        Table table = new Table();
        LinkedList<Student> studentList = table.populateStudentList();

        // print the students in the linked list
        System.out.println("Students in the Linked List:");
        for (Student student : studentList) {
            System.out.println(student);
        }
    }
}
