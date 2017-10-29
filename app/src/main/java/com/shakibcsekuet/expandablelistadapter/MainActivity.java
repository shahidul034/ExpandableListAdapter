package com.shakibcsekuet.expandablelistadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MainActivity extends AppCompatActivity {

    private LinkedHashMap<String, GroupItemsInfo> songsList = new LinkedHashMap<String, GroupItemsInfo>();
    private ArrayList<GroupItemsInfo> deptList = new ArrayList<GroupItemsInfo>();

    private MyExpandableListAdapter myExpandableListAdapter;
    private ExpandableListView simpleExpandableListView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add data for displaying in expandable list view
        loadData();

        //get reference of the ExpandableListView
        simpleExpandableListView = (ExpandableListView) findViewById(R.id.simpleExpandableListView);
        // create the adapter by passing your ArrayList data
        myExpandableListAdapter = new MyExpandableListAdapter(MainActivity.this, deptList);
        // attach the adapter to the expandable list view
        simpleExpandableListView.setAdapter(myExpandableListAdapter);

        // setOnChildClickListener listener for child row click or song name click
        simpleExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //get the group header
                GroupItemsInfo headerInfo = deptList.get(groupPosition);
                //get the child info
                ChildItemsInfo detailInfo = headerInfo.getSongName().get(childPosition);
                //display it or do something with it
                Toast.makeText(getBaseContext(), " List And Song Is :: " + headerInfo.getName()
                        + "/" + detailInfo.getName(), Toast.LENGTH_LONG).show();
                return false;
            }
        });
        // setOnGroupClickListener listener for group Song List click
        simpleExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //get the group header
                GroupItemsInfo headerInfo = deptList.get(groupPosition);
                //display it or do something with it
                Toast.makeText(getBaseContext(), " List Name :: " + headerInfo.getName(),
                        Toast.LENGTH_LONG).show();

                return false;
            }
        });


    }

    // load some initial data into out list
    private void loadData() {

        addProduct("Latest Punjabi Songs", "Tere Bagair - Amrinder Gill");
        addProduct("Latest Punjabi Songs", "Khaab - Akhil");
        addProduct("Latest Punjabi Songs", "Dil - Ninja");

        addProduct("Top 50 Songs", "Tere Bagair- Amrinder Gill");
        addProduct("Top 50 Songs", "Attt Karti - Jassi Gill");

        addProduct("Top Of This Week", "Khaab- Akhil");
        addProduct("Top Of This Week", "Tere Bagair- Amrinder Gill");
        addProduct("Top Of This Week", "Gal Sun Ja - Kanwar Chahal");

    }


    // here we maintain songsList and songs names
    private int addProduct(String songsListName, String songName) {

        int groupPosition = 0;

        //check the hashmap if the group already exists
        GroupItemsInfo headerInfo = songsList.get(songsListName);
        //add the group if doesn't exists
        if (headerInfo == null) {
            headerInfo = new GroupItemsInfo();
            headerInfo.setName(songsListName);
            songsList.put(songsListName, headerInfo);
            deptList.add(headerInfo);
        }

        // get the children for the group
        ArrayList<ChildItemsInfo> productList = headerInfo.getSongName();
        // size of the children list
        int listSize = productList.size();
        // add to the counter
        listSize++;

        // create a new child and add that to the group
        ChildItemsInfo detailInfo = new ChildItemsInfo();
        detailInfo.setName(songName);
        productList.add(detailInfo);
        headerInfo.setPlayerName(productList);

        // find the group position inside the list
        groupPosition = deptList.indexOf(headerInfo);
        return groupPosition;
    }

}