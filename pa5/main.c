//Bora Mutluoglu | bmutluog | 1564633 | cs12b | March 17, 2018 |
//Dictionary.c implementation using hash tables instead of previously
//used ADT/Linked Lists/BST implementations.

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

// private types, hash functions given by professor-------------------------------
const int tableSize = 101;

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
    int sizeInBits = 8*sizeof(unsigned int);
    shift = shift & (sizeInBits - 1);
    if ( shift == 0 )
        return value;
    return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {
    unsigned int result = 0xBAE86554;
    while (*input) {
        result ^= *input++;
        result = rotate_left(result, 5);
    }
    return result;
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
    return pre_hash(key)%tableSize;
}

//private types------------------------------------------------------------------

// NodeObj
typedef struct NodeObj{
    char* key;
    char* value;
    struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor of the Node type
Node newNode(char* k, char* v) {
    Node N = malloc(sizeof(NodeObj));
    assert(N!= NULL);
    //allocate memory for the respective keys and values
    N->key = calloc(strlen(k)+1, sizeof(char));
    N->value = calloc(strlen(v)+1, sizeof(char));
    strcpy(N->key, k);
    strcpy(N->value, v);
    N->next = NULL;
    return(N);
}

// freeNode()
// destructor for the Node type
void freeNode(Node* pN){
    if(pN != NULL && *pN != NULL){
        Node N = *pN;
        free(N->key);
        free(N->value);
        free(*pN);
        *pN = NULL;
    }
}

// DictionaryObj
typedef struct DictionaryObj{
    Node* table;
    int numItems;
}DictionaryObj;

//find, used to find a key referene
Node find(Dictionary D, char* k){
    Node N = D->table[hash(k)];
    while(N != NULL && strcmp(N->key, k) != 0) {
	       N = N->next;
    }
    return N;
}

//recursively deletes and frees the nodes
void deleteAll(Node N){
    if( N != NULL ){
        deleteAll(N->next);
        freeNode(&N);
    }
}

// public types ---------------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
    Dictionary D = malloc(sizeof(DictionaryObj));
    assert(D != NULL);
    D->table = calloc(tableSize, sizeof(Node*));
    D->numItems = 0;
    return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
    Dictionary D = *pD;
    if( pD != NULL && D != NULL ){
        if(!isEmpty(D)){
            makeEmpty(D);
        }
        for(int i = 0; i < tableSize; i++){
            Node N = D->table[i];
            while(N != NULL){
                free(N);
                N = N->next;
            }
            free(D->table[i]);
            D->table[i] = NULL;
        }
        free(*pD);
        *pD = NULL;
    }
}

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
    if( D == NULL){
        fprintf(stderr, "Dictionary Error: calling isEmpty() on a NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    if (D->numItems == 0){
        return 1;
    }else{
        return 0;
    }
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D){
    if( D == NULL){
        fprintf(stderr, "Dictionary Error: calling size() on a NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    return D->numItems;
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
    if( D == NULL){
        fprintf(stderr, "Dictionary Error: calling lookup() on a NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    Node N = find(D, k);
    if(N != NULL){
        return N->value;
    }else{
        return NULL;
    }
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
    if( D==NULL ){
        fprintf(stderr, "Dictionary Error: calling insert() on a NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    if( D->numItems > 0 && lookup(D, k) != NULL){
        fprintf(stderr, "Dictionary Error: cannot insert duplicate keys.");
        exit(EXIT_FAILURE);
    }
    else if(D->table[hash(k)] == NULL){
        Node N = newNode(k, v);
        D->table[hash(k)] = N;
        D->numItems++;
    }
    else{
        Node N = D->table[hash(k)];
        while(N->next != NULL){
            N = N->next;
        }
        N->next = newNode(k,v);
        D->numItems++;
    }
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
    if( D == NULL ){
        fprintf(stderr, "Dictionary Error: calling delete() on a NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    if( D->numItems == 0 ){
        fprintf(stderr, "Dictionary Error: calling delete() on an empty Dictionary\n");
        exit(EXIT_FAILURE);
    }
    Node N = find(D, k);
    if(D->table[hash(k)] != NULL && D->table[hash(k)]->next == NULL){
        freeNode(&N);
        D->table[hash(k)] = NULL;
        D->numItems--;
    }
    else if(lookup(D,k) != NULL && N == D->table[hash(k)]){
        D->table[hash(k)] = N->next;
        freeNode(&N);
        D->numItems--;
    }
    else{
        Node N = D->table[hash(k)];
        while(N->next != NULL && strcmp(N->next->key, k) != 0) {
            N = N->next;
        }
        Node P = N;
        P->next = N->next;
        freeNode(&N);
        D->numItems--;
    }
}
// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
    if( D == NULL ){
        fprintf(stderr, "Dictionary Error: calling makeEmpty() on a NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    
    for(int i = 0; i < tableSize; i++){
        deleteAll(D->table[i]);
        D->table[i] = NULL;
    }
    D->numItems = 0;
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
    if( D == NULL ){
        fprintf(stderr, "Dictionary Error: calling printDictionary() on a NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    Node N;
    for(int i = 0; i < tableSize; i++){
        N = D->table[i];
        while(N != NULL){
            fprintf(out, "%s %s \n" , N->key, N->value);
            N = N->next;
        }
    }
}
