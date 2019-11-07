package alarms;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputHandler {
    private final int x, y, z;
    private ArrayList<State> states;

    private InputHandler(ArrayList<State> stateList, int xDim, int yDim, int zDim) {
        x = xDim;
        y = yDim;
        z = zDim;
        states = stateList;
    }

    public static InputHandler of(File file) throws FileNotFoundException{
        int xDim, yDim, zDim;
        ArrayList<State> stateList = new ArrayList<State>();

        Scanner scan = new Scanner(file);
        requireValidInput(scan);

        xDim = Integer.parseInt(scan.findInLine("\\d"));
        yDim = Integer.parseInt(scan.findInLine("\\d"));
        zDim = Integer.parseInt(scan.findInLine("\\d"));

        while(scan.hasNextLine()) {
            String[] frameSet = scan.nextLine().split(" ");
            CameraView front = CameraView.of(xDim, zDim, CameraDirection.FRONT, frameSet[0]);
            CameraView side = CameraView.of(yDim, zDim, CameraDirection.SIDE, frameSet[1]);
            CameraView top = CameraView.of(xDim, yDim, CameraDirection.TOP, frameSet[2]);
            stateList.add(State.of(front, side, top));
        }
        return new InputHandler(stateList, xDim, yDim, zDim);
    }

    //boolean checkValidInput()
    //In any of these cases, throw invalid input exception
    //  is there 3 dimensions on the first line and nothing else
    //  are there any negative numbers
    //  does each line after contain 3 "words" separated by spaces?
    //  Does each line only contain 0 and 1?
    //  check size of each view and make sure it matches what it should be (in sets of 3)

    static void requireValidInput(Scanner scan){
        String[] dimensions = scan.nextLine().split(" ");
        //check size of set
        //check for all positive numbers
        while (scan.hasNextLine()){
            String[] views = scan.nextLine().split(" ");
            //check size of set
            //check only 0 or 1 in each string
            //check string [0] is x*z, string[1] is y*z, string [2] is x*y
        }
    }

    ArrayList<State> getStates(){
        return states;
    }
    int getX(){
        return x;
    }
    int getY(){
        return y;
    }
    int getZ(){
        return z;
    }
}
