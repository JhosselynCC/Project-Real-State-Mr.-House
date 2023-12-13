/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realestate.mrhouse.Repositories;

import com.realestate.mrhouse.Entities.OffersByProperty;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 2171584201008
 */
@Repository
public interface OffersByPropertyRepository extends JpaRepository<OffersByProperty, Long> {

    @Query("SELECT u FROM OffersByProperty u where u.userEmail= :email")
    List<OffersByProperty> findByUserEmail(@Param("email") String email);
//@Query("SELECT a.id AS oferta, b.id AS inmueble, b.typeProperty AS type_property, " +
  //     "b.typePublication AS type_publication, b.price, a.statusOffer AS status_offer " +
  //     "FROM OffersByProperty a INNER JOIN a.property b WHERE a.userEmail = :email")
//List<Object[]> findByUserEmail(@Param("email") String email);

}
