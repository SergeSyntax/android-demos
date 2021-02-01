package com.sergway.demolistfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import com.sergway.demolistfragment.data.Course;
import com.sergway.demolistfragment.data.CourseArrayAdapter;
import com.sergway.demolistfragment.data.CourseData;
import com.sergway.demolistfragment.util.ScreenUtility;

import java.util.List;

public class MyFragment extends ListFragment {
    List<Course> courses = new CourseData().courseList();

    public MyFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScreenUtility screenUtility = new ScreenUtility(getActivity());

        Log.d("Width", "onCreate: " + screenUtility.getDpWidth());
        Log.d("Height", "onCreate: " + screenUtility.getDpHeight());

        CourseArrayAdapter adapter = new CourseArrayAdapter(getActivity(), R.layout.cours_listitem, courses);
        setListAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_fragment, container, false);
    }
}
