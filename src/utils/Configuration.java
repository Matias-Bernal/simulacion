/********************************************************
  This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *********************************************************/
package utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Configuration {

	/* Valores por defecto - verificar el conf.ini */
	private static BigDecimal r = new BigDecimal(0.00); //Tasa de crecimiento del número de presas en ausencia de depredadores.
	private static BigDecimal a = new BigDecimal(0.00); //Coeficiente de depredación.
	private static BigDecimal b = new BigDecimal(0.00); //Tasa de crecimiento del número de depredadores en presencia de presas.
	private static BigDecimal m = new BigDecimal(0.00); //Tasa de mortalidad de los depredadores.
	private static BigDecimal h = new BigDecimal(0.00); //Paso de integración.
	private static BigDecimal min_t = new BigDecimal(0.0); //Tiempo minimo
	private static BigDecimal max_t = new BigDecimal(0.0); //Tiempo maximo
	private static String path_dat = "";
	private static MathContext mc = MathContext.UNLIMITED;

	public static void setConf(String nameFile) {
		if ((nameFile == null)||(nameFile.trim().length() == 0)){
			nameFile = "conf.ini";
		}
		IniFile ini = new IniFile(nameFile);
		setR(new BigDecimal(ini.getParameters("r")));
		setA(new BigDecimal(ini.getParameters("a")));
		setB(new BigDecimal(ini.getParameters("b")));
		setM(new BigDecimal(ini.getParameters("m")));
		setH(new BigDecimal(ini.getParameters("h")));
		setMin_t(new BigDecimal(ini.getParameters("min_t")));
		setMax_t(new BigDecimal(ini.getParameters("max_t")));
		setPath_dat(ini.getParameters("path_dat"));
		if(Integer.valueOf(ini.getParameters("decimal_pre")).intValue()>0){
			if(Integer.valueOf(ini.getParameters("rounded")).intValue()==0)
				setMc(new MathContext(Integer.valueOf(ini.getParameters("decimal_pre")).intValue(), RoundingMode.HALF_UP));
			else
				setMc(new MathContext(Integer.valueOf(ini.getParameters("decimal_pre")).intValue(), RoundingMode.HALF_DOWN));
		}else
			setMc(MathContext.UNLIMITED);
	}
	
	public static BigDecimal getR() {
		return r;
	}

	public static void setR(BigDecimal r) {
		Configuration.r = r;
	}

	public static BigDecimal getA() {
		return a;
	}

	public static void setA(BigDecimal a) {
		Configuration.a = a;
	}

	public static BigDecimal getB() {
		return b;
	}

	public static void setB(BigDecimal b) {
		Configuration.b = b;
	}

	public static BigDecimal getM() {
		return m;
	}

	public static void setM(BigDecimal m) {
		Configuration.m = m;
	}

	public static BigDecimal getH() {
		return h;
	}

	public static void setH(BigDecimal h) {
		Configuration.h = h;
	}

	public static BigDecimal getMin_t() {
		return min_t;
	}

	public static void setMin_t(BigDecimal min_t) {
		Configuration.min_t = min_t;
	}

	public static BigDecimal getMax_t() {
		return max_t;
	}

	public static void setMax_t(BigDecimal max_t) {
		Configuration.max_t = max_t;
	}

	public static String getPath_dat() {
		return path_dat;
	}

	public static void setPath_dat(String path_dat) {
		Configuration.path_dat = path_dat;
	}

	public static MathContext getMc() {
		return mc;
	}

	public static void setMc(MathContext mc) {
		Configuration.mc = mc;
	}
	
}

