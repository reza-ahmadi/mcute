#include <stdio.h>
#include <ctime>
enum STATES{
  START,END, FINAL
};

void getEnum(STATES s){
	if (s==START){
		printf ("\nSTART");
	}
	else if (s==END)
		printf ("\nEnd");
	else
		printf ("\nFINAL");
}
int main(){
	std::srand(time(0));
	for (int i=0;i<10;i++){
		int rnd = rand()%10;
		printf ("rand:%d", rnd);
	}
//	getEnum(END);
	return 0;
}
