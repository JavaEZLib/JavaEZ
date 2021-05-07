import static io.github.redstoneboy0509.javaez.extensions.Core.*;
import static io.github.redstoneboy0509.javaez.extensions.Files.*;

public class TestO {

    public static void main(String[] args) {
        openFileForWriting("test.txt", true);
        writeLine("Hello");
        closeFile();
        openFileForReading("test.txt");
        say(readLine());
        closeFile();
    }

}
