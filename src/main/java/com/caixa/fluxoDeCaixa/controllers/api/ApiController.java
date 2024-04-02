package com.caixa.fluxoDeCaixa.controllers.api;

import com.caixa.fluxoDeCaixa.dao.CaixaDao;
import com.caixa.fluxoDeCaixa.enums.Status;
import com.caixa.fluxoDeCaixa.models.Caixa;
import com.caixa.fluxoDeCaixa.models.dto.ResumoCaixa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE })
public class ApiController {

    @Autowired
    private final CaixaDao caixaDao;

    public ApiController(CaixaDao caixaDao) {
        this.caixaDao = caixaDao;
    }

    @GetMapping("/caixas")
    public List<Caixa> getCaixas() {
        return caixaDao.findAll();
    }

    @PostMapping("/caixas")
    public Caixa cadastrarCaixa(@RequestBody Caixa caixa) {
        return caixaDao.save(caixa);
    }

    @DeleteMapping("/caixas/{id}")
    public void excluirCaixa(@PathVariable int id) {
        caixaDao.deleteById(id);
    }

    @GetMapping("/caixas/resumo")
    public ResumoCaixa getResumoCaixa() {
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
        return new ResumoCaixa(caixas, totalReceitas, totalDespesas, valorTotal);
    }
}
