package android.helloandroiders.com.smssorter;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MessageGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String groupName = intent.getStringExtra("key");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        MessageGroupActivity.PlaceholderFragment place_holder = new MessageGroupActivity.PlaceholderFragment();
        place_holder.setGroupName(groupName);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.message_group_fragment, place_holder)
                    .commit();
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

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
          //  return true;
        //}

        return super.onOptionsItemSelected(item);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        String groupName = "";

        public PlaceholderFragment() {
        }
        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            //View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            SmsSorterController controller = SmsSorterController.getInstance();
            MessageGroups group = SmsSorterController.getInstance().getGroupNames().GetGroupNameMap().get(groupName);


//            // our adapter instance
//            //MessageContentAdapter adapter = new MessageContentAdapter(getActivity(), R.layout.message_content_listview_item, data);
//
//            MessageContentAdapter adapter = new MessageContentAdapter(getActivity(), R.layout.message_content_listview_item, data);
//            // create a new ListView, set the adapter and item click listener
            ListView listViewItems = new ListView(getActivity());
//            listViewItems.setAdapter(adapter);
//            //listViewItems.setOnItemClickListener(new ListItemClickListener());
//
//
//            //if (savedInstanceState == null) {
//            //setContentView(listViewItems);
            return listViewItems;
        }
    }
}

