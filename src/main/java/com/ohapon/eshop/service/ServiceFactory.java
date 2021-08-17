package com.ohapon.eshop.service;

public interface ServiceFactory {

    ProductService getProductService();

    UserService getUserService();

    SecurityService getSecurityService();



}
