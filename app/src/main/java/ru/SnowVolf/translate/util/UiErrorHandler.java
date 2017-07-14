package ru.SnowVolf.translate.util;

import android.graphics.Color;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

import ru.SnowVolf.translate.App;

/**
 * Created by Snow Volf on 01.06.2017, 4:20
 */

public class UiErrorHandler {
    private static UiErrorHandler INSTANCE = null;

    public UiErrorHandler() {
        INSTANCE = this;
    }

    public static UiErrorHandler get() {
        if (INSTANCE == null) {
            new UiErrorHandler();
        }
        return INSTANCE;
    }

    public void handle(Exception ex, View snackBarAnchor) {
        Snackbar.make(snackBarAnchor, "Ошибка!!!", Snackbar.LENGTH_INDEFINITE).setAction("Детали", view ->
                new AlertDialog.Builder(App.ctx())
                        .setTitle("Детали")
                        .setMessage(ex.getMessage()+"\n***---***\n"+Arrays.toString(ex.getStackTrace()))
                        .setPositiveButton("Ok", null)
                        .create()
                        .show()).setActionTextColor(Color.WHITE).show();
        ex.printStackTrace();
        //create a file with a ex details
        try {
            ex.printStackTrace(new PrintWriter(new File(Environment.getExternalStorageDirectory().getPath() + "/Android/data/" + App.ctx().getPackageName() +"/files/report-" + System.currentTimeMillis() + ".txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void handle(Throwable throwable, View snackBarAnchor) {
        Snackbar.make(snackBarAnchor, "Ошибка!!!", Snackbar.LENGTH_INDEFINITE).setAction("Детали", view ->
                new AlertDialog.Builder(App.ctx())
                        .setTitle("Детали")
                        .setMessage(throwable.getMessage()+"\n***---***\n"+Arrays.toString(throwable.getStackTrace()))
                        .setPositiveButton("Ok", null)
                        .create()
                        .show()).setActionTextColor(Color.WHITE).show();
        throwable.printStackTrace();
        //create a file with a ex details
        try {
            throwable.printStackTrace(new PrintWriter(new File("/sdcard/Android/data/" + "ru.SnowVolf.translate/files/reports/report-" + System.currentTimeMillis() + ".txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
