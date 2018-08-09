# 1 "./t2.cil.c"
# 1 "<built-in>"
# 1 "<command-line>"
# 1 "/usr/include/stdc-predef.h" 1 3 4
# 1 "<command-line>" 2
# 1 "./t2.cil.c"
# 7 "t2.c"
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
# 202 "./../include/crest.h"
extern void __CrestInt(int *x ) __attribute__((__crest_skip__)) ;
# 7 "t2.c"
int main(void)
{
  int desiredLevel ;
  int temprature ;
  int HighTemps ;
  int DesiredLevel ;
  int Temprature ;
  int __retres6 ;

  {
  __globinit_t2();
  __CrestCall(1, 1);
# 12 "t2.c"
  __CrestInt(& desiredLevel);
# 13 "t2.c"
  __CrestInt(& temprature);
  __CrestLoad(4, (unsigned long )(& HighTemps), (long long )HighTemps);
  __CrestLoad(3, (unsigned long )0, (long long )9);
  __CrestApply2(2, 15, (long long )(HighTemps <= 9));
# 15 "t2.c"
  if (HighTemps <= 9) {
    __CrestBranch(5, 3, 1);
    {
    __CrestLoad(9, (unsigned long )(& desiredLevel), (long long )desiredLevel);
    __CrestLoad(8, (unsigned long )0, (long long )0);
    __CrestApply2(7, 14, (long long )(desiredLevel > 0));
# 16 "t2.c"
    if (desiredLevel > 0) {
      __CrestBranch(10, 4, 1);
      __CrestLoad(12, (unsigned long )(& desiredLevel), (long long )desiredLevel);
      __CrestStore(13, (unsigned long )(& DesiredLevel));
# 17 "t2.c"
      DesiredLevel = desiredLevel;
    } else {
      __CrestBranch(11, 5, 0);

    }
    }
    {
    __CrestLoad(16, (unsigned long )(& temprature), (long long )temprature);
    __CrestLoad(15, (unsigned long )0, (long long )0);
    __CrestApply2(14, 14, (long long )(temprature > 0));
# 19 "t2.c"
    if (temprature > 0) {
      __CrestBranch(17, 7, 1);
      __CrestLoad(19, (unsigned long )(& temprature), (long long )temprature);
      __CrestStore(20, (unsigned long )(& Temprature));
# 20 "t2.c"
      Temprature = temprature;
      {
      __CrestLoad(23, (unsigned long )(& Temprature), (long long )Temprature);
      __CrestLoad(22, (unsigned long )0, (long long )90);
      __CrestApply2(21, 17, (long long )(Temprature >= 90));
# 21 "t2.c"
      if (Temprature >= 90) {
        __CrestBranch(24, 9, 1);
        __CrestLoad(28, (unsigned long )(& HighTemps), (long long )HighTemps);
        __CrestLoad(27, (unsigned long )0, (long long )1);
        __CrestApply2(26, 0, (long long )(HighTemps + 1));
        __CrestStore(29, (unsigned long )(& HighTemps));
# 22 "t2.c"
        HighTemps ++;
      } else {
        __CrestBranch(25, 10, 0);

      }
      }
    } else {
      __CrestBranch(18, 11, 0);

    }
    }
  } else {
    __CrestBranch(6, 12, 0);

  }
  __CrestLoad(30, (unsigned long )0, (long long )0);
  __CrestStore(31, (unsigned long )(& __retres6));
# 28 "t2.c"
  __retres6 = 0;
  __CrestLoad(32, (unsigned long )(& __retres6), (long long )__retres6);
  __CrestReturn(33);
# 7 "t2.c"
  return (__retres6);
}
}
void __globinit_t2(void)
{


  {
  __CrestInit();
}
}
