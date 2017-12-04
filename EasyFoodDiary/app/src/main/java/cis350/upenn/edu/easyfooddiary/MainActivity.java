package cis350.upenn.edu.easyfooddiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        //have to differentiate amongst buttons
        if (view.getId() == R.id.your_information) {
            Intent i = new Intent(this, InformationActivity.class);
            startActivity(i);
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
        //接下来这部分是新添加的，这里表示 如果button是Calories就启动CaloriestActivity
        else if (view.getId() == R.id.caloriesButton) {
            Intent i = new Intent(this, CaloriesActivity.class);
            startActivity(i);
            Log.v("MainActivity", "DEBUG start!!!");
        }



       /* else if (view.getId() == R.id.sleep) {
            Intent i = new Intent(this, CalendarActivity.class);
            i.putExtra("Type","sleep");
            startActivity(i);

        } */


        else if (view.getId() == R.id.sleep) {
            Intent i = new Intent(this, SleepActivity.class);//start sleepactivity directly
            i.putExtra("Type","sleep");
            startActivity(i);

        }



        else if(view.getId() == R.id.UserProfile){
            Intent i = new Intent(this, ProfileActivity.class);
            startActivity(i);

        }else if(view.getId() == R.id.Social){
            Intent i = new Intent(this, SocialActivity.class);
            startActivity(i);
        }
    }
}
