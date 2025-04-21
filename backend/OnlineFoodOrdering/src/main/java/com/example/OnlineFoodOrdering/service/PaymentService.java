package com.example.OnlineFoodOrdering.service;

import com.example.OnlineFoodOrdering.model.Order;
import com.example.OnlineFoodOrdering.response.PaymentResponse;

public interface PaymentService {
    public PaymentResponse createPaymentLink(Order order);
}
