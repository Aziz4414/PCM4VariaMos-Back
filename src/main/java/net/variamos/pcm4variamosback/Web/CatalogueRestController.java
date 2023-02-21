package net.variamos.pcm4variamosback.Web;

import net.variamos.pcm4variamosback.Dao.ProductRepository;
import net.variamos.pcm4variamosback.Entities.Product;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;

@CrossOrigin("*")
@RestController
public class CatalogueRestController {
    private final ProductRepository productRepository;

    public CatalogueRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @GetMapping(path="/photoProduct/{id}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
        Product p =productRepository.findById(id).get();
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+ "/Desktop/dev/Ecom/img/" +p.getPhotoName()));
    }


    @PostMapping(path = "/uploadPhoto/{id}")
    public void uploadPhoto(@RequestParam("file") MultipartFile file, @PathVariable Long id) throws Exception {
        Product p = productRepository.findById(id).get();
        p.setPhotoName(file.getOriginalFilename());
        Files.write(Paths.get(System.getProperty("user.home")+ "/Desktop/dev/Ecom/img/" +p.getPhotoName()), file.getBytes());
        productRepository.save(p);
    }

}

