package algoritimo;

public class Arvore_ABB {
	private Nodo_ABB raiz;
	private int quantNos;

	public Arvore_ABB() {
		this.quantNos = 0;
		this.raiz = null;
	}

	public boolean eVazia() {
		return (this.raiz == null);
	}

	public Nodo_ABB getRaiz() {
		return this.raiz;
	}

	public int getQuantNos() {
		return this.quantNos;
	}

	public boolean inserir(Item elem) {
		if (pesquisar(elem.getData())) {
			Nodo_ABB no = pesquisar(elem.getData(), this.raiz);
			if (no.getProx() == null) {
				ListaSimples nodo = new ListaSimples();
				nodo.inserirUltimo(elem);
				no.setProx(nodo);
				this.quantNos++;
			} else {
				ListaSimples atual = no.getProx();
				atual.inserirUltimo(elem);
				this.quantNos++;
			}
			return true;
		} else {
			this.raiz = inserir(elem, this.raiz);
			this.quantNos++;
			return true;
		}
	}

	private Nodo_ABB inserir(Item elem, Nodo_ABB no) {
		if (no == null) {
			Nodo_ABB novo = new Nodo_ABB(elem);
			return novo;
		} else {
			if (elem.getData() < no.getInfo().getData()) {
				no.setEsq(inserir(elem, no.getEsq()));
				return no;
			} else {
				no.setDir(inserir(elem, no.getDir()));
				return no;
			}
		}
	}

	public boolean pesquisar(int chave) {
		if (pesquisar(chave, this.raiz) != null) {
			return true;
		} else {
			return false;
		}
	}

	public Nodo_ABB pesquisarNo(int chave) {
		return pesquisar(chave, this.raiz);
	}
	private Nodo_ABB pesquisar(int chave, Nodo_ABB no) {
		if (no != null) {
			if (chave < no.getInfo().getData()) {
				no = pesquisar(chave, no.getEsq());
			} else {
				if (chave > no.getInfo().getData()) {
					no = pesquisar(chave, no.getDir());
				}
			}
		}
		return no;
	}

	public boolean remover(int chave) {
		if (pesquisar(chave, this.raiz) != null) {
			this.raiz = remover(chave, this.raiz);
			this.quantNos--;
			return true;
		} else {
			return false;
		}
	}

	private Nodo_ABB remover(int chave, Nodo_ABB arv) {
		if (chave < arv.getInfo().getData()) {
			arv.setEsq(remover(chave, arv.getEsq()));
		} else {
			if (chave > arv.getInfo().getData()) {
				arv.setDir(remover(chave, arv.getDir()));
			} else {
				if (arv.getDir() == null) {
					return arv.getEsq();
				} else {
					if (arv.getEsq() == null) {
						return arv.getDir();
					} else {
						arv.setEsq(Arrumar(arv, arv.getEsq()));
					}
				}
			}
		}
		return arv;
	}

	private Nodo_ABB Arrumar(Nodo_ABB arv, Nodo_ABB maior) {
		if (maior.getDir() != null) {
			maior.setDir(Arrumar(arv, maior.getDir()));
		} else {
			arv.setInfo(maior.getInfo());
			maior = maior.getEsq();
		}
		return maior;
	}

	public Item[] CamCentral() {
		int[] n = new int[1];
		n[0] = 0;
		Item[] vet = new Item[this.quantNos];
		return (FazCamCentral(this.raiz, vet, n));
	}

	private Item[] FazCamCentral(Nodo_ABB arv, Item[] vet, int[] n) {
		if (arv != null) {
			vet = FazCamCentral(arv.getEsq(), vet, n);
			vet[n[0]] = arv.getInfo();
			n[0]++;
			if (arv.getProx() != null) {
				ListaSimples list = arv.getProx();
				No corrente = list.getPrim();
				while (corrente != null) {
					vet[n[0]] = corrente.getInfo();
					corrente = corrente.getProx();
					n[0]++;
				}
			}
			vet = FazCamCentral(arv.getDir(), vet, n);
		}
		return vet;
	}

	public Arvore_ABB ArvoreBalanceada(Item[] vetOrdenado) {
		Arvore_ABB temp = new Arvore_ABB();
		this.Balancear(vetOrdenado, temp, 0, vetOrdenado.length - 1);
		return temp;
	}

	private void Balancear(Item[] vet, Arvore_ABB temp, int inic, int fim) {
		int meio;
		if (fim >= inic) {
			meio = (inic + fim) / 2;
			temp.inserir(new Item(vet[meio].getData(), vet[meio].getNome(), vet[meio].getCpf(), vet[meio].getValor(),
					vet[meio].isPagamento()));
			this.Balancear(vet, temp, inic, meio - 1);
			this.Balancear(vet, temp, meio + 1, fim);
		}
	}

}
