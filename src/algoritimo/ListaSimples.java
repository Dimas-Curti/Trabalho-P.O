package algoritimo;
public class ListaSimples {
	private No prim;
	private No ult;
	private int quantNos;

	// construtor da classe
	public ListaSimples() {
		this.prim = null;
		this.ult = null;
		this.quantNos = 0;
	}

	public int getQuantNos() {
		return this.quantNos;
	}

	public No getPrim() {
		return this.prim;
	}

	public No getUlt() {
		return this.ult;
	}

	public void setQuantNos(int novoValor) {
		this.quantNos = novoValor;
	}

	public void setPrim(No novoNo) {
		this.prim = novoNo;
	}

	public void setUlt(No novoNo) {
		this.ult = novoNo;
	}

	public boolean eVazia() {
		return (this.prim == null);
	}

	public void inserirPrimeiro(Item elem) {
		No novoNo = new No(elem);
		if (this.eVazia()) {
			this.ult = novoNo;
		}
		novoNo.setProx(this.getPrim());
		this.prim = novoNo;
		this.quantNos++;
	}

	public void inserirUltimo(Item elem) {
		No novoNo = new No(elem);
		if (this.eVazia()) {
			this.prim = novoNo;
		} else {
			this.ult.setProx(novoNo);
		}
		this.ult = novoNo;
		this.quantNos++;
	}

	public No pesquisarNo(int chave) {
		No atual = this.prim;
		while ((atual != null) && (atual.getInfo().getData() != chave)) {
			atual = atual.getProx();
		}
		return atual;
	}

	public boolean removerNo(int chave) {
		No atual = this.prim;
		No ant = null;
		if (eVazia()) {
			return false;
		} else {
			while ((atual != null) && (atual.getInfo().getData() != chave)) {
				ant = atual;
				atual = atual.getProx();
			}
			if (atual == null) {
				return false;
			} else {
				if (atual == this.prim) {
					if (this.prim == this.ult) {
						this.ult = null;
					}
					this.prim = this.prim.getProx();
				} else {
					if (atual == this.ult) {
						this.ult = ant;
					}
					ant.setProx(atual.getProx());
				}
				this.quantNos--;
				return true;
			}
		}
	}
	public void Print() {
		if (this.eVazia()) {
			return;
		} else {
			No corrente = this.prim;
			while (corrente != null) {
				System.out.print(corrente.getInfo().toString() + " ");
				corrente = corrente.getProx();
			}
		}

	}
}
