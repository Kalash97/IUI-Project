package pl.kielce.tu.travel_agency.services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public abstract class AbstractEntityService<T extends Serializable> {

    public abstract JpaRepository<T, Long> getEntityRepository();

    public void create(T entity) {
        getEntityRepository().save(entity);
    }

    public T readOne(Long id) {
        return (T) getEntityRepository().findAllById(Collections.singletonList(id)).get(0);
    }

    public List<T> read(List<Long> ids) {
        return getEntityRepository().findAllById(ids);
    }

    public List<T> readAll() {
        return getEntityRepository().findAll();
    }

    public void update(T entity) {
        create(entity);
    }

    public void delete(T entity) {
        getEntityRepository().delete(entity);
    }
}
