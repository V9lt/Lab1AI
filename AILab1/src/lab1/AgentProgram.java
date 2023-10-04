package lab1;
import java.util.Random;


public class AgentProgram {

	public Action execute(Percept p) {
		if (p.getLocationState().equals(Environment.LocationState.DIRTY) ) {
			return Environment.SUCK_DIRT;
		}
		Random r = new Random();
		switch (r.nextInt(3)+1) {
		case 1:
			return Environment.MOVE_LEFT;
		case 2:
			return Environment.MOVE_RIGHT;
		case 3:
			return Environment.MOVE_UP;
		case 4:
			return Environment.MOVE_DOWN;
		}	
		
//		if(p.getAgentLocation().equals(Environment.LOCATION_A)) {
//			return Environment.MOVE_RIGHT;
//		}
//		if(p.getAgentLocation().equals(Environment.LOCATION_B)) {
//			return Environment.MOVE_LEFT;
//		}
			
		return NoOpAction.NO_OP;
		
	}
}