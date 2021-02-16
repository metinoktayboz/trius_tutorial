package trius.springframework.services;

import trius.springframework.commands.ProductForm;
import trius.springframework.converters.ProductFormToProduct;
import trius.springframework.domain.Product;
import trius.springframework.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jt on 1/10/17.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductFormToProduct productFormToProduct;
    private Product product;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductFormToProduct productFormToProduct) {
        this.productRepository = productRepository;
        this.productFormToProduct = productFormToProduct;
    }


    @Override
    public List<Product> listAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add); //fun with Java 8
        return products;
    }

    @Override
    public Product getById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        productRepository.save(product);
        return product;
    }

    @Override
    public void delete(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product saveOrUpdateProductForm(ProductForm productForm) {
        Product savedProduct = saveOrUpdate(productFormToProduct.convert(productForm));

        System.out.println("Saved Product Id: " + savedProduct.getId());
        return savedProduct;
    }
    //yeni eklendi
    @Override
    public void order(ProductForm productForm) {

        BigDecimal one = new BigDecimal("1");
        BigDecimal stock = productForm.getStock();
        BigDecimal newStock = stock.subtract(one);
        int orderNumber = productForm.getOrderNumber();
        orderNumber++;
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        productForm.setOrderDate(date.format(formatter));
        productForm.setOrderNumber(orderNumber);
        productForm.setStock(newStock);
        Product product = saveOrUpdateProductForm(productForm);
    }
}
