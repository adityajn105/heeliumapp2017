package com.aditya.heeliumapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.aditya.heeliumapp.Pojos.DocumentUploadRequest;
import com.aditya.heeliumapp.Pojos.DocumentUploadResponse;
import com.aditya.heeliumapp.Pojos.ImageUploadResponse;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocumentAdder2 extends AppCompatActivity {

    ImageView mImageView;
    static final int REQUEST_TAKE_PHOTO = 1;
    String imageUploadPath;
    ProgressBar spinner;
    Uri mCurrentPhotoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_adder);
        mImageView = findViewById(R.id.photo);
        spinner = findViewById(R.id.spinner);
        dispatchTakePictureIntent();
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload();
            }
        });
    }

    private void upload() {
        try {
            spinner.setVisibility(View.VISIBLE);
            InputStream is = getContentResolver().openInputStream(mCurrentPhotoUri);
            uploadImage(getBytes(is));
        }
        catch (FileNotFoundException e){
            Toast.makeText(getApplicationContext(),"File Not There",Toast.LENGTH_LONG).show();
            e.printStackTrace();
            spinner.setVisibility(View.GONE);
        }
        catch (IOException e){
            Toast.makeText(getApplicationContext(),"Some Error Occured",Toast.LENGTH_LONG).show();
            e.printStackTrace();
            spinner.setVisibility(View.GONE);
        }
    }

    private void goHome(){
        Runnable runnable = new Runnable(){
            public void run() {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            }
        };
        new Handler().postDelayed(runnable, 1500);
    }

    private void uploadDocument(){
        HttpRequest.RetrofitInterface retrofitInterface
                = HttpRequest.retrofit.create(HttpRequest.RetrofitInterface.class);

        DocumentUploadRequest dur = new DocumentUploadRequest();
        dur.setBrand(((Spinner)findViewById(R.id.brand)).getSelectedItem().toString());
        dur.setAbout(((EditText)findViewById(R.id.about_product)).getText().toString());
        dur.setPath(imageUploadPath);
        dur.setPrice(((EditText)findViewById(R.id.price)).getText().toString());
        dur.setColor(((EditText)findViewById(R.id.color)).getText().toString());
        dur.setSize(((EditText)findViewById(R.id.size)).getText().toString());
        dur.setFirm_heel_support(((CheckBox)findViewById(R.id.firm_heel_support)).isChecked());
        dur.setArch_support(((CheckBox)findViewById(R.id.arch_support)).isChecked());
        dur.setHeel_cushioning(((CheckBox)findViewById(R.id.heel_cushioning)).isChecked());
        dur.setToe_cushioning(((CheckBox)findViewById(R.id.toe_cushioning)).isChecked());
        dur.setArched_insoles(((CheckBox)findViewById(R.id.arched_insoles)).isChecked());
        dur.setFlexi_cuts_on_outsole(((CheckBox)findViewById(R.id.flexi_cuts_on_outsole)).isChecked());
        dur.setToe_cap(((CheckBox)findViewById(R.id.toe_cap)).isChecked());
        dur.setHigh_in_linear_cushion_at_heel(((CheckBox)findViewById(R.id.high_in_liner_cushion_at_heel)).isChecked());
        dur.setWide_ball_area(((CheckBox)findViewById(R.id.wide_ball_area)).isChecked());
        dur.setHalf_sizes_in_product(((CheckBox)findViewById(R.id.half_sizes_in_product)).isChecked());
        dur.setDifferent_toe_box_sizes_available(((CheckBox)findViewById(R.id.different_toe_box_sizes_available)).isChecked());
        dur.setColors_choice_available(((CheckBox)findViewById(R.id.colors_choice_available)).isChecked());

        Call<DocumentUploadResponse> responseCall=retrofitInterface.uploadDocument(dur);
        responseCall.enqueue(new Callback<DocumentUploadResponse>() {
            @Override
            public void onResponse(Call<DocumentUploadResponse> call, Response<DocumentUploadResponse> response) {
                int code=response.code();
                Log.v("ResponseCode",""+code);
                DocumentUploadResponse docResponse=response.body();
                if(docResponse.isStatus()){
                    spinner.setVisibility(View.GONE);
                    Snackbar.make(findViewById(R.id.mainLayout),
                            docResponse.getMessage()+". Redirecting to Home.",Snackbar.LENGTH_SHORT).show();
                    goHome();
                }
                else{
                    spinner.setVisibility(View.GONE);
                    Snackbar.make(findViewById(R.id.mainLayout),docResponse.getMessage(),Snackbar.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<DocumentUploadResponse> call, Throwable t) {
                spinner.setVisibility(View.GONE);
                Snackbar.make(findViewById(R.id.mainLayout),"Some Error Occurred. Document not Uploaded",Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void uploadImage(byte[] imageBytes) {
        HttpRequest.RetrofitInterface retrofitInterface
                = HttpRequest.retrofit.create(HttpRequest.RetrofitInterface.class);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imageBytes);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", "image.jpg", requestFile);
        Call<ImageUploadResponse> call = retrofitInterface.uploadImage(body);
        call.enqueue(new Callback<ImageUploadResponse>() {
            @Override
            public void onResponse(Call<ImageUploadResponse> call, retrofit2.Response<ImageUploadResponse> response) {
                if (response.isSuccessful()) {
                    ImageUploadResponse responseBody = response.body();
                    imageUploadPath = responseBody.getPath();
                    uploadDocument();
                } else {
                    spinner.setVisibility(View.GONE);
                    ResponseBody errorBody = response.errorBody();
                    Gson gson = new Gson();
                    try {
                        ImageUploadResponse errorResponse = gson.fromJson(errorBody.string(), ImageUploadResponse.class);
                        Snackbar.make(findViewById(R.id.mainLayout), errorResponse.getMessage(),
                                Snackbar.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ImageUploadResponse> call, Throwable t) {
                Snackbar.make(findViewById(R.id.mainLayout), t.getLocalizedMessage(),
                        Snackbar.LENGTH_SHORT).show();
                spinner.setVisibility(View.GONE);
                Log.d("TAG", "onFailure: "+t.getLocalizedMessage());
            }
        });
    }

    private byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream byteBuff = new ByteArrayOutputStream();
        int buffSize = 1024;
        byte[] buff = new byte[buffSize];
        int len = 0;
        while ((len = is.read(buff)) != -1) {
            byteBuff.write(buff, 0, len);
        }
        return byteBuff.toByteArray();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            mCurrentPhotoUri = data.getData();
            mImageView.setImageURI(mCurrentPhotoUri);
        }
    }

    private void dispatchTakePictureIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),REQUEST_TAKE_PHOTO);
    }
}
