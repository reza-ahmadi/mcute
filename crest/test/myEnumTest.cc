#include <cstdlib>
#include <stdio.h>
#include <ctime>
#include <vector>
#include <algorithm>
#include <math.h>

using namespace std;

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
        //srand(time(0));
       // for (int i=0;i<10000;i++){
                //int rnd = rand()%3;
               // printf ("\trand:%d", rnd);
        //}
	vector<int> ids;
	for (int i=0;i<10;i++){
		if (std::find(ids.begin(), ids.end(), i)==ids.end()){
			ids.push_back(i);
		}
	}

	 for (int i=0;i<10;i++){
                printf ("from vector: %d", ids.at(i));   
        }
	
	 printf ("\ncompute start");   
	double x=0;
	for (double i=0;i<100000000;i++){
		x += sqrt(sqrt(sqrt(i)));
	}
printf ("\ncompute end");   


//      getEnum(END);
        return 0;
}


