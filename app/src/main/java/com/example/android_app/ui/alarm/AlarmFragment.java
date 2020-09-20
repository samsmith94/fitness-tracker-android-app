package com.example.android_app.ui.alarm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.android_app.R;

public class AlarmFragment extends Fragment {

    private AlarmViewModel alarmViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        alarmViewModel =
                ViewModelProviders.of(this).get(AlarmViewModel.class);
        View root = inflater.inflate(R.layout.fragment_alarm, container, false);

        TimePicker picker=(TimePicker)root.findViewById(R.id.timePicker);
        picker.setIs24HourView(true);

        final TextView textView = root.findViewById(R.id.text_alarm);
        alarmViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}