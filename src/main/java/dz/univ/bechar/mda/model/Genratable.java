package dz.univ.bechar.mda.model;

import java.io.InputStream;
import java.nio.file.Path;
import java.time.Duration;

public interface Genratable {
    Duration getduration();
    Path generatethumbnail();
}
