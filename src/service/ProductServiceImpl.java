package service;
import model.Product;
import java.util.*;
public class ProductServiceImpl implements ProductService {
    private static Map<Integer, Product> products;
    static {
        products = new HashMap<>();
        products.put(1, new Product(1, "Iphone", "Dien thoai", 800, true,"cho1.jpeg"));
        products.put(2, new Product(2, "Nokia", "Dien thoai", 500, false,"cho2.jpeg"));
        products.put(3, new Product(3, "Samsung Galaxy", "Dien thoai", 600, true,"cho3.jpeg"));
        products.put(4, new Product(4, "Xiaomi", "Dien thoai", 400, true,"anh1.jpg"));
        products.put(5, new Product(5, "Sony", "Tivi", 1500, true,"cho2.jpeg"));
    }
    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void update(int id, Product product) {
        products.put(id, product);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }

    @Override
    public Product findByName(String name){
        ArrayList<Product> list = new ArrayList<>(products.values());
        for (Product product : list ) {
            if(product.getName().equals(name)) {
                return product;
            }
        }
        return null;
        }
    }

