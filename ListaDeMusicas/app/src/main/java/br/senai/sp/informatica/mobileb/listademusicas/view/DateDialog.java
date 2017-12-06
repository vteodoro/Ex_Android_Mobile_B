package br.senai.sp.informatica.mobileb.listademusicas.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;
import java.text.DateFormat;
import java.util.Calendar;

public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private Calendar calendar;
    private int editTextResource;
    private static DateFormat fmt = DateFormat.getDateInstance(DateFormat.LONG);

    public static DateDialog makeDialog(Calendar calendar, int editTextResource) {
        DateDialog dialog = new DateDialog();
        dialog.calendar = calendar;
        dialog.editTextResource = editTextResource;
        return dialog;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if(calendar == null){
            long cal = savedInstanceState.getLong("cal");
            calendar = Calendar.getInstance();
            calendar.setTimeInMillis(cal);
            editTextResource = savedInstanceState.getInt("edit");
        }

        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH);
        int ano = calendar.get(Calendar.YEAR);

        return new DatePickerDialog(getActivity(), this, ano, mes, dia);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
        calendar.set(ano, mes, dia);
        EditText editText = getActivity().findViewById(editTextResource);
        editText.setText(fmt.format(calendar.getTime()));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putLong("cal", calendar.getTimeInMillis());
        outState.putInt("edit", editTextResource);
        super.onSaveInstanceState(outState);
    }
}