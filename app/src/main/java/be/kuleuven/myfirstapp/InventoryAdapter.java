package be.kuleuven.myfirstapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.MyViewHolder> {
    private List<Product> productList;
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView productName;
        private TextView barcode;

        public MyViewHolder(View view) {
            super(view);
            productName = (TextView) view.findViewById(R.id.name);
            barcode = (TextView) view.findViewById(R.id.barcode);
        }
    }
    public InventoryAdapter(List<Product> myDataset) {
        productList = myDataset;
    }

    @NonNull
    @Override
    public InventoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inventory_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productName.setText(product.getName());
        holder.barcode.setText(String.valueOf(product.getBarcode()));

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}