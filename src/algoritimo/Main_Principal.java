package algoritimo;

public class Main_Principal {

	public static void main(String[] args) throws Exception {
		long inicio; // faz a contagem em milissegundos
		long fim; // recebe a contagem final dos milissegundos
		String m = "";
		int p = 0;
		// Esse vetor contem o diretorio dos meus arquivos
		String[] vetDir = { "promissoria500alea", "promissoria500ord", "promissoria500inv", "promissoria1000alea",
				"promissoria1000ord", "promissoria1000inv", "promissoria5000alea", "promissoria5000ord",
				"promissoria5000inv", "promissoria10000alea", "promissoria10000ord", "promissoria10000inv",
				"promissoria30000alea", "promissoria30000ord", "promissoria30000inv" };

		// Métodos(HeapSort + Pesquisa binária)
		// Métodos(QuickSort + Pesquisa binária)
		//https://www.dropbox.com/home/TRABALHO DE P.O V01/arquivos Java P.O

		System.out.println("Métodos(QuickSort + Pesquisa binária)");
		m = "Q";
		while (p < 15) {
			fim = 0;
			for (int i = 0; i < 5; i++) {
				inicio = System.currentTimeMillis();
				processa_QuickSort("arquivos Java P.O/Promissorias/" + vetDir[p] + ".txt",
						"arquivos Java P.O/testes/arquivo " + m + vetDir[p] + ".txt",
						"arquivos Java P.O/data.txt",
						"arquivos Java P.O/testes/arquivos " + m + "datas" + vetDir[p] + ".txt");
				inicio = System.currentTimeMillis() - inicio;
				fim += inicio;
			}
			System.out.println("Tempo em milissegundos para " + vetDir[p] + " = " + fim / 5);
			p++;
		}

		System.out.println();
		
		System.out.println("Métodos(HeapSort + Pesquisa binária)");
		m = "H";
		p = 0;
		fim = 0;
		while (p < 15) {
			fim = 0;
			for (int i = 0; i < 5; i++) {
				inicio = System.currentTimeMillis();
				processa_HeapSort("arquivos Java P.O/Promissorias/" + vetDir[p] + ".txt",
						"arquivos Java P.O/testes/arquivo " + m + vetDir[p] + ".txt",
						"arquivos Java P.O/data.txt",
						"arquivos Java P.O/testes/arquivos " + m + "datas" + vetDir[p] + ".txt");
				inicio = System.currentTimeMillis() - inicio;
				fim += inicio;
			}
			System.out.println("Tempo em milissegundos para " + vetDir[p] + " = " + fim / 5);
			p++;
		}
		System.out.println();
//=============================================================================================================//

		// Métodos(Arvore ABB)
		// Métodos(Arvore AVL)

		fim = 0;
		p = 0;
		System.out.println("Método (Arvore ABB)");
		while (p < 15) {
			fim = 0;
			for (int i = 0; i < 5; i++) {
				inicio = System.currentTimeMillis();
				processa_ABB("arquivos Java P.O/Promissorias/" + vetDir[p] + ".txt", 
						"arquivos Java P.O/data.txt",
						"arquivos Java P.O/testes/arquivosArv_ABB_datas" + vetDir[p] + ".txt");
				inicio = System.currentTimeMillis() - inicio;
				fim += inicio;
			}
			System.out.println("Tempo em milissegundos para " + vetDir[p] + " = " + fim / 5);
			p++;
		}

		System.out.println();
		
		fim = 0;
		p = 0;
		System.out.println("Método (Arvore AVL)");
		while (p < 15) {
			fim = 0;
			for (int i = 0; i < 5; i++) {
				inicio = System.currentTimeMillis();
				processa_AVL("arquivos Java P.O/Promissorias/" + vetDir[p] + ".txt", 
						"arquivos Java P.O/data.txt",
						"arquivos Java P.O/testes/arquivosArv_AVL_datas" + vetDir[p] + ".txt");
				inicio = System.currentTimeMillis() - inicio;
				fim += inicio;
			}
			System.out.println("Tempo em milissegundos para " + vetDir[p] + " = " + fim / 5);
			p++;
		}

		System.out.println();
		fim = 0;
		p = 0;
		System.out.println("Método (Hashing)");
		while (p < 15) {
			fim = 0;
			for (int i = 0; i < 5; i++) {
				inicio = System.currentTimeMillis();
				processoHash("arquivos Java P.O/Promissorias/" + vetDir[p] + ".txt", 
						"arquivos Java P.O/data.txt",
						"arquivos Java P.O/testes/arquivosHashing" + vetDir[p] + ".txt");
				inicio = System.currentTimeMillis() - inicio;
				fim += inicio;
			}
			System.out.println("Tempo em milissegundos para " + vetDir[p] + " = " + fim / 5);
			p++;
		}

	}

