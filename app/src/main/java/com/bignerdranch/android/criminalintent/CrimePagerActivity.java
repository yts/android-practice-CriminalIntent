package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.support.v4.app.*;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Admin on 5/6/2014.
 */
public class CrimePagerActivity extends FragmentActivity implements DateTimeDialogFragment.OnFragmentInteractionListener{
    private ViewPager mViewPager;
    private ArrayList<Crime> mCrimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        mCrimes = CrimeLab.get(this).getCrimes();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Crime crime = mCrimes.get(position);
                if (crime.getTitle() != null) {
                    setTitle(crime.getTitle());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        UUID crimeId = (UUID) getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    @Override
    public void onFragmentInteraction(DialogFragment dialog, int which) {
        FragmentStatePagerAdapter adapter = (FragmentStatePagerAdapter) mViewPager.getAdapter();
        int currentIndex = mViewPager.getCurrentItem();
        DateTimeDialogFragment.OnFragmentInteractionListener currentFragment =
                (DateTimeDialogFragment.OnFragmentInteractionListener) adapter.instantiateItem(mViewPager, currentIndex);
        currentFragment.onFragmentInteraction(dialog, which);
    }
}
