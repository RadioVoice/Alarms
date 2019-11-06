public class AlarmSystem {
    //private List of States
    //correct state of boxes

    //Private constructor(List<State>)

    //main method(text file)
    //  TRY {Creates inputhandler using text file} {CATCH invalid input exception} print "invalid" and System.exit(-1)
    //  Create AlarmSystem from InputHandler.states()
    //  loop over the list of states:
    //      check for changes in this state compared to previous
    //          if yes, run through tests to determine cause
    //              if alarm should sound, print "true" and System.exit(0)
    //          if no, all good, continue loop
    //  End of loop print "false"

    //boolean shouldAlarmSound(list<State>, index)
    //  runs through tests for correct camera directions, depending on tests either true or false
    //  if only error is shift, return false
    //  if change is not permanent, return false
    // otherwise, return true

    //boolean isChangePermanent(list<state>, index)
    //  look ahead in the list of states and see if changes back to original, if not then continue

    //boolean isCameraShifted()
    //  compares state to all shifts of the correct state, if it is any shift then return true

    //boolean hasFloating(State)
    //  only applies to front and side cameras

    //boolean isCameraOff(State)
    //  checks for any null CameraViews in the state
}
