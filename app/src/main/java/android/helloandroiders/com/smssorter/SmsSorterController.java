package android.helloandroiders.com.smssorter;

import android.app.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nilotpal on 14/04/17.
 */

public class SmsSorterController {

    private SmsSorterController(){
        is_edit_enabled = false;
    }
    private Map<String,ArrayList<SmsMessage>> smsList = new HashMap();
    private ArrayList<SmsMessage> smsDetails = new ArrayList<SmsMessage>();
    private boolean[] checkbox_state;
    private static SmsSorterController ourInstance = new SmsSorterController();
    private Activity current_activity;
    private GroupNames groupNames = new GroupNames();
    private boolean is_edit_enabled;
    public final EventController eventController = new EventController();

    public void Initialize(Activity activity) {

        ReadAllMessage(activity);

    }

    private void ReadAllMessage(Activity activity) {

        SmsReader sms_reader = new SmsReader();
        sms_reader.ReadInbox(activity,smsList);
        for (String address : smsList.keySet()) {
            ArrayList<SmsMessage> each_list = smsList.get(address);
            smsDetails.add(each_list.get(each_list.size()-1));
        }
        checkbox_state = new boolean[smsDetails.size()];
    }

    public ArrayList<SmsMessage> getSmsDetails() {
        return smsDetails;
    }
    public ArrayList<SmsMessage> getSmsDetails(String address) {
        return smsList.get(address);
    }
    public void setCurrentActivity(Activity activity) {
        current_activity = activity;
    }
    public Activity getCurrentActivity() {
        return current_activity;
    }

    public static SmsSorterController getInstance() {
        return ourInstance;
    }
    public boolean addGroup(String groupName) {
        return groupNames.insertMessageGroup(groupName);
    }
    public GroupNames getGroupNames() {
        return groupNames;
    }
    public boolean getEditEnabledState() { return is_edit_enabled; }
    public void setEditEnabledForPage(final int pageNo) {
        if (pageNo == 1) {
            is_edit_enabled = ! is_edit_enabled;
            for (boolean value : checkbox_state) {
                value = false;
            }
        }
    }

    public void setCheckedState(final int position, boolean is_checked) {
        checkbox_state[position] = is_checked;

    }

    public boolean getCheckedState(final int position) {
        return checkbox_state[position];
    }
}

