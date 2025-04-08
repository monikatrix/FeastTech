package com.example.OnlineFoodOrdering.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.OnlineFoodOrdering.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {

}
