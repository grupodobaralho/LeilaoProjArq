package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginCheck() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Step 1: set content type
		response.setContentType("text/html");
		
		// Step 2: get the printwriter
		PrintWriter escreve = response.getWriter();
		
		// Step 3: generate the HTML content
		escreve.println("<html><body>");
		
		escreve.println("O usuario digitado é: " + request.getParameter("uname"));
		escreve.println("A senha digitada  é: " + request.getParameter("password"));
		
		escreve.println("</body></html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		String uname = request.getParameter("uname");
//		String password = request.getParameter("password");
//
//		if (uname.equals("java") && password.equals("1234")) {
//			response.sendRedirect("member.jsp");
//		}
//		else {
//			response.sendRedirect("error.jsp");
//		}
	}

}
