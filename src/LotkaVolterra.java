import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;

import utils.Configuration;
import utils.Triple;


public class LotkaVolterra {
	
	private BigDecimal r = new BigDecimal(0.0); //Tasa de crecimiento del número de presas en ausencia de depredadores.
	private BigDecimal a = new BigDecimal(0.0); //Coeficiente de depredación.
	private BigDecimal b = new BigDecimal(0.0); //Tasa de crecimiento del número de depredadores en presencia de presas.
	private BigDecimal m = new BigDecimal(0.0); //Tasa de mortalidad de los depredadores.
	private BigDecimal h = new BigDecimal(0.0); //Paso de integración.
	private BigDecimal min_t = new BigDecimal(0.0); //Tiempo minimo
	private BigDecimal max_t = new BigDecimal(0.0); //Tiempo maximo
		
	private LinkedList<Triple<BigDecimal, BigDecimal, BigDecimal>> log = new LinkedList<Triple<BigDecimal, BigDecimal, BigDecimal>>(); //lista de datos
	private LinkedList<String> graficos = new LinkedList<String>(); //Lista con los graficos para GNUPlot

	//Constructor de la clase
	public LotkaVolterra() {
		setR(Configuration.getR());
		setA(Configuration.getA());
		setB(Configuration.getB());
		setM(Configuration.getM());
		setH(Configuration.getH());
		setMinT(Configuration.getMin_t());
		setMaxT(Configuration.getMax_t());
	}
		
	//Metodo Euler para calcular el valor de q(t+h) = q(t) + h + (dp(t)/dt)
	public BigDecimal euler(BigDecimal derivada, BigDecimal valor_en_t){
		return valor_en_t.add(getH().multiply(derivada));
	}
	
	//Simulacion para el calculo de p(t) y d(t) para el modelo Lotka-Volterra
	//Ademas genera los archivos .dat para los graficos del GNUPLOT
	public void simulacion(){
		
		BigDecimal valor_de_p_en_t = new BigDecimal("2.000");
		BigDecimal valor_de_d_en_t = new BigDecimal("2.000");
		
		String dat_graph1 = "# Archivo grafico1.dat \n";
		dat_graph1+= "#Time\tPresas\tDepredadores\n";
		
		String dat_graph2 = "# Archivo grafico2.dat \n";
		dat_graph2+= "#Presas\tDepredadores\n";
		
		dat_graph1 += min_t.round(Configuration.getMc()).toString()+'\t';
    	dat_graph1 += valor_de_p_en_t.round(Configuration.getMc()).toString()+'\t';
    	dat_graph1 += valor_de_d_en_t.round(Configuration.getMc()).toString()+'\n';
    	
    	dat_graph2 += valor_de_p_en_t.round(Configuration.getMc()).toString()+'\t';
    	dat_graph2 += valor_de_d_en_t.round(Configuration.getMc()).toString()+'\n';
		
		System.out.println("Tiempo:"+ min_t.round(Configuration.getMc()).toString() +" Nro Presas:" + valor_de_p_en_t.round(Configuration.getMc()).toString()+" Nro Depredadores:" + valor_de_d_en_t.round(Configuration.getMc()).toString());
		
		for(BigDecimal t = min_t.add(getH()); t.compareTo(max_t)<=0; t = t.add(h)) {
			
			BigDecimal derivada_de_p = new BigDecimal(0.00);
			BigDecimal derivada_de_d = new BigDecimal(0.00);
			
			for(int i=0; i<log.size();i++){
				if(log.get(i).first().equals(t.subtract(getH()))){
					derivada_de_p = log.get(i).second().multiply(getR().subtract(getA().multiply(log.get(i).third())));
					derivada_de_d = log.get(i).third().multiply(getB().multiply(log.get(i).second().subtract(getM())));
					break;
				}
			}
			valor_de_p_en_t = euler(derivada_de_p, valor_de_p_en_t);
			valor_de_d_en_t = euler(derivada_de_d, valor_de_d_en_t);

			Triple<BigDecimal, BigDecimal, BigDecimal> log_data2 = new Triple<BigDecimal, BigDecimal, BigDecimal> (t,valor_de_p_en_t,valor_de_d_en_t);
			log.add(log_data2);
			dat_graph1 += t.round(Configuration.getMc()).toString()+'\t';
	    	dat_graph1 += valor_de_p_en_t.round(Configuration.getMc()).toString()+'\t';
	    	dat_graph1 += valor_de_d_en_t.round(Configuration.getMc()).toString()+'\n';
	    	
	    	dat_graph2 += valor_de_p_en_t.round(Configuration.getMc()).toString()+'\t';
	    	dat_graph2 += valor_de_d_en_t.round(Configuration.getMc()).toString()+'\n';
			   
			System.out.println("Tiempo:"+ t.round(Configuration.getMc()).toString() +" Nro Presas:" + valor_de_p_en_t.round(Configuration.getMc()).toString()+" Nro Depredadores:" + valor_de_d_en_t.round(Configuration.getMc()).toString());
		}
		
		graficos.add(dat_graph1);
		graficos.add(dat_graph2);
		
        BufferedWriter output1 = null;
        BufferedWriter output2 = null;
        try {
        	//Genero el primer archivo .dat para el grafico 1
            File file1 = new File(Configuration.getPath_dat()+File.separator+"grafico1.dat");
            file1.getParentFile().mkdirs(); 
            file1.createNewFile();
            //Genero el segundo archivo .dat para el grafico 2
            File file2 = new File(Configuration.getPath_dat()+File.separator+"grafico2.dat");
            file2.getParentFile().mkdirs(); 
            file2.createNewFile();
            
            output1 = new BufferedWriter(new FileWriter(file1));
            output1.write(dat_graph1);
            
            output2 = new BufferedWriter(new FileWriter(file2));
            output2.write(dat_graph2);
            
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if ( output1 != null && output2 != null)
				try {
					output1.close();
					output2.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        }
	}
	
	//Gets y Sets de los atributos
	
	public BigDecimal getR() {
		return r;
	}

	public void setR(BigDecimal r) {
		this.r = r;
	}

	public BigDecimal getA() {
		return a;
	}


	public void setA(BigDecimal a) {
		this.a = a;
	}


	public BigDecimal getB() {
		return b;
	}


	public void setB(BigDecimal b) {
		this.b = b;
	}


	public BigDecimal getM() {
		return m;
	}


	public void setM(BigDecimal m) {
		this.m = m;
	}


	public BigDecimal getH() {
		return h;
	}


	public void setH(BigDecimal h) {
		this.h = h;
	}


	public BigDecimal getMinT() {
		return min_t;
	}


	public void setMinT(BigDecimal min_t) {
		this.min_t = min_t;
	}
	
	public BigDecimal getMaxT() {
		return max_t;
	}


	public void setMaxT(BigDecimal min_t) {
		this.max_t = min_t;
	}

	public LinkedList<String> getGraficos() {
		return graficos;
	}

	public void setGraficos(LinkedList<String> graficos) {
		this.graficos = graficos;
	}
}
