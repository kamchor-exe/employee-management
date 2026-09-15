import java.sql.*;
import java.util.Scanner;

class Employee{
    public static int empId;
    public static String empName;
    public static String dept;
    public static int salary;
}
class Emp {
    private static final String url = "jdbc:postgresql://localhost:2007/employee";
    private static final String username = "postgres";
    private static final String password = System.getenv("DB_PASSWORD");

    //Function 1: to add employee details to the database
    public void add(){
        Scanner sc = new Scanner(System.in);
      
     System.out.println("\nEnter id, name , dept, salary");
                 Employee.empId = sc.nextInt();
                 Employee.empName = sc.next();
                 Employee.dept = sc.next();
                 Employee.salary = sc.nextInt();
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

    //Function 2: to view all employee details from the database
    public void view_all(){
        try {
            try (Connection con = DriverManager.getConnection(url, username, password);
                 Statement stmt = con.createStatement();
                 //*ResultSet* is a Java object that stores the data returned from a relational database after executing an SQL query
                 ResultSet rs = stmt.executeQuery("SELECT * FROM emp ORDER BY emp_id ASC")) {

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

    //Function 3: to Search employee details using emp_id
    public void search(){
        Scanner sc= new Scanner(System.in);
        System.out.print("\nEnter the id to be searched: ");
        int id;
        id=sc.nextInt();
        try{
            //connect and find the data
            Connection con= DriverManager.getConnection(url,username,password);
            String query= "select * from emp where emp_id= ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);

            //Display the data
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("\nEmployee with id: "+id);
                System.out.println("\nID: "+rs.getInt("emp_id"));
                System.out.println("Name: "+rs.getString("emp_name"));
                System.out.println("Department: "+rs.getString("dept"));
                System.out.println("Salary: "+rs.getInt("salary"));
                System.out.println();
            }
            else{
                System.out.println("ID not found!");
            }
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //Function 4: to update Name/Department/Salary (once at a time)
    public void update(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        System.out.println("What to update?\n1:Name | 2:Department | 3:Salary");
        int ch = sc.nextInt();



        switch (ch) {
            case 1://Update name
               try{
                System.out.println("Enter new name: ");
                String name= sc.next();
                Connection con = DriverManager.getConnection(url,username,password);
                String query = "update emp set emp_name= ? where emp_id=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, name);
                pst.setInt(2, id);
                pst.executeUpdate();
                pst.close();
                con.close();


               }
               catch(Exception e)
               {
                e.printStackTrace();
               }

                break;
            
            case 2://Update Department
               try{
                System.out.println("Enter new department: ");
                String dept= sc.next();
                Connection con = DriverManager.getConnection(url,username,password);
                String query = "update emp set dept= ? where emp_id=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, dept);
                pst.setInt(2, id);
                pst.executeUpdate();
                pst.close();
                con.close();


               }
               catch(Exception e)
               {
                e.printStackTrace();
               }

               break;

            case 3://Update salary
                try{
                System.out.println("Enter new salary: ");
                int sal = sc.nextInt();
                Connection con = DriverManager.getConnection(url,username,password);
                String query = "update emp set salary= ? where emp_id=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setInt(1, sal);
                pst.setInt(2, id);
                pst.executeUpdate();
                pst.close();
                con.close();


               }
               catch(Exception e)
               {
                e.printStackTrace();
               }

               break;

            default:
                break;
        }
    }

    //Function  5: to delete employee records. 
    // Id of eleminated employee is lost until its assigned to another employee
    public void delete(){
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter ID to be deleted: ");
        int id = sc.nextInt();

        try{
            Connection con = DriverManager.getConnection(url,username,password);
            String query= "delete from emp where emp_id=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            pst.executeUpdate();
            pst.close();
            con.close();
            System.out.println("Employee Eliminated Succesfully! :(");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    public void report(){
        System.out.println("\n------------------------------");
        System.out.println("Employee Report");
        System.out.println("------------------------------\n");
        
        try {
            Connection con = DriverManager.getConnection(url,username,password);
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select count(emp_id) from emp;");
            if(rs.next()){
                System.out.println("Total Employees: "+rs.getInt("count"));
            }
            
            ResultSet rs1 = stmt.executeQuery("select max(salary) from emp;");
            if(rs1.next()){
                System.out.println("Highest Salary: "+ rs1.getInt("max"));
            }
            
            ResultSet rs2 = stmt.executeQuery("select min(salary) from emp;");
            if(rs2.next()){
                System.out.println("Minimum Salary: "+ rs2.getInt("min"));
            }

            ResultSet rs3 = stmt.executeQuery("select avg(salary) from emp;");
            if (rs3.next()) {
                System.out.println("Average Saalry: "+rs3.getDouble("avg"));
            }

            ResultSet rs4 = stmt.executeQuery("select dept,count(emp_id) from emp group by dept;");
            System.out.println("\n-----------------------------------");
            System.out.println(" Employees In Each Department");
            System.out.println("-----------------------------------\n");
            while (rs4.next())
            {

                System.out.println(rs4.getString("dept")+ ":" +rs4.getInt("count")  );
            }
            System.out.println("");

    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    }


//Main function
    public static void main(String[] args) {
        Emp emp = new Emp();
        Scanner sc = new Scanner(System.in);
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

        System.out.println("Choose the option:\n1.Add\n2.View All\n3.Search By ID\n4.Update\n5.Delete\n6.Report");
        System.out.print("Enter your choice: ");
        ch = sc.nextInt();
        switch (ch) {
            case 1:
                    emp.add();
                break;
            case 2:
                    emp.view_all();
                break;
            case 3: 
                    emp.search();
                    break;
            case 4:
                    emp.update();
                    break;
            case 5:
                    emp.delete();
                    break;
            case 6:
                    emp.report();
                    break;
         }


    

    }
}
