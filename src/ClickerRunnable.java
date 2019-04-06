import java.awt.*;
import java.awt.event.MouseEvent;

public class ClickerRunnable implements Runnable {

    private Robot bot;
    private volatile boolean shutdown = false;

    void stop() {
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
        bot.delay((int)(seconds * 1000.0));
    }

    private void leftClick() {
        System.out.println("Click");
        bot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
    }

    private void createMacro() {
        try {
            bot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}