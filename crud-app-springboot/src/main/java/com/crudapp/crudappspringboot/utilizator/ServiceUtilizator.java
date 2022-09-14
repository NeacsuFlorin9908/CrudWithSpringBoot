package com.crudapp.crudappspringboot.utilizator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceUtilizator {
    @Autowired private RepositoryUtilizator repo;

    public List<Utilizator> afiseazaListaUtilizatori() {
        return (List<Utilizator>)repo.findAll();
    }

    public void salveaza(Utilizator utilizator) {
        repo.save(utilizator);
    }

    public Utilizator get(Integer id) throws ExceptieUtitilizatorulNuEsteGasit {
        Optional<Utilizator> rezultat = repo.findById(id);
        if(rezultat.isPresent()){
            return rezultat.get();
        }
        throw new ExceptieUtitilizatorulNuEsteGasit("Nu a fost gasit niciun utilizator cu acest ID" + id);
    }

    public void sterge(Integer id) throws ExceptieUtitilizatorulNuEsteGasit {
        Long numar = repo.countById(id);
        if(numar == null || numar == 0){
            throw new ExceptieUtitilizatorulNuEsteGasit("Nu a fost gasit niciun utilizator cu acest ID" + id);
        }
        repo.deleteById(id);
    }
}
