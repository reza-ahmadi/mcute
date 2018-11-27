#include <crest.h>
#include <stdio.h>




void main(){

int tank1, tank2, solution, drain;

int Warnings;

CREST_int(tank1);
CREST_int(tank2);
CREST_int(solution);
CREST_int(drain);

	if (tank1==0 && tank2 == 0){
		printf ("----branch visited: tank1==0 && tank2 == 0 ----");
		if (Warnings>1 && Warnings<5){
			printf ("----branch visited: Warnings>1 && Warnings<5 ----");
		}else{
			printf ("----branch visited: !(Warnings>1 && Warnings<5) ----");
		}
		if (solution>0){
			printf ("----branch visited: solution>0 ----");
		}else{
			printf ("----branch visited: !(solution>0) ----");
		}
	}
	else{
		printf ("----branch visited: !(tank1==0 && tank2 == 0) ----");
		if (drain>0){
			printf ("----branch visited: drain>0 ----");
		}
	}
}
