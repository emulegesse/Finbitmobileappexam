package et.com.synctech.mobileappexam.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import et.com.synctech.mobileappexam.R;
import et.com.synctech.mobileappexam.dto.Employee;
import et.com.synctech.mobileappexam.utils.ImageUtil;

import static et.com.synctech.mobileappexam.R.id;
import static et.com.synctech.mobileappexam.R.layout;

public class EmployeeDetailActivity extends AppCompatActivity {

    Employee employee;

    TextView textViewEmployeeName;
    TextView textViewEmployeeAge;
    TextView textViewEmployeeSalary;
    ImageView imageViewEmployeeProfilePic;

    TextView textViewEmployeeAgeLabel;
    TextView textViewEmployeeSalaryLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_employee_detail);

        textViewEmployeeName = findViewById(id.txt_employee_name);
        textViewEmployeeAge = findViewById(id.txt_employee_age);
        textViewEmployeeSalary = findViewById(id.txt_employee_salary);
        imageViewEmployeeProfilePic = findViewById(id.img_employee_profile_pic);
        textViewEmployeeAgeLabel = findViewById(id.txt_employee_age_label);
        textViewEmployeeSalaryLabel = findViewById(id.txt_employee_salary_label);

        employee = (Employee) getIntent().getExtras().getSerializable("EMPLOYEE");


        if (employee != null) {
            textViewEmployeeName.setText(employee.getEmployeeName());
            ImageUtil.loadRoundedImagePath(employee.getProfileImage(), imageViewEmployeeProfilePic, 110L);

            if (!employee.getEmployeeAge().equals("")) {
                textViewEmployeeAge.setText(employee.getEmployeeAge());
                textViewEmployeeAgeLabel.setText(R.string.age_label);
            }

            if (!employee.getEmployeeSalary().equals("")) {
                textViewEmployeeSalary.setText(employee.getEmployeeSalary());
                textViewEmployeeSalaryLabel.setText(R.string.salary_label);
            }

        }

    }

    public void getBackToPrevious(View view) {
        super.onBackPressed();
    }
}
