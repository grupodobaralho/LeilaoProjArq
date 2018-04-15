package model;

public class Lance {

	private int id;
	private int idProduto;
	private double valor;

	public Lance() {

	}

	public Lance(int id, int idProduto, int valor) {
		super();
		this.id = id;
		this.idProduto = idProduto;
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
