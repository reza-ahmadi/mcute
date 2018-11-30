
YICES_DIR=/home/reza/Downloads/new/yices-1.0.40

CC=$(CXX)
CFLAGS = -I. -I$(YICES_DIR)/include -Wall -O2 -std=c++11
CXXFLAGS = $(CFLAGS)
LDFLAGS = -L$(YICES_DIR)/lib
LOADLIBES = -lyices

BASE_LIBS = base/basic_types.o base/symbolic_execution.o \
            base/symbolic_interpreter.o base/symbolic_path.o \
            base/symbolic_predicate.o base/symbolic_expression.o \
            base/yices_solver.o


all: libcrest/libumlcute.a libcrest/libcrest.a run_crest/run_crest run_mcute/umlCUTE \
     process_cfg/process_cfg tools/print_execution \
     install

libcrest/libcrest.a: libcrest/crest.o $(BASE_LIBS)
	$(AR) rsv $@ $^


libcrest/libumlcute.a: run_mcute/concolic_search.o libcrest/crest.o run_mcute/branch_util.o run_mcute/coverage_util.o run_mcute/fileutil.o $(BASE_LIBS)
	$(AR) rsv $@ $^

run_crest/run_crest: run_crest/concolic_search.o $(BASE_LIBS)


run_mcute/umlCUTE: run_mcute/concolic_search.o $(BASE_LIBS)  #for testing executable

tools/print_execution: $(BASE_LIBS)

install:
	cp libcrest/libcrest.a ../lib
	cp libcrest/libumlcute.a ../lib
	cp run_crest/run_crest ../bin
	cp run_mcute/umlCUTE ../bin
	cp process_cfg/process_cfg ../bin
	cp tools/print_execution ../bin
	cp libcrest/crest.h ../include

clean:
	rm -f libcrest/libumlcute.a libcrest/libcrest.a run_crest/run_crest
	rm -f process_cfg/process_cfg tools/print_execution
	rm -f */*.o */*~ *~
