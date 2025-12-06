package com.example.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.beans.Contact;

@FeignClient(name = "contact-ms")
public interface ContactFeignClient {

    @GetMapping("/contacts/{userId}")
    List<Contact> getSpecificContact(@PathVariable("userId") int userId);

}
