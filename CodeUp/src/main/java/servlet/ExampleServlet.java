package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet("/example")
public class ExampleServlet extends HttpServlet {

    // 각 카테고리별 문제 배열
    private final String[] ifQuestions = {
        "숫자를 입력받아 양수인지 음수인지 판별하는 코드를 작성하세요.",
        "두 숫자를 입력받아 두 숫자가 같은지 판별하는 코드를 작성하세요.",
        "숫자를 입력받아 홀수인지 짝수인지 판별하는 코드를 작성하세요."
    };

    private final String[] forQuestions = {
        "1부터 10까지 숫자를 출력하는 코드를 작성하세요.",
        "1부터 100까지 홀수만 출력하는 코드를 작성하세요.",
        "1부터 n까지의 합을 구하는 코드를 작성하세요. (n은 입력값)"
    };

    private final String[] scannerQuestions = {
        "사용자로부터 문자열을 입력받아 출력하는 코드를 작성하세요.",
        "사용자로부터 숫자를 입력받아 그 숫자를 출력하는 코드를 작성하세요.",
        "사용자로부터 두 개의 정수를 입력받아 합을 출력하는 코드를 작성하세요."
    };

    private final String[] switchCaseQuestions = {
        "정수를 입력받아 요일을 출력하는 switch-case 문을 작성하세요.",
        "1에서 4까지의 숫자를 입력받아 해당하는 계절을 출력하는 switch-case 문을 작성하세요.",
        "문자를 입력받아 해당 문자가 모음인지 자음인지 판별하는 switch-case 문을 작성하세요."
    };

    // 랜덤으로 문제를 선택하는 메서드
    private String getRandomQuestion(String category) {
        Random random = new Random();
        switch (category) {
            case "If문":
                return ifQuestions[random.nextInt(ifQuestions.length)];
            case "For문":
                return forQuestions[random.nextInt(forQuestions.length)];
            case "Scanner":
                return scannerQuestions[random.nextInt(scannerQuestions.length)];
            case "SwitchCase":
                return switchCaseQuestions[random.nextInt(switchCaseQuestions.length)];
            default:
                return "해당 카테고리의 문제가 없습니다.";
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        String question = getRandomQuestion(category);

        request.setAttribute("category", category);
        request.setAttribute("question", question);
        request.getRequestDispatcher("solve.jsp").forward(request, response);
    }
}
