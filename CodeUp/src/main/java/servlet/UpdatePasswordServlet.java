package servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/updatePassword")
public class UpdatePasswordServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/user";	// DB URL
    private static final String DB_USER = "test";								// 사용자 이름
    private static final String DB_PASSWORD = "password12!";					// 비밀번호

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	// 세션에서 로그인된 사용자 정보 가져오기
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");	// 세션에 저장된 사용자 이름
        
        // 세션에 사용자 정보가 없는 경우
        if (username == null) {
            response.sendRedirect("login.jsp");		// 로그인 화면으로 이동
            return;
        }
        
        // 클라이언트로부터 전달받은 현재 비밀번호와 새 비밀번호
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");

        try (
        	// DB 연결
        	Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement checkStatement = connection.prepareStatement(
                     "SELECT * FROM users WHERE username = ? AND password = ?");
            PreparedStatement updateStatement = connection.prepareStatement(
                     "UPDATE users SET password = ? WHERE username = ?")) {

            // 현재 비밀번호 확인
            checkStatement.setString(1, username);
            checkStatement.setString(2, currentPassword);

            try (ResultSet resultSet = checkStatement.executeQuery()) {
                // 현재 비밀번호가 일치하는 경우
            	if (resultSet.next()) {
                    // 새 비밀번호로 변경하는 쿼리문 실행
                    updateStatement.setString(1, newPassword);
                    updateStatement.setString(2, username);
                    updateStatement.executeUpdate();
                    
                    // 성공 메시지 출력, 마이페이지 화면으로 이동
                    response.setContentType("text/html; charset=UTF-8");
                    try (PrintWriter out = response.getWriter()) {
                        out.println("<script>");
                        out.println("alert('비밀번호가 변경되었습니다.');");
                        out.println("location.href = 'myPage.jsp';");
                        out.println("</script>");
                    }
                } else {
                    // 현재 비밀번호가 일치하지 않을 경우
                    response.setContentType("text/html; charset=UTF-8");
                    try (PrintWriter out = response.getWriter()) {
                        out.println("<script>");
                        out.println("alert('현재 비밀번호가 올바르지 않습니다.');");
                        out.println("history.back();");
                        out.println("</script>");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
