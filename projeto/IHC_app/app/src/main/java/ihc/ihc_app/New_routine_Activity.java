package ihc.ihc_app;


import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.zip.Inflater;

public class New_routine_Activity extends AppCompatActivity implements com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener{

    Routine routine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_routine_);

        routine = new Routine();

        CardView cv = (CardView)findViewById(R.id.fim_date_card);
        cv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar now = Calendar.getInstance();
                DatePickerDialog datePD = DatePickerDialog.newInstance(New_routine_Activity.this, now.get(Calendar.YEAR),now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
                datePD.setTitle("Escolher Data Fim da Rotina");
                datePD.show(getFragmentManager(),"Data Fim");

            }
        });
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Data d = new Data(dayOfMonth,monthOfYear,year);
        routine.setData_fim(d.toString());
        TextView tv = (TextView) findViewById(R.id.fim_date);
        tv.setText(d.toString());
        Toast.makeText(New_routine_Activity.this, String.format("Data de Fim da Rotina Alterada para %s)",d.toString()), Toast.LENGTH_LONG).show();
    }




}
