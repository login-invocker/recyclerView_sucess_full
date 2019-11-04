package com.example.recyclerview.holder;

import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;

public class LoadingViewHolder  extends RecyclerView.ViewHolder {
    public ProgressBar progressBar;
    public LoadingViewHolder(View view) {
        super(view);
        progressBar= view.findViewById(R.id.processbar);
    }


}
