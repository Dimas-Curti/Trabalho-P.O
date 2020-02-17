package algoritimo;

public class No {
	private Item info; 
	private No prox;

	public No(Item elem) { 
		this.info = elem;
		this.prox = null;
	}

	public Item getInfo() {
		return this.info;
	}

	public void setInfo(Item elem) {
		this.info = elem;
	}

	public No getProx() {
		return this.prox;
	}

	public void setProx(No novoNo) {
		this.prox = novoNo;
	}

}
