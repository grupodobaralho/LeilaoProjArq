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
    double valorLance;
    
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
		
		//Verifica se ocorre o pedido de insersao no banco
		if(request.getParameter("valor_lance") != null && !request.getParameter("valor_lance").equals("")) 
			valorLance = Double.parseDouble(request.getParameter("valor_lance"));
		else 
			valorLance = 0;
		
        ProdutoDAO pDaoPk = new ProdutoDAO(idProduto);  
        Object produtoObjeto = pDaoPk.selectEspecificData();
        Produto prod = (Produto) produtoObjeto;
        
        LanceDAO lanceDaoSelect = new LanceDAO(prod.getId());
        ArrayList<Object> listLances = lanceDaoSelect.selectAll();
        
        //Mensagem caso o lance seja inalido
        String msgValorInvalido = null;
        
        if(valorLance > 0) {
        	if(valorLance >= prod.getValorInicial() && valorLance > prod.getMaiorLance()) {
	        	Lance lance = new Lance();
	        	lance.setId(lanceDaoSelect.sizeLance() + 1);
	        	lance.setIdProduto(idProduto);
	        	lance.setValor(valorLance);
	        	
	        	//Insere lance no banco
	        	LanceDAO lanceDaoInsert = new LanceDAO(lance);
	        	lanceDaoInsert.insert();
	        	
	        	//Atualiza maior lance
	        	pDaoPk.atualizaMaiorLance(valorLance);
	        	
	        	//Atualiza pagina sem inserir nenhum elemento no banco
			    response.sendRedirect("http://localhost:8080/LeilaoProjArq/LanceController?id_produto=" + idProduto);
        	} else if (valorLance < prod.getValorInicial())
        		msgValorInvalido = 	"Valor " + valorLance + " eh inferior ao valor incial!";
        	else
        		msgValorInvalido = 	"Valor " + valorLance + " eh igual ou inferior ao maior lance!";
        	
			
		}
        
        response.setContentType("text/html;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        
       
        out.println("<html>");
        out.println("<head><title>Leilao do Produto</title></head>\n");
        out.println("<body>");
        
        out.println("<input type=\"submit\" value=\"HOME\" \r\n" + 
        		"    onclick=\"window.location='http://localhost:8080/LeilaoProjArq/';\" /> ");
        
        out.println("<br>");
        
        out.println("<h2>Leilao do Produto</h2>");
        out.println("<br>");
        
      
        printTabelaInHtml(out, prod, listLances);
        
        printHtmlGerarLance(out, prod);
        
        if(msgValorInvalido != null)
        	printHtmlScriptLanceInvalido(out, msgValorInvalido); 
        
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
	
	private void printTabelaInHtml(ServletOutputStream out, Produto prod, ArrayList<Object> listLances) throws IOException {
		
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
	    
	    for(Object x : listLances) {	  
	    	out.println("<tr>");
	    	out.print("<td>"+ ((Lance) x).getId() +"</td>");
	    	out.print("<td>"+ ((Lance) x).getValor() +"</td>");
	    	out.println("</tr>");
	    }
	    
	    out.println("</table>");
	    
	    out.println("<br>");
	}
	
	private void printHtmlGerarLance(ServletOutputStream out, Produto prod) throws IOException {
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
	
	private void printHtmlScriptLanceInvalido(ServletOutputStream out, String msg) throws IOException {
		
		out.println("<script type=\"text/javascript\">");
		out.println("	alert(\"" + msg + "\");");
		out.println("</script>");		
				
	}
}
