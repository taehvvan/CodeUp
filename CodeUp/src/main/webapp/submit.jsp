<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: #f9f9f9;
        }
        .container {
            width: 90%;
            max-width: 600px;
            padding: 20px;
            border: 1px solid #ccc;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h1 {
            font-size: 24px;
            margin-bottom: 20px;
        }
        .buttons {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }
        .buttons button {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .buttons button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
	<div class="container">
        <p>제출 완료되었습니다.</p>
        <form action="SubmitSolutionServlet" method="post">
	        <div class="buttons">
	       		<button type="submit" onclick="location.href='main.jsp'">메인으로</button>
	        </div>
        </form>
    </div>
</body>
</html>