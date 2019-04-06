import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.lang.System.exit;

public class AutoClicker extends JFrame {

    private static ClickerRunnable runnable = new ClickerRunnable();
    private static Thread process = new Thread(runnable);
    private static JButton plainButton = new JButton("Not Running");
    private static boolean isRunning;
    private static Robot macro;

    private AutoClicker() {
        super("AutoClicker");

        Container container = getContentPane();
        container.setLayout(new GridLayout());
        container.add(plainButton);

        ButtonHandler handler = new ButtonHandler();
        plainButton.addActionListener(handler);

        setSize( 275, 100 );
        setVisible(true);
    }

    public static void main(String[] args) {
        createMacro();
        process.start();
        AutoClicker app = new AutoClicker();
        app.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                exit(0);
            }
        });
    }

    private static void delay(double seconds) {
        System.out.println("Delay");
        macro.delay((int)(seconds * 1000.0));
    }

    private static void leftClick() {
        System.out.println("Click");
        macro.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
        macro.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
    }

    private static void createMacro() {
        try {
            macro = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (isRunning) {
                plainButton.setText("Not Running");
                isRunning = false;
                runnable.stop();
            } else {
                plainButton.setText("Running");
                isRunning = true;
                runnable.start();
            }
        }
    }

    public static class ClickerRunnable implements Runnable {
        private boolean isStopped = false;

        private synchronized void stop() {
            System.out.println("Stopping");
            isStopped = true;
        }

        private synchronized void start() {
            System.out.println("Starting");
            isStopped = false;
        }

        private synchronized boolean isRunning() {
            return !isStopped;
        }

        @Override
        public void run() {
            System.out.println("Thread started");
            while (isRunning()) {
                System.out.println("Is running");
                delay(5.5);
                leftClick();
            }
        }
    }
}
