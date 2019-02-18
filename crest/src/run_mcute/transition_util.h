/**
 * @author reza
 */
// Copyright (c) 2018, Reza Ahmadi (ahmadi@cs.queensu.ca)


#ifndef __TRANSITION__UTIL__H
#define __TRANSITION__UTIL__H

#include <assert.h>
#include <stdio.h>
//#include <sys/time.h>
#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <map>
#include <algorithm>

#include <stdlib.h>     /* srand, rand */


#include <limits>
#include <cstdlib>

#include <ctime>

// #include <concolic_search.h>
// #include <fileutil.h>
#include "STATES.hh"

#include <coverage_util.h>
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

	class transition_util {

	private:
		// std::vector<string> _transitions;

	public:
		static void create_coverage_util(map<string, coverage_util*>& CoverageUtilTable);
		// static void send_next_message(string next_t, Protocol1::Conj data);
		static void select_next_transition(STATES& Curr_State, string& next_t, vector<string>& VisitedTransitions);
		static void select_next_transition(STATES& Curr_State, string& next_t, vector<string>& VisitedTransitions, string strategy);
	};

}

#endif
