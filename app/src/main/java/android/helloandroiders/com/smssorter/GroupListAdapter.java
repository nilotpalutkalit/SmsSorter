package android.helloandroiders.com.smssorter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by nilotpal on 22/04/17.
 */

public class GroupListAdapter extends ArrayAdapter<MessageGroups> {
    Context mContext;
    int layoutResourceId;
    MessageGroups data[] = null;

    public GroupListAdapter(Context context, int layoutResourceId, MessageGroups[] data) {
        super(context, layoutResourceId, data);

        this.layoutResourceId   =   layoutResourceId;
        mContext                =   context;
        this.data               =   data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        String item = data[position].getGroupName();

        TextView groupName = (TextView) convertView.findViewById(R.id.groupName);
        //transactionName.setText(item.getTransactionProviderName() + "   " + item.transactionValue + "   " + item.transactionDate + "    " + item.transactionName);
        groupName.setText(item);
        groupName.setTag(item);

        return convertView;
    }

}


