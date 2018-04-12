package model;

public class Produto {

	private int id;
	private String nome;
	private String descricao;
	private double valorInicial;
	private double maiorLance;

	public Produto() {

	}

	public Produto(int id, String nome, String descricao, double valorInicial, double maiorLance) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.valorInicial = valorInicial;
		this.maiorLance = maiorLance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(double valorInicial) {
		this.valorInicial = valorInicial;
	}

	public double getMaiorLance() {
		return maiorLance;
	}

	public void setMaiorLance(double maiorLance) {
		this.maiorLance = maiorLance;
	}

}
