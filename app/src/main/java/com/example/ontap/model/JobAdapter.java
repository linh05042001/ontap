package com.example.ontap.model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ontap.R;

import java.util.ArrayList;
import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder>{
    private Context context;
    private List<Job> listBack;
    private List<Job> mlist;
    private JobIteamListener mJobIteam;


    public JobAdapter(Context context) {
        this.context = context;
        mlist=new ArrayList<>();
        listBack=new ArrayList<>();
    }
    public List<Job> getBackUp(){
        return listBack;
    }
    public void setmList(List<Job> mlist){
        this.mlist=mlist;
    }
    public void filterList(List<Job> filterlist){
        mlist=filterlist;
        notifyDataSetChanged();
    }
    public void setClickListener(JobIteamListener mJobIteam){
        this.mJobIteam=mJobIteam;
    }
    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.iteam,parent,false);
        return new JobViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        Job job=mlist.get(position);
        if(job==null)
            return;
        holder.img.setImageResource(job.getImg());
        holder.tvName.setText(job.getName());
        holder.tvDes.setText(job.getDes());
        holder.tvGt.setText(job.getGt()+"");
        holder.tvDate.setText(job.getTime()+"");
        holder.btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Thong bao xoa");
                builder.setMessage("Ban co chac chan muon xoa"+job.getName()+"nay khong");
                builder.setIcon(R.drawable.img);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listBack.remove(position);
                        mlist.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();


            }
        });

    }
    public void add(Job j){
        listBack.add(j);
        mlist.add(j);
        notifyDataSetChanged();
    }
    public void update(int position,Job job){
        listBack.set(position,job);
        mlist.set(position,job);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if(mlist!=null)
            return mlist.size();
        return 0;
    }
    public Job getIteam(int position){
        return mlist.get(position);
    }

    public class JobViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView img;
        private TextView tvName,tvDes,tvGt,tvDate;
        private Button btRemove;

        public JobViewHolder(@NonNull View view) {
            super(view);
            img=view.findViewById(R.id.img);
            tvName=view.findViewById(R.id.txtName);
            tvDes=view.findViewById(R.id.txtDes);
            tvGt=view.findViewById(R.id.txtGt);
            tvDate=view.findViewById(R.id.txtDate);
            btRemove=view.findViewById(R.id.Remove);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(mJobIteam!=null){
                mJobIteam.onIteamClick(v,getAdapterPosition());
            }
        }
    }
    public interface JobIteamListener{
        void onIteamClick(View view,int position);
    }
}
