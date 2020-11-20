package com.example.livedata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.livedata.databinding.FragmentPoderBinding;

public class PoderFragment extends Fragment {
    private FragmentPoderBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentPoderBinding.inflate(inflater, container, false)).getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PoderViewModel poderViewModel = new ViewModelProvider(this).get(PoderViewModel.class);

        poderViewModel.obtenerPoder().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer poder) {
                Glide.with(PoderFragment.this).load(poder).into(binding.poder);
            }
        });

    }
}

