# 1 "./randomAc.cil.c"
# 1 "<built-in>"
# 1 "<command-line>"
# 1 "/usr/include/stdc-predef.h" 1 3 4
# 1 "<command-line>" 2
# 1 "./randomAc.cil.c"
# 3 "../test/randomAc.c"
void __globinit_randomAc(void) ;
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
# 3 "../test/randomAc.c"
int main(void)
{
  int p1 ;
  int CopyOf_p1_1 ;
  int CopyOf_p1_2 ;
  int CopyOf_p1_3 ;
  int CopyOf_p1_4 ;
  int __retres6 ;

  {
  __globinit_randomAc();
  __CrestCall(1, 1);
# 8 "../test/randomAc.c"
  __CrestInt(& p1);
# 9 "../test/randomAc.c"
  __CrestInt(& CopyOf_p1_1);
# 10 "../test/randomAc.c"
  __CrestInt(& CopyOf_p1_2);
# 11 "../test/randomAc.c"
  __CrestInt(& CopyOf_p1_3);
# 12 "../test/randomAc.c"
  __CrestInt(& CopyOf_p1_4);
  __CrestLoad(4, (unsigned long )(& p1), (long long )p1);
  __CrestLoad(3, (unsigned long )0, (long long )945243205);
  __CrestApply2(2, 14, (long long )(p1 > 945243205));
# 14 "../test/randomAc.c"
  if (p1 > 945243205) {
    __CrestBranch(5, 2003, 1);
    {
    __CrestLoad(9, (unsigned long )(& p1), (long long )p1);
    __CrestLoad(8, (unsigned long )0, (long long )985125732);
    __CrestApply2(7, 15, (long long )(p1 <= 985125732));
# 14 "../test/randomAc.c"
    if (p1 <= 985125732) {
      __CrestBranch(10, 2004, 1);
      __CrestLoad(14, (unsigned long )(& p1), (long long )p1);
      __CrestLoad(13, (unsigned long )0, (long long )945243205);
      __CrestApply2(12, 0, (long long )(p1 + 945243205));
      __CrestStore(15, (unsigned long )(& p1));
# 15 "../test/randomAc.c"
      p1 += 945243205;
      __CrestLoad(18, (unsigned long )(& p1), (long long )p1);
      __CrestLoad(17, (unsigned long )0, (long long )985125732);
      __CrestApply2(16, 2, (long long )(p1 * 985125732));
      __CrestStore(19, (unsigned long )(& p1));
# 16 "../test/randomAc.c"
      p1 *= 985125732;
      {
      __CrestLoad(22, (unsigned long )(& CopyOf_p1_1), (long long )CopyOf_p1_1);
      __CrestLoad(21, (unsigned long )0, (long long )37265035);
      __CrestApply2(20, 14, (long long )(CopyOf_p1_1 > 37265035));
# 17 "../test/randomAc.c"
      if (CopyOf_p1_1 > 37265035) {
        __CrestBranch(23, 2006, 1);
        {
        __CrestLoad(27, (unsigned long )(& CopyOf_p1_1), (long long )CopyOf_p1_1);
        __CrestLoad(26, (unsigned long )0, (long long )106224505);
        __CrestApply2(25, 15, (long long )(CopyOf_p1_1 <= 106224505));
# 17 "../test/randomAc.c"
        if (CopyOf_p1_1 <= 106224505) {
          __CrestBranch(28, 2007, 1);
          __CrestLoad(32, (unsigned long )(& CopyOf_p1_1), (long long )CopyOf_p1_1);
          __CrestLoad(31, (unsigned long )0, (long long )37265035);
          __CrestApply2(30, 0, (long long )(CopyOf_p1_1 + 37265035));
          __CrestStore(33, (unsigned long )(& CopyOf_p1_1));
# 18 "../test/randomAc.c"
          CopyOf_p1_1 += 37265035;
          __CrestLoad(36, (unsigned long )(& CopyOf_p1_1), (long long )CopyOf_p1_1);
          __CrestLoad(35, (unsigned long )0, (long long )106224505);
          __CrestApply2(34, 2, (long long )(CopyOf_p1_1 * 106224505));
          __CrestStore(37, (unsigned long )(& CopyOf_p1_1));
# 19 "../test/randomAc.c"
          CopyOf_p1_1 *= 106224505;
          {
          __CrestLoad(40, (unsigned long )(& CopyOf_p1_2), (long long )CopyOf_p1_2);
          __CrestLoad(39, (unsigned long )0, (long long )974363458);
          __CrestApply2(38, 14, (long long )(CopyOf_p1_2 > 974363458));
# 20 "../test/randomAc.c"
          if (CopyOf_p1_2 > 974363458) {
            __CrestBranch(41, 2009, 1);
            {
            __CrestLoad(45, (unsigned long )(& CopyOf_p1_2), (long long )CopyOf_p1_2);
            __CrestLoad(44, (unsigned long )0, (long long )1444801314);
            __CrestApply2(43, 15, (long long )(CopyOf_p1_2 <= 1444801314));
# 20 "../test/randomAc.c"
            if (CopyOf_p1_2 <= 1444801314) {
              __CrestBranch(46, 2010, 1);
              __CrestLoad(50, (unsigned long )(& CopyOf_p1_2), (long long )CopyOf_p1_2);
              __CrestLoad(49, (unsigned long )0, (long long )974363458);
              __CrestApply2(48, 0, (long long )(CopyOf_p1_2 + 974363458));
              __CrestStore(51, (unsigned long )(& CopyOf_p1_2));
# 21 "../test/randomAc.c"
              CopyOf_p1_2 += 974363458;
              __CrestLoad(54, (unsigned long )(& CopyOf_p1_2), (long long )CopyOf_p1_2);
              __CrestLoad(53, (unsigned long )0, (long long )1444801314);
              __CrestApply2(52, 2, (long long )(CopyOf_p1_2 * 1444801314));
              __CrestStore(55, (unsigned long )(& CopyOf_p1_2));
# 22 "../test/randomAc.c"
              CopyOf_p1_2 *= 1444801314;
              {
              __CrestLoad(58, (unsigned long )(& CopyOf_p1_3), (long long )CopyOf_p1_3);
              __CrestLoad(57, (unsigned long )0, (long long )624222804);
              __CrestApply2(56, 14, (long long )(CopyOf_p1_3 > 624222804));
# 23 "../test/randomAc.c"
              if (CopyOf_p1_3 > 624222804) {
                __CrestBranch(59, 2012, 1);
                {
                __CrestLoad(63, (unsigned long )(& CopyOf_p1_3), (long long )CopyOf_p1_3);
                __CrestLoad(62, (unsigned long )0, (long long )701649992);
                __CrestApply2(61, 15, (long long )(CopyOf_p1_3 <= 701649992));
# 23 "../test/randomAc.c"
                if (CopyOf_p1_3 <= 701649992) {
                  __CrestBranch(64, 2013, 1);
                  __CrestLoad(68, (unsigned long )(& CopyOf_p1_3), (long long )CopyOf_p1_3);
                  __CrestLoad(67, (unsigned long )0, (long long )624222804);
                  __CrestApply2(66, 0, (long long )(CopyOf_p1_3 + 624222804));
                  __CrestStore(69, (unsigned long )(& CopyOf_p1_3));
# 24 "../test/randomAc.c"
                  CopyOf_p1_3 += 624222804;
                  __CrestLoad(72, (unsigned long )(& CopyOf_p1_3), (long long )CopyOf_p1_3);
                  __CrestLoad(71, (unsigned long )0, (long long )701649992);
                  __CrestApply2(70, 2, (long long )(CopyOf_p1_3 * 701649992));
                  __CrestStore(73, (unsigned long )(& CopyOf_p1_3));
# 25 "../test/randomAc.c"
                  CopyOf_p1_3 *= 701649992;
                  {
                  __CrestLoad(76, (unsigned long )(& CopyOf_p1_4), (long long )CopyOf_p1_4);
                  __CrestLoad(75, (unsigned long )0, (long long )75561988);
                  __CrestApply2(74, 14, (long long )(CopyOf_p1_4 > 75561988));
# 26 "../test/randomAc.c"
                  if (CopyOf_p1_4 > 75561988) {
                    __CrestBranch(77, 2015, 1);
                    {
                    __CrestLoad(81, (unsigned long )(& CopyOf_p1_4), (long long )CopyOf_p1_4);
                    __CrestLoad(80, (unsigned long )0, (long long )369957327);
                    __CrestApply2(79, 15, (long long )(CopyOf_p1_4 <= 369957327));
# 26 "../test/randomAc.c"
                    if (CopyOf_p1_4 <= 369957327) {
                      __CrestBranch(82, 2016, 1);
                      __CrestLoad(86, (unsigned long )(& CopyOf_p1_4), (long long )CopyOf_p1_4);
                      __CrestLoad(85, (unsigned long )0, (long long )75561988);
                      __CrestApply2(84, 0, (long long )(CopyOf_p1_4 + 75561988));
                      __CrestStore(87, (unsigned long )(& CopyOf_p1_4));
# 27 "../test/randomAc.c"
                      CopyOf_p1_4 += 75561988;
                      __CrestLoad(90, (unsigned long )(& CopyOf_p1_4), (long long )CopyOf_p1_4);
                      __CrestLoad(89, (unsigned long )0, (long long )369957327);
                      __CrestApply2(88, 2, (long long )(CopyOf_p1_4 * 369957327));
                      __CrestStore(91, (unsigned long )(& CopyOf_p1_4));
# 28 "../test/randomAc.c"
                      CopyOf_p1_4 *= 369957327;
                    } else {
                      __CrestBranch(83, 2017, 0);

                    }
                    }
                  } else {
                    __CrestBranch(78, 2018, 0);

                  }
                  }
                } else {
                  __CrestBranch(65, 2019, 0);

                }
                }
              } else {
                __CrestBranch(60, 2020, 0);

              }
              }
            } else {
              __CrestBranch(47, 2021, 0);

            }
            }
          } else {
            __CrestBranch(42, 2022, 0);

          }
          }
        } else {
          __CrestBranch(29, 2023, 0);

        }
        }
      } else {
        __CrestBranch(24, 2024, 0);

      }
      }
    } else {
      __CrestBranch(11, 2025, 0);

    }
    }
  } else {
    __CrestBranch(6, 2026, 0);

  }
  __CrestLoad(92, (unsigned long )0, (long long )0);
  __CrestStore(93, (unsigned long )(& __retres6));
# 39 "../test/randomAc.c"
  __retres6 = 0;
  __CrestLoad(94, (unsigned long )(& __retres6), (long long )__retres6);
  __CrestReturn(95);
# 3 "../test/randomAc.c"
  return (__retres6);
}
}
void __globinit_randomAc(void)
{


  {
  __CrestInit();
}
}
