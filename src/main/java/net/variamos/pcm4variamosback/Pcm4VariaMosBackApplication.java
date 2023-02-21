package net.variamos.pcm4variamosback;
import net.bytebuddy.utility.RandomString;
import net.variamos.pcm4variamosback.Dao.*;
import net.variamos.pcm4variamosback.Entities.Product;
import net.variamos.pcm4variamosback.Entities.Category;
import net.variamos.pcm4variamosback.Dao.ProductRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Random;

@SpringBootApplication
public class Pcm4VariaMosBackApplication implements CommandLineRunner {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(Pcm4VariaMosBackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repositoryRestConfiguration.exposeIdsFor(Product.class, Category.class);

		categoryRepository.save(new Category(null,"Example of category 1 : Cat 1",null,null,null));
		categoryRepository.save(new Category(null,"Example of category 2 : Cat 2",null,null,null));
		categoryRepository.save(new Category(null,"Example of category 3 : Cat 3",null,null,null));
		categoryRepository.save(new Category(null,"Example of category 4 : Cat 4",null,null,null));
		categoryRepository.save(new Category(null,"Example of category 5 : Cat 5",null,null,null));

		Random rnd = new Random();

		categoryRepository.findAll().forEach(c ->{
			for (int i = 0; i < 10; i++) {
				Product p = new Product();
				p.setName(RandomString.make(15));
				p.setDescription(RandomString.make(28));
				p.setCurentPrice(100+rnd.nextInt(100));
				p.setAvailable(rnd.nextBoolean());
				p.setPromotion(rnd.nextBoolean());
				p.setSelected(rnd.nextBoolean());
				p.setCategory(c);
				p.setPhotoName("unknown.png");
				productRepository.save(p);
			}}
		);
	}
}

