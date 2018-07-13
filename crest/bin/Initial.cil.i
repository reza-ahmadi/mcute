# 1 "./Initial.cil.c"
# 1 "<built-in>"
# 1 "<command-line>"
# 1 "/usr/include/stdc-predef.h" 1 3 4
# 1 "<command-line>" 2
# 1 "./Initial.cil.c"
# 4 "/home/reza/workspace-papyrusrt1.0/Harness_UMLRT_CDTProject/src/Initial.c"
void __globinit_Initial(void) ;
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
# 4 "/home/reza/workspace-papyrusrt1.0/Harness_UMLRT_CDTProject/src/Initial.c"
int main(void)
{
  int DesiredLevel ;
  int Temprature ;
  int __retres3 ;

  {
  __globinit_Initial();
  __CrestCall(1, 1);
  __CrestLoad(2, (unsigned long )0, (long long )5);
  __CrestStore(3, (unsigned long )(& DesiredLevel));
# 6 "/home/reza/workspace-papyrusrt1.0/Harness_UMLRT_CDTProject/src/Initial.c"
  DesiredLevel = 5;
  __CrestLoad(4, (unsigned long )0, (long long )50);
  __CrestStore(5, (unsigned long )(& Temprature));
# 7 "/home/reza/workspace-papyrusrt1.0/Harness_UMLRT_CDTProject/src/Initial.c"
  Temprature = 50;
  __CrestLoad(6, (unsigned long )0, (long long )0);
  __CrestStore(7, (unsigned long )(& __retres3));
# 9 "/home/reza/workspace-papyrusrt1.0/Harness_UMLRT_CDTProject/src/Initial.c"
  __retres3 = 0;
  __CrestLoad(8, (unsigned long )(& __retres3), (long long )__retres3);
  __CrestReturn(9);
# 4 "/home/reza/workspace-papyrusrt1.0/Harness_UMLRT_CDTProject/src/Initial.c"
  return (__retres3);
}
}
void __globinit_Initial(void)
{


  {
  __CrestInit();
}
}
