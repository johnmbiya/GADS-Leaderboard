package com.johnmbiya.gads.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.johnmbiya.gads.R;
import com.johnmbiya.gads.api.ApiForm;
import com.johnmbiya.gads.api.RetrofitService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, SubmissionActivity.class);
        context.startActivity(starter);
    }

    private EditText mFirstName;
    private EditText mLastName;
    private EditText mEmailAdress;
    private EditText mGithubLink;
    private Button mButtonSubmit;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // make status bar transparent and navigation bar
        setFullScreenView();
        setContentView(R.layout.activity_submission);

        setupViews();
        mButtonSubmit.setOnClickListener(view -> submitProject());
    }

    private void setupViews() {
        mFirstName = findViewById(R.id.fname);
        mLastName = findViewById(R.id.lname);
        mEmailAdress = findViewById(R.id.et_email);
        mGithubLink = findViewById(R.id.et_github);
        mButtonSubmit = findViewById(R.id.btn_submit);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);
    }

    private void submitProject() {
        if(validateFields()){

            if(isConnectedToNetwork(this)) {
                showConfirmDialog();

            } else {
                Snackbar.make(mButtonSubmit, "You are not connected to internet", Snackbar.LENGTH_SHORT).show();
            }

        }
    }

    private void showConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_confirm_dialog,null);
        ImageButton btnClose = dialogView.findViewById(R.id.btn_close);
        Button btnConfirm = dialogView.findViewById(R.id.btn_confirm);


        builder.setCancelable(true);
        builder.setView(dialogView);

        final AlertDialog dialog = builder.create();

        btnClose.setOnClickListener(view -> {
            dialog.dismiss();
            showStatusDialog(false);
        });

        btnConfirm.setOnClickListener(view -> {
            dialog.dismiss();
            progressDialog.show();
            // FInaly send form data

            // sendFormData();
            showStatusDialog(true);
        });

        dialog.show();

    }

    private void sendFormData() {
        String fname = mFirstName.getText().toString();
        String lname = mLastName.getText().toString();
        String email = mEmailAdress.getText().toString();
        String github = mGithubLink.getText().toString();

        ApiForm retrofitService = RetrofitService.createFormService(ApiForm.class);
        retrofitService.submitProject(email, fname, lname, github)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(@NotNull Call<Void> call, @NotNull Response<Void> response) {
                        if(response.isSuccessful()){
                            // show success dialog
                            progressDialog.dismiss();
                            showStatusDialog(true);
                        } else {
                            // show error dialog
                            progressDialog.dismiss();
                            showStatusDialog(false);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<Void> call, @NotNull Throwable t) {
                        // show error dialog
                        progressDialog.dismiss();
                        showStatusDialog(false);
                    }
                });
    }

    private boolean isConnectedToNetwork(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    private boolean validateFields() {

        if(TextUtils.isEmpty(mFirstName.getText())) {
            mFirstName.setError(getString(R.string.required_field));
            return false;
        }

        if(TextUtils.isEmpty(mLastName.getText())) {
            mLastName.setError(getString(R.string.required_field));
            return false;
        }

        if(TextUtils.isEmpty(mEmailAdress.getText())) {
            mEmailAdress.setError(getString(R.string.required_field));
            return false;
        } else if(!Patterns.EMAIL_ADDRESS.matcher(mEmailAdress.getText().toString()).matches()){
            // if email field is not valid email
            mEmailAdress.setError(getString(R.string.email_field_invalid));
            return false;
        }

        if(TextUtils.isEmpty(mGithubLink.getText())) {
            mGithubLink.setError(getString(R.string.required_field));
            return false;
        } else if (!Patterns.WEB_URL.matcher(mGithubLink.getText().toString()).matches()){
            // this must be a valvid web url
            mGithubLink.setError(getString(R.string.url_field_invalid));
            return false;
        }

        return true;
    }

    /**
     * When back Arrow pressed
     * @param view
     */
    public void goBack(View view) {
        // quit the app
        finish();
    }

    private void showStatusDialog(boolean success){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_status_dialog,null);
        ImageView icon = dialogView.findViewById(R.id.icon);
        TextView status = dialogView.findViewById(R.id.status);

        if(success) {
            icon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_check_circle));
            status.setText(getString(R.string.submission_successful));
        } else {
            icon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_warning));
            status.setText(getString(R.string.submission_not_successful));
        }

        builder.setCancelable(true);
        builder.setView(dialogView);

        final AlertDialog dialog = builder.create();

        dialog.show();
    }
}