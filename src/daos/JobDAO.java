package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Job;
import org.jetbrains.annotations.NotNull;

public class JobDAO {
    Connection conn;

    public JobDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Job> getAll() {
        List<Job> jobs = new ArrayList<>();
        try {
            ResultSet resultSet = conn
                    .prepareStatement("SELECT * FROM job")
                    .executeQuery();
            while (resultSet.next()) {
                jobs.add(new Job(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getDouble(4)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }

    public boolean insert(@NotNull Job job) {
        try {
            //Code below is using a parameterized query in order to avoid sql injections
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO job(id, title," +
                    "min_salary, max_salary) VALUES(?,?,?,?)");
            //code below subtitues the parameterized query in line 44
            pstmt.setString(1, job.getJobId());
            pstmt.setString(2, job.getJobTitle());
            pstmt.setDouble(3, job.getMinSalary());
            pstmt.setDouble(4, job.getMaxSalary());
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(@NotNull Job job, String currId) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE job SET id=?, title=?" +
                    ", min_salary=?, max_salary=? WHERE id=?");
            pstmt.setString(1, job.getJobId());
            pstmt.setString(2, job.getJobTitle());
            pstmt.setDouble(3, job.getMinSalary());
            pstmt.setDouble(4, job.getMaxSalary());
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
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM job WHERE id=?");
            pstmt.setString(1, id);
            pstmt.executeUpdate(); //used for DML command (insert,update, delete)
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Job getById(String id) {
        Job job = new Job();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM job WHERE ID = ?");
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                job.setJobId(rs.getString(1));
                job.setJobTitle(rs.getString(2));
                job.setMinSalary(rs.getDouble(3));
                job.setMaxSalary(rs.getDouble(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return job;
    }
}
