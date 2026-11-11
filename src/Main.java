import ra.business.ProductBusiness;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductBusiness productBusiness = new ProductBusiness();

        while (true) {
            System.out.println("\n*********************QUẢN LÝ SẢN PHẨM*********************");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Danh sách sản phẩm");
            System.out.println("3. Cập nhật sản phẩm theo mã sản phẩm");
            System.out.println("4. Xóa sản phẩm theo mã sản phẩm");
            System.out.println("5. Tìm kiếm sản phẩm theo tên");
            System.out.println("6. Sắp xếp sản phẩm theo giá tăng dần");
            System.out.println("7. Sắp xếp sản phẩm theo số lượng giảm dần");
            System.out.println("8. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            String choiceStr = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập từ 1 đến 8.");
                continue;
            }

            switch (choice) {
                case 1:
                    productBusiness.addProduct(scanner);
                    break;
                case 2:
                    productBusiness.displayProducts();
                    break;
                case 3:
                    productBusiness.updateProduct(scanner);
                    break;
                case 4:
                    productBusiness.deleteProduct(scanner);
                    break;
                case 5:
                    productBusiness.searchByName(scanner);
                    break;
                case 6:
                    productBusiness.sortByPriceAscending();
                    break;
                case 7:
                    productBusiness.sortByQuantityDescending();
                    break;
                case 8:
                    System.out.println("Cảm ơn bạn đã sử dụng chương trình!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại từ 1 đến 8.");
            }
        }
    }
}