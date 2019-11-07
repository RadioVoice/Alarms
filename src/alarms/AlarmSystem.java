package alarms;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AlarmSystem {
    
	private List<State> states = new ArrayList<>();
    private List<CameraView> possibleShifts;



    //correct state of boxes

    //Private constructor(List<alarms.State>)

    //main method(text file)
    //  TRY {Creates inputhandler using text file} {CATCH invalid input exception} print "invalid" and System.exit(-1)
    //  Create alarms.AlarmSystem from alarms.InputHandler.states()
    //  loop over the list of states:
    //      check for changes in this state compared to previous
    //          if yes, run through tests to determine cause
    //              if alarm should sound, print "true" and System.exit(0)
    //          if no, all good, continue loop
    //  End of loop print "false"

    //boolean shouldAlarmSound(list<alarms.State>, index)
    //  runs through tests for correct camera directions, depending on tests either true or false
    //  if only error is shift, return false
    //  if change is not permanent, return false
    // otherwise, return true

    public boolean isChangePermanent(List<State> states, int index) {
    	Objects.requireNonNull(states);
    	Objects.requireNonNull(index);
    	int i = index + 1;
    	while(i < states.size() && i <= index + 5) {
    		if (states.get(index).equals(states.get(i))) {
    			return false;
    		}
    	}
    	return true;
    }
    //  look ahead in the list of states and see if changes back to original, if not then continue

    //boolean isCameraShifted()
    //  compares state to all shifts of the correct state, if it is any shift then return true

    //boolean isCameraOff(alarms.State)
    //  checks for any null CameraViews in the state
}
