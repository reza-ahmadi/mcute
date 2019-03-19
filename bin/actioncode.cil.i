# 1 "./actioncode.cil.c"
# 1 "<built-in>"
# 1 "<command-line>"
# 1 "./actioncode.cil.c"
# 4 "/tmp/mcute/actioncode.c"
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
# 4 "/tmp/mcute/actioncode.c"
void main(void)
{
  int Entrance_Free ;
  int Status ;

  {
  __globinit_actioncode();
  __CrestCall(1, 1);
# 11 "/tmp/mcute/actioncode.c"
  __CrestInt(& Status);
  __CrestLoad(2, (unsigned long )0, (long long )1);
  __CrestStore(3, (unsigned long )(& Entrance_Free));
# 11 "/tmp/mcute/actioncode.c"
  Entrance_Free = 1;
  __CrestReturn(4);
# 4 "/tmp/mcute/actioncode.c"
  return;
}
}
void __globinit_actioncode(void)
{


  {
  __CrestInit();
}
}
