import java.io.*;
import java.util.*;


class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;
    private int age;

    public Student(String name, int rollNumber, String grade, int age) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.age = age;
    }

    public String getName() { return name; }
    public int getRollNumber() { return rollNumber; }
    public String getGrade() { return grade; }
    public int getAge() { return age; }

    public void setName(String name) { this.name = name; }
    public void setGrade(String grade) { this.grade = grade; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "Roll No: " + rollNumber + " | Name: " + name + " | Grade: " + grade + " | Age: " + age;
    }
}

class StudentManagementSystem {
    private List<Student> students = new ArrayList<>();
    private final String FILE_NAME = "students.dat";

    public StudentManagementSystem() {
        loadData();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveData();
    }

    public boolean removeStudent(int rollNumber) {
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.getRollNumber() == rollNumber) {
                it.remove();
                saveData();
                return true;
            }
        }
        return false;
    }

    public Student searchStudent(int rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber() == rollNumber) {
                return s;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found!");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadData() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (List<Student>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}

public class StudentManagementApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();

        while (true) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    try {
                        System.out.print("Enter Roll Number: ");
                        int roll = Integer.parseInt(sc.nextLine());
                        if (sms.searchStudent(roll) != null) {
                            System.out.println(" Roll number already exists!");
                            break;
                        }

                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        if (name.trim().isEmpty()) {
                            System.out.println(" Name cannot be empty!");
                            break;
                        }

                        System.out.print("Enter Grade: ");
                        String grade = sc.nextLine();

                        System.out.print("Enter Age: ");
                        int age = Integer.parseInt(sc.nextLine());
                        if (age <= 0) {
                            System.out.println(" Age must be positive!");
                            break;
                        }

                        sms.addStudent(new Student(name, roll, grade, age));
                        System.out.println(" Student added successfully!");
                    } catch (NumberFormatException e) {
                        System.out.println(" Invalid input! Please enter numbers where required.");
                    }
                    break;

                case "2":
                    try {
                        System.out.print("Enter Roll Number to remove: ");
                        int roll = Integer.parseInt(sc.nextLine());
                        if (sms.removeStudent(roll)) {
                            System.out.println("Student removed successfully!");
                        } else {
                            System.out.println(" Student not found!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(" Invalid roll number!");
                    }
                    break;

                case "3":
                    try {
                        System.out.print("Enter Roll Number to search: ");
                        int roll = Integer.parseInt(sc.nextLine());
                        Student s = sms.searchStudent(roll);
                        if (s != null) {
                            System.out.println(" Found: " + s);
                        } else {
                            System.out.println(" Student not found!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(" Invalid roll number!");
                    }
                    break;

                case "4":
                    sms.displayAllStudents();
                    break;

                case "5":
                    System.out.println(" Exiting... Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println(" Invalid choice! Please try again.");
            }
        }
    }
}