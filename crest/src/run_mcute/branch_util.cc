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

#include "run_mcute/branch_util.h"


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
  // this method negates a branch, solves the resulting constraints, and stores
  // the inputs in some file, so the CUT can restore those inputs to take a new execution path
  ////////////////////////////////////////////////////////////////////////

  /*  void negate_old(const SymbolicExecution& ex, int& branch_idx){

  int constraints = ex.path().constraints().size();

  printf ("Harness: branch_idx is: %d, Constraints are:%d\n", branch_idx, constraints);
  //print SE object info
  fileutil::printSymExObj(false, true, false);

  if (branch_idx<constraints){
  printf ("Harness: Negating_Solving_Gen, branch_idx is: %d, Constraints are:%d", branch_idx, constraints);
  //handle iteration
  //0. read the symbolic execution object
  // Read the symbolic execution object of the previous execution of the transition
  // SymbolicExecution ex;
  // std::ifstream in("szd_execution", std::ios::in | std::ios::binary);
  // ex.Parse(in);
  // in.close();

  //1. negate and solve the PCs
  //ToDo: how to actually call SolveAtBranch
  vector<value_t> input;
  printf("number of constraints are: %d", ex.path().constraints().size());
  //for (size_t i = 0; (i < ex.path().constraints().size()); i++) {
  //	if (i%2==1)
  printf("\n---Call SolveAtBranch, branch_idx: %d", branch_idx);
  solveAtBranch(ex, branch_idx, input);
  //}
  branch_idx++;
  //print the generated inputs
  std::cout << "\n-----printing inputs generated by the Concolic engine-------\n";
  for (size_t i = 0; i < input.size(); i++) {
  std::cout << ":" << input[i] << std::endl;
}
//write the inputs to a file, since SI object is initialized by these inputs
fileutil::writeInputs("input", input);
}else{
branch_idx=0;
}
}

*/

////////////////////////////////////////////////////////////////////////
// this method negates a random constraint from the existing ones in the PCs,
// solves the resulting constraints, and stores
// the inputs in some file, so the CUT can restore those inputs to take a new execution path
////////////////////////////////////////////////////////////////////////
void branch_util::negate_sys(const SymbolicExecution& ex, int& branch_idx, map<string, coverage_util*> coverage_util_table, string next_t){

  // coverage_util* cu = coverage_util_table[next_t];

  // vector<SymbolicPred*> constraints = ex.path().constraints();
  int constraints_size = ex.path().constraints().size();
  const vector<branch_id_t>& branches = ex.path().branches();

  int new_branch_selected = false;
  for (map<string,coverage_util*>::iterator it=coverage_util_table.begin();it!=coverage_util_table.end();it++){
    if (new_branch_selected)
    break;
    coverage_util* cu = it->second;
    for (int i=0;i<branches.size();i++){
      cout<<endl<<"[branch_util::negate_sys]: branch to check for negation: " << branches.at(i) <<endl;
      cout<<"[branch_util::negate_sys]: branch belongs to transition " << cu->transition <<endl;
      //if the branch belongs to this cu object and is  its pair was not covered
      cout<<"[branch_util::negate_sys]: branchs already covered " << cu->total_num_covered_ << "out of" << cu->branches_.size() <<endl;
      for (int l=0;l<cu->covered_.size();l++)
        cout <<"covered["<<l<<"] = "<<cu->covered_[l]<<endl;
      if (cu->branchBelongsToTransition(branches.at(i)) && !(cu->covered_[cu->paired_branch_[branches.at(i)]-1])){
        branch_idx = i;
        new_branch_selected = true;
        cout<<"[branch_util::negate_sys]: pair of the branch not covered before, selecting branch: " << cu->paired_branch_[branches.at(i)] <<endl;
        break;
      }
    }
  }

  if (!new_branch_selected){
    if (constraints_size>0){
      branch_idx =constraints_size-1;
      cout<<"[branch_util::negate_sys]: pair of the branch already covered, selecting: " << branch_idx <<endl;
    }
  }

  printf ("\t[branch_util::negate_sys]:: branch_idx to negagte is: %d, total constraints are:%d\n", branch_idx, constraints_size);
  //print SE object info
  fileutil::printSymExObj(false, true, false);

  //1. negate and solve the PCs
  vector<value_t> input;
  printf("\n---Call SolveAtBranch, branch_idx: %d", branch_idx);
  solveAtBranch(ex, branch_idx, input);

  //print the generated inputs
  std::cout << "\n-----printing inputs generated by the Concolic engine-------\n";
  for (size_t i = 0; i < input.size(); i++) {
    std::cout << ":" << input[i] << std::endl;
  }

  //write the inputs to a file, since SI object is initialized by these inputs
  fileutil::writeInputs("input", input);
}


////////////////////////////////////////////////////////////////////////
// this method negates a random constraint from the existing ones in the PCs,
// solves the resulting constraints, and stores
// the inputs in some file, so the CUT can restore those inputs to take a new execution path
////////////////////////////////////////////////////////////////////////
void branch_util::negate_rand(const SymbolicExecution& ex, int& branch_idx){

  int constraints = ex.path().constraints().size();

  int min = 0; // min int value
  int max = constraints; //max int value

  // printf ("\ntotal constraints are:%d\n", constraints);

  if (max>0){
    branch_idx = rand() % max + min;
  }

  printf ("\t[branch_util::negate_rand]:: branch_idx to negagte is: %d, total constraints are:%d\n", branch_idx, constraints);
  //print SE object info
  fileutil::printSymExObj(false, true, false);

  //1. negate and solve the PCs
  vector<value_t> input;
  printf("\n---Call SolveAtBranch, branch_idx: %d", branch_idx);
  solveAtBranch(ex, branch_idx, input);

  //print the generated inputs
  std::cout << "\n-----printing inputs generated by the Concolic engine-------\n";
  for (size_t i = 0; i < input.size(); i++) {
    std::cout << ":" << input[i] << std::endl;
  }

  //write the inputs to a file, since SI object is initialized by these inputs
  fileutil::writeInputs("input", input);
}


////////////////////////////////////////////////////////////////////////
//solveAtBranch
////////////////////////////////////////////////////////////////////////

bool branch_util::solveAtBranch(const SymbolicExecution& ex, int branch_idx, vector<value_t>& input){
  const vector<SymbolicPred*>& constraints = ex.path().constraints();

  // Optimization: If any of the previous constraints are idential to the
  // branch_idx-th constraint, immediately return false.
  for (int i = static_cast<int>(branch_idx) - 1; i >= 0; i--) {
    if (constraints[branch_idx]->Equal(*constraints[i]))
    return false;
  }

  vector<const SymbolicPred*> cs(constraints.begin(),
  constraints.begin() + branch_idx + 1);
  map<var_t, value_t> soln;
  constraints[branch_idx]->Negate();
  // fprintf(stderr, "Yices . . . ");
  bool success = YicesSolver::IncrementalSolve(ex.inputs(), ex.vars(), cs,
  &soln);
  // fprintf(stderr, "%d\n", success);
  constraints[branch_idx]->Negate();

  if (success) {
    // Merge the solution with the previous input to get the next
    // input.  (Could merge with random inputs, instead.)
    input = ex.inputs();
    // RandomInput(ex.vars(), input);

    typedef map<var_t, value_t>::const_iterator SolnIt;
    for (SolnIt i = soln.begin(); i != soln.end(); ++i) {
      input[i->first] = i->second;
    }
    return true;
  }

  return false;

}

}  // namespace mcute
