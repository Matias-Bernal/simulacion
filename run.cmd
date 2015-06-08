cls
@echo off
Title Simulacion - Lotka-Volterra
echo "Simulacion - Lotka-Volterra"
java -jar Simulacion-j1.7.jar
echo "Mostrarndo Grafico 1"
gnuplot -persist -e "filename='grafico1.dat'" grafico1.plg
echo "Mostrando Grafico 2"
gnuplot -persist -e "filename='grafico2.dat'" grafico2.plg
echo "Presione una tecla para salir"
set /p tecla= 