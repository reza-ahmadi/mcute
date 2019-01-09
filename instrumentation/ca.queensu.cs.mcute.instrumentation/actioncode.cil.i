# 1 "./actioncode.cil.c"
# 1 "<built-in>"
# 1 "<command-line>"
# 1 "/usr/include/stdc-predef.h" 1 3 4
# 1 "<command-line>" 2
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
# 206 "/home/reza/Dropbox/Qlab/code/MCUTE/crest/bin/../include/crest.h"
extern void __CrestInt(int *x ) __attribute__((__crest_skip__)) ;
# 362 "/usr/include/stdio.h"
extern int printf(char const * __restrict __format , ...) ;
# 3 "/tmp/mcute/actioncode.c"
void main(void)
{
  int p1 ;
  int CopyOf_p1_1 ;
  int CopyOf_p1_2 ;
  int CopyOf_p1_3 ;
  int CopyOf_p1_4 ;

  {
  __globinit_actioncode();
  __CrestCall(1, 1);
# 4 "/tmp/mcute/actioncode.c"
  __CrestInt(& p1);
# 6 "/tmp/mcute/actioncode.c"
  __CrestInt(& CopyOf_p1_1);
# 8 "/tmp/mcute/actioncode.c"
  __CrestInt(& CopyOf_p1_2);
# 10 "/tmp/mcute/actioncode.c"
  __CrestInt(& CopyOf_p1_3);
# 12 "/tmp/mcute/actioncode.c"
  __CrestInt(& CopyOf_p1_4);
# 13 "/tmp/mcute/actioncode.c"
  printf((char const * __restrict )"MCUTESTART log.log(\"$transition t4 action code\"$) MCUTEEND");
  __CrestClearStack(2);
  __CrestLoad(5, (unsigned long )(& p1), (long long )p1);
  __CrestLoad(4, (unsigned long )0, (long long )54412619);
  __CrestApply2(3, 14, (long long )(p1 > 54412619));
# 14 "/tmp/mcute/actioncode.c"
  if (p1 > 54412619) {
    __CrestBranch(6, 22103, 1);
    {
    __CrestLoad(10, (unsigned long )(& p1), (long long )p1);
    __CrestLoad(9, (unsigned long )0, (long long )71797596);
    __CrestApply2(8, 15, (long long )(p1 <= 71797596));
# 14 "/tmp/mcute/actioncode.c"
    if (p1 <= 71797596) {
      __CrestBranch(11, 22104, 1);
      __CrestLoad(15, (unsigned long )(& p1), (long long )p1);
      __CrestLoad(14, (unsigned long )0, (long long )54412619);
      __CrestApply2(13, 0, (long long )(p1 + 54412619));
      __CrestStore(16, (unsigned long )(& p1));
# 14 "/tmp/mcute/actioncode.c"
      p1 += 54412619;
      __CrestLoad(19, (unsigned long )(& p1), (long long )p1);
      __CrestLoad(18, (unsigned long )0, (long long )71797596);
      __CrestApply2(17, 2, (long long )(p1 * 71797596));
      __CrestStore(20, (unsigned long )(& p1));
# 15 "/tmp/mcute/actioncode.c"
      p1 *= 71797596;
      {
      __CrestLoad(23, (unsigned long )(& CopyOf_p1_1), (long long )CopyOf_p1_1);
      __CrestLoad(22, (unsigned long )0, (long long )725244854);
      __CrestApply2(21, 14, (long long )(CopyOf_p1_1 > 725244854));
# 16 "/tmp/mcute/actioncode.c"
      if (CopyOf_p1_1 > 725244854) {
        __CrestBranch(24, 22106, 1);
        {
        __CrestLoad(28, (unsigned long )(& CopyOf_p1_1), (long long )CopyOf_p1_1);
        __CrestLoad(27, (unsigned long )0, (long long )1480577400);
        __CrestApply2(26, 15, (long long )(CopyOf_p1_1 <= 1480577400));
# 16 "/tmp/mcute/actioncode.c"
        if (CopyOf_p1_1 <= 1480577400) {
          __CrestBranch(29, 22107, 1);
          __CrestLoad(33, (unsigned long )(& CopyOf_p1_1), (long long )CopyOf_p1_1);
          __CrestLoad(32, (unsigned long )0, (long long )725244854);
          __CrestApply2(31, 0, (long long )(CopyOf_p1_1 + 725244854));
          __CrestStore(34, (unsigned long )(& CopyOf_p1_1));
# 16 "/tmp/mcute/actioncode.c"
          CopyOf_p1_1 += 725244854;
          __CrestLoad(37, (unsigned long )(& CopyOf_p1_1), (long long )CopyOf_p1_1);
          __CrestLoad(36, (unsigned long )0, (long long )1480577400);
          __CrestApply2(35, 2, (long long )(CopyOf_p1_1 * 1480577400));
          __CrestStore(38, (unsigned long )(& CopyOf_p1_1));
# 17 "/tmp/mcute/actioncode.c"
          CopyOf_p1_1 *= 1480577400;
          {
          __CrestLoad(41, (unsigned long )(& CopyOf_p1_2), (long long )CopyOf_p1_2);
          __CrestLoad(40, (unsigned long )0, (long long )1591679645);
          __CrestApply2(39, 14, (long long )(CopyOf_p1_2 > 1591679645));
# 18 "/tmp/mcute/actioncode.c"
          if (CopyOf_p1_2 > 1591679645) {
            __CrestBranch(42, 22109, 1);
            {
            __CrestLoad(46, (unsigned long )(& CopyOf_p1_2), (long long )CopyOf_p1_2);
            __CrestLoad(45, (unsigned long )0, (long long )1636319999);
            __CrestApply2(44, 15, (long long )(CopyOf_p1_2 <= 1636319999));
# 18 "/tmp/mcute/actioncode.c"
            if (CopyOf_p1_2 <= 1636319999) {
              __CrestBranch(47, 22110, 1);
              __CrestLoad(51, (unsigned long )(& CopyOf_p1_2), (long long )CopyOf_p1_2);
              __CrestLoad(50, (unsigned long )0, (long long )1591679645);
              __CrestApply2(49, 0, (long long )(CopyOf_p1_2 + 1591679645));
              __CrestStore(52, (unsigned long )(& CopyOf_p1_2));
# 18 "/tmp/mcute/actioncode.c"
              CopyOf_p1_2 += 1591679645;
              __CrestLoad(55, (unsigned long )(& CopyOf_p1_2), (long long )CopyOf_p1_2);
              __CrestLoad(54, (unsigned long )0, (long long )1636319999);
              __CrestApply2(53, 2, (long long )(CopyOf_p1_2 * 1636319999));
              __CrestStore(56, (unsigned long )(& CopyOf_p1_2));
# 19 "/tmp/mcute/actioncode.c"
              CopyOf_p1_2 *= 1636319999;
              {
              __CrestLoad(59, (unsigned long )(& CopyOf_p1_3), (long long )CopyOf_p1_3);
              __CrestLoad(58, (unsigned long )0, (long long )1587677804);
              __CrestApply2(57, 14, (long long )(CopyOf_p1_3 > 1587677804));
# 20 "/tmp/mcute/actioncode.c"
              if (CopyOf_p1_3 > 1587677804) {
                __CrestBranch(60, 22112, 1);
                {
                __CrestLoad(64, (unsigned long )(& CopyOf_p1_3), (long long )CopyOf_p1_3);
                __CrestLoad(63, (unsigned long )0, (long long )2085566852);
                __CrestApply2(62, 15, (long long )(CopyOf_p1_3 <= 2085566852));
# 20 "/tmp/mcute/actioncode.c"
                if (CopyOf_p1_3 <= 2085566852) {
                  __CrestBranch(65, 22113, 1);
                  __CrestLoad(69, (unsigned long )(& CopyOf_p1_3), (long long )CopyOf_p1_3);
                  __CrestLoad(68, (unsigned long )0, (long long )1587677804);
                  __CrestApply2(67, 0, (long long )(CopyOf_p1_3 + 1587677804));
                  __CrestStore(70, (unsigned long )(& CopyOf_p1_3));
# 20 "/tmp/mcute/actioncode.c"
                  CopyOf_p1_3 += 1587677804;
                  __CrestLoad(73, (unsigned long )(& CopyOf_p1_3), (long long )CopyOf_p1_3);
                  __CrestLoad(72, (unsigned long )0, (long long )2085566852);
                  __CrestApply2(71, 2, (long long )(CopyOf_p1_3 * 2085566852));
                  __CrestStore(74, (unsigned long )(& CopyOf_p1_3));
# 21 "/tmp/mcute/actioncode.c"
                  CopyOf_p1_3 *= 2085566852;
                  {
                  __CrestLoad(77, (unsigned long )(& CopyOf_p1_4), (long long )CopyOf_p1_4);
                  __CrestLoad(76, (unsigned long )0, (long long )14108254);
                  __CrestApply2(75, 14, (long long )(CopyOf_p1_4 > 14108254));
# 22 "/tmp/mcute/actioncode.c"
                  if (CopyOf_p1_4 > 14108254) {
                    __CrestBranch(78, 22115, 1);
                    {
                    __CrestLoad(82, (unsigned long )(& CopyOf_p1_4), (long long )CopyOf_p1_4);
                    __CrestLoad(81, (unsigned long )0, (long long )128501297);
                    __CrestApply2(80, 15, (long long )(CopyOf_p1_4 <= 128501297));
# 22 "/tmp/mcute/actioncode.c"
                    if (CopyOf_p1_4 <= 128501297) {
                      __CrestBranch(83, 22116, 1);
                      __CrestLoad(87, (unsigned long )(& CopyOf_p1_4), (long long )CopyOf_p1_4);
                      __CrestLoad(86, (unsigned long )0, (long long )14108254);
                      __CrestApply2(85, 0, (long long )(CopyOf_p1_4 + 14108254));
                      __CrestStore(88, (unsigned long )(& CopyOf_p1_4));
# 22 "/tmp/mcute/actioncode.c"
                      CopyOf_p1_4 += 14108254;
                      __CrestLoad(91, (unsigned long )(& CopyOf_p1_4), (long long )CopyOf_p1_4);
                      __CrestLoad(90, (unsigned long )0, (long long )128501297);
                      __CrestApply2(89, 2, (long long )(CopyOf_p1_4 * 128501297));
                      __CrestStore(92, (unsigned long )(& CopyOf_p1_4));
# 23 "/tmp/mcute/actioncode.c"
                      CopyOf_p1_4 *= 128501297;
                    } else {
                      __CrestBranch(84, 22117, 0);

                    }
                    }
                  } else {
                    __CrestBranch(79, 22118, 0);

                  }
                  }
                } else {
                  __CrestBranch(66, 22119, 0);

                }
                }
              } else {
                __CrestBranch(61, 22120, 0);

              }
              }
            } else {
              __CrestBranch(48, 22121, 0);

            }
            }
          } else {
            __CrestBranch(43, 22122, 0);

          }
          }
        } else {
          __CrestBranch(30, 22123, 0);

        }
        }
      } else {
        __CrestBranch(25, 22124, 0);

      }
      }
    } else {
      __CrestBranch(12, 22125, 0);

    }
    }
  } else {
    __CrestBranch(7, 22126, 0);

  }
  __CrestReturn(93);
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
