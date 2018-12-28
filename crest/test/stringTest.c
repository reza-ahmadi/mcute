#include <stdio.h>
#include <crest.h>


int main(void) {

  char input[4];

  CREST_char(input[0]);
  CREST_char(input[1]);
  CREST_char(input[2]);
  CREST_char(input[3]);


  int cnt=0;
  if (input[0] == 'b') cnt++;
  if (input[1] == 'a') cnt++;
  if (input[2] == 'd') cnt++;
  if (input[3] == '!') cnt++;

  printf("\nhere is the string: %s\n", input);

  if (cnt >= 4)
    printf("WOW!!!!!!!!!!!!! SO BAD\n" );; // error }

}
