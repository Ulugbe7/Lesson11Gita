package com.example.lesson11gita;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView add;
    ListView listView;
    MyAdapter adapter;
    DbHelper dbHelper;
    List<Data> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_main);

        init();
        updateListView();
    }

    private void init() {
        dbHelper = new DbHelper(this);
        findViewById(R.id.btnAdd).setOnClickListener(this::addData);
        listView = findViewById(R.id.listView);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, id + " " + position, Toast.LENGTH_SHORT).show();

                Data data = (Data) parent.getItemAtPosition(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Datani o'chirmoqchimisiz");
                builder.setIcon(R.drawable.ic_cancel_black);
                builder.setCancelable(false);

                builder.setPositiveButton("ha", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.deleteOne(data);
                        updateListView();
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("yoq", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.create();
                builder.show();

            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Data data = (Data) parent.getItemAtPosition(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());


                View view1 = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_name, null);
                builder.setView(view1);


                builder.setTitle("Datani yangilash");
                builder.setCancelable(false);

                EditText editName = view1.findViewById(R.id.inputName);
                EditText editPhone = view1.findViewById(R.id.inputPhone);

                editName.setText(data.getName());
                Log.wtf("name", "" + data.getName());
                editPhone.setText(data.getPhone());
                Log.wtf("phone", "" + data.getPhone());

                builder.setPositiveButton("Ha", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = editName.getText().toString();
                        String phone = editPhone.getText().toString();

                        if (!(name.length() == 0 && phone.length() == 0)) {
                            dbHelper.update(new Data(data.getId(), name, phone));
                            updateListView();
                        } else {
                            showMessage("Ism yoki raqam kiritilmadi?");
                        }
                    }
                });

                builder.setNegativeButton("Yo'q", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

//                builder.create();
                builder.show();

                return true;
            }
        });
    }


    public void addData(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("InputDialog");
        builder.setCancelable(false);
        View v = LayoutInflater.from(this).inflate(R.layout.dialog_name, null);
        builder.setView(v);

        final EditText name = (EditText) v.findViewById(R.id.inputName);
        final EditText phone = (EditText) v.findViewById(R.id.inputPhone);

        builder.setPositiveButton("Saqlash", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String mName = name.getText().toString();
                String mPhone = phone.getText().toString();

                if (mName.length() != 0) {
                    if (mPhone.length() != 0) {
                        long res = dbHelper.addData(mName, mPhone);
                        if (res != -1) {
                        } else {
                            showMessage("data added successfully");
                        }
                    } else {
                        showMessage("Raqam kiriting");
                    }
                } else {
                    showMessage("ism kiriitng");
                }
                updateListView();
            }
        });

        builder.setNegativeButton("Bekor qilish", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create();
        builder.show();
    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    void updateListView() {
        list = new ArrayList<>(dbHelper.getData());
        adapter = new MyAdapter(this, list);
        listView.setAdapter(adapter);
    }


//    void updateData(int position) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//
//        builder.setTitle("Update Dialog");
//        builder.setCancelable(false);
//        View v = LayoutInflater.from(this).inflate(R.layout.dialog_name, null);
//        builder.setView(v);
//
//        final EditText name = (EditText) v.findViewById(R.id.inputName);
//        final EditText phone = (EditText) v.findViewById(R.id.inputPhone);
//
//        Data data = list.get(position);
//
//        name.setText(data.getName());
//        phone.setText(data.getPhone());
//
//
////        builder.setPositiveButton("Yangilash", new DialogInterface.OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialog, int which) {
////                String mName = name.getText().toString();
////                String mPhone = phone.getText().toString();
////
////                if (mName.length() != 0) {
////                    if (mPhone.length() != 0) {
////                        boolean res = dbHelper.update(mName, mPhone);
////                        if (res) {
////                            updateListView();
////                            showMessage("data added successfully");
////                        } else {
////                            showMessage("error");
////                        }
////                    } else {
////                        showMessage("Raqam kiriting");
////                    }
////                } else {
////                    showMessage("ism kiriitng");
////                }
////                updateListView();
////            }
////        });
////
////        builder.setNegativeButton("Bekor qilish", new DialogInterface.OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialog, int which) {
////                dialog.dismiss();
////            }
////        });
////
////        builder.create();
////        builder.show();
////
////    }
//    }
}