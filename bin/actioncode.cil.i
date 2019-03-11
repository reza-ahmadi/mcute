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
# 204 "/home/vagrant/mcute/bin/../crest/include/crest.h"
extern void __CrestChar(char *x ) __attribute__((__crest_skip__)) ;
# 206 "/home/vagrant/mcute/bin/../crest/include/crest.h"
extern void __CrestInt(int *x ) __attribute__((__crest_skip__)) ;
# 363 "/usr/include/stdio.h"
extern int printf(char const * __restrict __format , ...) ;
# 4 "/tmp/mcute/actioncode.c"
void main(void)
{
  int Score_Player_1 ;
  int Score_Player_2 ;
  char Response_Player_1 ;
  char Response_Player_2 ;
  _Bool Read ;
  int ReadV ;
  int Player_Id ;
  char Hit ;

  {
  __globinit_actioncode();
  __CrestCall(1, 1);
# 13 "/tmp/mcute/actioncode.c"
  __CrestInt(& Player_Id);
# 14 "/tmp/mcute/actioncode.c"
  __CrestChar(& Hit);
# 14 "/tmp/mcute/actioncode.c"
  printf((char const * __restrict )"#0#");
  __CrestClearStack(2);
  __CrestLoad(5, (unsigned long )(& Player_Id), (long long )Player_Id);
  __CrestLoad(4, (unsigned long )0, (long long )1);
  __CrestApply2(3, 12, (long long )(Player_Id == 1));
# 15 "/tmp/mcute/actioncode.c"
  if (Player_Id == 1) {
    __CrestBranch(6, 503, 1);
    __CrestLoad(8, (unsigned long )(& Hit), (long long )Hit);
    __CrestStore(9, (unsigned long )(& Response_Player_1));
# 17 "/tmp/mcute/actioncode.c"
    Response_Player_1 = Hit;
  } else {
    __CrestBranch(7, 504, 0);
    __CrestLoad(10, (unsigned long )(& Hit), (long long )Hit);
    __CrestStore(11, (unsigned long )(& Response_Player_2));
# 21 "/tmp/mcute/actioncode.c"
    Response_Player_2 = Hit;
  }
  __CrestLoad(14, (unsigned long )(& Hit), (long long )Hit);
  __CrestLoad(13, (unsigned long )0, (long long )82);
  __CrestApply2(12, 12, (long long )((int )Hit == 82));
# 23 "/tmp/mcute/actioncode.c"
  if ((int )Hit == 82) {
    __CrestBranch(15, 506, 1);
# 25 "/tmp/mcute/actioncode.c"
    printf((char const * __restrict )"#1#");
    __CrestClearStack(17);
  } else {
    __CrestBranch(16, 507, 0);
    {
    __CrestLoad(20, (unsigned long )(& Hit), (long long )Hit);
    __CrestLoad(19, (unsigned long )0, (long long )80);
    __CrestApply2(18, 12, (long long )((int )Hit == 80));
# 27 "/tmp/mcute/actioncode.c"
    if ((int )Hit == 80) {
      __CrestBranch(21, 508, 1);
# 29 "/tmp/mcute/actioncode.c"
      printf((char const * __restrict )"#2#");
      __CrestClearStack(23);
    } else {
      __CrestBranch(22, 509, 0);
# 33 "/tmp/mcute/actioncode.c"
      printf((char const * __restrict )"#3#");
      __CrestClearStack(24);
    }
    }
  }
  __CrestLoad(27, (unsigned long )(& Response_Player_1), (long long )Response_Player_1);
  __CrestLoad(26, (unsigned long )(& Response_Player_2), (long long )Response_Player_2);
  __CrestApply2(25, 12, (long long )((int )Response_Player_1 == (int )Response_Player_2));
# 36 "/tmp/mcute/actioncode.c"
  if ((int )Response_Player_1 == (int )Response_Player_2) {
    __CrestBranch(28, 511, 1);
# 38 "/tmp/mcute/actioncode.c"
    printf((char const * __restrict )"#4#");
    __CrestClearStack(30);
# 39 "/tmp/mcute/actioncode.c"
    printf((char const * __restrict )"#5#");
    __CrestClearStack(31);
  } else {
    __CrestBranch(29, 512, 0);
    {
    __CrestLoad(34, (unsigned long )(& Response_Player_1), (long long )Response_Player_1);
    __CrestLoad(33, (unsigned long )0, (long long )82);
    __CrestApply2(32, 12, (long long )((int )Response_Player_1 == 82));
# 42 "/tmp/mcute/actioncode.c"
    if ((int )Response_Player_1 == 82) {
      __CrestBranch(35, 513, 1);
      {
      __CrestLoad(39, (unsigned long )(& Response_Player_2), (long long )Response_Player_2);
      __CrestLoad(38, (unsigned long )0, (long long )83);
      __CrestApply2(37, 12, (long long )((int )Response_Player_2 == 83));
# 42 "/tmp/mcute/actioncode.c"
      if ((int )Response_Player_2 == 83) {
        __CrestBranch(40, 514, 1);
# 42 "/tmp/mcute/actioncode.c"
        goto _L;
      } else {
        __CrestBranch(41, 515, 0);
# 42 "/tmp/mcute/actioncode.c"
        goto _L___2;
      }
      }
    } else {
      __CrestBranch(36, 516, 0);
      _L___2:
      {
      __CrestLoad(44, (unsigned long )(& Response_Player_1), (long long )Response_Player_1);
      __CrestLoad(43, (unsigned long )0, (long long )83);
      __CrestApply2(42, 12, (long long )((int )Response_Player_1 == 83));
# 42 "/tmp/mcute/actioncode.c"
      if ((int )Response_Player_1 == 83) {
        __CrestBranch(45, 517, 1);
        {
        __CrestLoad(49, (unsigned long )(& Response_Player_2), (long long )Response_Player_2);
        __CrestLoad(48, (unsigned long )0, (long long )80);
        __CrestApply2(47, 12, (long long )((int )Response_Player_2 == 80));
# 42 "/tmp/mcute/actioncode.c"
        if ((int )Response_Player_2 == 80) {
          __CrestBranch(50, 518, 1);
# 42 "/tmp/mcute/actioncode.c"
          goto _L;
        } else {
          __CrestBranch(51, 519, 0);
# 42 "/tmp/mcute/actioncode.c"
          goto _L___1;
        }
        }
      } else {
        __CrestBranch(46, 520, 0);
        _L___1:
        {
        __CrestLoad(54, (unsigned long )(& Response_Player_1), (long long )Response_Player_1);
        __CrestLoad(53, (unsigned long )0, (long long )80);
        __CrestApply2(52, 12, (long long )((int )Response_Player_1 == 80));
# 42 "/tmp/mcute/actioncode.c"
        if ((int )Response_Player_1 == 80) {
          __CrestBranch(55, 521, 1);
          {
          __CrestLoad(59, (unsigned long )(& Response_Player_2), (long long )Response_Player_2);
          __CrestLoad(58, (unsigned long )0, (long long )82);
          __CrestApply2(57, 12, (long long )((int )Response_Player_2 == 82));
# 42 "/tmp/mcute/actioncode.c"
          if ((int )Response_Player_2 == 82) {
            __CrestBranch(60, 522, 1);
            _L:
            __CrestLoad(64, (unsigned long )(& Score_Player_1), (long long )Score_Player_1);
            __CrestLoad(63, (unsigned long )0, (long long )1);
            __CrestApply2(62, 0, (long long )(Score_Player_1 + 1));
            __CrestStore(65, (unsigned long )(& Score_Player_1));
# 44 "/tmp/mcute/actioncode.c"
            Score_Player_1 ++;
# 45 "/tmp/mcute/actioncode.c"
            printf((char const * __restrict )"#6#");
            __CrestClearStack(66);
# 46 "/tmp/mcute/actioncode.c"
            printf((char const * __restrict )"#7#");
            __CrestClearStack(67);
            {
            __CrestLoad(74, (unsigned long )(& Score_Player_1), (long long )Score_Player_1);
            __CrestLoad(73, (unsigned long )(& ReadV), (long long )ReadV);
            __CrestLoad(72, (unsigned long )0, (long long )2);
            __CrestApply2(71, 3, (long long )(ReadV / 2));
            __CrestLoad(70, (unsigned long )0, (long long )1);
            __CrestApply2(69, 0, (long long )(ReadV / 2 + 1));
            __CrestApply2(68, 17, (long long )(Score_Player_1 >= ReadV / 2 + 1));
# 47 "/tmp/mcute/actioncode.c"
            if (Score_Player_1 >= ReadV / 2 + 1) {
              __CrestBranch(75, 524, 1);
              __CrestLoad(77, (unsigned long )0, (long long )(_Bool)1);
              __CrestStore(78, (unsigned long )(& Read));
# 49 "/tmp/mcute/actioncode.c"
              Read = (_Bool)1;
            } else {
              __CrestBranch(76, 525, 0);

            }
            }
          } else {
            __CrestBranch(61, 526, 0);
# 42 "/tmp/mcute/actioncode.c"
            goto _L___0;
          }
          }
        } else {
          __CrestBranch(56, 527, 0);
          _L___0:
# 55 "/tmp/mcute/actioncode.c"
          printf((char const * __restrict )"#8#");
          __CrestClearStack(79);
# 56 "/tmp/mcute/actioncode.c"
          printf((char const * __restrict )"#9#");
          __CrestClearStack(80);
          __CrestLoad(83, (unsigned long )(& Score_Player_2), (long long )Score_Player_2);
          __CrestLoad(82, (unsigned long )0, (long long )1);
          __CrestApply2(81, 0, (long long )(Score_Player_2 + 1));
          __CrestStore(84, (unsigned long )(& Score_Player_2));
# 57 "/tmp/mcute/actioncode.c"
          Score_Player_2 ++;
          {
          __CrestLoad(91, (unsigned long )(& Score_Player_2), (long long )Score_Player_2);
          __CrestLoad(90, (unsigned long )(& ReadV), (long long )ReadV);
          __CrestLoad(89, (unsigned long )0, (long long )2);
          __CrestApply2(88, 3, (long long )(ReadV / 2));
          __CrestLoad(87, (unsigned long )0, (long long )1);
          __CrestApply2(86, 0, (long long )(ReadV / 2 + 1));
          __CrestApply2(85, 17, (long long )(Score_Player_2 >= ReadV / 2 + 1));
# 58 "/tmp/mcute/actioncode.c"
          if (Score_Player_2 >= ReadV / 2 + 1) {
            __CrestBranch(92, 529, 1);
            __CrestLoad(94, (unsigned long )0, (long long )(_Bool)1);
            __CrestStore(95, (unsigned long )(& Read));
# 60 "/tmp/mcute/actioncode.c"
            Read = (_Bool)1;
          } else {
            __CrestBranch(93, 530, 0);

          }
          }
        }
        }
      }
      }
    }
    }
  }
# 67 "/tmp/mcute/actioncode.c"
  printf((char const * __restrict )"#10#");
  __CrestClearStack(96);
  __CrestReturn(97);
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
