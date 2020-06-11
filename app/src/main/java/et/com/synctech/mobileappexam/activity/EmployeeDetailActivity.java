package et.com.synctech.mobileappexam.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import et.com.synctech.mobileappexam.R;
import et.com.synctech.mobileappexam.dto.Employee;
import et.com.synctech.mobileappexam.utils.ImageUtil;

import static et.com.synctech.mobileappexam.R.*;

public class EmployeeDetailActivity extends AppCompatActivity {

    Employee employee;

    private TextView textViewEmployeeName;
    private TextView textViewEmployeeAge;
    private TextView textViewEmployeeSalary;
    private ImageView imageViewEmployeeProfilePic;

    private TextView textViewEmployeeAgeLabel;
    private TextView textViewEmployeeSalaryLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_employee_detail);
        textViewEmployeeName = (TextView) findViewById(id.txt_employee_name);
        textViewEmployeeAge = (TextView) findViewById(id.txt_employee_age);
        textViewEmployeeSalary = (TextView) findViewById(id.txt_employee_salary);
        imageViewEmployeeProfilePic = (ImageView) findViewById(id.img_employee_profile_pic);

        textViewEmployeeAgeLabel = (TextView) findViewById(id.txt_employee_age_label);
        textViewEmployeeSalaryLabel = (TextView) findViewById(id.txt_employee_salary_label);

        employee = (Employee) getIntent().getExtras().getSerializable("EMPLOYEE");


        if(employee !=null){
            textViewEmployeeName.setText(employee.getEmployeeName());
            ImageUtil.loadRoundedImagePath(employee.getProfileImage(),imageViewEmployeeProfilePic,110L);

            if(!employee.getEmployeeAge().equals("")) {
                textViewEmployeeAge.setText(employee.getEmployeeAge());
                textViewEmployeeAgeLabel.setText(R.string.age_label);  }

            if(!employee.getEmployeeSalary().equals("")) {
                textViewEmployeeSalary.setText(employee.getEmployeeSalary());
                textViewEmployeeSalaryLabel.setText(R.string.salary_label);  }

        }

    }

    public void getBackToPrevious(View view) {
        super.onBackPressed();
    }
}
