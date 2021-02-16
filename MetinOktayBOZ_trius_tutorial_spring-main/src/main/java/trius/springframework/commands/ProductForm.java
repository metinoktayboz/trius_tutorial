package trius.springframework.commands;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductForm {


    private String id;
    @NotNull
    private String description;
    @NotNull
    private BigDecimal price;
    @NotNull
    private BigDecimal stock;

    private int orderNumber=0;

    private String orderDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderDate() { return orderDate; }

    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }
}
