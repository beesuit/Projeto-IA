
public class Amostra {
	
	private double x0;
	private double x1;
	private double x2;
	private double y;
	
	public Amostra(String[] valores){
		x0 = 1;
		x1 = Double.parseDouble(valores[0]);
		x2 = Double.parseDouble(valores[1]);
		y = Double.parseDouble(valores[2]);
	}
	
	public double getX0(){
		return x0;
	}
	
	public double getX1(){
		return x1;
	}
	
	public double getX2(){
		return x2;
	}
	
	public double getY(){
		return y;
	}

}
