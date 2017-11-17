package cis350.upenn.edu.easyfooddiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;

public class SocialActivity extends AppCompatActivity {

    protected EditText searchEditText;
    protected Button buttonSearch;
    String uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        searchEditText = (EditText) findViewById(R.id.searchText);
        buttonSearch = (Button) findViewById(R.id.searchButton);

        //adding listener to button
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname = searchEditText.getText().toString();
                DatabaseReference root = FirebaseDatabase.getInstance().getReference();
                DatabaseReference profile = root.child("profile");
                //email = "ZxInLv9EmnRdzBtgzSRA0LYgms52";

                profile.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if(uname.isEmpty()){
                            Toast.makeText(SocialActivity.this, "User does not exist!", Toast.LENGTH_SHORT).show();
                        }else if (snapshot.hasChild(uname)) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            DatabaseReference myref_friend = FirebaseDatabase.getInstance().getReference("friendsName");
                            DatabaseReference myref_profile = FirebaseDatabase.getInstance().getReference("profile").child(uname);
                            myref_friend.child(user.getUid()).setValue(uname);


                            myref_profile.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    // This method is called once with the initial value and again
                                    // whenever data at this location is updated.
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    String uid = dataSnapshot.getValue(String.class);
                                    DatabaseReference id_friend = FirebaseDatabase.getInstance().getReference("friendsId");
                                    id_friend.child(user.getUid()).setValue(uid);
                                }

                                @Override
                                public void onCancelled(DatabaseError error) {
                                    // Failed to read value
                                    Log.w("tag", "Failed to read value.", error.toException());
                                }
                            });

                            Toast.makeText(SocialActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(SocialActivity.this, "User does not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("tag", "Failed to read value.", databaseError.toException());

                    }
                });


            }
        });
    }


}
