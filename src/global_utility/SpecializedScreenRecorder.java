package global_utility;

import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.monte.media.Format;
import org.monte.media.Registry;
import org.monte.screenrecorder.ScreenRecorder;

public class SpecializedScreenRecorder extends ScreenRecorder{
	private String name;
	 
    public SpecializedScreenRecorder(GraphicsConfiguration cfg,
           Rectangle captureArea, Format fileFormat, Format screenFormat,
           Format mouseFormat, Format audioFormat, File movieFolder,
           String name) throws IOException, AWTException {
         super(cfg, captureArea, fileFormat, screenFormat, mouseFormat,
                  audioFormat, movieFolder);
         this.name = name;
    }
 
    @Override
    protected File createMovieFile(Format fileFormat) throws IOException {
          if (!movieFolder.exists()) {
                movieFolder.mkdirs();
          } else if (!movieFolder.isDirectory()) {
                throw new IOException("\"" + movieFolder + "\" is not a directory.");
          }
          Path path = FileSystems.getDefault().getPath(movieFolder+"/"+ name+"."
                  + Registry.getInstance().getExtension(fileFormat));
			if(Files.deleteIfExists(path));           
                         
          return new File(movieFolder, name+"."
                  + Registry.getInstance().getExtension(fileFormat));
    }
}
