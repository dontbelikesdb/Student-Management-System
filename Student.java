public class Student {
    private int studentId;
    private String firstName;
    private String lastName;
    private String major;
    private String phone;
    private double gpa;
    private String dateOfBirth;

    // Constructor
    public Student(String studentId, String firstName, String lastName, String major, String phone, String gpa, String dateOfBirth) {
        this.studentId = Integer.parseInt(studentId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.major = major;
        this.phone = phone;
        this.gpa = Double.parseDouble(gpa);
        this.dateOfBirth = dateOfBirth;
    }

    // Getters and Setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", major='" + major + '\'' +
                ", phone='" + phone + '\'' +
                ", gpa=" + gpa +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }
}
