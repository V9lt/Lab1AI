package lab1; 

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action MOVE_UP = new DynamicAction("UP");
	public static final Action MOVE_DOWN = new DynamicAction("DOWN");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";
	public static final String LOCATION_C = "C";
	public static final String LOCATION_D = "D";
	
	// [A]-[B]
	//  |   |
	// [C]-[D]
	public enum LocationState {
		CLEAN, DIRTY
	}

	private EnvironmentState envState;
	private boolean isDone = false;// all squares are CLEAN
	private Agent agent = null;

	public Environment(LocationState locAState, LocationState locBState, LocationState locCState, LocationState locDState) {
		envState = new EnvironmentState(locAState, locBState, locCState, locDState);
	}

	// add an agent into the enviroment
	public void addAgent(Agent agent, String location) {
	
		this.agent = agent;
		this.envState.setAgentLocation(location);
	}

	public EnvironmentState getCurrentState() {
		return this.envState;
	}

	// Update enviroment state when agent do an action
	public EnvironmentState executeAction(Action action) {
		
		if	   (action.equals(MOVE_LEFT) && envState.getAgentLocation().equals(LOCATION_B)) {
			envState.setAgentLocation(LOCATION_A);
			envState.getLocationState(envState.getAgentLocation());
		}
		if	   (action.equals(MOVE_LEFT) && envState.getAgentLocation().equals(LOCATION_D)) {
			envState.setAgentLocation(LOCATION_C);
			envState.getLocationState(envState.getAgentLocation());
				
		}
		if	   (action.equals(MOVE_RIGHT) && envState.getAgentLocation().equals(LOCATION_A)) {
			envState.setAgentLocation(LOCATION_B);
			envState.getLocationState(envState.getAgentLocation());	
		}
		
		if	   (action.equals(MOVE_RIGHT) && envState.getAgentLocation().equals(LOCATION_C)) {
			envState.setAgentLocation(LOCATION_D);
			envState.getLocationState(envState.getAgentLocation());	
		}
		
		if	   (action.equals(MOVE_UP) && envState.getAgentLocation().equals(LOCATION_C)) {
			envState.setAgentLocation(LOCATION_A);
			envState.getLocationState(envState.getAgentLocation());	
		}
		if	   (action.equals(MOVE_UP) && envState.getAgentLocation().equals(LOCATION_D)) {
			envState.setAgentLocation(LOCATION_B);
			envState.getLocationState(envState.getAgentLocation());	
		}
		if	   (action.equals(MOVE_DOWN) && envState.getAgentLocation().equals(LOCATION_A)) {
			envState.setAgentLocation(LOCATION_C);
			envState.getLocationState(envState.getAgentLocation());	
		}
		if	   (action.equals(MOVE_DOWN) && envState.getAgentLocation().equals(LOCATION_B)) {
			envState.setAgentLocation(LOCATION_D);
			envState.getLocationState(envState.getAgentLocation());	
		}
		
		
		if(action.equals(SUCK_DIRT)){
			envState.setLocationState(envState.getAgentLocation(),LocationState.CLEAN);
		}
		return envState;
	}

	// get percept<AgentLocation, LocationState> at the current location where agent
	// is in.
	public Percept getPerceptSeenBy() {
		
		
		return new Percept(envState.getAgentLocation(), envState.getLocationState(envState.getAgentLocation()));
	}

	public void step() {
		envState.display();
		String agentLocation = this.envState.getAgentLocation();
		Action anAction = agent.execute(getPerceptSeenBy());
		EnvironmentState es = executeAction(anAction);

		System.out.println("Agent Loc.: " + agentLocation + "\tAction: " + anAction);

		if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_B) == LocationState.CLEAN) 
					&& (es.getLocationState(LOCATION_C) == LocationState.CLEAN)
						&& (es.getLocationState(LOCATION_D) == LocationState.CLEAN))
			isDone = true;// if all squares are clean, then agent do not need to do any action
		es.display();
	}

	public void step(int n) {
		for (int i = 0; i < n; i++) {
			step();
			System.out.println("-------------------------");
		}
	}

	public void stepUntilDone() {
		int i = 0;

		while (!isDone) {
			System.out.println("step: " + i++);
			step();
		}
	}
}
