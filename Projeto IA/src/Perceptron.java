import java.util.ArrayList;


public class Perceptron {
	
	private ArrayList<Amostra> amostras;
	private double tamanho;
	
	final double TH = 1; 
	
	public Perceptron(ArrayList<Amostra> array){
		amostras = array;
		tamanho = amostras.size();
	}
	
	public void fAptidão(Cromossomo c){
		
		double acertoCount = 0;
		double valorW0 = c.getValorW0();
		double valorW1 = c.getValorW1();
		double valorW2 = c.getValorW2();
		
		for(Amostra a : amostras){
			if(threshold(valorW0 * a.getX0() + valorW1 * a.getX1() + valorW2 * a.getX2()) == a.getY()){
				acertoCount += 1;
			}
		}
		
		c.setAptidão(acertoCount/tamanho);
	}
	
	private double threshold(double n){
		if(n <= TH){
			return 1; 
		}
		else{
			return 2;
		}
	}

}
