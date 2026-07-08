package com.proje.mobilesales.features.about.view.fragment;

import android.os.Bundle;c
import androidx.preference.PreferenceFragmentCompat;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.about.repository.AboutRepository;
import com.proje.mobilesales.features.about.viewmodel.AboutViewModel;

public final class AboutFragment extends PreferenceFragmentCompat {
    private final AboutRepository repository;
    private final AboutViewModel viewModel;
    public AboutFragment() {
        AboutRepository aboutRepository = new AboutRepository();
        this.repository = aboutRepository;
        this.viewModel = new AboutViewModel(aboutRepository);
    }
    public AboutRepository getRepository() {
        return this.repository;
    }
    public AboutViewModel getViewModel() {
        return this.viewModel;
    }
    public void onCreatePreferences(Bundle bundle, String str) {
        addPreferencesFromResource(R.xml.preference_about);
        this.viewModel.bindPreferences(this);
    }
}
