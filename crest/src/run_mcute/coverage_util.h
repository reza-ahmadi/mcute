// Copyright (c) 2018, Reza Ahmadi (ahmadi@cs.queensu.ca)
//
// This file is part of mCUTE, which is distributed under the revised
// BSD license.  A copy of this license can be found in the file LICENSE.
//
// This program is distributed in the hope that it will be useful, but
// WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See LICENSE
// for details.

#ifndef __COVERAGE__UTIL__H
#define __COVERAGE__UTIL__H

#include <algorithm>
#include <assert.h>
#include <cmath>
#include <fstream>
#include <functional>
#include <limits>
#include <stdio.h>
#include <stdlib.h>
#include <queue>
#include <utility>

#include "base/yices_solver.h"
#include "base/basic_types.h"
//#include "run_mcute/concolic_search.h"
#include "base/symbolic_execution.h"
#include <iostream>

using namespace std;

namespace mcute {

//typedef long long int value_t;

/*
 * author: reza
 * */

class coverage_util {

protected:
	vector<function_id_t> branch_function_;
	branch_id_t max_branch_;
	unsigned int num_covered_;
	vector<bool> reached_;
	vector<unsigned int> branch_count_;
	function_id_t max_function_;
	unsigned int reachable_functions_;
	unsigned int reachable_branches_;

	time_t start_time_;

	typedef vector<branch_id_t>::const_iterator BranchIt;

public:
	vector<branch_id_t> paired_branch_;
	vector<bool> total_covered_;
	vector<bool> covered_;
	vector<branch_id_t> branches_;
	unsigned int total_num_covered_;
	string transition;
	//returns a table of test file names and the list of inputs inside each
	coverage_util(string t);
	coverage_util();
	void initCoverageInfo();
	bool branchBelongsToTransition(int branchId);
	bool updateCoverageInfo(const SymbolicExecution& ex);
	bool updateCoverageInfo(const SymbolicExecution& ex, set<branch_id_t>* new_branches);
	void writeCoverage(const string& file);
//	void resetIterations();
	int incIterations();

private:
//	const int max_iters_;
	int num_iters_;
};

}

#endif
