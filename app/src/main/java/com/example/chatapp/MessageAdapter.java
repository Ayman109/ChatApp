package com.example.chatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MessageAdapter extends  RecyclerView.Adapter<MessageAdapter.ViewHolder>{
    Context context ;
    ArrayList<Message> messages ;

    public MessageAdapter(Context context, ArrayList<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.message,null);
        MessageAdapter.ViewHolder viewHolder  = new MessageAdapter.ViewHolder(layout);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        holder.message.setText(messages.get(position).getName()+" : "+messages.get(position).getMsg());

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        TextView message ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            message = (TextView) itemView.findViewById(R.id.messagetext);
        }


    }
}
