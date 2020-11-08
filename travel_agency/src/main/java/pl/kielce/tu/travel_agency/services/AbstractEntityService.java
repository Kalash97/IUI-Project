package pl.kielce.tu.travel_agency.services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractEntityService<T> {

    public abstract JpaRepository getEntityRepository();

    public void create(Serializable entity) {
        getEntityRepository().save(entity);
    }

    public List<T> read(List<Long> ids) {
        return getEntityRepository().findAllById(ids);
    }

    public List<T> readAll() {
        return getEntityRepository().findAll();
    }

    public void update(Serializable entity) {
        create(entity);
    }

    public void delete(Serializable entity) {
        getEntityRepository().delete(entity);
    }
}
