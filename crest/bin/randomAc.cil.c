/* Generated by CIL v. 1.7.3 */
/* print_CIL_Input is true */

#line 3 "../test/randomAc.c"
void __globinit_randomAc(void) ;
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
#line 3 "../test/randomAc.c"
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
#line 8
  __CrestInt(& p1);
#line 9
  __CrestInt(& CopyOf_p1_1);
#line 10
  __CrestInt(& CopyOf_p1_2);
#line 11
  __CrestInt(& CopyOf_p1_3);
#line 12
  __CrestInt(& CopyOf_p1_4);
  __CrestLoad(4, (unsigned long )(& p1), (long long )p1);
  __CrestLoad(3, (unsigned long )0, (long long )945243205);
  __CrestApply2(2, 14, (long long )(p1 > 945243205));
#line 14
  if (p1 > 945243205) {
    __CrestBranch(5, 2003, 1);
    {
    __CrestLoad(9, (unsigned long )(& p1), (long long )p1);
    __CrestLoad(8, (unsigned long )0, (long long )985125732);
    __CrestApply2(7, 15, (long long )(p1 <= 985125732));
#line 14
    if (p1 <= 985125732) {
      __CrestBranch(10, 2004, 1);
      __CrestLoad(14, (unsigned long )(& p1), (long long )p1);
      __CrestLoad(13, (unsigned long )0, (long long )945243205);
      __CrestApply2(12, 0, (long long )(p1 + 945243205));
      __CrestStore(15, (unsigned long )(& p1));
#line 15
      p1 += 945243205;
      __CrestLoad(18, (unsigned long )(& p1), (long long )p1);
      __CrestLoad(17, (unsigned long )0, (long long )985125732);
      __CrestApply2(16, 2, (long long )(p1 * 985125732));
      __CrestStore(19, (unsigned long )(& p1));
#line 16
      p1 *= 985125732;
      {
      __CrestLoad(22, (unsigned long )(& CopyOf_p1_1), (long long )CopyOf_p1_1);
      __CrestLoad(21, (unsigned long )0, (long long )37265035);
      __CrestApply2(20, 14, (long long )(CopyOf_p1_1 > 37265035));
#line 17
      if (CopyOf_p1_1 > 37265035) {
        __CrestBranch(23, 2006, 1);
        {
        __CrestLoad(27, (unsigned long )(& CopyOf_p1_1), (long long )CopyOf_p1_1);
        __CrestLoad(26, (unsigned long )0, (long long )106224505);
        __CrestApply2(25, 15, (long long )(CopyOf_p1_1 <= 106224505));
#line 17
        if (CopyOf_p1_1 <= 106224505) {
          __CrestBranch(28, 2007, 1);
          __CrestLoad(32, (unsigned long )(& CopyOf_p1_1), (long long )CopyOf_p1_1);
          __CrestLoad(31, (unsigned long )0, (long long )37265035);
          __CrestApply2(30, 0, (long long )(CopyOf_p1_1 + 37265035));
          __CrestStore(33, (unsigned long )(& CopyOf_p1_1));
#line 18
          CopyOf_p1_1 += 37265035;
          __CrestLoad(36, (unsigned long )(& CopyOf_p1_1), (long long )CopyOf_p1_1);
          __CrestLoad(35, (unsigned long )0, (long long )106224505);
          __CrestApply2(34, 2, (long long )(CopyOf_p1_1 * 106224505));
          __CrestStore(37, (unsigned long )(& CopyOf_p1_1));
#line 19
          CopyOf_p1_1 *= 106224505;
          {
          __CrestLoad(40, (unsigned long )(& CopyOf_p1_2), (long long )CopyOf_p1_2);
          __CrestLoad(39, (unsigned long )0, (long long )974363458);
          __CrestApply2(38, 14, (long long )(CopyOf_p1_2 > 974363458));
#line 20
          if (CopyOf_p1_2 > 974363458) {
            __CrestBranch(41, 2009, 1);
            {
            __CrestLoad(45, (unsigned long )(& CopyOf_p1_2), (long long )CopyOf_p1_2);
            __CrestLoad(44, (unsigned long )0, (long long )1444801314);
            __CrestApply2(43, 15, (long long )(CopyOf_p1_2 <= 1444801314));
#line 20
            if (CopyOf_p1_2 <= 1444801314) {
              __CrestBranch(46, 2010, 1);
              __CrestLoad(50, (unsigned long )(& CopyOf_p1_2), (long long )CopyOf_p1_2);
              __CrestLoad(49, (unsigned long )0, (long long )974363458);
              __CrestApply2(48, 0, (long long )(CopyOf_p1_2 + 974363458));
              __CrestStore(51, (unsigned long )(& CopyOf_p1_2));
#line 21
              CopyOf_p1_2 += 974363458;
              __CrestLoad(54, (unsigned long )(& CopyOf_p1_2), (long long )CopyOf_p1_2);
              __CrestLoad(53, (unsigned long )0, (long long )1444801314);
              __CrestApply2(52, 2, (long long )(CopyOf_p1_2 * 1444801314));
              __CrestStore(55, (unsigned long )(& CopyOf_p1_2));
#line 22
              CopyOf_p1_2 *= 1444801314;
              {
              __CrestLoad(58, (unsigned long )(& CopyOf_p1_3), (long long )CopyOf_p1_3);
              __CrestLoad(57, (unsigned long )0, (long long )624222804);
              __CrestApply2(56, 14, (long long )(CopyOf_p1_3 > 624222804));
#line 23
              if (CopyOf_p1_3 > 624222804) {
                __CrestBranch(59, 2012, 1);
                {
                __CrestLoad(63, (unsigned long )(& CopyOf_p1_3), (long long )CopyOf_p1_3);
                __CrestLoad(62, (unsigned long )0, (long long )701649992);
                __CrestApply2(61, 15, (long long )(CopyOf_p1_3 <= 701649992));
#line 23
                if (CopyOf_p1_3 <= 701649992) {
                  __CrestBranch(64, 2013, 1);
                  __CrestLoad(68, (unsigned long )(& CopyOf_p1_3), (long long )CopyOf_p1_3);
                  __CrestLoad(67, (unsigned long )0, (long long )624222804);
                  __CrestApply2(66, 0, (long long )(CopyOf_p1_3 + 624222804));
                  __CrestStore(69, (unsigned long )(& CopyOf_p1_3));
#line 24
                  CopyOf_p1_3 += 624222804;
                  __CrestLoad(72, (unsigned long )(& CopyOf_p1_3), (long long )CopyOf_p1_3);
                  __CrestLoad(71, (unsigned long )0, (long long )701649992);
                  __CrestApply2(70, 2, (long long )(CopyOf_p1_3 * 701649992));
                  __CrestStore(73, (unsigned long )(& CopyOf_p1_3));
#line 25
                  CopyOf_p1_3 *= 701649992;
                  {
                  __CrestLoad(76, (unsigned long )(& CopyOf_p1_4), (long long )CopyOf_p1_4);
                  __CrestLoad(75, (unsigned long )0, (long long )75561988);
                  __CrestApply2(74, 14, (long long )(CopyOf_p1_4 > 75561988));
#line 26
                  if (CopyOf_p1_4 > 75561988) {
                    __CrestBranch(77, 2015, 1);
                    {
                    __CrestLoad(81, (unsigned long )(& CopyOf_p1_4), (long long )CopyOf_p1_4);
                    __CrestLoad(80, (unsigned long )0, (long long )369957327);
                    __CrestApply2(79, 15, (long long )(CopyOf_p1_4 <= 369957327));
#line 26
                    if (CopyOf_p1_4 <= 369957327) {
                      __CrestBranch(82, 2016, 1);
                      __CrestLoad(86, (unsigned long )(& CopyOf_p1_4), (long long )CopyOf_p1_4);
                      __CrestLoad(85, (unsigned long )0, (long long )75561988);
                      __CrestApply2(84, 0, (long long )(CopyOf_p1_4 + 75561988));
                      __CrestStore(87, (unsigned long )(& CopyOf_p1_4));
#line 27
                      CopyOf_p1_4 += 75561988;
                      __CrestLoad(90, (unsigned long )(& CopyOf_p1_4), (long long )CopyOf_p1_4);
                      __CrestLoad(89, (unsigned long )0, (long long )369957327);
                      __CrestApply2(88, 2, (long long )(CopyOf_p1_4 * 369957327));
                      __CrestStore(91, (unsigned long )(& CopyOf_p1_4));
#line 28
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
#line 39
  __retres6 = 0;
  __CrestLoad(94, (unsigned long )(& __retres6), (long long )__retres6);
  __CrestReturn(95);
#line 3
  return (__retres6);
}
}
void __globinit_randomAc(void) 
{ 


  {
  __CrestInit();
}
}
