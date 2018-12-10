package android.helloandroiders.com.smssorter;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by nilotpal on 16/04/17.
 */

public class GroupNames implements Serializable {

    GroupNames() {
        Read("groupNames");
    }
    private Map<String, MessageGroups> messageGroupList = new LinkedHashMap<>();

    public Map<String, MessageGroups> GetGroupNameMap() {
        return messageGroupList;
    }

    public boolean insertMessageGroup(String groupName) {
        if(messageGroupList.get(groupName)!=null) {
            return false;
        }
        MessageGroups messageGroup = new MessageGroups();
        messageGroup.setGroupName(groupName);
        messageGroup.insertAddress("Address1dummy");
        messageGroup.insertAddress("Address2dummy");
        messageGroupList.put(groupName, messageGroup);
        Write("groupNames");
        return true;
    }

    public Map<String, MessageGroups> getMessageGroupList() {
        return messageGroupList;
    }
    public void Read(String fileName) {
        try {
            File dataDir = new File(Environment.getExternalStorageDirectory(),"SmsSorter");
            if (! dataDir.exists()) {
                return;
            }
            ObjectInputStream input;
            input = new ObjectInputStream(new FileInputStream(new File(Environment.getExternalStorageDirectory(),"SmsSorter")+File.separator+fileName));
            GroupNames groupNames = (GroupNames)input.readObject();
            this.messageGroupList = groupNames.messageGroupList;
            input.close();

        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void Write(String fileName) {
        ObjectOutput out = null;

        try {
            File dataDir = new File(Environment.getExternalStorageDirectory(),"SmsSorter");
            if (! dataDir.exists()) {
                dataDir.mkdirs();
            }
            out = new ObjectOutputStream(new FileOutputStream(new File(Environment.getExternalStorageDirectory(),"SmsSorter")+File.separator+fileName));
            out.writeObject(this);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
