Autor: Matias Bernal
Materia: Simulacion
Año: 2015
Titulo: Ejercicio 4 - Practica 8

Se necesita tener configuradas las variables de entorno para los programas:

*Java (vesion 1.7 o superior)
*Gnuplot (4.4 o superior)

Ademas se necesita configurar la ruta donde se guardan los .dat en el archivo conf.ini
usando la misma en donde estan los scripts para ejecutar la simulacion.

Archivos (minimos) necesarios:

*Simulacion-j1.7.jar (para java version 1.7) o Simulacion-j1.8.jar (para java version 1.8)
*conf.ini
*grafico1.plg
*grafico2.plg

-------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------
Ejecucion:

Metodo 1:

Para ejecutar el script que ejecuta la simulacion y despues los graficos

Windows:

Ejecutar el archivo (usando java version 1.7 si se desea usar java version 1.8 
debera editar este archivo y cambiar la linea que dice 
"java -jar Simulacion-j1.7.jar" por java -jar Simulacion-j1.8.jar) 

run.cmd

GNU\Linux (usando java version 1.7)

Ejecutar en consola

$ sh run.sh
-------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------
Metodo 2:

Usando la JVM ejecutar en terminar/consola:

(usando java version 1.7)

$ java -jar Simulacion-j1.7.jar

(usando java version 1.8)

$ java -jar Simulacion-j1.8.jar

Esto muestra los datos de la simulacion y genera los archivos grafico1.dat y 
grafico2.dat para el Gnuplot.

-------------------------------------------------------------------------------------
Windows:

Para ver los graficos ejecutar en consola de windows

Para el grafico 1

$ gnuplot -persist -e "filename='grafico1.dat'" grafico1.plg

Para el grafico 2

-------------------------------------------------------------------------------------
Gnu/Linux

Para ver los graficos ejecutar en consola

Para el grafico 1

$ gnuplot -persist -e "filename='grafico1.dat'" grafico1.plg

Para el grafico 2

$ gnuplot -persist -e "filename='grafico2.dat'" grafico2.plg

-------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------

Testeado en:

-------------------------------------------------------------------------------------
Hardware

Procesador: Intel® Core™ i5 CPU M 450  @ 2.40GHz × 4
Memoria: 3,7 GiB

-------------------------------------------------------------------------------------
Software

1)
*Windows 7 x64
*Gnuplot Version 5.0 patchlevel 0
*JDk x64 java version 1.8.0_20
2)
*Elementary OS Versión: 0.2.1 "Luna" ( 64-bit ) Built on: Ubuntu 12.04 ( "Precise" )
*Gnuplot Version 4.4 patchlevel 3
*OpenJDK 64bits java version 1.7.0_79
-------------------------------------------------------------------------------------
