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

@WebServlet("/generateCodingProblem")
public class CodingProblemServlet extends HttpServlet {
    private static final String API_URL = "";
    private static final String API_KEY = "";

    @SuppressWarnings("unchecked")
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 요청과 응답의 인코딩 설정
        request.setCharacterEncoding("UTF-8"); // 요청 데이터 인코딩 설정
        response.setContentType("text/html; charset=UTF-8"); // 응답 콘텐츠 타입 설정
        response.setCharacterEncoding("UTF-8"); // 응답 데이터 인코딩 설정
        
    	String category = request.getParameter("category");
        String difficulty = request.getParameter("difficulty");

        String generatedProblem = null; // ChatGPT가 생성한 문제

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(API_URL);
            httpPost.setHeader("Authorization", "Bearer " + API_KEY);
            httpPost.setHeader("Content-Type", "application/json");

            // ChatGPT 요청 JSON 데이터 생성
            JSONObject json = new JSONObject();
            json.put("model", "gpt-3.5-turbo");
            json.put("max_tokens", 150);
            json.put("temperature", 0.2); // 랜덤성 최소화
            json.put("top_p", 1.0); // 가능성 높은 응답 우선 선택

            JSONArray messages = new JSONArray();
            JSONObject message = new JSONObject();
            message.put("role", "user");
            message.put("content", "Generate a coding problem about " + category + " in "
            			+ difficulty + "level with Java. Generate with Korean.");
            messages.add(message);
            json.put("messages", messages);

            httpPost.setEntity(new StringEntity(json.toJSONString()));

            // ChatGPT API 호출 및 결과 처리
            try (CloseableHttpResponse apiResponse = httpClient.execute(httpPost)) {
                String responseBody = EntityUtils.toString(apiResponse.getEntity());
                System.out.println("API Response: " + responseBody); // 응답 출력

                // JSON 응답에서 문제 추출
                JSONObject jsonResponse = (JSONObject) new org.json.simple.parser.JSONParser().parse(responseBody);
                JSONArray choices = (JSONArray) jsonResponse.get("choices");
                JSONObject choice = (JSONObject) choices.get(0);
                JSONObject messageObject = (JSONObject) choice.get("message"); // message 객체 확인
                generatedProblem = (String) messageObject.get("content"); // 문제 텍스트 추출
            }
        } catch (Exception e) {
            e.printStackTrace();
            generatedProblem = "문제를 생성하는 동안 오류가 발생했습니다.";
        }

        // JSP로 전달
        request.setAttribute("category", category);
        request.setAttribute("difficulty", difficulty);
        request.setAttribute("problem", generatedProblem);

        // JSP로 포워딩
        request.getRequestDispatcher("example.jsp").forward(request, response);
    }
}

