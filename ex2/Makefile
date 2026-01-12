CC = gcc
all: bin/skiplist bin/skiplist_main bin/skiplist_tests

CFLAGS = -g -Wall -Wextra -Wpedantic -Wconversion -Wno-pointer-arith 

INCLUDES = src/*.h

COMMON_DEPS = $(INCLUDES) Makefile

build/%.o: src/%.c $(COMMON_DEPS)
	$(CC) $(CFLAGS) -c $< -o $@

bin/skiplist: build/skiplist.o build/skiplist_main.o $(COMMON_DEPS)
	$(CC) -o bin/skiplist build/skiplist.o build/skiplist_main.o

bin/skiplist_tests: build/skiplist_tests.o build/skiplist.o build/unity.o $(COMMON_DEPS)
	$(CC) -o bin/skiplist_tests  build/skiplist_tests.o build/skiplist.o build/unity.o


bin/skiplist_main: build/skiplist_main.o build/skiplist.o $(COMMON_DEPS)
	$(CC) -o bin/skiplist_main  build/skiplist_main.o build/skiplist.o 

clean:
	rm -f build/* bin/*