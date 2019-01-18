char* strategy = "BFS"; //default strategy
int iterationLength=1;
char* heuristicStr = "BFS"; //default strategy
int heuristic = 0; //default algorithm for branch selection
map<string,int> params;
int transitionIterations = 5; //default
int time = 0; //default time
int totalIterations = 0;
//read terminal inputs
int argc = UMLRTMain::getArgCount();
log.log("params count: %d", argc);
if (argc==1) {
  const char * arg = UMLRTMain::getArg(0);
  if (!strcmp(arg, "-h")) {
    log.log("mcute [program] [test-conf]");
    log.log("[program] : the executable program to be tested");
    log.log("[test-conf] : a config file that describes various parameters");
    log.log("for instance, to execute a program called sample1 using the config file conf1, run: mcute sample1 conf1");
  }else{
    string name;
    int value;
    ifstream conf(arg);
    string comments;
    std::getline(conf, comments); //the first two lines are comments
    std::getline(conf, comments); //the first two lines are comments
    while (conf>>name>>value){
      if (name.find("//")==std::string::npos){ //line is not a comment
        params.insert(pair<string,int>(name,value));
      }
    }
    cout<<"sanity checking the config file (mcute.conf).."<<endl;
    assert(params.size()>=3);
    if (params["strategy"]==0){
      strategy="rnd0";
    } else if (params["strategy"]==1){
      strategy="black-box";
    } else if (params["strategy"]==2){
      strategy="simple";
    } else if (params["strategy"]==3){
      strategy="conc";
    }

    settings.start(params["heuristic"], strategy, params["time"], params["iterationLength"] , params["totalIterations"], params["transitionIterations"]).send(); //default 5 seconds
  }
}
else if (argc==5) {
  //const char * arg = UMLRTMain::getArg(0);
  //const char * arg1 = UMLRTMain::getArg(1);
  //const char * arg2 = UMLRTMain::getArg(2);
  //log.log("  0:%s, 1:%s, 2:%s  " , arg, arg1, arg2);


  strategy = (char*) UMLRTMain::getArg(0);

  heuristicStr = (char*) UMLRTMain::getArg(1);

  heuristic = atoi(heuristicStr);

  const char * timeStr = UMLRTMain::getArg(2);
  time = atoi(timeStr);

  const char * totalIterationsStr = UMLRTMain::getArg(3);
  totalIterations = atoi(totalIterationsStr);

  const char * transitionIterationsStr = UMLRTMain::getArg(4);
  if (atoi(transitionIterationsStr)>0) {
    transitionIterations = atoi(transitionIterationsStr);
  }

  settings.start(heuristic, strategy, time, iterationLength, totalIterations, transitionIterations).send(); //default 5 seconds
}else{
  log.log("mcute [program] [test-conf]");
  log.log("[program] : the executable program to be tested");
  log.log("[test-conf] : a config file that describes various parameters");
  log.log("for instance, to execute a program called sample1 using the config file conf1, run: mcute sample1 conf1");
}
