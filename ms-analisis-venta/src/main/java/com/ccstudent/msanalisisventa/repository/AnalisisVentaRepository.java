package com.ccstudent.msanalisisventa.repository;

import com.ccstudent.msanalisisventa.entity.AnalisisVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalisisVentaRepository extends JpaRepository<AnalisisVenta, Long> {
}