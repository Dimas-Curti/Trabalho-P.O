package algoritimo;

public class Hashing_Encadeado {
	private ListaSimples[] vet;
	private int quant;

	public boolean inserir(Item elem) {
		int i = elem.getData() % vet.length;
		if (i < vet.length) {
			if (pesquisar(elem.getData()) == null) {
				vet[i] = new ListaSimples();
				vet[i].inserirUltimo(elem);
			} else {
				vet[i].inserirUltimo(elem);
			}
			this.quant++;
			return true;
		} else {
			return false;
		}

	}

	public ListaSimples pesquisarChave(int chave) {
		int i = chave % vet.length;
		return vet[i];
	}

	private ListaSimples pesquisar(int chave) {
		int i = chave % vet.length;
		return vet[i];
	}

	public ListaSimples[] getVet() {
		return vet;
	}

	public Hashing_Encadeado(int tam) throws Exception {
		tam = tamanhoVetor(tam);
		if (tam == 0) {
			throw new Exception("Vetor vazio");
		} else {
			this.vet = new ListaSimples[tam];
			this.quant = 0;
		}
	}

	public int getQuant() {
		return quant;
	}

	private int tamanhoVetor(int m) {
		int tam = 0;
		switch (m) {
		case 500:
			tam = 557;
			break;
		case 1000:
			tam = 1103;
			break;
		case 5000:
			tam = 5503;
			break;
		case 10000:
			tam = 11003;
			break;
		case 30000:
			tam = 33013;
			break;
		default:
			break;
		}
		return tam;
	}

}
