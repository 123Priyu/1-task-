import java.sql.*;
import java.util.Scanner;

public class EmployeeDatabaseApp {
    private static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    private static final String USER = "root"; // change to your DB username
    private static final String PASSWORD = "root"; // change to your DB password

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner sc = new Scanner(System.in)) {

            System.out.println("✅ Connected to Database!");

            while (true) {
                System.out.println("\n--- Employee Database Menu ---");
                System.out.println("1. Add Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> addEmployee(conn, sc);
                    case 2 -> viewEmployees(conn);
                    case 3 -> updateEmployee(conn, sc);
                    case 4 -> deleteEmployee(conn, sc);
                    case 5 -> {
                        System.out.println("👋 Exiting...");
                        return;
                    }
                    default -> System.out.println("❌ Invalid choice!");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Name: ");
        sc.nextLine(); // consume leftover newline
        String name = sc.nextLine();
        System.out.print("Enter Position: ");
        String position = sc.nextLine();
        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();

        String sql = "INSERT INTO employees (name, position, salary) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, position);
            stmt.setDouble(3, salary);
            int rows = stmt.executeUpdate();
            System.out.println(rows + " employee(s) added.");
        }
    }

    private static void viewEmployees(Connection conn) throws SQLException {
        String sql = "SELECT * FROM employees";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- Employee List ---");
            while (rs.next()) {
                System.out.printf("ID: %d | Name: %s | Position: %s | Salary: %.2f%n",
                        rs.getInt("id"), rs.getString("name"),
                        rs.getString("position"), rs.getDouble("salary"));
            }
        }
    }

    private static void updateEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Employee ID to Update: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume leftover newline
        System.out.print("Enter New Name: ");
        String name = sc.nextLine();
        System.out.print("Enter New Position: ");
        String position = sc.nextLine();
        System.out.print("Enter New Salary: ");
        double salary = sc.nextDouble();

        String sql = "UPDATE employees SET name=?, position=?, salary=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, position);
            stmt.setDouble(3, salary);
            stmt.setInt(4, id);
            int rows = stmt.executeUpdate();
            System.out.println(rows + " employee(s) updated.");
        }
    }

    private static void deleteEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Employee ID to Delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM employees WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            System.out.println(rows + " employee(s) deleted.");
        }
    }
}