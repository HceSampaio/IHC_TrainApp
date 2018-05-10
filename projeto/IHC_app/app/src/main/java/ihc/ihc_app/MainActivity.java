package ihc.ihc_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Globals global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void open_login (View view){
        Intent intent = new Intent (this, LoginActivity.class);
        startActivity(intent);
    }

    public void open_routines (View view){
        if( global.logged_in() ){
            Intent intent = new Intent (this, routineActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(MainActivity.this, "Please Login to access this feature!", Toast.LENGTH_LONG).show();
        }
    }

}
