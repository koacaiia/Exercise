package fine.koaca.exercise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String[] permission_list={Manifest.permission.CAMERA};
    RecyclerView recyclerView;
    SurfaceView surfaceView;
    SurfaceHolder holder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions(permission_list,0);

        recyclerView=findViewById(R.id.recyclerView);
        surfaceView=findViewById(R.id.surfaceView);
        holder=surfaceView.getHolder();
        CameraProcess process=new CameraProcess(this);
        surfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process.setmAutoFocus();
                Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();


            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for(int result:grantResults){
            if(result== PackageManager.PERMISSION_DENIED){
                Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show();
            }
        }
        Toast.makeText(this,"permission successed",Toast.LENGTH_SHORT).show();
        CameraProcess process=new CameraProcess(this);
        process.preViewProcess();

    }
}