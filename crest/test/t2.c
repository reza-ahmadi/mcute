//generated by mcute
//for concolic testing state machines

#include <crest.h>
#include <stdio.h>

int main(void) {
int desiredLevel;
int temprature;
int HighTemps, DesiredLevel, Temprature;

CREST_int(desiredLevel);
CREST_int(temprature);

	if (HighTemps<=9){
		if (desiredLevel>0){
			DesiredLevel=desiredLevel;
		}
		if (temprature>0){
			Temprature=temprature;
			if (Temprature>=90){
				HighTemps++;
			}
		}
	}else{ //is only enabled if the transition is executed many times

	}
}

