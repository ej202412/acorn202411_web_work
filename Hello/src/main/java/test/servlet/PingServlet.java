package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//3.어떤 요청이 왔을 때 이 클래스로 생성된 객체로 응답을 할지 정해야 한다.
@WebServlet("/ping")
public class PingServlet extends HttpServlet{//1. HttpServlet 클래스를 상속 받는다.
	
	//2. service() 메소드를 오버라이딩 한다.
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트에게 문자열을 출력할 수 있는 객체를 얻어낸다.
		PrintWriter pw=response.getWriter();
		//이 객체를 이용해서 출력하는 문자열은 요청을 한 클라이언트에게 출력된다.
		pw.println("pong!");
		pw.close();
	}
}
