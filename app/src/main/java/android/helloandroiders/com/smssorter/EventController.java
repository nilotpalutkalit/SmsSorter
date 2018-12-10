package android.helloandroiders.com.smssorter;

/**
 * Created by nilotpal on 20/04/17.
 */

public class EventController {
    public void OnAddGroup(String groupName) {
        SmsSorterController.getInstance().addGroup(groupName);
    }
}
