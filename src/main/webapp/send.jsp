<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="EUC-KR"%>
<%
try
{
	Class.forName("com.mysql.cj.jdbc.Driver");
	
	String db_address = "jdbc:mysql://localhost:3306/user";
	String db_username = "root";
	String db_pwd = "1234";
	
	Connection connection = DriverManager.getConnection(db_address, db_username, db_pwd);
	request.setCharacterEncoding("UTF-8");
	
	String category = request.getParameter("category");
	String difficulty = request.getParameter("difficulty");
	String userCode = request.getParameter("userCode");
	
	int num = 0;
	
	String insertQuery = "SELECT MAX(num) from user_solutions";
	PreparedStatement psmt = connection.prepareStatement(insertQuery);
	ResultSet result = psmt.executeQuery();
	
	while(result.next()) {
		num = result.getInt("MAX(num)") + 1;
	}
	
	insertQuery = "INSERT INTO user_solutions(num, category, difficulty) VALUES (?, ?, ?)";
	
	psmt = connection.prepareStatement(insertQuery);
	
	psmt.setInt(1, num);
	psmt.setString(2, category);
	psmt.setString(3, difficulty);
	
	psmt.executeUpdate();
	
	request.setAttribute("category", category);
    request.setAttribute("difficulty", difficulty);
    
    if(category.equals("if") && difficulty.equals("중")){
    	request.getRequestDispatcher("example_if.jsp").forward(request, response);
    } else {
    	request.getRequestDispatcher("generateCodingProblem").forward(request, response);
    }
} catch (Exception ex) {
	out.println("오류가 발생했습니다. 오류 메시지 : " + ex.getMessage());
}
%>
