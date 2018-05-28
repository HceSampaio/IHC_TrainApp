package ihc.ihc_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
        Client c = Client.getInstance();
        if( c.logged_in() ){
            Intent intent = new Intent (this, routineActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(MainMenu.this, "Please Login to access this feature!", Toast.LENGTH_SHORT).show();
        }
    }

    public void open_maps (View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void open_procura (View view){
        Intent intent = new Intent (this, procurar_comboio.class);
        startActivity(intent);
    }

    public void open_bilhetes (View view) {
        Intent intent = new Intent (this, BilhetesActivity.class);
        startActivity(intent);
    }

    public void logout (View view){
        Client.resetClient();
        finish();
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }

}
