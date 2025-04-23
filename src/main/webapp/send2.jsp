<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="EUC-KR"%>
<%
try
{
	// 문자열의 인코딩 방식 결정
	request.setCharacterEncoding("UTF-8");
	// 파라미터를 통해 전해진 작성자, 제목, 내용 정보를 받아와 각 문자열 변수에 저장
	String userCode = request.getParameter("userCode");
	String[] codeLines = null;
	
	if (userCode != null && !userCode.isEmpty()) {
		codeLines = userCode.split("\n");
	}

    // 줄바꿈 문제 해결 및 배열에 저장
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
	
	request.setAttribute("explanation", explanation);
    request.setAttribute("correctCode", codeLines);
    
    request.getRequestDispatcher("resultPage2.jsp").forward(request, response);
}
catch (Exception ex)
{
out.println("오류가 발생했습니다. 오류 메시지 : " + ex.getMessage());
}
%>
