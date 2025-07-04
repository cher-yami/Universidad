package com.ccstudent.msformapago.repository;

import com.ccstudent.msformapago.entity.FormaPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagoRepository extends JpaRepository<FormaPago, Long> {
}