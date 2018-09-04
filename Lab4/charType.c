#include <stdio.h>
 #include <stdlib.h>
 #include <string.h>
 #include <ctype.h>
 #include <assert.h>
 #define MAX 100
 
 // function prototype
 void extract_chars(char* s, char* a, char* d, char* p, char* w);
 
 // function main which takes command line arguments
 int main(int argc, char* argv[]){
 FILE* in;        // handle for input file
 FILE* out;       // handle for output file
 char* line;      // string holding input line
 char* character; // string holding all alpha-numeric chars
 char* number;    // holds all the numbers
 char* punctuation; //holds all punctuation
 char* space;        //holds all space
 // check command line for correct number of arguments
 if( argc != 3 ){
 printf("Usage: %s input-file output-file\n", argv[0]);
 exit(EXIT_FAILURE);
 }
 
 // open input file for reading
 if( (in=fopen(argv[1], "r"))==NULL ){
 printf("Unable to read from file %s\n", argv[1]);
 exit(EXIT_FAILURE);
 }
 rewind(in);
 // open output file for writing
 if( (out=fopen(argv[2], "w"))==NULL ){
 printf("Unable to write to file %s\n", argv[2]);
 exit(EXIT_FAILURE);
 }
 
 
 // allocate strings line, character, punctuation, space, number on the heap
 line = calloc(MAX+1, sizeof(char) );
 character = calloc(MAX+1, sizeof(char) );
 punctuation = calloc(MAX+1, sizeof(char));
 space = calloc(MAX+1, sizeof(char));
 number = calloc(MAX+1, sizeof(char));
 assert( line!=NULL && character!=NULL && number!=NULL && punctuation!=NULL && space!=NULL);
 
 // read each line in input file, extract alpha-numeric characters
 //int counter  = 0;
 int lines = 0;
 while( fgets(line, MAX, in) != NULL ){
 
 lines++;
 extract_chars(line, character, number, punctuation, space);
 fprintf(out, "line %d contains: \n", lines);
 if(strlen(character)>1){
 fprintf(out, "%d alphabetic characters: %s\n", (int)strlen(character), character);
 
 }else{
 fprintf(out, "%d alphabetic character: %s\n", (int)strlen(character), character);
 }
 if(strlen(number)>1){
 fprintf(out, "%d numeric characters: %s\n", (int)strlen(number), number);
 
 }else{
 fprintf(out, "%d numeric character: %s\n", (int)strlen(number), number);
 
 }
 if(strlen(punctuation)>1){
 fprintf(out, "%d punctuation characters: %s\n", (int)strlen(punctuation), punctuation);
 
 }else{
 fprintf(out, "%d punctuation character: %s\n", (int)strlen(punctuation), punctuation);
 
 }
 if(strlen(space)>1){
 fprintf(out, "%d whitespace characters: %s\n", (int)strlen(space), space);
 
 }else{
 fprintf(out, "%d whitespace character: %s\n", (int)strlen(space), space);
 
 
 }
 
 }
 
 // free heap memory
 free(line);
 free(character);
 free(punctuation);
 free(space);
 free(number);
 
 
 // close input and output files
 fclose(in);
 fclose(out);
 
 return EXIT_SUCCESS;
 }
 
 // function definition
 void extract_chars(char* s, char* a, char* d, char* p, char* w){
 int i=0;
 int j=0;
 int o=0;
 int z=0;
 int q=0;
 while(s[i]!='\0' && i<MAX){
 
 if (isupper((int) s[i])){
     a[j] = s[i];
     i++;
     j++;
 }else if(isalpha((int) s[i]) ){
     a[j] = s[i];
     j++;
     i++;
 }else if(isdigit((int) s[i])){
     d[o] = s[i];
     o++;
     i++;
 }else if(ispunct((int) s[i])){
     p[z] = s[i];
     z++;
     i++;
 }else if(isspace((int) s[i])){
     w[q] = s[i];
     q++;
     i++;
 }
 
 
 }
 a[j] = '\0';
 d[o] = '\0';
 p[z] = '\0';
 w[q] = '\0';
 }
 