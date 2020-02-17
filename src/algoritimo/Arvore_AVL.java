package algoritimo;

public class Arvore_AVL {
	private Nodo_AVL raiz;
	private boolean h;

	public Arvore_AVL() {
		this.raiz = null;
		this.h = true;
	}

	public void insereRaiz(Item elem) {
		this.raiz = this.insere(elem, this.raiz);
	}

	private Nodo_AVL insere(Item elem, Nodo_AVL no) {
		if (no == null) {
			Nodo_AVL novo = new Nodo_AVL(elem);
			this.h = true;
			return novo;
		} else {
			// Fazer a pesquisa para ver se já existe essa chave na arvore
			if(pesquisar(elem.getData())) {
				// pego esse no
				Nodo_AVL n = pesquisar(elem.getData(), this.raiz);
				// se o prox e null então eu inicializo a lista e insiro o elem
				if (n.getProx() == null) {
					ListaSimples nodo = new ListaSimples();
					nodo.inserirUltimo(elem);
					n.setProx(nodo);
				// se ja existe uma lista entao e so inserir o elem	
				} else {
					ListaSimples atual = n.getProx();
					atual.inserirUltimo(elem);
				}
				// vou retornar a mesma raiz que veio na chamada do metodo porque não ouve alteracao, somente
				// foi inserido um no na lista encadeada
				return no;
			}
			if (elem.getData() < no.getInfo().getData()) {
				// Insere à esquerda e verifica se precisa balancear à direita
				no.setEsq(this.insere(elem, no.getEsq()));
				no = this.balancearDir(no);
				return no;
			} else {
				// Insere à direita e verifica se precisa balancear à esquerda
				no.setDir(this.insere(elem, no.getDir()));
				no = this.balancearEsq(no);
				return no;
			}
		}
	}

	private Nodo_AVL balancearDir(Nodo_AVL no) {

		if (this.h)
			switch (no.getFatorBalanceamento()) {
			case 1:
				no.setFatorBalanceamento((byte) 0);
				this.h = false;
				break;
			case 0:
				no.setFatorBalanceamento((byte) -1);
				break;
			case -1:
				no = this.rotaçãoDireita(no);
			}
		return no;
	}

	private Nodo_AVL balancearEsq(Nodo_AVL no) {
		if (this.h)
			switch (no.getFatorBalanceamento()) {
			case -1:
				no.setFatorBalanceamento((byte) 0);
				this.h = false;
				break;
			case 0:
				no.setFatorBalanceamento((byte) 1);
				break;
			case 1:
				no = this.rotaçãoEsquerda(no);
			}
		return no;
	}

	private Nodo_AVL rotaçãoDireita(Nodo_AVL no) {
		Nodo_AVL temp1, temp2;
		temp1 = no.getEsq();
		if (temp1.getFatorBalanceamento() == -1) {
			no.setEsq(temp1.getDir());
			temp1.setDir(no);
			no.setFatorBalanceamento((byte) 0);
			no = temp1;
		} else {
			temp2 = temp1.getDir();
			temp1.setDir(temp2.getEsq());
			temp2.setEsq(temp1);
			no.setEsq(temp2.getDir());
			temp2.setDir(no);
			if (temp2.getFatorBalanceamento() == -1)
				no.setFatorBalanceamento((byte) 1);
			else
				no.setFatorBalanceamento((byte) 0);
			if (temp2.getFatorBalanceamento() == 1)
				temp1.setFatorBalanceamento((byte) -1);
			else
				temp1.setFatorBalanceamento((byte) 0);
			no = temp2;
		}
		no.setFatorBalanceamento((byte) 0);
		this.h = false;
		return no;

	}

	private Nodo_AVL rotaçãoEsquerda(Nodo_AVL no) {
		Nodo_AVL temp1, temp2;
		temp1 = no.getDir();
		if (temp1.getFatorBalanceamento() == 1) {
			no.setDir(temp1.getEsq());
			temp1.setEsq(no);
			no.setFatorBalanceamento((byte) 0);
			no = temp1;
		} else {
			temp2 = temp1.getEsq();
			temp1.setEsq(temp2.getDir());
			temp2.setDir(temp1);
			no.setDir(temp2.getEsq());
			temp2.setEsq(no);
			if (temp2.getFatorBalanceamento() == 1)
				no.setFatorBalanceamento((byte) -1);
			else
				no.setFatorBalanceamento((byte) 0);
			if (temp2.getFatorBalanceamento() == -1)
				temp1.setFatorBalanceamento((byte) 1);
			else
				temp1.setFatorBalanceamento((byte) 0);
			no = temp2;
		}
		no.setFatorBalanceamento((byte) 0);
		this.h = false;
		return no;
	}
	
	public boolean pesquisar(int chave) {
		if (pesquisar(chave, this.raiz) != null) {
			return true;
		} else {
			return false;
		}
	}

	public Nodo_AVL pesquisarNo(int chave) {
		return pesquisar(chave, this.raiz);
	}
	private Nodo_AVL pesquisar(int chave, Nodo_AVL no) {
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

}
