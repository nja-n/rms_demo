package com.nesa.springboot_rms.table.Api;

import java.util.List;

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

import com.nesa.springboot_rms.table.Api.dtos.TableDto;
import com.nesa.springboot_rms.table.Application.TableApplicationService;

import lombok.RequiredArgsConstructor;

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

    // @PostMapping("/create")
    // public ResponseEntity<List<Table>> createTables(@RequestBody List<Table>
    // tables) {
    // return ResponseEntity.ok(tableService.createTables(tables));
    // }
    @PostMapping("/create")
    public ResponseEntity<Void> createTables(@RequestBody List<TableDto> requests) {
        tableService.createTables(requests);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/all")
    public ResponseEntity<List<TableDto>> getAllTables() {
        List<TableDto> tables = tableService.getAllTables();
        return ResponseEntity.ok(tables);
    }

}
