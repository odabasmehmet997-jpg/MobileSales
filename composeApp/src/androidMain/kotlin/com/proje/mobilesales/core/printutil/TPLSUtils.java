package com.proje.mobilesales.core.printutil;

import com.proje.mobilesales.core.utils.BluetoothUtil;

public class TPLSUtils {
    public static void SendCommand(String str, boolean z) {
        BluetoothUtil.getBluetoothService(z).write(str.getBytes());
    }
    public static void PrintLabel(int i2, int i3, boolean z) {
        BluetoothUtil.getBluetoothService(z).write(("PRINT " + i2 + ", " + i3 + "\r\n").getBytes());
    }
    public static String PrinterFontStr(int i2, int i3, String str, int i4, String str2, String str3, String str4) {
        return "TEXT " + (i2 + "," + i3) + " ," + ("\"" + str + "\"") + " ," + ("" + i4) + " ," + (str2) + " ," + (str3) + " ," + ("\"" + str4 + "\"") + "\r\n";
    }
}