	// Esse metodo irá executar o QuickSort
	public static void processa_QuickSort(String a, String b, String c, String d) {
		Alg_Ordenacao ler = new Alg_Ordenacao();
		ler.setVetor(Leitura_Arquivo.criarVetor(a));
		ler.quicksort();
		Leitura_Arquivo.gravar_Arq(b, ler.getVetor());
		// abrir o arquivo para gravar as promissorias encontradas
		Leitura_Arquivo.abrir_arq(d);
		int[] vetData = Leitura_Arquivo.criarVetorData(c);
		int q1 = 0;
		int dir = 0, esq = 0;
		String p = ";";
		String s = ",";
		Double valor = 0.0;
		String pago = "";
		String notPago = "";
		String notInd = "";
		// pesquisa as datas no vetor de promissorias
		for (int i = 0; i < vetData.length; i++) {
			q1 = ler.pesqBinaria(vetData[i]);
			if (q1 != -1) {
				esq = q1;
				dir = q1;
				// Quantas datas iguais ha para frente da data encontrada
				if (q1 < ler.getVetor().length - 1) {
					int m = q1;
					while (m <= ler.getVetor().length - 1 && ler.getVetor()[m].getData() == vetData[i]) {
						esq = m;
						m++;
					}
				}
				// Quantas datas iguais ha para traz da data encontrada
				if (q1 > 0) {
					int m = q1;
					while (m >= 0 && ler.getVetor()[m].getData() == vetData[i]) {
						dir = m;
						m--;
					}
				}
				// pegando as datas pagas e não pagas
				for (int j = dir; j <= esq; j++) {
					if (ler.getVetor()[j].isPagamento()) {
						pago += ler.getVetor()[j].getData() + p + ler.getVetor()[j].getValor() + p
								+ ler.getVetor()[j].getNome() + p + ler.getVetor()[j].isPagamento() + s;
					} else {
						notPago += ler.getVetor()[j].getData() + p + ler.getVetor()[j].getValor() + p
								+ ler.getVetor()[j].getNome() + p + ler.getVetor()[j].isPagamento() + s;
						valor += Double.valueOf(ler.getVetor()[j].getValor());
					}
				}
				// Metodo para gravar as promissorias pagas e não pagas
				Leitura_Arquivo.gravar_Arq_Data(vetData[i], pago, notPago, valor);
				valor = 0.0;
				pago = "";
				notPago = "";
			} else {
				notInd += vetData[i] + p;
			}
		}
		// grava as datas não encontradas.
		Leitura_Arquivo.not_Datas(notInd);
		// fechar o arquivo.
		Leitura_Arquivo.fechar_arq();
	}

	public static void processa_HeapSort(String a, String b, String c, String d) {
		Alg_Ordenacao ler = new Alg_Ordenacao();
		ler.setVetor(Leitura_Arquivo.criarVetor(a));
		ler.HeapSort();
		Leitura_Arquivo.gravar_Arq(b, ler.getVetor());
		// abrir o arquivo para gravar as promissorias encontradas
		Leitura_Arquivo.abrir_arq(d);
		int[] vetData = Leitura_Arquivo.criarVetorData(c);
		int q1 = 0;
		int dir = 0, esq = 0;
		String p = ";";
		String s = ",";
		Double valor = 0.0;
		String pago = "";
		String notPago = "";
		String notInd = "";
		// pesquisa as datas no vetor de promissorias
		for (int i = 0; i < vetData.length; i++) {
			q1 = ler.pesqBinaria(vetData[i]);
			if (q1 != -1) {
				esq = q1;
				dir = q1;
				// Quantas datas iguais ha para frente da data encontrada
				if (q1 < ler.getVetor().length - 1) {
					int m = q1;
					while (m <= ler.getVetor().length - 1 && ler.getVetor()[m].getData() == vetData[i]) {
						esq = m;
						m++;
					}
				}
				// Quantas datas iguais ha para traz da data encontrada
				if (q1 > 0) {
					int m = q1;
					while (m >= 0 && ler.getVetor()[m].getData() == vetData[i]) {
						dir = m;
						m--;
					}
				}
				// pegando as datas pagas e não pagas
				for (int j = dir; j <= esq; j++) {
					if (ler.getVetor()[j].isPagamento()) {
						pago += ler.getVetor()[j].getData() + p + ler.getVetor()[j].getValor() + p
								+ ler.getVetor()[j].getNome() + p + ler.getVetor()[j].isPagamento() + s;
					} else {
						notPago += ler.getVetor()[j].getData() + p + ler.getVetor()[j].getValor() + p
								+ ler.getVetor()[j].getNome() + p + ler.getVetor()[j].isPagamento() + s;
						valor += Double.valueOf(ler.getVetor()[j].getValor());
					}
				}
				// Metodo para gravar as promissorias pagas e não pagas
				Leitura_Arquivo.gravar_Arq_Data(vetData[i], pago, notPago, valor);
				valor = 0.0;
				pago = "";
				notPago = "";
			} else {
				notInd += vetData[i] + p;
			}
		}
		// grava as datas não encontradas.
		Leitura_Arquivo.not_Datas(notInd);
		// fechar o arquivo.
		Leitura_Arquivo.fechar_arq();
	}

