import java.sql.*;
import java.util.Scanner;

class Employee{
    public static int empId;
    public static String empName;
    public static String dept;
    public static double salary;
}
class Emp {
    private static final String url = "jdbc:postgresql://localhost:2007/employee";
    private static final String username = "postgres";
    private static final String password = System.getenv("DB_PASSWORD");
    public void add(){
        Scanner sc = new Scanner(System.in);
      
     System.out.println("Enter id, name , dept, salary");
                 Employee.empId = sc.nextInt();
                 Employee.empName = sc.next();
                 Employee.dept = sc.next();
                 Employee.salary = sc.nextDouble();
                String query = "insert into emp (emp_id,emp_name,dept,salary) values(?,?,?,?)";
                try{
                    Connection con = DriverManager.getConnection(url,username,password);
                    PreparedStatement pst= con.prepareStatement(query);
                    pst.setInt(1, Employee.empId);
                    pst.setString(2, Employee.empName);
                    pst.setString(3, Employee.dept);
                    pst.setDouble(4, Employee.salary);

                    pst.executeUpdate();
                    System.out.println("\nEmployee added successfully!\n");
                    System.out.println();
                    pst.close();
                    con.close();
                } 
                catch(Exception e){
                    System.out.println(e.getMessage());
                }

    }

    public void view_all(){
        try {
            try (Connection con = DriverManager.getConnection(url, username, password);
                 Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM emp ORDER BY emp_id")) {

                // System.out.println("Connection Established\n");

                while (rs.next()) {
                    int id1 = rs.getInt("emp_id");
                    String name1 = rs.getString("emp_name");
                    String dept1 = rs.getString("dept");
                    double salary1 = rs.getDouble("salary");

                    System.out.println("\nid: "+id1);
                    System.out.println("Name: "+name1);
                    System.out.println("Department: "+dept1);
                    System.out.println("Salary: "+salary1);
                    System.out.println("\n-----------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }


    public static void main(String[] args) {
        Emp emp = new Emp();
        Scanner sc = new Scanner(System.in);
        // int id;
        // String name;
        // String dept;
        // double sal;
        int ch;

        if (password == null || password.isBlank()) {
            System.err.println("DB_PASSWORD is not set. Set it as an environment variable before running the program.");
            return;
        }

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC driver was not found. Add postgresql-42.7.13.jar to the runtime classpath.");
            return;
        }

        System.out.println("Choose the option:\n1.Add\n2.View All");
        System.out.print("Enter your choice: ");
        ch = sc.nextInt();
        switch (ch) {
            case 1:
                    emp.add();
                break;
            case 2:
                    emp.view_all();
                break;
         }


    

    }
}
