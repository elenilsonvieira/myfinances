package br.edu.ifpb.dac.myfinances.business.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.myfinances.business.service.PasswordEnconderService;

@Service
public class PasswordEnconderServiceImpl extends BCryptPasswordEncoder implements PasswordEnconderService {

}
