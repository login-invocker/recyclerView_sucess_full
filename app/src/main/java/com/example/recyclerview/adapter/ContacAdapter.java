package com.example.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.holder.ContactViewHolder;
import com.example.recyclerview.holder.LoadingViewHolder;
import com.example.recyclerview.model.Contact;
import com.example.recyclerview.model.OneloadmoreLissener;

import java.util.ArrayList;

public class ContacAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    RecyclerView recyclerView;
    Context context;
    ArrayList<Contact> conacts;
    private boolean isloading = false;
    int visibleThrehold = 5;
    int lastVisibleItem;
    int totalItemCount;
    OneloadmoreLissener oneloadmoreLissener;

    public void setLoaded() {
        isloading = false;

    }

    public OneloadmoreLissener getOneloadmoreLissener() {
        return oneloadmoreLissener;
    }

    public void setOneloadmoreLissener(OneloadmoreLissener oneloadmoreLissener) {
        this.oneloadmoreLissener = oneloadmoreLissener;
    }

    public ContacAdapter(Context context, RecyclerView recyclerView, ArrayList<Contact> conacts) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.conacts = conacts;
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isloading && totalItemCount <= (lastVisibleItem + visibleThrehold)) {
                    if (oneloadmoreLissener != null) oneloadmoreLissener.oneLoadmore();
                    isloading = true;
                }
            }
        });
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View contactView = LayoutInflater.from(context).inflate(R.layout.contactitem, parent, false);
            return new ContactViewHolder(contactView);
        }
        if (viewType == VIEW_TYPE_LOADING) {
            View loadingView = LayoutInflater.from(context).inflate(R.layout.processbar, parent, false);
            return new LoadingViewHolder(loadingView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContactViewHolder) {
            Contact contact = conacts.get(position);
            ContactViewHolder contactViewHolder = (ContactViewHolder) holder;
            contactViewHolder.txtPhone.setText(contact.getPhonr());
            contactViewHolder.txtName.setText(contact.getName());
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
             loadingViewHolder.progressBar.setIndeterminate(false);
        }
    }

    @Override
    public int getItemCount() {
        return conacts == null ? 0 : conacts.size();
    }

    @Override
    public int getItemViewType(int position) {
        return conacts.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

}
