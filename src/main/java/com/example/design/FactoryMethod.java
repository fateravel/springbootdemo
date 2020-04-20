package com.example.design;

/**
 * @author pengsong
 * @date 2020/4/19 1:14 下午
 * 工厂方法模式
 */
public class FactoryMethod {

    public static void main(String[] args) {
        Product product;
        ProductFactory factory;
        factory = new ProductAFactory();
        product = factory.getProduct();
        System.out.println(product.getClass().getName());
        factory = new ProductBFactory();
        product = factory.getProduct();
        System.out.println(product.getClass().getName());
    }
    
}

interface ProductFactory {
    Product getProduct();
}

interface Product {
}

class ProductA implements Product{
    public ProductA() {
        System.out.println("A is created");
    }
}

class ProductB implements Product {
    public ProductB() {
        System.out.println("B is created");
    }
}

class ProductAFactory implements ProductFactory {

    @Override
    public Product getProduct() {
        return new ProductA();
    }
}

class ProductBFactory implements ProductFactory {

    @Override
    public Product getProduct() {
        return new ProductB();
    }
}
