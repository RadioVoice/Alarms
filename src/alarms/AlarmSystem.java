package alarms;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AlarmSystem {

	private List<State> states = new ArrayList<>();
	private List<CameraView> frontPossibleShifts;
	private List<CameraView> sidePossibleShifts;
	private List<CameraView> topPossibleShifts;

	// correct state of boxes

	private AlarmSystem(List<State> states) {
		this.states = states;
		frontPossibleShifts = CameraView.possibleShiftedViews(states.get(0).getFrontView());
		sidePossibleShifts = CameraView.possibleShiftedViews(states.get(0).getSideView());
		topPossibleShifts = CameraView.possibleShiftedViews(states.get(0).getTopView());
	}

	// main method(text file)
	// TRY {Creates inputhandler using text file} {CATCH invalid input exception}
	// print "invalid" and System.exit(-1)
	// Create alarms.AlarmSystem from alarms.InputHandler.states()
	// loop over the list of states:
	// check for changes in this state compared to previous
	// if yes, run through tests to determine cause
	// if alarm should sound, print "true" and System.exit(0)
	// if no, all good, continue loop
	// End of loop print "false"

	boolean shouldAlarmSound(int index) {
		State state = states.get(index);
    	return isAlarmInFront(state.getFrontView(), index) && isAlarmInSide(state.getSideView(), index) && isAlarmInTop(state.getTopView(), index);
    }
	
	private boolean isAlarmInFront(CameraView view, int index) {
		if(!view.equals(states.get(index - 1))) {
			
		}
		return true;
	}
	// runs through tests for correct camera directions, depending on tests either
	// true or false
	// if only error is shift, return false
	// if change is not permanent, return false
	// otherwise, return true

	public boolean isChangePermanent(List<State> states, int index) {
		Objects.requireNonNull(states);
		Objects.requireNonNull(index);
		int i = index + 1;
		while (i < states.size() && i <= index + 5) {
			if (states.get(index).equals(states.get(i))) {
				return false;
			}
		}
		return true;
	}
	// look ahead in the list of states and see if changes back to original, if not
	// then continue

	// boolean isCameraShifted()
	// compares state to all shifts of the correct state, if it is any shift then
	// return true

	// boolean isCameraOff(alarms.State)
	// checks for any null CameraViews in the state
}
