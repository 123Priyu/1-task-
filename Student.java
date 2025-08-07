import java.util.*;

public class Student {
    private int id;
    private String name;
    private int age;
    private String course;

    public Student(int id, String name, int age, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCourse() { return course; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setCourse(String course) { this.course = course; }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Age: " + age + " | Course: " + course;
    }
import java.util.*;

public class StudentManager {
    private Map<Integer, Student> students = new HashMap<>();

    public void addStudent(Student student) {
        students.put(student.getId(), student);
        System.out.println("Student added successfully.");
    }

    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
        } else {
            for (Student s : students.values()) {
                System.out.println(s);
            }
        }
    }

    public void updateStudent(int id, String name, int age, String course) {
        Student s = students.get(id);
        if (s != null) {
            s.setName(name);
            s.setAge(age);
            s.setCourse(course);
            System.out.println("Student record updated.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void deleteStudent(int id) {
        if (students.remove(id) != null) {
            System.out.println("Student deleted.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public boolean exists(int id) {
        return students.containsKey(id);
    }
}import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nStudent Record Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    if (manager.exists(id)) {
                        System.out.println("Student with this ID already exists.");
                        break;
                    }
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Course: ");
                    String course = sc.nextLine();
                    manager.addStudent(new Student(id, name, age, course));
                    break;

                case 2:
                    manager.viewStudents();
                    break;

                case 3:
                    System.out.print("Enter ID to update: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    if (!manager.exists(uid)) {
                        System.out.println("Student not found.");
                        break;
                    }
                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter New Age: ");
                    int newAge = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Course: ");
                    String newCourse = sc.nextLine();
                    manager.updateStudent(uid, newName, newAge, newCourse);
                    break;

                case 4:
                    System.out.print("Enter ID to delete: ");
                    int did = sc.nextInt();
                    manager.deleteStudent(did);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}