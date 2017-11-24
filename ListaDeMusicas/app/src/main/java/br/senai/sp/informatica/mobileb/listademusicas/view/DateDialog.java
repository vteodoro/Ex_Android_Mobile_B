package br.senai.sp.informatica.mobileb.listademusicas.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;

public class DateDialog extends DialogFragment {
    private View view;
    private Calendar calendar;
    private EditText editText;
    private static DateFormat fmt = DateFormat.getDateInstance(DateFormat.LONG);

    public Dialog onCreateDialog(Bundle savedInstanceState){
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                calendar.set(ano, mes, dia);
                editText.setText(fmt.format(calendar.getTime()));
            }
        };

        try{
            calendar.setTime(fmt.parse(editText.getText().toString()));
        }catch (ParseException e){
        }

        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH);
        int ano = calendar.get(Calendar.YEAR);

        DatePickerDialog dialog = new DatePickerDialog(view.getContext(), listener, ano, mes, dia);
        return dialog;
    }

    public void setView(View view){
        this.view = view;
    }

    public void setCalendar(Calendar calendar){
        this.calendar = calendar;
    }

    public void setEditText(EditText editText){
        this.editText = editText;
    }


}
