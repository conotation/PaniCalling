package kr.connotation.fiermemory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Connotation on 2016-03-26.
 */
public class PanicAdapter extends ArrayAdapter<Panic> {
    private Context mContext;
    private LayoutInflater mInflater;

    public PanicAdapter(Context context, List<Panic> object) {
        super(context, 0, object);
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        View view = null;
        if (v == null) {
            view = mInflater.inflate(R.layout.panic_item, null);
        } else {
            view = v;
        }

        final Panic data = this.getItem(position);
        if (data != null) {
            TextView item_up = (TextView) view.findViewById(R.id.item_u_text);
            TextView item_down = (TextView) view.findViewById(R.id.item_d_text);
            ImageView item_group = (ImageView) view.findViewById(R.id.item_group);

            switch (data.mgroup) {
                case "기타":
                    item_group.setBackgroundResource(R.drawable.guitar_60);
                    break;
                case "사람":
                    item_group.setBackgroundResource(R.drawable.human_60);
                    break;
                case "동물":
                    item_group.setBackgroundResource(R.drawable.animals_60);
                    break;
                case "사물":
                    item_group.setBackgroundResource(R.drawable.object_60);
                    break;
                case "장소":
                    item_group.setBackgroundResource(R.drawable.place_60);
                    break;
            }
            item_up.setText(data.getTitle());
            item_down.setText(data.getMemo());
        }
        return view;
    }


}
