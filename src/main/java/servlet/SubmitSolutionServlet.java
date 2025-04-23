package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/SubmitSolutionServlet")
public class SubmitSolutionServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/user";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try  {
	    	HttpSession session = request.getSession(false);
	        String username = (String) session.getAttribute("username");
	
	        if (username == null) {
	            response.sendRedirect("login.jsp");
	            return;
	        }
	
	        // 요청으로부터 데이터 가져오기
	        String category = request.getParameter("category");
	        String difficulty = request.getParameter("difficulty");
	        
	        int num = 0;
	        
	        String insertQuery = "SELECT MAX(num) from user_solutions";
	
	        // 데이터베이스에 저장
	        
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        	PreparedStatement stmt = conn.prepareStatement(insertQuery);
                    
        	ResultSet result = stmt.executeQuery();
        	
        	while(result.next())
        	{
        		// 앞서 임시 선언한 num 변수에, 가져온 MAX(num) 칼럼값 + 1을 하여 저장
        		num = result.getInt("MAX(num)") + 1;
        	}
        	
        	insertQuery = "INSERT INTO user_solutions (num, category, difficulty) VALUES (?, ?, ?)";
        	
        	stmt.setInt(1, num);
            stmt.setString(2, category); // null 방지
            stmt.setString(3, difficulty); // null 방지
            
            stmt.executeUpdate();
            
         // 메인 페이지로 리다이렉트
            response.sendRedirect("main.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
        
    }
}
