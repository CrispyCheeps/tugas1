package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Region;
import org.jetbrains.annotations.NotNull;


public class RegionDAO {

    private Connection conn;

    public RegionDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Region> getAll() {
        List<Region> regions = new ArrayList<>();
        String query = "SELECT * FROM REGION";
        try {
            ResultSet resultSet = conn
                    .prepareStatement(query)
                    .executeQuery();
            while (resultSet.next()) {
                regions.add(new Region(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return regions;
    }

    public boolean insert(@NotNull Region region) {
        try {
            //Code below is using a parameterized query in order to avoid sql injections
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO REGION(id, name, count) VALUES(?,?,0)");
            //code below subtitues the parameterized query in line 44
            pstmt.setInt(1, region.getRegionId());
            pstmt.setString(2, region.getRegionName());
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(@NotNull Region region) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE REGION SET name=? WHERE id=?");
            pstmt.setString(1, region.getRegionName());
            pstmt.setInt(2, region.getRegionId());
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM region WHERE region.id=?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate(); //used for DML command (insert,update, delete)
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Region getById(int id) {
        Region region = new Region();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM REGION WHERE ID = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                region.setRegionId(rs.getInt(1));
                region.setRegionName(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return region;
    }


}
