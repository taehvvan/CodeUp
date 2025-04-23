<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>로그인 페이지</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #d3d3d3;
        }
        .login-container {
            width: 300px;
            padding: 20px;
            background-color: white;
            border: 1px solid #ccc;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h2 {
            margin-bottom: 20px;
        }
        label {
            display: block;
            font-size: 14px;
            margin: 10px 0 5px;
            text-align: left;
        }
        input[type="text"], input[type="password"] {
            width: calc(100% - 20px);
            padding: 8px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-bottom: 15px;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #333;
            color: white;
            font-size: 14px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #555;
        }
        .signup-link {
            display: block;
            margin-top: 10px;
            font-size: 12px;
            color: #555;
        }
        .signup-link a {
            text-decoration: none;
            color: #007BFF;
        }
        .signup-link a:hover {
            text-decoration: underline;
        }
        .error-message {
            color: red;
            margin-top: 10px;
            font-size: 12px;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>로그인</h2>
        <form action="login" method="post">
            <label for="username">아이디:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">비밀번호:</label>
            <input type="password" id="password" name="password" required>

            <button type="submit">로그인</button>
        </form>
        
        <p class="signup-link">계정이 없으신가요? <a href="signup.jsp">회원가입</a></p>
    </div>
</body>
</html>
