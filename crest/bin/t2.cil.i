# 1 "./t2.cil.c"
# 1 "<built-in>"
# 1 "<command-line>"
# 1 "/usr/include/stdc-predef.h" 1 3 4
# 1 "<command-line>" 2
# 1 "./t2.cil.c"
# 7 "../test/t2.c"
void __globinit_t2(void) ;
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
# 7 "../test/t2.c"
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
# 13 "../test/t2.c"
  __CrestInt(& tank1);
# 14 "../test/t2.c"
  __CrestInt(& tank2);
# 15 "../test/t2.c"
  __CrestInt(& solution);
# 16 "../test/t2.c"
  __CrestInt(& drain);
  __CrestLoad(4, (unsigned long )(& tank1), (long long )tank1);
  __CrestLoad(3, (unsigned long )0, (long long )0);
  __CrestApply2(2, 12, (long long )(tank1 == 0));
# 18 "../test/t2.c"
  if (tank1 == 0) {
    __CrestBranch(5, 17, 1);
    {
    __CrestLoad(9, (unsigned long )(& tank2), (long long )tank2);
    __CrestLoad(8, (unsigned long )0, (long long )0);
    __CrestApply2(7, 12, (long long )(tank2 == 0));
# 18 "../test/t2.c"
    if (tank2 == 0) {
      __CrestBranch(10, 18, 1);
# 19 "../test/t2.c"
      printf((char const * __restrict )"----branch visited: tank1==0 && tank2 == 0 ----");
      __CrestClearStack(12);
      {
      __CrestLoad(15, (unsigned long )(& Warnings), (long long )Warnings);
      __CrestLoad(14, (unsigned long )0, (long long )1);
      __CrestApply2(13, 14, (long long )(Warnings > 1));
# 20 "../test/t2.c"
      if (Warnings > 1) {
        __CrestBranch(16, 20, 1);
        {
        __CrestLoad(20, (unsigned long )(& Warnings), (long long )Warnings);
        __CrestLoad(19, (unsigned long )0, (long long )5);
        __CrestApply2(18, 16, (long long )(Warnings < 5));
# 20 "../test/t2.c"
        if (Warnings < 5) {
          __CrestBranch(21, 21, 1);
# 21 "../test/t2.c"
          printf((char const * __restrict )"----branch visited: Warnings>1 && Warnings<5 ----");
          __CrestClearStack(23);
        } else {
          __CrestBranch(22, 22, 0);
# 23 "../test/t2.c"
          printf((char const * __restrict )"----branch visited: !(Warnings>1 && Warnings<5) ----");
          __CrestClearStack(24);
        }
        }
      } else {
        __CrestBranch(17, 23, 0);
# 23 "../test/t2.c"
        printf((char const * __restrict )"----branch visited: !(Warnings>1 && Warnings<5) ----");
        __CrestClearStack(25);
      }
      }
      {
      __CrestLoad(28, (unsigned long )(& solution), (long long )solution);
      __CrestLoad(27, (unsigned long )0, (long long )0);
      __CrestApply2(26, 14, (long long )(solution > 0));
# 25 "../test/t2.c"
      if (solution > 0) {
        __CrestBranch(29, 25, 1);
# 26 "../test/t2.c"
        printf((char const * __restrict )"----branch visited: solution>0 ----");
        __CrestClearStack(31);
      } else {
        __CrestBranch(30, 26, 0);
# 28 "../test/t2.c"
        printf((char const * __restrict )"----branch visited: !(solution>0) ----");
        __CrestClearStack(32);
      }
      }
    } else {
      __CrestBranch(11, 27, 0);
# 18 "../test/t2.c"
      goto _L;
    }
    }
  } else {
    __CrestBranch(6, 28, 0);
    _L:
# 32 "../test/t2.c"
    printf((char const * __restrict )"----branch visited: !(tank1==0 && tank2 == 0) ----");
    __CrestClearStack(33);
    {
    __CrestLoad(36, (unsigned long )(& drain), (long long )drain);
    __CrestLoad(35, (unsigned long )0, (long long )0);
    __CrestApply2(34, 14, (long long )(drain > 0));
# 33 "../test/t2.c"
    if (drain > 0) {
      __CrestBranch(37, 30, 1);
# 34 "../test/t2.c"
      printf((char const * __restrict )"----branch visited: drain>0 ----");
      __CrestClearStack(39);
    } else {
      __CrestBranch(38, 31, 0);

    }
    }
  }
  __CrestReturn(40);
# 7 "../test/t2.c"
  return;
}
}
void __globinit_t2(void)
{


  {
  __CrestInit();
}
}
