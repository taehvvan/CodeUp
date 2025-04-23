package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/user";	// DB URL
    private static final String DB_USER = "test";								// 사용자이름
    private static final String DB_PASSWORD = "password12!";					// 비밀번호

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 클라이언트로부터 전달받은 파라미터(아이디, 비밀번호) 추출
    	String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("text/html; charset=UTF-8");

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        	
        	// 비밀번호를 조회하는 SQL문
            PreparedStatement stmt = conn.prepareStatement("SELECT password FROM users WHERE username = ?");

            stmt.setString(1, username);	// 사용자 입력값 지정
            ResultSet rs = stmt.executeQuery();

            // 아이디가 존재하는 경우
            if (rs.next()) {
                String storedPassword = rs.getString("password");	// 데이터베이스에 저장된 비밀번호 가져오기
                if (storedPassword.equals(password)) {				// 입력된 비밀번호와 일치하는지 확인
                    // 로그인 성공: 세션에 사용자 이름 저장
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);

                    // 메인 페이지로 이동
                    response.sendRedirect("main.jsp");
                } else {
                	// 비밀번호가 불일치할 경우
                    response.getWriter().println("<p>비밀번호가 틀렸습니다.</p>");
                    response.getWriter().println("<a href='login.jsp'>다시 시도</a>");
                }
            } else {
            	// 아이디가 존재하지 않는 경우
                response.getWriter().println("<p>아이디가 존재하지 않습니다.</p>");
                response.getWriter().println("<a href='login.jsp'>다시 시도</a>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<p>데이터베이스 오류가 발생했습니다: " + e.getMessage() + "</p>");
        }
    }
}
