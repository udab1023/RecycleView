package vn.edu.ntu.nguyenngoctien.recycleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.ntu.nguyenngoctien.controller.ICartController;
import vn.edu.ntu.nguyenngoctien.model.Product;

public class MainActivity extends AppCompatActivity {
    ArrayList<Product> listProduct;
    ProductAdapter adapter;
    RecyclerView rvListProduct;
    ActionBar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addView();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void addView()
    {
        rvListProduct=findViewById(R.id.rvListProduct);
        rvListProduct.setLayoutManager(new LinearLayoutManager(this));
        ICartController controller = (ICartController) getApplication();
        listProduct =controller.getListProduct();
        adapter=new ProductAdapter(listProduct);
        rvListProduct.setAdapter(adapter);
    }
    //Lop cai dat hien thi cua 1 product
    private class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView txtName, txtPrice, txtDesc;
        ImageButton imBtnCart;
        Product p;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = this.itemView.findViewById(R.id.txtName);
            txtPrice = this.itemView.findViewById(R.id.txtPrice);
            txtDesc = this.itemView.findViewById(R.id.txtDesc);
            imBtnCart = this.itemView.findViewById(R.id.imBtnCart);
            imBtnCart.setOnClickListener(this);
        }
        public void bind(Product p)
        {
            this.p =p;
            txtName.setText(p.getName());
            txtPrice.setText(new Integer(p.getPrice()).toString());
            txtDesc.setText(p.getDesc());
            imBtnCart.setImageResource(R.drawable.ic_action_add_to_cart);
        }

        @Override
        public void onClick(View v) {
            ICartController controller = (ICartController) getApplication();
            if (!controller.addToShoppingCart(p)){
                Toast.makeText(MainActivity.this,"sp: " +p.getName()+ "da co trong gio hang",Toast.LENGTH_SHORT).show();
            }else
                Toast.makeText(MainActivity.this,"sp: " +p.getName()+ "vao gio hang",Toast.LENGTH_SHORT).show();
        }
    }

    //Lop adapter ket noi recycleview vs data
    private class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder>
    {
        ArrayList<Product> listProduct;

        public ProductAdapter(ArrayList<Product> listProduct) {
            this.listProduct = listProduct;
        }
        //tao 1 viewholder de hien thi data
        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            //chuyen layout da thiet ke = xml thanh 1 doi tuong
            View view = layoutInflater.inflate(R.layout.product_item,parent,false);
            return new ProductViewHolder(view);
        }

        //ket noi 1 muc data trong list vs 1 cai vewholder
        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            holder.bind(listProduct.get(position));
        }

        @Override
        public int getItemCount() {
            return listProduct.size();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu1,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_cart:
                callshoppingCartActivity();
            break;
            case R.id.menu_close:
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void callshoppingCartActivity()
    {
        Intent intent = new Intent(this,shoppingCartActivity.class);
        startActivity(intent);
    }
}
