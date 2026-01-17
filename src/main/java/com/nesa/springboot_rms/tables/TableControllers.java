package com.nesa.springboot_rms.tables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesa.springboot_rms.outlet.application.RecipeApplicationService;
import com.nesa.springboot_rms.outlet.domain.Recipe;
import com.nesa.springboot_rms.table.Api.dtos.TableDto;
import com.nesa.springboot_rms.table.Application.TableApplicationService;

import lombok.Data;

@RestController
@RequestMapping("/tables")
@CrossOrigin(origins = "*") // IMPORTANT for Flutter Web
public class TableControllers {

    private HashMap<Long, CustomerOrder> orders = new HashMap<>();

    @Autowired
    private RecipeApplicationService recipeService;
    @Autowired
    private TableApplicationService tableService;

    private HashMap<Long, Long> tableOrderIdMap = new HashMap<>();

    // @PostMapping("/bulk")
    // public ResponseEntity<List<TableResponseDTO>> createTables(@RequestBody List<TableResponseDTO> requests) {
    //     for (TableResponseDTO request : requests) {
    //         TableResponseDTO tableWithId = TableResponseDTO.builder()
    //                 .id(tables.size() + 1L) // Setting the ID manually here
    //                 .tableNumber(request.getTableNumber())
    //                 .capacity(request.getCapacity())
    //                 .posX(request.getPosX())
    //                 .posY(request.getPosY())
    //                 .status(request.getStatus())
    //                 .outletId(request.getOutletId())
    //                 .build();

    //         tables.add(tableWithId);
    //     }
    //     return new ResponseEntity<>(tables, HttpStatus.CREATED);
    // }

    // @GetMapping
    // public ResponseEntity<List<TableResponseDTO>> getTables() {
    //     return new ResponseEntity<>(tables, HttpStatus.OK);
    // }

    @PostMapping("/ot")
    public String postOT(@RequestBody CustomerOrderToken entity) {
        // 1. Generate a new ID for the main Order
        CustomerOrder order = null;

        // 2. Process the order items (Tokens) to link them to the new orderId
        List<RecipeOrderToken> processedItems = new ArrayList<>();
        if (entity.getOrderItems() != null) {
            List<TableDto> tables = tableService.getAllTables();

            for (TableDto dto : tables) {
                if (dto.getId().equals(entity.getTableId())) {
                    if (tableOrderIdMap.get(dto.getId()) != null && dto.getStatus().equals("OCCUPIED")) {
                        order = orders.get(tableOrderIdMap.get(dto.getId()));
                    } else {
                        order = new CustomerOrder();
                        order.addToken(new CustomerOrderToken());
                        order.setTable(dto);
                        order.setCustomerId(entity.getCustomerId());
                        order.setStatus("ACTIVE");
                        order.setId(orders.size() + 1L);
                    }
                    tableOrderIdMap.put(dto.getId(), order.getId());
                    // dto.addOrder(order);
                }
            }

            for (int i = 0; i < entity.getOrderItems().size(); i++) {
                RecipeOrderToken rawToken = entity.getOrderItems().get(i);
                // Reconstruct the token with an ID and the parent orderId
                Recipe recipe = recipeService.get(rawToken.recipe().getId());
                processedItems.add(new RecipeOrderToken(
                        (long) (processedItems.size() + 100), // Temporary item ID
                        recipe,
                        order.getId(),
                        rawToken.qty(),
                        "ORDERED"));
            }
            CustomerOrderToken savedOrder = new CustomerOrderToken();
            savedOrder.setId(order.getId());
            savedOrder.setOrderItems(processedItems);
            savedOrder.setStatus("ACTIVE");

            order.addToken(savedOrder);
            orders.put(order.getId(), order);

        }

        return "Success";
    }

    @GetMapping("/orders/{status}")
    public ResponseEntity<List<CustomerOrder>> getOrders(@PathVariable String status) {
        return ResponseEntity.ok(orders.values().stream().filter(order -> order.getStatus().equals(status))
                .collect(Collectors.toList()));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<CustomerOrder> getOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orders.get(orderId));
    }

    @GetMapping("/ot")
    public ResponseEntity<List<CustomerOrderToken>> getOrderItems() {
        List<CustomerOrderToken> listOut = new ArrayList<>();
        for(CustomerOrder order : orders.values()) {
            if(order.getStatus().equals("ACTIVE") && order.getOrderItems().size() > 0) {
                for(CustomerOrderToken token: order.getOrderItems()) {
                    // if(token.getStatus().equals("ACTIVE")) {
                        listOut.add(token);
                    // }
                }
            }
        }
        return ResponseEntity.ok(listOut);
    }
}

// @Builder
// @Data
// class TableResponseDTO implements Serializable {
//     public void addOrder(CustomerOrder order) {
//         this.currentOrderId = order.getId();
//         this.status = "OCCUPIED";
//     }

//     Long id;
//     String tableNumber;
//     Integer capacity;
//     Double posX;
//     Double posY;
//     String status;
//     Long outletId;
//     Long currentOrderId;
// }

@Data
class CustomerOrder implements Serializable {
    Long id;
    TableDto table;
    Long customerId;
    String status;
    List<CustomerOrderToken> orderItems = new ArrayList<>();

    public CustomerOrderToken addToken(CustomerOrderToken token) {
        this.orderItems.add(token);
        return token;
    }
}

@Data
class CustomerOrderToken implements Serializable {

    Long id;
    Long orderId;
    List<RecipeOrderToken> orderItems;
    String status;

    // Transient
    Long tableId;
    Long customerId;

    public boolean setActive() {
        this.status = "ACTIVE";
        return true;
    }

    public boolean setInactive() {
        this.status = "INACTIVE";
        return true;
    }
}

record RecipeOrderToken(
        Long id,
        Recipe recipe,
        Long orderId,
        int qty,
        String status) implements Serializable {
}
