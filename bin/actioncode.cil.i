# 1 "./actioncode.cil.c"
# 1 "<built-in>"
# 1 "<command-line>"
# 1 "./actioncode.cil.c"
# 3 "/tmp/mcute/actioncode.c"
void __globinit_actioncode(void) ;
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
# 206 "/home/vagrant/mcute/bin/../crest/include/crest.h"
extern void __CrestInt(int *x ) __attribute__((__crest_skip__)) ;
# 363 "/usr/include/stdio.h"
extern int printf(char const * __restrict __format , ...) ;
# 3 "/tmp/mcute/actioncode.c"
void main(void)
{
  int p1 ;
  int p2 ;

  {
  __globinit_actioncode();
  __CrestCall(1, 1);
# 4 "/tmp/mcute/actioncode.c"
  __CrestInt(& p1);
# 4 "/tmp/mcute/actioncode.c"
  __CrestInt(& p2);
  __CrestLoad(4, (unsigned long )(& p2), (long long )p2);
  __CrestLoad(3, (unsigned long )0, (long long )0);
  __CrestApply2(2, 14, (long long )(p2 > 0));
# 4 "/tmp/mcute/actioncode.c"
  if (p2 > 0) {
    __CrestBranch(5, 303, 1);
# 4 "/tmp/mcute/actioncode.c"
    goto _L;
  } else {
    __CrestBranch(6, 304, 0);
    {
    __CrestLoad(9, (unsigned long )(& p2), (long long )p2);
    __CrestLoad(8, (unsigned long )0, (long long )-1);
    __CrestApply2(7, 16, (long long )(p2 < -1));
# 4 "/tmp/mcute/actioncode.c"
    if (p2 < -1) {
      __CrestBranch(10, 305, 1);
# 4 "/tmp/mcute/actioncode.c"
      goto _L;
    } else {
      __CrestBranch(11, 306, 0);
      {
      __CrestLoad(14, (unsigned long )(& p2), (long long )p2);
      __CrestLoad(13, (unsigned long )0, (long long )2000);
      __CrestApply2(12, 17, (long long )(p2 >= 2000));
# 4 "/tmp/mcute/actioncode.c"
      if (p2 >= 2000) {
        __CrestBranch(15, 307, 1);
        _L:
# 5 "/tmp/mcute/actioncode.c"
        printf((char const * __restrict )"\n(p2>0 || p2<-1 || p2>=2000)");
        __CrestClearStack(17);
        {
        __CrestLoad(22, (unsigned long )(& p2), (long long )p2);
        __CrestLoad(21, (unsigned long )0, (long long )2);
        __CrestApply2(20, 2, (long long )(p2 * 2));
        __CrestLoad(19, (unsigned long )0, (long long )1000);
        __CrestApply2(18, 14, (long long )(p2 * 2 > 1000));
# 7 "/tmp/mcute/actioncode.c"
        if (p2 * 2 > 1000) {
          __CrestBranch(23, 309, 1);
          {
          __CrestLoad(27, (unsigned long )(& p1), (long long )p1);
          __CrestLoad(26, (unsigned long )0, (long long )0);
          __CrestApply2(25, 14, (long long )(p1 > 0));
# 7 "/tmp/mcute/actioncode.c"
          if (p1 > 0) {
            __CrestBranch(28, 310, 1);
            {
            __CrestLoad(32, (unsigned long )(& p1), (long long )p1);
            __CrestLoad(31, (unsigned long )(& p2), (long long )p2);
            __CrestApply2(30, 12, (long long )(p1 == p2));
# 7 "/tmp/mcute/actioncode.c"
            if (p1 == p2) {
              __CrestBranch(33, 311, 1);
              {
              __CrestLoad(37, (unsigned long )(& p1), (long long )p1);
              __CrestLoad(36, (unsigned long )0, (long long )50000);
              __CrestApply2(35, 16, (long long )(p1 < 50000));
# 8 "/tmp/mcute/actioncode.c"
              if (p1 < 50000) {
                __CrestBranch(38, 312, 1);
# 9 "/tmp/mcute/actioncode.c"
                printf((char const * __restrict )"\ndevision by zero here!\n");
                __CrestClearStack(40);
# 11 "/tmp/mcute/actioncode.c"
                printf((char const * __restrict )"p1=%d, p2=%d");
                __CrestClearStack(41);
# 13 "/tmp/mcute/actioncode.c"
                printf((char const * __restrict )"some basic math: p2 = 200 / (p1-p2)");
                __CrestClearStack(42);
                __CrestLoad(47, (unsigned long )0, (long long )200);
                __CrestLoad(46, (unsigned long )(& p1), (long long )p1);
                __CrestLoad(45, (unsigned long )(& p2), (long long )p2);
                __CrestApply2(44, 1, (long long )(p1 - p2));
                __CrestApply2(43, 3, (long long )(200 / (p1 - p2)));
                __CrestStore(48, (unsigned long )(& p2));
# 15 "/tmp/mcute/actioncode.c"
                p2 = 200 / (p1 - p2);
# 17 "/tmp/mcute/actioncode.c"
                printf((char const * __restrict )"basic math done!\n");
                __CrestClearStack(49);
              } else {
                __CrestBranch(39, 313, 0);

              }
              }
            } else {
              __CrestBranch(34, 314, 0);

            }
            }
          } else {
            __CrestBranch(29, 315, 0);

          }
          }
        } else {
          __CrestBranch(24, 316, 0);

        }
        }
      } else {
        __CrestBranch(16, 317, 0);

      }
      }
    }
    }
  }
  __CrestReturn(50);
# 3 "/tmp/mcute/actioncode.c"
  return;
}
}
void __globinit_actioncode(void)
{


  {
  __CrestInit();
}
}
