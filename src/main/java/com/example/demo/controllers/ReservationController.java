package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Models.dto.ReservationDTO;
import com.example.demo.services.ReservationService;

@Controller
@RequestMapping("reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @GetMapping
    public String get(Model model){
        model.addAttribute("reservations", reservationService.getAll());
        return "reservation/index";
    }

    @GetMapping("form")
    public String form(Model model) {
        model.addAttribute("reservationDTO", new ReservationDTO());
        return "reservation/form";
    }

    @PostMapping("save")
    public String save(ReservationDTO reservationDTO) {
        boolean result = reservationService.save(reservationDTO);

        if(result){
            return "redirect:/reservation";
        }
        return "reservation/form";
    }

    // @GetMapping("edit/{id}") 
    // public String edit(@PathVariable Integer id, Model model) { 
    //     Reservation reservation = reservationService.get(id);
    //     ReservationDTO reservationDTO = new ReservationDTO();

    //     reservationDTO.setId(reservation.getId());
    //     reservationDTO.setEmployeeId(reservation.getEmployee().getId());;
    //     reservationDTO.setRoomId(reservation.getRoom().getId());
    //     reservationDTO.setSubmitDate(reservation.getSubmitDate());
    //     reservationDTO.setPurpose(reservation.getPurpose());
    //     reservationDTO.setReservationDate(reservation.getReservationDate());
    //     reservationDTO.setStartTime(reservation.getStartTime());
    //     reservationDTO.setEndTime(reservation.getEndTime());
    //     reservationDTO.setApprovalStatus(reservation.getApprovalStatus());

    //     if (reservation.getApprovedBy() != null) {
    //         reservationDTO.setApprovedBy(reservation.getApprovedBy().getId());
    //     } else {
    //         reservationDTO.setApprovedBy(null);
    //     }

    //     model.addAttribute("reservationDTO", reservationDTO); 
    //     return "reservation/form"; 
    // }

    @PostMapping("delete/{id}") 
    public String delete(@PathVariable Integer id) {
        reservationService.remove(id);
        return "redirect:/reservation";
    }
}