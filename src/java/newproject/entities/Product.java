package newproject.entities;

public class Product {

    private int productId;
    private String productName;
    private String productDescription;
    private double productPrice;
    private int productDiscount;
    private int productQuantity;
    private int categoryId;
    private String productImage;

    public Product(int productId, String productName, String productDescription,
                   double productPrice, int productDiscount, int productQuantity,
                   int categoryId, String productImage) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productDiscount = productDiscount;
        this.productQuantity = productQuantity;
        this.categoryId = categoryId;
        this.productImage = productImage;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getProductDiscount() {
        return productDiscount;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getProductImage() {
        return productImage;
    }
    
    //calculate price after discount
   public int getPriceAfterApplyingDiscount() {
    double discountMultiplier = 1 - (this.getProductDiscount() / 100.0);
    double discountedPrice = this.getProductPrice() * discountMultiplier;
    
    // Round to the nearest integer (optional)
    int roundedDiscountedPrice = (int) Math.round(discountedPrice);
    
    return roundedDiscountedPrice;
}

    
    
}
