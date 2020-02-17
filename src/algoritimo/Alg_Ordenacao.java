package algoritimo;

public class Alg_Ordenacao {
	private Item[] vetor;
	
	public Alg_Ordenacao() {
		
	}
	  public void print() { for (int i = 0; i < vetor.length; i++) {
	  System.out.println("["+vetor[i]+"]"); } }

	public Item[] getVetor() {
		return vetor;
	}

	public void setVetor(Item[] vetor) {
		this.vetor = vetor;
	}

	// ===========================================================================================================//

	public void HeapSort() {
		int dir = this.vetor.length - 1;
		int esq = (dir - 1) / 2;
		Item temp;
		while (esq >= 0) {
			refazHeap(esq, this.vetor.length - 1);
			esq--;
		}
		while (dir > 0) {
			temp = this.vetor[0];
			this.vetor[0] = this.vetor[dir];
			this.vetor[dir] = temp;
			dir--;
			refazHeap(0, dir);
		}
	}

	private void refazHeap(int esq, int dir) {
		int i = esq;
		int mF = 2 * i + 1; // maior filho
		Item raiz = this.vetor[i];
		boolean heap = false;
		while ((mF <= dir) && (!heap)) {
			if (mF < dir)
				if (this.vetor[mF].getData() < this.vetor[mF + 1].getData())
					mF++;
			if (raiz.getData() < this.vetor[mF].getData()) {
				this.vetor[i] = this.vetor[mF];
				i = mF;
				mF = 2 * i + 1;
			} else
				heap = true;
		}
		this.vetor[i] = raiz;
	}

	// ================================================================================================//

	public void quicksort() {
		ordena(0, this.vetor.length - 1);
	}

	private void ordena(int esq, int dir) {
		int pivo, i = esq, j = dir;
		Item temp;
		pivo = this.vetor[(i + j) / 2].getData();
		do {
			while (this.vetor[i].getData() < pivo)
				i++;
			while (this.vetor[j].getData() > pivo)
				j--;
			if (i <= j) {
				temp = this.vetor[i];
				this.vetor[i] = this.vetor[j];
				this.vetor[j] = temp;
				i++;
				j--;
			}
		} while (i <= j);
		if (esq < j)
			ordena(esq, j);
		if (dir > i)
			ordena(i, dir);
	}

	// =====================================================================================================//

	public int pesqBinaria(int chave) {
		int meio, esq, dir;
		esq = 0;
		dir = this.vetor.length - 1;
		while (esq <= dir) {
			meio = (esq + dir) / 2;
			if (chave == this.vetor[meio].getData())
				return meio;
			else {
				if (chave < this.vetor[meio].getData())
					dir = meio - 1;
				else
					esq = meio + 1;
			}
		}
		return -1;
	}
}