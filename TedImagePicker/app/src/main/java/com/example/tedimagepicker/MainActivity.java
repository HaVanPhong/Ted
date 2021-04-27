package com.example.tedimagepicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudinary.Cloudinary;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gun0912.tedbottompicker.TedBottomPicker;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btnSelect, btnCreat;
    EditText edt_username, edt_password;
    ImageView image;
    RecyclerView recv;
    PhotoAdapter photoAdapter;

    int request_code_Image = 123;
    String realPath = null;

    //cloudinary
    Map config = new HashMap();
    TextView mText;
    String avtLink = null;

    public void configCloudinary() {
        config.put("cloud_name", "djowq0mq4");
        config.put("api_key", "262823481682934");
        config.put("api_secret", "BEuY5pxfnwkTdifGNTBQHJjpZwU");

        MediaManager.init(this, config);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        configCloudinary();


        photoAdapter = new PhotoAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        recv.setLayoutManager(gridLayoutManager);
        recv.setFocusable(false);


        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissions();
            }
        });
        btnCreat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createProduct();
//                UploadFileImgToCloudinaryAndGetLink(realPath);
            }
        });
    }

    private void createProduct() {
        String username = edt_username.getText().toString().trim();
        String password = edt_password.getText().toString().trim();

        File file = new File(realPath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("avt", file.getName(), requestBody);

        NetWorkClient.net.createAccount(username, password, fileToUpload).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "fail: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


//    private void createProduct() {
//        UploadFileImgToCloudinaryAndGetLink(realPath);
//        avtLink;


    //cái này post báo lỗi đỏ chót
//        File file = new File(realPath);
//        String file_path= file.getAbsolutePath();
//
//        RequestBody requestBody =RequestBody.create(MediaType.parse("multipart/form-data"), file_path);
//
//        MultipartBody.Part body = MultipartBody.Part.createFormData(file.getName(), requestBody.toString(),requestBody);


    // cái này post báo thành công nhưng lại k thấy xuất hiện trên server
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile(realPath, options);
//        options.inSampleSize = 4;
//        options.inJustDecodeBounds = false;
//
//        Bitmap bm = BitmapFactory.decodeFile(realPath, options);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bm.compress(Bitmap.CompressFormat.JPEG, 50, baos); //bm is the bitmap object
//        byte[] b = baos.toByteArray();
//
//        NetWorkClient.net.createAccount(username, password, b).enqueue(new Callback<Account>() {
//            @Override
//            public void onResponse(Call<Account> call, Response<Account> response) {
//                Toast.makeText(MainActivity.this, "thành công", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<Account> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "thất bại", Toast.LENGTH_SHORT).show();
//
//            }
//        });


//    }


    //up ảnh lên cloudinary rồi lấy link up lên server
    public void UploadFileImgToCloudinaryAndGetLink(String path) {
        mText.setVisibility(View.VISIBLE);

        MediaManager.get().upload(path).callback(new UploadCallback() {
            @Override
            public void onStart(String requestId) {
                mText.setText("start");
            }

            @Override
            public void onProgress(String requestId, long bytes, long totalBytes) {
                mText.setText("Uploading... ");
            }

            @Override
            public void onSuccess(String requestId, Map resultData) {
                avtLink = resultData.get("url").toString();
                mText.setText("image URL: " + avtLink);

                String username = edt_username.getText().toString().trim();
                String password = edt_password.getText().toString().trim();

                AccountBody accountBody = new AccountBody(username, password, avtLink);




            }

            @Override
            public void onError(String requestId, ErrorInfo error) {
                mText.setText("error " + error.getDescription());
            }

            @Override
            public void onReschedule(String requestId, ErrorInfo error) {
                mText.setText("Reshedule " + error.getDescription());
            }
        }).dispatch();
    }


    private void AnhXa() {
        btnSelect = findViewById(R.id.btnSelect);
        image = findViewById(R.id.image);
        recv = findViewById(R.id.recv);
        btnCreat = findViewById(R.id.btnCreate);

        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);

        mText = findViewById(R.id.mText);

    }


    //lấy ảnh từ Gallery
    private void requestPermissions() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                openImagePicker();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(MainActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();
    }

    private void openImagePicker() {
        TedBottomPicker.OnMultiImageSelectedListener listener = new TedBottomPicker.OnMultiImageSelectedListener() {
            @Override
            public void onImagesSelected(ArrayList<Uri> uriList) {
                photoAdapter.setData(uriList);
                recv.setAdapter(photoAdapter);
                realPath = uriList.get(0).getPath();

            }
        };

        TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(MainActivity.this)
                .setOnMultiImageSelectedListener(listener)
                .setSelectMaxCount(10)
                .setIncludeEdgeSpacing(true)
                .setCompleteButtonText("POST")
                .setTitle("Chọn ảnh")
                .create();
        tedBottomPicker.show(getSupportFragmentManager());
    }
}
