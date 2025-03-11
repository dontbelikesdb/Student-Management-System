import java.sql.*;
import java.util.LinkedList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class Table {

    // Builds the table model for displaying in JTable
    DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();

        // Get column names
        int columnCount = metaData.getColumnCount();
        Vector<String> columnNames = new Vector<>();
        for (int i = 1; i <= columnCount; i++) {
            columnNames.add(metaData.getColumnName(i));
        }

        // Get row data
        Vector<Vector<Object>> rowData = new Vector<>();
        while (rs.next()) {
            Vector<Object> row = new Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                row.add(rs.getObject(i));
            }
            rowData.add(row);
        }

        return new DefaultTableModel(rowData, columnNames);
    }

    // Populates the linked list with student data from the ResultSet
    LinkedList<Student> populateStudentList() {
        LinkedList<Student> studentList = new LinkedList<>();
        dbConnect db = new dbConnect();
        Connection conn;
        Statement stmt;
        try {
            conn = db.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM student";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Student student = new Student(
                        rs.getString("student_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("major"),
                        rs.getString("phone"),
                        rs.getString("gpa"),
                        rs.getString("date_of_birth")
                );
                studentList.add(student);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return studentList;
    }
}
