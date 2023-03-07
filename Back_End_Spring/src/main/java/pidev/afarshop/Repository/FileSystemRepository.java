package pidev.afarshop.Repository;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Repository
public class FileSystemRepository  {

    public String save(MultipartFile file) throws Exception {
        Path newFile = Paths.get(System.getProperty("user.dir")+"/assets/" + file.getOriginalFilename()); // to change
        Files.createDirectories(newFile.getParent());
        Files.write(newFile, file.getBytes());
        return newFile.toAbsolutePath()
                .toString();
    }

    public FileSystemResource findInFileSystem(String location) {
        try {
            return new FileSystemResource(Paths.get(location));
        } catch (Exception e) {
            // Handle access or file not found problems.
            throw new RuntimeException();
        }
    }

    public void deletefile(String location){
        File file = new File(location);
        file.delete();
    }
}

