# COMPILAZIONE

Posizionarsi in ../ex3-ex4/src

## PER COMPILARE LE CLASSI PER LA STRUTTURA DATI MinimumHeap NEL PACKAGE `minimumheap`

javac -d ../classes minimumheap/MinimumHeap.java

## PER COMPILARE IL PACKAGE `minimumheapusage`

javac -d ../classes minimumheapusage/MinimumHeapUsage.java

## PER COMPILARE LE CLASSI PER GLI UNIT TEST NEL PACKAGE `minimumheap`

javac -d ../classes -cp '.;../junit-4.12.jar;../hamcrest-core-1.3.jar'minimumheap/*.java

javac -d ../classes -cp .:../junit-4.12.jar:../hamcrest-core-1.3.jar minimumheap/*.java

## PER COMPILARE LE CLASSI PER LA STRUTTURA DATI Graph NEL PACKAGE `graph`

javac -d ../classes graph/Graph.java

## PER COMPILARE IL PACKAGE `graphusage`

javac -d ../classes graphusage/GraphUsage.java

## PER COMPILARE LE CLASSI PER GLI UNIT TEST NEL PACKAGE `graph`

javac -d ../classes -cp '.;../junit-4.12.jar;../hamcrest-core-1.3.jar' graph/*.java 

javac -d ../classes -cp .:../junit-4.12.jar:../hamcrest-core-1.3.jar graph/*.java

## PER COMPILARE LE CLASSI PER LA CLASSE Dijkstra NEL PACKAGE `dijkstra`

javac -d ../classes dijkstra/Dijkstra.java

## PER COMPILARE IL PACKAGE `dijkstrausage`

javac -d ../classes dijkstrausage/DijkstraUsage.java

---

# ESECUZIONE

Posizionarsi in ../ex3-ex4/classes

## PER ESEGUIRE MinimumHeapUsage

java minimumheapusage/MinimumHeapUsage

## PER ESEGUIRE MinimumHeapTestsRunner

java -cp '.;../junit-4.12.jar;../hamcrest-core-1.3.jar'  minimumheap/MinimumHeapTestsRunner

java -cp .:../junit-4.12.jar:../hamcrest-core-1.3.jar  minimumheap/MinimumHeapTestsRunner

## PER ESEGUIRE GraphUsage

java graphusage/GraphUsage

## PER ESEGUIRE GraphTestsRunner

java -cp '.;../junit-4.12.jar;../hamcrest-core-1.3.jar' graph/GraphTestsRunner

java -cp .:../junit-4.12.jar:../hamcrest-core-1.3.jar graph/GraphTestsRunner

## PER ESEGUIRE DijkstraUsage

java dijkstrausage/DijkstraUsage `file_path` `source` `destination`

---
`NOTA IMPORTANTE`: i file hamcrest-core-1.3.jar e junit-4.12.jar devono essere nella cartella ../ex3-ex4, altrimenti le regole di compilazione e esecuzione non funzioneranno.
