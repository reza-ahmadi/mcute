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
  int p3 ;

  {
  __globinit_actioncode();
  __CrestCall(1, 1);
# 4 "/tmp/mcute/actioncode.c"
  __CrestInt(& p1);
# 4 "/tmp/mcute/actioncode.c"
  __CrestInt(& p3);
  __CrestLoad(4, (unsigned long )(& p1), (long long )p1);
  __CrestLoad(3, (unsigned long )0, (long long )100);
  __CrestApply2(2, 14, (long long )(p1 > 100));
# 4 "/tmp/mcute/actioncode.c"
  if (p1 > 100) {
    __CrestBranch(5, 503, 1);
    {
    __CrestLoad(9, (unsigned long )(& p1), (long long )p1);
    __CrestLoad(8, (unsigned long )0, (long long )104);
    __CrestApply2(7, 16, (long long )(p1 < 104));
# 4 "/tmp/mcute/actioncode.c"
    if (p1 < 104) {
      __CrestBranch(10, 504, 1);
      {
      __CrestLoad(14, (unsigned long )(& p3), (long long )p3);
      __CrestLoad(13, (unsigned long )0, (long long )0);
      __CrestApply2(12, 14, (long long )(p3 > 0));
# 4 "/tmp/mcute/actioncode.c"
      if (p3 > 0) {
        __CrestBranch(15, 505, 1);
        {
        __CrestLoad(19, (unsigned long )(& p3), (long long )p3);
        __CrestLoad(18, (unsigned long )0, (long long )13);
        __CrestApply2(17, 16, (long long )(p3 < 13));
# 4 "/tmp/mcute/actioncode.c"
        if (p3 < 13) {
          __CrestBranch(20, 506, 1);
# 5 "/tmp/mcute/actioncode.c"
          printf((char const * __restrict )"\n(p1>100 && p1<104 && p3>0 && p3<13)\n");
          __CrestClearStack(22);
          {
          __CrestLoad(27, (unsigned long )(& p1), (long long )p1);
          __CrestLoad(26, (unsigned long )(& p3), (long long )p3);
          __CrestApply2(25, 0, (long long )(p1 + p3));
          __CrestLoad(24, (unsigned long )0, (long long )111);
          __CrestApply2(23, 12, (long long )(p1 + p3 == 111));
# 8 "/tmp/mcute/actioncode.c"
          if (p1 + p3 == 111) {
            __CrestBranch(28, 508, 1);
# 9 "/tmp/mcute/actioncode.c"
            printf((char const * __restrict )"\np1+p3==111!\n");
            __CrestClearStack(30);
          } else {
            __CrestBranch(29, 509, 0);
            {
            __CrestLoad(35, (unsigned long )(& p1), (long long )p1);
            __CrestLoad(34, (unsigned long )(& p3), (long long )p3);
            __CrestApply2(33, 0, (long long )(p1 + p3));
            __CrestLoad(32, (unsigned long )0, (long long )112);
            __CrestApply2(31, 12, (long long )(p1 + p3 == 112));
# 12 "/tmp/mcute/actioncode.c"
            if (p1 + p3 == 112) {
              __CrestBranch(36, 510, 1);
# 13 "/tmp/mcute/actioncode.c"
              printf((char const * __restrict )"\np1+p3==112!\n");
              __CrestClearStack(38);
            } else {
              __CrestBranch(37, 511, 0);

            }
            }
          }
          }
        } else {
          __CrestBranch(21, 512, 0);
# 17 "/tmp/mcute/actioncode.c"
          printf((char const * __restrict )"\n!(p1>100 && p1<104 && p3>0 && p3<13)\n");
          __CrestClearStack(39);
        }
        }
      } else {
        __CrestBranch(16, 513, 0);
# 17 "/tmp/mcute/actioncode.c"
        printf((char const * __restrict )"\n!(p1>100 && p1<104 && p3>0 && p3<13)\n");
        __CrestClearStack(40);
      }
      }
    } else {
      __CrestBranch(11, 514, 0);
# 17 "/tmp/mcute/actioncode.c"
      printf((char const * __restrict )"\n!(p1>100 && p1<104 && p3>0 && p3<13)\n");
      __CrestClearStack(41);
    }
    }
  } else {
    __CrestBranch(6, 515, 0);
# 17 "/tmp/mcute/actioncode.c"
    printf((char const * __restrict )"\n!(p1>100 && p1<104 && p3>0 && p3<13)\n");
    __CrestClearStack(42);
  }
  __CrestReturn(43);
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
