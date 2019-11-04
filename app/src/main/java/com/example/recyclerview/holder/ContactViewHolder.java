package com.example.recyclerview.holder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;

import org.w3c.dom.Text;

public class ContactViewHolder extends  RecyclerView.ViewHolder{
public TextView txtName;
public  TextView txtPhone;

    public ContactViewHolder( View itemView) {

        super(itemView);
        txtName=itemView.findViewById(R.id.ten);
        txtPhone=itemView.findViewById(R.id.phone);
    }
}
