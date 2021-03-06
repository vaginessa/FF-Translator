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

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import ru.SnowVolf.translate.preferences.Preferences;

/**
 * Created by Snow Volf on 13.07.2017, 18:54
 */

public class FragmentUtil {
    private static FragmentUtil baseContext = new FragmentUtil();

    private FragmentUtil(){
        baseContext = this;
    }

    public static FragmentUtil ctx(){
        return baseContext;
    }

    public void iterateStack(Activity activity, int containerId, Fragment fragment){
        FragmentManager fragmentManager = activity.getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerId, fragment);
        if (Preferences.isTransitionsAllowed()){
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        }
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void iterate(Activity activity, int containerId, Fragment fragment){
        FragmentManager fragmentManager = activity.getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerId, fragment);
        if (Preferences.isTransitionsAllowed()){
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        }
        transaction.commit();
    }
}
