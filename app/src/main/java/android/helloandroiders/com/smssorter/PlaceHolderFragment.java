package android.helloandroiders.com.smssorter;

/**
 * Created by nilotpal on 22/10/17.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


enum FragmentType {
    Group,
    SmsContent
}
/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceHolderFragment extends Fragment {

    FragmentType fragmentType;
    static PlaceHolderFragment getPlaceHolderFragment(final FragmentType fragmentType) {
        PlaceHolderFragment phfragment  = new PlaceHolderFragment();
        phfragment.fragmentType = fragmentType;
        return phfragment;
    }

    public PlaceHolderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        SmsSorterController controller = SmsSorterController.getInstance();
        ListView listViewItems = null;
        ClickListener clickListener = new ClickListener();
        switch (fragmentType) {
            case Group:
                Object[] groupArrayObjects = new Object[controller.getGroupNames().GetGroupNameMap().values().size()];
                groupArrayObjects = controller.getGroupNames().GetGroupNameMap().values().toArray();
                MessageGroups[] groupArray = new MessageGroups[groupArrayObjects.length];
                for (int i = 0; i < groupArrayObjects.length; ++i) {
                    groupArray[i] = (MessageGroups) (groupArrayObjects[i]);
                }

                GroupListAdapter adapter = new GroupListAdapter(getActivity(), R.layout.message_group_listitem_layout, groupArray);
                controller.setCurrentActivity(getActivity());

                listViewItems = new ListView(getActivity());
                listViewItems.setAdapter(adapter);
                clickListener.SetType(ClickListener.Listenertype.Group);
                listViewItems.setOnItemClickListener(clickListener);
                break;

            case SmsContent:
                SmsMessage[] data = new SmsMessage[controller.getSmsDetails().size()];
                controller.getSmsDetails().toArray(data);


                // our adapter instance
                SmsArrayAdapter sms_adapter = new SmsArrayAdapter(getActivity(), R.layout.listview_item_layout, data);
                controller.setCurrentActivity(getActivity());

                // create a new ListView, set the adapter and item click listener
                listViewItems = new ListView(getActivity());
                listViewItems.setAdapter(sms_adapter);
                clickListener = new ClickListener();
                clickListener.SetType(ClickListener.Listenertype.Address);
                clickListener.setArrayAdapter(sms_adapter);
                listViewItems.setItemsCanFocus(true);
                listViewItems.setOnItemClickListener(clickListener);

                //if (savedInstanceState == null) {
                //setContentView(listViewItems);
                break;

        }
        return listViewItems;
        //return inflater.inflate(R.id.content_main_fragment_sms, container);
    }
}
