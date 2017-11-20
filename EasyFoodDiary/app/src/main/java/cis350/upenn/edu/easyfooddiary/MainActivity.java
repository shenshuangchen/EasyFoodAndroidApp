package cis350.upenn.edu.easyfooddiary;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    protected NotificationCompat.Builder notification;
    protected NotificationCompat.Builder notification_2;

    private static final int uniqueID = 45612;
    private static final int uniqueID2 = 45613;

    //protected String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setNotificationFitnessMinute();

//        notification = new NotificationCompat.Builder(this);
//        notification.setAutoCancel(false);
//        notification.setSmallIcon(R.drawable.food_diary_title);
//        notification.setTicker("This is the ticker");
//        notification.setWhen(System.currentTimeMillis());
//        notification.setContentTitle("This is the title");
//        notification.setContentText(editText_minutesPerWorkout.getText().toString());
//
//        notification.setContentText("this is the context");
//        Intent i = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
//        notification.setContentIntent(pendingIntent);
//        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        nm.notify(uniqueID, notification.build());

    }

    public void onClick(View view) {
        //have to differentiate amongst buttons
        if (view.getId() == R.id.your_information) {
            Intent i = new Intent(this, InformationActivity.class);
            startActivity(i);
//            notification.setSmallIcon(R.drawable.food_diary_title);
//            notification.setTicker("This is the ticker");
//            notification.setWhen(System.currentTimeMillis());
//            notification.setContentTitle("This is the title");
//            notification.setContentText("this is the context");
//            Intent i = new Intent(this, MainActivity.class);
//            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
//            notification.setContentIntent(pendingIntent);
//            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//            nm.notify(uniqueID, notification.build());
        }
        else if (view.getId() == R.id.motivation) {
            Intent i = new Intent(this, MotivationActivity.class);
            startActivity(i);
        }
        else if (view.getId() == R.id.calendar) {
            Intent i = new Intent(this, CalendarActivity.class);
            i.putExtra("Type","calendar");
            startActivity(i);
        }
        else if (view.getId() == R.id.fitness_goals) {
            Intent i = new Intent(this, FitnessActivity.class);
            startActivity(i);
        }

        else if (view.getId() == R.id.nutrition) {
            Intent i = new Intent(this, NutritionActivity.class);
            startActivity(i);
        }

        else if (view.getId() == R.id.calc) {
            Intent i = new Intent(this, CalculatorActivity.class);
            startActivity(i);
        }

        else if (view.getId() == R.id.bmi) {
            Intent i = new Intent(this, BMIActivity.class);
            startActivity(i);
        }
        else if (view.getId() == R.id.vitals) {
            Intent i = new Intent(this, CalendarActivity.class);
            i.putExtra("Type","vitals");
            startActivity(i);
        }
        else if (view.getId() == R.id.sleep) {
            Intent i = new Intent(this, CalendarActivity.class);
            i.putExtra("Type","sleep");
            startActivity(i);

        }else if(view.getId() == R.id.UserProfile){
            Intent i = new Intent(this, ProfileActivity.class);
            startActivity(i);

        }else if(view.getId() == R.id.Social){
            Intent i = new Intent(this, SocialActivity.class);
            startActivity(i);
        }
    }




    public void setNotificationFitnessMinute(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference myref_minutesPerWorkout = FirebaseDatabase.getInstance().getReference("Minutes_Per_Workout").child(user.getUid());

        myref_minutesPerWorkout.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);

                notification = new NotificationCompat.Builder(MainActivity.this);
                notification.setAutoCancel(false);
                notification.setSmallIcon(R.drawable.food_diary_title);
                notification.setTicker("This is the ticker");
                notification.setWhen(System.currentTimeMillis());
                notification.setContentTitle("DESIRED MINUTES");
                notification.setContentText("YOU NEED TO FINISH "+value+" MINUTES!!");
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
                notification.setContentIntent(pendingIntent);
                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                nm.notify(uniqueID, notification.build());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Log.w("tag", "Failed to read value.", databaseError.toException());
            }
        });
    }


}
