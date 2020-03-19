import io.reactivex.Observable;
import io.reactivex.Observer;
import model.Photo;
import util.PhotoDownloader;
import util.PhotoProcessor;
import util.PhotoSerializer;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhotoCrawler {

    private static final Logger log = Logger.getLogger(PhotoCrawler.class.getName());

    private final PhotoDownloader photoDownloader;

    private final PhotoSerializer photoSerializer;

    private final PhotoProcessor photoProcessor;

    public PhotoCrawler() throws IOException {
        this.photoDownloader = new PhotoDownloader();
        this.photoSerializer = new PhotoSerializer("./photos");
        this.photoProcessor = new PhotoProcessor();
    }

    public void resetLibrary() throws IOException {
        photoSerializer.deleteLibraryContents();
    }

    public void downloadPhotoExamples() {
        try {
            Observable<Photo> downloadedExamples = photoDownloader.getPhotoExamples();
            downloadedExamples.subscribe(photo -> photoSerializer.savePhoto(photo));
        } catch (IOException e) {
            log.log(Level.SEVERE, "Downloading photo examples error", e);
        }
    }

    public void downloadPhotosForQuery(String query) throws IOException {
        try {
            Observable<Photo> downloadedExamples = photoDownloader.searchForPhotos(query);
            downloadedExamples.subscribe(photo -> photoSerializer.savePhoto(photo), error -> error.printStackTrace());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Could not download a photo", e);
        }
    }

    public void downloadPhotosForMultipleQueries(List<String> query) throws IOException {

    }
}
