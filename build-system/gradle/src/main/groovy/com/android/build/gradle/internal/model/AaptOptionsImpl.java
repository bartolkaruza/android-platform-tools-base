/*
 * Copyright (C) 2014 The Android Open Source Project
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

package com.android.build.gradle.internal.model;

import java.io.Serializable;
import java.util.Collection;

import com.android.annotations.NonNull;
import com.android.annotations.Nullable;
import com.android.builder.model.AaptOptions;
import com.google.common.collect.ImmutableList;

/**
 * Implementation of AaptOptions that is Serializable.
 */
public class AaptOptionsImpl implements AaptOptions, Serializable {

    private static final long serialVersionUID = 1L;

    @Nullable
    private String ignoreAssets;

    @Nullable
    private Collection<String> noCompress;

    private boolean failOnMissingConfigEntry;


    static AaptOptions create(@NonNull AaptOptions aaptOptions) {
        return new AaptOptionsImpl(
                aaptOptions.getIgnoreAssets(),
                aaptOptions.getNoCompress(),
                aaptOptions.getFailOnMissingConfigEntry()
        );
    }

    private AaptOptionsImpl(String ignoreAssets, Collection<String> noCompress,
            boolean failOnMissingConfigEntry) {
        this.ignoreAssets = ignoreAssets;
        if (noCompress == null) {
            this.noCompress = null;
        } else {
            this.noCompress = ImmutableList.copyOf(noCompress);
        }
        this.failOnMissingConfigEntry = failOnMissingConfigEntry;
    }

    @Override

    public String getIgnoreAssets() {
        return ignoreAssets;
    }

    @Override
    public Collection<String> getNoCompress() {
        return noCompress;
    }

    @Override
    public boolean getFailOnMissingConfigEntry() {
        return failOnMissingConfigEntry;
    }


    public String toString() {
        return "AaptOptionsImpl{" +
                ", ignoreAssets=" + ignoreAssets +
                ", noCompress=" + noCompress +
                ", failOnMissingConfigEntry=" + failOnMissingConfigEntry +
                "}";
    }
}
