#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <map>
#include <stdio.h>
#include <sstream>
#include "run_mcute/fileutil.h"
using namespace std;

/*
 * author: reza
 * */

namespace mcute {

void fileutil::printSymExObj(bool inputs, bool constraints, bool branches){
  SymbolicExecution ex;

  ifstream in("szd_execution", ios::in | ios::binary);
  assert(ex.Parse(in));
  in.close();

  if (inputs){
    // Print input.
    for (size_t i = 0; i < ex.inputs().size(); i++) {
      cout << "(= x" << i << " " << ex.inputs()[i] << ")\n";
    }
    cout << endl;
  }

  if (constraints)
  { // Print the constraints.
    string tmp;
    for (size_t i = 0; i < ex.path().constraints().size(); i++) {
      tmp.clear();
      ex.path().constraints()[i]->AppendToString(&tmp);
      cout << tmp << endl;
    }
    cout << endl;
  }

  if (branches){
    // Print the branches.
    for (size_t i = 0; i < ex.path().branches().size(); i++) {
      cout << ex.path().branches()[i] << "\n";
    }
    cout << endl << endl;
  }
}

void fileutil::writeInputs(const string& file, const vector<value_t>& input) {
	FILE* f = fopen(file.c_str(), "w");
	if (!f) {
		fprintf(stderr, "Failed to open %s.\n", file.c_str());
		perror("Error: ");
		exit(-1);
	}

	for (size_t i = 0; i < input.size(); i++) {
		fprintf(f, "%lld\n", input[i]);
	}

	fclose(f);
}

void fileutil::writeData(const string& file, const string& data) {
	FILE* f = fopen(file.c_str(), "w");
	if (!f) {
		fprintf(stderr, "Failed to open %s.\n", file.c_str());
		perror("Error: ");
		exit(-1);
	}

	fprintf(f, "%s\n", data.c_str());

	fclose(f);
}

TestTable fileutil::getTests() {
	//returns a table of test file names and the list of inputs inside each
	TestTable tests;
	TestInputs inputs;
	string fileName = "input";
	int i = 0;
	while (true) {
		if (i > 0) {
			stringstream ss;
			ss << "input." << i;
			fileName = ss.str();
		}
		i++;

		ifstream testFile(fileName.c_str(), std::ifstream::in);
		if (!testFile.good())
			break;
//		cout << "\nreading file: " << fileName << endl;
		value_t input;
		while (testFile >> input) {
			inputs.push_back(input);
//			cout << "\n now reading: " << input << endl;
		}
		testFile.close();
		if (inputs.size() > 0) {
			tests.insert(TestTable::value_type(fileName, inputs));
			inputs.clear();
		}
	}

	return tests;

}

}

//int main()
//{
//	TestTable tests = getTests();
//	for (TestTable::iterator testItr=tests.begin();testItr!=tests.end();testItr++){
//		cout<< testItr->first << endl;
//		TestInputs inputs = testItr->second;
//		cout<< inputs.at(0) << "," << inputs.at(1) << endl;
////		for (TestInputs::iterator inputItr=inputs.begin();inputItr!=inputs.end();++inputItr++){
////			cout<< *inputItr << endl;
////		}
//	}
//	return 0;
//}
