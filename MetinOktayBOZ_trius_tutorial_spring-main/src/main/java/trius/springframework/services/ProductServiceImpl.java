package trius.springframework.services;

import trius.springframework.commands.ProductForm;
import trius.springframework.converters.ProductFormToProduct;
import trius.springframework.domain.Product;
import trius.springframework.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public void order(String id) {
        product = getById(id);
        BigDecimal stock = product.getStock();
        BigDecimal empty = new BigDecimal("0");
        int control = stock.compareTo(empty);
        if(control==0){
            System.out.println("Stok bitti");
        }else{
            BigDecimal order = new BigDecimal("1");
            stock = order.subtract(stock);
            product.setStock(stock);
            product.setOrderNumber(product.getOrderNumber()+1);

        }


    }
}
