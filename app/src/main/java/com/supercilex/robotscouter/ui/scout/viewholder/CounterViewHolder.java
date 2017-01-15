package com.supercilex.robotscouter.ui.scout.viewholder;

import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.Query;
import com.supercilex.robotscouter.R;
import com.supercilex.robotscouter.data.model.ScoutMetric;

public class CounterViewHolder extends ScoutViewHolderBase<Integer, TextView> implements View.OnClickListener {
    private ImageButton mIncrement;
    private TextView mCount;
    private ImageButton mDecrement;

    public CounterViewHolder(View itemView) {
        super(itemView);
        mIncrement = (ImageButton) itemView.findViewById(R.id.increment_counter);
        mCount = (TextView) itemView.findViewById(R.id.count);
        mDecrement = (ImageButton) itemView.findViewById(R.id.decrement_counter);
    }

    @Override
    public void bind(ScoutMetric<Integer> metric, Query query, SimpleItemAnimator animator) {
        super.bind(metric, query, animator);
        mCount.setText(String.valueOf(mMetric.getValue()));
        mIncrement.setOnClickListener(this);
        mDecrement.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.increment_counter) {
            int value = Integer.parseInt(mCount.getText().toString()) + 1;
            mCount.setText(String.valueOf(value));
            updateMetricValue(value);
        } else if (id == R.id.decrement_counter
                && Integer.parseInt(mCount.getText().toString()) > 0) { // no negative values
            int value = Integer.parseInt(mCount.getText().toString()) - 1;
            mCount.setText(String.valueOf(value));
            updateMetricValue(value);
        }
    }
}
