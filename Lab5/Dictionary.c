//Bora Mutluoglu | bmutluog | 1564633 | cs12M | February 19, 2018 |
//A Dictionary ADT coded in C. Implements some functions specified
//by professor Tantalo. I used my pa3 Dictionary as a guide for this
//assignment as it was very similar just in a different language with
//a few differences in functionality.
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"


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
    N->key = k;
    N->value = v;
    N->next = NULL;
    return(N);
}

// freeNode()
// destructor for the Node type
void freeNode(Node* pN){
    if( pN!=NULL && *pN!=NULL ){
        free(*pN);
        *pN = NULL;
    }
}

// DictionaryObj
typedef struct DictionaryObj{
    Node top;
    Node bot;
    int numItems;
} DictionaryObj;


// public functions -----------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
    Dictionary D = malloc(sizeof(DictionaryObj));
    assert(D!=NULL);
    D->top = NULL;
    D->bot = NULL;
    D->numItems = 0;
    return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
    if( pD!=NULL && *pD!=NULL ){
        if( !isEmpty(*pD) ) makeEmpty(*pD);
        free(*pD);
        *pD = NULL;
    }
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
    if(D->numItems==0){
        return 1;
    }
    return 0;
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D){
    return D->numItems;
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
//initialize N to the beginning of the Dictionary list
    Node N = D->top;
    int counter = 0;
//if the Dictionary is NULL throw an error
    if( D==NULL ){
        fprintf(stderr, "Dictionary Error: calling lookup() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    while(N != NULL && counter < 1){
        
        if(strcmp(N->key,k)==0){
            counter++;
            return N->value;
        }
//iterate through the Node positions too look for the key
            N = N->next;

        }
    return NULL;
}


// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
    int counter = 0;
    int nothing = 0;
    if (lookup(D, k) != NULL){
        
        fprintf(stderr, "Dictionary Error: cannot insert duplicate keys\n");
        exit(EXIT_FAILURE);
    } else {
//intialize a new Node object if current node is empty
            Node N = newNode(k, v);
            nothing = 0;
        if (!isEmpty(D)){
//If the Node isn't empty iterate through to the next node until it is null
//then insert the key and value into that node.
            D->bot->next = N;
            D->bot = D->bot->next;
        }else{
            D->top = N;
            D->bot = N;
            counter++;
//else if empty sets the current Node N's direction to the N node
//and increment the counter

            }
//increments the number of items in the dictionary
        D->numItems++;
        nothing++;
    }
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
    int counter = 0;
//initialize node N to the first position of the dictionary
    Node N = D->top;
//if the key is not found in the Dictionary then exit
    if (lookup(D,k)==NULL){
        fprintf(stderr, "Dictionary error: cannot delete non-existent key\n");
        exit(EXIT_FAILURE);
    }
//if the current key in the dictionary does not match the key we are looking
//for, move on.
    if (D->numItems==1 && counter==0){
        counter++;
        
    }
//if the keys match then change the pointer of the Node so it completely
//skips over and essentially deletes the target Key.
    if (strcmp(N->key,k)==0){
        Node A = D->top;
        Node B = A->next;
        D->top = B;
        freeNode(&A);
    }else{
//while the current and Next positions are not Null iterate through
//the list
        while(N!=NULL && N->next!=NULL){
            if(strcmp(N->next->key, k)==0){
                Node A = N;
                Node B = N->next;
                A->next = B->next;
                N=A;
                freeNode(&B);
            }
            N = N->next;
        }
    }
//decrease the number of items in the list since one got deleted
    D->numItems--;
}


// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
//setting top/bot to NULL and items to zero empties the Dictionary
    D->top = NULL;
    D->bot = NULL;
    D->numItems = 0;
}

void printDictionary(FILE* out, Dictionary D){
    Node N;
    if(D==NULL){
        fprintf(stderr, "Dictionary Error: printDictionary() called on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    for(N=D->top; N!=NULL; N=N->next){
        fprintf(out, "%s %s \n" , N->key, N->value);
    }
}

