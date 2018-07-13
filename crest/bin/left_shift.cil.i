# 1 "./left_shift.cil.c"
# 1 "<built-in>"
# 1 "<command-line>"
# 1 "/usr/include/stdc-predef.h" 1 3 4
# 1 "<command-line>" 2
# 1 "./left_shift.cil.c"
# 16 "../test/left_shift.c"
void __globinit_left_shift(void) ;
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
# 16 "../test/left_shift.c"
int main(void)
{
  int x ;
  int y ;
  int z ;
  int __retres4 ;

  {
  __globinit_left_shift();
  __CrestCall(1, 1);
# 19 "../test/left_shift.c"
  __CrestInt(& x);
# 20 "../test/left_shift.c"
  __CrestInt(& y);
# 21 "../test/left_shift.c"
  __CrestInt(& z);
  __CrestLoad(8, (unsigned long )(& x), (long long )x);
  __CrestLoad(7, (unsigned long )0, (long long )3);
  __CrestApply2(6, 0, (long long )(x + 3));
  __CrestLoad(5, (unsigned long )0, (long long )2);
  __CrestApply2(4, 8, (long long )((x + 3) << 2));
  __CrestLoad(3, (unsigned long )0, (long long )96);
  __CrestApply2(2, 12, (long long )((x + 3) << 2 == 96));
# 23 "../test/left_shift.c"
  if ((x + 3) << 2 == 96) {
    __CrestBranch(9, 3, 1);
# 24 "../test/left_shift.c"
    printf((char const * __restrict )"A\n");
    __CrestClearStack(11);
    {
    __CrestLoad(18, (unsigned long )(& y), (long long )y);
    __CrestLoad(17, (unsigned long )0, (long long )15);
    __CrestApply2(16, 1, (long long )(y - 15));
    __CrestLoad(15, (unsigned long )(& x), (long long )x);
    __CrestApply2(14, 8, (long long )((y - 15) << x));
    __CrestLoad(13, (unsigned long )(& z), (long long )z);
    __CrestApply2(12, 12, (long long )((y - 15) << x == z));
# 25 "../test/left_shift.c"
    if ((y - 15) << x == z) {
      __CrestBranch(19, 5, 1);
# 26 "../test/left_shift.c"
      printf((char const * __restrict )"B\n");
      __CrestClearStack(21);
    } else {
      __CrestBranch(20, 6, 0);
# 28 "../test/left_shift.c"
      printf((char const * __restrict )"C\n");
      __CrestClearStack(22);
    }
    }
  } else {
    __CrestBranch(10, 7, 0);

  }
  __CrestLoad(23, (unsigned long )0, (long long )0);
  __CrestStore(24, (unsigned long )(& __retres4));
# 32 "../test/left_shift.c"
  __retres4 = 0;
  __CrestLoad(25, (unsigned long )(& __retres4), (long long )__retres4);
  __CrestReturn(26);
# 16 "../test/left_shift.c"
  return (__retres4);
}
}
void __globinit_left_shift(void)
{


  {
  __CrestInit();
}
}
