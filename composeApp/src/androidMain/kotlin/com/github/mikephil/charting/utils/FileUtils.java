package com.github.mikephil.charting.utils;

import android.content.res.AssetManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public enum FileUtils {
    ;

    public static List<Entry> loadEntriesFromFile(final String str) {
        final File file = new File (Environment.getExternalStorageDirectory (), str);
        final List<Entry> arrayList = new ArrayList<> ();
        try {
            BufferedReader bufferedReader = null;
            if (Build.VERSION_CODES.TIRAMISU <= Build.VERSION.SDK_INT) {
                bufferedReader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    bufferedReader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
                }
            }
            while (true) {
                final String readLine = bufferedReader.readLine ();
                if (null == readLine) {
                    break;
                }
                final String[] split = readLine.split ("#");
                if (2 > split.length) {
                    continue;
                }
                if (2 >= split.length) {
                    arrayList.add (new Entry (Float.parseFloat (split[0]), Integer.parseInt (split[1])));
                } else {
                    final int length = split.length - 1;
                    final float[] fArr = new float[length];
                    for (int i = 0; i < length; i++) {
                        fArr[i] = Float.parseFloat (split[i]);
                    }
                    arrayList.add (new BarEntry (fArr, Integer.parseInt (split[length])));
                }
            }
        } catch (final IOException e) {
            Log.e ("MPChart-FileUtils", e.toString ());
        }
        return arrayList;
    }

    public static List<Entry> loadEntriesFromAssets(final AssetManager assetManager, final String str) {
        final List<Entry> arrayList;
        final String str2;
        BufferedReader bufferedReader;
        Throwable th;
        IOException e;
        BufferedReader bufferedReader2 = null;
        str2 = "MPChart-FileUtils";
        arrayList = new ArrayList<> ();
        bufferedReader = null;
        try {
            try {
                bufferedReader2 = new BufferedReader (new InputStreamReader (assetManager.open (str), StandardCharsets.UTF_8));
            } catch (final IOException e2) {
                e = e2;
            }
        } catch (final Throwable th2) {
            th = th2;
        }
        try {
            for (String readLine = bufferedReader2.readLine (); null != readLine; readLine = bufferedReader2.readLine ()) {
                final String[] split = readLine.split ("#");
                if (2 >= split.length) {
                    arrayList.add (new Entry (Float.parseFloat (split[0]), Integer.parseInt (split[1])));
                } else {
                    final int length = split.length - 1;
                    final float[] fArr = new float[length];
                    for (int i = 0; i < length; i++) {
                        fArr[i] = Float.parseFloat (split[i]);
                    }
                    arrayList.add (new BarEntry (fArr, Integer.parseInt (split[length])));
                }
            }
            bufferedReader2.close ();
        } catch (final IOException e4) {
            e = e4;
            bufferedReader = bufferedReader2;
            Log.e (str2, e.toString ());
            if (null != bufferedReader) {
                try {
                    bufferedReader.close ();
                } catch (final IOException ex) {
                    throw new RuntimeException (ex);
                }
            }
            return arrayList;
        } catch (final Throwable th3) {
            th = th3;
            bufferedReader = bufferedReader2;
            if (null != bufferedReader) {
                try {
                    bufferedReader.close ();
                } catch (final IOException e5) {
                    Log.e (str2, e5.toString ());
                }
            }
            try {
                throw th;
            } catch (final Throwable ex) {
                throw new RuntimeException (ex);
            }
        }
        return arrayList;
    }

    public static void saveToSdCard(final List<Entry> list, final String str) {
        final File file = new File (Environment.getExternalStorageDirectory (), str);
        if (!file.exists ()) {
            try {
                file.createNewFile ();
            } catch (final IOException e) {
                Log.e ("MPChart-FileUtils", e.toString ());
            }
        }
        try {
            final BufferedWriter bufferedWriter = new BufferedWriter (new FileWriter (file, true));
            for (final Entry entry : list) {
                bufferedWriter.append ((CharSequence) (entry.getVal () + "#" + entry.getXIndex ()));
                bufferedWriter.newLine ();
            }
            bufferedWriter.close ();
        } catch (final IOException e2) {
            Log.e ("MPChart-FileUtils", e2.toString ());
        }
    }

    public static List<BarEntry> loadBarEntriesFromAssets(final AssetManager assetManager, final String str) {
        Throwable th;
        BufferedReader bufferedReader = null;
        String str2 = "";
        ArrayList arrayList = null;
        BufferedReader bufferedReader2;
        IOException e;
        BufferedReader bufferedReader3 = null;
        float bufferedReader4 = Float.parseFloat (null);
        try {
            str2 = "MPChart-FileUtils";
            arrayList = new ArrayList ();
            bufferedReader2 = null;
            bufferedReader4 = Float.parseFloat (null);
            try {
                bufferedReader3 = new BufferedReader (new InputStreamReader (assetManager.open (str), StandardCharsets.UTF_8));
            } catch (final IOException e2) {
                e = e2;
            }
        } catch (final Throwable th2) {
            th = th2;
        }
        try {
            String readLine = bufferedReader3.readLine ();
            while (null != readLine) {
                final String[] split = readLine.split ("#");
                final float parseFloat = Float.parseFloat (split[0]);
                arrayList.add (new BarEntry (parseFloat, Integer.parseInt (split[1])));
                readLine = bufferedReader3.readLine ();
                bufferedReader4 = parseFloat;
            }
            bufferedReader3.close ();
        } catch (final IOException e4) {
            e = e4;
            bufferedReader2 = bufferedReader3;
            Log.e (str2, e.toString ());
            bufferedReader = bufferedReader2;
            if (null != bufferedReader2) {
                try {
                    bufferedReader2.close ();
                } catch (final IOException ex) {
                    throw new RuntimeException (ex);
                }
                bufferedReader = bufferedReader2;
            }
            return arrayList;
        } catch (final Throwable th3) {
            th = th3;
            bufferedReader = bufferedReader3;
            if (null != bufferedReader) {
                try {
                    bufferedReader.close ();
                } catch (final IOException e5) {
                    Log.e (str2, e5.toString ());
                }
            }
            try {
                throw th;
            } catch (final Throwable ex) {
                throw new RuntimeException (ex);
            }
        }
        return arrayList;
    }
}
