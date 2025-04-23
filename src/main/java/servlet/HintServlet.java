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

@WebServlet("/hint")
public class HintServlet extends HttpServlet {

	 private static final String API_URL = "https://api.openai.com/v1/chat/completions";
	 private static final String API_KEY = "sk-proj-Z1m-r7NZmBTpyxQjA7OqIhbrikcopBkRVeOUc"
											+ "t10EkZ95V5IDcT_-mBcRWYT4oNb0W4ta6c5G3T3Blbk"
											+ "FJWyND-rV-wcTItbwOVXcZLfyNhELoIp4T4lS8LYH8XB"
											+ "Rc1mHiWHJtU3FRQ4xmP12GGFzBahJa0A";

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	// 요청과 응답의 인코딩 설정
	        request.setCharacterEncoding("UTF-8"); // 요청 데이터 인코딩 설정
	        response.setContentType("application/json; charset=UTF-8");
	        response.setCharacterEncoding("UTF-8"); // 응답 데이터 인코딩 설정
	    	
	    	String category = request.getParameter("category");
	        String difficulty = request.getParameter("difficulty");
	        String problem = request.getParameter("problem");
	        
	        String generatedHint = null; // ChatGPT가 생성한 힌트

	        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
	            HttpPost httpPost = new HttpPost(API_URL);
	            httpPost.setHeader("Authorization", "Bearer " + API_KEY);
	            httpPost.setHeader("Content-Type", "application/json");

	            // ChatGPT 요청 JSON 데이터 생성
	            JSONObject json = new JSONObject();
	            json.put("model", "gpt-3.5-turbo");
	            json.put("max_tokens", 50); // 힌트는 짧게
	            json.put("temperature", 0.7);

	            JSONArray messages = new JSONArray();
	            JSONObject message = new JSONObject();
	            message.put("role", "user");
	            message.put("content", "Provide a simple hint for " + "문제 : 사용자로부터 입력받은 숫자가 짝수인지 홀수인지 판별하는 프로그램을 작성하시오. "
	            		+ "입력 : 사용자로부터 하나의 정수를 입력받는다. "
	            		+ "출력 : 입력받은 숫자가 짝수일 경우 \"짝수입니다.\"를 출력하고, 홀수일 경우 \"홀수입니다.\"를 출력한다."
	            		+ "예시 : 입력 : 6  출력 : 짝수입니다. 입력 : 3 출력 : 홀수입니다." + 
	            						". Generate with Korean.");
	            messages.add(message);
	            
	            json.put("messages", messages);

	            httpPost.setEntity(new StringEntity(json.toJSONString()));

	            // ChatGPT API 호출 및 결과 처리
	            try (CloseableHttpResponse apiResponse = httpClient.execute(httpPost)) {
	                String responseBody = EntityUtils.toString(apiResponse.getEntity());
	                System.out.println("API Response: " + responseBody); // 응답 출력
	                
	                response.getWriter().print(responseBody);
	        } catch (Exception e) {
	        	 e.printStackTrace();
	             response.getWriter().print("{\"error\": \"힌트를 가져오는 동안 오류가 발생했습니다.\"}");
	        }
	    }
	}
}
