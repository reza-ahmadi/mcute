# 1 "./t3.cil.c"
# 1 "<built-in>"
# 1 "<command-line>"
# 1 "/usr/include/stdc-predef.h" 1 3 4
# 1 "<command-line>" 2
# 1 "./t3.cil.c"
# 8 "../test/t3.c"
void __globinit_t3(void) ;
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
# 206 "./../include/crest.h"
extern void __CrestInt(int *x ) __attribute__((__crest_skip__)) ;
# 362 "/usr/include/stdio.h"
extern int printf(char const * __restrict __format , ...) ;
# 8 "../test/t3.c"
int main(void)
{
  int desiredLevel ;
  int temprature ;
  int HighTemps ;
  int __retres4 ;

  {
  __globinit_t3();
  __CrestCall(1, 1);
# 13 "../test/t3.c"
  __CrestInt(& desiredLevel);
# 14 "../test/t3.c"
  __CrestInt(& temprature);
  __CrestLoad(4, (unsigned long )(& temprature), (long long )temprature);
  __CrestLoad(3, (unsigned long )0, (long long )60);
  __CrestApply2(2, 17, (long long )(temprature >= 60));
# 17 "../test/t3.c"
  if (temprature >= 60) {
    __CrestBranch(5, 3, 1);
    {
    __CrestLoad(9, (unsigned long )(& desiredLevel), (long long )desiredLevel);
    __CrestLoad(8, (unsigned long )0, (long long )5);
    __CrestApply2(7, 17, (long long )(desiredLevel >= 5));
# 17 "../test/t3.c"
    if (desiredLevel >= 5) {
      __CrestBranch(10, 4, 1);
# 18 "../test/t3.c"
      printf((char const * __restrict )"----branch visited: temprature>=60 && desiredLevel>=5 ----\n");
      __CrestClearStack(12);
      __CrestLoad(15, (unsigned long )(& temprature), (long long )temprature);
      __CrestLoad(14, (unsigned long )0, (long long )60);
      __CrestApply2(13, 1, (long long )(temprature - 60));
      __CrestStore(16, (unsigned long )(& HighTemps));
# 19 "../test/t3.c"
      HighTemps = temprature - 60;
    } else {
      __CrestBranch(11, 5, 0);
# 22 "../test/t3.c"
      printf((char const * __restrict )"----branch visited: !(temprature>=60 && desiredLevel>=5)----\n");
      __CrestClearStack(17);
    }
    }
  } else {
    __CrestBranch(6, 6, 0);
# 22 "../test/t3.c"
    printf((char const * __restrict )"----branch visited: !(temprature>=60 && desiredLevel>=5)----\n");
    __CrestClearStack(18);
  }
  __CrestLoad(19, (unsigned long )0, (long long )0);
  __CrestStore(20, (unsigned long )(& __retres4));
# 24 "../test/t3.c"
  __retres4 = 0;
  __CrestLoad(21, (unsigned long )(& __retres4), (long long )__retres4);
  __CrestReturn(22);
# 8 "../test/t3.c"
  return (__retres4);
}
}
void __globinit_t3(void)
{


  {
  __CrestInit();
}
}