	// Esse metodo executa a Arvore ABB
	public static void processa_ABB(String a, String b, String c) {
		Item[] vet = Leitura_Arquivo.criarVetor(a);
		Arvore_ABB arv = new Arvore_ABB();
		for (int i = 0; i < vet.length; i++) {
			arv.inserir(new Item(vet[i].getData(), vet[i].getNome(), vet[i].getCpf(), vet[i].getValor(),
					vet[i].isPagamento()));
		}
		vet = arv.CamCentral();
		arv.ArvoreBalanceada(vet);
		// Abrir o arquivo onde será gravado
		Leitura_Arquivo.abrir_arq(c);
		String p = ";";
		String s = ",";
		Double valor = 0.0;
		String pago = "";
		String notPago = "";
		String notInd = "";
		int[] vetData = Leitura_Arquivo.criarVetorData(b);
		Nodo_ABB no;
		for (int i = 0; i < vetData.length; i++) {
			// Fazer a pesquisa na arvore para ver se encontro esse no
			no = arv.pesquisarNo(vetData[i]);
			if (no != null) {
				if (no.getInfo().isPagamento()) {
					pago += no.getInfo().getData() + p + no.getInfo().getValor() + p + no.getInfo().getNome() + p
							+ no.getInfo().isPagamento() + s;
				} else {
					notPago += no.getInfo().getData() + p + no.getInfo().getValor() + p + no.getInfo().getNome() + p
							+ no.getInfo().isPagamento() + s;
					valor += Double.valueOf(no.getInfo().getValor());
				}
				// O ponteiro prox aponta para uma lista encadeada
				if (no.getProx() != null) {
					ListaSimples list = no.getProx();
					No corrente = list.getPrim();
					Item elem;
					// percorrer a lista e pegar as informações
					while (corrente != null) {
						elem = corrente.getInfo();
						if (elem.isPagamento()) {
							pago += elem.getData() + p + elem.getValor() + p + elem.getNome() + p + elem.isPagamento()
									+ s;
						} else {
							notPago += elem.getData() + p + elem.getValor() + p + elem.getNome() + p
									+ elem.isPagamento() + s;
							valor += Double.valueOf(elem.getValor());
						}
						corrente = corrente.getProx();
					}
				}
				// Metodo para gravar as promissorias pagas e não pagas
				Leitura_Arquivo.gravar_Arq_Data(vetData[i], pago, notPago, valor);
				valor = 0.0;
				pago = "";
				notPago = "";
			} else {
				notInd += vetData[i] + p;
			}

		}
		// grava as datas não encontradas.
		Leitura_Arquivo.not_Datas(notInd);
		// fechar o arquivo.
		Leitura_Arquivo.fechar_arq();
	}

