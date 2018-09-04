#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "Dictionary.h"

const int tableSize = 101;

// private types --------------------------------------------------------------

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
    assert(N!=NULL);
    N->key = calloc(strlen(k)+1, sizeof(char));
    N->value = calloc(strlen(k)+1, sizeof(char));
    N->key = k;
    N->value = v;
    N->next = NULL;
    return(N);
}

// freeNode()
// destructor for the Node type
void freeNode(Node* pN){
    if( pN!=NULL && *pN!=NULL ){
        free((*pN)->key);
        free((*pN)->value);
        free(*pN);
        *pN = NULL;
    }
}

// DictionaryObj
typedef struct DictionaryObj{
    Node root;
    Node table;
    int numPairs;
} DictionaryObj;

typedef DictionaryObj* Dictionary;

// public functions -----------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
    Dictionary D = malloc(sizeof(DictionaryObj));
    assert(D!=NULL);
    D->table = calloc(tableSize, sizeof(DictionaryObj));
    D->root = NULL;
    D->table = NULL
    D->numPairs = 0;
    return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
    if( pD!=NULL && *pD!=NULL ){
        if( !isEmpty(*pD) ) makeEmpty(*pD);
        //free(*pD->table);
        free(*pD);
        *pD = NULL;
    }
}

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
    int sizeInBits = 8 * sizeof(unsigned int);
    shift = shift & (sizeInBits - 1);  // remainder of shift on division by sizeInBits
    if ( shift == 0 )
    return value;
    else
    return (value << shift) | (value >> (sizeInBits - shift)); // perform rotation
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {  // input points to first char in string
    unsigned int result = 0xBAE86554;  // = 10111010111010000110010101010100
    while (*input) {                   // while *input is not '\0' (not end of string)
        result ^= *input++;                // result = result ^ *input (current char alters result)
        // input++  (go to next char)
        result = rotate_left(result, 5);   // rotate result by fixed amount
    }
    return result;  // result is now a random looking bit pattern depending on input string
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
    return pre_hash(key)%tableSize;
}
// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
    //if the Dictionary is NULL throw the error
    if( D==NULL ){
        fprintf(stderr,
                "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    //if the number of items in the Dictionary is zero return true else return
    //false
    if(D->numPairs==0){
        return 1;
    }
    return 0;
}


// find()
// returns a reference to the Node at position index in this Hash Dictionary
Node find(Dictionary D, char* k){
    //while N does not equal null continue through the dictionary until the wanted key is found
    for(Node N = D->table[hash(k)]; N != NULL; N = N->next){
        //once the key has been found return it
        if (strcmp(N->key, k) == 0){
            return N;
        }
    }
    return NULL;
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D){
    if( D == NULL){
        fprintf(stderr, "Dictionary Error: calling size() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    return D->numPairs;
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
    int counter = 0;
    //if the Dictionary is NULL throw an error
    if( D==NULL ){
        fprintf(stderr, "Dictionary Error: calling lookup() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    //initialize N the key to be found so the corresponding value can be found
    Node N = find(D, k);
    if (N == NULL){
        return NULL;
    }else{
        return N->value;
    }
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
    int counter = 0;
    int nothing = 0;
    Node N;
    if(D == NULL){
        fprintf(stderr, "Dictionary Error: cannot call insert on a NULL Dictionary");
        exit(EXIT_FAILURE);
    }
    if (lookup(D, k) != NULL){
        
        fprintf(stderr, "Dictionary Error: cannot insert duplicate keys\n");
        exit(EXIT_FAILURE);
    }
    //intialize a new Node object if current node is empty
    N = newNode(k, v);
    if(D->table[hash(k)] == NULL){
        D->table[hash(k)] = newNode(k, v);
        D->numPairs++;
    } else {
        N = newNode(k, v);
        N->next = D->table[hash(k)];
        D->table[hash(k)] = N;
        D->numPairs++;
    }
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
    //if the key is not found in the Dictionary then exit
    if (lookup(D,k)==NULL){
        fprintf(stderr, "Dictionary error: cannot delete non-existent key\n");
        exit(EXIT_FAILURE);
    }
    //if the current key in the dictionary does not match the key we are looking
    //for, move on.
    D->table[hash(k)] = NULL;
    D->numPairs--;
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
    //setting top/bot to NULL and items to zero empties the Dictionary
    int counter = 0;
    while(D->table[counter] != NULL && counter < tableSize){
        Node N;
        N = D->table[counter];
        D->table[counter]=N->next;
        freeNode(&N);
        N = NULL;
        counter++;
    }
    D->numPairs = 0;
    
}

void printDictionary(FILE* out, Dictionary D){
    Node N;
    if(D==NULL){
        fprintf(stderr, "Dictionary Error: printDictionary() called on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    for(int i =0; i<tableSize; i++){
        N = D->table[i];
        while(N != NULL){
            fprintf(out, "%s %s \n" , N->key, N->value);
            N = N->next;
        }
    }
}
