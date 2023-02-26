package net.variamos.pcm4variamosback.Web;

import net.variamos.pcm4variamosback.Dao.ProductRepository;
import net.variamos.pcm4variamosback.Entities.Product;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

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

    @DeleteMapping("/deleteProduct/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long productId)
            throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not present for the id :: " + productId));

        productRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}

