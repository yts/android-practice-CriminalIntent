package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;
import com.bignerdranch.android.criminalintent.SingleFragmentActivity;

public class CrimeActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }
}