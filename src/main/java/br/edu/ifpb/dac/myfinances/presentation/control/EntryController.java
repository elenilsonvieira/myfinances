package br.edu.ifpb.dac.myfinances.presentation.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.edu.ifpb.dac.myfinances.business.service.ConverterService;
import br.edu.ifpb.dac.myfinances.business.service.EntryService;
import br.edu.ifpb.dac.myfinances.model.entity.Entry;
import br.edu.ifpb.dac.myfinances.presentation.dto.EntryDTO;

@Controller
public class EntryController {

	@Autowired
	private ConverterService converterService;
	@Autowired
	private EntryService entryService;
	
	public void save(EntryDTO dto) {
		Entry entry = converterService.dtoToDomain(dto);
		entryService.save(entry);
	}
	
}
