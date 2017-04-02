package com.company.main;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Lagrange on 3/21/2017.
 */
class GUI extends JComponent {


    public GUI(int width, int height, String title, ImageCanvas canvas) {

        Frame frame = new Frame(title);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.add(canvas);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static class ImageCanvas extends Canvas {

        private BufferedImage img;
        private BufferedImage referenceImage;

        public ImageCanvas(BufferedImage image, BufferedImage reference) {
            img = image;
            referenceImage = reference;

        }

        public void setPic(BufferedImage image){
            img = image;
        }
        public void setRef(BufferedImage reference){
            referenceImage = reference;
        }

        @Override
        public Dimension getPreferredSize() {
            return img == null ? new Dimension(200, 200) : new Dimension(img.getWidth(), img.getHeight());
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            if (img != null && referenceImage != null) {
                g.drawImage(referenceImage, img.getWidth(), 0, this);
                g.drawImage(img, 0, 0, this);
            }
        }

    }

}



