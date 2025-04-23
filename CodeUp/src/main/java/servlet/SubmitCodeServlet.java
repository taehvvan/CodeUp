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

@WebServlet("/submit")
public class SubmitCodeServlet extends HttpServlet {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/user";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";
	private static final String API_URL = "";
	 private static final String API_KEY = "";

	 @SuppressWarnings("unchecked")
	@Override
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");

        if (username == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String userCode = request.getParameter("userCode");
        String problem = request.getParameter("problem");
        String category = request.getParameter("category");
        String difficulty = request.getParameter("difficulty");

        String correctCode = null;
        String explanation = null;
        boolean isCorrect = false;

     // ChatGPT API 호출하여 정답 코드 및 설명 생성
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
        	
            HttpPost httpPost = new HttpPost(API_URL);
            httpPost.setHeader("Authorization", "Bearer " + API_KEY);
            httpPost.setHeader("Content-Type", "application/json");

            // 요청 JSON 생성
            JSONObject json = new JSONObject();
            json.put("model", "gpt-3.5-turbo");
            json.put("max_tokens", 300);
            json.put("temperature", 0.2);
            json.put("top_p", 1.0); // 가능성 높은 응답 우선 선택
            
            JSONArray messages = new JSONArray();
            JSONObject message = new JSONObject();
            message.put("role", "user");
            message.put("content", "Provide the correct solution for "
            		+ "the following problem in Java:\n "
            		+ "문제 : 사용자로부터 입력받은 숫자가 짝수인지 홀수인지 판별하는 프로그램을 작성하시오. "
            		+ "입력 : 사용자로부터 하나의 정수를 입력받는다. "
            		+ "출력 : 입력받은 숫자가 짝수일 경우 \"짝수입니다.\"를 출력하고, 홀수일 경우 \"홀수입니다.\"를 출력한다."
            		+ "예시 : 입력 : 6  출력 : 짝수입니다. 입력 : 3 출력 : 홀수입니다.");
            messages.add(message);
            json.put("messages", messages);
            httpPost.setEntity(new StringEntity(json.toJSONString()));

            // API 호출 및 응답 처리
            try (CloseableHttpResponse apiResponse = httpClient.execute(httpPost)) {
            	String responseBody = EntityUtils.toString(apiResponse.getEntity());
            	
            	JSONObject jsonResponse = (JSONObject) 
            			new org.json.simple.parser.JSONParser().parse(responseBody);
                JSONArray choices = (JSONArray) jsonResponse.get("choices");
                JSONObject choice = (JSONObject) choices.get(0);
                JSONObject messageObject = (JSONObject) choice.get("message"); // message 객체 확인
                correctCode = (String) messageObject.get("content"); // 문제 텍스트 추출
                
                
            }
           

            // 사용자 코드와 정답 코드 비교
            isCorrect = userCode.trim().equals(correctCode.trim());
            explanation = isCorrect ? "정답입니다." : "정답이 아닙니다.";
        } catch (Exception e) {
            e.printStackTrace();
            explanation = "정답 코드를 생성하는 중 오류가 발생했습니다.";
        }

        // 결과 JSP로 전달
        request.setAttribute("username", username);
        request.setAttribute("category", category);
        request.setAttribute("difficulty", difficulty);
        request.setAttribute("problem", problem);
        request.setAttribute("isCorrect", isCorrect);		// 정답 여부를 담은 boolean 변수
        request.setAttribute("explanation", explanation);	// 정답/오답 메시지 출력
        request.setAttribute("userCode", userCode);			// 사용자가 입력한 코드
        request.setAttribute("correctCode", correctCode);	// GPT가 생성한 정답 코드
        
        response.sendRedirect("resultPage.jsp");
	 }
}
