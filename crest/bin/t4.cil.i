# 1 "./t4.cil.c"
# 1 "<built-in>"
# 1 "<command-line>"
# 1 "/usr/include/stdc-predef.h" 1 3 4
# 1 "<command-line>" 2
# 1 "./t4.cil.c"
# 8 "../test/t4.c"
void __globinit_t4(void) ;
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
# 8 "../test/t4.c"
int main(void)
{
  int amount ;
  int tmp ;
  int __retres3 ;

  {
  __globinit_t4();
  __CrestCall(1, 1);
  __CrestLoad(2, (unsigned long )0, (long long )10);
  __CrestStore(3, (unsigned long )(& tmp));
# 9 "../test/t4.c"
  tmp = 10;
# 11 "../test/t4.c"
  __CrestInt(& amount);
  __CrestLoad(6, (unsigned long )(& amount), (long long )amount);
  __CrestLoad(5, (unsigned long )0, (long long )0);
  __CrestApply2(4, 17, (long long )(amount >= 0));
# 15 "../test/t4.c"
  if (amount >= 0) {
    __CrestBranch(7, 63, 1);
    {
    __CrestLoad(11, (unsigned long )(& amount), (long long )amount);
    __CrestLoad(10, (unsigned long )0, (long long )5);
    __CrestApply2(9, 16, (long long )(amount < 5));
# 15 "../test/t4.c"
    if (amount < 5) {
      __CrestBranch(12, 64, 1);
# 16 "../test/t4.c"
      printf((char const * __restrict )"----branch visited: amount>=0 && amount<5 ----\n");
      __CrestClearStack(14);
      __CrestLoad(17, (unsigned long )(& tmp), (long long )tmp);
      __CrestLoad(16, (unsigned long )(& amount), (long long )amount);
      __CrestApply2(15, 2, (long long )(tmp * amount));
      __CrestStore(18, (unsigned long )(& tmp));
# 17 "../test/t4.c"
      tmp *= amount;
    } else {
      __CrestBranch(13, 65, 0);
# 20 "../test/t4.c"
      printf((char const * __restrict )"----branch visited: !(amount>=0 && amount<5)----\n");
      __CrestClearStack(19);
    }
    }
  } else {
    __CrestBranch(8, 66, 0);
# 20 "../test/t4.c"
    printf((char const * __restrict )"----branch visited: !(amount>=0 && amount<5)----\n");
    __CrestClearStack(20);
  }
  __CrestLoad(23, (unsigned long )(& tmp), (long long )tmp);
  __CrestLoad(22, (unsigned long )0, (long long )10);
  __CrestApply2(21, 14, (long long )(tmp > 10));
# 22 "../test/t4.c"
  if (tmp > 10) {
    __CrestBranch(24, 68, 1);
    {
    __CrestLoad(28, (unsigned long )(& tmp), (long long )tmp);
    __CrestLoad(27, (unsigned long )0, (long long )50);
    __CrestApply2(26, 16, (long long )(tmp < 50));
# 22 "../test/t4.c"
    if (tmp < 50) {
      __CrestBranch(29, 69, 1);
# 23 "../test/t4.c"
      printf((char const * __restrict )"----branch visited: tmp>10 && tmp<50----\n");
      __CrestClearStack(31);
    } else {
      __CrestBranch(30, 70, 0);
# 26 "../test/t4.c"
      printf((char const * __restrict )"----branch visited: !tmp>10 && tmp<50----\n");
      __CrestClearStack(32);
    }
    }
  } else {
    __CrestBranch(25, 71, 0);
# 26 "../test/t4.c"
    printf((char const * __restrict )"----branch visited: !tmp>10 && tmp<50----\n");
    __CrestClearStack(33);
  }
  __CrestLoad(34, (unsigned long )0, (long long )0);
  __CrestStore(35, (unsigned long )(& __retres3));
# 28 "../test/t4.c"
  __retres3 = 0;
  __CrestLoad(36, (unsigned long )(& __retres3), (long long )__retres3);
  __CrestReturn(37);
# 8 "../test/t4.c"
  return (__retres3);
}
}
void __globinit_t4(void)
{


  {
  __CrestInit();
}
}
