/* Generated by CIL v. 1.7.3 */
/* print_CIL_Input is true */

#line 8 "../test/t4.c"
void __globinit_t4(void) ;
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
#line 362 "/usr/include/stdio.h"
extern int printf(char const   * __restrict  __format  , ...) ;
#line 8 "../test/t4.c"
int main(void) 
{ 
  int HighTemps ;
  int __retres2 ;

  {
  __globinit_t4();
  __CrestCall(1, 1);
  __CrestLoad(4, (unsigned long )(& HighTemps), (long long )HighTemps);
  __CrestLoad(3, (unsigned long )0, (long long )10);
  __CrestApply2(2, 17, (long long )(HighTemps >= 10));
#line 11
  if (HighTemps >= 10) {
    __CrestBranch(5, 9, 1);
    __CrestLoad(7, (unsigned long )(& HighTemps), (long long )HighTemps);
#line 12
    printf((char const   * __restrict  )"----branch visited: HighTemps>=10!!!, %d degrees more than standart!!!----\n",
           HighTemps);
    __CrestClearStack(8);
  } else {
    __CrestBranch(6, 10, 0);
#line 14
    printf((char const   * __restrict  )"----branch visited: !(HighTemps>=10)----\n");
    __CrestClearStack(9);
  }
  __CrestLoad(10, (unsigned long )0, (long long )0);
  __CrestStore(11, (unsigned long )(& __retres2));
#line 16
  __retres2 = 0;
  __CrestLoad(12, (unsigned long )(& __retres2), (long long )__retres2);
  __CrestReturn(13);
#line 8
  return (__retres2);
}
}
void __globinit_t4(void) 
{ 


  {
  __CrestInit();
}
}
