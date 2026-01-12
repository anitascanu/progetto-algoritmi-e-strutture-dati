CC = gcc
all: bin/array_sorting bin/array_sorting_tests bin/array_sorting_main

CFLAGS = -g -Wextra -Wpedantic -Wconversion -Wno-pointer-arith # -Wall

INCLUDES = src/*.h

COMMON_DEPS = $(INCLUDES) Makefile

build/%.o: src/%.c $(COMMON_DEPS)
	$(CC) $(CFLAGS) -c $< -o $@

bin/array_sorting: build/array_sorting.o build/array_sorting_main.o $(COMMON_DEPS)
	$(CC) -o bin/array_sorting build/array_sorting.o build/array_sorting_main.o

bin/array_sorting_tests: build/array_sorting_tests.o build/array_sorting.o build/unity.o $(COMMON_DEPS)
	$(CC) -o bin/array_sorting_tests build/array_sorting_tests.o build/array_sorting.o build/unity.o


bin/array_sorting_main: build/array_sorting_main.o build/array_sorting.o $(COMMON_DEPS)
	$(CC) -o bin/array_sorting_main build/array_sorting_main.o build/array_sorting.o 

clean:
	rm -f build/* bin/*