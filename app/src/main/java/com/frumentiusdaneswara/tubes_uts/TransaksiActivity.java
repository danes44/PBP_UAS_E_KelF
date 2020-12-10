package com.frumentiusdaneswara.tubes_uts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransaksiActivity extends AppCompatActivity {

    private ImageButton ibBack;
    private EditText editTextharga, editTextnama, editTextnohp, editTextmetode;
    private AutoCompleteTextView exposedDropdownmetode;
    private MaterialButton btn_cancel, btn_Create;
    private String smetode = "";
    private String[] sametode = new String[] {"Cash", "Credit"};
    private ProgressDialog progressDialog;
    private String userId;
    private Call<TransaksiResponse> add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        progressDialog = new ProgressDialog(this);

        editTextharga = findViewById(R.id.edit_hargakos);
        editTextnama = findViewById(R.id.edit_namapemasan);
        editTextnohp = findViewById(R.id.edit_nohppemesan);
        editTextmetode = findViewById(R.id.edit_metode_pembayaran);
        btn_cancel = findViewById(R.id.btnCancel);
        btn_Create = findViewById(R.id.btnBook);



        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextnama.getText().toString().isEmpty())
                {
                    editTextnama.setError("Isikan dengan benar");
                    editTextnama.requestFocus();
                }
                else if(editTextnohp.getText().toString().isEmpty())
                {
                    editTextnohp.setError("Isikan dengan benar");
                    editTextnohp.requestFocus();
                }
                else if(editTextmetode.getText().toString().isEmpty())
                {
                    editTextmetode.setError("Isikan dengan benar");
                    editTextmetode.requestFocus();
                }
                else if(editTextharga.getText().toString().isEmpty())
                {
                    editTextharga.setError("Isikan dengan benar");
                    editTextharga.requestFocus();
                }
                else
                {
                    progressDialog.show();
                    saveUser();
                }
            }
        });

    }

    private void saveUser() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TransaksiResponse> add = apiService.createTransaksi(editTextnama.getText().toString(),
                editTextnohp.getText().toString(), editTextmetode.getText().toString(), editTextharga.getText().toString());

        add.enqueue(new Callback<TransaksiResponse>() {
            @Override
            public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
//                Toast.makeText(TransaksiActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                Intent intent = new Intent(TransaksiActivity.this,searchActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<TransaksiResponse> call, Throwable t) {
//                Toast.makeText(TransaksiActivity.this, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
//                progressDialog.dismiss();
                Intent intent = new Intent(TransaksiActivity.this,searchActivity.class);
                startActivity(intent);
            }
        });

    }












}