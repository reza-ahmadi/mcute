/* Generated by CIL v. 1.7.3 */
/* print_CIL_Input is true */

#line 7 "../test/t2.c"
void __globinit_t2(void) ;
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
#line 206 "./../include/crest.h"
extern void __CrestInt(int *x )  __attribute__((__crest_skip__)) ;
#line 362 "/usr/include/stdio.h"
extern int printf(char const   * __restrict  __format  , ...) ;
#line 7 "../test/t2.c"
void main(void)
{
  int tank1 ;
  int tank2 ;
  int solution ;
  int drain ;
  int Warnings ;

  {
  __globinit_t2();
  __CrestCall(1, 1);
#line 13
  __CrestInt(& tank1);
#line 14
  __CrestInt(& tank2);
#line 15
  __CrestInt(& solution);
#line 16
  __CrestInt(& drain);
  __CrestLoad(4, (unsigned long )(& tank1), (long long )tank1);
  __CrestLoad(3, (unsigned long )0, (long long )0);
  __CrestApply2(2, 12, (long long )(tank1 == 0));
#line 18
  if (tank1 == 0) {
    printf((char const   * __restrict  )"----branch visited: tank1==0 ----");
    __CrestBranch(5, 103, 1);
    {
    __CrestLoad(9, (unsigned long )(& tank2), (long long )tank2);
    __CrestLoad(8, (unsigned long )0, (long long )0);
    __CrestApply2(7, 12, (long long )(tank2 == 0));
#line 18
    if (tank2 == 0) {
      __CrestBranch(10, 104, 1);
#line 19
      printf((char const   * __restrict  )"----branch visited: tank2 == 0 ----");
      __CrestClearStack(12);
      {
      __CrestLoad(15, (unsigned long )(& Warnings), (long long )Warnings);
      __CrestLoad(14, (unsigned long )0, (long long )1);
      __CrestApply2(13, 14, (long long )(Warnings > 1));
#line 20
      if (Warnings > 1) {
        printf((char const   * __restrict  )"----branch visited: Warnings>1 ----");
        __CrestBranch(16, 106, 1);
        {
        __CrestLoad(20, (unsigned long )(& Warnings), (long long )Warnings);
        __CrestLoad(19, (unsigned long )0, (long long )5);
        __CrestApply2(18, 16, (long long )(Warnings < 5));
#line 20
        if (Warnings < 5) {
          __CrestBranch(21, 107, 1);
#line 21
          printf((char const   * __restrict  )"----branch visited: Warnings<5 ----");
          __CrestClearStack(23);
        } else {
          printf((char const   * __restrict  )"----branch visited: !(Warnings<5) ----");
          __CrestBranch(22, 108, 0);
#line 23

          __CrestClearStack(24);
        }
        }
      } else {
        __CrestBranch(17, 109, 0);
#line 23
        printf((char const   * __restrict  )"----branch visited: !(Warnings>1) ----");
        __CrestClearStack(25);
      }
      }
      {
      __CrestLoad(28, (unsigned long )(& solution), (long long )solution);
      __CrestLoad(27, (unsigned long )0, (long long )0);
      __CrestApply2(26, 14, (long long )(solution > 0));
#line 25
      if (solution > 0) {
        __CrestBranch(29, 111, 1);
#line 26
        printf((char const   * __restrict  )"----branch visited: solution>0 ----");
        __CrestClearStack(31);
      } else {
        __CrestBranch(30, 112, 0);
#line 28
        printf((char const   * __restrict  )"----branch visited: !(solution>0) ----");
        __CrestClearStack(32);
      }
      }
    } else {
      __CrestBranch(11, 113, 0);
      printf((char const   * __restrict  )"----branch visited: !(tank2 == 0) ----");

#line 18
      goto _L;
    }
    }
  } else {
    __CrestBranch(6, 114, 0);
    _L: /* CIL Label */
#line 32
    printf((char const   * __restrict  )"----branch visited: !(tank1==0) ----");
    __CrestClearStack(33);
    {
    __CrestLoad(36, (unsigned long )(& drain), (long long )drain);
    __CrestLoad(35, (unsigned long )0, (long long )0);
    __CrestApply2(34, 14, (long long )(drain > 0));
#line 33
    if (drain > 0) {
      __CrestBranch(37, 116, 1);
#line 34
      printf((char const   * __restrict  )"----branch visited: drain>0 ----");
      __CrestClearStack(39);
    } else {
      __CrestBranch(38, 117, 0);
printf((char const   * __restrict  )"----branch visited: !(drain>0) ----");
    }
    }
  }
  __CrestReturn(40);
#line 7
  return;
}
}
void __globinit_t2(void)
{


  {
  __CrestInit();
}
}
