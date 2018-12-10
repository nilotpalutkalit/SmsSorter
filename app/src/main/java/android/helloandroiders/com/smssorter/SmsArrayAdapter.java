package android.helloandroiders.com.smssorter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
 * Created by nilotpal on 14/04/17.
 */

public class SmsArrayAdapter extends ArrayAdapter<SmsMessage> {
    Context mContext;
    int layoutResourceId;
    SmsMessage data[] = null;

    public SmsArrayAdapter(Context context, int layoutResourceId, SmsMessage[] data) {
        super(context, layoutResourceId, data);

        this.layoutResourceId   =   layoutResourceId;
        mContext                =   context;
        this.data               =   data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        SmsMessage item = data[position];

        TextView addressName = (TextView) convertView.findViewById(R.id.addressName);
        //transactionName.setText(item.getTransactionProviderName() + "   " + item.transactionValue + "   " + item.transactionDate + "    " + item.transactionName);
        addressName.setText(item.address);
        addressName.setTag(item.date);

//        TextView transactionAmount = (TextView) convertView.findViewById(R.id.transactionAmount);
//        transactionAmount.setText("" + item.transactionValue);
//        transactionAmount.setTag(item.transactionProviderType);

        TextView messageName = (TextView) convertView.findViewById(R.id.messageLine);
        //String smsDate = new SimpleDateFormat("dd MMM yyyy").format(item.date);
        messageName.setText(item.message);
        messageName.setTag(item.date);

        ImageView transactionIcon = (ImageView) convertView.findViewById(R.id.transactionIcon);

        final SmsSorterController controller = SmsSorterController.getInstance();

        //SetImage(transactionIcon,item.transactionProviderType);
        if (controller.getEditEnabledState() == true) {
            CheckBox checkbox = (CheckBox)convertView.findViewById(R.id.checkbox_message);
            checkbox.setVisibility(View.VISIBLE);
            checkbox.setTag(item.date);
            checkbox.setChecked(controller.getCheckedState(position));
            checkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    controller.setCheckedState(position, ! controller.getCheckedState(position));
                }
            });
        } else {
            View checkbox = convertView.findViewById(R.id.checkbox_message);
            checkbox.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }

//    private void SetImage(ImageView view, String transactionProviderType) {
//
//        if(transactionProviderType.compareTo(TransactionProviderType.ICICI)==0)
//            view.setImageResource(R.drawable.icici_icon);
//
//        else if(transactionProviderType.compareTo(TransactionProviderType.HDFC)==0)
//            view.setImageResource(R.drawable.hdfc_icon);
//
//        else if(transactionProviderType.compareTo(TransactionProviderType.Default)==0)
//            view.setImageResource(R.drawable.other_transaction_icon);
//
//    }

}

