package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/check")
public class CheckCodeServlet {
    	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
       String username = (String) session.getAttribute("username");

       if (username == null) {
           response.sendRedirect("login.jsp");
           return;
       }
	
		String userCode = request.getParameter("userCode");
	    // GPT API 응답에서 content를 추출했다고 가정
	    String content = """
	            import java.util.Scanner;
	
	            public class EvenOddChecker {
	                public static void main(String[] args) {
	                    Scanner scanner = new Scanner(System.in);
	                    System.out.print("숫자를 입력하세요: ");
	                    int number = scanner.nextInt();
	                    if (number % 2 == 0) {
	                        System.out.println("짝수입니다.");
	                    } else {
	                        System.out.println("홀수입니다.");
	                    }
	                    scanner.close();
	                }
	            }
	            """;
	
	    // 줄바꿈 문제 해결 및 배열에 저장
	    String[] codeLines = content.replace("\r\n", "\n").split("\n");
	    String explanation = null;
	    
	    boolean foundAnswer = false;

        // 문자열 배열을 한 줄씩 확인
        for (String line : codeLines) {
            if (line.contains("if (number % 2 == 0)")) {
            	explanation = "정답입니다.";
                foundAnswer = true;
                break; // 조건을 만족하면 반복 종료
            }
        }

        if (!foundAnswer) {
        	explanation = "정답이 아닙니다.";
        }
	
	    // 배열 요소 출력
	    System.out.println("코드 출력:");
	    for (String line : codeLines) {
	        System.out.println("|" + line.trim() + "|"); // 공백 포함 여부 확인
	    }
	    
	    request.setAttribute("explanation", explanation);	// 정답/오답 메시지 출력
	    request.setAttribute("correctCode", content);
	    
	    response.sendRedirect("resultPage2.jsp");
    }
    
}
