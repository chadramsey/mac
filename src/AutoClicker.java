import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.lang.System.exit;

public class AutoClicker extends JFrame {

    private static ClickerRunnable runnable;
    private static Thread process;
    private static JButton button;
    private static boolean isRunning;

    private AutoClicker() {
        super("MAC");

        button = new JButton("Not Running");

        Container container = getContentPane();
        container.setLayout(new GridLayout());
        container.add(button);

        ButtonHandler handler = new ButtonHandler();
        button.addActionListener(handler);

        setSize( 275, 100 );
        setVisible(true);
    }

    public static void main(String[] args) {
        AutoClicker app = new AutoClicker();
        app.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                exit(0);
            }
        });
    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (isRunning) {
                button.setText("Not Running");
                isRunning = false;
                runnable.stop();
            } else {
                button.setText("Running");
                isRunning = true;
                runnable = new ClickerRunnable();
                process = new Thread(runnable);
                process.start();
            }
        }
    }
}
