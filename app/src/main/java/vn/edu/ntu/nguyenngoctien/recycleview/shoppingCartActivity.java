package vn.edu.ntu.nguyenngoctien.recycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.IccOpenLogicalChannelResponse;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.ntu.nguyenngoctien.controller.ICartController;
import vn.edu.ntu.nguyenngoctien.model.Product;

public class shoppingCartActivity extends AppCompatActivity {
    TextView txtCartInfo;
    Button btnSubmit,btnClear;
    Product p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addViews();
        addEvents();
    }
    private void addViews(){
        txtCartInfo = findViewById(R.id.txtCartInfo);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnClear = findViewById(R.id.btnClear);
    }
    private void addEvents(){
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ICartController controller = (ICartController) getApplication();
                controller.clearShoppingCart();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ICartController controller = (ICartController) getApplication();
                controller.addToShoppingCart(p);
            }
        });
    }

    private void viewCartInfo(){
        ICartController controller = (ICartController) getApplication();
        ArrayList<Product> listProduct = controller.getShoppingCart();
        StringBuilder builder = new StringBuilder();
        for (Product p: listProduct){
            builder.append(p.getName() + "\t\t\t"+p.getPrice()+"vnd\n");

        }
        if (builder.toString().length()>0){
            txtCartInfo.setText(builder.toString());
        }
        else txtCartInfo.setText("Khong co mat hang nao trong gio hang");
    }
}
