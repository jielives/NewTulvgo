package com.yg.tulvgo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yg.common.base.BaseFootview_Adapter;
import com.yg.tulvgo.R;
import com.yg.tulvgo.bean.TicketBean;

/**
 * =====================
 * Author: shenjie
 * Date: 17-3
 * Time: 下午14:17
 * describe:儿童票(数据适配器)
 * =====================
 */

public class FoldingTicketAdapter extends BaseFootview_Adapter<TicketBean> {
    private ListItemView listItemView = null;
//    ScenicSpotDetailPageActivity scenicSpotDetailPageActivity;
    public FoldingTicketAdapter(Context mContext, ListView mListView) {
        super(mContext, mListView);
//        this.scenicSpotDetailPageActivity = (ScenicSpotDetailPageActivity) mContext;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        final StationImage_Children item = (StationImage_Children)getItem(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_ticket_layout, parent, false);
            listItemView = new ListItemView();
            creatView(convertView, listItemView);
            convertView.setTag(listItemView);
        } else {
            listItemView = (ListItemView) convertView.getTag();
        }
//        listItemView.gpn_name.setText(item.getImg_name());
//        listItemView.gpn_price.setText("￥" + item.getImg_url_l());
//        listItemView.gpn_old_price.setText("￥" + item.getImg_url_s());
//        listItemView.gpn_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
//        listItemView.predetermine.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(scenicSpotDetailPageActivity, FillOrderActivity.class);
//                scenicSpotDetailPageActivity.startActivity(i);
//            }
//        });
        return convertView;
    }

    public final class ListItemView {
        public LinearLayout gas_station_groupon_ll;
        public TextView gpn_name;
        public TextView gpn_price;
        public TextView gpn_old_price;
        public TextView predetermine;
    }
    private void creatView(View rowView, ListItemView listItemView) {
//        listItemView.gas_station_groupon_ll = (LinearLayout) rowView.findViewById(R.id.gas_station_groupon_ll);
//        listItemView.gpn_name = (TextView) rowView.findViewById(R.id.gpn_name);
//        listItemView.gpn_price = (TextView) rowView.findViewById(R.id.gpn_price);
//        listItemView.gpn_old_price = (TextView) rowView.findViewById(R.id.gpn_old_price);
//        listItemView.predetermine = (TextView) rowView.findViewById(R.id.predetermine);
//
    }
}
