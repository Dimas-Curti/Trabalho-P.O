package algoritimo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;

public class Leitura_Arquivo {
	private static BufferedWriter obj = null;

	public static Item[] criarVetor(String nome) {
		Item[] vetor;
		try {
			// faz a leitura dos bytes
			InputStream arq = new FileInputStream(nome);
			// Ler os caracteres(char)
			InputStreamReader isr = new InputStreamReader(arq);
			// ler a cadeia de caracteres(String)
			BufferedReader objeto = new BufferedReader(isr);
			File arq1 = new File(nome);
			LineNumberReader linhaLeitura = new LineNumberReader(new FileReader(arq1));
			linhaLeitura.skip(arq1.length());
			int qtdLinha = linhaLeitura.getLineNumber();
			linhaLeitura.close();
			// inicializa o vetor com o tamanho do arquivo
			vetor = new Item[qtdLinha + 1];
			String[] dados;
			for (int i = 0; i < vetor.length; i++) {
				dados = objeto.readLine().split(";");
				vetor[i] = new Item(inverteData(dados[0]), dados[1], dados[2], dados[3], Boolean.valueOf(dados[4]));
			}
			objeto.close();
			isr.close();
			return vetor;
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static void gravar_Arq(String diretorio, Item[] vet) {
		try {
			OutputStream gravacao = new FileOutputStream(diretorio);
			OutputStreamWriter osw = new OutputStreamWriter(gravacao);
			BufferedWriter objeto = new BufferedWriter(osw);
			String dados;
			for (int i = 0; i < vet.length; i++) {
				dados = vet[i].toString();
				dados = arrumaData(vet[i].getData()) + (dados.substring(dados.indexOf(";"), dados.length()));
				objeto.write(dados);
				objeto.newLine();
			}
			// Ao chamar o flush dizes que nesse momento quero forçar que todo o
			// conteúdo do buffer seja efectivamente escrito em disco.
			objeto.flush();
			objeto.close();
			osw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int[] criarVetorData(String nome) {
		int[] vetorDatas;
		try {
			InputStream arq = new FileInputStream(nome);
			InputStreamReader isr = new InputStreamReader(arq);
			BufferedReader objeto = new BufferedReader(isr);
			File arq1 = new File(nome);
			LineNumberReader linhaLeitura = new LineNumberReader(new FileReader(arq1));
			linhaLeitura.skip(arq1.length());
			int qtdLinha = linhaLeitura.getLineNumber();
			linhaLeitura.close();
			vetorDatas = new int[qtdLinha + 1];
			String[] dados;
			for (int i = 0; i < vetorDatas.length; i++) {
				dados = objeto.readLine().split("/");
				vetorDatas[i] = Integer.valueOf(dados[2] + dados[1] + dados[0]);
			}
			objeto.close();
			isr.close();
			return vetorDatas;
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static void abrir_arq(String diretorio) {
		try {
			OutputStream gravacao = new FileOutputStream(diretorio);
			OutputStreamWriter osw = new OutputStreamWriter(gravacao);
			obj = new BufferedWriter(osw);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	public static void fechar_arq() {
		try {
			obj.flush();
			obj.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// metodo para gravar os dados da pesquisa
	public static void gravar_Arq_Data(Integer Data, String pago, String notPago, Double valor) {
		try {
			obj.write("Para a data: " + arrumaData(Data));
			obj.newLine();
			String[] dados;
			String data;
			obj.write("PAGAS:");
			obj.newLine();
			dados = pago.split(",");
			for (int i = 0; (i < dados.length); i++) {
				if (dados[i] != "") {
					data = arrumaData(Integer.valueOf(dados[i].substring(0, dados[i].indexOf(";"))));
					data = data + (dados[i].substring(dados[i].indexOf(";"), dados[i].length()));
					obj.write(data);
					obj.newLine();
				}
			}
			obj.newLine();
			obj.write("NÃO PAGAS:");
			obj.newLine();
			dados = notPago.split(",");
			for (int i = 0; (i < dados.length); i++) {
				if (dados[i] != "") {
					data = arrumaData(Integer.valueOf(dados[i].substring(0, dados[i].indexOf(";"))));
					data = data + (dados[i].substring(dados[i].indexOf(";"), dados[i].length()));
					obj.write(data);
					obj.newLine();
				}
			}
			obj.newLine();
			DecimalFormat f = new DecimalFormat("0.00");
			obj.write("TOTAL NÃO PAGO: " + "R$" + f.format(valor));
			obj.newLine();
			obj.newLine();
			obj.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Grava as datas não encontradas.
	public static void not_Datas(String vet) {
		String[] dados = vet.split(";");
		try {
			obj.write("Não a promissorias nas seguintes datas:");
			obj.newLine();
			for (int i = 0; i < dados.length; i++) {
				if (dados[i] != "") {
					obj.write(arrumaData(Integer.valueOf(dados[i])));
					obj.newLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// metodo para arrumar a data na hora de gravar
	public static String arrumaData(Integer date) {
		String dados, data, dia, mes, ano;
		dados = String.valueOf(date);
		dia = dados.substring(6, 8) + "/";
		mes = dados.substring(4, 6) + "/";
		ano = dados.substring(0, 4);
		data = (dia + mes + ano);
		return data;
	}

	// metodo para inverter a data para fazer a pesquisa
	public static Integer inverteData(String Data) {
		String dia, mes, ano;
		String[] v = Data.split("/");
		dia = v[0];
		mes = v[1];
		ano = v[2];
		Integer s = Integer.valueOf(ano + mes + dia);
		return s;
	}

}
