#include "array_sorting.h"
#include "array_sorting_main.h"

// The structure represents a record composed by:
// id, string field, integer field, double field.
struct _Record{
  int id;
  char * field1;
  int field2;
  double field3;
};

// It takes as input two pointers to struct record.
// It returns 1 iff the string field of the first record is less than 
// the field field of the second one (0 otherwise).
static int compare_field1(void* v1, void* v2){
  Record* v1_cmp = (Record*)v1;
  Record* v2_cmp = (Record*)v2;
  return strcasecmp(v1_cmp->field1, v2_cmp->field1);
}

//It takes as input two pointers to struct record.
//It returns 1 iff the integer field of the first record is less than 
//the integer field of the second one (0 otherwise).
static int compare_field2(void* v1, void* v2){
  Record* v1_cmp = (Record*)v1;
  Record* v2_cmp = (Record*)v2;
  if(v1_cmp->field2 > v2_cmp->field2)
    return(1);
  else if (v1_cmp->field2 < v2_cmp->field2)
    return(-1);
  else
    return (0);
}

//It takes as input two pointers to struct record.
//It returns 1 iff the double field of the first record is less than 
//the double field of the second one (0 otherwise).
static int compare_field3(void* v1, void* v2){
  Record* v1_cmp = (Record*)v1;
  Record* v2_cmp = (Record*)v2;
  if(v1_cmp->field3 > v2_cmp->field3)
    return(1);
  else if (v1_cmp->field3 < v2_cmp->field3)
    return(-1);
  else
    return (0);
}

// Function to print nmemb sorted records stored in the array arr in the file file_name.
static void print_sorted_record_into_file( const char *file_name, Record* arr, int nmemb){
  clock_t begin, end;
  double time_spent;
  int i = 0;
  printf("Writing records into file...\n");
  // opening the file
  FILE* fp = fopen(file_name, "w");
  if(fp == NULL) {
	fprintf(stderr,"print_sorted_record_into_file: unable to open the file\n");
	exit(EXIT_FAILURE);
  }
  begin = clock();
  // printing all the records into the file
  while(i < nmemb){
    fprintf(fp, "%d,%s,%d,%f\n", arr[i].id, arr[i].field1,arr[i].field2,arr[i].field3);
    i++;
  }
  end = clock();
  time_spent = (double)(end - begin) / CLOCKS_PER_SEC;
  printf("File writing time: %f\n", time_spent);
  // closing the file
  fclose(fp);
  printf("\n");
}

// Function to sort all records in an array of records. The parameter sort_type permits to choose
// between two sorting methods: quick sort and binary insertion sort. The parameter field permits
// to choose between three fields on which to sort the records: the string field (1), the integer
// field (2) and the double field (3).
static void sorting_records(Record *arr, size_t nmemb, const char *sort_type, const char *field){
  clock_t begin, end;
  double time_spent;
  // getting the field on which sort the records
  int field_number = atoi(field);
  printf("Sorting records...\n");
  // quick sort case
  if(strcmp(sort_type, "q") == 0){
    if(field_number == 1){
      begin = clock();
      quick_sort((void *)arr, sizeof(Record), nmemb, compare_field1);
      end = clock();
    }
    else if (field_number == 2){
      begin = clock();
      quick_sort((void *)arr, sizeof(Record), nmemb, compare_field2);
      end = clock();
    }
    else if (field_number == 3){
      begin = clock();
      quick_sort((void *)arr, sizeof(Record), nmemb, compare_field3);
      end = clock();
    }
    time_spent = (double)(end - begin) / CLOCKS_PER_SEC;
	// printing the execution time
    printf("Quicksort execution time: %f\n", time_spent);
  }
  // binary insertion sort case
  else if(strcmp(sort_type, "b") == 0){
    if(field_number == 1) {
      begin = clock();
      binary_insertion_sort((void *)arr,sizeof(Record), nmemb, compare_field1);
      end = clock();
    }
    else if (field_number == 2){
	  begin = clock();
	  binary_insertion_sort((void *)arr,sizeof(Record), nmemb, compare_field2);
      end = clock();
    }
    else if (field_number == 3){
	  begin = clock();
	  binary_insertion_sort((void *)arr,sizeof(Record), nmemb, compare_field3);
	  end = clock();
    }
    time_spent = (double)(end - begin) / CLOCKS_PER_SEC;
	  // printing the execution time
    printf("Binary insertion sort execution time: %f\n", time_spent);
  }
  printf("\n");
}

