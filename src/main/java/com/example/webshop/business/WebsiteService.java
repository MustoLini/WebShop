package com.example.webshop.business;

import com.example.webshop.data.OrderRepository;
import com.example.webshop.data.PersonRepository;
import com.example.webshop.data.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
@SessionScope
public class WebsiteService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    PersonRepository personRepository;

    @Autowired
    OrderRepository orderRepository;

    Person person;
    Product product;
    Cart cart;


    WebsiteService() {
        cart= new Cart();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product getByIdProduct(long id){
        return productRepository.findById(id).get();
    }

    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    public Person login(String loginUser, String password) {
        List<Person> personList = personRepository.findByEmailAndPassWord(loginUser, password);
        person = personList.get(0);
        return person;
    }

    public String checkIfUserExist(String loginUser, String password) {
        List<Person> personList = personRepository.findByEmailAndPassWord(loginUser, password);
        if (personList.isEmpty()) {
            person = personRepository.save(new Person(loginUser, password));
            return "This user is now register.";
        }
        return "This user already exist.";
    }

    public Product addProductIntoDB(String productName, Double productPrice, String productCategory) {
        product = productRepository.save(new Product(productName, productPrice, productCategory));
        return product;
    }
    public Cart addProductIntoCart(Long id, int amount){
        cart.cartItems.add(new CartItem(getByIdProduct(id),amount));
        System.out.println(id+" "+ amount);
        return cart;
    }
    public Cart getCart(){return cart;}
    public List<CartItem> removeCartItem(int id){
       return cart.removeItemFromCart(id);
    }
    public void addIntoOrder(){
        person.addOrder(new CustomerOrder(getCart().getCartItems(),person));
        person = personRepository.save(person);
        clearCart();
    }
    public void clearCart(){
        cart= new Cart();
    }

    public List<CustomerOrder> getCustomerOrders() {
        return person.getCustomerOrders();
    }
    public List<Product>findSpecificProduct( String nameOfProduct){
        return productRepository.findByName(nameOfProduct);

    }
    public List<Product> findBySpecificCategory(String category){
        return productRepository.findByCategory(category);
    }
    public Set<String> getAllCategories(){
        Set<String> categories= new TreeSet<>();
        for (Product p: productRepository.findAll()) {
            categories.add(p.getCategory());
        }
        return categories;
    }
}
