package et.com.synctech.mobileappexam.recycler;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.balysv.materialripple.MaterialRippleLayout;

import java.io.Serializable;
import java.util.List;

import et.com.synctech.mobileappexam.R;
import et.com.synctech.mobileappexam.activity.EmployeeDetailActivity;
import et.com.synctech.mobileappexam.dto.Employee;
import et.com.synctech.mobileappexam.utils.ImageUtil;

public class EmployeeRecyclerViewAdapter extends RecyclerView.Adapter<EmployeeRecyclerViewAdapter.homeViewHolder> {


    public List<Employee> mEmployee;
    private Context mContext;


    public EmployeeRecyclerViewAdapter(Context mContext, List<Employee> employeeList) {
        this.mContext = mContext;
        this.mEmployee = employeeList;
    }

    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(mContext, R.anim.bounce_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
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
        homeViewHolder.cardViewEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EmployeeDetailActivity.class);
                intent.putExtra("EMPLOYEE", mEmployee.get(position));
                v.getContext().startActivity(intent);
            }
        });


        homeViewHolder.ripple.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), EmployeeDetailActivity.class);
                intent.putExtra("EMPLOYEE", mEmployee.get(position));
                v.getContext().startActivity(intent);

            }

        });

        ImageUtil.loadRoundedImagePath(mEmployee.get(position).getProfileImage(), homeViewHolder.imageViewEmployeeProfilePic, 50L);

    }

    @Override
    public int getItemCount() {
        return mEmployee.size();
    }


    public static class homeViewHolder extends RecyclerView.ViewHolder {

        MaterialRippleLayout ripple;
        private TextView textViewEmployeeName;
        private ImageView imageViewEmployeeProfilePic;
        private CardView cardViewEmployee;


        public homeViewHolder(View employeeView) {
            super(employeeView);

            textViewEmployeeName = employeeView.findViewById(R.id.txt_employee_name);
            imageViewEmployeeProfilePic = employeeView.findViewById(R.id.img_employee_profile_pic);
            cardViewEmployee = employeeView.findViewById(R.id.card_view_employee);

            ripple = itemView.findViewById(R.id.ripple);


        }

    }
}
