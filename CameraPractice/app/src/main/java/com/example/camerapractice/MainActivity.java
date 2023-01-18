package com.example.camerapractice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.content.PackageManagerCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaMetadataRetriever;
import android.media.metrics.MediaMetricsManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    RelativeLayout layout;
    ImageView plusImageView;
    File filePath;


    int reqWidth, reqHeight,flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.main_content);
        plusImageView = findViewById(R.id.main_photo_icon);
        plusImageView.setOnClickListener(this);

        reqWidth = getResources().getDimensionPixelSize(R.dimen.request_image_width);
        reqHeight =  getResources().getDimensionPixelSize(R.dimen.request_image_height);

    }
    // 다이얼로그 띄우기
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("촬영");
        builder.setItems(R.array.main_dialog_list, dialogListener);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    // 토스트 띄우기
    private void showToast(String message){
        Toast toast = Toast.makeText(MainActivity.this, message,Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onClick(View view) {
        // 권한 체크
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE )== PackageManager.PERMISSION_GRANTED){
            showDialog();
        }else{
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
        }
    }

    // 권한 요청
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100 && grantResults.length>0){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                showDialog();
            }
        }else{
            showToast("no permission");

        }
    }
    DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            if(i==0){
                try{ // 사진 촬영     // 카메라 기능을 Intent
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    // 사진파일 변수 선언 및 경로세팅
                    filePath = createImageFile();

                    // 사진을 저장하고 이미지뷰에 출력
                    if (filePath != null) {
                        Uri photoUri = FileProvider.getUriForFile(MainActivity.this, getPackageName() + ".provider", filePath);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                        startActivityForResult(intent, 10);
                    }
                } catch (Exception e){
                    e.printStackTrace();

                }
            }else if(i==1){ //동영상 촬영
                try{
                    File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

                    filePath = File.createTempFile("VIDEO","mp4",storageDir);
                    if(!filePath.exists()){
                        filePath.createNewFile();
                    }

                    Uri videoURI = FileProvider.getUriForFile(MainActivity.this,"com.example.camerapractice.provider",filePath);
                    Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,0);
                    intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,20);
                    intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT,1024*1024*10);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,videoURI);
                    startActivityForResult(intent,20);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }

    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10 && resultCode == RESULT_OK) {
            showImage();
            flag=0;

        } else if (requestCode == 20 && resultCode == RESULT_OK) {
            showVideo();
            flag=1;
        }
    }
    private void showVideo(){
        if (filePath != null) {
            VideoView videoView = new VideoView(this);
            videoView.setMediaController(new MediaController(this));
            Uri videoUri = Uri.parse(filePath.getAbsolutePath());

            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            Bitmap bitmap = null;
            retriever.setDataSource(filePath.getAbsolutePath());
            bitmap = retriever.getFrameAtTime();
            int videoHeight = bitmap.getHeight();
            int videoWidth = bitmap.getWidth();

            videoView.setVideoURI(videoUri);
            if (videoWidth >= videoHeight) {
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(reqWidth, reqHeight);
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                layout.addView(videoView, params);
            } else {
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(reqHeight, reqWidth);
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                layout.addView(videoView, params);
            }
            videoView.start();
        }
    }
    private void showImage(){

        if (filePath != null) { //사진 사이즈 줄여서 화면에 뿌리기
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;

            try {
                InputStream in = new FileInputStream(filePath);
                BitmapFactory.decodeStream(in, null, options);
                in.close();
                in = null;
            } catch (Exception e) {
                e.printStackTrace();
            }

            final int height = options.outHeight;
            final int width = options.outWidth;
            int inSampleSize = 1;

            if (height > reqHeight || width > reqWidth) {
                final int heightRatio = Math.round((float) height / (float) reqHeight);
                final int widthRatio = Math.round((float) width / (float) reqWidth);
                inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
            }
            BitmapFactory.Options imgOptions = new BitmapFactory.Options();
            imgOptions.inSampleSize = inSampleSize;
            Bitmap bitmap = BitmapFactory.decodeFile(filePath.getAbsolutePath(), imgOptions);


            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            Bitmap newBmp = Bitmap.createBitmap(bitmap, 0, 0,
                    bitmap.getWidth(), bitmap.getHeight(), matrix, true);

            ImageView imageView = new ImageView(this);

            imageView.setImageBitmap(newBmp);

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

            layout.addView(imageView, params);
        }

    }

    private File createImageFile() throws IOException {
        // 파일이름을 세팅 및 저장경로 세팅
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

        return image;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(filePath!=null) {
            outState.putString("filePath", filePath.getAbsolutePath());
            outState.putInt("flag", flag);
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null){
            String path = savedInstanceState.getString("filePath");
            flag = savedInstanceState.getInt("flag");

            if(path!=null){
                filePath = new File(path);
                if(flag==0)
                    showImage();
                else if(flag==1)
                    showVideo();
            }
        }
    }
}