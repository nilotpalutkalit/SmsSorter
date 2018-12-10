package android.helloandroiders.com.smssorter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddGroup extends AppCompatActivity {
    String []array_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
/*        // Here come all the options that you wish to show depending on the
        // size of the array.
        array_spinner=new String[5];
        array_spinner[0]="option 1";
        array_spinner[1]="option 2";
        array_spinner[2]="option 3";
        array_spinner[3]="option 4";
        array_spinner[4]="option 5";
        Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_spinner);
        s.setAdapter(adapter);*/

        final Button addButton = (Button) findViewById(R.id.addGroupButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText groupNameEdit = (EditText) findViewById(R.id.groupNameEdit);
                String groupName = groupNameEdit.getText().toString();
                SmsSorterController.getInstance().eventController.OnAddGroup(groupName);
                finish();
            }
        });
    }
}
