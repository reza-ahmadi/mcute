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

#include "run_mcute/data_generator.h"


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

  ////////////////////////////////////////////////////////////////////////
  // this method negates a random constraint from the existing ones in the PCs,
  // solves the resulting constraints, and stores
  // the inputs in some file, so the CUT can restore those inputs to take a new execution path
  ////////////////////////////////////////////////////////////////////////
  void data_generator::generateInteger(int size, vector<value_t>& inputs){
    //int imin = std::numeric_limits<int>::min(); // min int value
    //int imax = std::numeric_limits<int>::max(); //max int value
    //TODO: fix this to generate numbers between ranges uniforly using a c++ library
    //int inp1 = rand() % imax + imin;

    for (int i=0;i<size;i++){
      value_t inp = rand() % data_generator::_max + data_generator::_min;
      // printf("\ninput generated by the harness (in random inp gen): %d,\n",inp );
      inputs.push_back(inp);
    }
  }

}  // namespace mcute
