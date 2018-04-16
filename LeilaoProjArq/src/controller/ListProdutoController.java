package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProdutoDAO;
import model.Produto;

/**
 * Servlet implementation class ListProdutoController
 */
@WebServlet("/ListProdutoController")
public class ListProdutoController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    int id;
    String nome, descricao;
    double valorInicial;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListProdutoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("ISO-8859-1");//CODIFICAO charset=ISO-8859-1 PARA ACENTUACAO E CARACTRES ESPECIAIS
		
		ProdutoDAO pDaoGeneric = new ProdutoDAO(null);   
        ArrayList<Object> listProdutos = pDaoGeneric.selectAll();
        
        response.setContentType("text/html;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
	    
        out.println("<html>");
        out.println("<head><title>Listas de Produtos</title></head>\n");
        out.println("<body>");
        
        out.println("<input type=\"submit\" value=\"HOME\" \r\n" + 
        		"    onclick=\"window.location='http://localhost:8080/LeilaoProjArq/';\" /> ");
        
        out.println("<br>");
        
        out.println("<h2>Lista de Produtos em Leilao</h2>\r\n" + 
        		"<p>Selecione o produto desejado e precione o botao \"Fazer Lances\"</p>");
        out.println("<br>");

	    out.println("<form action=\"LanceController\" method=\"GET\">");
	    out.println("<select name=\"id_produto\" id=\"produtos\" size=7>");
	    
	    if(!listProdutos.isEmpty()) {
	    	for(Object x : listProdutos) {	    	
	    		out.println("<option value=" + ((Produto) x).getId() + ">" + ((Produto) x).getNome() + "</option>" );
	    	}
	    }
	   
	    out.println("</select>");
	    out.println("<br><br>");
	    out.println("<input type=\"submit\" value=\"Fazer Lances\">");
	    
	    out.println("</form>");
	    
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

}
