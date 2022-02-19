package daos;

import models.Department;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    Connection conn;

    public DepartmentDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Department> getAll() {
        List<Department> departments = new ArrayList<>();
        try {
            ResultSet resultSet = conn
                    .prepareStatement("SELECT * FROM department")
                    .executeQuery();
            while (resultSet.next()) {
                departments.add(new Department(
                        resultSet.getInt(1);
                        resultSet.getString(2);
                        resultSet.getString(3);
                        resultSet.getString(4);
                        resultSet.getString(5);
                        resultSet.getString(6);
                        resultSet.getString(7);
                        resultSet.getDouble(8);
                        resultSet.getDouble(9);
                        resultSet.getInt(10);
                        resultSet.getInt(11);


                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    public boolean insert(@NotNull Department department) {
        try {
            //Code below is using a parameterized query in order to avoid sql injections
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Department(id, first_name," +
                    "last_name, email, phone_number, hire_date, job, salary, comission_pct, manager, department) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            //code below subtitues the parameterized query in line 44
            pstmt.setString(1, department.getId());
            pstmt.setString(2, department.ge);
            pstmt.setDouble(3, );
            pstmt.setDouble(4, );
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(@NotNull Department Department, String currId) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE Department SET id=?, title=?" +
                    ", min_salary=?, max_salary=? WHERE id=?");
            pstmt.setString(1, models.Department.getDepartmentId());
            pstmt.setString(2, models.Department.getDepartmentTitle());
            pstmt.setDouble(3, models.Department.getMinSalary());
            pstmt.setDouble(4, models.Department.getMaxSalary());
            pstmt.setString(5, currId);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String id) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Department WHERE id=?");
            pstmt.setString(1, id);
            pstmt.executeUpdate(); //used for DML command (insert,update, delete)
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Department getById(String id) {
        Department department = new Department();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Department WHERE ID = ?");
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                models.Department.setDepartmentId(rs.getString(1));
                models.Department.setDepartmentTitle(rs.getString(2));
                models.Department.setMinSalary(rs.getDouble(3));
                models.Department.setMaxSalary(rs.getDouble(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return models.Department;
    }
}
