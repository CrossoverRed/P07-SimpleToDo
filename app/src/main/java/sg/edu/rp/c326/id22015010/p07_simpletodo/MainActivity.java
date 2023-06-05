package sg.edu.rp.c326.id22015010.p07_simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spn;
    TextView tv;
    EditText et;
    Button btnAdd;
    Button btnRemove;
    Button btnClear;
    ListView lv;
    ArrayList<String> taskList;
    ArrayAdapter<String> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.buttonAddItem);
        btnRemove = findViewById(R.id.buttonRemoveItem);
        btnClear = findViewById(R.id.buttonClear);
        spn = findViewById(R.id.spinner);
        tv = findViewById(R.id.tvDisplay);
        et = findViewById(R.id.editText1);
        lv = findViewById(R.id.listView);

        taskList = new ArrayList<>();
        aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        lv.setAdapter(aa);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = spn.getSelectedItem().toString();
                Toast.makeText(MainActivity.this, selected, Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
                        btnAdd.setEnabled(true);
                        btnRemove.setEnabled(false);
                        et.setHint("Type in a new task here...");
                        tv.setText("Spinner Item, Add Item Task Selected");
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnRemove.setEnabled(true);
                        et.setHint("Type in the index of the task to be removed...");
                        tv.setText("Spinner Item, Remove Item Task Selected");
                        break;

                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = et.getText().toString();
                if (!task.isEmpty()) {
                    taskList.add(task);
                    aa.notifyDataSetChanged();
                    et.setText("");
                }
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (taskList.isEmpty()) {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                } else {
                    int pos = Integer.parseInt(MainActivity.this.et.getText().toString());
                    if (pos < 0 || pos >= taskList.size()) {
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                    } else {
                        taskList.remove(pos);
                        aa.notifyDataSetChanged();
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskList.clear();
                aa.notifyDataSetChanged();
            }
        });

    }
}