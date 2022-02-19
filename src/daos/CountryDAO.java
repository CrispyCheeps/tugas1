package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Country;
import org.jetbrains.annotations.NotNull;

public class CountryDAO {

    private Connection conn;

    public CountryDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Country> getAll() {
        List<Country> Countries = new ArrayList<>();
        try {
            ResultSet rs = conn
                    .prepareStatement("SELECT * FROM country")
                    .executeQuery();
            while (rs.next()) {
                Countries.add(new Country(
                        //inisiasi objek country berdasarkan data yg disimpan di result set
                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Countries;
    }

    public boolean insert(@NotNull Country country) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO country (id, name, region) VALUES(?,?,?)"
            );
            pstmt.setString(1, country.getCountryId());
            pstmt.setString(2, country.getCountryName());
            pstmt.setInt(3, country.getCountryRegion());
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(@NotNull Country country, String currID) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(
                    "UPDATE country SET id=?, name=? WHERE id=?"
            );
            pstmt.setString(1, country.getCountryId());
            pstmt.setString(2, country.getCountryName());
            pstmt.setString(3, currID);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String id) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM country WHERE id=?");
            pstmt.setString(1, id);
            pstmt.executeUpdate(); //used for DML command (insert,update, delete)
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Country getById(String id) {
        Country country = new Country();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM country WHERE ID = ?");
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                country.setCountryId(rs.getString(1));
                country.setCountryName(rs.getString(2));
                country.setCountryRegion(rs.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return country;
    }
}
