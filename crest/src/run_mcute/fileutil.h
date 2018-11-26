#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <map>
#include <stdio.h>
#include "base/basic_types.h"
#include "base/symbolic_execution.h"
#include <assert.h>

using namespace std;

namespace mcute {

//typedef long long int value_t;
typedef vector<value_t> TestInputs;
typedef map<string,TestInputs> TestTable;

/*
 * author: reza
 * */

class fileutil{
public:
	//returns a table of test file names and the list of inputs inside each
	static TestTable getTests();
	static void writeInputs(const string& file, const vector<value_t>& input);
	static void writeData(const string& file, const string& data);
	static void printSymExObj(bool inputs, bool constraints, bool branches);
//	static void writeCoverage(const string& file);

};

}
