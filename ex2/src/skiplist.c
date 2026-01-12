#include "skiplist.h"
#include <time.h>

// The randomLevel function determines the number of pointers to include in the new node.
static unsigned int random_level();
// The create_node function create a new node with the item inside.
static Node * create_node(unsigned int size, void *item);
// The node_delete function delete a node and free it's memory.
static Node* node_delete(Node * n);

struct _SkipList {
  Node *head;
  unsigned int max_level; // current max level of the skiplist
  int (*compare)(void*, void*); // function to compare two elements of the skiplist
};

struct _Node {
  Node **next; // pointers to other nodes in the skiplist
  unsigned int size; // number of pointers in next
  void *item; // item stored in the node
};

static Node *create_node(unsigned int size, void *item){
  Node *new_node = (Node *)malloc(sizeof(Node)); 
  new_node->next = (Node **) malloc(sizeof(Node *)*size);
  new_node->size = size;
  new_node->item = item;
  return new_node;
}

SkipList * create_skiplist(int (*compare)(void*, void*)){
  if(compare == NULL){
    fprintf(stderr,"create_skiplist: compare parameter cannot be NULL\n");
  }
  srand((unsigned int)time(NULL));
  // allocating space for the new skiplist
  SkipList *new_skiplist = (SkipList *)malloc(sizeof(SkipList));
  // creating the head node
  Node *new_node = (Node *)malloc(sizeof(Node)); 
  new_node->next = (Node**)malloc(sizeof(Node) * MAX_HEIGHT);
  // setting all the pointers to NULL
  for(int i = 0; i < MAX_HEIGHT; i++)
  	new_node->next[i]=NULL;
  new_node->size = MAX_HEIGHT;
  new_node->item = NULL;
  // creating the skiplist
  new_skiplist->head = new_node;
  new_skiplist-> max_level = 0;
  new_skiplist->compare = compare;
  return new_skiplist;
}

static unsigned int random_level(){
  unsigned int lvl = 1;
  while (((double)random() / (double)RAND_MAX) > 0.5 && lvl < MAX_HEIGHT){
    lvl = lvl + 1;
  }
  return lvl;
}


void insert_skiplist(SkipList *s, void * item){
  if(s == NULL){
	  fprintf(stderr,"insert_skiplist: skiplist cannot be NULL\n");
  }
  if(item == NULL){
	  fprintf(stderr,"insert_skiplist: item cannot be NULL\n");
  }
  // creating the new node to insert in the skiplist
  Node *new_node = create_node(random_level(), item);
  // update max_level
  if (new_node->size > s->max_level){
    s->max_level = new_node->size;
  }
  Node *x = s->head;
  int k;
  // descending the levels of the skiplist
  for(k = (int)(s->max_level) - 1; k >= 0; k--){
    if((x->next)[k] == NULL || ((*(s->compare))(item, (x->next)[k]->item) < 0)){
      if (k < (int)new_node->size){
        (new_node->next)[k] = (x->next)[k];
        (x->next)[k] = new_node;	
      }
    }
	else{
      x = x->next[k];
      k = k + 1;
    }
  }
}

int search_skiplist(SkipList *s, void * item){
  if(s == NULL){
	  fprintf(stderr,"search_skiplist: skiplist cannot be NULL\n");
  }
  if(item == NULL){
	  fprintf(stderr,"search_skiplist: item cannot be NULL\n");
  }
  Node *x = s->head;
  int i;
  // searching for the item
  for(i = (int)s->max_level - 1; i >= 0; i--){
    while(x->next[i]!=NULL && (*(s->compare))(x->next[i]->item, item) < 0){
      x = x->next[i];	
	  }  
  }
  x = x->next[0];
  // checking if the item exists
  if (x != NULL && (*(s->compare))(x->item, item) == 0)
    return 1;
  else
    return 0; 
}

static Node* node_delete(Node * n){
  Node * next_node =n->next[0];
  free(n->next);
  free(n);
  return next_node;
}

void skiplist_delete(SkipList *s){
  if(s == NULL){
	  fprintf(stderr,"skiplist_delete: skiplist cannot be NULL\n");
  }
  else{
    Node* temp = s->head;
	// delete every node in the skiplist
    while(temp != NULL){
	  temp = node_delete(temp);
	}
    free(s);	
  }
}