# COMPILAZIONE

- posizionarsi in ../ex1
- creare le cartelle ex1/bin e ex1/build se non presenti
- make (nella directory corrente ci deve essere il Makefile opportuno)

---

# CANCELLAZIONE

- posizionarsi in ../ex1
- make clean (nella directory corrente ci deve essere il Makefile opportuno)

---

# ESECUZIONE

## PER ESEGUIRE array_sorting_main

- compilare
- posizionarsi in ../ex1/bin
- ./array_sorting_main `file_input_record` `file_output_record` `numero_record_da_ordinare` `sorting_method` `campo_su_cui_riordinare_i_record`

`file_input_record`: percorso file con i record da riordinare

`file_output_record`: percorso file su cui scrivere i record riordinati

`numero_record_da_ordinare`: numero di record che voglio leggere dal file

`sorting_method`: b indica binary insertsort, q indica quicksort

`campo_su_cui_riordinare_i_record`: 1 indica field1 (string), 2 indica field2 (int), 3 indica field3 (double)

## PER ESEGUIRE array_sorting_tests

- compilare
- posizionarsi in ../ex1/bin
- ./array_sorting_tests
