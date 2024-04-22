package com.example.waveexpress.car.wash.repository;

import org.springframework.data.jpa.repository.*;
import com.example.waveexpress.car.wash.beans.*;

/**
 * Repository interface for CRUD operations on Sale entities.
 */
public interface SaleRepository extends JpaRepository<Sale, Integer> {
    
}
