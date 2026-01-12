#define _GNU_SOURCE
#include "unity.h"
#include "skiplist.h"

/*
 * Test suite for skiplist data structure and algorithms
 */

// integer precedence relation used in tests
int cmp_int(void* a, void* b){
  return *((int*)a) - *((int*)b);
}

// data elements that are initialized before each test
static SkipList *s;
static int i1, i2, i3;

void setUp(void){
  s = create_skiplist(cmp_int);
  i1 = -2;
  i2 = 5;
  i3 = 9;
}

void tearDown(void){
  skiplist_delete(s);
}

static void search_void_skiplist(void){
  TEST_ASSERT(0 == search_skiplist(s,&i2));
}

static void search_one_el_true(void){
  insert_skiplist(s,&i1);
  int r = search_skiplist(s, &i1);
  TEST_ASSERT(1 == r);
}

static void search_one_el_false(void){
  insert_skiplist(s,&i1);
  int r = search_skiplist(s, &i2);
  TEST_ASSERT(0 == r);
}

static void search_two_el_true(void){
  insert_skiplist(s,&i1);
  insert_skiplist(s,&i2);
  int r = search_skiplist(s, &i1);
  TEST_ASSERT(1 == r);
}

static void search_two_el_false(void){
  insert_skiplist(s,&i1);
  insert_skiplist(s,&i2);
  int r1 = search_skiplist(s, &i3);
  int r2 = search_skiplist(s, &i1);
  TEST_ASSERT(0 == r1 && 1 == r2);
}

static void search_three_el_true(void){
  insert_skiplist(s,&i1);
  insert_skiplist(s,&i2);
  insert_skiplist(s,&i3);
  int r1 = search_skiplist(s, &i1);
  int r2 = search_skiplist(s, &i2);
  int r3 = search_skiplist(s, &i3);
  TEST_ASSERT(1 == r1 && 1 == r2 && 1 == r3);
}

int main(void){
  UNITY_BEGIN();
  RUN_TEST(search_void_skiplist);
  RUN_TEST(search_one_el_true);
  RUN_TEST(search_one_el_false);
  RUN_TEST(search_two_el_true);
  RUN_TEST(search_two_el_false);
  RUN_TEST(search_three_el_true);
  return UNITY_END();
}

