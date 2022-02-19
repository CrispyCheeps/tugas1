package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Employee;
import models.Employee;
import models.Location;
import org.jetbrains.annotations.NotNull;

public class EmployeeDAO {

    private Connection conn;

    public EmployeeDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        try {
            ResultSet rs = conn
                    .prepareStatement("SELECT * FROM Employee")
                    .executeQuery();
            while (rs.next()) {
                employees.add(new Employee(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getDouble(8),
                        rs.getDouble(9),
                        rs.getInt(10),
                        rs.getInt(11)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public boolean insert(@NotNull Employee emp) {
        try {
            PreparedStatement pstmt=conn.prepareStatement("INSERT INTO employee (id, first_name,"+
                    "last_name, email, phone_number, hire_date, job, salary, comission_pct, manager, department) VALUES(?,?,?,?,?,?,?,?,?,?,?)"
            );
            pstmt.setInt(1, emp.getId());
            pstmt.setString(2, emp.getfName());
            pstmt.setString(3, emp.getlName());
            pstmt.setString(4, emp.getEmail());
            pstmt.setString(5, emp.getNoTelp());
            pstmt.setString(6, emp.getHireDate());
            pstmt.setString(7, emp.getJob());
            pstmt.setDouble(8, emp.getSalary());
            pstmt.setDouble(9, emp.getCommission());
            pstmt.setInt(10, emp.getManager());
            pstmt.setInt(11, emp.getDepartment());
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(@NotNull Employee emp) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(
                    "UPDATE employee SET first_name=?, last_name=?, phone_number=?" +
                            ", hire_date=?, salary=?, comission_pct=? WHERE id=?"
            );
            pstmt.setString(1, emp.getfName());
            pstmt.setString(2, emp.getfName());
            pstmt.setString(3, emp.getNoTelp());
            pstmt.setString(4, emp.getHireDate());
            pstmt.setDouble(5, emp.getSalary());
            pstmt.setDouble(6, emp.getCommission());
            pstmt.setInt(7, emp.getId());
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM employee WHERE id=?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate(); //used for DML command (insert,update, delete)
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Employee getById(int id) {
        Employee emp = new Employee();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM employee WHERE ID = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                emp.setId(rs.getInt(1));
                emp.setfName(rs.getString(2));
                emp.setlName(rs.getString(3));
                emp.setEmail(rs.getString(4));
                emp.setNoTelp(rs.getString(5));
                emp.setHireDate(rs.getString(6));
                emp.setJob(rs.getString(7));
                emp.setSalary(rs.getDouble(8));
                emp.setCommission(rs.getDouble(9));
                emp.setManager(rs.getInt(10));
                emp.setDepartment(rs.getInt(11));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emp;
    }
}


