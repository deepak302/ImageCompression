package com.mentobile.imagecompression;

//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.Iterator;
//
//import java.awt.Canvas;
//import java.awt.Graphics2D;
//import java.awt.Image;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.Iterator;
//import javax.imageio.ImageIO;
//import javax.imageio.ImageReadParam;
//import javax.imageio.ImageReader;
//import javax.imageio.stream.ImageInputStream;


import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Build;

import com.sun.media.jai.codecimpl.JPEGCodec;
import com.sun.media.jai.codecimpl.JPEGImageEncoder;

import javax.media.jai.RenderedImageAdapter;

/**
 * Created by user on 11/5/2015.
 */

public class GrayScaleImage {



    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static Bitmap ConvertToGrayscale(Bitmap bitmap) {

        int height = bitmap.getHeight();
        int width = bitmap.getWidth();

        float[] arrayForColorMatrix = new float[] {
                0, 0, 1, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0};

        Bitmap.Config config = bitmap.getConfig();
        Bitmap grayScaleBitmap = Bitmap.createBitmap(width, height, config.ARGB_8888);
        Canvas c = new Canvas(grayScaleBitmap);
        Paint paint = new Paint();

        ColorMatrix matrix = new ColorMatrix(arrayForColorMatrix);
        matrix.setSaturation(0);

        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);

        paint.setColorFilter(filter);

        c.drawBitmap(bitmap, 0, 0, paint);

        return grayScaleBitmap;
    }

//    public static BufferedImage createGrayScaleImage(String inputPath) {
//        File inputFile = null;
//        try {
//            inputFile = new File(inputPath);
//            FileInputStream fis = new FileInputStream(inputFile);
//
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            byte[] buf = new byte[1024];
//
//            for (int readNum; (readNum = fis.read(buf)) != -1; ) {
//                //Writes to this byte array output stream
//                bos.write(buf, 0, readNum);
//            }
//
//            byte[] bytes = bos.toByteArray();
//
//
//            //2. How to convert byte array back to an image file ?
//
//            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
//            Iterator<?> readers = ImageIO.getImageReadersByFormatName("jpg");
//
//            //ImageIO is a class containing static methods for locating ImageReaders
//            //and ImageWriters, and performing simple encoding and decoding.
//            ImageReader reader = (ImageReader) readers.next();
//            Object source = bis;
//            ImageInputStream iis = ImageIO.createImageInputStream(source);
//            reader.setInput(iis, true);
//            ImageReadParam param = reader.getDefaultReadParam();
//
//            Image image = reader.read(0, param);
//            //got an image file
//
//            BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_BYTE_GRAY);
//            //bufferedImage is the RenderedImage to be written
//
//            Canvas canvas = new Canvas();
//
//            Graphics2D g2 = bufferedImage.createGraphics();
//            g2.drawImage(image, null, null);
//
//            return bufferedImage;
//
//        } catch (Exception e) {
//
//        }
//        return null;
//    }
}
