#include <crest.h>
#include <stdio.h>

int A[100];
char B[12][97];

int main(void) {
int x;
int y;
int z;

//scanf ("%d %d %d", &x,&y,&z);

CREST_int(x);
CREST_int(y);
CREST_int(z);

printf ("\n------------------------new execution-------------------------------");

if (x>0 && x<10)
{
  printf ("\n**** in: x>0 && x<10, x:%d, y:%d, z:%d ****", x, y, z);
  if (y>x){
        printf ("\n*** y is greater than x. x: %d, y:%d, z:%d", x, y, z);
  	if (z > 0){
           printf ("\n*** y is greater than x, and z>0, x:%d, y:%d, z:%d", x, y, z);
	}
  }else{
        printf ("\n*** x is greater than or equal to y. x: %d, y:%d, z:%d", x, y, z);
   }
}else{
   printf ("\n**** in: ! (x>0 && x<10) ****: x:%d,, y:%d, z:%d", x, y, z);
}

if (x>0 && z<=10){
 printf ("\n$$$$$$$$$$ in: x>0 && z<=10, x:%d, z:%d ****", x, z);
 if (z<-5){
   printf ("\t$$$$$$$$$$ z<5, z:%d", z);
   if (x == 5){
       printf ("\t$$$$$$$$$ x == 5");
      if (z + x == 10){
          printf ("\t+++++++++++++++++++++++++++++++++++++++++ z*x==10");
      }
   }
 }else
 {
   printf ("$$$$$$$$$$ in: z>=-5");
 }
}

if (x+y+z == 100){
   printf ("\n!!!!!!!!!!!!!!!!!! x+y+z == 100 ");
   if (x == z){
	   printf ("\t!!!!!!!!!!!!!!!!!! x == z ");
   }
}

}

