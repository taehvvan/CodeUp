package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        // 데이터베이스 연결 정보
        String jdbcURL = "jdbc:mysql://localhost:3306/user"; // DB URL
        String dbUser = "test"; // 사용자 이름
        String dbPassword = "password12!"; // 비밀번호

        try {
            // JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 데이터베이스 연결
            System.out.println("Connecting to database...");
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // 연결 확인
            System.out.println("Database connected!");

            // 샘플 쿼리 실행
            String sql = "SELECT 1"; // 간단한 테스트 쿼리
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // 쿼리 결과 확인
            if (resultSet.next()) {
                System.out.println("Query successful: " + resultSet.getInt(1));
            }

            // 리소스 정리
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            // 오류 출력
            e.printStackTrace();
        }
    }
}

