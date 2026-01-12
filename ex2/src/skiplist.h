#ifndef SKIPLIST_H_laokjsdnbudjllvfidkfmqm
#define SKIPLIST_H_laokjsdnbudjllvfidkfmqm

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Is a constant that defines the maximum number of pointers that can be in a single node of the skip_list.
#define MAX_HEIGHT 20

typedef struct _SkipList SkipList;
typedef struct _Node Node;

// It's a fuction that create a new empty skiplist.
// The parameter compare is a comparator implementing the precedence relation between the skiplist elements.
SkipList * create_skiplist(int (*compare)(void*,void*));

// It's a function that insert a new node into the skiplist.
// The first parameter is the skiplist in which to insert the new item.
// The second parameter is the item to insert in the skiplist.
void insert_skiplist(SkipList  * s, void * item);

// It's a function that checks if an item is in the skiplist.
// The first parameter is the skiplist in which to search if the element is present.
// The second parameter is the item to search in the skiplist.
// It returns 1 iff the item is in the skiplist, 0 otherwise. 
int search_skiplist(SkipList * s, void * item);

// It's a function that completely clears the skiplist.
// The parameter s is the skiplist to delete.
void skiplist_delete(SkipList * s);

#endif /* SKIPLIST_H_laokjsdnbudjllvfidkfmqm */
