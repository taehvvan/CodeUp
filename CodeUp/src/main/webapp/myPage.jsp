<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.List, servlet.UserSolution" %>
<%
    String username = (String) session.getAttribute("username");

    if (username == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>마이페이지</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
        }
        header {
            background-color: aqua;
            color: white;
            padding: 15px 20px;
            text-align: center;
        }
        header h1 {
            margin: 0;
            font-size: 24px;
        }
        .container {
            max-width: 800px;
            margin: 20px auto;
            background-color: white;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            overflow: hidden;
        }
        .content {
            padding: 20px;
            text-align: center;
        }
        .content h2 {
            margin: 0;
            margin-bottom: 10px;
            font-size: 22px;
            color: #333;
        }
        .content p {
            font-size: 18px;
            margin: 10px 0;
            color: #555;
        }
        .form-container {
            margin-top: 20px;
            text-align: left;
        }
        form {
            margin: 0 auto;
            max-width: 400px;
        }
        label {
            display: block;
            font-size: 16px;
            margin-bottom: 5px;
            color: #333;
        }
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }
        button {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        button:hover {
            background-color: #0056b3;
        }
        .logout {
            margin-top: 15px;
            background-color: #dc3545;
        }
        .logout:hover {
            background-color: #a71d2a;
        }
    </style>
</head>
<body>
    <header>
        <a href="main.jsp">메인 화면</a>
        <a href="updatePassword.jsp">비밀번호 변경</a>
        <a href="logout">로그아웃</a>
    </header>
    <main align="center">
        <h1>마이페이지</h1>
        <p>아이디: <%= username %></p>
        
        <%
try
{
	// JDBC 드라이버 연결
	Class.forName("com.mysql.cj.jdbc.Driver");
	String db_address = "jdbc:mysql://localhost:3306/user";
	String db_username = "root";
	String db_pwd = "1234";
	Connection connection = DriverManager.getConnection(db_address, db_username, db_pwd);
	// MySQL로 전송하기 위한 쿼리문인 insertQuery 문자열 선언
	String insertQuery = "SELECT * FROM user_solutions order by num desc";
	// MySQL 쿼리문 실행
	PreparedStatement psmt = connection.prepareStatement(insertQuery);
	// 쿼리문을 전송해 받아온 정보를 result 객체에 저장
	ResultSet result = psmt.executeQuery();
	
	%>
	
	<table border="1" align="center">
		<tr>
			<td colspan="4">
			<h3>풀었던 문제 목록</h3>
			</td>
		</tr>
		<tr>
			<td>번호</td>
			<td>카테고리</td>
			<td>난이도</td>
		</tr>
			<%
			// 받아온 정보를 입력하고, 하나씩 커서를 다음으로 넘김
			while (result.next())
			{%>
				<tr>
					<td><%=result.getInt("num") %></td>
					<td><%=result.getString("category") %></td>
					<td><%=result.getString("difficulty") %></td>
				</tr>
			<%
		}%>
	</table>
	<%
}
catch (Exception ex)
{
out.println("오류가 발생했습니다. 오류 메시지 : " + ex.getMessage());
}
%>
        
    </main>
    
</body>
</html>
