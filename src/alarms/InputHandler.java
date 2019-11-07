package alarms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputHandler {
    int x, y, z;
    private ArrayList<CameraView> frames;

    //Private Constructor (txt file)
    //  takes from file and stores in local vars, separates each space/line as needed
    //  takes dimensions -> x, y, z
    //  takes rest of file and splits on space or line break -> Frames

    private InputHandler(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        x = Integer.parseInt(scan.findInLine("\\d"));
        y = Integer.parseInt(scan.findInLine("\\d"));
        z = Integer.parseInt(scan.findInLine("\\d"));
        while(scan.hasNextLine()){
            String[] frameSet = scan.nextLine().split(" ");
            CameraView front = CameraView.of(x, z, CameraDirection.FRONT, frameSet[0]);
            CameraView side = CameraView.of(y, z, CameraDirection.SIDE, frameSet[0]);
            CameraView top = CameraView.of(x, y, CameraDirection.TOP, frameSet[0]);
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


    //List<alarms.State> states
    //  repeatably takes 3 Strings from Frames and makes a alarms.State, then returns a list of States
}
