# 1 "./stringTest.cil.c"
# 1 "<built-in>"
# 1 "<command-line>"
# 1 "/usr/include/stdc-predef.h" 1 3 4
# 1 "<command-line>" 2
# 1 "./stringTest.cil.c"
# 5 "../test/stringTest.c"
void __globinit_stringTest(void) ;
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
# 362 "/usr/include/stdio.h"
extern int printf(char const * __restrict __format , ...) ;
# 204 "./../include/crest.h"
extern void __CrestChar(char *x ) __attribute__((__crest_skip__)) ;
# 5 "../test/stringTest.c"
int main(void)
{
  char input[4] ;
  int cnt ;
  int __retres3 ;

  {
  __globinit_stringTest();
  __CrestCall(1, 1);
# 9 "../test/stringTest.c"
  __CrestChar(& input[0]);
# 10 "../test/stringTest.c"
  __CrestChar(& input[1]);
# 11 "../test/stringTest.c"
  __CrestChar(& input[2]);
# 12 "../test/stringTest.c"
  __CrestChar(& input[3]);
  __CrestLoad(2, (unsigned long )0, (long long )0);
  __CrestStore(3, (unsigned long )(& cnt));
# 15 "../test/stringTest.c"
  cnt = 0;
  __CrestLoad(6, (unsigned long )(& input[0]), (long long )input[0]);
  __CrestLoad(5, (unsigned long )0, (long long )98);
  __CrestApply2(4, 12, (long long )((int )input[0] == 98));
# 16 "../test/stringTest.c"
  if ((int )input[0] == 98) {
    __CrestBranch(7, 3, 1);
    __CrestLoad(11, (unsigned long )(& cnt), (long long )cnt);
    __CrestLoad(10, (unsigned long )0, (long long )1);
    __CrestApply2(9, 0, (long long )(cnt + 1));
    __CrestStore(12, (unsigned long )(& cnt));
# 16 "../test/stringTest.c"
    cnt ++;
  } else {
    __CrestBranch(8, 4, 0);

  }
  __CrestLoad(15, (unsigned long )(& input[1]), (long long )input[1]);
  __CrestLoad(14, (unsigned long )0, (long long )97);
  __CrestApply2(13, 12, (long long )((int )input[1] == 97));
# 17 "../test/stringTest.c"
  if ((int )input[1] == 97) {
    __CrestBranch(16, 6, 1);
    __CrestLoad(20, (unsigned long )(& cnt), (long long )cnt);
    __CrestLoad(19, (unsigned long )0, (long long )1);
    __CrestApply2(18, 0, (long long )(cnt + 1));
    __CrestStore(21, (unsigned long )(& cnt));
# 17 "../test/stringTest.c"
    cnt ++;
  } else {
    __CrestBranch(17, 7, 0);

  }
  __CrestLoad(24, (unsigned long )(& input[2]), (long long )input[2]);
  __CrestLoad(23, (unsigned long )0, (long long )100);
  __CrestApply2(22, 12, (long long )((int )input[2] == 100));
# 18 "../test/stringTest.c"
  if ((int )input[2] == 100) {
    __CrestBranch(25, 9, 1);
    __CrestLoad(29, (unsigned long )(& cnt), (long long )cnt);
    __CrestLoad(28, (unsigned long )0, (long long )1);
    __CrestApply2(27, 0, (long long )(cnt + 1));
    __CrestStore(30, (unsigned long )(& cnt));
# 18 "../test/stringTest.c"
    cnt ++;
  } else {
    __CrestBranch(26, 10, 0);

  }
  __CrestLoad(33, (unsigned long )(& input[3]), (long long )input[3]);
  __CrestLoad(32, (unsigned long )0, (long long )33);
  __CrestApply2(31, 12, (long long )((int )input[3] == 33));
# 19 "../test/stringTest.c"
  if ((int )input[3] == 33) {
    __CrestBranch(34, 12, 1);
    __CrestLoad(38, (unsigned long )(& cnt), (long long )cnt);
    __CrestLoad(37, (unsigned long )0, (long long )1);
    __CrestApply2(36, 0, (long long )(cnt + 1));
    __CrestStore(39, (unsigned long )(& cnt));
# 19 "../test/stringTest.c"
    cnt ++;
  } else {
    __CrestBranch(35, 13, 0);

  }
# 21 "../test/stringTest.c"
  printf((char const * __restrict )"\nhere is the string: %s\n", input);
  __CrestClearStack(40);
  __CrestLoad(43, (unsigned long )(& cnt), (long long )cnt);
  __CrestLoad(42, (unsigned long )0, (long long )4);
  __CrestApply2(41, 17, (long long )(cnt >= 4));
# 23 "../test/stringTest.c"
  if (cnt >= 4) {
    __CrestBranch(44, 16, 1);
# 24 "../test/stringTest.c"
    printf((char const * __restrict )"WOW!!!!!!!!!!!!! SO BAD\n");
    __CrestClearStack(46);
  } else {
    __CrestBranch(45, 17, 0);

  }
  __CrestLoad(47, (unsigned long )0, (long long )0);
  __CrestStore(48, (unsigned long )(& __retres3));
# 26 "../test/stringTest.c"
  __retres3 = 0;
  __CrestLoad(49, (unsigned long )(& __retres3), (long long )__retres3);
  __CrestReturn(50);
# 5 "../test/stringTest.c"
  return (__retres3);
}
}
void __globinit_stringTest(void)
{


  {
  __CrestInit();
}
}
