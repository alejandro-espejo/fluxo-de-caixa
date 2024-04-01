package com.caixa.fluxoDeCaixa.controllers;

import com.caixa.fluxoDeCaixa.dao.CaixaDao;
import com.caixa.fluxoDeCaixa.enums.Status;
import com.caixa.fluxoDeCaixa.models.Caixa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;
import java.util.List;

@Controller
public class HomeController {

    private final CaixaDao caixaDao;

    public HomeController(CaixaDao caixaDao) {
        this.caixaDao = caixaDao;
    }

    @GetMapping("/")
    public String index(Model model) {

        List<Caixa> caixas = caixaDao.findAll();

        float totalReceitas = 0;
        float totalDespesas = 0;

        for (Caixa caixa : caixas) {
            if (caixa.getStatus() == Status.RECEITA) {
                totalReceitas += caixa.getValor();
            } else if (caixa.getStatus() == Status.DESPESA) {
                totalDespesas += caixa.getValor();
            }
        }

        float valorTotal = totalReceitas - totalDespesas;

        DecimalFormat df = new DecimalFormat("#,##0.00");

        model.addAttribute("caixas", caixas);
        model.addAttribute("totalReceitas", df.format(totalReceitas));
        model.addAttribute("totalDespesas", df.format(totalDespesas));
        model.addAttribute("valorTotal", df.format(valorTotal));

        return "home/index";
    }

    @GetMapping("/adicionar")
    public String adicionar() {
        return "home/adicionar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        caixaDao.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@RequestParam("tipo") String tipo,
                            @RequestParam("valor") float valor,
                            @RequestParam("status") int status, Model model) {
        Caixa caixa = new Caixa();
        caixa.setTipo(tipo);
        caixa.setValor(valor);
        caixa.setStatus(Status.fromCode(status));
        caixaDao.save(caixa);
        return "redirect:/";
    }
}
