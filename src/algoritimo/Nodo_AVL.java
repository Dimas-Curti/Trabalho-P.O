package algoritimo;
public class Nodo_AVL {

	private Item info;
	private Nodo_AVL esq, dir;
	private byte fatorBalanceamento;
	private ListaSimples prox;

	public ListaSimples getProx() {
		return prox;
	}

	public void setProx(ListaSimples prox) {
		this.prox = prox;
	}

	public Nodo_AVL(Item i) {// construtor
		this.prox=null;
		this.info = i;
		this.fatorBalanceamento = 0;
	}

	public Nodo_AVL getDir() {
		return this.dir;
	}

	public void setDir(Nodo_AVL dir) {
		this.dir = dir;
	}

	public Nodo_AVL getEsq() {
		return this.esq;
	}

	public void setEsq(Nodo_AVL esq) {
		this.esq = esq;
	}

	public byte getFatorBalanceamento() {
		return this.fatorBalanceamento;
	}

	public void setFatorBalanceamento(byte fatorBalanceamento) {
		this.fatorBalanceamento = fatorBalanceamento;
	}

	public Item getInfo() {
		return this.info;
	}
}


