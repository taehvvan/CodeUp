<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    String username = (String) session.getAttribute("username");

    if (username == null) {
        // 세션에 username이 없으면 로그인 페이지로 리다이렉트
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CodeUp</title>
    <style>
    	header {
            position: fixed;
            top: 0;
            right: 0;
            background-color: #f4f4f4;
            padding: 10px 20px;
            border-bottom-left-radius: 10px;
            text-align: right;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        header p {
            margin: 0;
            font-size: 16px;
            color: #333;
        }
        header a {
            margin-left: 10px;
            text-decoration: none;
            color: #007bff;
            font-size: 14px;
        }
        header a:hover {
            text-decoration: underline;
        }
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }
        h1 {
            font-size: 36px;
            margin-top: 50px;
        }
        p {
            font-size: 18px;
            color: #333;
        }
        .button-container {
            display: inline-block;
            margin-top: 30px;
        }
        .button-container button {
            display: block;
            width: 300px;
            padding: 15px;
            margin: 10px 0;
            font-size: 18px;
            background-color: #fff;
            border: 2px solid #333;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease, color 0.3s ease;
        }
        .button-container button:hover {
            background-color: #333;
            color: #fff;
        }
    </style>
</head>
<body>
	<header>
        <p><%= username %>님, 환영합니다.</p>
        <a href="myPage.jsp">마이페이지</a>
        <a href="logout">로그아웃</a>
    </header>
    <h1>CodeUp</h1>
    
    <p>CodeUp에 오신 것을 환영합니다.<br>문제를 풀고 싶은 카테고리와 난이도를 입력해주세요.</p>
    <form action="send.jsp" method="post">
	    카테고리 : <input type="text" name="category">&nbsp;&nbsp;&nbsp;&nbsp;
	    난이도 : <input type="text" name="difficulty">&nbsp;&nbsp;&nbsp;&nbsp;
    
    	<button type="submit" name="move">이동</button>
    </form>
    <h3>카테고리 : if, for, while, switch-case, 생성자, 업캐스팅, 다운캐스팅</h3>
    <h3>메소드 오버라이딩, 접근지정자, 인터페이스, 상속, 파일입출력 등</h3>
    <h3>난이도 : 상, 중, 하</h3>

</body>
</html>
