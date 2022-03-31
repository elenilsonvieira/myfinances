package br.edu.ifpb.dac.myfinances.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.edu.ifpb.dac.myfinances.model.entity.SystemUser;

public interface SystemUserRepository extends PagingAndSortingRepository<SystemUser, Long> {

}
