package servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/check-duplicate")
public class CheckDuplicateServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/user"; 	// DB URL
    private static final String DB_USER = "test"; 								// 사용자 이름
    private static final String DB_PASSWORD = "password12!"; 					// 비밀번호

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");		// 클라이언트에서 전달받은 아이디 추출
        boolean exists = false;		// 중복 여부 기본값을 false로 지정

        // 데이터베이스 연결 및 쿼리 실행
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        
        try {
            // 데이터베이스 연결
        	Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 아이디 중복 확인을 위한 SQL문
            String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);	// 사용자 입력값 지정

            resultSet = statement.executeQuery();	// 쿼리문 실행

            // 결과 처리
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                exists = count > 0;
            } else {
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
            // 리소스 정리
            try {
                if (resultSet != null) resultSet.close();		// ResultSet 닫기
                if (statement != null) statement.close();		// PreparedStatement 닫기
                if (connection != null) connection.close();		// Connection 닫기
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // JSON 형식으로 응답 전송
        sendJsonResponse(response, exists);
    }

    private void sendJsonResponse(HttpServletResponse response, boolean exists) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print("{\"exists\": " + exists + "}");	// JSON 형태로 결과 전송
        }
    }
}


