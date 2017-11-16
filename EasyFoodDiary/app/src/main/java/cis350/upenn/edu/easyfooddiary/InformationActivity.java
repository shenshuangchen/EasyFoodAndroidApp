package cis350.upenn.edu.easyfooddiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;


public class InformationActivity extends AppCompatActivity {

    protected InformationView informationView;
    /*protected String namer;
    protected String birthdate;
    protected String eGoal;
    protected String wGoal;
    protected String mGoal;*/
    protected String [] strArray = new String[5];
    /*protected EditText editText_name;
    protected EditText editText_date;
    protected EditText editText_egoal;
    protected EditText editText_wgoal;
    protected EditText editText_mgoal;*/
    //Added Variables!
    static int index = 0;
    protected EditText[] et_array = new EditText[5];
    protected DatabaseReference[] dbr = new DatabaseReference[5];
    protected String[] refName = new String[5];
    /////////////////////////////////////
    protected void initRefName(){
        refName[0] = "name";
        refName[1] = "date";
        refName[2] = "egoal";
        refName[3] = "wgoal";
        refName[4] = "mgoal";
    }
    ////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        informationView = new InformationView(this);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        //Modified Part!
        initRefName();
        reference(database);


        /*DatabaseReference myref_name = database.getReference("name");
        myref_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                editText_name = (EditText) findViewById(R.id.Name);
                editText_name.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("tag", "Failed to read value.", error.toException());
            }
        });

        DatabaseReference myref_date = database.getReference("date");
        myref_date.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                editText_date = (EditText) findViewById(R.id.Date);
                editText_date.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("tag", "Failed to read value.", error.toException());
            }
        });

        DatabaseReference myref_egoal = database.getReference("egoal");
        myref_egoal.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                editText_egoal = (EditText) findViewById(R.id.eGoal);
                editText_egoal.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("tag", "Failed to read value.", error.toException());
            }
        });

        DatabaseReference myref_wgoal = database.getReference("wgoal");
        myref_wgoal.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                editText_wgoal = (EditText) findViewById(R.id.wGoal);
                editText_wgoal.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("tag", "Failed to read value.", error.toException());
            }
        });

        DatabaseReference myref_mgoal = database.getReference("mgoal");
        myref_mgoal.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                editText_mgoal = (EditText) findViewById(R.id.mGoal);
                editText_mgoal.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("tag", "Failed to read value.", error.toException());
            }
        });*/

    }
    ////////////////////////////////////////////////////
    private void findEditTextId(){
        et_array[0] = (EditText) findViewById(R.id.Name);
        et_array[1] = (EditText) findViewById(R.id.Date);
        et_array[2] = (EditText) findViewById(R.id.eGoal);
        et_array[3] = (EditText) findViewById(R.id.wGoal);
        et_array[4] = (EditText) findViewById(R.id.mGoal);
    }

    private void reference(FirebaseDatabase database){
        findEditTextId();
        for(int i = 0;i < 5;i++){
            dbr[i] = database.getReference(refName[i]);
            index = i;
            dbr[i].addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated
                    String value = dataSnapshot.getValue(String.class);
                    //et_array[index] = (EditText) findViewById(R.id.Name);
                    et_array[index].setText(value);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("tag", "Failed to read value.", error.toException());
                }
            });

        }
    }
    /////////////////////////////////////////////////
    protected void onClick(View view) {
        /*if (view.getId() == R.id.main) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        } else {*/
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            /*DatabaseReference myref_name = database.getReference("name");
            DatabaseReference myref_date = database.getReference("date");
            DatabaseReference myref_egoal = database.getReference("egoal");
            DatabaseReference myref_wgoal = database.getReference("wgoal");
            DatabaseReference myref_mgoal = database.getReference("mgoal");*/

            for(int i = 0;i < 5;i++){
                dbr[i] = database.getReference(refName[i]);
            }

            Log.v("Setting name", "done");

            findEditTextId();
            for(int i = 0;i < 5;i++){
                strArray[i] = et_array[i].getText().toString();
            }
            /*editText_name = (EditText) findViewById(R.id.Name);
            namer = editText_name.getText().toString();

            editText_date = (EditText) findViewById(R.id.Date);
            birthdate = editText_date.getText().toString();

            editText_egoal = (EditText) findViewById(R.id.eGoal);
            eGoal = editText_egoal.getText().toString();

            editText_wgoal = (EditText) findViewById(R.id.wGoal);
            wGoal = editText_wgoal.getText().toString();

            editText_mgoal = (EditText) findViewById(R.id.mGoal);
            mGoal = editText_mgoal.getText().toString();*/

            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
            try {
                /*df.parse(birthdate);
                myref_name.setValue(namer);
                myref_date.setValue(birthdate);
                myref_egoal.setValue(eGoal);
                myref_wgoal.setValue(wGoal);
                myref_mgoal.setValue(mGoal);*/

                df.parse(strArray[1]);
                for(int i = 0;i < 5;i++)
                    dbr[i].setValue(strArray[i]);


                Toast.makeText(informationView.getContext(),
                        "Your information has been saved", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            } catch (ParseException e) {
                Toast.makeText(informationView.getContext(),
                        "Please enter valid date format", Toast.LENGTH_SHORT).show();
            }
        //}
    }

}
