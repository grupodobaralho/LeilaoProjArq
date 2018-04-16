package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProdutoDAO;
import model.Produto;

/**
 * Servlet implementation class ProdutoController
 */
@WebServlet("/ProdutoController")
public class ProdutoController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    int id;
    String nome, descricao;
    double valorInicial;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdutoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("ISO-8859-1");//CODIFICAO charset=ISO-8859-1 PARA ACENTUACAO E CARACTRES ESPECIAIS

		id = Integer.parseInt(request.getParameter("id"));
        nome = request.getParameter("nome");
        descricao = request.getParameter("descricao");
        valorInicial = Double.parseDouble(request.getParameter("valorInicial"));
        
        Produto p = new Produto();        
        p.setId(id);
        p.setNome(nome);
        p.setDescricao(descricao);
        p.setValorInicial(valorInicial);
        p.setMaiorLance(0);
        
        ProdutoDAO pDao = new ProdutoDAO(p);   
        pDao.insert();
        
        response.sendRedirect("http://localhost:8080/LeilaoProjArq/ListProdutoController?");
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
