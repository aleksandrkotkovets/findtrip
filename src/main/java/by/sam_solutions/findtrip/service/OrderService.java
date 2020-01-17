package by.sam_solutions.findtrip.service;

import by.sam_solutions.findtrip.controller.dto.OrderCreateUpdateDTO;
import by.sam_solutions.findtrip.controller.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO add(OrderCreateUpdateDTO orderDTO);

    List<OrderDTO> getOrdersByUserId(Long id);

    OrderDTO findById(Long id);

    void deleteTicketsOnFlightByUSer(OrderCreateUpdateDTO order);

    OrderDTO takeMoreTickets(OrderCreateUpdateDTO order);
}
