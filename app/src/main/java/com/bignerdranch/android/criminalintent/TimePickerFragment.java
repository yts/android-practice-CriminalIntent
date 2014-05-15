package com.bignerdranch.android.criminalintent;



import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Date;
import java.util.GregorianCalendar;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Use the {@link TimePickerFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class TimePickerFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String EXTRA_DATE = "com.bignerdranch.android.criminalintent.date";

    // TODO: Rename and change types of parameters
    private Date mDate;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param date Parameter 1.
     * @return A new instance of fragment TimePickerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TimePickerFragment newInstance(Date date) {
        TimePickerFragment fragment = new TimePickerFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATE, date);
        fragment.setArguments(args);
        return fragment;
    }
    public TimePickerFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getArguments() == null) {
            return null;
        }

        mDate = (Date)getArguments().getSerializable(EXTRA_DATE);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(mDate);
        int hour = calendar.get(GregorianCalendar.HOUR_OF_DAY);
        int minute = calendar.get(GregorianCalendar.MINUTE);

        return new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(mDate);
                calendar.set(GregorianCalendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(GregorianCalendar.MINUTE, minute);
                mDate = calendar.getTime();

                Intent i = new Intent();
                i.putExtra(EXTRA_DATE, mDate);

                getTargetFragment()
                        .onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
            }
        }, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }



}
