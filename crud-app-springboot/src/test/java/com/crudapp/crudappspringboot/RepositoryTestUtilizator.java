package com.crudapp.crudappspringboot;


import com.crudapp.crudappspringboot.utilizator.RepositoryUtilizator;
import com.crudapp.crudappspringboot.utilizator.Utilizator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RepositoryTestUtilizator {
    @Autowired private RepositoryUtilizator repo;


    @Test
    public void testAdaugaUtilizator(){
        Utilizator utilizator = new Utilizator();
        utilizator.setEmail("raluca.barboselu@yahoo.com");
        utilizator.setParola("parola");
        utilizator.setNume("Barboselu");
        utilizator.setPrenume("Raluca");
        utilizator.setAcces(true);

        Utilizator utilizatorSalvat = repo.save(utilizator);

        Assertions.assertThat(utilizatorSalvat).isNotNull();
        Assertions.assertThat(utilizatorSalvat.getId()).isGreaterThan(0);
    }

    @Test
    public void testAfiseazaTot(){
        Iterable<Utilizator> utilizatori = repo.findAll();
        Assertions.assertThat(utilizatori).hasSizeGreaterThan(0);

        for( Utilizator utilizator:utilizatori){
            System.out.println(utilizator);
        }
    }


    @Test
    public void testModifica(){
        Integer idUtilizator =1;
        Optional<Utilizator> utilizatorOptional = repo.findById(idUtilizator);
        Utilizator utilizator = utilizatorOptional.get();
        utilizator.setParola("parola");
        repo.save(utilizator);

        Utilizator utilizatorModificat = repo.findById(idUtilizator).get();
        Assertions.assertThat(utilizatorModificat.getParola()).isEqualTo("parola");
    }

    @Test
    public void testPrimeste(){
        Integer idUtilizator =2;
        Optional<Utilizator> utilizatorOptional = repo.findById(idUtilizator);


        Assertions.assertThat(utilizatorOptional).isPresent();
        System.out.println(utilizatorOptional.get());
    }

    @Test
    public void testSterge(){
        Integer idUtilizator =2;
        repo.deleteById(idUtilizator);
        Optional<Utilizator> utilizatorOptional = repo.findById(idUtilizator);

        Assertions.assertThat(utilizatorOptional).isNotPresent();
    }
}
