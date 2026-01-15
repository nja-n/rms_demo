package com.nesa.springboot_rms.table.Application;

import com.nesa.springboot_rms.outlet.domain.extention.OutletId;
import com.nesa.springboot_rms.table.Api.dtos.TableDto;
import com.nesa.springboot_rms.table.Domain.Table;
import com.nesa.springboot_rms.table.Domain.TableId;
import com.nesa.springboot_rms.table.Domain.TableRepository;
import com.nesa.springboot_rms.table.Domain.TableStatus;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TableApplicationService {

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

        table.release();

        tableRepository.save(table);
    }

    @Transactional
    public void createTables(List<TableDto> requests) {

        List<Table> domainTables = new ArrayList<>();
        for(TableDto request : requests){
            domainTables.add(new Table(
                null, // We generate the ID, don't trust the JSON
                request.getCapacity(),
                TableStatus.fromString(request.getStatus()),
                request.getName(),
                request.getPosX(),
                request.getPosY(),
                new OutletId(request.getOutletId())
                // Map other fields here...
            ));
        }
        tableRepository.saveAll(domainTables);
    }

	public List<TableDto> getAllTables() {
		List<Table> tables = tableRepository.findAll();

        List<TableDto> tableDtos = new ArrayList<>();
        for(Table table : tables){
            TableDto dto=new TableDto();
            dto.setCapacity(table.getCapacity());
            dto.setId(table.getId().value());
            dto.setStatus(table.getStatus().name());
            dto.setName(table.getName());
            dto.setPosX(table.getPosX());
            dto.setPosY(table.getPosY());
            dto.setOutletId(table.getOutletId().getValue());
            tableDtos.add(dto);
        }
        return tableDtos;
    
	}

}

