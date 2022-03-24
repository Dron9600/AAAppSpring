package kz.prudnikov.airAstanaAppSpring.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    static String url = "jdbc:postgresql://localhost:5432/airastanadb";
    static String user = "postgres";
    static String password = "postgres";
    static Connection connection;


        public List direction () throws ClassNotFoundException, SQLException {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);

            List directionList = new ArrayList();
            String selectSQL = "SELECT direction FROM routes";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                directionList.add(rs.getString(1));
            }
            return directionList;
    }

        public String countres(String direction) throws SQLException, ClassNotFoundException {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            String selectSQL = "SELECT countres FROM routes WHERE direction = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, direction);
            String answer = null;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                answer = rs.getString("countres");
            }
            return answer;
        }
//            String direction = "ALA/MSK";
//            String insertSQL = "INSERT INTO routes(direction) VALUES(?)";
//        PreparedStatement pstmt = connection.prepareStatement(insertSQL);
//
//        pstmt.setString(1, direction);
//        pstmt.executeUpdate();

}
