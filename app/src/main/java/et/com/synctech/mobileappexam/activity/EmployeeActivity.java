package et.com.synctech.mobileappexam.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.orm.SugarRecord;

import et.com.synctech.mobileappexam.R;
import et.com.synctech.mobileappexam.dto.Datum;
import et.com.synctech.mobileappexam.dto.EmployeeResponseDto;
import et.com.synctech.mobileappexam.recycler.EmployeeRecyclerViewAdapter;
import et.com.synctech.mobileappexam.services.ApiService;
import et.com.synctech.mobileappexam.services.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeActivity extends AppCompatActivity {

    RecyclerView recyclerViewEmployee;
    EmployeeRecyclerViewAdapter employeeRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        recyclerViewEmployee = findViewById(R.id.recyler_view_employees);

        fetchEmployeeData();

    }

    private void setRecyclerView() {

        employeeRecyclerViewAdapter = new EmployeeRecyclerViewAdapter(getApplicationContext(), Datum.listAll(Datum.class));
        recyclerViewEmployee.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerViewEmployee.setAdapter(employeeRecyclerViewAdapter);
        employeeRecyclerViewAdapter.notifyDataSetChanged();

    }

    public void fetchEmployeeData() {

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");
        progressDialog.setTitle("Loading");
        progressDialog.show();

        RetrofitInstance
                .getRetrofitInstance()
                .create(ApiService.class)
                .fetchEmployees()
                .enqueue(new Callback<EmployeeResponseDto>() {
                    @Override
                    public void onResponse(Call<EmployeeResponseDto> call, Response<EmployeeResponseDto> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null && response.body().getData() != null) {
                                SugarRecord.saveInTx(response.body().getData()); } }

                        progressDialog.dismiss();
                        setRecyclerView();
                    }

                    @Override
                    public void onFailure(Call<EmployeeResponseDto> call, Throwable t) {
                        progressDialog.dismiss();

                        if(Datum.count(Datum.class)!=0){
                            setRecyclerView();
                        }else {

                        }

                    }
                });


    }

    private static boolean checkConnection(Activity activity) {
        ConnectivityManager manager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo connection = manager.getActiveNetworkInfo();
        return connection != null && connection.isConnectedOrConnecting();

    }


}