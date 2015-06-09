import utils.Configuration;


public class Main {

	public static void main(String[] args) {
		Configuration.setConf("conf.ini");
		LotkaVolterra lv = new LotkaVolterra();
		System.out.println("-----------------------------------");
		System.out.println("Parametros del modelo:");
		System.out.println("r = "+lv.getR());
		System.out.println("a = "+lv.getA());
		System.out.println("b = "+lv.getB());
		System.out.println("m = "+lv.getM());
		System.out.println("h = "+lv.getH());
		System.out.println("Tiempo inicial simulacion = "+lv.getMinT());
		System.out.println("Tiempo final simulacion = "+lv.getMaxT());
		System.out.println("-----------------------------------");
		lv.simulacion();
		System.out.println("-----------------------------------");
		System.out.println("Dat file grafico1.dat");
		System.out.println(lv.getGraficos().getFirst());
		System.out.println("-----------------------------------");
		System.out.println("Dat file grafico2.dat");
		System.out.println(lv.getGraficos().getLast());
	}

}
