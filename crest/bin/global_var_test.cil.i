# 1 "./global_var_test.cil.c"
# 1 "<built-in>"
# 1 "<command-line>"
# 1 "/usr/include/stdc-predef.h" 1 3 4
# 1 "<command-line>" 2
# 1 "./global_var_test.cil.c"
# 4 "../test/global_var_test.c"
void __globinit_global_var_test(void) ;
extern void __CrestInit(void) __attribute__((__crest_skip__)) ;
extern void __CrestHandleReturn(int id , long long val ) __attribute__((__crest_skip__)) ;
extern void __CrestReturn(int id ) __attribute__((__crest_skip__)) ;
extern void __CrestCall(int id , unsigned int fid ) __attribute__((__crest_skip__)) ;
extern void __CrestBranch(int id , int bid , unsigned char b ) __attribute__((__crest_skip__)) ;
extern void __CrestApply2(int id , int op , long long val ) __attribute__((__crest_skip__)) ;
extern void __CrestApply1(int id , int op , long long val ) __attribute__((__crest_skip__)) ;
extern void __CrestClearStack(int id ) __attribute__((__crest_skip__)) ;
extern void __CrestStore(int id , unsigned long addr ) __attribute__((__crest_skip__)) ;
extern void __CrestLoad(int id , unsigned long addr , long long val ) __attribute__((__crest_skip__)) ;
# 362 "/usr/include/stdio.h"
extern int printf(char const * __restrict __format , ...) ;
# 206 "./../include/crest.h"
extern void __CrestInt(int *x ) __attribute__((__crest_skip__)) ;
# 4 "../test/global_var_test.c"
int main(int argc , char const **argv )
{
  int desiredLevel ;
  int temprature ;
  int HighTemps ;
  int __retres6 ;

  {
  __globinit_global_var_test();
  __CrestCall(2, 1);
  __CrestStore(1, (unsigned long )(& argc));
# 11 "../test/global_var_test.c"
  __CrestInt(& desiredLevel);
# 12 "../test/global_var_test.c"
  __CrestInt(& temprature);
  __CrestLoad(5, (unsigned long )(& temprature), (long long )temprature);
  __CrestLoad(4, (unsigned long )0, (long long )60);
  __CrestApply2(3, 17, (long long )(temprature >= 60));
# 15 "../test/global_var_test.c"
  if (temprature >= 60) {
    __CrestBranch(6, 10, 1);
    {
    __CrestLoad(10, (unsigned long )(& desiredLevel), (long long )desiredLevel);
    __CrestLoad(9, (unsigned long )0, (long long )5);
    __CrestApply2(8, 17, (long long )(desiredLevel >= 5));
# 15 "../test/global_var_test.c"
    if (desiredLevel >= 5) {
      __CrestBranch(11, 11, 1);
# 16 "../test/global_var_test.c"
      printf((char const * __restrict )"----branch visited: temprature>=60 && desiredLevel>=5 ----\n");
      __CrestClearStack(13);
      __CrestLoad(16, (unsigned long )(& temprature), (long long )temprature);
      __CrestLoad(15, (unsigned long )0, (long long )60);
      __CrestApply2(14, 1, (long long )(temprature - 60));
      __CrestStore(17, (unsigned long )(& HighTemps));
# 17 "../test/global_var_test.c"
      HighTemps = temprature - 60;
    } else {
      __CrestBranch(12, 12, 0);
# 20 "../test/global_var_test.c"
      printf((char const * __restrict )"----branch visited: !(temprature>=60 && desiredLevel>=5)----\n");
      __CrestClearStack(18);
    }
    }
  } else {
    __CrestBranch(7, 13, 0);
# 20 "../test/global_var_test.c"
    printf((char const * __restrict )"----branch visited: !(temprature>=60 && desiredLevel>=5)----\n");
    __CrestClearStack(19);
  }
  __CrestLoad(22, (unsigned long )(& HighTemps), (long long )HighTemps);
  __CrestLoad(21, (unsigned long )0, (long long )10);
  __CrestApply2(20, 17, (long long )(HighTemps >= 10));
# 23 "../test/global_var_test.c"
  if (HighTemps >= 10) {
    __CrestBranch(23, 15, 1);
    __CrestLoad(25, (unsigned long )(& HighTemps), (long long )HighTemps);
# 24 "../test/global_var_test.c"
    printf((char const * __restrict )"----branch visited: HighTemps>=10!!!, %d degrees more than standart!!!----\n",
           HighTemps);
    __CrestClearStack(26);
  } else {
    __CrestBranch(24, 16, 0);
# 26 "../test/global_var_test.c"
    printf((char const * __restrict )"----branch visited: !(HighTemps>=10)----\n");
    __CrestClearStack(27);
  }
  __CrestLoad(28, (unsigned long )0, (long long )0);
  __CrestStore(29, (unsigned long )(& __retres6));
# 29 "../test/global_var_test.c"
  __retres6 = 0;
  __CrestLoad(30, (unsigned long )(& __retres6), (long long )__retres6);
  __CrestReturn(31);
# 4 "../test/global_var_test.c"
  return (__retres6);
}
}
void __globinit_global_var_test(void)
{


  {
  __CrestInit();
}
}
