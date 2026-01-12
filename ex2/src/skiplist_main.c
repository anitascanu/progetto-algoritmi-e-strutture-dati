#include "skiplist.h"
#include <time.h>

// String compare function
int cmp_string(void* s1, void* s2){
	return strcasecmp((const char*)s1, (const char*)s2);
}

// It returns the number of lines in a file.
static int n_line(const char *file_name){
  FILE* fp = fopen(file_name, "r");
  if (fp == NULL) {
    printf("Could not open file.");
    exit(EXIT_FAILURE);
  }
  int ch, nline = 0;
  for (ch = getc(fp); ch != EOF; ch = getc(fp)){
    if (ch == '\n') nline++;
  }
  fclose(fp);
  return nline;
}

// It returns the number of words in a file.
static int number_words(const char *file_name){
  FILE* fp = fopen(file_name, "r");
  if (fp == NULL) {
    printf("Could not open file.");
    exit(EXIT_FAILURE);
  }
  int n = 0;
  char buf[32];
  while( fscanf(fp, "%s ", buf) != EOF ){
    n++;
  }
  fclose(fp);
  return n;   
}

static void read_words_from_dictionary(const char *file_name, SkipList *s, int nline){
  int i = 0;
  char line[20];
  char *word;
  clock_t begin_ins, end_ins;
  double time_spent_ins = 0;
  FILE *fp = fopen(file_name, "r"); // open the file
  if(fp == NULL) {
	  fprintf(stderr,"read_words_from_dictionary: unable to open the file\n");
	  exit(EXIT_FAILURE);
  }
  printf("Reading words from dictionary and inserting them into the skiplist...\n");
  // read every word in the file
  while ((fgets(line, sizeof(line), fp) != NULL) && (i < nline)){ // get the line
    word = (char*)malloc(sizeof(line));
    // store the word in the variable word
    sscanf(line, "%s", word);
    begin_ins = clock();
    // insert the word read in the skiplist
    insert_skiplist(s, (void*)word);
    end_ins = clock();
    time_spent_ins += (double)(end_ins - begin_ins) / CLOCKS_PER_SEC;
    i++;
  }
  printf("Insertion time: %f\n", time_spent_ins);
  fclose(fp); // close the file
  printf("\n");
}

static void correct_file(const char *file_name, char **words, SkipList * s){
  FILE *fp = fopen(file_name,"r"); // open the file 
  if(fp == NULL) {
	  fprintf(stderr,"correct_file: unable to open the file\n");
	  exit(EXIT_FAILURE);
  }
  char buf[32];
  int i = 0;
  clock_t begin, end;
  double time_spent = 0;
  printf("Reading words from file and correcting them...\n");
  printf("List of uncorrect words:\n");
  // read every string in the file one by one
  while(fscanf(fp, "%s ", buf) != EOF ){
    char *r, *w;
    // delete special character in the string
    for (w = r = buf; *r; r++) {
      if (*r != ',' && *r != ';' && *r != '.' && *r != ':' && *r != ' ') {
        *w++ = *r;
      }
    }
    *w = '\0'; // end of the string
    // add the word to the array of words
    words[i] = (char *)malloc(sizeof(buf));
    words[i] = buf;
    begin = clock();
    // check if the word scanned is in the skiplist (dictionary)
    if(!search_skiplist(s, words[i]))
      printf("\t%s\n", words[i]);
    i++;
    end = clock();
    time_spent += (double)(end - begin) / CLOCKS_PER_SEC;
  }
  printf("Correction time: %f\n", time_spent);
  fclose(fp); // close the file
  printf("\n");
}


static void test_skiplist(const char *dictionary_file_name, const char *correct_me_file_name){
  // create the new skiplist
  SkipList *dictionary = create_skiplist(cmp_string);
  int n_words = n_line(dictionary_file_name);
  int num_words = number_words(correct_me_file_name);
  char **words = (char **)malloc(sizeof(char *) * (size_t)num_words);
  // populate the skiplist
  read_words_from_dictionary(dictionary_file_name, dictionary, n_words);
  // correct the file, if a word in the correct_me_file is not in the skiplist the word is uncorrect
  correct_file(correct_me_file_name, words, dictionary);
}

// It should be invoked with 2 parameters:
// 1) the first one is the file path of the dictionary (the file that contains the words)
// 2) the second one is the file path of the file to correct
int main(int argc, char const *argv[]){
  if(argc < 3) {
    exit(EXIT_FAILURE);
  }
  test_skiplist(argv[1], argv[2]);
  exit(EXIT_SUCCESS);
}
