package ra.business;

import ra.entity.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ProductBusiness {
    private ArrayList<Product> productList = new ArrayList<>();

    // Thêm sản phẩm mới
    public void addProduct(Scanner scanner) {
        Product product = new Product();
        product.inputData(scanner, productList);
        productList.add(product);
        System.out.println("Thêm sản phẩm thành công!");
    }

    // Hiển thị danh sách sản phẩm
    public void displayProducts() {
        if (productList.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống!");
            return;
        }
        System.out.println("\n--- DANH SÁCH SẢN PHẨM ---");
        for (Product p : productList) {
            System.out.println(p);
        }
    }

    // Cập nhật sản phẩm theo mã
    public void updateProduct(Scanner scanner) {
        System.out.print("Nhập mã sản phẩm cần cập nhật: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Mã sản phẩm không hợp lệ!");
            return;
        }

        Product product = findById(id);
        if (product == null) {
            System.out.println("Không tìm thấy mã sản phẩm có ID: " + id);
            return;
        }

        System.out.println("Đã tìm thấy sản phẩm. Nhập thông tin mới:");
        // Cập nhật tên
        System.out.print("Nhập tên mới (10-50 ký tự, bỏ trống nếu giữ nguyên): ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) {
            if (name.length() >= 10 && name.length() <= 50) {
                product.setProductName(name);
            } else {
                System.out.println("Tên không hợp lệ, giữ nguyên tên cũ.");
            }
        }

        // Cập nhật giá
        System.out.print("Nhập giá mới (hoặc nhập số âm/0 nếu giữ nguyên): ");
        try {
            float p = Float.parseFloat(scanner.nextLine());
            if (p > 0) product.setPrice(p);
        } catch (NumberFormatException ignored) {}

        // Cập nhật danh mục
        System.out.print("Nhập danh mục mới (bỏ trống nếu giữ nguyên): ");
        String cat = scanner.nextLine().trim();
        if (!cat.isEmpty() && cat.length() <= 200) {
            product.setCategory(cat);
        }

        // Cập nhật số lượng
        System.out.print("Nhập số lượng mới (hoặc nhập số âm nếu giữ nguyên): ");
        try {
            int q = Integer.parseInt(scanner.nextLine());
            if (q >= 0) product.setQuantity(q);
        } catch (NumberFormatException ignored) {}

        System.out.println("Cập nhật thông tin sản phẩm thành công!");
    }

    // Xóa sản phẩm theo mã
    public void deleteProduct(Scanner scanner) {
        System.out.print("Nhập mã sản phẩm cần xóa: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Mã sản phẩm không hợp lệ!");
            return;
        }

        Product product = findById(id);
        if (product != null) {
            productList.remove(product);
            System.out.println("Xóa sản phẩm thành công!");
        } else {
            System.out.println("Không tìm thấy mã sản phẩm cần xóa.");
        }
    }

    // Tìm kiếm sản phẩm theo tên (gần đúng, không phân biệt hoa/thường)
    public void searchByName(Scanner scanner) {
        System.out.print("Nhập từ khóa tên sản phẩm cần tìm: ");
        String keyword = scanner.nextLine().toLowerCase().trim();
        boolean found = false;

        System.out.println("\n--- KẾT QUẢ TÌM KIẾM ---");
        for (Product p : productList) {
            if (p.getProductName().toLowerCase().contains(keyword)) {
                System.out.println(p);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy sản phẩm phù hợp với từ khóa.");
        }
    }

    // Sắp xếp sản phẩm theo giá tăng dần
    public void sortByPriceAscending() {
        if (productList.isEmpty()) {
            System.out.println("Danh sách trống!");
            return;
        }
        productList.sort(Comparator.comparingDouble(Product::getPrice));
        System.out.println("Đã sắp xếp sản phẩm theo giá tăng dần:");
        displayProducts();
    }

    // Sắp xếp sản phẩm theo số lượng giảm dần
    public void sortByQuantityDescending() {
        if (productList.isEmpty()) {
            System.out.println("Danh sách trống!");
            return;
        }
        productList.sort((p1, p2) -> Integer.compare(p2.getQuantity(), p1.getQuantity()));
        System.out.println("Đã sắp xếp sản phẩm theo số lượng giảm dần:");
        displayProducts();
    }

    // Phương thức phụ trợ tìm theo ID
    private Product findById(int id) {
        for (Product p : productList) {
            if (p.getProductId() == id) {
                return p;
            }
        }
        return null;
    }
}