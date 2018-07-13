class CUTE{
	Object pc;
	ExecConfig conf;
	StateMachineInfo smInfo;
	public CUTE(ExecConfig _conf, StateMachineInfo _smInfo){
		this.conf = _conf;
		this.smInfo = _smInfo;
	}

	public List<Object> generateInputs(State currentState){
		//based on the outgoing transitions from the current state
		//0. pick a transition
		//1. run its action code
		//2. update PC and return the generated inputs
	}

}
