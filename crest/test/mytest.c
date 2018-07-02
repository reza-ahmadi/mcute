#include <crest.h>
#include <stdio.h>

int A[100];
char B[12][97];

int main(void) {
int x;
int y;
int z;

CREST_int(x);
CREST_int(y);
CREST_int(z);

if (x>0 && x<10)
{
 printf ("**** in: x>0 && x<10 ****");
 if (y>x){
	printf ("*** y is greater than x. x: %d, y:%d", x, y);
 }else{
	printf ("*** x is greater than or equal to y. x: %d, y:%d", x, y);
 }
}else{
 printf ("**** in: ! (x>0 && x<10) ****");
}

if (x>=10 && z<=100){
 printf ("$$$$$$$$$$ in: x>0 && z<=10 ****");
 if (z<5){
   printf ("$$$$$$$$$$ z<5");
 }else
 {
 printf ("$$$$$$$$$$ in: z>=5");
 } 	
}

}


