#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <map>
#include <stdio.h>
#include <sstream>

using namespace std;

typedef long long int value_t;
typedef vector<value_t> TestInputs;
typedef map<string,TestInputs> TestTable;

TestTable getTests() {
	//returns a table of test file names and the list of inputs inside each
	TestTable tests;
	TestInputs inputs;
	string fileName = "input";
	int i = 0;
	while (true) {
		if (i>0){
			stringstream ss;
			ss << "input." << i;
			fileName = ss.str();
		}
		i++;

		ifstream testFile(fileName.c_str(), std::ifstream::in);
		if (!testFile.good()) break;
		cout<<"\nreading file: "<<fileName << endl;

		value_t input;
		while (testFile >> input) {
			inputs.push_back(input);
			cout<<"\n now reading: "<<input<<endl;
		}
		testFile.close();
		tests.insert(TestTable::value_type(fileName, inputs));
		inputs.clear();
	}

	return tests;
}

int main()
{
	TestTable tests = getTests();
	for (TestTable::iterator testItr=tests.begin();testItr!=tests.end();testItr++){
		cout<< testItr->first << endl;
		TestInputs inputs = testItr->second;
		cout<< inputs.at(0) << "," << inputs.at(1) << endl;
//		for (TestInputs::iterator inputItr=inputs.begin();inputItr!=inputs.end();++inputItr++){
//			cout<< *inputItr << endl;
//		}
	}
	return 0;
}
