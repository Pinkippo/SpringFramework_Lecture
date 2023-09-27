package inhatc.spring.shop.controller;


import inhatc.spring.shop.dto.ItemDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeLeafController {

    @GetMapping("/thyme/ex1")
    public String ex1(Model model) {

        ItemDto itemDto = ItemDto.builder()
                .itemNm("최신 스프링")
                .price(20000)
                .itemDetail("스프링부트 3.1.4")
                .itemSellStatus("SELL")
                .build();

        model.addAttribute("itemDto", itemDto);

        return "thyme/ex1";
    }

}
