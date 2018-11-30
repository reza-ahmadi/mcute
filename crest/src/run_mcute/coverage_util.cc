// Copyright (c) 2018, Reza Ahmadi (ahmadi@cs.queensu.ca)
//
// This file is part of mCUTE, which is distributed under the revised
// BSD license.  A copy of this license can be found in the file LICENSE.
//
// This program is distributed in the hope that it will be useful, but
// WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See LICENSE
// for details.


#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <map>
#include <stdio.h>
#include <sstream>
#include "run_mcute/coverage_util.h"
#include "run_mcute/fileutil.h"
using namespace std;

/*
 * author: reza
 * */

namespace mcute {

coverage_util::coverage_util() {
}

coverage_util::coverage_util(string t) {
	transition = t;
//	max_iters_=100;
}

void coverage_util::initCoverageInfo() {

	num_iters_ = 0;
	max_branch_ = 0;
	max_function_ = 0;
	branches_.reserve(100000);
	branch_count_.reserve(100000);
	branch_count_.push_back(0);

	//read in the set of transition branches
	cout << "trying to read branches file: " << "branches_" + transition
			<< endl;
	ifstream in("branches_" + transition);
	assert(in);
	function_id_t fid;
	int numBranches;
	while (in >> fid >> numBranches) {
		branch_count_.push_back(2 * numBranches);
		branch_id_t b1, b2;
		for (int i = 0; i < numBranches; i++) {
			assert(in >> b1 >> b2);
			branches_.push_back(b1);
			branches_.push_back(b2);
			max_branch_ = max(max_branch_, max(b1, b2));
		}
	}
	in.close();
	max_branch_++;
	max_function_ = branch_count_.size();

// Compute the paired-branch map.
	paired_branch_.resize(max_branch_);
	for (size_t i = 0; i < branches_.size(); i += 2) {
		paired_branch_[branches_[i]] = branches_[i + 1];
		paired_branch_[branches_[i + 1]] = branches_[i];
	}

	// Compute the branch-to-function map.
	branch_function_.resize(max_branch_);
	{
		size_t i = 0;
		for (function_id_t j = 0; j < branch_count_.size(); j++) {
			for (size_t k = 0; k < branch_count_[j]; k++) {
				branch_function_[branches_[i++]] = j;
			}
		}
	}

	// Initialize all branches to "uncovered" (and functions to "unreached").
	total_num_covered_ = num_covered_ = 0;
	reachable_functions_ = reachable_branches_ = 0;
	covered_.resize(max_branch_, false);
	total_covered_.resize(max_branch_, false);
	reached_.resize(max_function_, false);

	// Print out the initial coverage.
//	fprintf(stderr,	"Iteration 0 (0s): covered %u branches [%u reach funs, %u reach branches].\n",
//			num_covered_, reachable_functions_, reachable_branches_);

// Sort the branches.
	sort(branches_.begin(), branches_.end());

}

bool coverage_util::updateCoverageInfo(const SymbolicExecution& ex) {
	return updateCoverageInfo(ex, NULL);
}

bool coverage_util::updateCoverageInfo(const SymbolicExecution& ex,
		set<branch_id_t>* new_branches) {

	cout << "trying to update branches for transition: " << transition << endl;

	const unsigned int prev_covered_ = num_covered_;
	const vector<branch_id_t>& branches = ex.path().branches();
	for (BranchIt i = branches.begin(); i != branches.end(); ++i) {
		//reza start
		//		if ((*i > 0) && !covered_[*i]) {
		if ((*i > 0) && !covered_[*i] && branchBelongsToTransition(*i)) {
			//reza end
			covered_[*i] = true;
			num_covered_++;
			if (new_branches) {
				new_branches->insert(*i);
			}
			if (!reached_[branch_function_[*i]]) {
				reached_[branch_function_[*i]] = true;
				reachable_functions_++;
				reachable_branches_ += branch_count_[branch_function_[*i]];
			}
		}
		//reza start
//		if ((*i > 0) && !total_covered_[*i]) {
		if ((*i > 0) && !total_covered_[*i] && branchBelongsToTransition(*i)) {
			//reza end
			total_covered_[*i] = true;
			total_num_covered_++;
		}
	}

//  fprintf(stderr, "Iteration %d (%lds): covered %u branches [%u reach funs, %u reach branches].\n",
//	  num_iters_, time(NULL)-start_time_, total_num_covered_, reachable_functions_, reachable_branches_);
	fprintf(stderr,
			"\n--------------------------------------------------------------------------------\n");
	fprintf(stderr,
			"Transition %s: Iteration %d : covered %u branches [%u reach branches].\n",
			transition.c_str(), num_iters_, total_num_covered_,
			reachable_branches_);
	fprintf(stderr,
			"--------------------------------------------------------------------------------\n");

	bool found_new_branch = (num_covered_ > prev_covered_);
	if (found_new_branch) {
//		string coverageFile ("coverage_");
//		coverageFile = coverageFile + transition;
//		fileutil fu;
		writeCoverage(string("coverage_") + transition);
	}

	return found_new_branch;
}

//void coverage_util::resetIterations(){
//	num_iters_=0;
//}

int coverage_util::incIterations() {
	num_iters_++;
}

bool coverage_util::branchBelongsToTransition(int branchId) {
	for (BranchIt i = branches_.begin(); i != branches_.end(); ++i) {
		if (*i == branchId)
			return true;
	}
	return false;
}

void coverage_util::writeCoverage(const string& file) {
	FILE* f = fopen(file.c_str(), "w");
	if (!f) {
		fprintf(stderr, "Failed to open %s.\n", file.c_str());
		perror("Error: ");
		exit(-1);
	}

	for (BranchIt i = branches_.begin(); i != branches_.end(); ++i) {
		if (total_covered_[*i]) {
			fprintf(f, "%d\n", *i);
		}
	}

	fclose(f);
}

}
