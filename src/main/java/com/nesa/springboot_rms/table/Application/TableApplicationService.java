package com.nesa.springboot_rms.table.Application;

import com.nesa.springboot_rms.outlet.domain.extention.OutletId;
import com.nesa.springboot_rms.table.Api.dtos.TableCreateDto;
import com.nesa.springboot_rms.table.Domain.Table;
import com.nesa.springboot_rms.table.Domain.TableId;
import com.nesa.springboot_rms.table.Domain.TableRepository;
import com.nesa.springboot_rms.table.Domain.TableStatus;

import com.nesa.springboot_rms.table.Infrastructure.TableJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TableApplicationService {

    @Transactional
    public void createTables(List<TableCreateDto> requests) {
        List<Table> domainTables = requests.stream()
                .map(dto -> Table.createNew(
                        dto.getCapacity(),
                        dto.getName(),
                        dto.getPosX(),
                        dto.getPosY(),
                        dto.getOutletId(),
                        dto.getStatus()
                ))
                .collect(Collectors.toList());

        tableRepository.saveAll(domainTables);
    }

    private final TableRepository tableRepository;

    @Transactional
    public void assignAndOccupy(Long tableIdRaw, String waiterId) {
        TableId tableId = new TableId(tableIdRaw);
        Table table = tableRepository.findById(tableId)
                .orElseThrow(() -> new IllegalArgumentException("Table with ID " + tableIdRaw + " not found"));

        // table.assignWaiter(waiterId);

        tableRepository.save(table);
    }

    @Transactional
    public void releaseTable(Long tableIdRaw) {
        TableId tableId = new TableId(tableIdRaw);
        Table table = tableRepository.findById(tableId)
                .orElseThrow(() -> new IllegalArgumentException("Table with ID " + tableIdRaw + " not found"));

//        table.release();

        tableRepository.save(table);
    }

	public List<TableCreateDto> getAllTables() {
		List<Table> tables = tableRepository.findAll();

        List<TableCreateDto> tableCreateDtos = new ArrayList<>();
        for(Table table : tables){
            TableCreateDto dto=new TableCreateDto();
            dto.setCapacity(table.getCapacity());
//            dto.setId(table.getId().value());
//            dto.setStatus(table.getStatus().name());
//            dto.setName(table.getName());
//            dto.setPosX(table.getPosX());
//            dto.setPosY(table.getPosY());
//            dto.setOutletId(table.getOutletId().getValue());
            tableCreateDtos.add(dto);
        }
        return tableCreateDtos;
    
	}

}

