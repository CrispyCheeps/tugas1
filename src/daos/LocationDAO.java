package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Location;
import org.jetbrains.annotations.NotNull;

public class LocationDAO {

    private Connection conn;

    public LocationDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Location> getAll() {
        List<Location> locations = new ArrayList<>();
        try {
            ResultSet rs = conn
                    .prepareStatement("SELECT * FROM LOCATION")
                    .executeQuery();
            while (rs.next()) {
                locations.add(new Location(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locations;
    }

    public boolean insert(@NotNull Location loc) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO location (id, street_address, postal_code, city," +
                            "state_province, country) VALUES(?,?,?,?,?,?)"
            );
            pstmt.setInt(1, loc.getLocationId());
            pstmt.setString(2, loc.getAlamat());
            pstmt.setString(3, loc.getKodePos());
            pstmt.setString(4, loc.getCity());
            pstmt.setString(5, loc.getProvinsi());
            pstmt.setString(6, loc.getCountry());
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(@NotNull Location loc) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(
              "UPDATE location SET street_address=?, postal_code=?, city=?" +
                      ", state_province=?, country=? WHERE id=?"
            );
            pstmt.setString(1, loc.getAlamat());
            pstmt.setString(2, loc.getKodePos());
            pstmt.setString(3, loc.getCity());
            pstmt.setString(4, loc.getProvinsi());
            pstmt.setString(5, loc.getCountry());
            pstmt.setInt(6, loc.getLocationId());
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM location WHERE id=?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate(); //used for DML command (insert,update, delete)
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Location getById(int id) {
        Location loc = new Location();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM location WHERE ID = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                loc.setLocationId(rs.getInt(1));
                loc.setAlamat(rs.getString(2));
                loc.setKodePos(rs.getString(3));
                loc.setCity(rs.getString(4));
                loc.setProvinsi(rs.getString(5));
                loc.setCountry(rs.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loc;
    }
}
