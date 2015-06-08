import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;

import utils.Configuration;
import utils.Triple;


public class LotkaVolterra {
	
	private BigDecimal r = new BigDecimal(0.0);
	private BigDecimal a = new BigDecimal(0.0); 
	private BigDecimal b = new BigDecimal(0.0);
	private BigDecimal m = new BigDecimal(0.0);
	private BigDecimal h = new BigDecimal(0.0);
	private BigDecimal min_t = new BigDecimal(0.0);
	private BigDecimal max_t = new BigDecimal(0.0);
		
	private LinkedList<Triple<BigDecimal, BigDecimal, BigDecimal>> log = new LinkedList<Triple<BigDecimal, BigDecimal, BigDecimal>>(); 
	
	public LotkaVolterra() {
		setR(Configuration.getR());
		setA(Configuration.getA());
		setB(Configuration.getB());
		setM(Configuration.getM());
		setH(Configuration.getH());
		setMinT(Configuration.getMin_t());
		setMaxT(Configuration.getMax_t());
	}
	
	//Numero de presas
	public BigDecimal p(BigDecimal t){
		assert t!=null;
		if(t.doubleValue()==0.0)
			return new BigDecimal("2.000");
		else
			return p(t.subtract(getH())).add(getH().multiply(dp_dt(t.subtract(getH())))).round(Configuration.getMc());
	}
	
	public BigDecimal dp_dt(BigDecimal t){
		assert t!=null;
		return p(t).multiply(getR().subtract(getA().multiply(d(t)))).round(Configuration.getMc());

	}
	
	//Numero de depredadores
	public BigDecimal d(BigDecimal t){
		assert t!=null;
		if(t.doubleValue()==0.0)
			return new BigDecimal("2.000");
		else
			return d(t.subtract(getH())).add(getH().multiply(dd_dt(t.subtract(getH())))).round(Configuration.getMc());
	}
	
	public BigDecimal dd_dt(BigDecimal t){
		assert t!=null;
		return d(t).multiply(getB().multiply(p(t)).subtract(getM())).round(Configuration.getMc());
	}

	public String generate_grap1() {
		String dat_graph = "# This file is called grafico1.dat \n";
		dat_graph+= "#Time\tPresas\tDepredadores\n";
		Iterator<Triple<BigDecimal,BigDecimal,BigDecimal>> iterator = log.iterator();
        while (iterator.hasNext()){
        	Triple<BigDecimal,BigDecimal,BigDecimal> elementTriple = iterator.next();
        	dat_graph += elementTriple.first().round(Configuration.getMc()).toString()+'\t';
        	dat_graph += elementTriple.second().round(Configuration.getMc()).toString()+'\t';
        	dat_graph += elementTriple.third().round(Configuration.getMc()).toString()+'\n';
        }
        BufferedWriter output = null;
        try {
            File file = new File(Configuration.getPath_dat()+File.separator+"grafico1.dat");
            file.getParentFile().mkdirs(); 
            file.createNewFile();
            output = new BufferedWriter(new FileWriter(file));
            output.write(dat_graph);
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if ( output != null )
				try {
					output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }
		return dat_graph;
	}
	
	public String generate_grap2() {
		String dat_graph = "# This file is called grafico2.dat \n";
		dat_graph+= "#Presas\tDepredadores\n";
		Iterator<Triple<BigDecimal,BigDecimal,BigDecimal>> iterator = log.iterator();
        while (iterator.hasNext()){
        	Triple<BigDecimal,BigDecimal,BigDecimal> elementTriple = iterator.next();
        	dat_graph += elementTriple.second().round(Configuration.getMc()).toString()+'\t';
        	dat_graph += elementTriple.third().round(Configuration.getMc()).toString()+'\n';
        }
        BufferedWriter output = null;
        try {
            File file = new File(Configuration.getPath_dat()+File.separator+"grafico2.dat");
            file.getParentFile().mkdirs(); 
            file.createNewFile();
            output = new BufferedWriter(new FileWriter(file));
            output.write(dat_graph);
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if ( output != null )
				try {
					output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }
		return dat_graph;
	}	
	
	public void simulacion(){
		for(BigDecimal t = min_t; t.compareTo(max_t)<=0; t = t.add(h)) {
			BigDecimal presa = p(t);
			BigDecimal depredador = d(t);
			Triple<BigDecimal, BigDecimal, BigDecimal> log_data = new Triple<BigDecimal, BigDecimal, BigDecimal> (t,presa,depredador);
			log.add(log_data);
			System.out.println("Tiempo:"+ t +" Nro Presas:" + presa.toString()+" Nro Depredadores:" + depredador.toString());
		}
	}
	
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
}
