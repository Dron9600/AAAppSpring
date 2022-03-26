package kz.prudnikov.airAstanaAppSpring.controllers;

import kz.prudnikov.airAstanaAppSpring.engines.Separator;
import kz.prudnikov.airAstanaAppSpring.getterSetter.Data;
import kz.prudnikov.airAstanaAppSpring.getterSetter.RoutesData;
import kz.prudnikov.airAstanaAppSpring.database.DataBase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class MainController {

    DataBase dataBase = new DataBase();
    Separator separator = new Separator();
    String countries = null;
    List<String> countriesList = new ArrayList<>();

    @GetMapping()
    public String mainPage(Model model) {
      //  model.addAttribute("data", new Data());
        return "mainPage";
    }

    @PostMapping()
    public String mainPageSubmit(/*@ModelAttribute Data data,*/ @RequestParam(value ="section") String secVal, Model model){
//        model.addAttribute("data", data);

        try {
            countries = dataBase.countres(secVal);
            System.out.println(" counties " + dataBase.countres(secVal));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        countriesList.addAll(separator.countriesSeparator(countries));
        for (String s : countriesList) {
            System.out.println(s);
        }
//        String countest = countriesList.get(1);
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

    @ModelAttribute("message")
    public List<String> messages(Model model){
        model.addAttribute("testlist", countriesList);
        return countriesList;
    }


    @PostMapping("/huy")
    public String  testPageSub(Model model/*, @RequestParam("se") List<String> se*/, HttpServletRequest request){
        String[] gr = request.getParameterValues("mess");
        boolean butt = false;
     //   model.addAttribute("answer", butt);
        System.out.println(" Hall"  + butt);
        return "mainPage";
    }
}
