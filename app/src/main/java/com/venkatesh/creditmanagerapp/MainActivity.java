package com.venkatesh.creditmanagerapp;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    ListView listView;
    TextView textView , editText3;
    Button button;
    EditText editText1 , editText2;
    Button button2, button3, button4;
    int n1,n2;
    int flag=0;
    int help;
    DummyDatabase d[]=new DummyDatabase[10];

    public void showusers(View view) {



        listView.setVisibility(View.VISIBLE);
        editText2.setVisibility(View.INVISIBLE);
        editText1.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        button4.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        editText3.setVisibility(View.INVISIBLE);

    }

    public void transfer(View view){

        if (! editText1.getText().toString().isEmpty() && !editText2.getText().toString().isEmpty()){
            n1= Integer.parseInt(editText1.getText().toString());
            n2= Integer.parseInt(editText2.getText().toString());
            help = 0;
            for (int i=0; i< 10; i++){
                if (i == flag&& n1>=0 && n1<=9){
                    if (d[i].getCurrent_credits() < n2){
                        Toast.makeText(this, "Transfer Failed..!! Ran Out of Credits", Toast.LENGTH_SHORT).show();


                    }else {
                        d[i].setCurrent_credits(d[i].getCurrent_credits() - n2);
                        d[i].setCredits(n2);
                        db.updateToDo(d[i]);
                        help = 1;
                        editText3.setText("CURRENT USER : "+ d[flag].getName() + "\n"+"E-mail : "+d[flag].getEmail() + "\n"+"ID : "+d[flag].getId() + "\n"+"Available Credits :" +d[flag].getCurrent_credits());


                    }
                }
            }

            if (help!=1){

                Toast.makeText(this,"INVALID ID",Toast.LENGTH_SHORT).show();
                help=0;
            }

            if (help == 1){
                if (n1>=0&&n1<=9){
                    for (int i = 0; i < 10; i++){
                        if(i == n1){
                            d[i].setCurrent_credits(d[i].getCurrent_credits() + n2);
                            db.updateToDo(d[i]);
                        }
                    }
                }else {

                    help=0;
                }

            }

            if(help == 1)

                Toast.makeText(this, "TRANSFERRED", Toast.LENGTH_SHORT).show();

            else {

                Toast.makeText(this, "NOT TRANSFERRED", Toast.LENGTH_SHORT).show();
            }

        }
        else {

            Toast.makeText(this, "NO POINTS ENTERED", Toast.LENGTH_SHORT).show();        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        db = new DatabaseHelper(getApplicationContext());

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        editText3 = findViewById(R.id.editText3);

        d[0] = new DummyDatabase("Joel Preetham","abc@gmail.com",0,1000,0,0);
        d[1] = new DummyDatabase("Venkata Kusumakar","def@gmail.com",1,1000,1,0);
        d[2] = new DummyDatabase("Prathap","ghi07@gmail.com",2,1000,2,0);
        d[3] = new DummyDatabase("Chandra Sekhar","jkl@gmail.com",3,1000,3,0);
        d[4] = new DummyDatabase("Rajesh","mno@gmail.com",4,1000,4,0);
        d[5] = new DummyDatabase("Jyoshnavi","pqr01@gmail.com",5,1000,5,0);
        d[6] = new DummyDatabase("Nikhila","stu@gmail.com",6,1000,6,0);
        d[7] = new DummyDatabase("Soundarya","vwx@gmail.com",7,1000,7,0);
        d[8] = new DummyDatabase("Angel Supriya","yza@gmail.com",8,1000,8,0);
        d[9] = new DummyDatabase("Swetha","bcd@gmail.com",9,1000,9,0);

        for (int i=0;i<10;i++)
        {
            db.createToDo(d[i]);
        }



        listView = findViewById(R.id.listView);
        ArrayList<String> users = new ArrayList<>();
        users.add("Joel Preetham");
        users.add("Venkata Kusumakar");
        users.add("Prathap");
        users.add("Chandra Sekhar");
        users.add("Rajesh");
        users.add("Jyoshnavi");
        users.add("Nikhila");
        users.add("Soundarya");
        users.add("Angel Supriya");
        users.add("Swetha");


        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,users);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                listView.setVisibility(View.INVISIBLE);
                editText1.setVisibility(View.VISIBLE);
                editText2.setVisibility(View.VISIBLE);
                editText2.setText(null);
                editText1.setText(null);
                button2.setVisibility(View.VISIBLE);
                button3.setVisibility(View.VISIBLE);
                button4.setVisibility(View.VISIBLE);
                flag = position;
                editText3.setVisibility(View.VISIBLE);
                editText3.setText("CURRENT USER : "+ d[position].getName()+  "\n"+"E-mail : "+ d[position].getEmail() + "\n"+"ID : "+ d[position].getId()+"\n"+"Credits :"+ d[position].getCurrent_credits());

            }
        });


    }

    public void Previous(View view){

        listView.setVisibility(View.VISIBLE);
        editText1.setVisibility(View.INVISIBLE);
        editText2.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
        button4.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
        editText3.setVisibility(View.INVISIBLE);
    }

    public void Home(View view){

        textView.setVisibility(View.VISIBLE);
        button.setVisibility(View.VISIBLE);
        editText1.setVisibility(View.INVISIBLE);
        editText2.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
        button4.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.INVISIBLE);
        editText3.setVisibility(View.INVISIBLE);
    }

}
