            package android.helloandroiders.com.smssorter;

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

            public class MainSmsActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

                SmsSorterController appController = SmsSorterController.getInstance();
                PlaceHolderFragment placeHolderFragment;
                private TabLayout tabLayout;
                private ViewPager viewPager;
                private SmsPageAdapter pageAdapter;
                private String[] tabs = { "Groups", "All messages"};
                private Menu mMenu;

                @Override
                protected void onCreate(Bundle savedInstanceState) {
                        super.onCreate(savedInstanceState);
                        setContentView(R.layout.activity_main_sms);
                        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                        setSupportActionBar(toolbar);
                        viewPager = (ViewPager) findViewById(R.id.pager);
                        pageAdapter = new SmsPageAdapter(getSupportFragmentManager());
                        viewPager.setAdapter(pageAdapter);

                       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                        fab.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int position = viewPager.getCurrentItem();
                                if (position==0) {
                                    Intent myIntent = new Intent(MainSmsActivity.this, AddGroup.class);
                                    //myIntent.putExtra("key", SmsSorterController.getInstance().getSmsDetails().get(position).address);
                                    MainSmsActivity.this.startActivity(myIntent);
                                } else {

                                    //Snackbar.make(view, "On Address Widget Click", Snackbar.LENGTH_LONG)
                                          //.setAction("Action", null).show();
                                    Intent myIntent = new Intent(MainSmsActivity.this, SelectGroup.class);
                                    MainSmsActivity.this.startActivity(myIntent);
                                    //Show a new activity
                                }
                            }
                        });
                        appController.Initialize(this);
                        /* if (savedInstanceState == null) {
                            placeHolderFragment = new PlaceholderFragment();
                            getSupportFragmentManager().beginTransaction()
                                    .add(R.id.content_main_sms, placeHolderFragment)
                                    .commit();
                        } */
                    //Initializing the tablayout
                    tabLayout = (TabLayout) findViewById(R.id.tabLayout);

                    //Adding the tabs using addTab() method
                    //tabLayout.addTab(tabLayout.newTab().setText("Group"));
                    //tabLayout.addTab(tabLayout.newTab().setText("All Messages"));
                    //tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

                    //Initializing viewPager
                    //viewPager = (ViewPager) findViewById(R.id.pager);

                    //Creating our pager adapter
                    //SmsPageAdapter adapter = new SmsPageAdapter(getSupportFragmentManager());

                    //Adding adapter to pager
                    //viewPager.setAdapter(adapter);

                    //Adding onTabSelectedListener to swipe views
                    //tabLayout.setOnTabSelectedListener(this);
                    tabLayout.setupWithViewPager(viewPager);
                    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        public void onPageScrollStateChanged(int state) {
                        }

                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        }

                        public void onPageSelected(int position) {
                            switch (position) {
                                case 0:
                                    if (mMenu != null) {
                                        MenuItem edit_item = mMenu.findItem(R.id.action_edit);
                                        edit_item.setVisible(false);
                                    }
                                    break;
                                case 1:
                                    if (mMenu != null) {
                                        MenuItem edit_item = mMenu.findItem(R.id.action_edit);
                                        edit_item.setVisible(true);
                                    }
                                    break;
                            }
                        }
                    });


                }
                /*@Override
                public void onResume() {
                    super.onResume();
                    //FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    //ft.detach(placeHolderFragment).attach(placeHolderFragment).commit();
                    ////finish();
                    ////startActivity(getIntent());

                }*/

                @Override
                public boolean onCreateOptionsMenu(Menu menu) {
                    // Inflate the menu; this adds items to the action bar if it is present.
                    mMenu = menu;
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
                    if (id == R.id.action_edit) {
                        appController.setEditEnabledForPage(1);
                        viewPager.setAdapter(pageAdapter);
                        viewPager.setCurrentItem(1);
                        return true;
                    }

                    return super.onOptionsItemSelected(item);
                }

        //        /**
        //         * A placeholder fragment containing a simple view.
        //         */
        //        public static class PlaceholderFragment extends Fragment {
        //
        //            public PlaceholderFragment() {
        //            }
        //
        //            @Override
        //            public View onCreateView(LayoutInflater inflater, ViewGroup container,
        //                                     Bundle savedInstanceState) {
        //                //View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        //
        //                SmsSorterController controller = SmsSorterController.getInstance();
        //                Object []groupArrayObjects = new Object[controller.getGroupNames().GetGroupNameMap().values().size()];
        //                groupArrayObjects = controller.getGroupNames().GetGroupNameMap().values().toArray();
        //                MessageGroups []groupArray = new MessageGroups[groupArrayObjects.length];
        //                for(int i = 0; i < groupArrayObjects.length; ++i) {
        //                    groupArray[i] = (MessageGroups)(groupArrayObjects[i]);
        //                }
        //
        //                GroupListAdapter adapter = new GroupListAdapter(getActivity(), R.layout.message_group_listitem_layout, groupArray);
        //                controller.setCurrentActivity(getActivity());
        //
        //                ListView listViewItems = new ListView(getActivity());
        //                listViewItems.setAdapter(adapter);
        //                ClickListener clickListener = new ClickListener();
        //                clickListener.SetType(ClickListener.Listenertype.Group);
        //                listViewItems.setOnItemClickListener(clickListener);
        //
        //                return listViewItems;
        //
        //    //            SmsMessage[] data = new SmsMessage[controller.getSmsDetails().size()];
        //    //            controller.getSmsDetails().toArray(data);
        //    //
        //    //
        //    //            // our adapter instance
        //    //            SmsArrayAdapter adapter = new SmsArrayAdapter(getActivity(), R.layout.listview_item_layout, data);
        //    //            controller.setCurrentActivity(getActivity());
        //    //
        //    //            // create a new ListView, set the adapter and item click listener
        //    //            ListView listViewItems = new ListView(getActivity());
        //    //            listViewItems.setAdapter(adapter);
        //    //            ClickListener clickListener = new ClickListener();
        //    //            clickListener.SetType(ClickListener.Listenertype.Address);
        //    //            listViewItems.setOnItemClickListener(clickListener);
        //    //
        //    //
        //    //            //if (savedInstanceState == null) {
        //    //            //setContentView(listViewItems);
        //    //            return listViewItems;
        //            }
        //        }
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    //viewPager.setCurrentItem(tab.getPosition());
                }
                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }
                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            }
