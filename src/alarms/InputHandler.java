package alarms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputHandler {
    private final int x, y, z;
    private ArrayList<State> states;

    //Private Constructor (txt file)
    //  takes from file and stores in local vars, separates each space/line as needed
    //  takes dimensions -> x, y, z
    //  takes rest of file and splits on space or line break -> states

    private InputHandler(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        x = Integer.parseInt(scan.findInLine("\\d"));
        y = Integer.parseInt(scan.findInLine("\\d"));
        z = Integer.parseInt(scan.findInLine("\\d"));
        while(scan.hasNextLine()) {
            String[] frameSet = scan.nextLine().split(" ");
            CameraView front = CameraView.of(x, z, CameraDirection.FRONT, frameSet[0]);
            CameraView side = CameraView.of(y, z, CameraDirection.SIDE, frameSet[1]);
            CameraView top = CameraView.of(x, y, CameraDirection.TOP, frameSet[2]);
            states.add(State.of(front, side, top));
        }
    }

    //Public Build method (txt file)
    //  checks for valid input, catches all exceptions and throws
    //  uses constructor

    //boolean checkValidInput()
    //In any of these cases, throw invalid input exception
    //  is there 3 dimensions on the first line and nothing else
    //  are there any negative numbers
    //  does each line after contain 3 "words" separated by spaces?
    //  Does each line only contain 0 and 1?
    //  check size of each view and make sure it matches what it should be (in sets of 3)

    ArrayList<State> getStates(){
        return states;
    }
}
