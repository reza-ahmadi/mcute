/* Generated by CIL v. 1.7.3 */
/* print_CIL_Input is true */

#line 3 "/tmp/mcute/actioncode.c"
void __globinit_actioncode(void) ;
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
#line 206 "/home/reza/Dropbox/Qlab/code/MCUTE/crest/bin/../include/crest.h"
extern void __CrestInt(int *x )  __attribute__((__crest_skip__)) ;
#line 362 "/usr/include/stdio.h"
extern int printf(char const   * __restrict  __format  , ...) ;
#line 3 "/tmp/mcute/actioncode.c"
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
#line 4
  __CrestInt(& p1);
#line 6
  __CrestInt(& CopyOf_p1_1);
#line 8
  __CrestInt(& CopyOf_p1_2);
#line 10
  __CrestInt(& CopyOf_p1_3);
#line 12
  __CrestInt(& CopyOf_p1_4);
#line 13
  printf((char const   * __restrict  )"MCUTESTART log.log(\"$transition t4 action code\"$) MCUTEEND");
  __CrestClearStack(2);
  __CrestLoad(5, (unsigned long )(& p1), (long long )p1);
  __CrestLoad(4, (unsigned long )0, (long long )453824451);
  __CrestApply2(3, 14, (long long )(p1 > 453824451));
