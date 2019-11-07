import javax.naming.directory.InvalidAttributeValueException;
import java.util.Objects;

public class CameraView {
    private final int width;
    private final int height;
    private final int[][] data;

    private CameraView(int x, int y) {
        width = x;
        height = y;
        data = new int[x][y];
    }

    public static CameraView of(int x, int y){
        assert(x>0 && y>0);
        return new CameraView(x, y);
    }

    public static CameraView of(int x, int y, String inputData){
        assert(x>0 && y>0);
        Objects.requireNonNull(inputData);

        CameraView returnView = new CameraView(x, y);
        byte[] inputBytes = inputData.getBytes();
        int byteIndex = 0;

        for (int j = 0; j < y; j++){
            for (int i = 0; i < x; i++){
                returnView.data[i][j] = inputBytes[byteIndex];
            }
        }

        return returnView;
    }

    void setValue(int x, int y, int value){
        data[x][y] = value;
    }

    int getValue(int x, int y){
        return data[x][y];
    }
}
