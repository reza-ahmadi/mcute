/* Generated by CIL v. 1.7.3 */
/* print_CIL_Input is true */

#line 8 "../test/t3.c"
void __globinit_t3(void) ;
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
#line 362 "/usr/include/stdio.h"
extern int printf(char const   * __restrict  __format  , ...) ;
#line 8 "../test/t3.c"
int main(void) 
{ 
  int color ;
  int tmp ;
  int __retres3 ;

  {
  __globinit_t3();
  __CrestCall(1, 1);
  __CrestLoad(2, (unsigned long )0, (long long )0);
  __CrestStore(3, (unsigned long )(& tmp));
#line 9
  tmp = 0;
#line 11
  __CrestInt(& color);
  __CrestLoad(6, (unsigned long )(& color), (long long )color);
  __CrestLoad(5, (unsigned long )0, (long long )0);
  __CrestApply2(4, 17, (long long )(color >= 0));
#line 15
  if (color >= 0) {
    __CrestBranch(7, 43, 1);
    {
    __CrestLoad(11, (unsigned long )(& color), (long long )color);
    __CrestLoad(10, (unsigned long )0, (long long )5);
    __CrestApply2(9, 16, (long long )(color < 5));
#line 15
    if (color < 5) {
      __CrestBranch(12, 44, 1);
#line 16
      printf((char const   * __restrict  )"----branch visited: color>=0 && color<5 ----\n");
      __CrestClearStack(14);
      __CrestLoad(17, (unsigned long )(& tmp), (long long )tmp);
      __CrestLoad(16, (unsigned long )(& color), (long long )color);
      __CrestApply2(15, 0, (long long )(tmp + color));
      __CrestStore(18, (unsigned long )(& tmp));
#line 17
      tmp += color;
    } else {
      __CrestBranch(13, 45, 0);
#line 20
      printf((char const   * __restrict  )"----branch visited: !(color>=0 && color<5)----\n");
      __CrestClearStack(19);
    }
    }
  } else {
    __CrestBranch(8, 46, 0);
#line 20
    printf((char const   * __restrict  )"----branch visited: !(color>=0 && color<5)----\n");
    __CrestClearStack(20);
  }
  __CrestLoad(23, (unsigned long )(& tmp), (long long )tmp);
  __CrestLoad(22, (unsigned long )0, (long long )2);
  __CrestApply2(21, 17, (long long )(tmp >= 2));
#line 22
  if (tmp >= 2) {
    __CrestBranch(24, 48, 1);
#line 23
    printf((char const   * __restrict  )"----branch visited: tmp>=2----\n");
    __CrestClearStack(26);
  } else {
    __CrestBranch(25, 49, 0);
#line 26
    printf((char const   * __restrict  )"----branch visited: !tmp>=2----\n");
    __CrestClearStack(27);
  }
  __CrestLoad(28, (unsigned long )0, (long long )0);
  __CrestStore(29, (unsigned long )(& __retres3));
#line 28
  __retres3 = 0;
  __CrestLoad(30, (unsigned long )(& __retres3), (long long )__retres3);
  __CrestReturn(31);
#line 8
  return (__retres3);
}
}
void __globinit_t3(void) 
{ 


  {
  __CrestInit();
}
}
