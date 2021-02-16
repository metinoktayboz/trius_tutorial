package trius.springframework.converters;

import trius.springframework.commands.ProductForm;
import trius.springframework.domain.Product;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ProductFormToProduct implements Converter<ProductForm, Product> {

    @Override
    public Product convert(ProductForm productForm) {

        Product product = new Product();
        if (productForm.getId() != null  && !StringUtils.isEmpty(productForm.getId())) {
            product.setId(new ObjectId(productForm.getId()));
        }
        product.setDescription(productForm.getDescription());
        product.setPrice(productForm.getPrice());
        product.setStock(productForm.getStock());
        product.setOrderNumber(productForm.getOrderNumber());
        product.setOrderDate(productForm.getOrderDate());


        return product;
    }
}
