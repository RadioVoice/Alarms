package alarms;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;

public class AlarmSystem {

	private List<State> states = new ArrayList<>();
	private EnumMap<CameraDirection, CameraView> originalViewMap;
	private EnumMap<CameraDirection, List<CameraView>> possibleShiftsMap;

	// constructor
	public AlarmSystem(List<State> states) {
		this.states = states;
		originalViewMap = states.get(0).getViewMap();
		EnumSet.allOf(CameraDirection.class).forEach(direction -> possibleShiftsMap.put(direction,
				CameraView.possibleShiftedViews(originalViewMap.get(direction))));
	}

	// check front, side, top view separately for each frame.
	boolean shouldAlarmSound(int index) {
		boolean returnBoolean = false;
		for (CameraDirection direction : CameraDirection.values()) { 
			returnBoolean = returnBoolean || isAlarmInDirection(direction, index);
			if (returnBoolean) {
				break;
			}
        }
		return returnBoolean;
	}

	// check if the view equals to or is a shift from the original one
	private boolean isAlarmInDirection(CameraDirection direction, int index) {
		CameraView view = states.get(index).getViewMap().get(direction);
		if (!view.equals(originalViewMap.get(direction)) && !possibleShiftsMap.get(direction).contains(view)) {
			return isViewChangePermanent(direction, index);
		}
		return false;
	}

	// check if the view change permanently
	private boolean isViewChangePermanent(CameraDirection direction, int index) {
		CameraView view;
		int i = index + 1;
		while (i < states.size() && i <= index + 5) {
			view = states.get(i).getViewMap().get(direction);
			if (view.equals(originalViewMap.get(direction)) || possibleShiftsMap.get(direction).contains(view)) {
				return false;
			}
		}
		return true;
	}
	
	// boolean isCameraOff(alarms.State)
	// checks for any null CameraViews in the state
}
