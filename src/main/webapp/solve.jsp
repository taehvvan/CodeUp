<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>문제 풀이</title>
    <script>
        function showHint() {
            const problem = document.getElementById("problem").innerText; // 문제 설명 가져오기
            fetch("hint?problem=" + encodeURIComponent(problem))
                .then(response => response.text())
                .then(data => {
                    document.getElementById("hint").innerText = data;
                    document.getElementById("hint").style.display = "block";
                })
                .catch(error => {
                    console.error("Error fetching hint:", error);
                    document.getElementById("hint").innerText = "힌트를 가져오는 중 오류가 발생했습니다.";
                    document.getElementById("hint").style.display = "block";
                });
        }
    </script>
</head>
<body>
    <h2>문제</h2>
    <p id="problem">사용자로부터 숫자를 입력받아 양수인지 음수인지 판단하는 프로그램을 작성하세요.</p>

    <button onclick="showHint()">힌트 보기</button>
    <p id="hint" style="display:none; color:blue; margin-top:10px;"></p>
</body>
</html>

