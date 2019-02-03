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
  __CrestLoad(2, (unsigned long )(& p1), (long long )p1);
  __CrestLoad(3, (unsigned long )(& p3), (long long )p3);
# 4 "/tmp/mcute/actioncode.c"
  printf((char const * __restrict )"\n???setting timer with: %d, %d?????\n", p1,
         p3);
  __CrestClearStack(4);
# 5 "/tmp/mcute/actioncode.c"
  printf((char const * __restrict )"MCUTESTART \ntiming.informIn(UMLRTTimespec(p1,p3)) MCUTEEND");
  __CrestClearStack(5);
  __CrestReturn(6);
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
