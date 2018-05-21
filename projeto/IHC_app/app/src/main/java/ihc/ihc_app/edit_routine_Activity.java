package ihc.ihc_app;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

public class edit_routine_Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    Routine routine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_routine_);

        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Data d = new Data(dayOfMonth,monthOfYear+1,year);
        routine.setData_fim(d.toString());
        TextView tv = (TextView) findViewById(R.id.fim_date);
        tv.setText(d.toString());
        //Toast.makeText(New_routine_Activity.this, String.format("Data de Fim da Rotina Alterada para %s)",d.toString()), Toast.LENGTH_LONG).show();
    }

    public void box_clicked(View view){
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.Segunda:
                if (checked){
                    routine.addRepetir("Seg");
                }
                else{
                    routine.removeRepetir("Seg");
                }
                break;
            case R.id.Terca:
                if (checked){
                    routine.addRepetir("Ter");
                }
                // Put some meat on the sandwich
                else{

                    routine.removeRepetir("Ter");

                }
                break;
            case R.id.Quarta:
                if (checked){
                    routine.addRepetir("Qua");
                }
                // Put some meat on the sandwich
                else{

                    routine.removeRepetir("Qua");

                }
                break;
            case R.id.Quinta:
                if (checked){
                    routine.addRepetir("Qui");
                }
                // Put some meat on the sandwich
                else{

                    routine.removeRepetir("Qui");
                }
                break;
            case R.id.Sexta:
                if (checked){
                    routine.addRepetir("Sex");
                }
                // Put some meat on the sandwich
                else{

                    routine.removeRepetir("Sex");
                }
                break;
            case R.id.Sabado:
                if (checked){
                    routine.addRepetir("Sáb");
                }
                // Put some meat on the sandwich
                else{

                    routine.removeRepetir("Sáb");

                }
                break;
            case R.id.Domingo:
                if (checked){
                    routine.addRepetir("Dom");
                }
                // Put some meat on the sandwich
                else{
                    routine.removeRepetir("Dom");
                }
                break;

        }
    }
}
