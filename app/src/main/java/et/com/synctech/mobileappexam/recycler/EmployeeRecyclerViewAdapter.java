package et.com.synctech.mobileappexam.recycler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

import et.com.synctech.mobileappexam.R;
import et.com.synctech.mobileappexam.activity.EmployeeDetailActivity;
import et.com.synctech.mobileappexam.dto.Employee;
import et.com.synctech.mobileappexam.utils.ImageUtil;

public class EmployeeRecyclerViewAdapter extends RecyclerView.Adapter<EmployeeRecyclerViewAdapter.homeViewHolder> {


    private Context mContext;
    private List<Employee> mEmployee;


    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(mContext, R.anim.bounce_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }


    public EmployeeRecyclerViewAdapter(Context mContext, List<Employee> employeeList) {
        this.mContext = mContext;
        this.mEmployee = employeeList;
    }


    @Override
    public homeViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.card_view_employee, parent, false);
        return new homeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(homeViewHolder homeViewHolder, final int position) {

        animate(homeViewHolder);



        homeViewHolder.textViewEmployeeName.setText(mEmployee.get(position).getEmployeeName());
        homeViewHolder.textViewEmployeeAge.setText(mEmployee.get(position).getEmployeeAge());
        homeViewHolder.textViewEmployeeSalary.setText(mEmployee.get(position).getEmployeeSalary());
        homeViewHolder.cardViewEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EmployeeDetailActivity.class);
                intent.putExtra("EMPLOYEE", (Serializable) mEmployee.get(position));
                v.getContext().startActivity(intent);
            }
        });

        ImageUtil.loadRoundedImagePath(mEmployee.get(position).getProfileImage(),homeViewHolder.imageViewEmployeeProfilePic,50L);

    }

    @Override
    public int getItemCount() {
        return mEmployee.size();
    }



    public static class homeViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewEmployeeName;
        private TextView textViewEmployeeAge;
        private TextView textViewEmployeeSalary;
        private ImageView imageViewEmployeeProfilePic;
        private CardView cardViewEmployee;


        public homeViewHolder(View employeeView) {
            super(employeeView);

            textViewEmployeeName = (TextView) employeeView.findViewById(R.id.txt_employee_name);
            textViewEmployeeAge = (TextView) employeeView.findViewById(R.id.txt_employee_age);
            textViewEmployeeSalary = (TextView) employeeView.findViewById(R.id.txt_employee_salary);
            imageViewEmployeeProfilePic = (ImageView) employeeView.findViewById(R.id.img_employee_profile_pic);
            cardViewEmployee = (CardView) employeeView.findViewById(R.id.card_view_employee);
        }

    }
}
