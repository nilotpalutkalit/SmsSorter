package android.helloandroiders.com.smssorter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nilotpal on 16/04/17.
 */

public class MessageGroups implements Serializable {

    private String groupName = "";
    private Map<String, String> addressList = new HashMap<String, String>();

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void insertAddress(String address) {
        addressList.put(address, address);
    }

    public String getGroupName() {
        return groupName;
    }

    public Map<String, String> getAddressList() {
        return addressList;
    }
}
