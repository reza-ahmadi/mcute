#include <iostream>
#include <string>
#include <stdio.h>
#include <stdlib.h>

using namespace std;

void f1(string s)
{
	s = "changed in f1";
	
}


void f2(string& s)
{
        s = "changed in f2";
        
}

void f3(string* s)
{
        *s = "changed in f3";	
}

void f4(string* s)
{
        s->append (" here is f4" );	
}



int main(){

        string ss = "org";
        f1(ss);
        cout << "after calling f1, s1 is:" << ss << endl;

        ss = "org";
        f2(ss);
        cout << "after calling f2, s1 is:" << ss << endl;


        ss = "org";
        f3(&ss);
        cout << "after calling f3, s1 is:" << ss << endl;


	string ss2 = "org";
        f4(&ss2);
        cout << "after calling f4, ss2 is:" << ss2 << endl;

	int x = 10202;
//	char buff[10];
//	itoa(x, buff, 10);
  	string buff = to_string(x);
	string ss5 = "here it is my number: " + buff;

	printf (ss5.c_str());

}
