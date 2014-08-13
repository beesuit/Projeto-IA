import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;


public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	
	final static int TAMANHO_POPULA��O = 10;
	
	static ArrayList<Amostra> amostras = new ArrayList<Amostra>();
	static ArrayList<Cromossomo> gera��o = new ArrayList<Cromossomo>();
	static ArrayList<Cromossomo> aptos = new ArrayList<Cromossomo>();
	
	static Perceptron perceptron;
	
	static Random random = new Random();
	
	static String ln = System.lineSeparator();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//PrintStream out = new PrintStream(new FileOutputStream("./saida.txt"));
		//System.setOut(out);
		
		int whileCount = 0;
		
		geraPopula��oInicial();
		carregaAmostras();
		
		perceptron = new Perceptron(amostras);
		
	
		while(checaAptid�oDaGera��o()){
			whileCount += 1;
			selecionaAptos();
			printAptos();
			gera��o.clear();
			
			for (int i = 0; i < TAMANHO_POPULA��O/4; i++){
				
				Cromossomo[] pais = sele��oRoleta();
				
				System.out.println(ln + "->Filho 1:");
				gera��o.add(pais[0].cruzaCom(pais[1]));
				System.out.println(ln + "->Filho 2:");
				gera��o.add(pais[1].cruzaCom(pais[0]));
				
			}
			
			gera��o.addAll(aptos);
				
		}
		
		System.out.println(ln + "Terminado. N� de itera��es = " + whileCount);
		
	}
	
	public static void carregaAmostras() throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader("C:/Users/Rodrigo/Desktop/Projeto IA/base.txt"));
		//BufferedReader br = new BufferedReader(new FileReader("./base.txt"));
		
		
		while(br.ready()){
			String linha = br.readLine();
			amostras.add(new Amostra(linha.split(" ")));
		}
		
		br.close();
	}
	
	public static void geraPopula��oInicial() {
		
		for (int i = 0; i < TAMANHO_POPULA��O; i++){
			gera��o.add(new Cromossomo());
		}
	}
	
	public static boolean checaAptid�oDaGera��o() {
		System.out.println(ln + "->Nova Gera��o: ");
		for(Cromossomo c : gera��o){
			perceptron.fAptid�o(c);
			System.out.println(c.toString());
			//System.out.println(c.getAptid�o());
			if(c.getAptid�o() == 1){
				return false;
			}
		}
		return true;
	}
	
	public static void selecionaAptos() {
		
		aptos.clear();
		
		for (int i = 0; i < TAMANHO_POPULA��O/2; i++){
			
			Cromossomo maior = new Cromossomo();
			
			for(Cromossomo c : gera��o){
				maior = maior.checaMaisApto(c);
			}
			
			aptos.add(maior);
			gera��o.remove(maior);
		}
		
	}
	
	public static Cromossomo[] sele��oRoleta() {
		
		Cromossomo a = new Cromossomo();
		Cromossomo b = new Cromossomo();
		double somaP = 0;
		double pNormalizadaAcumulada = 0;
		Cromossomo[] pais = new Cromossomo[2];
		double r;
		
		for (Cromossomo c : aptos){
			somaP = somaP + c.getAptid�o();
		}
		
		//System.out.println("soma" + somaP);
		
		r = random.nextDouble();
		
		for (Cromossomo c : aptos){
			
			pNormalizadaAcumulada = pNormalizadaAcumulada + c.getAptid�o()/somaP;
			
			//System.out.println(r);
			//System.out.println(pNormalizadaAcumulada);
			
			if ((r < pNormalizadaAcumulada) && (c != b)){
				a = c;
				break;
			}
		}
		
		pNormalizadaAcumulada = 0;
		r = random.nextDouble();
		
		for (Cromossomo c : aptos){
			
			pNormalizadaAcumulada = pNormalizadaAcumulada + c.getAptid�o()/somaP;
			
			//System.out.println(r);
			//System.out.println(pNormalizadaAcumulada);
			
			if ((r < pNormalizadaAcumulada) && (c != a)){
				b = c;
				break;
			}
		}
		
		pais[0] = a;
		pais[1] = b;
		
		System.out.println(ln + "->Cruzamento:" + ln + a.toString() + ln + b.toString());
		
		return pais;
		
	}
	
	static void printAptos() {
		System.out.println(ln + "->Aptos: ");
		for(Cromossomo c : aptos){
			System.out.println(c.toString());
		}
	}
	

}
