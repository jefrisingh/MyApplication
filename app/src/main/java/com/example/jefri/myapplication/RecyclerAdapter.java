package com.example.jefri.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by JEFRI SINGH(ஜெப்ரி சிங்) on 4/2/2016.
 * Organization "The Tuna Group" - Kerala
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<UserDetails> arrayList;
    RecyclerViewClicked callback;
    public RecyclerAdapter(ArrayList<UserDetails> arrayList,RecyclerViewClicked callback) {
        this.arrayList = arrayList;
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_child,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserDetails userDetails = arrayList.get(position);

        holder.name.setText(userDetails.getName());
        holder.city.setText(userDetails.getCity());
        holder.country.setText(userDetails.getCountry());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,city,country;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name_textView);
            city = (TextView)itemView.findViewById(R.id.city_textView);
            country = (TextView)itemView.findViewById(R.id.country_textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            callback.OnClick(arrayList.get(getAdapterPosition()));
        }
    }
}
