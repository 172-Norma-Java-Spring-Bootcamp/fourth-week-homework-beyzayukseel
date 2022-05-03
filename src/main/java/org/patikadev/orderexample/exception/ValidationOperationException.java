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

    public static class TaxPriceNotValidException extends BaseException {
        public TaxPriceNotValidException(String message) {
            super(message);
        }
    }

    public static class DiscountPriceNotValidException extends BaseException {
        public DiscountPriceNotValidException(String message) {
            super(message);
        }
    }

    public static class ShippingPriceNotValidException extends BaseException {
        public ShippingPriceNotValidException(String message) {
            super(message);
        }
    }

    public static class PriceNotValidException extends BaseException {
        public PriceNotValidException(String message) {
            super(message);
        }
    }

    public static class CouponCodeNotValidException extends BaseException {
        public CouponCodeNotValidException(String message) {
            super(message);
        }
    }

    public static class DateNotValidException extends BaseException {
        public DateNotValidException(String message) {
            super(message);
        }
    }
}
