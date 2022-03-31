package br.edu.ifpb.dac.myfinances.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.dac.myfinances.model.entity.Entry;

public interface EntryRepository extends JpaRepository<Entry, Long> {

}
