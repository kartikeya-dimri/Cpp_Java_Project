output: Main.o Authenticate.o
	g++ Main.o Authenticate.o -o output

Main.o: Main.cpp
	g++ -c Main.cpp

Authenticate.o: Authenticate.h Authenticate.cpp
	g++ -c Authenticate.cpp
