package com.baeldung;

import java.io.File;
import java.net.URL;
import java.util.Optional;

public final class Resources {

    private Resources() {
    }

    public static File resourceFile(String fileName) {
        return Optional.ofNullable(Resources.class.getResource(fileName))
                .map(URL::getFile)
                .map(File::new)
                .orElseThrow(() -> new IllegalArgumentException("File not found: " + fileName));
    }
}
