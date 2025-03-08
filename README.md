# Student Management System

## 📌 Overview
The **Student Management System** is a Java-based application that allows users to manage student records efficiently. It supports **CRUD operations** (Create, Read, Update, Delete) and stores student data in a **MySQL database**.

## 🚀 Features
- Add new students with details such as name, age, GPA, fees, contact info, and more.
- View all student records.
- Delete students by ID.
- MySQL database integration for persistent storage.
- Console-based user interface.

## 🛠 Technologies Used
- **Java** (Core logic)
- **JDBC** (Java Database Connectivity)
- **MySQL** (Database for storing student records)
- **Scanner (Java.util)** (For user input handling)

## 📂 Project Structure
```
📁 StudentManagementSystem
│── 📂 src
│   ├── com/shiva/Student.java
│   ├── com/shiva/Students.java
│── README.md
│── .gitignore
```

## 🏗 Installation & Setup

### 1️⃣ Clone the Repository
```sh
git clone https://github.com/your-username/student-management-system.git
cd student-management-system
```

### 2️⃣ Set Up the MySQL Database
1. Open **MySQL Workbench** or the **MySQL command line**.
2. Create a database:
   ```sql
   CREATE DATABASE studentdb;
   ```
3. Use the database:
   ```sql
   USE studentdb;
   ```
4. (Optional) You can manually create the `students` table, but the Java program automatically does this.

### 3️⃣ Configure Database Credentials
- Update the database **URL, username, and password** in `Students.java`:
  ```java
  static final String DB_URL = "jdbc:mysql://localhost:3306/studentdb";
  static final String USER = "root";
  static final String PASS = "your_password";
  ```

### 4️⃣ Compile and Run the Program
```sh
javac -d . src/com/shiva/Students.java
java com.shiva.Students
```

## 📖 Usage
1. Run the program.
2. Select an option:
   - **1**: Add a student.
   - **2**: Display all students.
   - **3**: Delete a student by ID.
   - **4**: Exit the application.

## 📝 Example Output
```
1. Add Student
2. Display Students
3. Delete Student
4. Exit
Enter your choice: 1
Enter student ID: 101
Enter student name: John Doe
Enter student age: 20
...
Student added successfully.
```

## 🏗 Future Enhancements
- Implement an **Update Student** feature.
- Add **GUI (JavaFX/Swing)** for a better user experience.
- Implement **pagination** for large datasets.

## 🤝 Contribution
Feel free to fork this repository and submit **pull requests** for improvements!

## 📜 License
This project is open-source and available under the **MIT License**.

---

Happy Coding! 🚀
```

This README includes everything needed for installation, setup, and usage. Let me know if you want any modifications! 😊
