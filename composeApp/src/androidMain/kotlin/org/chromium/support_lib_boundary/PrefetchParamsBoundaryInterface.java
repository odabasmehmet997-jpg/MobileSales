package org.chromium.support_lib_boundary;

import androidx.annotation.Nullable;
import java.util.Map;


public interface PrefetchParamsBoundaryInterface {

    Map<String, String> getAdditionalHeaders();
    String getNoVarySearchHint();
}
