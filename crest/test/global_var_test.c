#include <stdio.h>
#include <crest.h>

int main(int argc, char const *argv[]) {
  /* code */

  int gv1 = 0;
  int i1 = 0;

  CREST_int(i1);

  if (i1 > 0){
    printf("%d is greater than 0\n", i1 );
    gv1 = i1 * 2;
  }

  if (gv1 > 100 && gv1 < 200){
    printf("%d is > 100 && %d < 200 0\n", gv1, gv1 );
  }

  return 0;
}
