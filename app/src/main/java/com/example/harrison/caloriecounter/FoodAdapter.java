package com.example.harrison.caloriecounter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/*
    This adapter has been modified to accept hashmap input from the firebase...
    We don't have time to make our own Hashmap implementation so wemma use Herman's and
    tack on a hashmap
 */
public class FoodAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private List<String> foodList = new ArrayList<String>();
    private HashMap<String, Integer> hmData = new HashMap<String, Integer>();

    public FoodAdapter( Context context, ArrayList<String> list, HashMap<String, Integer> hmData)
    {
        super( context, 0, list);
        mContext = context;
        foodList = list;
        this.hmData = hmData;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        // Associates the list with the XML Layout file "food_listview"
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.food_listview,parent,false);

        String currentContact = foodList.get(position);

        // Extracts the name of the Food
        TextView name = (TextView) listItem.findViewById(R.id.text_view_item_name);
        name.setText(currentContact);

        // Extracts the Calorie of the Food
        TextView number = (TextView) listItem.findViewById(R.id.text_view_item_description);
        number.setText(Integer.toString(hmData.get(currentContact)));

        return listItem;
    }
}