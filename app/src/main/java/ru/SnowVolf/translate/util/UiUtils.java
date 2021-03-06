/*
 * Copyright (c) 2017 Snow Volf (Artem Zhiganov).
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.SnowVolf.translate.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.graphics.Palette;
import android.util.TypedValue;

import ru.SnowVolf.translate.R;

/**
 * Created by Snow Volf on 30.05.2017, 21:49
 */

public final class UiUtils {

    private UiUtils() {
    }

    public static boolean isColorLight(int color) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);

        float hsl[] = new float[3];
        ColorUtils.RGBToHSL(red, green, blue, hsl);
        return hsl[2] > 0.5f;
    }

    public static int getColor(Context context, Bitmap bitmap, boolean incognito) {
        Palette palette = Palette.from(bitmap).generate();
        int primary = ContextCompat.getColor(context, R.color.light_colorPrimary);
        int alternative = ContextCompat.getColor(context, R.color.md_grey_50);
        return incognito ?
                palette.getMutedColor(alternative) : palette.getVibrantColor(primary);
    }

    public static Bitmap getShortcutIcon(Context context, Bitmap bitmap) {
        Bitmap out = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(out);
        int color = getColor(context, bitmap, false);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getWidth());
        float radius = bitmap.getWidth() / 2;
        paint.setAntiAlias(true);
        paint.setColor(color);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(radius, radius, radius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return Bitmap.createScaledBitmap(out, 192, 192, true);
    }

    public static int getPositionInTime(long timeMilliSec) {
        long diff = System.currentTimeMillis() - timeMilliSec;

        long hour = 1000 * 60 * 60;
        long day = hour * 24;
        long week = day * 7;
        long month = day * 30;

        return hour > diff ? 0 : day > diff ? 1 : week > diff ? 2 : month > diff ? 3 : 4;
    }

    public static float dpToPx(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

}

