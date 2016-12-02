package com.example.jefri.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by JEFRI SINGH(ஜெப்ரி சிங்) on 4/2/2016.
 * Organization "The Tuna Group" - Kerala
 */
class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<UserDetails> arrayList;
    private RecyclerViewClicked callback;
    private Context context;
    RecyclerAdapter(Context context,ArrayList<UserDetails> arrayList,RecyclerViewClicked callback) {
        this.context = context;
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

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,city,country;
        ImageButton add,remove;
        ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name_textView);
            city = (TextView)itemView.findViewById(R.id.city_textView);
            country = (TextView)itemView.findViewById(R.id.country_textView);
            add = (ImageButton) itemView.findViewById(R.id.imageButton_add);
            remove = (ImageButton) itemView.findViewById(R.id.imageButton_remove);
            add.setOnClickListener(this);
            remove.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getTag().toString().equals("add")){
                //add button clicked
                new AlertDialog.Builder(context)
                        .setTitle("Add Item")
                        .setMessage("Do you want add item to list?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //adding item at the end
                                //you can add XML ui for dialog for getting details here
                                arrayList.add(new UserDetails("New Name","New city","New County"));
                                notifyItemInserted(arrayList.size());
                            }
                        })
                        .setNegativeButton("NO",null)
                        .create().show();
            }else if (v.getTag().toString().equals("remove")){
                //delete button clicked
                arrayList.remove(getAdapterPosition());
                //getAdapterPosition() used to get the position of the button clicked
                notifyItemRemoved(getAdapterPosition());
            }
            //callback.OnClick(arrayList.get(getAdapterPosition()));
        }
    }
}
