//Bora Mutluoglu | bmutluog | 1564633 | cs12M | February 19, 2018 |
//A program to test the different functionalities of Dictionary.c
//I test certain cases such as inserting keys at the beginning and end
//of the Dictionary List, deleting keys in the middle, beginning and end of
//the list. I used printDictionary to check if the proper keys were
//being deleted or inserted and then I used the printf function to check
//if functions like size() or isEmpty() returned the correct results.
#include<stdlib.h>
#include<stdio.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){
    Dictionary A = newDictionary();
    //char* k;
    //char* v;
    
    insert(A, "one", "A");
    insert(A, "two", "B");
    //delete(A, "twenty"); //cannot delete non-existent key error
    insert(A, "three", "C");
    insert(A, "four", "D");
    insert(A, "five", "E");
    delete(A, "one");
    delete(A, "four");
    delete(A, "two");
    // insert(A, "five", "glow"); // error: key collision
    
    
    printDictionary(stdout, A);
    
    
    // delete(A, "one");  // error: key not found
    
    printf("%s\n", (isEmpty(A)?"true":"false"));
    insert(A,"six", "F");
    printf("%s\n", (lookup(A, "five")?"true":"false"));
    printf("%d\n", size(A));
    makeEmpty(A);
    printf("%s\n", (lookup(A, "five")?"true":"false"));
    printf("%s\n", (isEmpty(A)?"true":"false"));
    
    freeDictionary(&A);
    
    return(EXIT_SUCCESS);
}