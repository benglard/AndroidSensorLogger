package com.sstp.androidsensorlogger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.util.Log;
 
public class CameraSWAGActivity extends Activity {
    /** Called when the activity is first created. */
        private static final String TAG = "AMEE LD";
        Camera camera;
        private int pcounter;

        CameraSWAGActivity(int picCounter) {
        	pcounter = picCounter;
        	onCreate(null);
        }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        try
        {
                Camera camera = Camera.open();
                Camera.Parameters parameters = camera.getParameters();
                camera.setParameters(parameters);
                camera.startPreview();
                camera.setPreviewDisplay(null);
                camera.takePicture(null, rawCallback,jpegCallback );
                Log.d(TAG,"Cam ABERTA");
        }
        catch (Exception e)
        {
                        Log.d(TAG,"ERRO" + e.getMessage());
                }
    }
    ShutterCallback shutterCallback = new ShutterCallback() {
                public void onShutter() {
                        Log.d(TAG, "onShutter'd");
                }
        };
 
        /** Handles data for raw picture */
        PictureCallback rawCallback = new PictureCallback() {
                public void onPictureTaken(byte[] data, Camera camera) {
                        Log.d(TAG, "onPictureTaken - raw");
                }
        };
 
        /** Handles data for jpeg picture */
        PictureCallback jpegCallback = new PictureCallback() {
                public void onPictureTaken(byte[] data, Camera camera) {
                        FileOutputStream outStream = null;
                        try {
                                camera.startPreview();
                                // write to local sandbox file system
//                              outStream = CameraDemo.this.openFileOutput(String.format("%d.jpg", System.currentTimeMillis()), 0);    
                                // Or write to sdcard
                                outStream = new FileOutputStream(String.format("/sdcard/%d.jpg", pcounter)); 
                                outStream.write(data);
                                outStream.close();
                                Log.d(TAG, "onPictureTaken - wrote bytes: " + data.length);
                        } catch (FileNotFoundException e) {
                                e.printStackTrace();
                        } catch (IOException e) {
                                e.printStackTrace();
                        } finally {
                                camera.stopPreview();
                                camera.release();
                        }
                        Log.d(TAG,"onPictureTaken - jpeg");
                }
        };
       
}