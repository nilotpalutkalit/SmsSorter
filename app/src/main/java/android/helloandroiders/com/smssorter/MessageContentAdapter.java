package android.helloandroiders.com.smssorter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by nilotpal on 15/04/17.
 */

public class MessageContentAdapter extends ArrayAdapter<SmsMessage> {
    Context mContext;
    int layoutResourceId;
    SmsMessage data[] = null;

    public MessageContentAdapter(Context context, int layoutResourceId, SmsMessage[] data) {
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

        SmsMessage item = data[position];
        TextView dateName = (TextView) convertView.findViewById(R.id.dateText);
        //transactionName.setText(item.getTransactionProviderName() + "   " + item.transactionValue + "   " + item.transactionDate + "    " + item.transactionName);
        dateName.setText(item.date.toString());
        dateName.setTag(item.date);

        TextView messageName = (TextView) convertView.findViewById(R.id.messageText);
        //String smsDate = new SimpleDateFormat("dd MMM yyyy").format(item.date);
        messageName.setText(item.message);
        messageName.setTag(item.date);

        return convertView;
    }

}


