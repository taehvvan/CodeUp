package servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/register")		// "/register" URL로 요청을 받음
public class RegisterServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/user";	// DB URL
    private static final String DB_USER = "test";								// DB 사용자 이름
    private static final String DB_PASSWORD = "password12!";					// DB 비밀번호

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	// 클라이언트로부터 전달받은 파라미터(아이디와 비밀번호) 추출
    	String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean exists = false;		// 아이디 중복 여부를 저장할 변수

        try (
        	// 데이터베이스 연결 생성
        	Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        	// 아이디 중복 확인을 위해 동일한 username의 개수를 세는 SQL문
        	PreparedStatement checkStatement = connection.prepareStatement("SELECT COUNT(*) FROM users WHERE username = ?");
        	// 사용자 정보를 추가하기 위한 SQL문
        	PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)")
            ){
        	Class.forName("com.mysql.cj.jdbc.Driver");
            // 중복 체크
            checkStatement.setString(1, username);		// SQL문에 사용자 입력값 설정
            try (ResultSet resultSet = checkStatement.executeQuery()) {
            	// 중복된 아이디가 있을 경우
                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    exists = true;	// 중복 확인 변수를 true로 설정
                }
            }
            
            // 중복 아이디인 경우 경고 출력 및 돌아가기
            if (exists) {
                response.setContentType("text/html; charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    out.println("<script>");
                    out.println("alert('이미 존재하는 아이디입니다. 다른 아이디를 입력하세요.');");
                    out.println("history.back();");
                    out.println("</script>");
                }
            } else {
                // 아이디가 중복되지 않으면 회원가입 처리
                insertStatement.setString(1, username);		// 아이디 설정
                insertStatement.setString(2, password);		// 비밀번호 설정
                insertStatement.executeUpdate();			// 데이터베이스에 정보 삽입

                response.sendRedirect("login.jsp"); // 성공 시 로그인 화면으로 이동
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
