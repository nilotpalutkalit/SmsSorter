package android.helloandroiders.com.smssorter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by nilotpal on 15/04/17.
 */

public class ClickListener implements AdapterView.OnItemClickListener {

    public enum Listenertype {
        Address,
        Group,
        Message
    }

    private Listenertype clickListenerType;
    private SmsArrayAdapter arrayAdapter;

    public void SetType(Listenertype type) {
        clickListenerType = type;
    }
    public void setArrayAdapter(SmsArrayAdapter arrayAdapter) {
        this.arrayAdapter = arrayAdapter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch(clickListenerType) {
            case Address:
                onAddressClicked(parent, view, position, id);
                break;
            case Group:
                onGroupClicked(parent, view, position, id);
                break;
            case Message:
                onMessageClicked(parent, view, position, id);
                break;
        }

    }

    public void onGroupClicked(AdapterView<?> parent, View view, int position, long id) {

        Context context = view.getContext();

        TextView textViewItem = ((TextView) view.findViewById(R.id.groupName));
        String groupName = textViewItem.getText().toString();
        String listItemId = textViewItem.getTag().toString();
        //MessageGroups group = SmsSorterController.getInstance().getGroupNames().GetGroupNameMap().get(groupName);

        //Toast.makeText(context, "Item: " + listItemText + ", Item ID: " + listItemId, Toast.LENGTH_SHORT).show();
        //Toast.makeText(context, message , Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(SmsSorterController.getInstance().getCurrentActivity(), MessageGroupActivity.class);
        myIntent.putExtra("key", groupName); //Optional parameters
        SmsSorterController.getInstance().getCurrentActivity().startActivity(myIntent);

    }

    public void onAddressClicked(AdapterView<?> parent, View view, int position, long id) {

        SmsSorterController controller = SmsSorterController.getInstance();
        if (controller.getEditEnabledState()) {
            controller.setCheckedState(position, ! controller.getCheckedState(position));
            if (arrayAdapter !=null) {
                arrayAdapter.notifyDataSetChanged();
            }
            return;
        }
        Context context = view.getContext();

        TextView textViewItem = ((TextView) view.findViewById(R.id.addressName));
        String listItemText = textViewItem.getText().toString();
        String listItemId = textViewItem.getTag().toString();
        String message = SmsSorterController.getInstance().getSmsDetails().get(position).address;

        //Toast.makeText(context, "Item: " + listItemText + ", Item ID: " + listItemId, Toast.LENGTH_SHORT).show();
        //Toast.makeText(context, message , Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(SmsSorterController.getInstance().getCurrentActivity(), MessageDetailActivity.class);
        myIntent.putExtra("key", SmsSorterController.getInstance().getSmsDetails().get(position).address); //Optional parameters
        SmsSorterController.getInstance().getCurrentActivity().startActivity(myIntent);
    }

    public void onMessageClicked(AdapterView<?> parent, View view, int position, long id) {

    }

    public void onEditAction(final int position) {
        if (position == 1) {
            SmsSorterController.getInstance().setEditEnabledForPage(position);
        }
    }
}