#line 14
  if (p1 > 453824451) {
    __CrestBranch(6, 25403, 1);
    {
    __CrestLoad(10, (unsigned long )(& p1), (long long )p1);
    __CrestLoad(9, (unsigned long )0, (long long )1985103776);
    __CrestApply2(8, 15, (long long )(p1 <= 1985103776));
#line 14
    if (p1 <= 1985103776) {
      __CrestBranch(11, 25404, 1);
      __CrestLoad(15, (unsigned long )(& p1), (long long )p1);
      __CrestLoad(14, (unsigned long )0, (long long )453824451);
      __CrestApply2(13, 0, (long long )(p1 + 453824451));
      __CrestStore(16, (unsigned long )(& p1));
#line 14
      p1 += 453824451;
      __CrestLoad(19, (unsigned long )(& p1), (long long )p1);
      __CrestLoad(18, (unsigned long )0, (long long )1985103776);
      __CrestApply2(17, 2, (long long )(p1 * 1985103776));
      __CrestStore(20, (unsigned long )(& p1));
#line 15
      p1 *= 1985103776;
      {
      __CrestLoad(23, (unsigned long )(& CopyOf_p1_1), (long long )CopyOf_p1_1);
      __CrestLoad(22, (unsigned long )0, (long long )285238877);
      __CrestApply2(21, 14, (long long )(CopyOf_p1_1 > 285238877));
#line 16
      if (CopyOf_p1_1 > 285238877) {
        __CrestBranch(24, 25406, 1);
        {
        __CrestLoad(28, (unsigned long )(& CopyOf_p1_1), (long long )CopyOf_p1_1);
        __CrestLoad(27, (unsigned long )0, (long long )461310963);
        __CrestApply2(26, 15, (long long )(CopyOf_p1_1 <= 461310963));
#line 16
        if (CopyOf_p1_1 <= 461310963) {
          __CrestBranch(29, 25407, 1);
          __CrestLoad(33, (unsigned long )(& CopyOf_p1_1), (long long )CopyOf_p1_1);
          __CrestLoad(32, (unsigned long )0, (long long )285238877);
          __CrestApply2(31, 0, (long long )(CopyOf_p1_1 + 285238877));
          __CrestStore(34, (unsigned long )(& CopyOf_p1_1));
#line 16
          CopyOf_p1_1 += 285238877;
          __CrestLoad(37, (unsigned long )(& CopyOf_p1_1), (long long )CopyOf_p1_1);
          __CrestLoad(36, (unsigned long )0, (long long )461310963);
          __CrestApply2(35, 2, (long long )(CopyOf_p1_1 * 461310963));
          __CrestStore(38, (unsigned long )(& CopyOf_p1_1));
#line 17
          CopyOf_p1_1 *= 461310963;
          {
          __CrestLoad(41, (unsigned long )(& CopyOf_p1_2), (long long )CopyOf_p1_2);
          __CrestLoad(40, (unsigned long )0, (long long )139987537);
          __CrestApply2(39, 14, (long long )(CopyOf_p1_2 > 139987537));
#line 18
          if (CopyOf_p1_2 > 139987537) {
            __CrestBranch(42, 25409, 1);
            {
            __CrestLoad(46, (unsigned long )(& CopyOf_p1_2), (long long )CopyOf_p1_2);
            __CrestLoad(45, (unsigned long )0, (long long )1759082098);
            __CrestApply2(44, 15, (long long )(CopyOf_p1_2 <= 1759082098));
#line 18
            if (CopyOf_p1_2 <= 1759082098) {
              __CrestBranch(47, 25410, 1);
              __CrestLoad(51, (unsigned long )(& CopyOf_p1_2), (long long )CopyOf_p1_2);
              __CrestLoad(50, (unsigned long )0, (long long )139987537);
              __CrestApply2(49, 0, (long long )(CopyOf_p1_2 + 139987537));
              __CrestStore(52, (unsigned long )(& CopyOf_p1_2));
#line 18
              CopyOf_p1_2 += 139987537;
              __CrestLoad(55, (unsigned long )(& CopyOf_p1_2), (long long )CopyOf_p1_2);
              __CrestLoad(54, (unsigned long )0, (long long )1759082098);
              __CrestApply2(53, 2, (long long )(CopyOf_p1_2 * 1759082098));
              __CrestStore(56, (unsigned long )(& CopyOf_p1_2));
#line 19
              CopyOf_p1_2 *= 1759082098;
              {
              __CrestLoad(59, (unsigned long )(& CopyOf_p1_3), (long long )CopyOf_p1_3);
              __CrestLoad(58, (unsigned long )0, (long long )453636802);
              __CrestApply2(57, 14, (long long )(CopyOf_p1_3 > 453636802));
#line 20
              if (CopyOf_p1_3 > 453636802) {
                __CrestBranch(60, 25412, 1);
                {
                __CrestLoad(64, (unsigned long )(& CopyOf_p1_3), (long long )CopyOf_p1_3);
                __CrestLoad(63, (unsigned long )0, (long long )1726859609);
                __CrestApply2(62, 15, (long long )(CopyOf_p1_3 <= 1726859609));
#line 20
                if (CopyOf_p1_3 <= 1726859609) {
                  __CrestBranch(65, 25413, 1);
                  __CrestLoad(69, (unsigned long )(& CopyOf_p1_3), (long long )CopyOf_p1_3);
                  __CrestLoad(68, (unsigned long )0, (long long )453636802);
                  __CrestApply2(67, 0, (long long )(CopyOf_p1_3 + 453636802));
                  __CrestStore(70, (unsigned long )(& CopyOf_p1_3));
#line 20
                  CopyOf_p1_3 += 453636802;
                  __CrestLoad(73, (unsigned long )(& CopyOf_p1_3), (long long )CopyOf_p1_3);
                  __CrestLoad(72, (unsigned long )0, (long long )1726859609);
                  __CrestApply2(71, 2, (long long )(CopyOf_p1_3 * 1726859609));
                  __CrestStore(74, (unsigned long )(& CopyOf_p1_3));
#line 21
                  CopyOf_p1_3 *= 1726859609;
                  {
                  __CrestLoad(77, (unsigned long )(& CopyOf_p1_4), (long long )CopyOf_p1_4);
                  __CrestLoad(76, (unsigned long )0, (long long )197492637);
                  __CrestApply2(75, 14, (long long )(CopyOf_p1_4 > 197492637));
#line 22
                  if (CopyOf_p1_4 > 197492637) {
                    __CrestBranch(78, 25415, 1);
                    {
                    __CrestLoad(82, (unsigned long )(& CopyOf_p1_4), (long long )CopyOf_p1_4);
                    __CrestLoad(81, (unsigned long )0, (long long )359521823);
                    __CrestApply2(80, 15, (long long )(CopyOf_p1_4 <= 359521823));
#line 22
                    if (CopyOf_p1_4 <= 359521823) {
                      __CrestBranch(83, 25416, 1);
                      __CrestLoad(87, (unsigned long )(& CopyOf_p1_4), (long long )CopyOf_p1_4);
                      __CrestLoad(86, (unsigned long )0, (long long )197492637);
                      __CrestApply2(85, 0, (long long )(CopyOf_p1_4 + 197492637));
                      __CrestStore(88, (unsigned long )(& CopyOf_p1_4));
#line 22
                      CopyOf_p1_4 += 197492637;
                      __CrestLoad(91, (unsigned long )(& CopyOf_p1_4), (long long )CopyOf_p1_4);
                      __CrestLoad(90, (unsigned long )0, (long long )359521823);
                      __CrestApply2(89, 2, (long long )(CopyOf_p1_4 * 359521823));
                      __CrestStore(92, (unsigned long )(& CopyOf_p1_4));
#line 23
                      CopyOf_p1_4 *= 359521823;
                    } else {
                      __CrestBranch(84, 25417, 0);

                    }
                    }
                  } else {
                    __CrestBranch(79, 25418, 0);

                  }
                  }
                } else {
                  __CrestBranch(66, 25419, 0);

                }
                }
              } else {
                __CrestBranch(61, 25420, 0);

              }
              }
            } else {
              __CrestBranch(48, 25421, 0);

            }
            }
          } else {
            __CrestBranch(43, 25422, 0);

          }
          }
        } else {
          __CrestBranch(30, 25423, 0);

        }
        }
      } else {
        __CrestBranch(25, 25424, 0);

      }
      }
    } else {
      __CrestBranch(12, 25425, 0);

    }
    }
  } else {
    __CrestBranch(7, 25426, 0);

  }
  __CrestReturn(93);
#line 3
  return;
}
}
void __globinit_actioncode(void) 
{ 


  {
  __CrestInit();
}
}
