package com.frumentiusdaneswara.tubes_uts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    public static final int mode = Activity.MODE_PRIVATE;
    private static final String TAG = "ProfileUser";

    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    ImageView selectedImage;
    MaterialButton btnChange,btnEdit;
    TextView viewEmail;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    String userID;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Window window = ProfileActivity.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(ProfileActivity.this, R.color.colorPrimary));

        MaterialToolbar materialToolbar = (MaterialToolbar) findViewById(R.id.profileToolbar);
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //nanti disini balik ke home
                finish();
            }
        });

        selectedImage = findViewById(R.id.profilePicture);
        btnChange = (MaterialButton) findViewById(R.id.btnChangePicture);
        btnEdit = (MaterialButton) findViewById(R.id.btnEditProfile);
        TextView viewFullName    =  findViewById(R.id.inputFullName);
        TextView viewEmail       =  findViewById(R.id.inputEmail);
        TextView viewPhone       =  findViewById(R.id.viewPhone);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();

        DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                viewEmail.setText(value.getString("email"));
                viewFullName.setText(value.getString("name"));
                viewPhone.setText(value.getString("phone"));
            }
        });

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askCameraPermission();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),EditProfileActivity.class));
            }
        });
    }

//    private void loadPreferences() {
//        preferences = getSharedPreferences("myKey", mode);
//        TextView viewFullName    =  findViewById(R.id.inputFullName);
//        TextView viewPhone       =  findViewById(R.id.viewPhone);
//        selectedImage = (ImageView) findViewById(R.id.profilePicture);
//        if (preferences!=null){
//            String fullName = preferences.getString("fullName", "-");
//            String phoneNumber = preferences.getString("phoneNumber", "-");
//            String img_str = preferences.getString("userphoto", "");
//            viewFullName.setText(fullName);
//            viewPhone.setText(phoneNumber);
//            if (!img_str.equals("")){
//                //decode string to image
//                String base=img_str;
//                byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
//                selectedImage.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
//            }
//
//        }
//
//    }

    public void signout(View view) {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(ProfileActivity.this,"Good Bye!", Toast.LENGTH_SHORT).show();
        System.out.println("BACCCCCCCAAAAAAAAAAAAAA INNNNNNNNIIIIIIIIIIIIIII"+firebaseAuth.getCurrentUser());
        startActivity(new Intent(getApplicationContext(),RegisterUser.class));
        finish();

    }

    private void askCameraPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        }
        else {
            openCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_PERM_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openCamera();
            }
            else{
                Toast.makeText(this, "Camera Permission is Required to Use Camera", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            selectedImage.setImageBitmap(image);// buat ngeset bitmap ke imageView

            selectedImage.buildDrawingCache();//buat convert ke string dari bitmap
            Bitmap bitmap = selectedImage.getDrawingCache();
            ByteArrayOutputStream stream=new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
            byte[] imageByte=stream.toByteArray();
            String img_str = Base64.encodeToString(imageByte, 0);

            //memasukkan string image ke firestore
            DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
            Map<String,Object> user = new HashMap<>();
            user.put("profileImage",img_str);
            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>(){
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d(TAG,"onSuccess: Profile Created" + userID);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG, "onFailure: " + e.toString());
                }
            });

//            SharedPreferences preferences = getSharedPreferences("myKey",MODE_PRIVATE);//buat nyimpen ke data perersistent
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.putString("userphoto",img_str);
//            editor.apply();
//            System.out.println("TERLEWATI");
        }
    }
}