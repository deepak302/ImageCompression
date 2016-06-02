package com.mentobile.imagecompression;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGEncodeParam;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;

import com.sun.media.imageio.plugins.tiff.BaselineTIFFTagSet;
import com.sun.media.imageio.plugins.tiff.TIFFTag;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncodeParam;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.JPEGEncodeParam;
import com.sun.media.jai.codec.TIFFEncodeParam;
import com.sun.media.jai.codec.TIFFField;
import com.sun.media.jai.codecimpl.JPEGCodec;
import com.sun.media.jai.codecimpl.JPEGImageEncoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;

//import java.awt.image.BufferedImage;
//import java.awt.image.RenderedImage;
//import java.io.File;
//import java.io.FileOutputStream;
//
//import javax.imageio.ImageIO;
//import javax.media.jai.JAI;
//import javax.media.jai.RenderedOp;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button btnGrayScale;
    private Button btnFront;
    private Button btnReverse;

    private Bitmap bmpImage;
    private ImageView imageView;

    private static int DPI_X = 200;
    private static int DPI_Y = 200;

    String path = "D:\\Project\\Android_Workspace\\ImageCompression\\output\\";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imgv_demo);

        btnGrayScale = (Button) findViewById(R.id.btn_GrayScaleImage);
        btnGrayScale.setOnClickListener(this);

        btnFront = (Button) findViewById(R.id.btn_FrontBlackWhite);
        btnFront.setOnClickListener(this);

        btnReverse = (Button) findViewById(R.id.btn_ReverseBlackWhite);
        btnReverse.setOnClickListener(this);

        bmpImage = BitmapFactory.decodeResource(getResources(), R.mipmap.demo);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_GrayScaleImage:
                convertImage_To_JFIF();
                break;
            case R.id.btn_FrontBlackWhite:
//                convertImage_To_TFIF("demo.jpg");
                break;
            case R.id.btn_ReverseBlackWhite:
                break;
        }
    }

    private void convertImage_To_JFIF() {

        bmpImage = BitmapFactory.decodeResource(getResources(), R.mipmap.demo);

//        Bitmap newBitmap = GrayScaleImage.ConvertToGrayscale(bmpImage);
//        imageView.setImageBitmap(newBitmap);

        String root = Environment.getExternalStorageDirectory().toString();
//        File myDir = new File(root + "/saved_images");
//        myDir.mkdirs();
        File file = new File(root, "NewFile26.jpg");
        Bitmap newBitmap = ShrinkBitmap(root + "/demo.jpg", 100, 100);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            newBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);

            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Bitmap ShrinkBitmap(String file, int width, int height) {

        BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
        bmpFactoryOptions.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(file, bmpFactoryOptions);

        int heightRatio = (int) Math.ceil(bmpFactoryOptions.outHeight / (float) height);
        int widthRatio = (int) Math.ceil(bmpFactoryOptions.outWidth / (float) width);

        if (heightRatio > 1 || widthRatio > 1) {
            if (heightRatio > widthRatio) {
                bmpFactoryOptions.inSampleSize = heightRatio;
            } else {
                bmpFactoryOptions.inSampleSize = widthRatio;
            }
        }

        bmpFactoryOptions.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(file, bmpFactoryOptions);
        return bitmap;
    }

    private void convertImage_To_TFIF(String name) {
        try {
//            FileOutputStream fos = new FileOutputStream(path + "output_android.tif");
//            RenderedOp src = JAI.create("fileload", path + name);

//            Bitmap bitmap = new Bitmap(src.getWidth(), src.getHeight(),Bitmap.DENSITY_NONE);
//
//            BufferedImage buf = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
//
//            buf.getGraphics().drawImage(src.getAsBufferedImage(), 0, 0, null);
//
//            RenderedImage ri = (RenderedImage) buf;
//
//            TIFFEncodeParam encodeParam = new TIFFEncodeParam();
//            encodeParam.setCompression(TIFFEncodeParam.COMPRESSION_GROUP4);
//
//            BaselineTIFFTagSet base = BaselineTIFFTagSet.getInstance();
//            TIFFField[] extras1 = new TIFFField[2];
//            extras1[0] = new TIFFField(282, TIFFTag.TIFF_RATIONAL, 1, new long[][]{{DPI_X, 1}});
//            extras1[1] = new TIFFField(283, TIFFTag.TIFF_RATIONAL, 1, new long[][]{{DPI_Y, 1}});
//            encodeParam.setExtraFields(extras1);
//
//            ImageEncoder enc = ImageCodec.createImageEncoder("TIFF", fos, encodeParam);
//            enc.encode(ri);
//
//            System.out.println("Image has been successfully converted");

        } catch (Exception e) {
            System.out.println("Exception " + e.getMessage());
        }
    }

 //   public native String helloJNI();
//
//    static {
//        System.loadLibrary("HelloJNI");
//    }

}
