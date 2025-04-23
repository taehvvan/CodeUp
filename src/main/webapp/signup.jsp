<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <script src="signup.js" defer></script> <!-- JavaScript 파일 연결 -->
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }
        form {
            display: inline-block;
            text-align: left;
            margin-top: 50px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #fff;
        }
        label {
            font-size: 16px;
            display: block;
            margin-bottom: 10px;
        }
        input {
            width: 100%;
            padding: 10px 0;
            margin-bottom: 20px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            padding: 10px 20px;
            font-size: 14px;
            border: none;
            border-radius: 5px;
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
        .error {
            color: red;
            font-size: 14px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <h1>회원가입</h1>
    <form action="register" method="post" onsubmit="return validateForm()">
        <label for="username">아이디:</label>
        <input type="text" id="username" name="username" required>
        <button type="button" onclick="checkDuplicate()">중복 확인</button>
        <div id="duplicateMessage" style="color: red;"></div>

        <label for="password">비밀번호:</label>
        <input type="password" id="password" name="password" required>

        <button type="submit">회원가입</button>
    </form>
</body>
</html>
