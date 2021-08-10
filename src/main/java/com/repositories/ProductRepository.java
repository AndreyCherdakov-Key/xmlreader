package com.repositories;

import com.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    @Query(value = "SELECT p.ID, p.TYPE, PRICE, START_DATE, END_DATE from REQUEST_DETAILS rd " +
            "inner join ROOT r on rd.ID = r.REQUEST_DETAILS_ID " +
            "inner join ROOT_EVENTS re on r.ID = re.ROOT_ENTITY_ID " +
            "inner join EVENT e on re.EVENTS_ID = e.ID " +
            "inner join EVENT_PRODUCTS ep on e.ID = ep.EVENT_ENTITY_ID " +
            "inner join PRODUCT p on ep.PRODUCTS_ID = p.ID " +
            "where INSURED_ID=:insuredId " +
            "group by SOURCE_COMPANY, p.ID", nativeQuery = true)
    List<ProductEntity> findAllProductsByInsuredIdGroupBySourceCompany(@Param("insuredId") int insuredId);

}
