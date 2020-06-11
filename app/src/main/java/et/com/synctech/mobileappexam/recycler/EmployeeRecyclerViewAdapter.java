package et.com.synctech.mobileappexam.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import et.com.synctech.mobileappexam.R;
import et.com.synctech.mobileappexam.dto.Datum;
import et.com.synctech.mobileappexam.utils.Util;

public class EmployeeRecyclerViewAdapter extends RecyclerView.Adapter<EmployeeRecyclerViewAdapter.homeViewHolder> {


    private Context mContext;
    private List<Datum> mDatum;


    public EmployeeRecyclerViewAdapter(Context mContext, List<Datum> datumList) {
        this.mContext = mContext;
        this.mDatum = datumList;
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




        homeViewHolder.textViewEmployeeName.setText(mDatum.get(position).getEmployeeName());
        homeViewHolder.textViewEmployeeAge.setText(mDatum.get(position).getEmployeeAge());
        homeViewHolder.textViewEmployeeSalary.setText(mDatum.get(position).getEmployeeSalary());
        homeViewHolder.cardViewEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Util.loadRoundedImagePath("https://cdn.shopify.com/s/files/1/0877/4986/products/001_235_Coral_shop_hover_2x_e8dcf78f-e483-4a4f-86bf-48eeb783634a.jpg?v=1580431943",homeViewHolder.imageViewEmployeeProfilePic);
//        Util.loadRoundedImagePath("https://cdn.shopify.com/s/files/1/0877/4986/products/001_235_Coral_shop_hover_2x_e8dcf78f-e483-4a4f-86bf-48eeb783634a.jpg?v=1580431943",homeViewHolder.imageViewEmployeeProfilePic);

    }

    @Override
    public int getItemCount() {
        return mDatum.size();
    }



    public static class homeViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewEmployeeName;
        private TextView textViewEmployeeAge;
        private TextView textViewEmployeeSalary;
        private ImageView imageViewEmployeeProfilePic;
        private CardView cardViewEmployee;


        public homeViewHolder(View itemView) {
            super(itemView);

            textViewEmployeeName = (TextView) itemView.findViewById(R.id.txt_employee_name);
            textViewEmployeeAge = (TextView) itemView.findViewById(R.id.txt_employee_age);
            textViewEmployeeSalary = (TextView) itemView.findViewById(R.id.txt_employee_salary);
            imageViewEmployeeProfilePic = (ImageView) itemView.findViewById(R.id.img_employee_profile_pic);
            cardViewEmployee = (CardView) itemView.findViewById(R.id.card_view_employee);
        }

    }
}
