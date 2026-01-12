#ifndef ARRAY_SORTING_H_asdjkzlaofvdl
#define ARRAY_SORTING_H_asdjkzlaofvdl

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// The quick_sort function sort an array with nmemb elements of size size.
// The arr argument points to the start of the array.
// The contents of the array are sorted in ascending order according to a
// comparison function pointed to by compare, which is called with two arguments
// that point to the objects being compared.
// The quick_sort function returns no value.
void quick_sort(void* arr, size_t size, size_t nmemb, int (*compare)(void*, void*));

// The binary_insertion_sort function sort an array with n elements of size size.
// The arr argument points to the start of the array.
// The contents of the array are sorted in ascending order according to a
// comparison function pointed to by compare, which is called with two arguments
// that point to the objects being compared.
// The binary_insertion_sort function returns no value.
void binary_insertion_sort(void* arr, size_t size, size_t n, int (*compare)(void *, void *));

#endif /* ARRAY_SORTING_H_asdjkzlaofvdl */ 