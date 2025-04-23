<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>난이도 선택</title>
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
    <%
        // URL에서 카테고리 매개변수 가져오기
        String category = request.getParameter("category");
    %>
    <div class="container">
        <h1><%= request.getParameter("category") %> - 난이도 선택</h1>
        <p>문제의 난이도를 선택해주세요.</p>
        <form action="generateCodingProblem" method="post">
	        <div class="buttons">
	       		<input type="hidden" name="category" value="<%= request.getParameter("category") %>">
	            <button type="submit" name="difficulty" value="하">하</button>
		        <button type="submit" name="difficulty" value="중">중</button>
		        <button type="submit" name="difficulty" value="상">상</button>
	        </div>
        </form>
    </div>
</body>
</html>
