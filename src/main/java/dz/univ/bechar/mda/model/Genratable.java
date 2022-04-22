package dz.univ.bechar.mda.model;

import java.io.InputStream;
import java.io.OutputStream;
import java.time.Duration;

public interface Genratable {
    public Duration getduration();
    public OutputStream generatethumbnail();
}
