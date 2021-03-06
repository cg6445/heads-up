/*
 * Copyright (C) 2011 The CyanogenMod Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package codes.simen.l50notifications.ui;

import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;

import codes.simen.l50notifications.R;
import codes.simen.l50notifications.util.Mlog;

public class OpacityPreference extends DialogPreference {
    private int mMin;
    private static final int mMax = 50;
    private final int mDefault = 98;

    private SeekBar mSeekBar;

    public OpacityPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected View onCreateDialogView() {
        int max = mMax;
        int value = getPersistedInt(mDefault) - 50;

        LayoutInflater inflater =
                (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.opacity_picker_dialog, null);

        mSeekBar = (SeekBar) view.findViewById(R.id.seekBar);

        // Initialize state
        mSeekBar.setMax(max);
        mSeekBar.setProgress(value);

        Mlog.d(String.valueOf(value), String.valueOf(max));

        return view;
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            persistInt(mSeekBar.getProgress() + 50);
        }
    }

}