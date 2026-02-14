package com.hiberne;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CrudOperation {

    public static void main(String[] args) {

        // CREATE
        Session session = HiberUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Product product = new Product(
                "Laptop",
                "Gaming Laptop",
                "Electronics",
                10,
                75000.00,
                "SKU1001",
                true
        );







        session.save(product);
        tx.commit();
        session.close();

        // READ
        session = HiberUtil.getSessionFactory().openSession();
        Product fetchedProduct = session.get(Product.class, 1L);
        System.out.println("Product Name: " + fetchedProduct.getName());
        session.close();

        // UPDATE
        session = HiberUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();

        Product updateProduct = session.get(Product.class, 1L);
        updateProduct.setPrice(70000.00);
        updateProduct.setQuantity(8);

        session.update(updateProduct);
        tx.commit();
        session.close();

        // DELETE
        session = HiberUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();

        Product deleteProduct = session.get(Product.class, 1L);
        session.delete(deleteProduct);

        tx.commit();
        session.close();

        HiberUtil.getSessionFactory().close();
    }
}
