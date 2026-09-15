# Employee Management System

A simple Employee Management System built using Java, JDBC, and PostgreSQL. This project was created while learning Java, SQL, JDBC, and database connectivity.

## Features

The current version supports the following operations:

- Add a new employee
- View all employees
- Search for an employee by ID
- Update employee details
  - Name
  - Department
  - Salary
- Delete an employee by ID
- Generate an employee report
  - Total number of employees
  - Highest salary
  - Minimum salary
  - Average salary
  - Number of employees in each department
- Exit through a menu-driven interface

## Technologies Used

- Java
- JDBC
- PostgreSQL
- pgAdmin 4
- PostgreSQL JDBC Driver

## Database

The project uses a PostgreSQL database named `employee` with an `emp` table.

The table contains the following fields:

- `emp_id`
- `emp_name`
- `dept`
- `salary`

Java connects to PostgreSQL using JDBC and performs SQL operations using `Statement` and `PreparedStatement`.

## How It Works

When the program starts, a menu is displayed:

    Choose the option:
    1. Add
    2. View All
    3. Search By ID
    4. Update
    5. Delete
    6. Report
    7. Exit

The user selects an option and the corresponding operation is performed on the employee database.

### Add Employee

The user enters the employee ID, name, department, and salary. The details are inserted into the PostgreSQL database using a parameterized SQL query.

### View All Employees

Displays all employee records from the database. The records are sorted in ascending order based on employee ID.

### Search By ID

The user enters an employee ID. If the employee exists, their ID, name, department, and salary are displayed. If no matching employee is found, the program displays an appropriate message.

### Update Employee

The user first enters the employee ID and then selects which information to update:

1. Name
2. Department
3. Salary

Only one field is updated at a time.

### Delete Employee

The user enters an employee ID and the corresponding employee record is removed from the database.

### Employee Report

The report provides basic information about the employee database, including:

- Total number of employees
- Highest salary
- Minimum salary
- Average salary
- Number of employees in each department

SQL aggregate functions such as `COUNT`, `MAX`, `MIN`, and `AVG`, along with `GROUP BY`, are used to generate the report.

## Database Connection

The application connects to PostgreSQL using JDBC.

The database password is read from the `DB_PASSWORD` environment variable instead of being directly written in the source code.

Make sure the `DB_PASSWORD` environment variable is configured before running the application.

## Version 1

This is **Version 1** of the Employee Management System.

The main focus of this version is learning and implementing:

- Java
- SQL
- JDBC
- PostgreSQL
- CRUD operations
- Prepared statements
- Database connectivity
- Basic SQL reporting

The current application uses a simple console-based interface.

Future versions will include a **GUI and a more interactive interface**, along with cleaner and more properly structured output to improve the overall user experience.

## Future Improvements

- GUI-based interface
- More interactive user experience
- Cleaner and structured output
- Better input validation
- Improved error handling
- Additional employee management features

## Author

**Shrisay Sunil Nehate**

This project is part of my learning journey with Java, SQL, JDBC, and PostgreSQL.