// Function to read nline records from the file file_name and store them in ad
// array of records called arr.
static void read_records_from_file(const char *file_name, Record *arr, int nline){
  int i = 0;
  char line[128];
  clock_t begin, end;
  double time_spent;
  char w[20];
  char *word;
  // open the file
  FILE *fp = fopen(file_name, "r");
  if(fp == NULL) {
    fprintf(stderr,"read_records_from_file: unable to open the file\n");
    exit(EXIT_FAILURE);
  }
  printf("Reading records from file...\n");
  begin = clock();
  // scan all the lines (records) in the file and save them in the array of records
  while ((fgets(line, sizeof(line), fp) != NULL) && (i < nline)){
    sscanf(line, " %d, %[^,], %d, %lf", &arr[i].id, w, &arr[i].field2, &arr[i].field3);
    word = (char*)malloc(sizeof(w));
    sscanf(w, "%s", word);
    arr[i].field1 = word;
    //printf("%d, %s, %d, %lf", arr[i].id, arr[i].field1, arr[i].field2, arr[i].field3);
    i++;
  }
  end = clock();
  time_spent = (double)(end - begin) / CLOCKS_PER_SEC;
  printf("File reading time: %f\n", time_spent);
  // close the file
  fclose(fp);
  printf("\n");
}

static void test_sorting(const char *reading_file_name, const char *writing_file_name, int n_record, const char *sorting_type, const char *sorting_field){
  // generates the new array of records
  Record* arr;
  arr = (Record*)calloc((size_t)n_record, sizeof(Record));
  if(arr == NULL){
	  fprintf(stderr,"test_sorting: unable to allocate memory for the array\n");
	  exit(EXIT_FAILURE);
  }
  // read the records from the file called reading_file_name
  read_records_from_file(reading_file_name, arr, n_record);
  // sort the records
  sorting_records(arr, (size_t)n_record, sorting_type, sorting_field);
  // print the sorted records in the file called writing_file_name
  print_sorted_record_into_file(writing_file_name, arr, n_record);
  for(int k = 0; k < n_record;  k++){
    free(arr[k].field1);
  }
  free(arr);
}

// It should be invoked with 5 parameters:
// 1) the first one is the file path of the records to read
// 2) the second one is the file path to write the sorted records to
// 3) the third one is the number of records to sort
// 4) the fourth one is the sort method ("q" stands for quicksort and 
//    "b" stands for binary insertion sort)
// 5) the fifth one is the field to sort records on (1 is field1 
//    (string), 2 is field2 (int), 3 is field3 (double))
int main(int argc, char const *argv[]){
  if(argc < 6) {
    printf("The function must be invoked with 5 parameters\n");
    exit(EXIT_FAILURE);
  }
  if(strcmp(argv[4], "b") != 0 && strcmp(argv[4], "q") != 0){
    printf("The fourth argument must be \"b\" or \"q\", not \"%s\"\n", argv[4]);
    exit(EXIT_FAILURE);
  }
  if(atoi(argv[5]) != 1 && atoi(argv[5]) != 2 && atoi(argv[5]) != 3){
    printf("The fifth argument must be 1 or 2 or 3, not %s\n", argv[5]);
    exit(EXIT_FAILURE);
  }
  test_sorting(argv[1], argv[2], atoi(argv[3]), argv[4], argv[5]);
  exit(EXIT_SUCCESS);
}
