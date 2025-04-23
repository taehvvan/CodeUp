<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title><%= request.getAttribute("category") %> 예제 - <%= request.getAttribute("difficulty") %> 난이도</title>
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
            width: 90%; /* 반응형을 고려한 너비 */
            max-width: 800px;
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
        p {
            font-size: 18px;
            margin-bottom: 20px;
            text-align: left;
        }
        .textarea-container {
            margin-bottom: 20px;
            text-align: left;
        }
        textarea {
            width: calc(100% - 20px); /* 부모 요소 안에 정확히 맞도록 조정 */
            height: 200px;
            font-size: 16px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            resize: none;
            font-family: monospace; /* 코드 스타일 폰트 */
            box-sizing: border-box; /* 패딩과 보더를 포함한 크기 조정 */
        }
        .buttons {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 20px;
        }
        button {
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease, color 0.3s ease;
        }
        .btn-hint {
            background-color: #007bff;
            color: #fff;
        }
        .btn-hint:hover {
            background-color: #0056b3;
        }
        .btn-submit {
            background-color: #28a745;
            color: #fff;
        }
        .btn-submit:hover {
            background-color: #218838;
        }
        #hint {
            display: none;
            margin-top: 10px;
            text-align: left;
            color: #555;
            background-color: #f0f0f0;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        
        #hintModal {
            display: none; /* 기본적으로 숨김 */
            position: fixed;
            z-index: 1000;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
        }

        #hintContent {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            text-align: center;
            width: 400px;
        }

        #hintContent p {
            margin: 10px 0;
        }

        #closeHint {
            background: red;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 5px;
            cursor: pointer;
        }
    </style>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title><%= request.getAttribute("category") %> 예제 - <%= request.getAttribute("difficulty") %> 난이도</title>
    <script>
        function getHint() {
        	document.getElementById("hintModal").style.display = "block";
        	
        	const problem = `<%= request.getAttribute("problem") %>`;
            const difficulty = `<%= request.getAttribute("difficulty") %>`;

            // ChatGPT API 호출
            fetch("getHint", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: `problem=<%= request.getAttribute("problem") %>&difficulty=<%= request.getAttribute("difficulty") %>`
                })
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    document.getElementById("hintText").innerText = "힌트를 가져오는 데 실패했습니다.";
                } else {
                    const hint = data.choices[0].message.content; // ChatGPT의 응답
                    document.getElementById("hintText").innerText = hint;
                }
            })
            .catch(error => {
                console.error("Error fetching hint:", error);
                document.getElementById("hintText").innerText = "힌트를 가져오는 중 오류가 발생했습니다.";
            });
        }
        
        function closeHint() {
            document.getElementById("hintModal").style.display = "none";
        }
    </script>
</head>
<body>
    
    <div class="container">
        <h1><%= request.getAttribute("category") %> 예제 - <%= request.getAttribute("difficulty") %> 난이도</h1>
    	<p><pre><%= request.getAttribute("problem") %></pre></p>

        <div class="textarea-container">
            <textarea id="userCode" placeholder="여기에 코드를 입력하세요"></textarea>
        </div>

        <div class="buttons">
            <button class="btn-hint" type="button" onclick="getHint()">힌트 보기</button>
            <form action="submit" method="post" style="margin: 0; display: inline;">
                <input type="hidden" name="category">
                <input type="hidden" name="difficulty">
                <input type="hidden" name="problem">
                <input type="hidden" id="codeInput" name="userCode">
                <button class="btn-submit" type="submit">제출</button>
            </form>
        </div>
        <div id="hintModal">
	        <div id="hintContent">
	            <p id="hintText">힌트를 가져오는 중...</p>
	            <button id="closeHint" onclick="closeHint()">닫기</button>
	        </div>
    	</div>
    </div>
</body>
</html>
