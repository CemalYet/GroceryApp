package be.kuleuven.myfirstapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GroceriesAdapter extends ArrayAdapter<Groceries> {
    public GroceriesAdapter(Context context, ArrayList<Groceries> groceryList){
        super(context,0,groceryList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }
    private View initView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.groceries_spinner_row,parent,false);
        }
        ImageView imageViewGrocery=convertView.findViewById(R.id.image_view_groceries);


        Groceries currentItem=getItem(position);

        if(currentItem!=null) {
            imageViewGrocery.setImageResource(currentItem.getgImage());

        }

        return convertView;

    }
}
