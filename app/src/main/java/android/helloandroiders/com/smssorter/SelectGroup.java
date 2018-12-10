package android.helloandroiders.com.smssorter;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

                    import android.content.Intent;
                    import android.os.Bundle;
                    import android.support.design.widget.FloatingActionButton;
                    import android.support.design.widget.Snackbar;
                    import android.support.design.widget.TabLayout;
                    import android.support.v4.app.FragmentTransaction;
                    import android.support.v4.view.ViewPager;
                    import android.support.v7.app.AppCompatActivity;
                    import android.support.v7.widget.Toolbar;
                    import android.view.LayoutInflater;
                    import android.view.View;
                    import android.view.Menu;
                    import android.view.MenuItem;
                    import android.support.v4.app.Fragment;
                    import android.view.ViewGroup;
                    import android.widget.ListView;

public class SelectGroup extends AppCompatActivity {

    SmsSorterController appController = SmsSorterController.getInstance();
    PlaceHolderFragment placeHolderFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sms);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        SelectGroup.PlaceholderFragment place_holder = new SelectGroup.PlaceholderFragment();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_main_fragment_sms, place_holder)
                    .commit();
        }

    }

            /**
             * A placeholder fragment containing a simple view.
             */
            public static class PlaceholderFragment extends Fragment {

                public PlaceholderFragment() {
                }

                @Override
                public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                         Bundle savedInstanceState) {
                    //View rootView = inflater.inflate(R.layout.fragment_main, container, false);

                    SmsSorterController controller = SmsSorterController.getInstance();
                    //Object []groupArrayObjects = new Object[controller.getGroupNames().GetGroupNameMap().values().size()];
                    Object []groupArrayObjects = controller.getGroupNames().GetGroupNameMap().values().toArray();
                    MessageGroups []groupArray = new MessageGroups[groupArrayObjects.length];
                    for(int i = 0; i < groupArrayObjects.length; ++i) {
                        groupArray[i] = (MessageGroups)(groupArrayObjects[i]);
                    }

                    GroupListAdapter adapter = new GroupListAdapter(getActivity(), R.layout.message_group_listitem_layout, groupArray);
                    controller.setCurrentActivity(getActivity());

                    ListView listViewItems = new ListView(getActivity());
                    listViewItems.setAdapter(adapter);
                    ClickListener clickListener = new ClickListener();
                    clickListener.SetType(ClickListener.Listenertype.Group);//change this
                    listViewItems.setOnItemClickListener(clickListener);

                    return listViewItems;

        //            SmsMessage[] data = new SmsMessage[controller.getSmsDetails().size()];
        //            controller.getSmsDetails().toArray(data);
        //
        //
        //            // our adapter instance
        //            SmsArrayAdapter adapter = new SmsArrayAdapter(getActivity(), R.layout.listview_item_layout, data);
        //            controller.setCurrentActivity(getActivity());
        //
        //            // create a new ListView, set the adapter and item click listener
        //            ListView listViewItems = new ListView(getActivity());
        //            listViewItems.setAdapter(adapter);
        //            ClickListener clickListener = new ClickListener();
        //            clickListener.SetType(ClickListener.Listenertype.Address);
        //            listViewItems.setOnItemClickListener(clickListener);
        //
        //
        //            //if (savedInstanceState == null) {
        //            //setContentView(listViewItems);
        //            return listViewItems;
                }
            }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_sms, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }
}