	// Esse metodo executa a Arvore AVL
	public static void processa_AVL(String a, String b, String c) {
		Item[] vet = Leitura_Arquivo.criarVetor(a);
		Arvore_AVL arv = new Arvore_AVL();
		for (int i = 0; i < vet.length; i++) {
			arv.insereRaiz(new Item(vet[i].getData(), vet[i].getNome(), vet[i].getCpf(), vet[i].getValor(),
					vet[i].isPagamento()));
		}
		// Abrir o arquivo onde será gravado
		Leitura_Arquivo.abrir_arq(c);
		String p = ";";
		String s = ",";
		Double valor = 0.0;
		String pago = "";
		String notPago = "";
		String notInd = "";
		int[] vetData = Leitura_Arquivo.criarVetorData(b);
		Nodo_AVL no;
		for (int i = 0; i < vetData.length; i++) {
			// Fazer a pesquisa na arvore para ver se encontro esse no
			no = arv.pesquisarNo(vetData[i]);
			if (no != null) {
				if (no.getInfo().isPagamento()) {
					pago += no.getInfo().getData() + p + no.getInfo().getValor() + p + no.getInfo().getNome() + p
							+ no.getInfo().isPagamento() + s;
				} else {
					notPago += no.getInfo().getData() + p + no.getInfo().getValor() + p + no.getInfo().getNome() + p
							+ no.getInfo().isPagamento() + s;
					valor += Double.valueOf(no.getInfo().getValor());
				}
				// O ponteiro prox aponta para uma lista encadeada
				if (no.getProx() != null) {
					ListaSimples list = no.getProx();
					No corrente = list.getPrim();
					Item elem;
					// percorrer a lista e pegar as informações
					while (corrente != null) {
						elem = corrente.getInfo();
						if (elem.isPagamento()) {
							pago += elem.getData() + p + elem.getValor() + p + elem.getNome() + p + elem.isPagamento()
									+ s;
						} else {
							notPago += elem.getData() + p + elem.getValor() + p + elem.getNome() + p
									+ elem.isPagamento() + s;
							valor += Double.valueOf(elem.getValor());
						}
						corrente = corrente.getProx();
					}
				}
				// Metodo para gravar as promissorias pagas e não pagas
				Leitura_Arquivo.gravar_Arq_Data(vetData[i], pago, notPago, valor);
				valor = 0.0;
				pago = "";
				notPago = "";
			} else {
				notInd += vetData[i] + p;
			}

		}
		// grava as datas não encontradas.
		Leitura_Arquivo.not_Datas(notInd);
		// fechar o arquivo.
		Leitura_Arquivo.fechar_arq();
	}

	// Metodo para o Hashing
	public static void processoHash(String a, String b, String c) throws Exception {
		Item[] vet = Leitura_Arquivo.criarVetor(a);
		Hashing_Encadeado alg = new Hashing_Encadeado(vet.length);
		for (int i = 0; i < vet.length; i++) {
			alg.inserir(new Item(vet[i].getData(), vet[i].getNome(), vet[i].getCpf(), vet[i].getValor(),
					vet[i].isPagamento()));
		}
		Leitura_Arquivo.abrir_arq(c);
		String p = ";";
		String s = ",";
		Double valor = 0.0;
		String pago = "";
		String notPago = "";
		String notInd = "";
		int[] vetData = Leitura_Arquivo.criarVetorData(b);
		ListaSimples no;
		for (int i = 0; i < vetData.length; i++) {
			// me retorna o no do vetor onde contem uma lista simples
			no = alg.pesquisarChave(vetData[i]);
			if (no != null) {
				No atual = no.getPrim();
				while (atual != null) {
					// percorrendo a lista e pegando apenas as chaves iguais
					if (atual.getInfo().getData() == vetData[i]) {
						if (atual.getInfo().isPagamento()) {
							pago += atual.getInfo().getData() + p + atual.getInfo().getValor() + p
									+ atual.getInfo().getNome() + p + atual.getInfo().isPagamento() + s;
						} else {
							notPago += atual.getInfo().getData() + p + atual.getInfo().getValor() + p
									+ atual.getInfo().getNome() + p + atual.getInfo().isPagamento() + s;
							valor += Double.valueOf(atual.getInfo().getValor());
						}
					}
					atual = atual.getProx();
				}
				// Metodo para gravar as promissorias pagas e não pagas
				Leitura_Arquivo.gravar_Arq_Data(vetData[i], pago, notPago, valor);
				valor = 0.0;
				pago = "";
				notPago = "";
			} else {
				notInd += vetData[i] + p;
			}
		}
		// grava as datas não encontradas.
		Leitura_Arquivo.not_Datas(notInd);
		// fechar o arquivo.
		Leitura_Arquivo.fechar_arq();
	}
}