package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class MyPageServlet
 */
@WebServlet("/myPage")
public class MyPageServlet extends HttpServlet {
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/user";
    private static final String DB_USER = "test";
    private static final String DB_PASSWORD = "password12!";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 세션에서 사용자 이름 가져오기
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");

        if (username == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<UserSolution> solutions = new ArrayList<>();
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        	PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user_solutions WHERE username = ?");
            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    UserSolution solution = new UserSolution();
                    System.out.println("Category: " + rs.getString("category"));
                    System.out.println("Difficulty: " + rs.getString("difficulty"));
                    System.out.println("Solution: " + rs.getString("solution_text"));
                    solution.setCategory(rs.getString("category"));
                    solution.setDifficulty(rs.getString("difficulty"));
                    solution.setSolutionText(rs.getString("solution_text"));
                    solution.setCreatedAt(rs.getTimestamp("created_at"));
                    solutions.add(solution);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // JSP에 데이터 전달
        request.setAttribute("solutions", solutions);
        request.getRequestDispatcher("myPage.jsp").forward(request, response);
    }

}
