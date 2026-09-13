import java.sql.*;
import java.util.Scanner;

// class Employee{
//     int empId;
//     String empName;
//     String dept;
//     double salary;
//}
class Emp {
    private static final String url = "jdbc:postgresql://localhost:2007/employee";
    private static final String username = "postgres";
    private static final String password = "Shrisay1234";
    // void add(){
    //     System.out.println("Enter id, name , dept, salary");

    // }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int id;
        String name;
        String dept;
        double sal;
        int ch;


        System.out.println("Choose the option:\n1.Add");
        ch = sc.nextInt();
        switch (ch) {
            case 1:
                 System.out.println("Enter id, name , dept, salary");
                 id = sc.nextInt();
                 name = sc.next();
                 dept=sc.next();
                 sal= sc.nextDouble();
                String query = "insert into emp (emp_id,emp_name,dept,salary) values(?,?,?,?)";
                try{
                    Connection con = DriverManager.getConnection(url,username,password);
                    PreparedStatement pst= con.prepareStatement(query);
                    pst.setInt(1, id);
                    pst.setString(2, name);
                    pst.setString(3,dept);
                    pst.setDouble(4, sal);

                    pst.executeUpdate();
                    System.out.println("\nEmployee added successfully!\n");
                    System.out.println();
                    pst.close();
                    con.close();
                } 
                catch(Exception e){
                    System.out.println(e.getMessage());
                }

                break;
        
            default:
                break;
        }


    
        try {
            Class.forName("org.postgresql.Driver");

            try (Connection con = DriverManager.getConnection(url, username, password);
                 Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM emp")) {

                System.out.println("Connection Established\n");

                while (rs.next()) {
                    int id1 = rs.getInt("emp_id");
                    String name1 = rs.getString("emp_name");
                    String dept1 = rs.getString("dept");
                    double salary1 = rs.getDouble("salary");

                    System.out.println("id: "+id1);
                    System.out.println("Name: "+name1);
                    System.out.println("Department: "+dept1);
                    System.out.println("Salary: "+salary1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}