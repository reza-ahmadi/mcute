#include <crest.h>
#include <stdio.h>
#include "test_func.h"

// int getMax(int a, int b);

int main(){

  int x,y,z;
  //
  CREST_int(x);
  CREST_int(y);

  /* code */
  int max = getMax(x,y);

  if (x == max){
    z = x;
    printf("x > y\n");
    if (x-y > 100){
      printf("x-y > 100\n");
    }
  }
  else{
    z = y;
    printf("x <= y\n");
  }

  printf("x=%d, y=%d, z=%d\n", x,y,z);

  return 0;
}

// int getMax(int a, int b){
//   if (a>b) return a;
//   return b;
// }
