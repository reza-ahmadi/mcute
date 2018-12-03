# 1 "./test_func.cil.c"
# 1 "<built-in>"
# 1 "<command-line>"
# 1 "/usr/include/stdc-predef.h" 1 3 4
# 1 "<command-line>" 2
# 1 "./test_func.cil.c"
# 7 "../test/test_func.c"
void __globinit_test_func(void) ;
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
# 3 "../test/test_func.h"
int getMax(int a , int b )
{
  int __retres3 ;

  {
  __CrestCall(3, 1);
  __CrestStore(2, (unsigned long )(& b));
  __CrestStore(1, (unsigned long )(& a));
  {
  __CrestLoad(6, (unsigned long )(& a), (long long )a);
  __CrestLoad(5, (unsigned long )(& b), (long long )b);
  __CrestApply2(4, 14, (long long )(a > b));
# 4 "../test/test_func.h"
  if (a > b) {
    __CrestBranch(7, 2, 1);
    __CrestLoad(9, (unsigned long )(& a), (long long )a);
    __CrestStore(10, (unsigned long )(& __retres3));
# 4 "../test/test_func.h"
    __retres3 = a;
# 4 "../test/test_func.h"
    goto return_label;
  } else {
    __CrestBranch(8, 4, 0);

  }
  }
  __CrestLoad(11, (unsigned long )(& b), (long long )b);
  __CrestStore(12, (unsigned long )(& __retres3));
# 5 "../test/test_func.h"
  __retres3 = b;
  return_label:
  {
  __CrestLoad(13, (unsigned long )(& __retres3), (long long )__retres3);
  __CrestReturn(14);
# 3 "../test/test_func.h"
  return (__retres3);
  }
}
}
# 7 "../test/test_func.c"
int main(void)
{
  int x ;
  int y ;
  int z ;
  int max ;
  int tmp ;
  int __retres6 ;

  {
  __globinit_test_func();
  __CrestCall(15, 2);
# 11 "../test/test_func.c"
  __CrestInt(& x);
# 12 "../test/test_func.c"
  __CrestInt(& y);
  __CrestLoad(16, (unsigned long )(& x), (long long )x);
  __CrestLoad(17, (unsigned long )(& y), (long long )y);
# 15 "../test/test_func.c"
  tmp = getMax(x, y);
  __CrestHandleReturn(19, (long long )tmp);
  __CrestStore(18, (unsigned long )(& tmp));
  __CrestLoad(20, (unsigned long )(& tmp), (long long )tmp);
  __CrestStore(21, (unsigned long )(& max));
# 15 "../test/test_func.c"
  max = tmp;
  __CrestLoad(24, (unsigned long )(& x), (long long )x);
  __CrestLoad(23, (unsigned long )(& max), (long long )max);
  __CrestApply2(22, 12, (long long )(x == max));
# 17 "../test/test_func.c"
  if (x == max) {
    __CrestBranch(25, 9, 1);
    __CrestLoad(27, (unsigned long )(& x), (long long )x);
    __CrestStore(28, (unsigned long )(& z));
# 18 "../test/test_func.c"
    z = x;
# 19 "../test/test_func.c"
    printf((char const * __restrict )"x > y\n");
    __CrestClearStack(29);
    {
    __CrestLoad(34, (unsigned long )(& x), (long long )x);
    __CrestLoad(33, (unsigned long )(& y), (long long )y);
    __CrestApply2(32, 1, (long long )(x - y));
    __CrestLoad(31, (unsigned long )0, (long long )100);
    __CrestApply2(30, 14, (long long )(x - y > 100));
# 20 "../test/test_func.c"
    if (x - y > 100) {
      __CrestBranch(35, 11, 1);
# 21 "../test/test_func.c"
      printf((char const * __restrict )"x-y > 100\n");
      __CrestClearStack(37);
    } else {
      __CrestBranch(36, 12, 0);

    }
    }
  } else {
    __CrestBranch(26, 13, 0);
    __CrestLoad(38, (unsigned long )(& y), (long long )y);
    __CrestStore(39, (unsigned long )(& z));
# 25 "../test/test_func.c"
    z = y;
# 26 "../test/test_func.c"
    printf((char const * __restrict )"x <= y\n");
    __CrestClearStack(40);
  }
  __CrestLoad(41, (unsigned long )(& x), (long long )x);
  __CrestLoad(42, (unsigned long )(& y), (long long )y);
  __CrestLoad(43, (unsigned long )(& z), (long long )z);
# 29 "../test/test_func.c"
  printf((char const * __restrict )"x=%d, y=%d, z=%d\n", x, y, z);
  __CrestClearStack(44);
  __CrestLoad(45, (unsigned long )0, (long long )0);
  __CrestStore(46, (unsigned long )(& __retres6));
# 31 "../test/test_func.c"
  __retres6 = 0;
  __CrestLoad(47, (unsigned long )(& __retres6), (long long )__retres6);
  __CrestReturn(48);
# 7 "../test/test_func.c"
  return (__retres6);
}
}
void __globinit_test_func(void)
{


  {
  __CrestInit();
}
}
