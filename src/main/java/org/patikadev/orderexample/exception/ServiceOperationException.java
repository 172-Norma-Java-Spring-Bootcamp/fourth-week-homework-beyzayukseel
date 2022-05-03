package org.patikadev.orderexample.exception;

public final class ServiceOperationException {

    private ServiceOperationException() {
    }

    public static class CustomerNotFoundException extends BaseException {
        public CustomerNotFoundException(String message) {
            super(message);
        }
    }

    public static class CustomerAlreadyDeleted extends BaseException {
        public CustomerAlreadyDeleted(String message) {
            super(message);
        }
    }

    public static class ProductNotFoundException extends BaseException {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }

    public static class CategoryNotFoundException extends BaseException {
        public CategoryNotFoundException(String message) {
            super(message);
        }
    }

    public static class BasketNotFoundException extends BaseException {
        public BasketNotFoundException(String message) {
            super(message);
        }
    }

    public static class BasketItemNotFoundException extends BaseException {
        public BasketItemNotFoundException(String message) {
            super(message);
        }
    }

    public static class BrandNotFoundException extends BaseException {
        public BrandNotFoundException(String message) {
            super(message);
        }
    }

    public static class OrderNotFoundException extends BaseException {
        public OrderNotFoundException(String message) {
            super(message);
        }
    }

    public static class OrderItemNotFoundException extends BaseException {
        public OrderItemNotFoundException(String message) {
            super(message);
        }
    }

    public static class CouponNotFoundException extends BaseException {
        public CouponNotFoundException(String message) {
            super(message);
        }
    }


}
