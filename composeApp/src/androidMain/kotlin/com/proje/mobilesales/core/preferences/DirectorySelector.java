package com.proje.mobilesales.core.preferences;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.FileObserver;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.view.ViewCompat;
import com.proje.mobilesales.core.utils.DirectoryFileFilter;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

abstract class DirectorySelector {
    private static final String TAG = "DirectorySelector";
    private ImageButton btnCreateFolder;
    private ImageButton btnNavUp;
    private final Callback callback;
    private FileObserver fileObserver;
    private FileAdapter listAdapter;
    private ListView listDirectories;
    private File selectedDir;
    private TextView txtSelectedFolder;
    private final Handler handler = new Handler();
    private final ArrayList<File> files = new ArrayList<>();
    private final AdapterView.OnItemClickListener listClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
            DirectorySelector directorySelector = DirectorySelector.this;
            directorySelector.changeDirectory(directorySelector.listAdapter.getItem(i2));
        }
    };
    private final View.OnClickListener clickListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (view.getId() == R.id.btn_nav_up) {
                DirectorySelector.this.changeUp();
            } else if (view.getId() == R.id.btn_create_folder) {
                DirectorySelector.this.callback.onNewDirButtonClicked();
            }
        }
    };
    public interface Callback {
        void onNewDirButtonClicked();
    }
    protected abstract Context getContext();
    protected abstract File getInitialDirectory();
    protected int getViewResId() {
        return R.layout.directory_chooser;
    }
    protected DirectorySelector(Callback callback) {
        this.callback = callback;
    }
    protected void onPause() {
        FileObserver fileObserver = this.fileObserver;
        if (fileObserver != null) {
            fileObserver.stopWatching();
        }
    }
    protected void onResume() {
        FileObserver fileObserver = this.fileObserver;
        if (fileObserver != null) {
            fileObserver.startWatching();
        }
    }
    protected File getSelectedDir() {
        return this.selectedDir;
    }
    protected void setSelectedDir(String str) {
        changeDirectory(new File(str));
    }
    protected boolean createFolder(String str) {
        File file = this.selectedDir;
        if (file == null) {
            showToast(R.string.str_no_directory_selected);
            return false;
        }
        if (!file.canWrite()) {
            showToast(R.string.str_no_write_access);
            return false;
        }
        File file2 = new File(this.selectedDir, str);
        if (file2.exists()) {
            showToast(R.string.str_error_already_exists);
            return false;
        }
        if (!file2.mkdir()) {
            showToast(R.string.str_create_folder_error);
            return false;
        }
        changeDirectory(new File(this.selectedDir, str));
        return true;
    }
    protected void initViews(View view) {
        this.btnNavUp = view.findViewById(R.id.btn_nav_up);
        this.btnCreateFolder = view.findViewById(R.id.btn_create_folder);
        this.txtSelectedFolder = view.findViewById(R.id.txt_selected_folder);
        ListView listView = view.findViewById(R.id.list_dirs);
        this.listDirectories = listView;
        listView.setEmptyView(view.findViewById(R.id.txt_list_empty));
        this.listDirectories.setOnItemClickListener(this.listClickListener);
        this.btnNavUp.setOnClickListener(this.clickListener);
        this.btnCreateFolder.setOnClickListener(this.clickListener);
        adjustImages();
        FileAdapter fileAdapter = new FileAdapter(getContext(), this.files);
        this.listAdapter = fileAdapter;
        this.listDirectories.setAdapter(fileAdapter);
        changeDirectory(getInitialDirectory());
    }
    private void adjustImages() {
        int i2;
        TypedArray obtainStyledAttributes;
        Resources.Theme theme = getContext().getTheme();
        if (theme == null || (obtainStyledAttributes = theme.obtainStyledAttributes(new int[]{R.attr.colorBackground})) == null) {
            i2 = 16777215;
        } else {
            i2 = obtainStyledAttributes.getColor(0, ViewCompat.MEASURED_SIZE_MASK);
            obtainStyledAttributes.recycle();
        }
        if (i2 == 16777215 || (Color.red(i2) * 0.21d) + (Color.green(i2) * 0.71d) + (Color.blue(i2) * 0.07d) >= 128.0d) {
            return;
        }
        this.btnNavUp.setImageResource(R.drawable.navigation_up_light);
        this.btnCreateFolder.setImageResource(R.drawable.ic_action_create_light);
    }
    public void changeUp() {
        File parentFile;
        File file = this.selectedDir;
        if (file == null || (parentFile = file.getParentFile()) == null) {
            return;
        }
        changeDirectory(parentFile);
    }
    public void changeDirectory(File file) {
        if (file == null || !file.isDirectory()) {
            return;
        }
        File[] listFiles = file.listFiles(new DirectoryFileFilter());
        Collection<? extends File> asList = listFiles != null ? Arrays.asList(listFiles) : new ArrayList<>();
        this.files.clear();
        this.files.addAll(asList);
        Collections.sort(this.files);
        FileAdapter fileAdapter = this.listAdapter;
        if (fileAdapter != null) {
            fileAdapter.notifyDataSetChanged();
        }
        this.selectedDir = file;
        this.txtSelectedFolder.setText(file.getAbsolutePath());
        FileObserver fileObserver = this.fileObserver;
        if (fileObserver != null) {
            fileObserver.stopWatching();
        }
        FileObserver createFileObserver = createFileObserver(file.getAbsolutePath());
        this.fileObserver = createFileObserver;
        createFileObserver.startWatching();
        Log.d(TAG, "Changed directory to " + file.getAbsolutePath());
    }
    public void refreshDirectory() {
        File file = this.selectedDir;
        if (file != null) {
            changeDirectory(file);
        }
    }
    @SuppressLint("WrongConstant")
    private FileObserver createFileObserver(String str) {
        return new FileObserver(str, 4032) {
            public void onEvent(int i2, String str2) {
                Log.d(DirectorySelector.TAG, "FileObserver received event " + i2);
                if ((i2 & 256) != 0 || (i2 & 512) != 0 || (i2 & 64) != 0 || (i2 & 128) != 0) {
                    DirectorySelector.this.handler.post(new Runnable() {
                        @Override // java.lang.Runnable
                        public void run() {
                            DirectorySelector.this.refreshDirectory();
                        }
                    });
                } else {
                    if ((i2 & 1024) == 0 && (i2 & 2048) == 0) {
                        return;
                    }
                    DirectorySelector.this.handler.post(new Runnable() { // from class: com.proje.mobilesales.core.preferences.DirectorySelector.3.2
                        @Override // java.lang.Runnable
                        public void run() {
                            DirectorySelector.this.changeUp();
                        }
                    });
                }
            }
        };
    }
    private void showToast(int i2) {
        Toast.makeText(getContext(), i2, Toast.LENGTH_LONG).show();
    }
}
