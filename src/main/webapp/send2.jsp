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
	// ���ڿ��� ���ڵ� ��� ����
	request.setCharacterEncoding("UTF-8");
	// �Ķ���͸� ���� ������ �ۼ���, ����, ���� ������ �޾ƿ� �� ���ڿ� ������ ����
	String userCode = request.getParameter("userCode");
	String[] codeLines = null;
	
	if (userCode != null && !userCode.isEmpty()) {
		codeLines = userCode.split("\n");
	}

    // �ٹٲ� ���� �ذ� �� �迭�� ����
    String explanation = null;
    
    boolean foundAnswer = false;

    // ���ڿ� �迭�� �� �پ� Ȯ��
    for (String line : codeLines) {
        if (line.contains("if (number % 2 == 0)")) {
        	explanation = "�����Դϴ�.";
            foundAnswer = true;
            break; // ������ �����ϸ� �ݺ� ����
        }
    }

    if (!foundAnswer) {
    	explanation = "������ �ƴմϴ�.";
    }
	
	request.setAttribute("explanation", explanation);
    request.setAttribute("correctCode", codeLines);
    
    request.getRequestDispatcher("resultPage2.jsp").forward(request, response);
}
catch (Exception ex)
{
out.println("������ �߻��߽��ϴ�. ���� �޽��� : " + ex.getMessage());
}
%>
