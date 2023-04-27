package com.example.ontap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.ontap.model.Job;
import com.example.ontap.model.JobAdapter;
import com.example.ontap.model.SpinnerAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener ,JobAdapter.JobIteamListener,
        SearchView.OnQueryTextListener {
//    private Spinner sp;
    private JobAdapter adapter;
    private RecyclerView recyclerView;
    private JobAdapter jobAdapter;
    private EditText eName,eDes,eDate;
    private RadioButton eNam,eNu;
    private CheckBox ck1,ck2;
    private Button btAdd,btUpdate;
    private SearchView searchView;
    private int pcurr;
    private int[] imgs= {R.drawable.img,R.drawable.img_1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        adapter= new JobAdapter(this);
        LinearLayoutManager manager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
        eDate.setOnClickListener(this);
        searchView.setOnQueryTextListener(this);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Job job=new Job();
//                String i=sp.getSelectedItem().toString();
                String name=eName.getText().toString();
                String Des=eDes.getText().toString();
                String Gt="";
//                if(eNam.isChecked()){
//                     Gt=eNam.getText().toString();
//                }
//                if(eNu.isChecked()){
//                     Gt=eNu.getText().toString();
//                }
                if(ck1.isChecked()){
                    Gt=ck1.getText().toString();
                }
                String date=eDate.getText().toString();
                int img=R.drawable.img;
                try{
                    if(eNam.isChecked()){
                        img=imgs[0];
                    }
                    if(eNu.isChecked()){
                        img=imgs[1];
                    }
                    job.setImg(img);
                    job.setName(name);
                    job.setGt(Gt);
                    job.setDes(Des);
                    job.setTime(date);
                    adapter.add(job);

                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "nhap lai",Toast.LENGTH_SHORT).show();
                }


            }
        });
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Job job=new Job();
//                String i=sp.getSelectedItem().toString();
                String name=eName.getText().toString();
                String Des=eDes.getText().toString();
                String Gt="";
                if(eNam.isChecked()){
                    Gt=eNam.getText().toString();
                }
                if(eNu.isChecked()){
                    Gt=eNu.getText().toString();
                }
                String date=eDate.getText().toString();
                int img=R.drawable.img;
                try{
                    if(eNam.isChecked()){
                        img=imgs[0];
                    }
                    if(eNu.isChecked()){
                        img=imgs[1];
                    }
//                    img=imgs[Integer.parseInt(i)];
                    job.setImg(img);
                    job.setName(name);
                    job.setGt(Gt);
                    job.setDes(Des);
                    job.setTime(date);
                    adapter.update(pcurr,job);
                    btAdd.setEnabled(true);
                    btUpdate.setEnabled(false);

                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "nhap lai",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initView() {
//        sp=findViewById(R.id.img);
//        SpinnerAdapter adapter= new SpinnerAdapter(this);
//        sp.setAdapter(adapter);
        recyclerView=findViewById(R.id.recyclerView);
        eName=findViewById(R.id.name);
        eDes=findViewById(R.id.des);
        eDate=findViewById(R.id.date);
        eNam=findViewById(R.id.nam);
        eNu=findViewById(R.id.nu);
        ck1=findViewById(R.id.ck1);
        ck2=findViewById(R.id.ck2);
        btAdd=findViewById(R.id.add);
        btUpdate=findViewById(R.id.update);
        btUpdate.setEnabled(false);
        searchView=findViewById(R.id.search);



    }

    @Override
    public void onClick(View v) {
        if (v == eDate) {
//            Calendar c = Calendar.getInstance();
//            int y = c.get(Calendar.YEAR);
//            int m = c.get(Calendar.MONTH);
//            int d = c.get(Calendar.DAY_OF_MONTH);
//            DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//                @Override
//                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                    eDate.setText(year + "/" + (month + 1) + "/" + dayOfMonth);
//                }
//                }, y, m, d);
//                    dialog.show();
            Calendar c = Calendar.getInstance();
            int hh=c.get(Calendar.HOUR_OF_DAY);
            int mm=c.get(Calendar.MINUTE);
            TimePickerDialog timedialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    eDate.setText(hourOfDay+":"+minute);
                }
            },hh,mm,false);
            timedialog.show();
        }

    }

    @Override
    public void onIteamClick(View view, int position) {
        btAdd.setEnabled(false);
        btUpdate.setEnabled(true);
        pcurr=position;
        Job job=adapter.getIteam(position);
        int img=job.getImg();
        int p=0;
        for(int i=0;i<imgs.length;i++){
            if(img==imgs[i]){
                p=i;
                break;
            }
        }
//        sp.setSelection(p);
        eName.setText(job.getName());
        eDes.setText(job.getDes());
        eDate.setText(job.getTime()+"");
        if(job.getGt()==eNam.getText().toString()){
            eNam.setChecked(true);
        }
        if(job.getGt()==eNu.getText().toString()){
            eNu.setChecked(true);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        filter(newText);
        return false;
    }

    private void filter(String newText) {
        List<Job> filterlist=new ArrayList<>();
        for (Job i:adapter.getBackUp()){
            if(i.getName().toLowerCase().contains(newText.toLowerCase())){
                filterlist.add(i);
            }
        }
        if(filterlist.isEmpty()){
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }else{
            adapter.filterList(filterlist);
        }
    }
}