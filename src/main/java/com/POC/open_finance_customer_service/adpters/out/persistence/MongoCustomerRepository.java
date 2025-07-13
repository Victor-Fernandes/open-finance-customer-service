package com.POC.open_finance_customer_service.adpters.out.persistence;

import com.POC.open_finance_customer_service.application.port.out.CustomerRepositoryPort;
import com.POC.open_finance_customer_service.domain.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MongoCustomerRepository implements CustomerRepositoryPort {

    private static final Logger log = LoggerFactory.getLogger(MongoCustomerRepository.class);
    private final ReactiveMongoTemplate mongoTemplate;

    public MongoCustomerRepository(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Mono<Customer> save(Customer customer) {
        log.debug("Salvando cliente: {}", customer);
        return mongoTemplate.save(customer);
    }

    @Override
    public Mono<Customer> findById(String id) {
        log.debug("Buscando cliente por ID: {}", id);
        return mongoTemplate.findById(id, Customer.class);
    }

    @Override
    public Flux<Customer> findAll() {
        log.debug("Buscando todos os clientes");
        return mongoTemplate.findAll(Customer.class);
    }

    @Override
    public Mono<Customer> findByDocumentNumber(String documentNumber) {
        log.debug("Buscando cliente por número de documento: {}", documentNumber);
        Query query = new Query(Criteria.where("documentNumber").is(documentNumber));
        return mongoTemplate.findOne(query, Customer.class);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        log.debug("Excluindo cliente com ID: {}", id);
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.remove(query, Customer.class).then();
    }

    @Override
    public Mono<Boolean> existsByDocumentNumber(String documentNumber) {
        log.debug("Verificando existência de cliente com documento: {}", documentNumber);
        Query query = new Query(Criteria.where("documentNumber").is(documentNumber));
        return mongoTemplate.exists(query, Customer.class);
    }
}
