package ra.entity;

import java.util.Scanner;

public class Product {
    private static int nextId = 1; // Biến tĩnh để tự tăng mã sản phẩm
    private int productId;
    private String productName;
    private float price;
    private String category;
    private int quantity;

    // Constructor không tham số
    public Product() {
        this.productId = nextId++;
    }

    // Constructor đầy đủ tham số
    public Product(String productName, float price, String category, int quantity) {
        this.productId = nextId++;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    // Getters và Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Phương thức nhập dữ liệu có validate
    public void inputData(Scanner scanner, java.util.List<Product> existingProducts) {
        // Nhập tên sản phẩm: 10-50 ký tự, không được trùng lặp
        System.out.print("Nhập tên sản phẩm (10-50 ký tự): ");
        while (true) {
            this.productName = scanner.nextLine().trim();
            if (productName.length() < 10 || productName.length() > 50) {
                System.out.print("Tên sản phẩm phải từ 10 đến 50 ký tự. Vui lòng nhập lại: ");
                continue;
            }
            // Kiểm tra trùng lặp tên
            boolean isExist = false;
            for (Product p : existingProducts) {
                if (p.getProductName().equalsIgnoreCase(this.productName)) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                System.out.print("Tên sản phẩm đã tồn tại. Vui lòng nhập tên khác: ");
            } else {
                break;
            }
        }

        // Nhập giá: lớn hơn 0
        System.out.print("Nhập giá sản phẩm (> 0): ");
        while (true) {
            try {
                this.price = Float.parseFloat(scanner.nextLine());
                if (this.price > 0) {
                    break;
                }
                System.out.print("Giá phải lớn hơn 0. Vui lòng nhập lại: ");
            } catch (NumberFormatException e) {
                System.out.print("Định dạng không hợp lệ. Vui lòng nhập số thực: ");
            }
        }

        // Nhập loại sản phẩm: tối đa 200 ký tự
        System.out.print("Nhập loại sản phẩm (tối đa 200 ký tự): ");
        while (true) {
            this.category = scanner.nextLine().trim();
            if (!this.category.isEmpty() && this.category.length() <= 200) {
                break;
            }
            System.out.print("Loại sản phẩm không được để trống và tối đa 200 ký tự. Nhập lại: ");
        }

        // Nhập số lượng tồn kho: lớn hơn hoặc bằng 0
        System.out.print("Nhập số lượng tồn kho (>= 0): ");
        while (true) {
            try {
                this.quantity = Integer.parseInt(scanner.nextLine());
                if (this.quantity >= 0) {
                    break;
                }
                System.out.print("Số lượng phải lớn hơn hoặc bằng 0. Vui lòng nhập lại: ");
            } catch (NumberFormatException e) {
                System.out.print("Định dạng không hợp lệ. Vui lòng nhập số nguyên: ");
            }
        }
    }

    // Ghi đè phương thức toString()
    @Override
    public String toString() {
        return String.format("ID: %d | Tên: %s | Giá: %.2f | Loại: %s | Tồn kho: %d",
                productId, productName, price, category, quantity);
    }
}