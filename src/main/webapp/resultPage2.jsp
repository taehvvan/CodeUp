<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>결과 페이지</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            margin: 20px;
        }
        h2 {
            color: #333;
        }
        p {
            margin-bottom: 20px;
        }
        .code-box {
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            padding: 15px;
            border-radius: 5px;
            font-family: 'Courier New', Courier, monospace;
            overflow-x: auto;
            white-space: pre-wrap;
            line-height: 1;
            margin-top: 10px;
        }
        .result-message {
            font-size: 18px;
            font-weight: bold;
            margin-bottom: 20px;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 15px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h2>제출 결과</h2>

    <!-- 결과 메시지 -->
    <p class="result-message">
        <%= request.getAttribute("explanation") %>
    </p>

    <!-- 정답 코드가 있을 경우 박스에 출력 -->
    <h3>정답 예시:</h3>
    <div class="code-box">
        <p>import java.util.Scanner;<br><br>
	  		public class EvenOddChecker {<br>
	  			public static void main(String[] args) {<br>
	  				Scanner scanner = new Scanner(System.in);<br>
	  				System.out.print("숫자를 입력하세요: ");<br>
	  				int number = scanner.nextInt();<br>
	  				if (number % 2 == 0) {<br>
	  					System.out.println("짝수입니다.");<br>
	  				} else {<br>
	  					System.out.println("홀수입니다.");<br>
	  				}<br>
	  			scanner.close();<br>
	  			}<br>
	  		}<br>
       	</p>   
    </div>

    <!-- 메인 페이지로 이동 버튼 -->
    <form action="main.jsp" method="post">
	    <button type="submit">메인으로 돌아가기</button>
	</form>
	
	

</body>
</html>
