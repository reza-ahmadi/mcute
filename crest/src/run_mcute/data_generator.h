// Copyright (c) 2018, Reza Ahmadi (ahmadi@cs.queensu.ca)
//
// This file is part of mCUTE, which is distributed under the revised
// BSD license.  A copy of this license can be found in the file LICENSE.
//
// This program is distributed in the hope that it will be useful, but
// WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See LICENSE
// for details.

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
		const static value_t _max = 1000000;
		const static value_t _min = -1000000;

	public:
		static void generateInteger(int size, vector<value_t>& input);
	};

}

#endif
