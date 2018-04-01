/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
       // displayMessage(createOderSummary(0));
        //Whipped Check Box
       CheckBox whippedCreamBox = (CheckBox)findViewById(R.id.whipped_cream);
        boolean hasWhippedCream = whippedCreamBox.isChecked();
       Log.v("Main Activity","The Whipped Cream box" + hasWhippedCream);

       //Chocolate Check Box
       CheckBox chocolateBox = (CheckBox)findViewById(R.id.chocolate_Box);
       boolean hasChocolate = chocolateBox.isChecked();
        Log.v("Main Activity","The Whipped Cream box" + hasChocolate);

          int price = quantity;
         calculatePrice(hasWhippedCream,hasChocolate);
     displayMessage(createOderSummary(price,hasWhippedCream,hasChocolate));
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, "bhagirathi694@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT,"My friend");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        getName();

    }

    public String createOderSummary(int price,boolean addWhippedCream,boolean addChocolate){
        String priceMessage = getString(R.string.name)+" " + getName();
        priceMessage += "\nAdd whipped cream? " + addWhippedCream ;
        priceMessage += "\nAdd Chocolate? " + addChocolate;   Log.v("MainActivity",priceMessage);
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\ntotal: $" + calculatePrice(addWhippedCream,addChocolate) ;
        priceMessage += "\nThank you!";
       return priceMessage;
    }

    public String getName(){
        EditText nameTextView = (EditText) findViewById(R.id.name_view);
        String hasName = nameTextView.getText().toString();
        return hasName;
    }
    /**
     * Calculates the price of the order.
     *
     *
     */
    private int calculatePrice(boolean addWhippedCream,boolean addChocolate) {
        int basePrice = 5;
        if(addWhippedCream){
            basePrice += 1;
        }
        if(addChocolate){
            basePrice += 2;
        }
        return quantity * basePrice;
    }

    public void increment(View view) {
    if(quantity > 99){
        Toast.makeText(this,"You Can't have more than 100 coffee", Toast.LENGTH_SHORT).show();
        return;
    }
            quantity = quantity + 1;
    displayQuantity(quantity);
    }

    public void decrement(View view) {
    if(quantity == 1){
        Toast.makeText(this,"You Can't have less than 1 coffee", Toast.LENGTH_SHORT).show();
        return;
    }
            quantity = quantity - 1;
            displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int value) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(" " + value);
    }
    /**
     * This method displays the given price on the screen.
     */

    /**
     * This method displays the given text on the screen.
     */
   private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }

}