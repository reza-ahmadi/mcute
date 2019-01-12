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
# 206 "./../include/crest.h"
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
# 4 "/tmp/mcute/actioncode.c"
  __CrestInt(& CopyOf_p1_1);
# 4 "/tmp/mcute/actioncode.c"
  __CrestInt(& CopyOf_p1_2);
# 4 "/tmp/mcute/actioncode.c"
  __CrestInt(& CopyOf_p1_3);
# 4 "/tmp/mcute/actioncode.c"
  __CrestInt(& CopyOf_p1_4);
# 4 "/tmp/mcute/actioncode.c"
  printf((char const * __restrict )"MCUTESTART log.log(\"$transition t19 action code\"$);if(p1 > 814103662 && p1 <=897033810){p1+= 814103662;p1*= 897033810;if(CopyOf_p1_1 > 566498792 && CopyOf_p1_1 <=1521067732){CopyOf_p1_1+= 566498792;CopyOf_p1_1*= 1521067732;if(CopyOf_p1_2 > 916130954 && CopyOf_p1_2 <=1364126752){CopyOf_p1_2+= 916130954;CopyOf_p1_2*= 1364126752;if(CopyOf_p1_3 > 456238838 && CopyOf_p1_3 <=890032181){CopyOf_p1_3+= 456238838;CopyOf_p1_3*= 890032181;if(CopyOf_p1_4 > 752951849 && CopyOf_p1_4 <=1267609542){CopyOf_p1_4+= 752951849;CopyOf_p1_4*= 1267609542;}else{}}else{}}else{}}else{}}else{} MCUTEEND");
  __CrestClearStack(2);
  __CrestReturn(3);
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
