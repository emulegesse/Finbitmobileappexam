package et.com.synctech.mobileappexam.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import et.com.synctech.mobileappexam.R;
import et.com.synctech.mobileappexam.dto.Datum;
import et.com.synctech.mobileappexam.recycler.EmployeeRecyclerViewAdapter;

public class EmployeeActivity extends AppCompatActivity {

    RecyclerView recyclerViewEmployee;
    EmployeeRecyclerViewAdapter employeeRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        recyclerViewEmployee = (RecyclerView)findViewById(R.id.recyler_view_employees);

    }

    private void setRecyclerView(List<Datum> itemDtoList){

        employeeRecyclerViewAdapter = new EmployeeRecyclerViewAdapter(getApplicationContext(),itemDtoList);
        recyclerViewEmployee.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewEmployee.setAdapter(employeeRecyclerViewAdapter);
        employeeRecyclerViewAdapter.notifyDataSetChanged();

    }

}
