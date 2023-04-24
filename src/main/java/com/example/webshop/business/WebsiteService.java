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
    CustomerOrder customerOrder;
    @Autowired
    EmailService emailService;


    WebsiteService() {
        cart= new Cart();
        customerOrder= new CustomerOrder(cart.getCartItems(),person);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public List<Product> deleteProduct(Integer id){
        productRepository.deleteById(Long.valueOf(id));
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

    public String checkIfUserExist(String loginUser, String password, boolean admin) {
        List<Person> personList = personRepository.findByEmailAndPassWord(loginUser, password);
        if (personList.isEmpty()) {
            person = personRepository.save(new Person(loginUser, password, admin));
            return "This user is now register.";
        }
        return "This user already exist.";
    }

    public Product addProductIntoDB(String productName, Double productPrice, String productCategory) {
        product = productRepository.save(new Product(productName, productPrice, productCategory));
        return product;
    }
    public Product updatePrice(Integer id, Double price){
        product= productRepository.findById(Long.valueOf(id)).get();
        product.setPrice(price);
        product= productRepository.save(product);
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
        emailService.sendEmail(person.getEmail(),"What You have Ordered", "This is all the products you have ordered" + person.getCustomerOrders());
        clearCart();
    }
    public void clearCart(){
        cart= new Cart();
    }

    public List<CustomerOrder> getCustomerOrders() {
        return person.getCustomerOrders();
    }
    public List<CustomerOrder> getAllCustomersOrders(){
        return orderRepository.findAll();
    }
    public List<Person> personList(){
        return personRepository.findAll();
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
    public Boolean getAdminLogin(){
        return person.isAdmin();
    }
    public void saveOrder(CustomerOrder customerOrder){
        customerOrder =orderRepository.save(customerOrder);
    }
}
