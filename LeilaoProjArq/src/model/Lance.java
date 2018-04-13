package model;

//TODO: trocar int por double no valor
public class Lance {

	private int id;
	private Produto produto;
	private int valor;

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

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

}
