package android.helloandroiders.com.smssorter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by nilotpal on 22/10/17.
 */

public class SmsPageAdapter extends FragmentPagerAdapter {

    public SmsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return PlaceHolderFragment.getPlaceHolderFragment(FragmentType.Group);
            case 1:
                return PlaceHolderFragment.getPlaceHolderFragment(FragmentType.SmsContent);
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "Groups";
            case 1:
                return "All Messages";
        }
        return "";
    }
}
