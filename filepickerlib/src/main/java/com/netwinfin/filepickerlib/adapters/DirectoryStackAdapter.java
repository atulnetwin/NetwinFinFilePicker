package com.netwinfin.filepickerlib.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.netwinfin.filepickerlib.R;
import com.netwinfin.filepickerlib.models.DirectoryModel;

import java.util.ArrayList;


public class DirectoryStackAdapter extends RecyclerView.Adapter<com.netwinfin.filepickerlib.adapters.DirectoryStackAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DirectoryModel> directoryList;
    private onDirectoryStackListener onDirectoryStackListener;
    private TypedValue typedValue;

    @ColorInt
    private int textColor;
    @ColorInt
    private int selectedTextColor;


    public interface onDirectoryStackListener{
        void onDirClicked(DirectoryModel model);
    }

    public DirectoryStackAdapter(Context context, ArrayList<DirectoryModel> directoryList, onDirectoryStackListener listener) {
        this.context = context;
        this.directoryList = directoryList;
        this.onDirectoryStackListener = listener;

        this.typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(R.attr.unicorn_primaryTextColor, typedValue, true);
        this.textColor = typedValue.data;
        theme.resolveAttribute(R.attr.unicorn_colorAccent, typedValue, true);
        this.selectedTextColor = typedValue.data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_directory_stack, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_dir_name.setText(directoryList.get(position).getName());

        if(position == getItemCount()-1){
            holder.tv_dir_name.setTextColor(this.selectedTextColor);
        }else{
            holder.tv_dir_name.setTextColor(this.textColor);
        }

        holder.itemView.setOnClickListener((v)->{
            onDirectoryStackListener.onDirClicked(directoryList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return directoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_dir_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_dir_name = itemView.findViewById(R.id.tv_dir_name);
        }
    }
}
