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
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4)
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
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Department(id, name," +
                    "manager, location) VALUES(?,?,?,?)");
            //code below subtitues the parameterized query in line 44
            pstmt.setString(1, department.getId());
            pstmt.setString(2, department.getName());
            pstmt.setDouble(3, department.getManager());
            pstmt.setDouble(4, department.getLocation());
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(@NotNull Department department, int currId) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE department SET id=?, name=?" +
                    " WHERE id=?");
            pstmt.setInt(1, department.getId());
            pstmt.setString(2, department.getName());
            pstmt.setInt(3, currId);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM department WHERE id=?");
            pstmt.setString(1, id);
            pstmt.executeUpdate(); //used for DML command (insert,update, delete)
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Department getById(int id) {
        Department department = new Department();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM department WHERE ID = ?");
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                department.setId(rs.getInt(1));
                department.setName(rs.getString(2));
                department.setManager(rs.getInt(3));
                department.setLocation(rs.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }
}
