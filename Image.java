package com.company.main;

import javax.imageio.*;
import java.awt.*;
import java.net.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * Created by Lagrange on 3/20/2017.
 */

public class Image {

    public static void SaveImage(String url, String filename) throws Exception {
        String imageUrl = url;
        String destinationFile = filename;

        getImage(imageUrl, destinationFile);
    }

    public static void getImage(String imageUrl, String destinationFile) throws IOException {
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
    }




}
