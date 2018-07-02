# 1 "./mytest.cil.c"
# 1 "<built-in>"
# 1 "<command-line>"
# 1 "/usr/include/stdc-predef.h" 1 3 4
# 1 "<command-line>" 2
# 1 "./mytest.cil.c"
# 7 "../test/mytest.c"
void __globinit_mytest(void) ;
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
# 202 "./../include/crest.h"
extern void __CrestInt(int *x ) __attribute__((__crest_skip__)) ;
# 362 "/usr/include/stdio.h"
extern int printf(char const * __restrict __format , ...) ;
# 4 "../test/mytest.c"
int A[100] ;
# 5 "../test/mytest.c"
char B[12][97] ;
# 7 "../test/mytest.c"
int main(void)
{
  int x ;
  int y ;
  int z ;
  int __retres4 ;

  {
  __globinit_mytest();
  __CrestCall(1, 1);
# 12 "../test/mytest.c"
  __CrestInt(& x);
# 13 "../test/mytest.c"
  __CrestInt(& y);
# 14 "../test/mytest.c"
  __CrestInt(& z);
  __CrestLoad(4, (unsigned long )(& x), (long long )x);
  __CrestLoad(3, (unsigned long )0, (long long )0);
  __CrestApply2(2, 14, (long long )(x > 0));
# 16 "../test/mytest.c"
  if (x > 0) {
    __CrestBranch(5, 3, 1);
    {
    __CrestLoad(9, (unsigned long )(& x), (long long )x);
    __CrestLoad(8, (unsigned long )0, (long long )10);
    __CrestApply2(7, 16, (long long )(x < 10));
# 16 "../test/mytest.c"
    if (x < 10) {
      __CrestBranch(10, 4, 1);
# 18 "../test/mytest.c"
      printf((char const * __restrict )"**** in: x>0 && x<10 ****");
      __CrestClearStack(12);
      {
      __CrestLoad(15, (unsigned long )(& y), (long long )y);
      __CrestLoad(14, (unsigned long )(& x), (long long )x);
      __CrestApply2(13, 14, (long long )(y > x));
# 19 "../test/mytest.c"
      if (y > x) {
        __CrestBranch(16, 6, 1);
        __CrestLoad(18, (unsigned long )(& x), (long long )x);
        __CrestLoad(19, (unsigned long )(& y), (long long )y);
# 20 "../test/mytest.c"
        printf((char const * __restrict )"*** y is greater than x. x: %d, y:%d",
               x, y);
        __CrestClearStack(20);
      } else {
        __CrestBranch(17, 7, 0);
        __CrestLoad(21, (unsigned long )(& x), (long long )x);
        __CrestLoad(22, (unsigned long )(& y), (long long )y);
# 22 "../test/mytest.c"
        printf((char const * __restrict )"*** x is greater than or equal to y. x: %d, y:%d",
               x, y);
        __CrestClearStack(23);
      }
      }
    } else {
      __CrestBranch(11, 8, 0);
# 25 "../test/mytest.c"
      printf((char const * __restrict )"**** in: ! (x>0 && x<10) ****");
      __CrestClearStack(24);
    }
    }
  } else {
    __CrestBranch(6, 9, 0);
# 25 "../test/mytest.c"
    printf((char const * __restrict )"**** in: ! (x>0 && x<10) ****");
    __CrestClearStack(25);
  }
  __CrestLoad(28, (unsigned long )(& x), (long long )x);
  __CrestLoad(27, (unsigned long )0, (long long )10);
  __CrestApply2(26, 17, (long long )(x >= 10));
# 28 "../test/mytest.c"
  if (x >= 10) {
    __CrestBranch(29, 11, 1);
    {
    __CrestLoad(33, (unsigned long )(& z), (long long )z);
    __CrestLoad(32, (unsigned long )0, (long long )100);
    __CrestApply2(31, 15, (long long )(z <= 100));
# 28 "../test/mytest.c"
    if (z <= 100) {
      __CrestBranch(34, 12, 1);
# 29 "../test/mytest.c"
      printf((char const * __restrict )"$$$$$$$$$$ in: x>0 && z<=10 ****");
      __CrestClearStack(36);
      {
      __CrestLoad(39, (unsigned long )(& z), (long long )z);
      __CrestLoad(38, (unsigned long )0, (long long )5);
      __CrestApply2(37, 16, (long long )(z < 5));
# 30 "../test/mytest.c"
      if (z < 5) {
        __CrestBranch(40, 14, 1);
# 31 "../test/mytest.c"
        printf((char const * __restrict )"$$$$$$$$$$ z<5");
        __CrestClearStack(42);
      } else {
        __CrestBranch(41, 15, 0);
# 34 "../test/mytest.c"
        printf((char const * __restrict )"$$$$$$$$$$ in: z>=5");
        __CrestClearStack(43);
      }
      }
    } else {
      __CrestBranch(35, 16, 0);

    }
    }
  } else {
    __CrestBranch(30, 17, 0);

  }
  __CrestLoad(44, (unsigned long )0, (long long )0);
  __CrestStore(45, (unsigned long )(& __retres4));
# 38 "../test/mytest.c"
  __retres4 = 0;
  __CrestLoad(46, (unsigned long )(& __retres4), (long long )__retres4);
  __CrestReturn(47);
# 7 "../test/mytest.c"
  return (__retres4);
}
}
void __globinit_mytest(void)
{


  {
  __CrestInit();
}
}
