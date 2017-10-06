package dev.tilegame.display;

import javax.swing.*;
import java.awt.*;

public class Display {

    //declarations
    private JFrame frame;
    private Canvas canvas;
    private String title;
    private int width, height;

    //Display Constructor
    public Display(String title, int width, int height) {
        frame =new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        //canvas is temp "art diplay" that is not visible until "framed" in JFrame
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));

        //pack makes sure canvas is completely visible in frame
        frame.add(canvas);
        frame.pack();
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
