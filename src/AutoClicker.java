import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.lang.System.exit;

public class AutoClicker extends JFrame {

    private static ClickerRunnable clickerRunnable;
    private static SpacebarRunnable spacebarRunnable;
    private static JButton leftClickButton;
    private static JButton spaceButton;
    private static boolean clickIsRunning;
    private static boolean spaceIsRunning;

    private AutoClicker() {
        super("mac-0.2.0");
        super.setAutoRequestFocus(false);
        setSize( 275, 100 );
        setVisible(true);

        leftClickButton = new JButton("Not Clicking");
        spaceButton = new JButton("Not Pressing");

        Container container = getContentPane();
        container.setLayout(new GridLayout(0, 2));
        container.add(leftClickButton);
        container.add(spaceButton);

        LeftClickButtonHandler leftClickButtonHandler = new LeftClickButtonHandler();
        leftClickButton.addActionListener(leftClickButtonHandler);

        SpacePressButtonHandler spacePressButtonHandler = new SpacePressButtonHandler();
        spaceButton.addActionListener(spacePressButtonHandler);

        leftClickButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_O, 0), "SpaceHotKey");
        leftClickButton.getActionMap().put("SpaceHotKey", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LeftClickButtonHandler handler = new LeftClickButtonHandler();
                handler.actionPerformed(e);
            }
        });

        spaceButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), "SpaceHotKey");
        spaceButton.getActionMap().put("SpaceHotKey", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SpacePressButtonHandler handler = new SpacePressButtonHandler();
                handler.actionPerformed(e);
            }
        });
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

    private class LeftClickButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (clickIsRunning) {
                leftClickButton.setText("Not Clicking");
                clickIsRunning = false;
                clickerRunnable.stop();
            } else {
                leftClickButton.setText("Clicking");
                clickIsRunning = true;
                clickerRunnable = new ClickerRunnable();
                Thread process = new Thread(clickerRunnable);
                process.start();
            }
        }
    }

    private class SpacePressButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (spaceIsRunning) {
                spaceButton.setText("Not Pressing");
                spaceIsRunning = false;
                spacebarRunnable.stop();
            } else {
                spaceButton.setText("Pressing");
                spaceIsRunning = true;
                spacebarRunnable = new SpacebarRunnable();
                Thread process = new Thread(spacebarRunnable);
                process.start();
            }
        }
    }
}
