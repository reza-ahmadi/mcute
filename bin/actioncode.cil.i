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
  int p5 ;
  int p6 ;

  {
  __globinit_actioncode();
  __CrestCall(1, 1);
# 4 "/tmp/mcute/actioncode.c"
  __CrestInt(& p5);
# 4 "/tmp/mcute/actioncode.c"
  __CrestInt(& p6);
# 4 "/tmp/mcute/actioncode.c"
  printf((char const * __restrict )"MCUTESTART log.log(\"$transition t9 action code\"$) MCUTEEND");
  __CrestClearStack(2);
  __CrestLoad(5, (unsigned long )(& p5), (long long )p5);
  __CrestLoad(4, (unsigned long )0, (long long )623840763);
  __CrestApply2(3, 14, (long long )(p5 > 623840763));
# 5 "/tmp/mcute/actioncode.c"
  if (p5 > 623840763) {
    __CrestBranch(6, 1003, 1);
    {
    __CrestLoad(10, (unsigned long )(& p5), (long long )p5);
    __CrestLoad(9, (unsigned long )0, (long long )1605364139);
    __CrestApply2(8, 15, (long long )(p5 <= 1605364139));
# 5 "/tmp/mcute/actioncode.c"
    if (p5 <= 1605364139) {
      __CrestBranch(11, 1004, 1);
      __CrestLoad(15, (unsigned long )(& p5), (long long )p5);
      __CrestLoad(14, (unsigned long )0, (long long )623840763);
      __CrestApply2(13, 0, (long long )(p5 + 623840763));
      __CrestStore(16, (unsigned long )(& p5));
# 5 "/tmp/mcute/actioncode.c"
      p5 += 623840763;
      __CrestLoad(19, (unsigned long )(& p5), (long long )p5);
      __CrestLoad(18, (unsigned long )0, (long long )1605364139);
      __CrestApply2(17, 2, (long long )(p5 * 1605364139));
      __CrestStore(20, (unsigned long )(& p5));
# 6 "/tmp/mcute/actioncode.c"
      p5 *= 1605364139;
      {
      __CrestLoad(23, (unsigned long )(& p6), (long long )p6);
      __CrestLoad(22, (unsigned long )0, (long long )1492389488);
      __CrestApply2(21, 14, (long long )(p6 > 1492389488));
# 7 "/tmp/mcute/actioncode.c"
      if (p6 > 1492389488) {
        __CrestBranch(24, 1006, 1);
        {
        __CrestLoad(28, (unsigned long )(& p6), (long long )p6);
        __CrestLoad(27, (unsigned long )0, (long long )2118246845);
        __CrestApply2(26, 15, (long long )(p6 <= 2118246845));
# 7 "/tmp/mcute/actioncode.c"
        if (p6 <= 2118246845) {
          __CrestBranch(29, 1007, 1);
          __CrestLoad(33, (unsigned long )(& p6), (long long )p6);
          __CrestLoad(32, (unsigned long )0, (long long )1492389488);
          __CrestApply2(31, 0, (long long )(p6 + 1492389488));
          __CrestStore(34, (unsigned long )(& p6));
# 7 "/tmp/mcute/actioncode.c"
          p6 += 1492389488;
          __CrestLoad(37, (unsigned long )(& p6), (long long )p6);
          __CrestLoad(36, (unsigned long )0, (long long )2118246845);
          __CrestApply2(35, 2, (long long )(p6 * 2118246845));
          __CrestStore(38, (unsigned long )(& p6));
# 8 "/tmp/mcute/actioncode.c"
          p6 *= 2118246845;
        } else {
          __CrestBranch(30, 1008, 0);

        }
        }
      } else {
        __CrestBranch(25, 1009, 0);

      }
      }
    } else {
      __CrestBranch(12, 1010, 0);

    }
    }
  } else {
    __CrestBranch(7, 1011, 0);

  }
  __CrestReturn(39);
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
