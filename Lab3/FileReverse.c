//Bora Mutluoglu | bmutluog | 1564633 | cs12M | January 2, 2018 |
//Reads in seperate words found in a file and reverses them, prints them to an out file
//line by line. coded in C.
#include<stdio.h>
#include<stdlib.h>
#include<string.h>


void stringReverse(char* string) {
    int character = 0;
    //int j = sizeof(string)-1;
    int j = strlen(string)-1;
    for ( int i=0; i<j; i++, j--) {
        //swaps the char at position i with the char at position j
        character = string[i];
        string[i] = string[j];
        string[j] = character;
    }
}

int main(int argc, char* argv[]){
    FILE* in; /* file handle for input */
    FILE* out; /* file handle for output */
    char word[256]; /* char array to store words from input file */
    
    /* check command line for correct number of arguments */
    if( argc != 3 ){
        printf("Usage: %s <input file> <output file>\n", argv[0]);
        exit(EXIT_FAILURE);
    }
    
    /* open input file for reading */
    in = fopen(argv[1], "r");
    if( in==NULL ){
        printf("Unable to read from file %s\n", argv[1]);
        exit(EXIT_FAILURE);
    }
    
    /* open output file for writing */
    out = fopen(argv[2], "w");
    if( out==NULL ){
        printf("Unable to write to file %s\n", argv[2]);
        exit(EXIT_FAILURE);
    }
    
    /* read words from input file, print on separate lines to output file*/
    while( fscanf(in, " %s", word) != EOF ){
        //calls the string reverse function on the current read in word to reverse it.
        stringReverse(word);
        fprintf(out, "%s\n", word);
    }
    /* close input and output files */
    fclose(in);
    fclose(out);
    
    return(EXIT_SUCCESS);
}

