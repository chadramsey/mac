import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class SpacebarRunnable implements Runnable {

    private Robot bot;
    private volatile boolean shutdown = false;
    private static final double SECONDS_DELAY = 0.5;

    void stop() {
        shutdown = true;
    }

    @Override
    public void run() {
        createMacro();
        while (!shutdown) {
            delay();
            if (!shutdown) {
                spacePress();
            }
        }
    }

    private void delay() {
        bot.delay((int)(SECONDS_DELAY * 1000.0));
    }

    private void spacePress() {
        bot.keyPress(KeyEvent.VK_SPACE);
        bot.delay((200));
        bot.keyRelease(KeyEvent.VK_SPACE);
    }

    private void createMacro() {
        try {
            bot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}