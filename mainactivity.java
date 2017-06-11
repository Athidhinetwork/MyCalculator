package com.adix.mycalculator;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private TextView screen;
    private String display="";
    private String currentOp="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen=(TextView)findViewById(R.id.textView);
        screen.setText(display);
    }
    /*
    NOW WE CREATE A METHOD THAT WILL USEFUL FOR RENDER IN OUTPUT SCREEN
     */

    private void updateScreen(){
        screen.setText(display);
    }
    /*
    now we will create a method for printing the number buttons when we will hit them
     */
    public void onClickNumber(View v){   //here it shows us to import the view file to this program
        Button b=(Button)v;
        display+=b.getText();
        updateScreen();      //the updateScreen method we created above will update the numbers in the textView

    }
      /*
    now we will create a method for printing the operator buttons when we will hit them
     */
    public void onClickOperator(View v){
        Button b=(Button)v;
        display+=b.getText();
        currentOp=b.getText().toString();
        updateScreen();
    }

    /*
    now we will create a method for calculation part and later we will set it to equal button
     */

    private double opArithmatic(String a,String b,String x){   //here i took 3 strings because our calculator will work out with given two inputs
                                                               //where a & b will be input and x will be result
                                                               //if we want to add more inputs we can take more strings.
       switch (x) {
           case "+":
               return (Double.valueOf(a) + Double.valueOf(b));
           case "-":
               return (Double.valueOf(a) - Double.valueOf(b));
           case "*":
               return (Double.valueOf(a) * Double.valueOf(b));
           case "/":
               try {
                   return (Double.valueOf(a) / Double.valueOf(b));
               } catch (Exception e) {
                   Log.d("Calc", e.getMessage());              //we use this for showing the error message
               }                                               //when we divide something with '0'
           default:
               return -1;
       }
    }

    /*
    now we will set the above method to equal button
     */
        public void onClickEqual(View v){
            String[] calculation=display.split(Pattern.quote(currentOp));
            Double result;
            if (calculation.length<2){
                return;
            }
            else{
                result=opArithmatic(calculation[0],calculation[1],currentOp);
                screen.setText(display+"\n"+result);  //the result will come after a new line
            }
        }
    public void clear(){
        display="";
        currentOp="";
    }                                                  //method to clear the screen
    public void onClickClear(View v){
        clear();
        updateScreen();
    }
}
