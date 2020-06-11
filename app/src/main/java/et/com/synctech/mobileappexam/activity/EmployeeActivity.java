package et.com.synctech.mobileappexam.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.orm.SugarRecord;

import java.util.List;

import et.com.synctech.mobileappexam.R;
import et.com.synctech.mobileappexam.dto.Employee;
import et.com.synctech.mobileappexam.dto.EmployeeResponseDto;
import et.com.synctech.mobileappexam.recycler.EmployeeRecyclerViewAdapter;
import et.com.synctech.mobileappexam.service.ApiService;
import et.com.synctech.mobileappexam.service.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeActivity extends AppCompatActivity {

    RecyclerView recyclerViewEmployee;
    EmployeeRecyclerViewAdapter employeeRecyclerViewAdapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        recyclerViewEmployee = findViewById(R.id.recyler_view_employees);
        searchView = findViewById(R.id.search_view);

        setUpEmployeeSearchListener();

        fetchEmployeeData();

    }



    @Override
    protected void onResume() {
        super.onResume();
        fetchEmployeeData();
    }

    private void setRecyclerView(List<Employee> employeeList) {

        if (employeeList == null) employeeList = Employee.listAll(Employee.class,"employee_name");

        employeeRecyclerViewAdapter = new EmployeeRecyclerViewAdapter(getApplicationContext(), employeeList);
        recyclerViewEmployee.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerViewEmployee.setAdapter(employeeRecyclerViewAdapter);
        employeeRecyclerViewAdapter.notifyDataSetChanged();

    }

    private void setUpEmployeeSearchListener() {

        String query = "Select * from Employee where employee_name like '%";

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                setRecyclerView(Employee.findWithQuery(Employee.class, query + s + "%'"+ " order by employee_name"));

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                setRecyclerView(Employee.findWithQuery(Employee.class, query + s + "%'"+ " order by employee_name"));
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                setRecyclerView(null);
                return false;
            }
        });

    }


    public void fetchEmployeeData() {

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.wait_msg));
        progressDialog.setTitle(getString(R.string.loading));
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
                                SugarRecord.saveInTx(response.body().getData());
                            }
                        }

                        progressDialog.dismiss();
                        setRecyclerView(null);
                    }

                    @Override
                    public void onFailure(Call<EmployeeResponseDto> call, Throwable t) {
                        progressDialog.dismiss();

                        if (Employee.count(Employee.class) != 0) {
                            setRecyclerView(null);
                        } else if (!checkConnection(EmployeeActivity.this)) {
                            startActivity(new Intent(EmployeeActivity.this, NotConnectedActivity.class));
                        }

                    }
                });


    }

    private boolean checkConnection(Activity activity) {
        ConnectivityManager manager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo connection = manager.getActiveNetworkInfo();
        return connection != null && connection.isConnectedOrConnecting();

    }

}
