package com.shiva;

import java.sql.*;
import java.util.Scanner;

class Student {
    int id, age, yearOfJoining, hostel, transport;
    String name, phoneNumber, address, branch;
    char section;
    float gpa, feesPaid;
}
public class Students {
    static final String DB_URL = "jdbc:mysql://localhost:3306/studentdb";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection conn = null;
        
        try {
            // Establishing connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            createTable(conn);

            int choice;
            do {
                System.out.println("1. Add Student");
                System.out.println("2. Display Students");
                System.out.println("3. Delete Student");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addStudent(conn, scanner);
                        break;
                    case 2:
                        displayStudents(conn);
                        break;
                    case 3:
                        deleteStudent(conn, scanner);
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } while (choice != 4);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        scanner.close();
    }

    public static void createTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS students ("
                + "id INT PRIMARY KEY,"
                + "name VARCHAR(50),"
                + "age INT,"
                + "gpa FLOAT,"
                + "fees_paid FLOAT,"
                + "phone_number VARCHAR(15),"
                + "address VARCHAR(100),"
                + "branch VARCHAR(20),"
                + "section CHAR(1),"
                + "year_of_joining INT,"
                + "hostel INT,"
                + "transport INT)";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    public static void addStudent(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student age: ");
        int age = scanner.nextInt();
        System.out.print("Enter student GPA: ");
        float gpa = scanner.nextFloat();
        System.out.print("Enter fees paid: ");
        float feesPaid = scanner.nextFloat();
        scanner.nextLine();
        System.out.print("Enter student phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter student address: ");
        String address = scanner.nextLine();
        System.out.print("Enter student branch: ");
        String branch = scanner.nextLine();
        System.out.print("Enter student section: ");
        char section = scanner.next().charAt(0);
        System.out.print("Enter student year of joining: ");
        int yearOfJoining = scanner.nextInt();
        System.out.print("Does the student want to join the hostel? (1 for Yes, 0 for No): ");
        int hostel = scanner.nextInt();
        System.out.print("Does the student want transport? (1 for Yes, 0 for No): ");
        int transport = scanner.nextInt();

        String sql = "INSERT INTO students VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, age);
            pstmt.setFloat(4, gpa);
            pstmt.setFloat(5, feesPaid);
            pstmt.setString(6, phoneNumber);
            pstmt.setString(7, address);
            pstmt.setString(8, branch);
            pstmt.setString(9, String.valueOf(section));
            pstmt.setInt(10, yearOfJoining);
            pstmt.setInt(11, hostel);
            pstmt.setInt(12, transport);
            pstmt.executeUpdate();
            System.out.println("Student added successfully.");
        }
    }

    public static void displayStudents(Connection conn) throws SQLException {
        String sql = "SELECT * FROM students";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Student List:");
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s, Age: %d, GPA: %.2f, Fees Paid: %.2f, Phone: %s, Address: %s, Branch: %s, Section: %c, Year: %d, Hostel: %s, Transport: %s%n",
                        rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getFloat("gpa"), rs.getFloat("fees_paid"),
                        rs.getString("phone_number"), rs.getString("address"), rs.getString("branch"), rs.getString("section").charAt(0),
                        rs.getInt("year_of_joining"), rs.getInt("hostel") == 1 ? "Yes" : "No", rs.getInt("transport") == 1 ? "Yes" : "No");
            }
        }
    }

    public static void deleteStudent(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter the ID of the student to delete: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student with ID " + id + " not found.");
            }
        }
    }
}
