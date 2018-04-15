package model;

//TODO: trocar int por double no valor -- COMPLETO
public class Lance {

	private int id;
	private Produto produto;
	private double valor;

	public Lance() {

	}

	public Lance(int id, Produto produto, int valor) {
		super();
		this.id = id;
		this.produto = produto;
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
