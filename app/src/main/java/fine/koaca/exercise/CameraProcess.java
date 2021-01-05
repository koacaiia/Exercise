package fine.koaca.exercise;

import android.hardware.Camera;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.IOException;

public class CameraProcess implements SurfaceHolder.Callback{
    Camera camera;
    MainActivity mainActivity;

    public CameraProcess(MainActivity mainActivity) {
        this.mainActivity=mainActivity;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        preViewProcess();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        preViewProcess();
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    public void preViewProcess(){
        mainActivity.holder.addCallback(this);
        mainActivity.holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        camera=Camera.open();
        int degree=getDegree();
        camera.setDisplayOrientation(degree);
        try {
            camera.setPreviewDisplay(mainActivity.holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        camera.startPreview();

    }
    public int getDegree(){
        WindowManager windowManager=mainActivity.getWindowManager();
        Display display=windowManager.getDefaultDisplay();
        int rotation=display.getRotation();
        int degree=0;
        switch(rotation){
            case Surface
                    .ROTATION_0:
                degree=90;
                break;
            case Surface.ROTATION_90:
                degree=0;
                break;
            case Surface.ROTATION_180:
                degree=270;
                break;
            case Surface.ROTATION_270:
                degree=180;
                break;

        }
        return degree;
    }

    public void setmAutoFocus(){

        camera.autoFocus(mAutoFocus);
    }
    Camera.AutoFocusCallback mAutoFocus=new Camera.AutoFocusCallback(){

        @Override
        public void onAutoFocus(boolean success, Camera camera) {
            Toast.makeText(mainActivity, "autofocus activated", Toast.LENGTH_SHORT).show();

        }
    };
}
