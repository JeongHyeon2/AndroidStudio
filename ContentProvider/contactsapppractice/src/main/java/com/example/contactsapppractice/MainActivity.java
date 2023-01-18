package com.example.contactsapppractice;

import android.Manifest;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button contactBtn;
    Button galleryBtn;
    LinearLayout mainContent;

    int reqWidth;
    int reqHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactBtn=(Button)findViewById(R.id.lab2_contacts);
        galleryBtn=(Button)findViewById(R.id.lab2_gallery);
        mainContent=(LinearLayout)findViewById(R.id.lab2_content);

        contactBtn.setOnClickListener(this);
        galleryBtn.setOnClickListener(this);

        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        reqWidth = metrics.widthPixels;
        reqHeight = metrics.heightPixels;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 100);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        }

    }

    // 사용자가 선택한 파일의 경로를 매개변수로 넘기면 해당 파일을 ImageView로 만들어 화면에 출력
    private void insertImageView(String filePath) {
        if(!filePath.equals("")){
            File file = new File(filePath);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            try{
                InputStream in = new FileInputStream(filePath);
                BitmapFactory.decodeStream(in,null,options);
                in.close();
                in= null;
            }catch (Exception e){
                e.printStackTrace();
            }
            final int width = options.outWidth;
            int inSampleSize = 1;

            if(width>reqWidth){
                int widthRatio = Math.round((float) width/(float) reqWidth);
                inSampleSize = widthRatio;
            }
            BitmapFactory.Options imgOptions = new BitmapFactory.Options();
            imgOptions.inSampleSize = inSampleSize;
            Bitmap bitmap = BitmapFactory.decodeFile(filePath,imgOptions);

            ImageView imageView = new ImageView(this);
            imageView.setImageBitmap(bitmap);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            mainContent.addView(imageView,params);
        }

    }
    // 도큐먼트식 Uri 값에서 사용자가 선택한 이미지 파일 경로를 뢱득하기 위한 함수
    private String getFilePathFromDocumentUri(Context context, Uri uri){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
            String docId = DocumentsContract.getDocumentId(uri);
//docId = content://com.android.providers.media.documents/document/image:3A35260
            String[] split = docId.split(":");
            String type = split[0];
            Uri contentUri = null;
            if("image".equals(type)){ // video, audio 타입도 가능
                contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            }
            String selection = MediaStore.Images.Media._ID+"=?";
            String[] selectionArgs = new String[]{split[1]};

            String column = "_data";
            String[] projection = {column};

            Cursor cursor = getApplicationContext().getContentResolver().query(contentUri,projection,selection,selectionArgs,null);
            String filePath = null;
            if(cursor!=null && cursor.moveToFirst()){
                int column_index = cursor.getColumnIndexOrThrow(column);
                filePath = cursor.getString(column_index);
            }
            cursor.close();
            return  filePath;
        }else{
            return null;
        }
    }

    private String getFilePathFromUriSegment(Uri uri){

        return null;
    }

    @Override
    public void onClick(View v) {
        if(v==contactBtn){

        }else if(v==galleryBtn){
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent,30);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 30) {
            ClipData clipData = data.getClipData();

            for (int i = 0; i < clipData.getItemCount(); i++) {
                ClipData.Item item = clipData.getItemAt(i);
                Uri uri = item.getUri();
                if ("com.android.providers.media.documents".equals(uri.getAuthority()) && Build.VERSION.SDK_INT >= 19) {
                    String filePath = getFilePathFromDocumentUri(this, uri);
                    if (filePath != null) {
                        insertImageView(filePath);
                    }
                }else{
                    Uri uri1 = data.getData();
                    String filePath = getFilePathFromDocumentUri(this,uri1);
                }
            }
        }

    }

}

