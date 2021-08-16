package com.ohapon.eshop.service;

public interface ServiceFactory {

    ProductService getProductService();

    SecurityService getSecurityService();

}
