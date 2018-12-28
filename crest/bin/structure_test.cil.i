# 1 "./structure_test.cil.c"
# 1 "<built-in>"
# 1 "<command-line>"
# 1 "/usr/include/stdc-predef.h" 1 3 4
# 1 "<command-line>" 2
# 1 "./structure_test.cil.c"
# 26 "../test/structure_test.c"
void __globinit_structure_test(void) ;
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
# 15 "../test/structure_test.c"
struct bar {
   int x ;
   int y ;
};
# 20 "../test/structure_test.c"
struct foo {
   int x ;
   struct bar bar ;
   int y ;
};
# 206 "./../include/crest.h"
extern void __CrestInt(int *x ) __attribute__((__crest_skip__)) ;
# 26 "../test/structure_test.c"
int main(void)
{
  struct foo f1 ;
  struct foo f2 ;
  struct bar b ;
  int __retres4 ;

  {
  __globinit_structure_test();
  __CrestCall(1, 1);
# 30 "../test/structure_test.c"
  __CrestInt(& b.x);
# 31 "../test/structure_test.c"
  __CrestInt(& b.y);
  __CrestLoad(2, (unsigned long )0, (long long )7);
  __CrestStore(3, (unsigned long )(& f1.x));
# 33 "../test/structure_test.c"
  f1.x = 7;
# 34 "../test/structure_test.c"
  f1.bar = b;
  __CrestLoad(4, (unsigned long )0, (long long )19);
  __CrestStore(5, (unsigned long )(& f1.y));
# 35 "../test/structure_test.c"
  f1.y = 19;
# 37 "../test/structure_test.c"
  __CrestInt(& f2.x);
# 38 "../test/structure_test.c"
  __CrestInt(& f2.y);
# 40 "../test/structure_test.c"
  f2 = f1;
  __CrestLoad(6, (unsigned long )0, (long long )0);
  __CrestStore(7, (unsigned long )(& __retres4));
# 47 "../test/structure_test.c"
  __retres4 = 0;
  __CrestLoad(8, (unsigned long )(& __retres4), (long long )__retres4);
  __CrestReturn(9);
# 26 "../test/structure_test.c"
  return (__retres4);
}
}
void __globinit_structure_test(void)
{


  {
  __CrestInit();
}
}
