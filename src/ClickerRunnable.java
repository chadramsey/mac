import java.awt.*;
import java.awt.event.MouseEvent;

public class ClickerRunnable implements Runnable {
    private Robot macro;
    private volatile boolean shutdown = false;

    public void stop() {
        shutdown = true;
    }

    @Override
    public void run() {
        createMacro();
        while (!shutdown) {
            delay(1.5);
            if (!shutdown) {
                leftClick();
            }
        }
    }

    private void delay(double seconds) {
        macro.delay((int)(seconds * 1000.0));
    }

    private void leftClick() {
        System.out.println("Click");
        macro.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
        macro.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
    }

    private void createMacro() {
        try {
            macro = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}