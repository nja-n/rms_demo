package com.nesa.springboot_rms.table.Api;

import com.nesa.springboot_rms.table.Api.dtos.TableCreateDto;
import com.nesa.springboot_rms.table.Application.TableApplicationService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/tables")
@RequiredArgsConstructor
public class TableController {

    private final TableApplicationService tableService;

    // Endpoint to assign a waiter and occupy a table
    @PatchMapping("/{tableId}/occupy")
    public ResponseEntity<Void> occupyTable(
            @PathVariable Long tableId,
            @RequestParam String waiterId) {

        tableService.assignAndOccupy(tableId, waiterId);

        return ResponseEntity.noContent().build();
    }

    // Endpoint to release/clear a table
    @PostMapping("/{tableId}/release")
    public ResponseEntity<Void> releaseTable(@PathVariable Long tableId) {
        tableService.releaseTable(tableId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createTables(@RequestBody List<TableCreateDto> requests) {
        tableService.createTables(requests);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/all")
    public ResponseEntity<List<TableCreateDto>> getAllTables() {
        List<TableCreateDto> tables = tableService.getAllTables();
        return ResponseEntity.ok(tables);
    }

}
