package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProdutoDAO;
import model.Lance;
import model.LanceDAO;
import model.Produto;

/**
 * Servlet implementation class ListProdutoController
 */
@WebServlet("/LanceController")
public class LanceController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    int idProduto;
    double valorLance = 0;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LanceController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("ISO-8859-1");//CODIFICAO charset=ISO-8859-1 PARA ACENTUACAO E CARACTRES ESPECIAIS
		
		idProduto = Integer.parseInt(request.getParameter("id_produto"));
		
		if(request.getParameter("valor_lance") != null && !request.getParameter("valor_lance").equals("")) {
			valorLance = Integer.parseInt(request.getParameter("valor_lance"));
		} else {
			valorLance = 0;
		}
		
        
        ProdutoDAO dao = new ProdutoDAO();  
        LanceDAO lDao = new LanceDAO();
        Produto prod = dao.getProdutoEspecifico(idProduto);
        ArrayList<Lance> listLances = lDao.getLances(idProduto);
        
        if(valorLance > 0) {
        	lDao.inserir(idProduto, valorLance);
        	if(valorLance > prod.getMaiorLance())
        		dao.atualizaMaiorLance(idProduto, valorLance);
        	//TODO: mudar maior lance para controller. --COMPLETO
		    response.sendRedirect("http://localhost:8080/LeilaoProjArq/LanceController?id_produto=" + idProduto);
			
		}
        
        response.setContentType("text/html;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        
       
        out.println("<html>");
        out.println("<head><title>Leilao do Produto</title></head>\n");
        out.println("<body>");
        
        out.println("<h2>Leilao do Produto</h2>");
        out.println("<br>");
        
      
        printTabelaInHtml(out, prod, listLances);
        
        pritHtmlGerarLance(out, prod);
        
        out.println("</body></html>");
	    out.close();
        
        
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void printTabelaInHtml(ServletOutputStream out, Produto prod, ArrayList<Lance> listLances) throws IOException {
		
	    out.println("<table>\r\n" + 
	    		"			<tr>\r\n" + 
	    		"				<td>Id:" + prod.getId() + "</td>\r\n" + 
	    		"				<td> | Nome: " + prod.getNome() + "</td>\r\n" + 
	    		"				<td> | Valor Inicial: " + prod.getValorInicial() + "</td>\r\n" + 
	    		"				<td> | Maior Lance: " + prod.getMaiorLance() + "</td>\r\n" +
	    		"			</tr>" 		
	    		+ "</table>" 
	    		+ "<table>\r\n" + 
	    		"			<tr>\r\n" +
	    		" 				<p>Descricao: " +	prod.getDescricao() + "</p>\r\n" +
	    		" 			</tr>\r\n"
	    		+ "</table>");
	    
	    out.println("<br>");
	  
	    out.println("<table border=1>");
	    out.println("<tr>\r\n" + 
	    		"	      <th>Id</th>\r\n" + 
	    		"	      <th>Valor</th>\r\n" +  
	    		"</tr>");
	    
	    for(Lance x : listLances) {	  
	    	out.println("<tr>");
	    	out.print("<td>"+ x.getId() +"</td>");
	    	out.print("<td>"+ x.getValor() +"</td>");
	    	out.println("</tr>");
	    }
	    
	    out.println("</table>");
	    
	    out.println("<br>");
//	    out.println("<input type=\"submit\" value=\"Fazer Lances\">");


	}
	
	private void pritHtmlGerarLance(ServletOutputStream out, Produto prod) throws IOException {
		out.println("<form action=\"LanceController\" method=\"GET\">");
		out.println("<table>\r\n" + 
				"       <tr><th>Fazer Lance</th></tr>\n" +
				"		<tr>\r\n" + 
				"			<td>Valor do lance:</td>\r\n" + 
				"			<td><input type=\"text\" name=\"valor_lance\"></td>\r\n" + 
				"		</tr>\r\n");
		out.println("	<tr>\r\n" + 
				"			<td></td>\r\n" + 
				"			<td><input type=\"submit\" data-value=\"" + prod.getId() +"\" value=\"Registrar Lance\"></td>\r\n" + 
				"			<td><input type=\"hidden\" name=\"id_produto\" value=\"" + prod.getId() + "\"/>" + 
				"	</tr>");
		out.println("</table>");
		out.println("</form>");
		
		
		
	}

}
