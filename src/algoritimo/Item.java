package algoritimo;

public class Item {
	private String cpf;
	private Integer data;
	private String valor;
	private String nome;
	private Boolean pagamento;

	public Item(Integer date, String nome, String cpf, String valor, Boolean pagamento) {
		this.cpf = cpf;
		this.valor = valor;
		this.nome = nome;
		this.pagamento = pagamento;
		this.data = date;
	}

	public Integer getData() {
		return data;
	}

	public void setData(Integer data) {
		this.data = data;
	}

	public String getValor() {
		return valor;
	}

	public String getNome() {
		return nome;
	}

	public boolean isPagamento() {
		return pagamento;
	}

	public String getCpf() {
		return cpf;
	}
	
	public String toString() {
		return this.getData() + ";" + this.getNome() + ";" + this.getCpf() + ";" + this.getValor() + ";"
				+ this.isPagamento();
	}
}

