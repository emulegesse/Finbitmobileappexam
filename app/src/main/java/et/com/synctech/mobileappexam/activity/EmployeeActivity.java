package et.com.synctech.mobileappexam.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

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
        recyclerViewEmployee = (RecyclerView)findViewById(R.id.recyler_view_employees);

        fetchEmployeeData(this);

    }

    private void setRecyclerView(List<Datum> datumList){

        employeeRecyclerViewAdapter = new EmployeeRecyclerViewAdapter(getApplicationContext(),datumList);
        recyclerViewEmployee.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewEmployee.setAdapter(employeeRecyclerViewAdapter);
        employeeRecyclerViewAdapter.notifyDataSetChanged();

    }

    public  void fetchEmployeeData(final Activity activity) {

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(activity);
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

                            Log.d("AAAAAAAAAAAAA", "response is succ from api" + response.body());

                            for (Datum datum:response.body().getData()){
                                Log.d("AAAAAAAAAAAAA", "response "+datum.getEmployeeName() );

                            }
                            setRecyclerView(response.body().getData());

                            progressDialog.dismiss();


                        } else {
                            Log.d("AAAAAAAAAAAAA", "response not succesfulllll from ai" + response.body());
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<EmployeeResponseDto> call, Throwable t) {
                        progressDialog.dismiss();
                    }
                });
    }


}
