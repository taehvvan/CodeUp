<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="javax.servlet.http.HttpSession" %>
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

        <!-- 비밀번호 변경 -->
        <form action="updatePassword" method="post">
            <label for="currentPassword">현재 비밀번호:</label>
            <input type="password" id="currentPassword" name="currentPassword" required><br>
            <label for="newPassword">새 비밀번호:</label>
            <input type="password" id="newPassword" name="newPassword" required><br>
            <button type="submit">비밀번호 변경</button>
        </form>
    </main>
</body>
</html>
