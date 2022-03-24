package kz.prudnikov.airAstanaAppSpring.controllers;

import kz.prudnikov.airAstanaAppSpring.data.Data;
import kz.prudnikov.airAstanaAppSpring.data.RoutesData;
import kz.prudnikov.airAstanaAppSpring.database.DataBase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class MainController {

    DataBase dataBase = new DataBase();

    @GetMapping()
    public String mainPage(Model model) {
      //  model.addAttribute("data", new Data());
        return "mainPage";
    }

    @PostMapping()
    public String mainPageSubmit(@ModelAttribute Data data, @RequestParam(value ="section") String secVal, Model model){
//        model.addAttribute("data", data);
        System.out.println(" without String " + secVal);
        try {
            System.out.println(" counties " + dataBase.countres(secVal));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "secondPage";
    }


    @ModelAttribute("routes")
    public List getRoutes(){
        List<RoutesData> directionList = new ArrayList<RoutesData>();
        try {
            for (int i = 0; i < dataBase.direction().size(); i++) {
                directionList.add(new RoutesData((String) dataBase.direction().get(i)));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return directionList;
    }


}
