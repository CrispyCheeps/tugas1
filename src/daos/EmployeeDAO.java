package daos;

public class EmployeeDAO {

}

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

PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Department(id, first_name," +
"last_name, email, phone_number, hire_date, job, salary, comission_pct, manager, department) VALUES(?,?,?,?,?,?,?,?,?,?,?)");