#include <iostream>
#include <string>
#include <fstream>


using namespace std;

int main(){

ifstream in("branches");

int fin,branches, b1,b2;

in >> fin >> branches;

cout << "fin, brahcnes" << fin << branches << endl;

for (int i=0;i<branches;i++){
    in >> b1 >> b2;
cout << "b1, b2" << b1 << b2 << endl;


}

in.close();

}
