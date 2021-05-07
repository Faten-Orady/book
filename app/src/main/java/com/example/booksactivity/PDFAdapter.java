package com.example.booksactivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

class PDFAdapter extends RecyclerView.Adapter<PDFAdapter.ViewHolder>{
    private Context mContext;
    private ArrayList<File> mFiles;
    String[] items;

    public PDFAdapter(Context mContext, ArrayList<File> mFiles ,String[] items) {
        this.mContext = mContext;
        this.mFiles = mFiles;
        this.items=items;
    }

    public class   ViewHolder extends RecyclerView.ViewHolder{
        TextView file_name;
        ImageView img_icon;
        RelativeLayout pdf_item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            file_name=itemView.findViewById(R.id.pdf_file_item );
            img_icon=itemView.findViewById(R.id.img_pdf);
            pdf_item =itemView.findViewById( R.id.pdf_item);
        }
    }


    @NonNull
    @Override
    public  ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.pdf_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PDFAdapter.ViewHolder holder, int position) {
        holder.file_name.setText(items[position]);
        holder.pdf_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,ActivityPdfView.class);
                intent.putExtra("Position",position);
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mFiles.size();
    }
}

