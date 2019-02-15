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

//get index of a branch
int getElementIndex (vector<branch_id_t> branches, branch_id_t branch){
  for (int i=0;i<branches.size();i++){
    if (branches.at(i) == branch){
      return i;
    }
  }
  return -1;
}

////////////////////////////////////////////////////////////////////////
// this method negates constraints systematiclly in the PCs,
// solves the resulting constraints, and stores
// the inputs in some file, so the CUT can restore those inputs to take a new execution path
////////////////////////////////////////////////////////////////////////
void branch_util::negate_sys(const SymbolicExecution& ex, int& branch_idx, map<string, coverage_util*> coverage_util_table, string next_t){

  bool debug=false;

  int constraints_size = ex.path().constraints().size();
  vector<branch_id_t> branches_ids;

  //getting branch ids
  for (int i=0;i<ex.path().branches().size();i++){
    if (ex.path().branches().at(i)>0)
    branches_ids.push_back(ex.path().branches().at(i));
  }

  int constraint_id_to_negate = 0;
  int new_branch_selected = false;
  // cout << "\nsize of the coverage_util_table table is:" << coverage_util_table.size() <<endl;

    // int bid = constraints_size;
  for (int i=constraints_size-1;i>=0 && !new_branch_selected;i--){

      for (map<string,coverage_util*>::iterator it=coverage_util_table.begin();it!=coverage_util_table.end() && !new_branch_selected;it++){

        coverage_util* cu = it->second;
        // cout << "coverage_util object for the transition:" << it->first <<endl;

      //if this branch does not contain the current transition
      if (!cu->branchBelongsToTransition(branches_ids.at(i))){
        continue;
      }

      if (debug){
        cout<<endl<<"[branch_util::negate_sys]: branch to check for negation: " << branches_ids.at(i) <<endl;
        cout<<"[branch_util::negate_sys]: the pair of the branch is: " << cu->paired_branch_[branches_ids.at(i)] <<endl;

        //printing the covered branches
        for (int l=1;l<cu->covered_.size();l++)
        {
          if (cu->covered_[l]){
            cout <<"covered["<<l<<"] = "<<cu->covered_[l]<<endl;
          }
        }
      }

      //selecting a branch for negation
      int branch_not_covered = -1;
      branch_idx = branches_ids.at(i);
      int pair_branch_idx = cu->paired_branch_[branch_idx];
      int ubound = max(branch_idx,pair_branch_idx);
      int lbound = min(branch_idx,pair_branch_idx);
      // cout<<"[branch_util::negate_sys]: lbound branch: " << lbound << ", ubound branch: " << ubound << ", " << cu->branches_.size() << ", " << cu->covered_.size()<<endl;
      for (int b=lbound;b<=ubound;b++){
        //pair was not covered and b is indeed a branchid
        if (getElementIndex(branches_ids, b)>=0 && !(cu->covered_[b])){
          branch_not_covered = b;

          //is the constraint to negate now available in the current PCs?
          if (find(branches_ids.begin(), branches_ids.end(), cu->paired_branch_[branch_not_covered]) == branches_ids.end())
          {
            //constraint to negate does not exist in the current PC
            for (int k=b;k>=lbound;k--){
              if(find(branches_ids.begin(), branches_ids.end(), k) != branches_ids.end()){
                  constraint_id_to_negate = getElementIndex(branches_ids, k);
                  break;
              }
            }

          }else{
            constraint_id_to_negate = i;
            // branch_idx = cu->paired_branch_[branch_not_covered];
            // branch_idx = branch_not_covered;
          }
          new_branch_selected = true;
          // cout<<"[branch_util::negate_sys]: branch not covered before, selecting branch: " << branch_not_covered <<endl;
          break;
        }//if
      }//for bound

    }//for constraint
  }//for coverage

  if (!new_branch_selected){
    // if (constraints_size>0){
    branch_util::negate_rand(ex,constraint_id_to_negate);
    // branch_idx =constraints_size-1;
    // cout<<"[branch_util::negate_sys]: cannot continue systematic branch coverage, selecting random constaint: " << constraint_id_to_negate <<endl;
    // }
  }

  if (debug){
    //print SE object info
    fileutil::printSymExObj(false, true, false);
  }

  //1. negate and solve the PCs
  vector<value_t> input;
  // printf("\n---Call SolveAtBranch, branch_idx: %d", constraint_id_to_negate);
  solveAtBranch(ex, constraint_id_to_negate, input);

  if (debug){
    //print the generated inputs
    std::cout << "\n-----printing inputs generated by the Concolic engine-------\n";
    for (size_t i = 0; i < input.size(); i++) {
      std::cout << ":" << input[i] << std::endl;
    }
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

  bool debug=false;

  int constraints = ex.path().constraints().size();

  int min = 0; // min int value
  int max = constraints; //max int value

  // printf ("\ntotal constraints are:%d\n", constraints);

  if (max>0){
    branch_idx = rand() % max + min;
  }

  if (debug){
    printf ("\t[branch_util::negate_rand]:: branch_idx to negagte is: %d, total constraints are:%d\n", branch_idx, constraints);
    //print SE object info
    fileutil::printSymExObj(false, true, false);
  }

  //1. negate and solve the PCs
  vector<value_t> input;
  // printf("\n---Call SolveAtBranch, branch_idx: %d", branch_idx);
  solveAtBranch(ex, branch_idx, input);

  //print the generated inputs
  if (debug){
    std::cout << "\n-----printing inputs generated by the Concolic engine-------\n";
    for (size_t i = 0; i < input.size(); i++) {
      std::cout << ":" << input[i] << std::endl;
    }
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
