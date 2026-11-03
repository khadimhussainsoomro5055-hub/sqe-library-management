
import java.io.IOException;

public interface CatalogWriter {
    void write(String path, String content) throws IOException;
}
