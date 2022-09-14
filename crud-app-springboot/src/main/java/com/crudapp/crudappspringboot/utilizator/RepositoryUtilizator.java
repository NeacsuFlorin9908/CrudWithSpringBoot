package com.crudapp.crudappspringboot.utilizator;

import org.springframework.data.repository.CrudRepository;

public interface RepositoryUtilizator extends CrudRepository <Utilizator, Integer> {
        public Long countById(Integer id);

}
