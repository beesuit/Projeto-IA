import java.util.Random;


public class Cromossomo {
	
	final int TAMANHO_CROMOSSOMO = 24;
	final int TAMANHO_W_INTEIRO = 3;
	final int TAMANHO_W_DECIMAL = 5;
	final int TAMANHO_W = TAMANHO_W_INTEIRO + TAMANHO_W_DECIMAL;
	final int PONTO = 8;
	
	private int[] cromossomo = new int[TAMANHO_CROMOSSOMO];
	private double aptid�o;
	private double valorW0;
	private double valorW1;
	private double valorW2;
	private Random random = new Random();
	private String ln = System.lineSeparator();
	
	
	public Cromossomo(){
		geraCromossomo();
		setValorAll();
		aptid�o = 0;
	}
	
	public Cromossomo(int[] c){
		cromossomo = c;
		setValorAll();
		aptid�o = 0;
	}
	
	private void geraCromossomo(){
		for (int i = 0; i < TAMANHO_CROMOSSOMO; i++){
			cromossomo[i] = random.nextInt(2);
		}
	}
	
	public Cromossomo cruzaCom(Cromossomo c2){
		
		int[] filho = new int[TAMANHO_CROMOSSOMO];
		Cromossomo c;
		//int ponto = random.nextInt(TAMANHO_CROMOSSOMO - 1) + 1; //min 1 - max 23
		
		System.out.println("ponto = " + PONTO);
		
		for (int i = 0; i < PONTO ; i++){
			filho[i] = this.getCromossomo()[i];
		}
		
		for (int i = PONTO; i < TAMANHO_CROMOSSOMO; i++){
			filho[i] = c2.getCromossomo()[i];
		}
		
		c = new Cromossomo(muta��o(filho));
		
		System.out.println(c.toString());
		
		return c;
	}
	
	private int[] muta��o(int[] c){
		int bit = random.nextInt(TAMANHO_CROMOSSOMO);
		System.out.println("bit = " + bit);
		c[bit] = ~c[bit] + 2;
		
		return c;
	}
	
	public Cromossomo checaMaisApto(Cromossomo c){
		if(this.getAptid�o() >= c.getAptid�o()){
			return this;
		}
		else{
			return c;
		}
	}
	
	@Override
	
	public String toString(){
		
		String s = "";
		
		for(int i = 0; i < TAMANHO_CROMOSSOMO; i++){
			
			s = s + getCromossomo()[i];
		}
		
		return s + " W0 = " + getValorW0() + " W1 = " + getValorW1() + " W2 = " + getValorW2() + " Aptid�o = " + getAptid�o();
	}
	
	public int[] getCromossomo(){
		return cromossomo;
	}
	
	public double getAptid�o(){
		return aptid�o;
	}
	
	public double getValorW0(){
		return valorW0;
	}
	
	public double getValorW1(){
		return valorW1;
	}
	
	public double getValorW2(){
		return valorW2;
	}
	
	private void setValor(int w){
		
		double expoente = -1;
		double valor = 0;
		
		for (int i = TAMANHO_W_INTEIRO + TAMANHO_W * w; i < TAMANHO_W * (w+1); i++){
			valor += cromossomo[i]*Math.pow(2, expoente);
			expoente -= 1;
		}
		
		expoente = 1;
		
		for (int i = TAMANHO_W * w + 1; i < TAMANHO_W_INTEIRO + TAMANHO_W * w; i++){
			valor += cromossomo[i]*Math.pow(2, expoente);
			expoente -= 1;
		}
		
		if (cromossomo[TAMANHO_W * w] == 1){
			valor = valor * -1;
		}
		
		switch(w){
		
		case 0:
			valorW0 = valor;
		case 1:
			valorW1 = valor;
		case 2:
			valorW2 = valor;
		}
		
	}
	
	private void setValorAll(){
		setValor(0);
		setValor(1);
		setValor(2);
	}
	
	public void setAptid�o(double value){
		aptid�o = value;
	}
	
	public void setCromossomo(int[] array){
		cromossomo = array;
	}
	
	
}
