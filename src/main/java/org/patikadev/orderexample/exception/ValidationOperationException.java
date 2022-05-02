package org.patikadev.orderexample.exception;

public class ValidationOperationException {
    public ValidationOperationException() {
    }

    public static class CustomerNotValidException extends BaseException {
        public CustomerNotValidException(String message) {
            super(message);
        }
    }

    public static class ProductNotValidException extends BaseException {
        public ProductNotValidException(String message) {
            super(message);
        }
    }

    public static class CustomerIDNotValidException extends BaseException {
        public CustomerIDNotValidException(String message) {
            super(message);
        }
    }

    public static class BrandNotValidException extends BaseException {
        public BrandNotValidException(String message) {
            super(message);
        }
    }

    public static class CategoryNotValidException extends BaseException {
        public CategoryNotValidException(String message) {
            super(message);
        }
    }

    public static class CouponNotValidException extends BaseException {
        public CouponNotValidException(String message) {
            super(message);
        }
    }

    public static class OrderNotValidException extends BaseException {
        public OrderNotValidException(String message) {
            super(message);
        }
    }

}
