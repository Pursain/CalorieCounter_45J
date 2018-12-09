import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.harrison.caloriecounter.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DateListAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private List<String> foodList = new ArrayList<String>();

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