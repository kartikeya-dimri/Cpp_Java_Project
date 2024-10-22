output: Main.o Authenticate.o CEO.o
	g++ Main.o Authenticate.o CEO.o -o output

Main.o: Main.cpp
	g++ -c Main.cpp

Authenticate.o: Authenticate.h Authenticate.cpp
	g++ -c Authenticate.cpp

CEO.o: CEO.h CEO.cpp
	g++ -c CEO.cpp
