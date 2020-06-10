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
