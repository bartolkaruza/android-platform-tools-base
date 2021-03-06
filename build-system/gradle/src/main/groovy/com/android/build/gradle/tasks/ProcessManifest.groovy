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

package com.android.build.gradle.tasks

import com.android.builder.core.VariantConfiguration
import com.android.manifmerger.ManifestMerger2
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Optional

/**
 * a Task that only merge a single manifest with its overlays.
 */
class ProcessManifest extends ManifestProcessorTask {

    // ----- PUBLIC TASK API -----
    @Input @Optional
    String minSdkVersion

    @Input @Optional
    String targetSdkVersion

    @Input @Optional
    Integer maxSdkVersion

    VariantConfiguration variantConfiguration

    @InputFile
    File getMainManifest() {
        return variantConfiguration.getMainManifest();
    }

    @Input @Optional
    String getPackageOverride() {
        return variantConfiguration.getApplicationId();
    }

    @Input
    int getVersionCode() {
        variantConfiguration.getVersionCode();
    }

    @Input @Optional
    String getVersionName() {
        variantConfiguration.getVersionName();
    }

    @InputFiles
    List<File> getManifestOverlays() {
        return variantConfiguration.getManifestOverlays();
    }

    /**
     * Return a serializable version of our map of key value pairs for placeholder substitution.
     * This serialized form is only used by gradle to compare past and present tasks to determine
     * whether a task need to be re-run or not.
     */
    @Input @Optional
    String getManifestPlaceholders() {
        return serializeMap(variantConfiguration.getManifestPlaceholders());
    }

    @Override
    protected void doFullTaskAction() {

        getBuilder().mergeManifests(
                getMainManifest(),
                getManifestOverlays(),
                Collections.emptyList(),
                getPackageOverride(),
                getVersionCode(),
                getVersionName(),
                getMinSdkVersion(),
                getTargetSdkVersion(),
                getMaxSdkVersion(),
                getManifestOutputFile().absolutePath,
                ManifestMerger2.MergeType.LIBRARY,
                variantConfiguration.getManifestPlaceholders())
    }
}
