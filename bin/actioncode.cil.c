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
#line 206 "/home/vagrant/mcute/bin/../crest/include/crest.h"
extern void __CrestInt(int *x )  __attribute__((__crest_skip__)) ;
#line 363 "/usr/include/stdio.h"
extern int printf(char const   * __restrict  __format  , ...) ;
#line 3 "/tmp/mcute/actioncode.c"
void main(void) 
{ 
  int p5 ;
  int p6 ;

  {
  __globinit_actioncode();
  __CrestCall(1, 1);
#line 4
  __CrestInt(& p5);
#line 4
  __CrestInt(& p6);
#line 4
  printf((char const   * __restrict  )"MCUTESTART log.log(\"$transition t149 action code\"$) MCUTEEND");
  __CrestClearStack(2);
  __CrestLoad(5, (unsigned long )(& p5), (long long )p5);
  __CrestLoad(4, (unsigned long )0, (long long )126329276);
  __CrestApply2(3, 14, (long long )(p5 > 126329276));
#line 5
  if (p5 > 126329276) {
    __CrestBranch(6, 15003, 1);
    {
    __CrestLoad(10, (unsigned long )(& p5), (long long )p5);
    __CrestLoad(9, (unsigned long )0, (long long )751694258);
    __CrestApply2(8, 15, (long long )(p5 <= 751694258));
#line 5
    if (p5 <= 751694258) {
      __CrestBranch(11, 15004, 1);
      __CrestLoad(15, (unsigned long )(& p5), (long long )p5);
      __CrestLoad(14, (unsigned long )0, (long long )126329276);
      __CrestApply2(13, 0, (long long )(p5 + 126329276));
      __CrestStore(16, (unsigned long )(& p5));
#line 5
      p5 += 126329276;
      __CrestLoad(19, (unsigned long )(& p5), (long long )p5);
      __CrestLoad(18, (unsigned long )0, (long long )751694258);
      __CrestApply2(17, 2, (long long )(p5 * 751694258));
      __CrestStore(20, (unsigned long )(& p5));
#line 6
      p5 *= 751694258;
      {
      __CrestLoad(23, (unsigned long )(& p6), (long long )p6);
      __CrestLoad(22, (unsigned long )0, (long long )322971714);
      __CrestApply2(21, 14, (long long )(p6 > 322971714));
#line 7
      if (p6 > 322971714) {
        __CrestBranch(24, 15006, 1);
        {
        __CrestLoad(28, (unsigned long )(& p6), (long long )p6);
        __CrestLoad(27, (unsigned long )0, (long long )1502966218);
        __CrestApply2(26, 15, (long long )(p6 <= 1502966218));
#line 7
        if (p6 <= 1502966218) {
          __CrestBranch(29, 15007, 1);
          __CrestLoad(33, (unsigned long )(& p6), (long long )p6);
          __CrestLoad(32, (unsigned long )0, (long long )322971714);
          __CrestApply2(31, 0, (long long )(p6 + 322971714));
          __CrestStore(34, (unsigned long )(& p6));
#line 7
          p6 += 322971714;
          __CrestLoad(37, (unsigned long )(& p6), (long long )p6);
          __CrestLoad(36, (unsigned long )0, (long long )1502966218);
          __CrestApply2(35, 2, (long long )(p6 * 1502966218));
          __CrestStore(38, (unsigned long )(& p6));
#line 8
          p6 *= 1502966218;
        } else {
          __CrestBranch(30, 15008, 0);

        }
        }
      } else {
        __CrestBranch(25, 15009, 0);

      }
      }
    } else {
      __CrestBranch(12, 15010, 0);

    }
    }
  } else {
    __CrestBranch(7, 15011, 0);

  }
  __CrestReturn(39);
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
