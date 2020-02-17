package algoritimo;
public class Nodo_ABB {
	private Item info; // o tipo Item está declarado no capítulo 1
	private Nodo_ABB esq, dir;
	private ListaSimples prox;

	public ListaSimples getProx() {
		return prox;
	}

	public void setProx(ListaSimples prox) {
		this.prox = prox;
	}

	public Nodo_ABB(Item elem) {
		this.info = elem;
		this.esq = null;
		this.dir = null;
		this.prox=null;
	}

	public Nodo_ABB getEsq() {
		return this.esq;
	}

	public Nodo_ABB getDir() {
		return this.dir;
	}

	public Item getInfo() {
		return this.info;
	}

	public void setEsq(Nodo_ABB no) {
		this.esq = no;
	}

	public void setDir(Nodo_ABB no) {
		this.dir = no;
	}

	public void setInfo(Item elem) {
		this.info = elem;
	}
}

