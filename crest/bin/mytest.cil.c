/* Generated by CIL v. 1.7.3 */
/* print_CIL_Input is true */

#line 7 "../test/mytest.c"
void __globinit_mytest(void) ;
extern void __CrestInit(void)  __attribute__((__crest_skip__)) ;
extern void __CrestHandleReturn(int id , long long val )  __attribute__((__crest_skip__)) ;
extern void __CrestReturn(int id )  __attribute__((__crest_skip__)) ;
extern void __CrestCall(int id , unsigned int fid )  __attribute__((__crest_skip__)) ;
extern void __CrestBranch(int id , int bid , unsigned char b )  __attribute__((__crest_skip__)) ;
extern void __CrestApply2(int id , int op , long long val )  __attribute__((__crest_skip__)) ;
extern void __CrestApply1(int id , int op , long long val )  __attribute__((__crest_skip__)) ;
extern void __CrestClearStack(int id )  __attribute__((__crest_skip__)) ;
extern void __CrestStore(int id , unsigned long addr )  __attribute__((__crest_skip__)) ;
extern void __CrestLoad(int id , unsigned long addr , long long val )  __attribute__((__crest_skip__)) ;
#line 202 "./../include/crest.h"
extern void __CrestInt(int *x )  __attribute__((__crest_skip__)) ;
#line 362 "/usr/include/stdio.h"
extern int printf(char const   * __restrict  __format  , ...) ;
#line 4 "../test/mytest.c"
int A[100]  ;
#line 5 "../test/mytest.c"
char B[12][97]  ;
#line 7 "../test/mytest.c"
int main(void) 
{ 
  int x ;
  int y ;
  int z ;
  int __retres4 ;

  {
  __globinit_mytest();
  __CrestCall(1, 1);
#line 12
  __CrestInt(& x);
#line 13
  __CrestInt(& y);
#line 14
  __CrestInt(& z);
  __CrestLoad(4, (unsigned long )(& x), (long long )x);
  __CrestLoad(3, (unsigned long )0, (long long )0);
  __CrestApply2(2, 14, (long long )(x > 0));
#line 16
  if (x > 0) {
    __CrestBranch(5, 3, 1);
    {
    __CrestLoad(9, (unsigned long )(& x), (long long )x);
    __CrestLoad(8, (unsigned long )0, (long long )10);
    __CrestApply2(7, 16, (long long )(x < 10));
#line 16
    if (x < 10) {
      __CrestBranch(10, 4, 1);
#line 18
      printf((char const   * __restrict  )"**** in: x>0 && x<10 ****");
      __CrestClearStack(12);
      {
      __CrestLoad(15, (unsigned long )(& y), (long long )y);
      __CrestLoad(14, (unsigned long )(& x), (long long )x);
      __CrestApply2(13, 14, (long long )(y > x));
#line 19
      if (y > x) {
        __CrestBranch(16, 6, 1);
        __CrestLoad(18, (unsigned long )(& x), (long long )x);
        __CrestLoad(19, (unsigned long )(& y), (long long )y);
#line 20
        printf((char const   * __restrict  )"*** y is greater than x. x: %d, y:%d",
               x, y);
        __CrestClearStack(20);
      } else {
        __CrestBranch(17, 7, 0);
        __CrestLoad(21, (unsigned long )(& x), (long long )x);
        __CrestLoad(22, (unsigned long )(& y), (long long )y);
#line 22
        printf((char const   * __restrict  )"*** x is greater than or equal to y. x: %d, y:%d",
               x, y);
        __CrestClearStack(23);
      }
      }
    } else {
      __CrestBranch(11, 8, 0);
#line 25
      printf((char const   * __restrict  )"**** in: ! (x>0 && x<10) ****");
      __CrestClearStack(24);
    }
    }
  } else {
    __CrestBranch(6, 9, 0);
#line 25
    printf((char const   * __restrict  )"**** in: ! (x>0 && x<10) ****");
    __CrestClearStack(25);
  }
  __CrestLoad(28, (unsigned long )(& x), (long long )x);
  __CrestLoad(27, (unsigned long )0, (long long )10);
  __CrestApply2(26, 17, (long long )(x >= 10));
#line 28
  if (x >= 10) {
    __CrestBranch(29, 11, 1);
    {
    __CrestLoad(33, (unsigned long )(& z), (long long )z);
    __CrestLoad(32, (unsigned long )0, (long long )100);
    __CrestApply2(31, 15, (long long )(z <= 100));
#line 28
    if (z <= 100) {
      __CrestBranch(34, 12, 1);
#line 29
      printf((char const   * __restrict  )"$$$$$$$$$$ in: x>0 && z<=10 ****");
      __CrestClearStack(36);
      {
      __CrestLoad(39, (unsigned long )(& z), (long long )z);
      __CrestLoad(38, (unsigned long )0, (long long )5);
      __CrestApply2(37, 16, (long long )(z < 5));
#line 30
      if (z < 5) {
        __CrestBranch(40, 14, 1);
#line 31
        printf((char const   * __restrict  )"$$$$$$$$$$ z<5");
        __CrestClearStack(42);
      } else {
        __CrestBranch(41, 15, 0);
#line 34
        printf((char const   * __restrict  )"$$$$$$$$$$ in: z>=5");
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
#line 38
  __retres4 = 0;
  __CrestLoad(46, (unsigned long )(& __retres4), (long long )__retres4);
  __CrestReturn(47);
#line 7
  return (__retres4);
}
}
void __globinit_mytest(void) 
{ 


  {
  __CrestInit();
}
}
