// Copyright (c) 2018, Reza Ahmadi (ahmadi@cs.queensu.ca)
//
// This file is part of mCUTE, which is distributed under the revised
// BSD license.  A copy of this license can be found in the file LICENSE.
//
// This program is distributed in the hope that it will be useful, but
// WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See LICENSE
// for details.

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
#include <cstdlib>

#include "run_mcute/transition_util.h"

// #include "base/yices_solver.h"
// #include "base/basic_types.h"
// //#include "run_mcute/concolic_search.h"
// #include "base/symbolic_execution.h"
// #include <iostream>

using std::binary_function;
using std::ifstream;
using std::ios;
using std::cout;
using std::cin;
using std::endl;
using std::min;
using std::max;
using std::numeric_limits;
using std::pair;
using std::queue;
using std::random_shuffle;
using std::stable_sort;

namespace mcute {

  void transition_util::create_coverage_util(map<string, coverage_util*>& CoverageUtilTable){

    coverage_util* cu1 = new coverage_util(string("t1"));
    cu1->initCoverageInfo();

    coverage_util* cu2 = new coverage_util(string("t2"));
    cu2->initCoverageInfo();

    CoverageUtilTable.insert(pair<string, coverage_util*>("t1",cu1));
    CoverageUtilTable.insert(pair<string, coverage_util*>("t2",cu2));
  }

  ////////////////////////////////////////////////////////////////////////
  // this method selects the next transition for execution based on the
  // possible and exacutable transitions
  ////////////////////////////////////////////////////////////////////////
  void transition_util::select_next_transition(STATES& Curr_State, string& next_t, vector<string>& VisitedTransitions){
    if (Curr_State==INIT){
      //it is possible to run t3
      next_t = "t1";
      printf ("Curr state: INIT");
    }else if (Curr_State==SETUP){
      //it is possible to run t4
      next_t = "t2";
      printf ("Curr state: SETUP");
    }
    else{
      printf ("Curr state: last state. Restarting..");
      //commands.loopBack().send(); //to be able to re-execute the previous transitions
      //printf ("++++++Harness: msg 'loopBack' sent");
      Curr_State=INIT;
      next_t = "t1";
    }

    //adding the transition to the list of covered transitions
    if(std::find(VisitedTransitions.begin(), VisitedTransitions.end(), next_t) == VisitedTransitions.end()){
      VisitedTransitions.push_back(next_t);
    }
  }

}  // namespace mcute