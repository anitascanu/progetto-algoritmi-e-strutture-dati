#define _GNU_SOURCE
#include "unity.h"
#include "array_sorting.h"

/*
 * Test suite for array sorting algorithms
 */

// integer precedence relation used in tests
int compare_int(void* a, void* b){
  return *((int*)a) - *((int*)b);
} 

// string precedence relation used in tests
int compare_string(void* v1, void* v2){
  char *a1 = *(char**)v1;
  char *a2 = *(char**)v2;
  return strcmp(a1, a2);
}

// data elements that are initialized before each test
static int i1,i2,i3;

void setUp(void){
  i1 = 1;
  i2 = 2;
  i3 = 3;
}

void tearDown(void){ }

static void test_quicksort_one_el_int(void){
  int exp_arr[] = {i1};
  int act_arr[] = {i1};
  size_t n = sizeof(act_arr) / sizeof(int);
  quick_sort((void *)act_arr, sizeof(int), n, compare_int);
  TEST_ASSERT_EQUAL_INT_ARRAY(exp_arr, act_arr, 1);
}

static void test_quicksort_two_el_int(void){
  int exp_arr[] = {i1,i2};
  int act_arr[] = {i2,i1};
  size_t n = sizeof(act_arr) / sizeof(int);
  quick_sort((void *)act_arr, sizeof(int), n, compare_int);
  TEST_ASSERT_EQUAL_INT_ARRAY(exp_arr, act_arr, 2);
}

static void test_quicksort_three_el_int(void){
  int exp_arr[] = {i1,i2,i3};
  int act_arr[] = {i2,i3,i1};
  size_t n = sizeof(act_arr) / sizeof(int);
  quick_sort((void *)act_arr,sizeof(int), n, compare_int);
  TEST_ASSERT_EQUAL_INT_ARRAY(exp_arr, act_arr, 3);
}

static void test_quicksort_three_el_ordered_backwards_int(void){
  int exp_arr[] = {i1,i2,i3};
  int act_arr[] = {i3,i2,i1};
  size_t n = sizeof(act_arr) / sizeof(int);
  quick_sort((void *)act_arr,sizeof(int), n, compare_int);
  TEST_ASSERT_EQUAL_INT_ARRAY(exp_arr, act_arr, 3);
}

static void test_quicksort_three_el_already_sorted_int(void){
  int exp_arr[] = {i1,i2,i3};
  int act_arr[] = {i1,i2,i3};
  size_t n = sizeof(act_arr) / sizeof(int);
  quick_sort((void *)act_arr,sizeof(int), n, compare_int);
  TEST_ASSERT_EQUAL_INT_ARRAY(exp_arr, act_arr, 3);
}

static void test_quicksort_one_el_string(void){
  char* exp_arr[] = {"abc"};
  char* act_arr[] = {"abc"};
  size_t n = sizeof(act_arr) / sizeof(char*);
  quick_sort((void *)act_arr,sizeof(char*), n, compare_string);
  TEST_ASSERT_EQUAL_INT_ARRAY(exp_arr, act_arr, 1);
}

static void test_quicksort_two_el_string(void){
  char* exp_arr[] = {"abc", "d"};
  char* act_arr[] = {"d","abc"};
  size_t n = sizeof(act_arr) / sizeof(char*);
  quick_sort((void *)act_arr,sizeof(char*), n, compare_string);
  TEST_ASSERT_EQUAL_INT_ARRAY(exp_arr, act_arr, 2);
}

static void test_quicksort_three_el_string(void){
  char* exp_arr[] = {"abc","ok", "zzz"};
  char* act_arr[] = {"zzz", "ok", "abc"};
  size_t n = sizeof(act_arr) / sizeof(char*);
  quick_sort((void *)act_arr,sizeof(char*), n, compare_string);
  TEST_ASSERT_EQUAL_INT_ARRAY(exp_arr, act_arr, 3);
}

static void test_insertsort_one_el_int(void){
  int exp_arr[] = {i1};
  int act_arr[] = {i1};
  size_t n = sizeof(act_arr) / sizeof(int);
  binary_insertion_sort(act_arr, sizeof(int), n, compare_int);
  TEST_ASSERT_EQUAL_INT_ARRAY(exp_arr, act_arr, 1);
}

static void test_insertsort_two_el_int(void){
  int exp_arr[] = {i1,i2};
  int act_arr[] = {i1,i2};
  size_t n = sizeof(act_arr) / sizeof(int);
  binary_insertion_sort(act_arr, sizeof(int), n, compare_int);
  TEST_ASSERT_EQUAL_INT_ARRAY(exp_arr, act_arr, 2);
}

static void test_insertsort_three_el_int(void){
  int exp_arr[] = {i1,i2,i3};
  int act_arr[] = {i1,i3,i2};
  size_t n = sizeof(act_arr) / sizeof(int);
  binary_insertion_sort(act_arr, sizeof(int), n, compare_int);
  TEST_ASSERT_EQUAL_INT_ARRAY(exp_arr, act_arr, 3);
}

static void test_insertsort_three_el_already_sorted_int(void){
  int exp_arr[] = {i1,i2,i3};
  int act_arr[] = {i1,i2,i3};
  size_t n = sizeof(act_arr) / sizeof(int);
  binary_insertion_sort(act_arr, sizeof(int), n, compare_int);
  TEST_ASSERT_EQUAL_INT_ARRAY(exp_arr, act_arr, 3);
}

static void test_insertsort_three_el_ordered_backwards_int(void){
  int exp_arr[] = {i1,i2,i3};
  int act_arr[] = {i3,i2,i1};
  size_t n = sizeof(act_arr) / sizeof(int);
  binary_insertion_sort(act_arr, sizeof(int), n, compare_int);
  TEST_ASSERT_EQUAL_INT_ARRAY(exp_arr, act_arr, 3);
}

static void test_insertsort_three_el_string(void){
  char* exp_arr[] = {"abc","ok", "zzz"};
  char* act_arr[] = {"zzz", "ok", "abc"};
  size_t n = sizeof(act_arr) / sizeof(char*);
  binary_insertion_sort(act_arr, sizeof(char*), n, compare_string);
  TEST_ASSERT_EQUAL_INT_ARRAY(exp_arr, act_arr, 3);
}

int main(void){
  UNITY_BEGIN();
  RUN_TEST(test_quicksort_one_el_int);
  RUN_TEST(test_quicksort_two_el_int);
  RUN_TEST(test_quicksort_three_el_int);
  RUN_TEST(test_quicksort_three_el_ordered_backwards_int);
  RUN_TEST(test_quicksort_three_el_already_sorted_int);
  RUN_TEST(test_quicksort_one_el_string);
  RUN_TEST(test_quicksort_two_el_string);
  RUN_TEST(test_quicksort_three_el_string);
  RUN_TEST(test_insertsort_one_el_int);
  RUN_TEST(test_insertsort_two_el_int);
  RUN_TEST(test_insertsort_three_el_int);
  RUN_TEST(test_insertsort_three_el_already_sorted_int);
  RUN_TEST(test_insertsort_three_el_ordered_backwards_int);
  RUN_TEST(test_insertsort_three_el_string);
  return UNITY_END();
}

