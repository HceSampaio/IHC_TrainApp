package ihc.ihc_app;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Globals global = (Globals)getApplication();
        Client client = Client.getInstance();
        TextView textElement = (TextView) findViewById(R.id.username);
        //textElement.setText("I love you"); //leave this line to assign a specific text
        textElement.setText(client.getName()); //leave this line to assign a string resource

    }
    public void open_routines (View view){
        Intent intent = new Intent (this, routineActivity.class);
        startActivity(intent);
    }

}
