#include "array_sorting.h"

// 0 stands for last_partition
// 1 stands for randomized_partition
// 2 stands for hoare_partition
// 3 stands for first_partition
#define WHICHPARTITION 2

static void _quicksort(void* arr, size_t size, int first, int last, int (*compare)(void*, void*));
static void swap(void* v1, void* v2, size_t size);
static int last_partition(void* arr, size_t size, int first, int last, int (*compare)(void *, void *));
static int randomized_partition(void* arr, size_t size, int first, int last, int (*compare)(void *, void *));
static int hoare_partition(void* arr, size_t size, int first, int last, int (*compare)(void *, void *));
static int first_partition(void* arr, size_t size, int first, int last, int (*compare)(void *, void *));

static int binary_search(void* arr, size_t size, void* item, int first, int last,int (*compare)(void *, void *));

void quick_sort(void* arr, size_t size, size_t nmemb, int (*compare)(void*, void*)){
  if(arr == NULL){
    fprintf(stderr, "quick_sort: arr parameter cannot be NULL\n");
    exit(EXIT_FAILURE);
  }
  if(compare == NULL){
    fprintf(stderr, "quick_sort: compare parameter cannot be NULL\n");
    exit(EXIT_FAILURE);
  }
  _quicksort(arr, size, 0, (int)nmemb - 1, compare);
}

void binary_insertion_sort(void* arr, size_t size, size_t n, int (*compare)(void *, void *)){
  if(arr == NULL){
    fprintf(stderr, "binary_insertion_sort: arr parameter cannot be NULL\n");
    exit(EXIT_FAILURE);
  }
  if(compare == NULL){
    fprintf(stderr, "binary_insertion_sort: compare parameter cannot be NULL\n");
    exit(EXIT_FAILURE);
  }
  int i, pos, j;
  void *selected = malloc(size * sizeof(size_t));
  for (i = 1; i < (int)n; ++i){
    j = i - 1;
    memcpy(selected, arr + (i * (int)size), size);
    pos = binary_search(arr,size, selected, 0, j,compare); // find position where selected should be inserted
    while(j >= pos){
      memcpy(arr + ((j + 1) * (int)size), arr + (j * (int)size), size);
      j--;
    }
    memcpy(arr + ((j + 1) * (int)size), selected, size);
  }
  free(selected);
}

// This function is the real implementation of the quicksort algorithm.
// It can use different partition function based on the value of the macro WICHPARTITION.
static void _quicksort(void* arr, size_t size, int first, int last, int (*compare)(void*, void*)){
  if (first >= last)
    return;
  // last_partition
  #if WHICHPARTITION == 0
  int p = last_partition(arr, size, first, last, compare);
  _quicksort(arr, size, first, p - 1, compare);
  _quicksort(arr, size, p + 1, last, compare);
  #endif
  // randomized_partition
  #if WHICHPARTITION == 1
  int p = randomized_partition(arr, size, first, last, compare);
  _quicksort(arr, size, first, p - 1, compare);
  _quicksort(arr, size, p + 1, last, compare);
  #endif
  // hoare_partition
  #if WHICHPARTITION == 2
  int p = hoare_partition(arr, size, first, last, compare);
  _quicksort(arr, size, first, p, compare);
  _quicksort(arr, size, p + 1, last, compare);
  #endif
  // first_partition
  #if WHICHPARTITION == 3
  int p = first_partition(arr, size, first, last, compare);
  _quicksort(arr, size, first, p - 1, compare);
  _quicksort(arr, size, p + 1, last, compare);
  #endif
}

// The swap function swaps the values of two elements.
static void swap(void* v1, void* v2, size_t size){
  char buffer[size];
  memcpy(buffer, v1, size);
  memcpy(v1, v2, size);
  memcpy(v2, buffer, size);
}

// This function selects the last element as the pivot to partition the array around.
static int last_partition(void* arr, size_t size, int first, int last, int (*compare)(void *, void *)){
  void* x = arr + (last * (int)size);
  int i = first - 1;
  for(int j = first; j <= last - 1; j++){
    if((*compare)(arr + (j * (int)size), x) <= 0){
      i = i + 1;
      swap(arr + (i * (int)size), arr + (j * (int)size), size);
    }
  } 
  swap(arr + ((i + 1) * (int)size), arr + (last * (int)size), size);
  return i + 1;
}

// A randomized partition routine. It uses a randomly choosen element of the array as pivot.
static int randomized_partition(void* arr, size_t size, int first, int last, int (*compare)(void *, void *)){
  // choose a random index between 'first' and 'last'
  int pivotIndex = rand() % (last - first + 1) + first;
  // swap the last element with the element present at random index
  swap(arr + pivotIndex * (int)size, arr + last *(int)size, size);
  // call partition procedure
  return last_partition(arr, size, first, last, compare);
}

// Hoare works by moving two boundaries towards the middle, one from the left and one from the right. 
// The following steps are done in a loop:
// The left boundary moves until it reaches something larger than the chosen element.
// The right boundary moves until it reaches something smaller than the chosen element.
// If the boundaries haven't crossed, the two are swapped.
// This process is repeated until the boundaries meet in the middle. 
// The result is a partition with everything less on the left and everything greater on the right.
// The pivot element at the beginning is the first element.
static int hoare_partition(void* arr, size_t size, int first, int last, int (*compare)(void *, void *)){
  void* x = arr + (first * (int)size);
  int i = first - 1;
  int j = last + 1;
  while(1){
    do{
      i++;
    } while(compare(arr + i*(int)size, x) < 0);
    do{
      j--;
    } while(compare(arr + j*(int)size, x) > 0);
    
    if(i < j)
      swap(arr + (i * (int)size), arr + (j * (int)size), size);
    else
      return j;
  }
}

// This function selects the first element as the pivot to partition the array around.
static int first_partition(void* arr, size_t size, int first, int last, int (*compare)(void *, void *)){
  int i = first + 1;
  int j = last;
  while (i <= j){
    if((*compare)(arr + (i * (int)size), arr + (first * (int)size)) <= 0)
      i = i + 1;
    else{
      if((*compare)(arr + (j * (int)size), arr + (first * (int)size)) > 0)
        j = j - 1;
      else{
        swap(arr + (i * (int)size), arr + (j * (int)size), size);
        i = i + 1;
        j = j - 1;
      }
    }
  }
  swap(arr + (first * (int)size), arr + (j * (int)size), size);
  return j;
}

// This function returns the position where the new element must be inserted.
static int binary_search(void* arr, size_t size, void* item, int first, int last, int (*compare)(void *, void *)){
  if (last <= first)
    return ((*compare)(item, arr + first * (int)size) > 0)? (first + 1):first;
  int mid = (first + last)/2;
  if((*compare)(item, arr + mid * (int)size) == 0)
    return mid + 1;
  if((*compare)(item, arr + mid * (int)size) > 0)
    return binary_search(arr, size, item, mid + 1, last, compare);
  return binary_search(arr, size, item, first, mid - 1, compare);
}

