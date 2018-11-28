#include <stdio.h>
#include <crest.h>

int main(int argc, char const *argv[]) {
  /* code */

  int desiredLevel;
  int temprature;
  int HighTemps;

  CREST_int(desiredLevel);
  CREST_int(temprature);
  //CREST_int(HighTemps);

  if (temprature>=60 && desiredLevel>=5){
    printf("----branch visited: temprature>=60 && desiredLevel>=5 ----\n");
    HighTemps = temprature-60;
  }
  else{
    printf("----branch visited: !(temprature>=60 && desiredLevel>=5)----\n");
  }

  if (HighTemps>=10){
    printf("----branch visited: HighTemps>=10!!!, %d degrees more than standart!!!----\n", HighTemps);
  }else{
    printf("----branch visited: !(HighTemps>=10)----\n");
  }

  return 0;
}
