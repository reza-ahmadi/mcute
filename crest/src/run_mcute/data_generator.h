/**
 * @author reza
 */
// Copyright (c) 2018, Reza Ahmadi (ahmadi@cs.queensu.ca)


#ifndef __DATA__GENERATOR__H
#define __DATA__GENERATOR__H


#include <assert.h>
#include <stdio.h>
//#include <sys/time.h>
#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <map>
#include <algorithm>


#include <limits>
#include <cstdlib>

#include <ctime>

#include <fileutil.h>
// #include <coverage_util.h>
#include "base/basic_types.h"
#include "base/symbolic_execution.h"
#include "base/symbolic_expression.h"
#include "base/symbolic_interpreter.h"
#include "base/symbolic_path.h"
#include "base/symbolic_predicate.h"
#include "base/yices_solver.h"



using namespace std;

namespace mcute {

	//typedef long long int value_t;

	/*
	* author: reza
	* */

	class data_generator {

	private:
		const static value_t _max = std::numeric_limits<int>::max();
		const static value_t _min = std::numeric_limits<int>::min();

	public:
		static void generateInteger(int size, vector<value_t>& input);
	};

}

#endif
