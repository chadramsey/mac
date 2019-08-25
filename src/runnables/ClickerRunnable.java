package runnables;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ClickerRunnable implements Runnable {

    private Robot bot;
    private volatile boolean shutdown = false;
    private static final double SECONDS_DELAY = 1.0;

    public void stop() {
        shutdown = true;
    }

    @Override
    public void run() {
        createMacro();
        while (!shutdown) {
            delay();
            if (!shutdown) {
                leftClick();
            }
        }
    }

    private void delay() {
        bot.delay((int)(SECONDS_DELAY * 1000.0));
    }

    private void leftClick() {
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