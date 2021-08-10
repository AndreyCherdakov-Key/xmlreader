package com.services;

import com.domain.Event;
import com.domain.Product;
import com.domain.RequestDetails;
import com.domain.Root;
import com.entities.EventEntity;
import com.entities.ProductEntity;
import com.entities.RequestDetailsEntity;
import com.entities.RootEntity;
import com.repositories.EventRepository;
import com.repositories.ProductRepository;
import com.repositories.RequestDetailsRepository;
import com.repositories.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class XMLReaderService implements IXMLReader {

    final private RootRepository rootRepository;
    final private RequestDetailsRepository requestDetailsRepository;
    final private EventRepository eventRepository;
    final private ProductRepository productRepository;

    @Autowired
    public XMLReaderService(RootRepository rootRepository, RequestDetailsRepository requestDetailsRepository, EventRepository eventRepository, ProductRepository productRepository) {
        this.rootRepository = rootRepository;
        this.requestDetailsRepository = requestDetailsRepository;
        this.eventRepository = eventRepository;
        this.productRepository = productRepository;
    }

    @Override
    //Save into DB. Transactional with default params for rollback if have any exceptions
    @Transactional(rollbackFor = Exception.class)
    public void save(Root root) {
        RootEntity rootEntity = rootDtoToEntity(root);
        RequestDetailsEntity requestDetailsEntity = requestDetailsDtoToEntity(root.getRequestDetails());
        List<EventEntity> eventEntity = root.getEvent()
                                            .stream()
                                            .map(this::eventDtoToEntity)
                                            .collect(Collectors.toList());
        //Id in the RequestId it s a main id
        if (!requestDetailsRepository.existsById(requestDetailsEntity.getId())) {
            for (EventEntity eventEntity1 : eventEntity) {
                for (ProductEntity productEntity : eventEntity1.getProducts()) {
                    productRepository.saveAndFlush(productEntity);
                }
                eventRepository.saveAndFlush(eventEntity1);
            }
            requestDetailsRepository.saveAndFlush(requestDetailsEntity);
            rootRepository.saveAndFlush(rootEntity);
        } else {
            System.err.println("Root already in database");
            //Can t add new then update Root by RequestDetails id
        }

    }

    @Override
    //Can be phantom rows, used isolation serializable
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<Product> findProducts(int insureId) {
        List<ProductEntity> lProductEntities = productRepository.findAllProductsByInsuredIdGroupBySourceCompany(insureId);
        return lProductEntities.stream()
                                .map(this::productEntityToDto)
                                .collect(Collectors.toList());
    }

    /*          Transfer  DTO  -->  Entity          */

    private RootEntity rootDtoToEntity(Root root){
        return new RootEntity(
                requestDetailsDtoToEntity(root.getRequestDetails()),
                root.getEvent()
                        .stream()
                        .map(this::eventDtoToEntity)
                        .collect(Collectors.toList()));
    }

    private EventEntity eventDtoToEntity(Event event){
        return new EventEntity(
                event.getId(),
                event.getType(),
                event.getInsuredId(),
                event.getProduct()
                        .stream()
                        .map(this::productDtoToEntity)
                        .collect(Collectors.toList()));
    }

    private ProductEntity productDtoToEntity(Product product) {
        return new ProductEntity(
                product.getType(),
                product.getPrice(),
                product.getStartDate(),
                product.getEndDate());
    }

    private RequestDetailsEntity requestDetailsDtoToEntity(RequestDetails requestDetails){
        return new RequestDetailsEntity(
                requestDetails.getId(),
                requestDetails.getAcceptDate(),
                requestDetails.getSourceCompany());
    }

/*      Transfer  Entity  -->  DTO      */

    private Root rootEntityToDto(RootEntity rootEntity){
        return new Root(
                requestDetailsEntityToDto(
                        rootEntity.getRequestDetails()),
                rootEntity.getEvents()
                        .stream()
                        .map(this::eventEntityToDto)
                        .collect(Collectors.toList()));
    }

    private Event eventEntityToDto(EventEntity eventEntity){
        return new Event(
                eventEntity.getId(),
                eventEntity.getType(),
                eventEntity.getInsuredId(),
                eventEntity.getProducts()
                        .stream()
                        .map(this::productEntityToDto)
                        .collect(Collectors.toList()));
    }

    private Product productEntityToDto(ProductEntity productEntity) {
        return new Product(
                productEntity.getType(),
                productEntity.getPrice(),
                productEntity.getStartDate(),
                productEntity.getEndDate());
    }

    private RequestDetails requestDetailsEntityToDto(RequestDetailsEntity requestDetailsEntity){
        return new RequestDetails(
                requestDetailsEntity.getId(),
                requestDetailsEntity.getAcceptDate(),
                requestDetailsEntity.getSourceCompany());
    }
}
